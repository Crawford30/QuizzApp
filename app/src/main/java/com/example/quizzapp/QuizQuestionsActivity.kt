package com.example.quizzapp

import android.graphics.Color
import android.graphics.Color.parseColor
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*
import kotlinx.android.synthetic.main.activity_quiz_questions.view.*

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    //variable to work with quizz
    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null //set to null, we shall initialise later
    private var mSelectedOptionPosition: Int = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mQuestionsList = Constants.getQuestions()
//        Log.i("Questions size", "${questionList.size}")

        setQuestion()

        //setting on click listener by making the class extends on create listener and implement onClick
        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)



    }

    private fun setQuestion() {

        //assign  values
         mCurrentPosition = 1

//        val currentQuestion: Question? = mQuestionsList[mCurrentPosition - 1] //-1 because array index starts at 0

       // val currentQuestion = mQuestionsList!!.get(mCurrentPosition - 1)

        val currentQuestion = mQuestionsList!![mCurrentPosition - 1]

        //Each time we set a new question, we set default option view
        //so that the buttons(text views) are back to default appearance

        defaultOptionView()

        //assigning progress the value of current position
        progressBar.progress = mCurrentPosition

        tv_progress.text =  "$mCurrentPosition" + "/" + progressBar.max

        //set text of question
        tv_question.text = currentQuestion!!.question
        iv_image.setImageResource(currentQuestion.image)

        tv_option_one.text =  currentQuestion.optionOne
        tv_option_two.text =  currentQuestion.optionTwo
        tv_option_three.text =  currentQuestion.optionThree
        tv_option_four.text =  currentQuestion.optionFour

    }


    //fun for default look

    private  fun defaultOptionView(){
        //texviews option
        val options = ArrayList<TextView>()

        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)

        //for loop
        for (option in options) {

            //set color
            option.setTextColor(Color.parseColor("#7A8089"))

            //set typeface
            option.typeface = Typeface.DEFAULT

            //set background to default
            option.background = ContextCompat.getDrawable(
                    this,
            R.drawable.default_option_border_bg)
        }

    }


    //implement on click
    override fun onClick(v: View?) {

        //the tv should be selected when a user clicks on it

       when(v?.id) {
           R.id.tv_option_one -> {
//               selectedOptionView(v, 1)
               selectedOptionView(tv_option_one, 1)
           }

           R.id.tv_option_two -> {
//               selectedOptionView(v, 2)
               selectedOptionView(tv_option_two, 2)
           }


           R.id.tv_option_three -> {
//               selectedOptionView(v, 3)
               selectedOptionView(tv_option_three, 1)
           }


           R.id.tv_option_four -> {
//               selectedOptionView(v, 4)
               selectedOptionView(tv_option_four, 1)
           }
       }

    }

    private fun selectedOptionView(tv: TextView, selectedOptionNumber: Int) {

        //reset everything to default view
        defaultOptionView()

        //current button we clicked on
        mSelectedOptionPosition = selectedOptionNumber

        //set text view passed
        tv.setTextColor(Color.parseColor("#363A43"))

        //set typeface
       // tv.typeface = Typeface.DEFAULT

        tv.setTypeface(tv.typeface, Typeface.BOLD)

        //set background to default
        tv.background = ContextCompat.getDrawable(
                this,
                R.drawable.selected_option_border_bg)

    }


}
