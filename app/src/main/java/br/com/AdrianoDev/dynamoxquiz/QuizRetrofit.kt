package br.com.AdrianoDev.dynamoxquiz

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
//import br.com.AdrianoDev.dynamoxquiz.Network.Utils.Endpoint
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.FormBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.nio.charset.Charset


data class Question(
    val id: String,
    val statement: String,
    val options: List<String>
)

data class AnswerResult(val result: Boolean)


class QuizRetrofit : ComponentActivity() {
    private var currentQuestion: Question? = null // Armazena a pergunta atual


    private lateinit var btnStartQuiz: Button
    private lateinit var btnNextQuestion: Button
    private lateinit var radioGroup: RadioGroup
    private lateinit var textViewScore: TextView
    private lateinit var textViewQuestion: TextView
    private lateinit var questions: List<Question>
    private var currentQuestionIndex = 0
    private var score = 0
    private var clickCount = 0
    private var isButtonClickable = false // Inicialmente, o botão não deve ser clicável

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_retrofit)


        btnStartQuiz = findViewById(R.id.btnStartQuiz)
        btnNextQuestion = findViewById(R.id.btnNextQuestion)
        radioGroup = findViewById(R.id.radioGroup)
        textViewScore = findViewById(R.id.textViewScore)
        textViewQuestion = findViewById<TextView>(R.id.textViewQuestion)
        btnStartQuiz.setOnClickListener {
            startQuiz()
            textViewQuestion.visibility = View.VISIBLE // Tornar a textViewQuestion visível apenas ao iniciar o quiz

        }

        radioGroup.setOnCheckedChangeListener { _, _ ->
            isButtonClickable = true // Habilitar o botão quando um radio button for selecionado
            btnNextQuestion.visibility = View.VISIBLE
        }

        btnNextQuestion.setOnClickListener {
            if (isButtonClickable) {
                nextQuestion()
                isButtonClickable = false // Desabilitar o botão após o clique
            }
        }

        fetchQuestions()
    }

    private fun startQuiz() {
        btnStartQuiz.visibility = View.GONE
//            editTextName.isEnabled = false
        showNextQuestion()
        textViewScore.visibility = View.VISIBLE
    }

    private fun showNextQuestion() {
        if (currentQuestionIndex < questions.size) {
            currentQuestion = questions[currentQuestionIndex]

            textViewQuestion.text = "Pergunta: ${currentQuestion?.statement}"

            radioGroup.removeAllViews()

            for (option in currentQuestion?.options ?: emptyList()) {
                val radioButton = RadioButton(this)
                radioButton.text = option
                radioGroup.addView(radioButton)
            }

            btnNextQuestion.visibility = if (isButtonClickable) View.VISIBLE else View.GONE
        }
    }

    private fun nextQuestion() {
        val selectedRadioButton = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
        val answer = selectedRadioButton?.text?.toString() ?: ""

        val question = currentQuestion
        if (question != null) {
            checkAnswer(question.id, answer)

            currentQuestionIndex++

            if (currentQuestionIndex < questions.size) {
                showNextQuestion() // Mostrar a próxima pergunta
                isButtonClickable = false // Desabilitar o botão para a próxima pergunta
                btnNextQuestion.visibility = View.GONE // Ocultar o botão imediatamente
            } else {
                clickCount++
                if (clickCount < 10) {
                    currentQuestionIndex = 0
                    startQuiz() // Reiniciar o ciclo do quiz
                    fetchQuestions()
                } else {
                    finishQuiz() // Finalizar o quiz após 10 cliques no botão
                }
            }
        }
    }


    private fun finishQuiz() {
        btnNextQuestion.visibility = View.GONE
        textViewScore.text = "Pontuação: $score"
        textViewScore.visibility = View.VISIBLE
    }


    private fun fetchQuestions() {
        GlobalScope.launch(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://quiz-api-bwi5hjqyaq-uc.a.run.app/question")
                .build()

            try {
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()

                if (responseData != null) {
                    val gson = Gson()
                    val question = gson.fromJson(responseData, Question::class.java)
                    questions = listOf(question)
                    Log.d("FetchQuestions", "Perguntas carregadas: $questions")
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }


    private fun checkAnswer(questionId: String, answer: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val client = OkHttpClient()

            // Crie o JSON de resposta do usuário
            val json = "{\"answer\":\"$answer\"}"
            val requestBody = json.toRequestBody("application/json".toMediaTypeOrNull())

            val request = Request.Builder()
                .url("https://quiz-api-bwi5hjqyaq-uc.a.run.app/answer?questionId=$questionId")
                .post(requestBody)
                .build()

            try {
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()

                if (responseData != null) {
                    val gson = Gson()
                    val answerResult = gson.fromJson(responseData, AnswerResult::class.java)

                    runOnUiThread {
                        if (answerResult.result) {
                            score++ // Incrementar a pontuação se a resposta estiver correta
                            textViewScore.text =
                                "Pontuação: $score" // Atualizar a pontuação exibida
                        }
                        showNextQuestion() // Mostrar próxima pergunta após verificar a resposta
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}