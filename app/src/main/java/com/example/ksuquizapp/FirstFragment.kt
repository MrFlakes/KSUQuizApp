package com.example.ksuquizapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amplifyframework.api.graphql.model.ModelQuery
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.query.Where
import com.amplifyframework.datastore.generated.model.Questions
import com.amplifyframework.datastore.generated.model.Questions.QUESTION_NO
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
              { failure -> Log.e("Amplifyy", "Could not query DataStore", failure) }
          )
          binding.buttonFirst.setOnClickListener {
              findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
          }*/

    }

private fun getQuestionFuntion(id: String) {
    Amplify.API.query(
        ModelQuery.get(Questions::class.java, id),


        { Log.i("MyAmplifyApp", "${(it.data as Questions).question }")},


        { Log.e("MyAmplifyApp", "Query failure", )



        })

}

    // -Generate a numberlist
    //- pull everything, save into list

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
