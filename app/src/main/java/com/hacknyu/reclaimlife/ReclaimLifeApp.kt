package com.hacknyu.reclaimlife

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hacknyu.reclaimlife.navigation.Screen
import com.hacknyu.reclaimlife.navigation.navigationItems
import com.hacknyu.reclaimlife.ui.community.CommunityScreen
import com.hacknyu.reclaimlife.ui.home.HomeScreen
import com.hacknyu.reclaimlife.ui.motivation.MotivationScreen
import com.hacknyu.reclaimlife.ui.settings.SettingsScreen
import com.hacknyu.reclaimlife.ui.theme.Typography
import com.hacknyu.reclaimlife.ui.threaddetail.ThreadDetailScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReclaimLifeApp(
    navController: NavHostController = rememberNavController(),
    onLogout: () -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.ThreadDetail.route) {
                BottomBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Screen.Home.route) {
                HomeScreen()
            }
            composable(route = Screen.Community.route) {
                CommunityScreen(onClickThread = { threadId ->
                    navController.navigate(Screen.ThreadDetail.createRoute(threadId))
                })
            }
            composable(route = Screen.Motivation.route) {
                MotivationScreen()
            }
            composable(
                route = Screen.ThreadDetail.route,
                arguments = listOf(
                    navArgument("threadId") { type = NavType.StringType }
                )
            ) { navBackStackEntry ->
                val threadId = navBackStackEntry.arguments?.getString("threadId") ?: ""
                ThreadDetailScreen(threadId = threadId, onClickBack = {
                    navController.navigateUp()
                })
            }
            composable(
                route = Screen.Settings.route
            ) {
                SettingsScreen {
                    onLogout()
                }
            }
        }

    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        BottomNavigation {
            navigationItems.map { navigationItem ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = navigationItem.icon,
                            contentDescription = navigationItem.title
                        )
                    },
                    label = {
                        Text(
                            text = navigationItem.title,
                            fontSize = 15.sp,
                            softWrap = false
                        )
                    },
                    selected = currentRoute == navigationItem.screen.route,
                    onClick = {
                        navController.navigate(navigationItem.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }

        }
    }
}