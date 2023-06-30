package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import java.util.Currency


class QuziQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    //global variable
    private var mCurrentPosition:Int = 1        //starting
    private var mQuestionList: ArrayList<Question>? = null      //questions stored in ArrayList
    private var mSelectedOptionPosition: Int = 0        //to know selected option
    private var mCorrectAnswers: Int = 0
    private var mUserName: String? = null       //username given by the user




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quzi_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        mQuestionList = Constants.getQuestions()
        //Log.i("Question Size","${mQuestionList.size}")

        setQuestion()
        var tv_option_one = findViewById<TextView>(R.id.tv_option_one)
        var tv_option_two = findViewById<TextView>(R.id.tv_option_two)
        var tv_option_three = findViewById<TextView>(R.id.tv_option_three)
        var tv_option_four = findViewById<TextView>(R.id.tv_option_four)
        var btn_submit = findViewById<Button>(R.id.btn_submit)

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)

        btn_submit.setOnClickListener(this)

    }

    private fun setQuestion(){
        var btn_submit = findViewById<Button>(R.id.btn_submit)
        //mCurrentPosition = 1
        val question =  mQuestionList!![mCurrentPosition - 1]

        defaultOptionsView()

        if(mCurrentPosition == mQuestionList!!.size){
            btn_submit.text = "FINISH"
        }
        else{
            btn_submit.text = "SUBMIT"
        }


        var progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.progress = mCurrentPosition

        var tv_progress = findViewById<TextView>(R.id.tv_progress)
        tv_progress.text = "$mCurrentPosition" + "/" + progressBar.max

        var tv_question = findViewById<TextView>(R.id.tv_question)
        tv_question.text = question!!.question


        var iv_image = findViewById<ImageView>(R.id.iv_image)
        iv_image.setImageResource(question.image)

        var tv_option_one = findViewById<TextView>(R.id.tv_option_one)
        var tv_option_two = findViewById<TextView>(R.id.tv_option_two)
        var tv_option_three = findViewById<TextView>(R.id.tv_option_three)
        var tv_option_four = findViewById<TextView>(R.id.tv_option_four)

        tv_option_one.text = question.OptionOne


        tv_option_two.text = question.OptionTwo


        tv_option_three.text = question.optionThree


        tv_option_four.text = question.OptionFour

    }

    override fun onClick(v: View?) {
        var tv_option_one = findViewById<TextView>(R.id.tv_option_one)
        var tv_option_two = findViewById<TextView>(R.id.tv_option_two)
        var tv_option_three = findViewById<TextView>(R.id.tv_option_three)
        var tv_option_four = findViewById<TextView>(R.id.tv_option_four)
        var btn_submit = findViewById<Button>(R.id.btn_submit)
        when(v?.id){
            R.id.tv_option_one->{
                selectedOptionView(tv_option_one, 1)
            }

            R.id.tv_option_two->{
                selectedOptionView(tv_option_two, 2)
            }

            R.id.tv_option_three->{
                selectedOptionView(tv_option_three, 3)
            }

            R.id.tv_option_four->{
                selectedOptionView(tv_option_four, 4)
            }

            R.id.btn_submit ->{
                if(mSelectedOptionPosition == 0){
                    mCurrentPosition++

                    when{
                        mCurrentPosition <= mQuestionList!!.size ->{
                            setQuestion()
                        }else ->{
                        val intent = Intent(this, ResuktActivity::class.java)
                        intent.putExtra(Constants.USER_NAME, mUserName)
                        intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                        intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList!!.size)
                        startActivity(intent)
                        finish()
                    }
                    }
                }else{
                    val question = mQuestionList?.get(mCurrentPosition - 1)
                    if(question!!.correctAnswer != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }
                    else{
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if(mCurrentPosition == mQuestionList!!.size){
                        btn_submit.text = "FINISH"
                    }
                    else{
                        btn_submit.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int){
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum


        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg)
    }





    private fun defaultOptionsView(){
        var tv_option_one = findViewById<TextView>(R.id.tv_option_one)
        var tv_option_two = findViewById<TextView>(R.id.tv_option_two)
        var tv_option_three = findViewById<TextView>(R.id.tv_option_three)
        var tv_option_four = findViewById<TextView>(R.id.tv_option_four)


        val options = ArrayList<TextView>()         // to set every option visible view once at a time we pushed them in an arraylist
        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)


        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }

    }





    private fun answerView(answer:Int, drawableView: Int){
        var tv_option_one = findViewById<TextView>(R.id.tv_option_one)
        var tv_option_two = findViewById<TextView>(R.id.tv_option_two)
        var tv_option_three = findViewById<TextView>(R.id.tv_option_three)
        var tv_option_four = findViewById<TextView>(R.id.tv_option_four)
        var btn_submit = findViewById<Button>(R.id.btn_submit)


        when(answer){
            1->{
                tv_option_one.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2->{
                tv_option_two.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3->{
                tv_option_three.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4->{
                tv_option_four.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }
    }
}