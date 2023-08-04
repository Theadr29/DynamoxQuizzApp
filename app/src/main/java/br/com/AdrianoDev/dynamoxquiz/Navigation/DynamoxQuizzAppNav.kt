package br.com.AdrianoDev.dynamoxquiz.Navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen {

    object SignUpScreen : Screen()
    object TermsAndConditionsScreen : Screen()
    object LoginScreen : Screen()
    object HomeScreen : Screen()
    object ErroScreen : Screen()
    object EnviarEmail : Screen()
    object VerificationCode : Screen()
    object HomeInVerification: Screen()
}


object DynamoxQuizzAppRoute{

    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.LoginScreen)

    fun navigateTo(destination : Screen){
        currentScreen.value = destination
    }


}