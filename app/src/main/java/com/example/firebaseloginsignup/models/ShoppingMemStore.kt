package org.wit.shopping.models

import android.util.Log.i
import com.example.firebaseloginsignup.models.ShoppingModel
import com.example.firebaseloginsignup.models.ShoppingStore
import timber.log.Timber


var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class ShoppingMemStore : ShoppingStore {

    val shoppings = ArrayList<ShoppingModel>()

    override fun findAll(): List<ShoppingModel> {
        return shoppings
    }

    override fun create(shopping: ShoppingModel) {
        shopping.id = getId()
        shoppings.add(shopping)
        logAll()
    }

    override fun update(shopping: ShoppingModel) {
        val foundShopping: ShoppingModel? = shoppings.find { p -> p.id == shopping.id }
        if (foundShopping != null) {
            foundShopping.title = shopping.title
            foundShopping.description = shopping.description

        }
    }

    override fun delete(shopping: ShoppingModel) {
        shoppings.remove(shopping)
    }

    private fun logAll() {
        shoppings.forEach { Timber.i("$it") }
    }
}