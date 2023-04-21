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
import androidx.navigation.fragment.findNavController
import com.amplifyframework.api.graphql.model.ModelQuery
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.query.Where
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

        var s = "Score: ${Manager.score}"
        var q = "Question ${Manager.progress}/10"

        binding.score.text = s
        binding.questionNum.text = q


        Amplify.DataStore.query(
            Questions::class.java,
            Where.matches(Questions.QUESTION_NO.eq(Manager.progress)),
            { items ->
                while (items.hasNext()) {

                    val item = items.next()
                    binding.questionText.text = item.question
                    _buttons = arrayOf(binding.button1, binding.button2, binding.button3, binding.button4)
                    _buttons.shuffle()

                    for(b in _buttons){
                        b.setOnClickListener{checkAnswer(b, item.correctAnswer)}
                    }
                    _buttons[0].text = item.answer[0]
                    _buttons[1].text = item.answer[1]
                    _buttons[2].text = item.answer[2]
                    _buttons[3].text = item.answer[3]
                    Log.i("Amplifyy", "Queried item: " + item.question)
                }
            },
            { failure -> Log.e("Amplifyy", "Could not query DataStore", failure) }
        )

    }

    fun disableButtons(){
        _buttons.forEach { it.isEnabled = false }
    }

    fun checkAnswer(button: Button, rightAnswer: String){

        var color = if(button.text.equals(rightAnswer)) R.color.ksu_green else R.color.ksu_red

        button.setBackgroundTintList(ColorStateList.valueOf(
            ResourcesCompat.getColor(
                getResources(), color, null)))
        disableButtons()


        Handler(Looper.getMainLooper()).postDelayed({

            Manager.score = if (button.text.equals(rightAnswer)) Manager.score + 1 else Manager.score - 1


            if(Manager.progress >= Manager.maxScore){
                findNavController().navigate(R.id.action_SecondFragment_to_thirdFragment)
            } else{
                Manager.progress++
                val helper = Helper()
                helper.recreateActivityCompat(activity)
            }

        }, 1000)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}