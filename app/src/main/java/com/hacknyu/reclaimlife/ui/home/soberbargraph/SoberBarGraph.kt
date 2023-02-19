package com.hacknyu.reclaimlife.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hacknyu.reclaimlife.ui.theme.LightGreen
import com.hacknyu.reclaimlife.ui.theme.ReclaimLifeTheme
import com.hacknyu.reclaimlife.ui.theme.Typography

@Composable
fun SoberBarGraph(
    data: Map<Float, String>,
) {
    val context = LocalContext.current
    val maxValue = 7
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(50.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(text = maxValue.toString())
                    Spacer(modifier = Modifier.fillMaxHeight())
                }
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(text = (maxValue / 2).toString())
                    Spacer(modifier = Modifier.fillMaxHeight(0.5f))
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(2.dp)
                    .background(Color.Black)
            )

            data.forEach {
                Box(
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .width(20.dp)
                        .fillMaxHeight(it.key)
                        .background(LightGreen)
                        .clickable {
                        }
                )
            }

        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
            .background(Color.Black))
        
        Row(modifier = Modifier
            .padding(start = 72.dp)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Labels for each day
            data.values.forEach { 
                Text(
                    modifier = Modifier.width(20.dp),
                    text = it.toString(),
                    textAlign = TextAlign.Center,
                    style = Typography.subtitle2
                )
            }
            
        }
    }

}

@Preview
@Composable
fun SoberBarGroupPreview() {
    ReclaimLifeTheme {
        SoberBarGraph(data = mapOf(
            Pair(0.5f, "Mon"),
            Pair(0.6f, "Tue"),
            Pair(0.2f, "Wed"),
            Pair(0.7f, "Thu"),
            Pair(0.8f, "Fri"),
            Pair(0.9f, "Sat"),
            Pair(0.1f, "Sun"),
        ))
        
    }
}