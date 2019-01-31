package com.sengbh.prtyup

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ResetPasswordActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        reset_password_btm.setOnClickListener {
            val resetEmail = reset_email_btm.text.toString().trim()

            if(TextUtils.isEmpty(resetEmail)){
                Toast.makeText(applicationContext, "Enter email", Toast.LENGTH_SHORT).show()
            }else{
                FirebaseAuth.getInstance()!!.sendPasswordResetEmail(resetEmail)
                    .addOnCompleteListener{ task ->
                        if(task.isSuccessful){
                            Toast.makeText(this@ResetPasswordActivity, "An email has sent to your email address", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this@ResetPasswordActivity, "Fail to send an email", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }

}