package com.hacknyu.reclaimlife.data.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {

    val AUTH_TOKEN = stringPreferencesKey(name = "authToken")
    val USERNAME = stringPreferencesKey(name = "username")
}