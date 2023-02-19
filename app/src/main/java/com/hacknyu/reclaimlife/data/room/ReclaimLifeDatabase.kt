package com.hacknyu.reclaimlife.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hacknyu.reclaimlife.model.Quotes

@Database(
    entities = [
        Quotes::class
    ], version = 1
)
abstract class ReclaimLifeDatabase : RoomDatabase() {

    abstract fun quotesDao(): QuotesDao

    companion object {
        @Volatile
        private var instance: ReclaimLifeDatabase? = null
        fun getInstance(context: Context): ReclaimLifeDatabase {
            return instance ?: synchronized(this) {
                buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): ReclaimLifeDatabase {
            return Room.databaseBuilder(context, ReclaimLifeDatabase::class.java, "reclaim-db")
                .build()
        }
    }
}