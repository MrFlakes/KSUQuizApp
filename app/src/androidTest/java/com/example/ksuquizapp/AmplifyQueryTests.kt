package com.example.ksuquizapp

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.query.Where
import com.amplifyframework.datastore.generated.model.Questions

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class AmplifyQueryTests {
    @Test
    fun QueryByNoForQ1(){
        Amplify.DataStore.query(Questions::class.java,
            Where.matches(Questions.QUESTION_NO.eq(1)),
            { items ->
                while (items.hasNext()) {
                    val item = items.next()
                    assertEquals("In what year was KSU founded?", item.question)
                }
            },
            { failure -> Log.e("TestQueryError", "Could not query DataStore", failure) }
        )
    }

    @Test
    fun QueryByAnswerForQ2(){
        Amplify.DataStore.query(Questions::class.java,
            Where.matches(Questions.ANSWER.eq("1963")),
            { items ->
                while (items.hasNext()) {
                    val item = items.next()
                    assertEquals("On what day of the week does KSU test their\n" +
                            "emergency alert system?", item.question)
                }
            },
            { failure -> Log.e("TestQueryError", "Could not query DataStore", failure) }
        )
    }

    @Test
    fun QueryByIDForQ3(){
        Amplify.DataStore.query(Questions::class.java,
            Where.matches(Questions.ID.eq("da01ac33-bde1-4bc2-89bf-f1b307271a7f")),
            { items ->
                while (items.hasNext()) {
                    val item = items.next()
                    assertEquals("In what year did KSU and SPSU consolidate?", item.question)
                }
            },
            { failure -> Log.e("TestQueryError", "Could not query DataStore", failure) }
        )
    }
    @Test
    fun QueryByNoForAnswer4(){
        Amplify.DataStore.query(Questions::class.java,
            Where.matches(Questions.QUESTION_NO.eq(4)),
            { items ->
                while (items.hasNext()) {
                    val item = items.next()
                    assertEquals("The Sentinel", item.answer)
                }
            },
            { failure -> Log.e("TestQueryError", "Could not query DataStore", failure) }
        )
    }
}