package com.tsu.wordsfactory.sign_up

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
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
            if (binding.textName.text.isNullOrEmpty()) {
                basicAlert("Please enter your name")
            } else if (binding.textEmail.text.isNullOrEmpty()) {
                basicAlert("Please enter your E-mail")
            } else if (binding.textPassword.text.isNullOrEmpty()) {
                basicAlert("Please enter your Password")
            } else {
                    val intent = Intent(this, DictionaryActivity::class.java)
                    startActivity(intent)
            }

        }

    }

    private fun basicAlert(message: String){
        val builder = AlertDialog.Builder(this)

        with(builder)
        {
            setTitle("Error")
            setMessage(message)
            setPositiveButton("OK") { _, _ ->
                // User clicked OK button
            }
            show()
        }

    }
}