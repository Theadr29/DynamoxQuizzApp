package br.com.AdrianoDev.dynamoxquiz.App

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import br.com.AdrianoDev.dynamoxquiz.Navigation.DynamoxQuizzAppRoute
import br.com.AdrianoDev.dynamoxquiz.Navigation.Screen
import br.com.AdrianoDev.dynamoxquiz.Screens.MainScreen


@Composable
fun DynamoxQuizzApp() {
//    homeViewModel: HomeViewModel = viewModel()
//    homeViewModel.checkForActiveSession()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

//        if (homeViewModel.Finalizado.value == true) {
//            DynamoxQuizzAppNav.navigateTo(Screen.HomeScreen)
//        }

        Crossfade(targetState = DynamoxQuizzAppRoute.currentScreen) { currentState ->
            when (currentState.value) {
                is Screen.LoginScreen -> {
                    MainScreen()

                }

//                is Screen.SignUpScreen -> {
//                    Pergunta1()
//                }
//
//                is Screen.TermsAndConditionsScreen -> {
//                    Pergunta2()
//                }

//                is Screen.ErroScreen -> {
//                    Pergunta4()
//                }
//
//                is Screen.EnviarEmail -> {
//                    Pergunta5()
//                }
//
//                is Screen.VerificationCode -> {
//                    Pergunta6()
//                }
//
//                is Screen.HomeInVerification -> {
//                    Pergunta7()
//                }
//
//                is Screen.HomeScreen -> {
//                    Pergunta8()
//                }
//
//                is Screen.HomeScreen -> {
//                    Pergunta9()
//                }
//
//                is Screen.HomeScreen -> {
//                    Pergunta10()
//                }

                else -> {}
            }
        }

    }
}