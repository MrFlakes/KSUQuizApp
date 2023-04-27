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
 * Used as the question delivery screen. Most of our logic in the app is written here.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var _buttons : Array<Button>

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

        //Query the database for a question with Question_NO corresponding to how many questions the user has already answered (i.e. progress).
        Amplify.DataStore.query(
            Questions::class.java,
            Where.matches(Questions.QUESTION_NO.eq(Manager.progress)),
            { items ->
                while (items.hasNext()) {

                    //Store the queried information into a locally scopred array.
                    val item = items.next()
                    binding.questionText.text = item.question
                    _buttons = arrayOf(binding.button1, binding.button2, binding.button3, binding.button4)
                    _buttons.shuffle() //Shuffle the button order so the fourth button does not always contain the right answer.

                    for(b in _buttons){
                        b.setOnClickListener{checkAnswer(b, _buttons, item.correctAnswer)}
                    }
                    //Set the button text to the queried information.
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

    //Disable the buttons once a user has answered, so they cannot choose multiple choices.
    fun disableButtons(){
        _buttons.forEach { it.isEnabled = false }
    }

    fun checkAnswer(clickedButton: Button, buttons: Array<Button>, rightAnswer: String){

        //Set the button color
        var color = if(clickedButton.text.equals(rightAnswer)) R.color.ksu_green else R.color.ksu_red
        var delay = if(clickedButton.text.equals(rightAnswer)) 1500 else 4000

        clickedButton.setBackgroundTintList(ColorStateList.valueOf(
            ResourcesCompat.getColor(
                getResources(), color, null)))

        for(b in _buttons){
            if(b.text.equals(rightAnswer)){
                b.setBackgroundTintList(ColorStateList.valueOf(
                    ResourcesCompat.getColor(
                        getResources(), R.color.ksu_green, null)))
            }
        }

        disableButtons()

        //Delay the screen refresh for 1 second to allow the button color to be changed.
        Handler(Looper.getMainLooper()).postDelayed({

            Manager.score = if (clickedButton.text.equals(rightAnswer)) Manager.score + 1 else Manager.score //Update the user's score
            Manager.score = Manager.score.coerceIn(0, Manager.maxScore)

            if(Manager.progress >= Manager.maxScore){
                findNavController().navigate(R.id.action_SecondFragment_to_thirdFragment) //If the user has answered 10 questions, show the final score screen.
            } else{
                Manager.progress++
                val helper = Helper()
                helper.recreateActivityCompat(activity) //Refresh the screen, this triggering the onViewCreated again, but this time with progress increased by 1.
            }

        }, delay.toLong())
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}