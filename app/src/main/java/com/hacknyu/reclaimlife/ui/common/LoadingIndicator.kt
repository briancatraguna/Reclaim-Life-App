package com.hacknyu.reclaimlife.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hacknyu.reclaimlife.ui.theme.LightBlue

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(35.dp)
                .align(Alignment.Center)
            ,
            color = LightBlue
        )
    }
}

@Composable
fun LoadingIndicatorIcon(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier,
        color = LightBlue
    )
}