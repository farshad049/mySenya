package com.example.mysenya.ui.fragment.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyController
import com.bumptech.glide.Glide
import com.dmp.senya.data.Attraction

import com.example.mysenya.R
import com.example.mysenya.databinding.ViewHolderAttractionBinding
import com.example.mysenya.ui.epoxy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso


class HomeFragmentController(
    private val onClickedCallback: (String) -> Unit
): EpoxyController() {
    var isLoading:Boolean=false
    set(value) {
        field=value
        if (field){
            requestModelBuild()
        }
    }

    var attractions = ArrayList<Attraction>()
    set(value) {
        field=value
        isLoading=false
        requestModelBuild()
    }



    override fun buildModels() {
        if (isLoading){
            //show loading state
            return
        }
        if (attractions.isEmpty()){
            //show empty state
            return
        }
        attractions.forEach { attraction ->
            AttractionEpoxyModel(attraction, onClickedCallback )
                .id(attraction.id)
                .addTo(this)

        }
    }

    data class AttractionEpoxyModel(val attraction: Attraction,val onClicked: (String) -> Unit)
        :ViewBindingKotlinModel<ViewHolderAttractionBinding>(R.layout.view_holder_attraction){
        override fun ViewHolderAttractionBinding.bind() {
            tvTitle.text = attraction.title
            Picasso.get().load(attraction.image_url).into(ivImage)
            tvMonthTovisit.text = attraction.months_to_visit

            root.setOnClickListener {
                onClicked(attraction.id)
            }
        }

    }




}