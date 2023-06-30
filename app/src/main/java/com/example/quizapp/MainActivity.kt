package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import com.example.quizapp.QuziQuestionsActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        var btn_Start = findViewById<Button>(R.id.btn_start)
        var et_name = findViewById<AppCompatEditText>(R.id.et_name)
        btn_Start.setOnClickListener {
            if (et_name.text.toString().isEmpty()){     //if user does not fill the name box
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent(this, QuziQuestionsActivity::class.java)    //proceed to QuizQuestionActivity
                intent.putExtra(Constants.USER_NAME, et_name.text.toString())               //Giving username received from user    putExtra(key, data)
                startActivity(intent)
                finish()
            }
        }


    }


}