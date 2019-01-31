package com.sengbh.prtyup

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_create_account.*
import java.util.*

class CreateAccountActivity: AppCompatActivity(){
    private var selectPhotoUri: Uri? = null
    private var username: String? = null
    private var useremail: String? = null
    private var userpassword: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        register_btm.setOnClickListener{
            parseCreateAccountActivity()
        }
        select_photo_btm.setOnClickListener{
            selectPhoto()
        }
    }

    private fun parseCreateAccountActivity(){

        //val username = username_editText.text.toString()
        this.username = username_editText?.text.toString()
        this.useremail = useremail_editText.text.toString()
        this.userpassword= userpassword_editText.text.toString()

        if (TextUtils.isEmpty(useremail) || TextUtils.isEmpty(userpassword) || TextUtils.isEmpty(username)){
            Toast.makeText(this, "Enter username, email and password", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d(MESSAGE, "User name is: $username")
        Log.d(MESSAGE, "Email is: $useremail")
        Log.d(MESSAGE, "Password is: $userpassword")

        //Firebase authentication
        fun createAccount(useremail: String, userpassword: String, callback: (FirebaseUser?) -> Unit){
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(useremail, userpassword)
                .addOnCompleteListener{ task ->
                    if(task.isSuccessful){
                        Log.d(MESSAGE, "Create an account is successful")
                        val user = FirebaseAuth.getInstance().currentUser
                        callback.invoke(user)

                        uploadPhotoToFirebase()

                    }else{
                        Log.d(MESSAGE, "Create an account fail", task.exception)
                        Toast.makeText(this, "Failed to create an account", Toast.LENGTH_SHORT).show()
                        callback.invoke(null)
                    }
                }
        }
    }

    private fun selectPhoto(){
        Log.d(MESSAGE, "show photo selector")
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "imag/*"
        val pickIntent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        val chooserInt = Intent.createChooser(intent, "Select Image")
        chooserInt.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(pickIntent))
        startActivityForResult(chooserInt, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0 && resultCode == Activity.RESULT_OK && data != null){
            Log.d(MESSAGE, "Photo was selected")

            selectPhotoUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectPhotoUri)

            selectphoto_Image_btm.setImageBitmap(bitmap)
            select_photo_btm.alpha = 0f
            /*val bitmapDrawable = BitmapDrawable(bitmap)
            select_photo_btm.setBackgroundDrawable(bitmapDrawable)*/
        }
    }

    private fun uploadPhotoToFirebase(){
        if(selectPhotoUri == null) return
        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(selectPhotoUri!!)
            .addOnSuccessListener {
                Log.d(MESSAGE, "Successfully upload an image: ${it.metadata?.path}")

                ref.downloadUrl.addOnSuccessListener {
                    Log.d(MESSAGE, "File location: ${it}")

                    saveUserToDatabase(it.toString())
                }
            }
            .addOnFailureListener{

            }
    }

    private fun saveUserToDatabase(profileImageUrl: String){
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference(("/users/$uid"))

        val user = User(uid, username_editText.text.toString(), profileImageUrl)
        ref.setValue(user)
            .addOnSuccessListener {
                Log.d(MESSAGE, "save image:")
            }
    }

    companion object {
        private const val MESSAGE = "CreateAccountActivity"
    }


}

class User(val uid: String, val userName: String, val profileImageUrl: String)