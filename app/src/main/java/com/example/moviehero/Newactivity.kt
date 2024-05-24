package com.example.moviehero

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NewActivity : AppCompatActivity() {
    lateinit var mob: EditText
    lateinit var pass: EditText
    lateinit var btn1: Button
    lateinit var textforget: TextView
    lateinit var newregistration: TextView
    val validMobileNumber = "7599940643"
    val validPassword = "thonas"
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            val intent = Intent(this@NewActivity, MainActivity::class.java)
            startActivity(intent)

        }else{
            setContentView(R.layout.activity_newactivity)

        }



        // Set the title of the activity
        supportActionBar?.title = "Login Page"

        // Initialize views
        mob = findViewById(R.id.mob)
        pass = findViewById(R.id.pass)
        btn1 = findViewById(R.id.btn1)
        textforget = findViewById(R.id.textforget)
        newregistration = findViewById(R.id.newregistration)

        // Set click listener on the button
        btn1.setOnClickListener {
            val mobileNumber = mob.text.toString()
            val password = pass.text.toString()

            if ((mobileNumber == validMobileNumber) && (password == validPassword)) {
                savePreferences()
                val intent = Intent(this@NewActivity, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this@NewActivity, "Incorrect password", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        finish()
    }

    fun savePreferences() {
        sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
    }
}

