package com.example.quizapp

data class Question (
        val id: Int,
        val question: String,
        val image: Int,
        val OptionOne: String,
        val OptionTwo: String,
        val optionThree: String,
        val OptionFour: String,
        val correctAnswer:Int
)