package com.hacknyu.reclaimlife.ui.motivation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hacknyu.reclaimlife.ui.ViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.SubcomposeAsyncImage
import com.hacknyu.reclaimlife.R
import com.hacknyu.reclaimlife.ui.common.CardWithTitleAndIcon
import com.hacknyu.reclaimlife.ui.common.LoadingIndicator
import com.hacknyu.reclaimlife.ui.home.dateheader.Title
import com.hacknyu.reclaimlife.ui.motivation.maincard.MotivationMainCard
import com.hacknyu.reclaimlife.ui.theme.Turquoise
import com.hacknyu.reclaimlife.ui.theme.Typography
import com.hacknyu.reclaimlife.utils.Injection
import com.hacknyu.reclaimlife.utils.from


@Composable
fun MotivationScreen(
    viewModel: MotivationViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository(LocalContext.current.applicationContext)
        )
    )
) {
    viewModel.motivationViewState.collectAsState().value.let { motivationViewState ->
        LazyColumn(modifier = Modifier.padding(horizontal = 32.dp)) {
            item {
                Box(modifier = Modifier.wrapContentSize()) {
                    if (motivationViewState.quotes.isEmpty()) {
                        LoadingIndicator()
                    } else {
                        Canvas(modifier = Modifier.align(Alignment.TopEnd)) {
                            drawCircle(Turquoise, radius = 100.dp.toPx())
                        }
                        Canvas(modifier = Modifier.align(Alignment.TopStart)) {
                            drawCircle(Turquoise, radius = 100.dp.toPx())
                        }
                        MotivationMainCard(quote = motivationViewState.quotes.first())
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            if (motivationViewState.quotes.isNotEmpty()) {
                items(motivationViewState.quotes.from(1)) { quote ->
                    CardWithTitleAndIcon(
                        icon = R.drawable.motivation,
                        titleText = quote.author,
                        modifier = Modifier.padding(vertical = 10.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(
                                start = 10.dp,
                                bottom = 15.dp
                            )
                        ) {
                            Card(
                                modifier = Modifier
                                    .size(75.dp),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                SubcomposeAsyncImage(
                                    model = quote.imgUrl,
                                    contentScale = ContentScale.Crop,
                                    contentDescription = quote.quote,
                                    modifier = Modifier.fillParentMaxSize(),
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
                            }

                            Title(
                                text = quote.quote,
                                style = Typography.body1,
                                modifier = Modifier.padding(horizontal = 15.dp),
                                color = Color.Black
                            )
                        }

                    }
                }
            }
        }
    }

}