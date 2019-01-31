package com.sengbh.prtyup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        google_btm.setOnClickListener {
            //call google account function
            googleAccount()
        }
        facebook_btm.setOnClickListener {
            //call facebook function
            facebookAccount()
        }
        have_an_account_textView.setOnClickListener {
            logIn()
        }

        create_account_btm.setOnClickListener {
            //call createAccount function
            createAccount()
        }

        /*forgot_password_textView.setOnClickListener {
            forgotPassword()
        }*/


        /* override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }*/

    }

    private fun logIn() {
        Log.d(MESSAGE, "show log in screen")
        val intent = Intent(this, LogInActivity::class.java)
        startActivity(intent)
    }

    private fun createAccount() {
        Log.d(MESSAGE, "show create an account screen")
        val intent = Intent(this, CreateAccountActivity::class.java)
        startActivity(intent)
    }

    private fun googleAccount() {
        Log.d(MESSAGE, "show google account screen")
        val intent = Intent(this, GoogleActivity::class.java)
        startActivity(intent)

    }

    private fun facebookAccount() {
        Log.d(MESSAGE, "show facebook screen")
        val intent = Intent(this, FacebookActivity::class.java)
        startActivity(intent)
    }

    /*private fun forgotPassword() {
        Log.d(MESSAGE, "show reset password screen")
        val intent = Intent(this, ResetPasswordActivity::class.java)
        startActivity(intent)
    }*/

    companion object {
        private const val MESSAGE = "MainActivity"
    }
}
