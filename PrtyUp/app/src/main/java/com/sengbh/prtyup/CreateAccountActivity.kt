package com.sengbh.prtyup

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_create_account.*

class CreateAccountActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        register_btm.setOnClickListener{
            parseCreateAccountActivity()
        }

    }
    private fun parseCreateAccountActivity(){
        val useremail = useremail_editText.text.toString()
        val userpassword = userpassword_editText.text.toString()

        if (useremail.isEmpty() || userpassword.isEmpty()){
            Toast.makeText(this, "Enter email and password", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d("CreateAccountActivity", "Email is: $useremail")
        Log.d("CreateAccountActivity", "Password is: $userpassword")

        //Firebase auth
        fun createAccount(useremail: String, userpassword: String, callback: (FirebaseUser?) -> Unit){
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(useremail, userpassword)
                .addOnCompleteListener{ task ->
                    if(task.isSuccessful){
                        Log.d("CreateAccountActivity", "Create an account is successful")
                        val user = FirebaseAuth.getInstance().currentUser
                        callback.invoke(user)

                    }else{
                        Log.d("CreateAccountActivity", "Create an account fail", task.exception)
                        Toast.makeText(this, "Failed to create an account", Toast.LENGTH_SHORT).show()
                        callback.invoke(null)
                    }
                }
        }
    }

}