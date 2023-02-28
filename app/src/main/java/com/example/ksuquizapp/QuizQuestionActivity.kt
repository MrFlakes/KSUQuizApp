package com.example.ksuquizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


private var userName: String? = null

private val questionsList: ArrayList<Question> = Constants.getQuestions()
private var currentQuestionIndex = 0;
private var selectedAlternativeIndex = -1;
private var isAnswerChecked = false;
private var totalScore = 0;
private val alternativesIds = arrayOf(R.id.optionOne, R.id.optionTwo, R.id.optionThree, R.id.optionFour)

private var tvQuestion: TextView? = null
private var ivImage: ImageView? = null
private var progressBar: ProgressBar? = null
private var tvProgress: TextView? = null
private var btnSubmit: Button? = null
private var tvAlternatives: ArrayList<TextView>? = null
