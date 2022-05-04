package com.tsu.wordsfactory.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.tsu.wordsfactory.R
import com.tsu.wordsfactory.databinding.FragmentDictionaryBinding
import com.tsu.wordsfactory.databinding.FragmentIntroBinding
import com.tsu.wordsfactory.databinding.FragmentVideoBinding

const val ARG_OBJECT = "text"
const val ARG_OBJECT2 = "image"

class IntroFragment : Fragment() {

    private var _binding: FragmentIntroBinding? = null

    // This property is only valid between onCreateView and  onDestroyView.
    //private val binding get() = _binding!!
    private lateinit var binding: FragmentIntroBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIntroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            binding.textHeader.setText(getInt(ARG_OBJECT))
            binding.imageIntro.setImageResource(getInt(ARG_OBJECT2))
        }
    }

}