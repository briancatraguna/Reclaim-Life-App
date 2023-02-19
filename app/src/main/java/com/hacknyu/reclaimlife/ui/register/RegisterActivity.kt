package com.hacknyu.reclaimlife.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.hacknyu.reclaimlife.MainActivity

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            RegisterApp(onLogin = {
                startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                finish()
            })
        }
    }
}