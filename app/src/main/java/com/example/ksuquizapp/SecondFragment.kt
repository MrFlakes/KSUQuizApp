package com.example.ksuquizapp

import android.content.res.ColorStateList
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.ksuquizapp.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("createView", "Frag 2 OnViewCreated Called")
        binding.button1.setOnClickListener{
            binding.button1.text = "I've changed"
            binding.button1.setBackgroundTintList(ColorStateList.valueOf(
                ResourcesCompat.getColor(
                    getResources(), R.color.ksu_green, null)))
            Handler(Looper.getMainLooper()).postDelayed({
                val helper = Helper()
                helper.recreateActivityCompat(activity)
            }, 1000)

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}