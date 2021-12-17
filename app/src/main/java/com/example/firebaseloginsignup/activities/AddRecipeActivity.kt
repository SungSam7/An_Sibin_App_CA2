package com.example.firebaseloginsignup.activities

import android.app.ActionBar
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaseloginsignup.databinding.ActivityAddRecipeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AddRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddRecipeBinding

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var progressDialog: ProgressDialog

    private lateinit var actionBar: androidx.appcompat.app.ActionBar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = supportActionBar!!
        actionBar.title = "Add Recipe"

        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait....")
        progressDialog.setCanceledOnTouchOutside(false)


        binding.backBtn.setOnClickListener{
            onBackPressed()
        }

        binding.submitBtn.setOnClickListener{
            validateData()
        }


    }

    private var recipe = ""

    private fun validateData() {
        recipe = binding.recipeEt.text.toString().trim()

        if(recipe.isEmpty()){
            Toast.makeText(this, "Enter a Recipe....", Toast.LENGTH_SHORT).show()
        }
        else
            addRecipeFirebase()
    }

    private fun addRecipeFirebase() {
        progressDialog.show()

        val timestamp = System.currentTimeMillis()

        // adding the data to firebase
        val hashMap = HashMap<String, Any>()
        hashMap["id"] = "$timestamp"
        hashMap["recipe"] = recipe
        hashMap["timestamp"] = timestamp


        val ref = FirebaseDatabase.getInstance().getReference("Recipes")
        ref.child("$timestamp")
            .setValue(hashMap)
            .addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show()


            }
            .addOnFailureListener{ e->
                progressDialog.dismiss()
                Toast.makeText(this, "Failed to add due to ${e.message}", Toast.LENGTH_SHORT).show()

            }
    }
}