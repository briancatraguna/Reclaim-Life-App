package com.hacknyu.reclaimlife.ui.launch

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.hacknyu.reclaimlife.MainActivity
import com.hacknyu.reclaimlife.data.datastore.PreferenceKeys
import com.hacknyu.reclaimlife.data.datastore.dataStore
import com.hacknyu.reclaimlife.ui.register.RegisterActivity
import com.hacknyu.reclaimlife.utils.Injection
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LaunchActivity : ComponentActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        GlobalScope.launch {
            if (isLoggedIn()) {
                startActivity(Intent(this@LaunchActivity, MainActivity::class.java))
            } else {
                startActivity(Intent(this@LaunchActivity, RegisterActivity::class.java))
            }
            finish()
            cancel()
        }
    }

    private suspend fun isLoggedIn(): Boolean {
        return !this.dataStore.data.first()[PreferenceKeys.AUTH_TOKEN].isNullOrBlank()
    }
}