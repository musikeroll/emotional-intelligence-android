package com.roaringapps.codingchallenge.feature.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.roaringapps.codingchallenge.R
import com.roaringapps.codingchallenge.feature.domain.model.Activity
import com.roaringapps.codingchallenge.feature.domain.model.Level
import com.roaringapps.codingchallenge.ui.theme.BlackAlpha8
import com.roaringapps.codingchallenge.ui.theme.BluishBlack
import com.roaringapps.codingchallenge.ui.theme.Euclid
import com.roaringapps.codingchallenge.ui.theme.GrayMain
import com.roaringapps.codingchallenge.ui.theme.LightGrey
import com.roaringapps.codingchallenge.ui.theme.MainTextStyle
import com.roaringapps.codingchallenge.ui.theme.PurpleMain

/**
 * @author   RoRR <rollolpindo@gmail.com>
 * @since    16/05/2024
 */
@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .padding(bottom = 48.dp)
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        Spacer(
            modifier = Modifier
                .height(24.dp)
                .fillMaxWidth()
        )
        Header()
        RadioTabs()
        HomeBody()
    }
}

@Composable
fun HomeBody(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    if (state.loading) {
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            color = PurpleMain,
            trackColor = BlackAlpha8
        )
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        state.emotionLevels.levels.forEach { level ->
            item { LevelHeader(level = level) }

            level.activities.chunked(2).forEach { chunks ->
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Row {
                        chunks.forEach { activity ->
                            ActivityItem(
                                activity = activity,
                            )
                        }
                    }
                }
            }
        }

        if (!state.loading) {
            item { JourneyFooter() }
        }
    }
}

@Composable
fun JourneyFooter() {
    Spacer(modifier = Modifier.height(40.dp))
    TextButton(
        onClick = { },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = PurpleMain
        ),
        modifier = Modifier
            .padding(horizontal = 48.dp)
            .fillMaxWidth()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.ic_flag),
                contentDescription = stringResource(
                    id = R.string.condes_flag_icon
                ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Journey",
                color = PurpleMain,
                fontSize = 12.sp,
                style = MainTextStyle,
            )
        }
    }
}

@Composable
fun LevelHeader(level: Level) {
    Spacer(modifier = Modifier.height(32.dp))
    Box(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_level),
            contentDescription = stringResource(
                id = R.string.condes_level_image
            ),
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.TopCenter)
        )
        Text(
            text = "LEVEL ${level.level}",
            color = Color.White,
            fontSize = 12.sp,
            style = MainTextStyle,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(
                    color = BluishBlack,
                    shape = RoundedCornerShape(8.dp),
                )
                .padding(horizontal = 12.dp, vertical = 4.dp)
                .align(Alignment.BottomCenter)
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = level.title,
        color = BluishBlack,
        fontSize = 18.sp,
        textAlign = TextAlign.Center,
        style = MainTextStyle,
        modifier = Modifier
            .padding(horizontal = 64.dp)
            .fillMaxWidth()
    )
    Text(
        text = level.description,
        color = LightGrey,
        fontSize = 12.sp,
        textAlign = TextAlign.Center,
        style = MainTextStyle,
        modifier = Modifier
            .padding(horizontal = 64.dp)
            .fillMaxWidth()
    )
}

@Composable
fun ActivityItem(activity: Activity) {
    Column(modifier = Modifier.padding(horizontal = 4.dp)) {
        Image(
            painter = painterResource(id = R.drawable.img_activity),
            contentDescription = stringResource(
                id = R.string.condes_activity_image
            ),
            modifier = Modifier.width(160.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = activity.title,
            color = BluishBlack,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            style = MainTextStyle,
            modifier = Modifier.width(160.dp)
        )
    }
}

@Composable
fun RadioTabs() {
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")
    TabRow(
        selectedTabIndex = tabIndex,
        containerColor = Color.White,
        indicator = { tabPositions ->
            if (tabIndex < tabPositions.size) {
                TabRowDefaults.SecondaryIndicator(
                    color = PurpleMain,
                    modifier = Modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                )
            }
        }
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                text = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        RadioButton(
                            selected = index == tabIndex,
                            onClick = { tabIndex = index },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = PurpleMain,
                                unselectedColor = GrayMain
                            )
                        )
                        Text(
                            text = title,
                            fontSize = 9.sp,
                            style = MainTextStyle
                        )
                    }
                },
                selectedContentColor = PurpleMain,
                unselectedContentColor = GrayMain,
                selected = tabIndex == index,
                onClick = { tabIndex = index },

                )
        }
    }
}

@Composable
fun Header() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                top = 10.dp,
                end = 8.dp
            )
    ) {
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(id = R.drawable.ic_journey),
            contentDescription = stringResource(
                id = R.string.condes_journey_icon
            )
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = "Taming temper",
                fontSize = 16.sp,
                style = MainTextStyle
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .width(115.dp)
            ) {
                LinearProgressIndicator(
                    progress = { .68f },
                    color = PurpleMain,
                    trackColor = BlackAlpha8,
                    modifier = Modifier
                        .width(72.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "3%",
                    color = PurpleMain,
                    fontSize = 10.sp,
                    fontFamily = Euclid,
                    fontWeight = FontWeight.Normal
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = R.drawable.ic_fire),
            contentDescription = stringResource(
                id = R.string.condes_account_icon
            )
        )
        Text(
            text = "0",
            color = PurpleMain,
            fontSize = 16.sp,
            fontFamily = Euclid,
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.width(7.dp))
        Image(
            modifier = Modifier.size(50.dp),
            painter = painterResource(id = R.drawable.ic_account),
            contentDescription = stringResource(
                id = R.string.condes_account_icon
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}