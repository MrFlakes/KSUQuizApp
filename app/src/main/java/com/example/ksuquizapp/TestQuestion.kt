package com.example.ksuquizapp

data class TestQuestion(
    var question: String,
    var rightAnswer : String,
    var alternatives : Array<String>
)