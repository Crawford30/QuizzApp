package com.example.quizzapp

import android.graphics.Color
import android.graphics.Color.parseColor
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*
import kotlinx.android.synthetic.main.activity_quiz_questions.view.*

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    //variable to work with Quiz
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

        //submit btn on click listener
        btn_submit.setOnClickListener(this)



    }

    private fun setQuestion() {

        //assign  values
         //mCurrentPosition = 1  //for testing purpose

//        val currentQuestion: Question? = mQuestionsList[mCurrentPosition - 1] //-1 because array index starts at 0

       // val currentQuestion = mQuestionsList!!.get(mCurrentPosition - 1)

        val currentQuestion = mQuestionsList!![mCurrentPosition - 1]

        //Each time we set a new question, we set default option view
        //so that the buttons(text views) are back to default appearance

        defaultOptionView()


        //Reset the text to submit each time after set to GO TO NEXT QUESTION
        if(mCurrentPosition == mQuestionsList!!.size) {


            //on the last screen or last question display finish
            btn_submit.text = "FINISH"

        } else {

            btn_submit.text = "SUBMIT"

        }

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


    //fun for default look, set default layout

    private  fun defaultOptionView(){

        //TextViews option(array list of TextViews)
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
               selectedOptionView(tv_option_three, 3)
           }


           R.id.tv_option_four -> {
//               selectedOptionView(v, 4)
               selectedOptionView(tv_option_four, 4)
           }

           R.id.btn_submit -> {

               if (mSelectedOptionPosition == 0) { //reset it to 0

                   mCurrentPosition ++ //if selected position is 0, increase current position by 1, go to next question

                   //check when the current position is less or > question list size

                   when {
                       mCurrentPosition <= mQuestionsList!!.size -> {

                           setQuestion() //set the next question

                       } else -> {

                           Toast.makeText(this, "You have successfully completed the Quiz", Toast.LENGTH_SHORT).show()

                       }
                   }



               } else {

                   //means the user has selected an option position and we do not have mSelectedPosition = 0

                       //Hence we create a question
                   val question = mQuestionsList?.get(mCurrentPosition - 1) //get the question at the current position - 1

                   //check if correct answer
                   if (question!!.correctAnswer != mSelectedOptionPosition) {

                       //means we selected a wrong answer
                       answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                   }

                   // correct answer is set to green in any case, need not to be inside if
                   answerView(question!!.correctAnswer, R.drawable.correct_option_border_bg)


                   //if on the last question, the  btn should change to FINISH, else set to GO TO NEXT QUESTION
                   if(mCurrentPosition == mQuestionsList!!.size) {

                       btn_submit.text = "FINISH"

                   } else {

                       btn_submit.text = "GO TO NEXT QUESTION"

                   }

                   //====reset the selected position to 0, very important
                   mSelectedOptionPosition = 0


               }

           }
       }

    }


    //==use in submit btn (time: 1:35:29)========
    private fun answerView(answer: Int, drawableView: Int) {

        when(answer) {
            1 -> {
                tv_option_one.background = ContextCompat.getDrawable(this, drawableView)
            }

            2 -> {
                tv_option_two.background = ContextCompat.getDrawable(this, drawableView)
            }

            3 -> {
                tv_option_three.background = ContextCompat.getDrawable(this, drawableView)
            }

            4 -> {
                tv_option_four.background = ContextCompat.getDrawable(this, drawableView)
            }
        }

    }



    //========SELECTED OPTION VIEW====
    private fun selectedOptionView(tv: TextView, selectedOptionNumber: Int) {

        //reset everything to default view, for instance when we click on button 3 reset the previously selected
        defaultOptionView()

        //current button we clicked on. selectedOptionNumber will be the new selected position
        mSelectedOptionPosition = selectedOptionNumber

        //set text view passed
        tv.setTextColor(Color.parseColor("#363A43"))

        //set typeface, it can be done this way or  tv.setTypeface(tv.typeface, Typeface.BOLD)
       // tv.typeface = Typeface.DEFAULT

        tv.setTypeface(tv.typeface, Typeface.BOLD)

        //set background to default
        tv.background = ContextCompat.getDrawable(
                this,
                R.drawable.selected_option_border_bg)

    }


}
