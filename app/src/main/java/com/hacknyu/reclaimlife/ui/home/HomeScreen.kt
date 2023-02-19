package com.hacknyu.reclaimlife.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hacknyu.reclaimlife.R
import com.hacknyu.reclaimlife.model.GroupType
import com.hacknyu.reclaimlife.model.PhysicalActivityLevel
import com.hacknyu.reclaimlife.ui.ViewModelFactory
import com.hacknyu.reclaimlife.ui.common.CardWithTitleAndIcon
import com.hacknyu.reclaimlife.ui.home.dateheader.Title
import com.hacknyu.reclaimlife.ui.theme.Turquoise
import com.hacknyu.reclaimlife.ui.theme.Typography
import com.hacknyu.reclaimlife.utils.DateUtils
import com.hacknyu.reclaimlife.utils.Injection

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository(LocalContext.current.applicationContext)
        )
    )
) {
    val context = LocalContext.current
    viewModel.homeViewState.collectAsState().value.let { homeViewState ->
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .clip(RoundedCornerShape(bottomEnd = 40.dp, bottomStart = 40.dp))
                    .background(Turquoise)
                    .height(110.dp)
                    .fillMaxWidth()
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    Column {
                        Title(
                            text = DateUtils.getCurrentDate(),
                            modifier = Modifier.padding(vertical = 20.dp, horizontal = 32.dp)
                        )
                        Title(
                            text = context.resources.getString(R.string.reclaim_life_title),
                            style = Typography.h1,
                            modifier = Modifier
                                .padding(vertical = 10.dp, horizontal = 32.dp)
                        )
                        CardWithTitleAndIcon(
                            icon = R.drawable.no_drugs_icon,
                            titleText = context.resources.getString(R.string.days_of_sober_title),
                            modifier = Modifier
                                .padding(vertical = 20.dp, horizontal = 32.dp)
                                .height(120.dp)
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Image(
                                    painter = painterResource(id = R.drawable.days_of_sober_background),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .align(Alignment.CenterStart)
                                        .padding(start = 20.dp)
                                )
                                Row(
                                    modifier = Modifier
                                        .padding(
                                            top = 0.dp,
                                            bottom = 20.dp,
                                            end = 20.dp
                                        )
                                        .align(
                                            Alignment.CenterEnd
                                        )
                                ) {
                                    Title(
                                        text = homeViewState.daysOfSober.toString(),
                                        style = Typography.h1,
                                        modifier = Modifier.align(Alignment.CenterVertically)
                                    )
                                    Title(
                                        text = context.resources.getString(R.string.days),
                                        style = Typography.body1,
                                        modifier = Modifier
                                            .align(Alignment.CenterVertically)
                                            .padding(start = 3.dp)
                                    )
                                }
                            }

                        }
                    }

                    Title(
                        text = stringResource(id = R.string.your_week_so_far),
                        style = Typography.h2,
                        modifier = Modifier.padding(vertical = 20.dp, horizontal = 32.dp)
                    )
                    SoberBarGraph(
                        data = mapOf(
                            Pair(0.1f, "Mon"),
                            Pair(0.2f, "Tue"),
                            Pair(0.3f, "Wed"),
                            Pair(0.4f, "Thu"),
                            Pair(0.5f, "Fri"),
                            Pair(0.6f, "Sat"),
                            Pair(0.7f, "Sun")
                        ),
                    )

                    Title(
                        text = "Challenge of The Day!",
                        style = Typography.h2,
                        modifier = Modifier.padding(vertical = 20.dp, horizontal = 32.dp)
                    )
                    val todaysChallenge = homeViewState.todaysChallenge
                    CardWithTitleAndIcon(
                        icon = if (todaysChallenge.group == GroupType.GROUP) R.drawable.group_icon else R.drawable.icon_person,
                        titleText = if (todaysChallenge.group == GroupType.GROUP) "Group Activity" else "Individual Activity",
                        modifier = Modifier.padding(start = 32.dp, end = 32.dp, top = 10.dp, bottom = 30.dp),
                        style = Typography.body1
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Row(modifier = Modifier.align(Alignment.CenterStart)) {
                                Image(
                                    painter = when (todaysChallenge.level) {
                                        (PhysicalActivityLevel.LIGHT) -> painterResource(id = R.drawable.light_intensity)
                                        (PhysicalActivityLevel.MODERATE) -> painterResource(id = R.drawable.moderate_icon)
                                        (PhysicalActivityLevel.INTENSE) -> painterResource(id = R.drawable.intense_icon)
                                        else -> painterResource(id = R.drawable.image_error_placeholder)
                                    }, contentDescription = null,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(start = 20.dp, bottom = 20.dp)
                                )
                                Title(
                                    text = when (todaysChallenge.level) {
                                        (PhysicalActivityLevel.LIGHT) -> "Light"
                                        (PhysicalActivityLevel.MODERATE) -> "Moderate"
                                        (PhysicalActivityLevel.INTENSE) -> "Intense"
                                        else -> "Others"
                                    }, style = Typography.subtitle1,
                                    modifier = Modifier.padding(start = 20.dp)
                                )
                            }
                            Title(text = todaysChallenge.activity,
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(end = 20.dp, bottom = 20.dp),
                                style = Typography.h1
                            )
                        }
                    }
                }

            }
        }
    }
}