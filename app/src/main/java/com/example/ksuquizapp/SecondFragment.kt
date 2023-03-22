package com.example.ksuquizapp

import android.content.res.ColorStateList
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.amplifyframework.api.graphql.model.ModelQuery
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.Questions
import com.example.ksuquizapp.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var _buttons : Array<Button>
    private var _index : Int = 0

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _index = Manager.progress - 1
        if(_index > 0) _index %= 2

        var s = "Score: ${Manager.score}"
        var q = "Question ${Manager.progress}/10"

        binding.score.text = s
        binding.questionNum.text = q
        binding.questionText.text = Manager.questions[_index].question

        _buttons = arrayOf(binding.button1, binding.button2, binding.button3, binding.button4)
        _buttons.shuffle()


        for(b in _buttons){
            b.setOnClickListener{checkAnswer(b)}
        }
        _buttons[0].text = Manager.questions[_index].rightAnswer
        _buttons[1].text = Manager.questions[_index].alternatives[0]
        _buttons[2].text = Manager.questions[_index].alternatives[1]
        _buttons[3].text = Manager.questions[_index].alternatives[2]

    }

    fun disableButtons(){
        _buttons.forEach { it.isEnabled = false }
    }

    fun checkAnswer(button: Button){

        val answer = Manager.questions[_index].rightAnswer

        var color = if(button.text.equals(answer)) R.color.ksu_green else R.color.ksu_red

        button.setBackgroundTintList(ColorStateList.valueOf(
            ResourcesCompat.getColor(
                getResources(), color, null)))
        disableButtons()


        Handler(Looper.getMainLooper()).postDelayed({

            Manager.score = if (button.text.equals(answer)) Manager.score + 1 else Manager.score - 1
            Manager.progress++

            val helper = Helper()
            helper.recreateActivityCompat(activity)
        }, 1000)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}