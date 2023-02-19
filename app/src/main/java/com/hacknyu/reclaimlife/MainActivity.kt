package com.hacknyu.reclaimlife

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.hacknyu.reclaimlife.data.datastore.PreferenceKeys
import com.hacknyu.reclaimlife.data.datastore.dataStore
import com.hacknyu.reclaimlife.ui.register.RegisterActivity
import com.hacknyu.reclaimlife.ui.theme.ReclaimLifeTheme
import com.hacknyu.reclaimlife.utils.Injection
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    @OptIn(DelicateCoroutinesApi::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        GlobalScope.launch {
            if (isLoggedIn().not()) {
                startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
                finish()
                cancel()
            }
        }

        setContent {
            ReclaimLifeTheme {
                ReclaimLifeApp(onLogout = {
                    val repository = Injection.provideRepository(this)
                    GlobalScope.launch {
                        repository.clearDataStore()
                    }
                    startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
                    finish()
                })
            }
        }
    }

    private suspend fun isLoggedIn(): Boolean {
        return !this.dataStore.data.first()[PreferenceKeys.AUTH_TOKEN].isNullOrBlank()
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ReclaimLifeTheme {

    }
}