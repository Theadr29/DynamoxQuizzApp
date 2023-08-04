package br.com.AdrianoDev.dynamoxquiz.Screens

import android.content.Intent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.AdrianoDev.dynamoxquiz.R


//import com.AdrianoDev.WingsTeste.navigation.SystemBackButtonHandler
//import com.AdrianoDev.WingsTeste.theme.rememberImeState

import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import br.com.AdrianoDev.dynamoxquiz.App.DynamoxQuizzApp
import br.com.AdrianoDev.dynamoxquiz.Components.ButtonComponent
import br.com.AdrianoDev.dynamoxquiz.Components.HeadingTextComponent
import br.com.AdrianoDev.dynamoxquiz.Components.LogoDynamox
import br.com.AdrianoDev.dynamoxquiz.Components.MyTextFieldComponent
import br.com.AdrianoDev.dynamoxquiz.Navigation.DynamoxQuizzAppRoute
import br.com.AdrianoDev.dynamoxquiz.Navigation.Screen
import br.com.AdrianoDev.dynamoxquiz.QuizRetrofit
import br.com.AdrianoDev.dynamoxquiz.ViewModel.IntentViewModel
import br.com.AdrianoDev.dynamoxquiz.theme.rememberImeState

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen() {


    val screenHeight = LocalConfiguration.current.screenHeightDp.dp


    //Define o padding pelo tamanho da tela, no caso o vertical

    val Padding = if (screenHeight > 630.dp) {
        20.dp // Se a tela for maior que 600 dp
    } else if (screenHeight < 585.dp) {
        5.dp
    } else {
        10.dp
    }

    val Padding1 = if (screenHeight > 630.dp) {
        30.dp // Se a tela for maior que 600 dp
    } else if (screenHeight < 585.dp) {
        20.dp
    } else {
        20.dp
    }

    val Padding3 = if (screenHeight > 630.dp) {
        15.dp // Se a tela for maior que 600 dp
    } else if (screenHeight < 585.dp) {
        5.dp
    } else {
        3.dp
    }

    val Pading4 = if (screenHeight > 630.dp) {
        10.dp // Se a tela for maior que 600 dp
    } else if (screenHeight < 585.dp) {
        5.dp
    } else {
        0.dp
    }

    val Padding5 = if (screenHeight > 630.dp) {
        10.dp // Se a tela for maior que 600 dp
    } else if (screenHeight < 585.dp) {
        0.dp
    } else {
        0.dp
    }

    val Padding7 = if (screenHeight > 630.dp) {
        0.dp // Se a tela for maior que 600 dp
    } else if (screenHeight < 585.dp) {
        (-20).dp
    } else if (screenHeight > 720.dp) {
        20.dp
    } else {
        0.dp
    }

    val imeState = rememberImeState()
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    var shouldScroll by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value) {
            // The keyboard is visible, allow scrolling after a delay
            shouldScroll = true
            // Scroll to the bottom
            scrollState.animateScrollTo(scrollState.maxValue, tween(300))
        } else {
            shouldScroll = true
        }
    }

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        if (shouldScroll) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(bottom = 70.dp)
                    .offset(y = Padding7),
                contentAlignment = Alignment.Center,

                ) {

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(horizontal = 28.dp)

                ) {
                    var textFieldValue by remember { mutableStateOf("") }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = Padding5)

                    ) {

                        LogoDynamox()

                        HeadingTextComponent(value = "Bem vindo ao DynamoxQuiz")
                        Spacer(modifier = Modifier.height(Padding))

                        MyTextFieldComponent(
                            labelValue = "Diga-me o seu nome",
                            painterResource(id = R.drawable.profile),
                            onTextChanged = { newValue ->
                                textFieldValue = newValue
                            },
                            errorStatus = textFieldValue.isBlank()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        ButtonComponent(
                            value = "Entrar",
                            onButtonClicked = {

                                val viewModel = IntentViewModel()
                                viewModel.QuizRetrofit(context)
                            },
                            isEnabled = !textFieldValue.isBlank()
                        )


                    }


                }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}