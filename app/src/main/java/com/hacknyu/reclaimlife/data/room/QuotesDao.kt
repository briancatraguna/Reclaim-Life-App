package com.hacknyu.reclaimlife.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hacknyu.reclaimlife.model.Quotes

@Dao
interface QuotesDao {

    @Query("SELECT * FROM quotes")
    suspend fun getQuotes(): List<Quotes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllQuotes(quotes: List<Quotes>)

}