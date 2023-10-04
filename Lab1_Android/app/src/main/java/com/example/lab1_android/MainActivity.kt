package com.example.lab1_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lab1_android.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var user: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        user = FirebaseAuth.getInstance()

        checkIfUserIsLogged()
        binding.btnLogin.setOnClickListener{
            registerUser()
        }





    }

    private fun checkIfUserIsLogged()
    {
        if(user.currentUser !=null)
            startActivity(Intent(this,SecondActivity::class.java))
            finish()
    }
    private fun registerUser()
    {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty()) {
            user.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful)
                {
                    Toast.makeText(this, "User added successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,SecondActivity::class.java))
                    finish()
                }else
                    {
                        user.signInWithEmailAndPassword(email, password).addOnCompleteListener { mtask->
                            if(mtask.isSuccessful)
                            {
                                startActivity(Intent(this,SecondActivity::class.java))
                                finish()
                            }else
                                    {
                                        Toast.makeText(this, task.exception!!.message, Toast.LENGTH_SHORT).show()
                                    }
                        }

                    }
            }





        }else {
            Toast.makeText(this, "Email and passwword cannot be empty", Toast.LENGTH_SHORT).show()
        }
    }
}