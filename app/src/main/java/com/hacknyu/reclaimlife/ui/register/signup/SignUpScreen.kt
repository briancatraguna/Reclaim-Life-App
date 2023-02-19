package com.hacknyu.reclaimlife.ui.register.signup

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hacknyu.reclaimlife.model.User
import com.hacknyu.reclaimlife.ui.ViewModelFactory
import com.hacknyu.reclaimlife.ui.common.BackButton
import com.hacknyu.reclaimlife.ui.home.dateheader.Title
import com.hacknyu.reclaimlife.ui.theme.DarkBlue
import com.hacknyu.reclaimlife.ui.theme.LightBlue
import com.hacknyu.reclaimlife.ui.theme.Typography
import com.hacknyu.reclaimlife.utils.Injection

@Composable
fun SignUpScreen(
    navigateBack: () -> Unit,
    viewModel: SignUpViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository(LocalContext.current.applicationContext)
        )
    )
) {
    val context = LocalContext.current
    viewModel.isSignUpSuccessful.collectAsState().value.let { signUpState ->
        if (signUpState == SignUpState.SUCCESSFUL) {
            Toast.makeText(context, "Account successfully created.", Toast.LENGTH_SHORT).show()
            navigateBack()
        } else if (signUpState == SignUpState.FAILED) {
            Toast.makeText(context, "Failed to create account.", Toast.LENGTH_SHORT).show()
        }
    }
    var firstName by remember { mutableStateOf("") }
    var isFirstNameError by remember { mutableStateOf(false) }
    var lastName by remember { mutableStateOf("") }
    var isLastNameError by remember { mutableStateOf(false) }
    var userName by remember { mutableStateOf("") }
    var isUsernameError by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var isEmailError by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
    var isPasswordError by remember { mutableStateOf(false) }
    var confirmPassword by remember { mutableStateOf("") }
    var isConfirmPasswordError by remember { mutableStateOf(false) }
    var isPasswordMatching by remember { mutableStateOf(true) }
    // firstName, lastName, userName, email, password
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.align(Alignment.TopEnd)) {
            drawCircle(LightBlue, radius = 100.dp.toPx())
        }
        Canvas(modifier = Modifier.align(Alignment.BottomStart)) {
            drawCircle(LightBlue, radius = 100.dp.toPx())
        }
        Column {

            Box(modifier = Modifier.fillMaxWidth()) {
                BackButton(
                    onClick = { navigateBack() },
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(32.dp)
                        .size(50.dp)
                )
                Title(
                    text = "Create an account.",
                    style = Typography.h1,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            TextField(
                value = firstName,
                onValueChange = { newFirstName ->
                    firstName = newFirstName
                    isFirstNameError = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, top = 20.dp, bottom = 10.dp)
                    .align(Alignment.CenterHorizontally),
                placeholder = {
                    Title(text = "Enter First Name")
                },
                isError = isFirstNameError
            )

            TextField(
                value = lastName,
                onValueChange = { newLastName ->
                    lastName = newLastName
                    isLastNameError = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, top = 20.dp, bottom = 10.dp)
                    .align(Alignment.CenterHorizontally),
                placeholder = {
                    Title(text = "Enter Last Name")
                },
                isError = isLastNameError
            )

            TextField(
                value = userName,
                onValueChange = { newUserName ->
                    userName = newUserName
                    isUsernameError = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, top = 20.dp, bottom = 10.dp)
                    .align(Alignment.CenterHorizontally),
                placeholder = {
                    Title(text = "Enter User Name")
                },
                isError = isUsernameError
            )

            TextField(
                value = email,
                onValueChange = { newEmail ->
                    email = newEmail
                    isEmailError = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, top = 20.dp, bottom = 10.dp)
                    .align(Alignment.CenterHorizontally),
                placeholder = {
                    Title(text = "Enter Email")
                },
                isError = isEmailError
            )

            TextField(
                value = password,
                onValueChange = { newPassword ->
                    password = newPassword
                    isPasswordError = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 10.dp)
                    .align(Alignment.CenterHorizontally),
                visualTransformation = PasswordVisualTransformation(),
                placeholder = {
                    Title(text = "Enter Password")
                },
                isError = isPasswordError
            )

            TextField(
                value = confirmPassword,
                onValueChange = { newConfirmPassword ->
                    confirmPassword = newConfirmPassword
                    isConfirmPasswordError = false
                    isPasswordMatching = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 10.dp)
                    .align(Alignment.CenterHorizontally),
                visualTransformation = PasswordVisualTransformation(),
                placeholder = {
                    Title(text = "Confirm Password")
                },
                isError = isConfirmPasswordError
            )
            if (!isPasswordMatching) {
                Title(text = "Password does not match!", color = Color.Red, style = Typography.subtitle1, modifier = Modifier.padding(start = 32.dp))
            }

            Button(
                onClick = {
                    if (firstName.isEmpty()) isFirstNameError = true
                    if (lastName.isEmpty()) isLastNameError = true
                    if (userName.isEmpty()) isUsernameError = true
                    if (email.isEmpty()) isEmailError = true
                    if (password.isEmpty()) isPasswordError = true
                    if (confirmPassword.isEmpty()) isConfirmPasswordError = true
                    if (password != confirmPassword) {
                        isConfirmPasswordError = true
                        isPasswordMatching = false
                    }

                    if (isFirstNameError || isLastNameError || isUsernameError || isEmailError || isPasswordError || isConfirmPasswordError) return@Button
                    viewModel.signUp(
                        User(
                            firstName = firstName,
                            lastName = lastName,
                            userName = userName,
                            email = email,
                            password = password
                        )
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = DarkBlue,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 10.dp)
            ) {
                Title(text = "Sign Up", style = Typography.h1)
            }


        }
    }
}