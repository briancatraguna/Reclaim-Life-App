package com.hacknyu.reclaimlife.ui.home.dateheader

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.hacknyu.reclaimlife.ui.theme.Typography

@Composable
fun Title(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = Typography.body1,
    color: Color = Color.Black
) {
    Box(modifier = modifier) {
        Text(text = text, style = style, color = color)
    }

}