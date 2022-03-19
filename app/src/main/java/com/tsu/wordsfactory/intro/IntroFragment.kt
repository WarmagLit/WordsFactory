package com.tsu.wordsfactory.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.tsu.wordsfactory.R

const val ARG_OBJECT = "text"
const val ARG_OBJECT2 = "image"

class IntroFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            val textView: TextView = view.findViewById(R.id.textView)
            textView.setText(getInt(ARG_OBJECT))
            val imageView: ImageView = view.findViewById(R.id.imageView)
            imageView.setImageResource(getInt(ARG_OBJECT2))
        }
    }

}