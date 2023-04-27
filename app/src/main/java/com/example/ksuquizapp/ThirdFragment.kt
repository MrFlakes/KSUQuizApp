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
import com.amplifyframework.core.model.query.Where
import com.amplifyframework.datastore.generated.model.Questions
import com.example.ksuquizapp.databinding.FragmentFirstBinding
import com.example.ksuquizapp.databinding.FragmentThirdBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 * Used as the final score screen.
 */
class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var s = "";
        if(Manager.score >= 10){
            s = "Perfect!!!\nYou got ${Manager.score} right!"
        } else if(Manager.score >= 7 && Manager.score <= 9){
            s = "Nice Job!\nYou got ${Manager.score} right!"
        } else if(Manager.score >= 5 && Manager.score <= 8){
            s = "Not bad.\nYou got ${Manager.score} right!"
        } else {
            s = "Uh oh.\nYou only got ${Manager.score} right!"
        }
        binding.textView2.text = s;

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_thirdFragment_to_FirstFragment)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}