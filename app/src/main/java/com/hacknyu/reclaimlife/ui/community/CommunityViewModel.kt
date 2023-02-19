package com.hacknyu.reclaimlife.ui.community

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hacknyu.reclaimlife.data.Repository
import com.hacknyu.reclaimlife.model.NewThread
import com.hacknyu.reclaimlife.model.Threads
import com.hacknyu.reclaimlife.model.ThreadsList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CommunityViewModel(
    private val repository: Repository
): ViewModel() {

    private val _communityViewState: MutableStateFlow<CommunityViewState> = MutableStateFlow(CommunityViewState())
    val communityViewState: StateFlow<CommunityViewState> get() = _communityViewState

    init {
        getAuthToken()
        getThreads()
        getUsername()
    }

    fun addThread(title: String, content: String) {
        viewModelScope.launch {
            val userName = _communityViewState.value.username
            repository.addThread(NewThread(
                title = title,
                content = content,
                authorName = userName
            )).collect {
                getThreads()
            }
        }
    }

    private fun getUsername() {
        viewModelScope.launch {
            repository.getUsername().collect { userName ->
                _communityViewState.value = _communityViewState.value.copy(
                    username = userName
                )
            }
        }
    }

    private fun getThreads() {
        viewModelScope.launch {
            repository.getThreads().collect { threads ->
                _communityViewState.value = _communityViewState.value.copy(
                    threads = threads,
                    isThreadLoading = false
                )
            }
        }
    }

    private fun getAuthToken() {
        viewModelScope.launch {
            repository.getAuthToken().collect { authToken ->
                _communityViewState.value = _communityViewState.value.copy(
                    authToken = authToken
                )
            }
        }
    }
}

data class CommunityViewState(
    var authToken: String = "",
    var threads: List<ThreadsList> = listOf(),
    var isThreadLoading: Boolean = true,
    var username: String = ""
)