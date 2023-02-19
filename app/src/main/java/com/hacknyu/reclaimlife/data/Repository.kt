package com.hacknyu.reclaimlife.data

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import com.hacknyu.reclaimlife.data.datastore.PreferenceKeys
import com.hacknyu.reclaimlife.data.datastore.dataStore
import com.hacknyu.reclaimlife.data.retrofit.BackendService
import com.hacknyu.reclaimlife.data.retrofit.QuotesService
import com.hacknyu.reclaimlife.data.retrofit.RestClient
import com.hacknyu.reclaimlife.data.room.QuotesDao
import com.hacknyu.reclaimlife.data.room.ReclaimLifeDatabase
import com.hacknyu.reclaimlife.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class Repository(val context: Context) {

    private val quotesService: QuotesService = RestClient.quotesService

    private val backendService: BackendService = RestClient.backendService

    private val quotesDao: QuotesDao

    init {
        val db = ReclaimLifeDatabase.getInstance(context)
        quotesDao = db.quotesDao()
    }

    fun getRandomActivity(): Activities {
        return activitiesList.random()
    }

    suspend fun saveUserName(userName: String){
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.USERNAME] = userName
        }
    }

    fun getUsername(): Flow<String> = context.dataStore.data
        .map { preferences -> preferences[PreferenceKeys.USERNAME] ?: ""}

    fun getAuthToken(): Flow<String> = context.dataStore.data
        .map { preferences -> preferences[PreferenceKeys.AUTH_TOKEN] ?: "" }

    suspend fun saveAuthToken(authToken: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.AUTH_TOKEN] = authToken
        }
    }

    suspend fun registerUser(user: User): Flow<Boolean> = flow {
        try {
            backendService.registerUser(user)
            emit(true)
        } catch (e: java.lang.Exception) {
            emit(false)
        }
    }

    suspend fun loginUser(user: User): Flow<Boolean> = flow {
        try {
            val userObj = backendService.loginUser(user)
            val authToken = userObj.authToken
            val username = userObj.userName
            if (authToken.isBlank().not()) {
                saveAuthToken(authToken)
                saveUserName(username)
                emit(true)
            }
        } catch (e: java.lang.Exception) {
            emit(false)
        }
    }

    suspend fun addComment(threadId: String, comment: NewComment) = flow {
        try {
            getAuthToken().collect { token ->
                if (token.isNotEmpty()) {
                    backendService.addComment(
                        token = token,
                        threadId = threadId,
                        comment = comment
                    )
                    emit(true)
                } else {
                    emit(false)
                }
            }
        } catch (e: java.lang.Exception) {
            emit(false)
        }
    }

    suspend fun addThread(newThread: NewThread) = flow {
        try {
            getAuthToken().collect { token ->
                if (token.isNotEmpty()) {
                    backendService.addThread(
                        token = token,
                        newThread = newThread
                    )
                    emit(true)
                } else {
                    emit(false)
                }
            }
        } catch (e: java.lang.Exception) {
            emit(false)
        }
    }

    suspend fun getThreadDetail(threadId: String): Flow<Threads> = flow {
        try {
            emit(backendService.getThreadDetail(threadId))
        } catch (e: java.lang.Exception) {
            println(e.message)
            Log.e("ERROR", e.message.orEmpty())
        }
    }

    suspend fun getThreads(): Flow<List<ThreadsList>> = flow {
        try {
            emit(backendService.getThreads())
        } catch (e: java.lang.Exception) {
            Log.e("ERROR", e.message.orEmpty())
        }
    }

    suspend fun clearDataStore() {
        context.dataStore.edit { preferences -> preferences.clear() }
    }

    suspend fun getQuotes(): Flow<List<Quotes>> = flow {
        val localQuotes = quotesDao.getQuotes()
        emit(localQuotes)
        try {
            val remoteQuotes = quotesService.getQuotes()
            emit(remoteQuotes)
            quotesDao.insertAllQuotes(remoteQuotes)
        } catch (e: java.lang.Exception) {
            Log.e("ERROR", e.message.orEmpty())
        }
    }

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(context: Context): Repository =
            instance ?: synchronized(this) {
                Repository(context).apply {
                    instance = this
                }
            }
    }
}