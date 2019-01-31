package com.sengbh.prtyup

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login_screen.*


class LogInActivity: AppCompatActivity() {
    private var mProgressBar: ProgressBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        initialize()

        log_in_btm.setOnClickListener {
            parseLogInActivity()
        }

        forgot_password_textView.setOnClickListener{
            forgotPassword()

        }

    }

    private fun initialize(){
        mProgressBar = ProgressBar(this)
    }

    private fun parseLogInActivity() {
        val email = email_login_edittext.text.toString()
        val password = password_login_editText.text.toString()

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Enter email and password", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d("Login", "Log in with email and password: $email/$password")
    }

    fun signIn(email: String, password: String, callback: (FirebaseUser?) -> Unit) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    //mProgressBar!!.setMessage("Login user..")
                    Log.d(MESSAGE, "Log in successfully")
                    val user = FirebaseAuth.getInstance().currentUser
                    callback.invoke(user)
                } else {
                    Log.d(MESSAGE, "Log in unsuccessfully", task.exception)
                    Toast.makeText(this, "Enter email and password", Toast.LENGTH_SHORT).show()
                    callback.invoke(null)
                }
            }
    }

    private fun forgotPassword() {
        Log.d(MESSAGE, "show reset password screen")
        val intent = Intent(this, ResetPasswordActivity::class.java)
        startActivity(intent)
    }
    companion object {
        private const val MESSAGE = "LogInActivity"
    }

}