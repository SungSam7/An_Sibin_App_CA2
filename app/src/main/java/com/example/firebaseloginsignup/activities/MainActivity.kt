package com.example.firebaseloginsignup.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firebaseloginsignup.databinding.ActivityMainBinding

import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.firebaseloginsignup.adapters.CardAdapter
import com.example.firebaseloginsignup.R
import com.example.firebaseloginsignup.models.Recipe
import com.example.firebaseloginsignup.models.ShoppingStore
import com.example.firebaseloginsignup.models.recipeList
import com.google.android.material.navigation.NavigationView
import org.wit.placemark.models.ShoppingJSONStore
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var toggle : ActionBarDrawerToggle
    private lateinit var actionBar: ActionBar
    lateinit var app: MainActivity

    lateinit var shoppings: ShoppingStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // maybe login page?

        Timber.plant(Timber.DebugTree())
        shoppings = ShoppingJSONStore(applicationContext)
        Timber.i("Shopping List started")

        actionBar = supportActionBar!!
        actionBar.title = "Profile"


        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        navView.setNavigationItemSelectedListener {

            when(it.itemId)
            {
                R.id.nav_home -> startActivity(Intent(this, ProfileActivity::class.java))
                R.id.add_recipe -> startActivity(Intent(this, AddRecipeActivity::class.java))
                R.id.show_recipe -> Toast.makeText(applicationContext, "Show Recipes", Toast.LENGTH_SHORT).show()
                R.id.delete_update_recipe -> Toast.makeText(applicationContext, "Update/Delete Recipes", Toast.LENGTH_SHORT).show()
                R.id.shopping_list -> startActivity(Intent(this, ShoppingActivity::class.java))
                R.id.contact_us -> startActivity(Intent(this, LocationActivity::class.java))

            }

            true
        }


        populateRecipes()

        val mainActivity = this
        binding.recyclerView.apply{
            layoutManager = GridLayoutManager(applicationContext, 3)
            adapter = CardAdapter(recipeList)
        }
    }

    private fun populateRecipes() {
        val recipe1 = Recipe(
            R.drawable.food1,
            "Gordon Ramsey",
            "Kitchen Nightmares",
            "Hell is at your door"
        )
        recipeList.add(recipe1)

        val recipe2 = Recipe(
            R.drawable.food2,
            "Gordon Ramsey",
            "Kitchen Nightmares",
            "Hell is at your door"
        )
        recipeList.add(recipe2)

        val recipe3 = Recipe(
            R.drawable.food3,
            "Gordon Ramsey",
            "Kitchen Nightmares",
            "Hell is at your door"
        )
        recipeList.add(recipe3)

        val recipe4 = Recipe(
            R.drawable.food4,
            "Gordon Ramsey",
            "Kitchen Nightmares",
            "Hell is at your door"
        )
        recipeList.add(recipe1)

        val recipe5 = Recipe(
            R.drawable.food5,
            "Gordon Ramsey",
            "Kitchen Nightmares",
            "Hell is at your door"
        )
        recipeList.add(recipe5)








    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }




    }



