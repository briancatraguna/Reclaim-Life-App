package com.hacknyu.reclaimlife.navigation


sealed class Screen( val route: String ) {
    object SignIn : Screen("signin")
    object SignUp : Screen("signup")
    object Home : Screen("home")
    object Community : Screen("community")
    object Motivation : Screen("motivation")
    object ThreadDetail : Screen("threaddetail/{threadId}") {
        fun createRoute(threadId: String) = "threaddetail/$threadId"
    }
    object Settings : Screen("settings")
}
