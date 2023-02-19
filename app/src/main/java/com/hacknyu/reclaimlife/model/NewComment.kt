package com.hacknyu.reclaimlife.model

import com.google.gson.annotations.SerializedName

data class NewComment(
    @SerializedName("text")
    val text: String = "",
    @SerializedName("author")
    val author: String = ""
)
