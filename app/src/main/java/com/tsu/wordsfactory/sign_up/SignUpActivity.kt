package com.tsu.wordsfactory.sign_up

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import androidx.appcompat.app.AppCompatActivity
import com.tsu.wordsfactory.R
import com.tsu.wordsfactory.databinding.ActivitySignUpBinding
import com.tsu.wordsfactory.dictionary.DictionaryActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private var isInputPassVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textInputPassword.setEndIconOnClickListener {
            isInputPassVisible = !isInputPassVisible
            if (isInputPassVisible) {
                binding.textInputPassword.setEndIconDrawable(R.drawable.ic_eye_opened)
                binding.textPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                binding.textInputPassword.setEndIconDrawable(R.drawable.ic_eye_closed)
                binding.textPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }

        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, DictionaryActivity::class.java)
            startActivity(intent)
        }

    }
}