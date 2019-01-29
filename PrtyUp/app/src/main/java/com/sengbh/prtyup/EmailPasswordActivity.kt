package com.sengbh.prtyup

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class EmailPasswordActivity{
        fun signUp(email: String, password: String, callback: (FirebaseUser?) -> Unit) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("PRTYUP", "createUserWithEmail:success")
                        val user = FirebaseAuth.getInstance().currentUser
                        callback.invoke(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("PRTYUP", "createUserWithEmail:failure", task.exception)
                        callback.invoke(null)
                    }
                }
        }

}