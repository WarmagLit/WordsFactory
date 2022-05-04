package com.tsu.wordsfactory.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.tsu.wordsfactory.R
import com.tsu.wordsfactory.databinding.ActivityIntroBinding
import com.tsu.wordsfactory.sign_up.SignUpActivity
import com.tsu.wordsfactory.utils.Preferences
import timber.log.Timber

class IntroductionActivity : AppCompatActivity() {

    private lateinit var adapter: IntroAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var viewModel: IntroductionViewModel

    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = IntroductionViewModel(Preferences(this))

        val textList = listOf(R.string.learn_anytime, R.string.find_a_course, R.string.improve_your_skills)
        val imgList = listOf(R.drawable.img_intro, R.drawable.img_splash_screen, R.drawable.img_intro)

        if (!viewModel.isFirstAppStart()) {
            Timber.d("True...")
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        //Timber.d("False...")
        Log.d("Tags", "False")

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