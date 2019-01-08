package com.sengbh.prtyup

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class EmailPasswordActivity {
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


//  //  private fun signIn(email: String, password: String) {
//        Log.d(TAG, "signIn:$email")
//        if (!validateForm()) {
//            return
//        }
//
//        showProgressDialog()
//
//        // [START sign_in_with_email]
//        auth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "signInWithEmail:success")
//                    val user = auth.currentUser
//                    updateUI(user)
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "signInWithEmail:failure", task.exception)
//                    Toast.makeText(baseContext, "Authentication failed.",
//                        Toast.LENGTH_SHORT).show()
//                    updateUI(null)
//                }
//
//                // [START_EXCLUDE]
//                if (!task.isSuccessful) {
//                    status.setText(R.string.auth_failed)
//                }
//                hideProgressDialog()
//                // [END_EXCLUDE]
//            }
//        // [END sign_in_with_email]
//    }

}