package com.hacknyu.reclaimlife.model

import com.google.gson.annotations.SerializedName

data class NewThread(
    @SerializedName("title")
    val title: String,
    @SerializedName("blog")
    val content: String,
    @SerializedName("author_name")
    val authorName: String
)
