package com.tsu.wordsfactory.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.tsu.wordsfactory.R
import com.tsu.wordsfactory.databinding.ActivityIntroBinding
import com.tsu.wordsfactory.sign_up.SignUpActivity

class IntroductionActivity : AppCompatActivity() {

    private lateinit var adapter: IntroAdapter
    private lateinit var viewPager: ViewPager2

    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textList = listOf(R.string.learn_anytime, R.string.find_a_course, R.string.improve_your_skills)
        val imgList = listOf(R.drawable.img_intro, R.drawable.img_splash_screen, R.drawable.img_intro)

        adapter = IntroAdapter(this, textList, imgList)
        viewPager = binding.viewPager
        viewPager.adapter = adapter

        binding.btnSkip.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.btnNext.setOnClickListener {
            if (viewPager.currentItem == viewPager.childCount + 1) {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }
            viewPager.currentItem ++

        }
    }
}