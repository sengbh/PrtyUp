package com.sengbh.prtyup

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login_screen.*

class LogInActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        log_in_btm.setOnClickListener {
            parseLogInActivity()
        }

        back_to_main_screen_textView.setOnClickListener {
            finish()
        }
    }

    private fun parseLogInActivity() {
        val email = email_login_edittext.text.toString()
        val password = password_login_editText.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Enter email and password", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d("Login", "Log in with email and password: $email/$password")
    }

    fun loggingIn(email: String, password: String, callback: (FirebaseUser?) -> Unit) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("LogInAccountActivity", "Log in successfully")
                    val user = FirebaseAuth.getInstance().currentUser
                    callback.invoke(user)
                } else {
                    Log.d("LogInAccountActivity", "Log in unsuccessfully", task.exception)
                    Toast.makeText(this, "Enter email and password", Toast.LENGTH_SHORT).show()
                    callback.invoke(null)
                }
            }
    }

}