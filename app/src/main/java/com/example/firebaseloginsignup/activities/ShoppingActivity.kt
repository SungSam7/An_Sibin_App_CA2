package com.example.firebaseloginsignup.activities

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import androidx.appcompat.app.ActionBar
import com.example.firebaseloginsignup.databinding.ActivityShoppingBinding


class ShoppingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingBinding
    private lateinit var actionBar: ActionBar
    //lateinit var app: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        actionBar = supportActionBar!!
        actionBar.title = "Shopping List"
      //  shoppingList = shoppingListJSONStore(applicationContext)

        binding = ActivityShoppingBinding.inflate(layoutInflater)

        setContentView(binding.root)

       // app = application as MainActivity


        binding.backBtn.setOnClickListener{
            onBackPressed()
        }
        // Initializing the array lists and the adapter
        var itemlist = arrayListOf<String>()
        var adapter = ArrayAdapter<String>(this,
            R.layout.simple_list_item_multiple_choice
            , itemlist)

    binding.add.setOnClickListener {
    itemlist.add(binding.editText.text.toString())
    binding.listView.adapter =  adapter
    adapter.notifyDataSetChanged()

        //app.shoppings.create(itemlist.copy())

    binding.editText.text.clear()
}


        // Selecting and Deleting the items from the list when the delete button is pressed
        binding.delete.setOnClickListener {
            val position: SparseBooleanArray = binding.listView.checkedItemPositions
            val count = binding.listView.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item))
                {
                    adapter.remove(itemlist.get(item))
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }

        // Clearing all the items in the list when the clear button is pressed
        binding.clear.setOnClickListener {

            itemlist.clear()
            adapter.notifyDataSetChanged()

        }

        // Adding the toast message to the list when an item on the list is pressed
        binding.listView.setOnItemClickListener { adapterView, view, i, l ->
            android.widget.Toast.makeText(this, "You Selected the item --> "+itemlist.get(i), android.widget.Toast.LENGTH_SHORT).show()
        }
    }
}