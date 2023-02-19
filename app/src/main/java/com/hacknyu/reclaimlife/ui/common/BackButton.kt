package com.hacknyu.reclaimlife.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hacknyu.reclaimlife.R

@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier
) {
    Card(
        modifier = modifier
            .clickable { onClick() }
            .height(44.dp)
            .width(44.dp)
        ,
        backgroundColor = Color.White,
        shape = CircleShape,
        elevation = 5.dp
    ) {
        Image(
            painter = painterResource(id = R.drawable.back_button),
            contentDescription = null,
            contentScale = ContentScale.Inside,
            modifier = Modifier.padding(12.dp)
        )
    }
}