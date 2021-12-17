package com.example.firebaseloginsignup.views

import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseloginsignup.databinding.CardCellBinding
import com.example.firebaseloginsignup.models.Recipe

class CardViewHolder (private val cardCellBinding: CardCellBinding) : RecyclerView.ViewHolder(cardCellBinding.root) {

    fun bindRecipe(recipe: Recipe)
    {
        cardCellBinding.cover.setImageResource(recipe.cover)
        cardCellBinding.chef.text = recipe.chef
        cardCellBinding.title.text = recipe.title

    }

}