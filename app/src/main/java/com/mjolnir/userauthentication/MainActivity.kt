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

class MainActivity : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference
    companion object{
        const val KEY1="com.mjolnir.userauthentication.MainActivity.id"
        const val KEY2="com.mjolnir.userauthentication.MainActivity.email"
        const val KEY3="com.mjolnir.userauthentication.MainActivity.password"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var etUsername=findViewById<EditText>(R.id.etUsername2)
        var btnSignIn=findViewById<Button>(R.id.btnSignIn)


        var tvSignUp=findViewById<TextView>(R.id.tvSignUp)
        tvSignUp.setOnClickListener {
            intent= Intent(this,SignUp::class.java)
            startActivity(intent)
        }

        btnSignIn.setOnClickListener {
            val uniqueId=etUsername.text.toString().trim()
            if(uniqueId.isNotEmpty()){
                readData(uniqueId)
                etUsername.text.clear()
            }
            else{
                Toast.makeText(this,"Username Can't be Empty.",Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun readData(uniqueId: String) {
        databaseReference=FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(uniqueId).get().addOnSuccessListener {
            if(it.exists()){
                val email=it.child("email").value
                val password=it.child("password").value
                val userId=it.child("username").value
                intent=Intent(this,WelcomeActivity::class.java)
                intent.putExtra(KEY1,userId.toString())
                intent.putExtra(KEY2,email.toString())
                intent.putExtra(KEY3,password.toString())
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"User Not Found,Please Register!",
                    Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this,"Database Error!",Toast.LENGTH_SHORT).show()
        }
    }
}