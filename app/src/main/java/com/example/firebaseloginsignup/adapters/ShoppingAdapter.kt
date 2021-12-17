//package com.example.firebaseloginsignup.adapters
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.firebaseloginsignup.models.ShoppingModel
//import com.squareup.picasso.Picasso
//import org.wit.placemark.databinding.CardPlacemarkBinding
//import org.wit.placemark.models.PlacemarkModel
//
//interface ShoppingListener {
//    fun onPlacemarkClick(placemark: ShoppingModel)
//}
//
//class ShoppingAdapter constructor(
//    private var placemarks: List<ShoppingModel>,
//    private val listener: ShoppingListener
//) :
//        RecyclerView.Adapter<ShoppingAdapter.MainHolder>() {
//
//
//
//
//
//    override fun getItemCount(): Int = placemarks.size
//
//    class MainHolder(private val binding: CardPlacemarkBinding) :
//            RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(placemark: PlacemarkModel, listener: PlacemarkListener) {
//            binding.placemarkTitle.text = placemark.title
//            binding.description.text = placemark.description
//            Picasso.get().load(placemark.image).resize(200,200).into(binding.imageIcon)
//            binding.root.setOnClickListener { listener.onPlacemarkClick(placemark) }
//        }
//    }
//}
