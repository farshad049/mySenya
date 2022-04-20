package com.example.mysenya.ui.fragment.details

import com.airbnb.epoxy.EpoxyController
import com.dmp.senya.data.Attraction
import com.example.mysenya.R
import com.example.mysenya.databinding.ModelHeaderImageBinding
import com.example.mysenya.ui.epoxy.EmptyEpoxyModel
import com.example.mysenya.ui.epoxy.LoadingEpoxyModel
import com.example.mysenya.ui.epoxy.ViewBindingKotlinModel
import com.example.mysenya.ui.fragment.home.HomeFragmentController
import com.squareup.picasso.Picasso

class HeaderController(
     //val imageUrls:List<String>
    val imageUrls:Attraction
): EpoxyController() {



    override fun buildModels() {

        imageUrls.image_urls.forEachIndexed { index, url ->
            ImageEpoxyModel(url)
                .id(index)
                .addTo(this)
        }

//        imageUrls.forEachIndexed { index, url ->
//            ImageEpoxyModel(url)
//                .id(index)
//                .addTo(this)
//        }

    }



    data class ImageEpoxyModel(val imageUrl:String)
        :ViewBindingKotlinModel<ModelHeaderImageBinding>(R.layout.model_header_image){
        override fun ModelHeaderImageBinding.bind(){
            Picasso.get().load(imageUrl).into(imageView)
        }
    }




}