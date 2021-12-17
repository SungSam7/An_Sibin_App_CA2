package com.example.firebaseloginsignup.models

interface ShoppingStore {
    fun findAll(): List<ShoppingModel>
    fun create(placemark: ShoppingModel)
    fun update(placemark: ShoppingModel)
    fun delete(placemark: ShoppingModel)
}