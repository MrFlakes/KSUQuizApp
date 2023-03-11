package com.example.ksuquizapp

class Manager {
    companion object{
        var score = 0
        var progress = 1
        val questions = arrayOf(
            TestQuestion("What is the name of the library located on \n" +
                    "the Marietta campus?","Lawrence V. Johnson Library",
                arrayOf("Horace W. Sturgis Library", "Joe Mack Wilson Library", "Rossbacher Library")),

            TestQuestion("What is located in Room 263 of the J building on\n" +
                    "the Marietta Campus?","CCSE Tutoring Lab",
                arrayOf("UITS Service Desk", "Smart Center", "Writing Center")
            )
        )
    }
}