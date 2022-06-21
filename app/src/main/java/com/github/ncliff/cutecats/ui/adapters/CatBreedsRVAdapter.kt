package com.github.ncliff.cutecats.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.ncliff.cutecats.R
import com.github.ncliff.cutecats.data.network.CatBreeds

class CatBreedsRVAdapter(private val onClick: (name: String) -> Unit): RecyclerView.Adapter<CatBreedsRVAdapter.CatBreedsHolder>() {
    private var _catBreedsList = ArrayList<CatBreeds>()

    class CatBreedsHolder(private val onClick: (name: String) -> Unit, view: View): RecyclerView.ViewHolder(view) {
        private val pbCatBreeds: ProgressBar = view.findViewById(R.id.item_cat_progress_bar)
        private val ivCatBreeds: ImageView = view.findViewById(R.id.item_cat_image)
        private val tvCatBreeds: TextView = view.findViewById(R.id.item_cat_text)
        private val cardCatBreeds: CardView = view.findViewById(R.id.item_cat_card)

        fun bind(catBreeds: CatBreeds) {
            tvCatBreeds.text = catBreeds.name
            ivCatBreeds.load(catBreeds.image?.url) {
                listener(onSuccess = { _, _ ->
                    pbCatBreeds.isVisible = false
                })
            }
            cardCatBreeds.setOnClickListener {
                catBreeds.name?.apply(onClick)
            }
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
        return CatBreedsHolder(onClick, view)
    }

    override fun onBindViewHolder(holder: CatBreedsHolder, position: Int) {
        holder.bind(getItemByPosition(position))
    }

    override fun getItemCount(): Int = _catBreedsList.size
}