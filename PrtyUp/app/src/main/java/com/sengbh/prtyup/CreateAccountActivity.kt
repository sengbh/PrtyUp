package com.sengbh.prtyup

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_create_account.*

class CreateAccountActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        val username = username_editText.toString()
        val email = useremail_editText.toString()
        val password = userpassword_editText.toString()

        Log.d("CreateAccountActivity", "Email is" + email)
        Log.d("CreateAccountActivity", "Password: $password")

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if(task.isSuccessful)
                    Log.d("CreateAccountActivity", "CreateUserWithEmail: Success")
                    val username = FirebaseAuth.getInstance().currentUser
                    return@addOnCompleteListener
            }

    }

}