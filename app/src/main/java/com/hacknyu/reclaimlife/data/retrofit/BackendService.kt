package com.hacknyu.reclaimlife.data.retrofit

import com.hacknyu.reclaimlife.model.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface BackendService {

    @GET("blogs")
    suspend fun getThreads(): List<ThreadsList>

    @POST("blogs/{threadId}/comments")
    suspend fun addComment(
        @Header("Authorization") token: String,
        @Path("threadId") threadId: String,
        @Body comment: NewComment,
    ): Threads

    @POST("blogs/new-blog")
    suspend fun addThread(
        @Header("Authorization") token: String,
        @Body newThread: NewThread
    ): ThreadsList

    @GET("blogs/{threadId}")
    suspend fun getThreadDetail(
        @Path("threadId") threadId: String
    ): Threads

    @POST("user/register")
    suspend fun registerUser(
        @Body user: User
    ): User

    @POST("user/login")
    suspend fun loginUser(
        @Body user: User
    ): User
}