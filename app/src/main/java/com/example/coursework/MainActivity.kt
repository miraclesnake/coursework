package com.example.coursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coursework.entryPages.LogInPage
import com.example.coursework.entryPages.SignUpPage
import com.example.coursework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var main: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main = ActivityMainBinding.inflate(layoutInflater)
        setContentView(main.root)

        main.LogIn.setOnClickListener {
            val logInIntent = Intent(this, LogInPage::class.java)
            startActivity(logInIntent)
        }

        main.SignUp.setOnClickListener {
            val signUpIntent = Intent(this, SignUpPage::class.java)
            startActivity(signUpIntent)
        }
    }
}