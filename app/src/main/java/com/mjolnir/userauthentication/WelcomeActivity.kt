package com.mjolnir.userauthentication

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_wecome)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var tvusername=findViewById<TextView>(R.id.tvUsername)
        var tvemail=findViewById<TextView>(R.id.tvEmail)
        var tvpassword=findViewById<TextView>(R.id.tvPassword)

        val username=intent.getStringExtra(MainActivity.KEY1)
        val email=intent.getStringExtra(MainActivity.KEY2)
        val password=intent.getStringExtra(MainActivity.KEY3)

        tvusername.text=username.toString()
        tvemail.text=email.toString()
        tvpassword.text=password.toString()
    }
}