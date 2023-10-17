package com.example.pets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.example.pets.databinding.ActivityMainBinding
import com.example.pets.petViewmodel.PetViewModel
import com.example.pets.petimgadpater.petAdapter

private lateinit var  viewmodel: PetViewModel
class MainActivity : AppCompatActivity() {

    private lateinit var adapter: petAdapter
    private lateinit var petimage: ImageView
    private lateinit var viewModel: PetViewModel
    private var peturls =  mutableListOf<String>()
    private lateinit var petrecyclerview: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        adapter = petAdapter(peturls)
//        binding.petRecyclerView?.adapter = adapter
//        binding.petRecyclerView?.layoutManager = LinearLayoutManager(this)

//        val showPetsButton: AppCompatButton = findViewById(R.id.randompetbutton)


        // Use findViewById here
        petrecyclerview= findViewById(R.id.petRecyclerView)

        adapter = petAdapter(peturls)
        petrecyclerview.adapter = adapter
        petrecyclerview.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this)[PetViewModel::class.java]





        viewModel.GetDogImageURl()
        viewModel.dogImageUrl.observe(this) { urls ->
            if (!urls.isNullOrEmpty()) {
                peturls.clear()
                peturls.addAll(urls)
                adapter.notifyDataSetChanged()
//                petrecyclerview.scrollToPosition(peturls.size - 1)
            }
        }





    }


    
}