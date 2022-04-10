package com.example.mysenya.ui.fragment.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mysenya.R
import com.example.mysenya.data.Attraction
import com.example.mysenya.databinding.ViewHolderAttractionBinding
import com.squareup.picasso.Picasso


class HomeFragmentAdapter(
    private val onClickedCallback:() -> Unit
):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val attractions=ArrayList<Attraction>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AttractionViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AttractionViewHolder).onBind(attractions[position],onClickedCallback)
    }

    override fun getItemCount(): Int {
        return attractions.size
    }

    fun setData(attractions:List<Attraction>){
        this.attractions.clear()
        this.attractions.addAll(attractions)
        notifyDataSetChanged()

    }

    inner class AttractionViewHolder(parent:ViewGroup):RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.view_holder_attraction,parent,false)
    ){
        private val binding=ViewHolderAttractionBinding.bind(itemView)

        fun onBind(attraction:Attraction,onClicked:() -> Unit){
            binding.tvTitle.text=attraction.title
            //Glide.with(context).load(attraction.image_urls).placeholder(R.mipmap.ic_launcher).into(binding.ivImage)
            Picasso.get().load(attraction.image_url).into(binding.ivImage)
            binding.tvMonthTovisit.text=attraction.months_to_visit
            binding.root.setOnClickListener {
                onClicked()
            }
        }

    }
}