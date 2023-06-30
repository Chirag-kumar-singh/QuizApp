package com.example.quizapp

object Constants{

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS:String = "total_question"
    const val CORRECT_ANSWERS:String = "correct_anserrs"

    fun getQuestions(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        val que1 = Question(1, "What country does this flag belong to ?",
        R.drawable.argentina_flag,
            "Argentina",
            "Australia",
            "Armenia",
            "Afghanistan",
            1
        )

        questionsList.add(que1)

        val que2 = Question(2, "What country does this flag belong to ?",
            R.drawable.india_flag,
            "Argentina",
            "India",
            "Armenia",
            "Afghanistan",
            2
        )

        questionsList.add(que2)

        val que3 = Question(3, "What country does this flag belong to ?",
            R.drawable.usa_flag,
            "Argentina",
            "Australia",
            "U.S.A",
            "Afghanistan",
            3
        )

        questionsList.add(que3)

        val que4 = Question(4, "What country does this flag belong to ?",
            R.drawable.france_image,
            "Argentina",
            "Australia",
            "Armenia",
            "France",
            4
        )

        questionsList.add(que4)

        val que5 = Question(5, "What country does this flag belong to ?",
            R.drawable.russia_image,
            "Russia",
            "Australia",
            "Armenia",
            "Afghanistan",
            1
        )

        questionsList.add(que5)


        return questionsList
    }

}