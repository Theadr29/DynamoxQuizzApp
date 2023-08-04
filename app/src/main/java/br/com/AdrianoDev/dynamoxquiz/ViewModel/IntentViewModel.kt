package br.com.AdrianoDev.dynamoxquiz.ViewModel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import br.com.AdrianoDev.dynamoxquiz.QuizRetrofit


class IntentViewModel : ViewModel() {


    fun QuizRetrofit(context: Context) {
        val intent = Intent(context, QuizRetrofit::class.java)
        context.startActivity(intent)
    }

}