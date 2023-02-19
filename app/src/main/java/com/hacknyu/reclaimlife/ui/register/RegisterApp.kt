package com.hacknyu.reclaimlife.ui.register

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hacknyu.reclaimlife.navigation.Screen
import com.hacknyu.reclaimlife.ui.register.signin.SignInScreen
import com.hacknyu.reclaimlife.ui.register.signup.SignUpScreen

@Composable
fun RegisterApp(
    navController: NavHostController = rememberNavController(),
    onLogin: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SignIn.route,
    ) {
        composable(Screen.SignIn.route) {
            SignInScreen(navigateToSignUp = {
                navController.navigate(Screen.SignUp.route)
            }, onSuccessSignIn = {
                onLogin()
            })
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(navigateBack = {
                navController.navigateUp()
            })
        }
    }

}