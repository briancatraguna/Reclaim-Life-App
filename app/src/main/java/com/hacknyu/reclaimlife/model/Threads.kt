package com.hacknyu.reclaimlife.model

import com.google.gson.annotations.SerializedName

data class Threads(
    @SerializedName("_id")
    val id: String = "",
    @SerializedName("author_name")
    val authorName: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("blog")
    val content: String = "",
    @SerializedName("tags")
    val tags: List<String> = listOf(),
    @SerializedName("comments")
    val comments: List<Comment> = listOf(),
)

data class ThreadsList(
    @SerializedName("_id")
    val id: String = "",
    @SerializedName("author_name")
    val authorName: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("blog")
    val content: String = "",
    @SerializedName("tags")
    val tags: List<String> = listOf(),
    @SerializedName("comments")
    val comments: List<String> = listOf(),
)

data class Comment(
    @SerializedName("_id")
    val id: String,
    @SerializedName("text")
    val text: String,
)


