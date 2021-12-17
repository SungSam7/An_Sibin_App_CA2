package com.example.firebaseloginsignup.models

var recipeList = mutableListOf<Recipe>()



class Recipe (var cover: Int, var chef: String, var title: String, var description: String, val id: Int? = recipeList.size)
