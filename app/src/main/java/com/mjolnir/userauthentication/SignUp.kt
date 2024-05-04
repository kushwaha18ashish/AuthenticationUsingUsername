package com.mjolnir.userauthentication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    lateinit var database:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var etUsername=findViewById<EditText>(R.id.etUsername)
        var etEmail=findViewById<EditText>(R.id.etEmail)
        var etPassword=findViewById<EditText>(R.id.etPassword)
        var btnSignUp=findViewById<Button>(R.id.btnSignUp)
        var tvSignIn=findViewById<TextView>(R.id.tvSignIn)


        tvSignIn.setOnClickListener {
            intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnSignUp.setOnClickListener {

            val uniqueId=etUsername.text.toString()
            val email=etEmail.text.toString()
            val password=etPassword.text.toString()
            val user=User(uniqueId,email,password)
            database=FirebaseDatabase.getInstance().getReference("Users")
            database.child(uniqueId).setValue(user).addOnSuccessListener {
                etUsername.text.clear()
                etEmail.text.clear()
                etPassword.text.clear()
                Toast.makeText(this,"User Registered Successfully.",
                    Toast.LENGTH_SHORT).show()
                intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}