package com.example.firebaseloginsignup.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.firebaseloginsignup.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var actionBar: ActionBar
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = supportActionBar!!
        actionBar.title = "Main Menu"

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()


        //handle click, logout
        binding.logoutBtn.setOnClickListener{
          //  firebaseAuth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            checkUser()
        }

        binding.logoutBtn1.setOnClickListener{
            firebaseAuth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            checkUser()
        }

    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser != null)
        {
            val email = firebaseUser.email

            //set to text view
            binding.emailTv.text = email

        }

        else{

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}