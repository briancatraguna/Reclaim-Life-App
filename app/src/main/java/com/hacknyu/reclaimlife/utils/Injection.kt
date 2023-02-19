package com.hacknyu.reclaimlife.utils

import android.content.Context
import com.hacknyu.reclaimlife.data.Repository

object Injection {

    fun provideRepository(context: Context): Repository {
        return Repository.getInstance(context)
    }
}