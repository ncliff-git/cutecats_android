package com.github.ncliff.cutecats.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.ncliff.cutecats.R
import com.github.ncliff.cutecats.data.model.CatFav

class CatFavRVAdapter: RecyclerView.Adapter<CatFavRVAdapter.CatFavHolder>() {
    private var _catFavList = ArrayList<CatFav>()

    class CatFavHolder(view: View): RecyclerView.ViewHolder(view) {
        private val ivCatFav: ImageView = view.findViewById(R.id.iv_fav_cat)

        fun bind(catFav: CatFav) {
            ivCatFav.load(catFav.image?.url)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCatFavList(list: List<CatFav>) {
        _catFavList = list as ArrayList<CatFav>
        notifyDataSetChanged()
    }

    fun getItemByPosition(position: Int) = _catFavList[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatFavHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fav_cat, parent, false)
        return CatFavHolder(view)
    }

    override fun onBindViewHolder(holder: CatFavHolder, position: Int) {
        holder.bind(getItemByPosition(position))
    }

    override fun getItemCount() = _catFavList.size
}