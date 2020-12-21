package com.example.quizzapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_quiz_questions.*
import kotlinx.android.synthetic.main.activity_quiz_questions.view.*

class QuizQuestionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        val questionList = Constants.getQuestions()
//        Log.i("Questions size", "${questionList.size}")

        //assign  values
        val currentPosition = 1

        val currentQuestion: Question? = questionList[currentPosition - 1] //-1 because array index starts at 0

        //assigning progress the value of current position
        progressBar.progress = currentPosition

        tv_progress.text =  "$currentPosition" + "/" + progressBar.max

        //set text of question
        tv_question.text = currentQuestion!!.question
        iv_image.setImageResource(currentQuestion.image)

        tv_option_one.text =  currentQuestion.optionOne
        tv_option_two.text =  currentQuestion.optionTwo
        tv_option_three.text =  currentQuestion.optionThree
        tv_option_four.text =  currentQuestion.optionFour

    }
}