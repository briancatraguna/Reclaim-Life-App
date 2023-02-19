package com.hacknyu.reclaimlife.ui.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hacknyu.reclaimlife.ui.home.dateheader.Title
import com.hacknyu.reclaimlife.ui.theme.Typography

@Composable
fun SettingsScreen(onLogout: () -> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable { onLogout() }) {
        Row(modifier = Modifier.padding(15.dp)) {
            Image(imageVector = Icons.Default.Logout, contentDescription = null, modifier = Modifier.padding(end = 10.dp))
            Title(text = "Logout", color = Color.Red, style = Typography.h2)
        }
        Divider(modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth())
    }
}