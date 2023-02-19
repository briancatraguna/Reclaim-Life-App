package com.hacknyu.reclaimlife.ui.register.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hacknyu.reclaimlife.R
import com.hacknyu.reclaimlife.model.User
import com.hacknyu.reclaimlife.ui.ViewModelFactory
import com.hacknyu.reclaimlife.ui.home.dateheader.Title
import com.hacknyu.reclaimlife.ui.theme.*
import com.hacknyu.reclaimlife.utils.Injection

@Composable
fun SignInScreen(
    navigateToSignUp: () -> Unit,
    onSuccessSignIn: () -> Unit,
    viewModel: SignInViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository(LocalContext.current.applicationContext)
        )
    )
) {
    viewModel.signInState.collectAsState().value.let { signInState ->
        if (signInState == SignInState.SUCCESSFUL) {
            onSuccessSignIn()
        }
    }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_smallest),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 100.dp)
                .size(100.dp)
        )
        Title(
            text = stringResource(id = R.string.welcome_title),
            style = Typography.h1,
            modifier = Modifier.align(
                Alignment.CenterHorizontally
            )
        )
        Title(
            text = stringResource(id = R.string.welcome_subtitle),
            style = Typography.body1,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        TextField(
            value = email,
            onValueChange = { newEmail -> email = newEmail },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 20.dp, bottom = 10.dp)
                .align(Alignment.CenterHorizontally),
            placeholder = {
                Title(text = "Enter Email")
            }
        )

        TextField(
            value = password,
            onValueChange = { newPassword -> password = newPassword },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 10.dp)
                .align(Alignment.CenterHorizontally),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            placeholder = {
                Title(text = "Enter Password")
            },
            trailingIcon = {
                val image =
                    if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, description)
                }
            }
        )

        Button(
            onClick = {
                viewModel.signInUser(
                    User(
                        firstName = "",
                        lastName = "",
                        userName = "",
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
            Title(text = "Login", style = Typography.h1)
        }

        Box(modifier = Modifier.fillMaxWidth()) {
            Title(
                text = "Don't have an account?", modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(top = 40.dp, start = 32.dp), style = Typography.subtitle1
            )
            Title(
                text = "Sign up", modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(top = 40.dp, end = 32.dp)
                    .clickable {
                        navigateToSignUp()
                    },
                style = Typography.h1,
                color = Turquoise
            )
        }


    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    ReclaimLifeTheme {
        SignInScreen(navigateToSignUp = {}, onSuccessSignIn = {})
    }
}