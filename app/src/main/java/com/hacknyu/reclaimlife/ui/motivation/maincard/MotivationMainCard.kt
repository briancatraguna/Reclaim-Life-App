package com.hacknyu.reclaimlife.ui.motivation.maincard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.hacknyu.reclaimlife.R
import com.hacknyu.reclaimlife.model.Quotes
import com.hacknyu.reclaimlife.ui.common.LoadingIndicator
import com.hacknyu.reclaimlife.ui.home.dateheader.Title
import com.hacknyu.reclaimlife.ui.theme.Typography

@Composable
fun MotivationMainCard(modifier: Modifier = Modifier, quote: Quotes) {
    Card(
        modifier = modifier
            .padding(top = 30.dp)
            .height(300.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp
    ) {
        Box(modifier = modifier.fillMaxSize()) {
            SubcomposeAsyncImage(
                model = quote.imgUrl,
                contentScale = ContentScale.Crop,
                contentDescription = quote.quote,
                modifier = Modifier.fillMaxSize(),
                loading = {
                    LoadingIndicator()
                },
                error = {
                    Image(
                        painter = painterResource(id = R.drawable.image_error_placeholder),
                        contentDescription = null
                    )
                }
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
            )
            Title(
                text = quote.quote,
                style = Typography.h1,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(32.dp),
                color = Color.White
            )
            Title(
                text = "- ${quote.author} -",
                style = Typography.h2,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 20.dp, bottom = 20.dp),
                color = Color.White
            )
        }

    }
}