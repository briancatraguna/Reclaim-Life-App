package com.hacknyu.reclaimlife.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "quotes")
data class Quotes(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "quote")
    @SerializedName("q")
    val quote: String = "",
    @ColumnInfo(name = "author")
    @SerializedName("a")
    val author: String = "",
    @ColumnInfo(name = "imgUrl")
    @SerializedName("i")
    val imgUrl: String = "",
)
