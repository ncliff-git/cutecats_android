package com.github.ncliff.cutecats.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.ncliff.cutecats.R
import com.github.ncliff.cutecats.data.network.CatBreeds

class CatBreedsRVAdapter: RecyclerView.Adapter<CatBreedsRVAdapter.CatBreedsHolder>() {
    private var _catBreedsList = ArrayList<CatBreeds>()

    class CatBreedsHolder(view: View): RecyclerView.ViewHolder(view) {
        private val ivCatBreeds: ImageView = view.findViewById(R.id.item_cat_image)
        private val tvCatBreeds: TextView = view.findViewById(R.id.item_cat_text)

        fun bind(catBreeds: CatBreeds) {
            tvCatBreeds.text = catBreeds.name
            ivCatBreeds.load(catBreeds.image?.url)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCatBreedsList(catBreedsList: List<CatBreeds>) {
        _catBreedsList = catBreedsList as ArrayList<CatBreeds>
        notifyDataSetChanged()
    }

    private fun getItemByPosition(position: Int) = _catBreedsList[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatBreedsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cat, parent, false)
        return CatBreedsHolder(view)
    }

    override fun onBindViewHolder(holder: CatBreedsHolder, position: Int) {
        holder.bind(getItemByPosition(position))
    }

    override fun getItemCount(): Int = _catBreedsList.size
}