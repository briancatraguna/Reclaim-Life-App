package com.hacknyu.reclaimlife.ui.common.background

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hacknyu.reclaimlife.ui.theme.LightBlue
import com.hacknyu.reclaimlife.ui.theme.ReclaimLifeTheme

@Composable
fun HomeBackground() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.align(Alignment.TopEnd)) {
            translate() {
                drawCircle(LightBlue, radius = 200.dp.toPx())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeBackgroundPreview() {
    ReclaimLifeTheme {
        HomeBackground()
    }
}