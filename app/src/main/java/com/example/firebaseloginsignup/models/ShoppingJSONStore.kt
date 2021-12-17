package org.wit.placemark.models

import android.content.Context
import android.net.Uri
import android.util.Log.i
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.example.firebaseloginsignup.helpers.*
import com.example.firebaseloginsignup.models.ShoppingModel
import com.example.firebaseloginsignup.models.ShoppingStore

import java.lang.reflect.Type
import java.util.*

const val JSON_FILE = "shoppings.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
                 .registerTypeAdapter(Uri::class.java, UriParser())
                 .create()
val listType: Type = object : TypeToken<ArrayList<ShoppingModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class ShoppingJSONStore(private val context: Context) : ShoppingStore {

    var shoppings = mutableListOf<ShoppingModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<ShoppingModel> {
        logAll()
        return shoppings
    }

    override fun create(shopping: ShoppingModel) {
        shopping.id = generateRandomId()
        shoppings.add(shopping)
        serialize()
    }


    override fun update(shopping: ShoppingModel) {
        val shoppingsList = findAll() as ArrayList<ShoppingModel>
        var foundShopping: ShoppingModel? = shoppingsList.find { p -> p.id == shopping.id }
        if (foundShopping != null) {
            foundShopping.title = shopping.title
            foundShopping.description = shopping.description

        }
        serialize()
    }

    override fun delete(shopping: ShoppingModel) {
        shoppings.remove(shopping)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(shoppings, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        shoppings = gsonBuilder.fromJson(jsonString, listType)
    }

    private fun logAll() {
        shoppings.forEach { ("$it") }
    }
}

class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}