package com.example.lab1_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab1_android.databinding.ActivityMainBinding
import com.example.lab1_android.databinding.ActivitySecondBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private  lateinit var user:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)


        user = FirebaseAuth.getInstance()

        if(user.currentUser != null)
        {
             user.currentUser?.let {
                binding.tvUserEmail.text = it.email
             }
        }
        binding.btnSignOut.setOnClickListener{
            user.signOut()
            startActivity(
                Intent(this,MainActivity::class.java)
            )
            finish()
        }
    }
}