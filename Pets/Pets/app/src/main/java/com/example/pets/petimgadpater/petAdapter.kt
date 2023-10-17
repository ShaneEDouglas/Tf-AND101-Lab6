package com.example.pets.petimgadpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pets.R

class petAdapter(private val pets: MutableList<String>): RecyclerView.Adapter<petAdapter.ViewHolder>() {

        class ViewHolder(itemview: View):RecyclerView.ViewHolder(itemview) {
            val petimage: ImageView = itemView.findViewById(R.id.petimage)
            fun bind(url: String) {
                Glide.with(itemView)
                    .load(url)
                    .into(petimage)
            }
        }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         val view = LayoutInflater.from(parent.context).inflate(R.layout.pet_item, parent, false)
         return ViewHolder(view)
     }

     override fun getItemCount(): Int {
         return pets.size
     }

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         val pet = pets[position]
         holder.bind(pet)
     }
 }


