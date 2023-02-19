package com.hacknyu.reclaimlife.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hacknyu.reclaimlife.R
import com.hacknyu.reclaimlife.ui.home.dateheader.Title
import com.hacknyu.reclaimlife.ui.theme.ReclaimLifeTheme
import com.hacknyu.reclaimlife.ui.theme.Typography

@Composable
fun CardWithTitleAndIcon(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    titleText: String,
    backgroundColor: Color = Color.White,
    style: TextStyle = Typography.h2,
    content: @Composable () -> Unit
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp,
        backgroundColor = backgroundColor,

    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(15.dp)
                        .size(30.dp),
                    alignment = Alignment.Center
                )
                Title(
                    text = titleText,
                    style = style,
                    modifier = Modifier.align(Alignment.CenterVertically).padding(end = 15.dp)
                )
            }
            content()
        }

    }
}

@Preview
@Composable
fun CardWithTitleAndIconPreview() {
    ReclaimLifeTheme {
        CardWithTitleAndIcon(
            icon = R.drawable.no_drugs_icon,
            titleText = "Days of Sober"
        ) {
            Text(text = "Hello")
        }
    }
}