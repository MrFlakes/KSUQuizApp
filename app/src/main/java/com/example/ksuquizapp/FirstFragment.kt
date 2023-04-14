package com.example.ksuquizapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.amplifyframework.api.graphql.model.ModelQuery
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.Consumer
import com.amplifyframework.core.model.query.predicate.QueryField
import com.amplifyframework.datastore.DataStoreException
import com.amplifyframework.datastore.DataStoreItemChange
import com.amplifyframework.datastore.generated.model.Questions
import com.example.ksuquizapp.databinding.FragmentFirstBinding



/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("OnViewCreated", "CreatedView")

       getQuestionFuntion("da01ac33-bde1-4bc2-89bf-f1b307271a7f")

       Log.i("QuestionOne" , getQuestionFuntion("da01ac33-bde1-4bc2-89bf-f1b307271a7f").toString())
        /*  Amplify.DataStore.query(
              Questions::class.java,
              { items ->
                  while (items.hasNext()) {
                      val item = items.next()
                      Log.i("Amplifyy", "Queried item: " + item.id)
                  }
              },
          )
          binding.buttonFirst.setOnClickListener {
              findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
          }*/

    }
     //   Log.d("Question", QuestionText)

        StoreQuestionList()
        Log.d("All Questions" ,   StoreQuestionList().toString())
    }
private fun getQuestionFuntion(id: String) {
    Amplify.API.query(
        ModelQuery.get(Questions::class.java, id),


        { Log.i("MyAmplifyApp", (it.data as Questions).question)},


        { Log.e("MyAmplifyApp", "Query failure")



        })

}
    private fun ListAllQuestion() {

        Amplify.DataStore.query(
            Questions::class.java,
            { items ->
                while (items.hasNext()) {
                    val item = items.next()
                    Log.i("Amplify", "Queried item: " + item.id)
                }
            },
            { failure -> Log.e("Tutorial", "Could not query DataStore", failure) }
        )
    }

   /* private fun getQuestionFuntion2(id: Int): String {

    var myString: String= ""
    Amplify.DataStore.query(
    Questions::class.java,
    { results ->
        if (!results.hasNext()) {
            val myModel = results.equals(Questions.QUESTION_NO.eq(id))
            myString = myModel.toString()
        }
    }, { error -> Log.e("Query failed", error.toString()) }
        )

        return myString;

    }*/

// create a list with all id's.


    private fun StoreQuestionList(): List<Unit> {
        // -Generate a numberlist

        val QuestionList = listOf(ListAllQuestion())

        //- pull everything, save into list
        return QuestionList
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
