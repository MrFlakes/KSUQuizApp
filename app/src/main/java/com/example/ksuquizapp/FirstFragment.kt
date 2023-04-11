package com.example.ksuquizapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.amplifyframework.api.graphql.model.ModelQuery
import com.amplifyframework.core.Amplify
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

        var number = 1
        while (number>0) {number++}
        Amplify.API.query(
            ModelQuery.list (Questions::class.java, Questions.QUESTION_NO.contains(number.toString())), // wait to update to Int
            { response ->
                response.data.forEach { Questions ->
                    // Where.matches(Questions.questionNo)
                    Log.i("MyAmplifyApp", Questions.questionNo.toString())
                }
            },
            { Log.e("MyAmplifyApp", "Query failure", it) }
        )
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}