package com.example.mysenya.ui.fragment.details

import com.airbnb.epoxy.EpoxyController
import com.dmp.senya.data.Attraction
import com.example.mysenya.R
import com.example.mysenya.databinding.*
import com.example.mysenya.ui.epoxy.ViewBindingKotlinModel


class ContentEpoxyController(
    val attraction:Attraction
):EpoxyController() {

    var isGridMode: Boolean=false
    lateinit var onChangeLayoutCallBack: () -> Unit

    override fun buildModels() {

        Description(attraction.description).id("description").addTo(this)

        HeaderFact("${attraction.facts.size} facts",isGridMode,onChangeLayoutCallBack).id("fact_Header").addTo(this)

        attraction.facts.forEachIndexed { index, fact ->
            Fact(fact).id(index).addTo(this)
        }

        MonthToVisit(attraction.months_to_visit).id("Month_to_visit").addTo(this)


    }//data classes

    data class Description(val description:String)
        : ViewBindingKotlinModel<ModelDescriptionBinding>(R.layout.model_description){
        override fun ModelDescriptionBinding.bind(){
            tv.text=description
        }
        override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
            //return full size of grid (in this option "2")
            return totalSpanCount
        }
    }

    data class MonthToVisit(val monthToVisit:String)
        :ViewBindingKotlinModel<ModelMonthToVisitBinding>(R.layout.model_month_to_visit){
            override fun ModelMonthToVisitBinding.bind(){
                tv.text=monthToVisit
            }
        override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
            //return full size of grid (in this option "2")
            return totalSpanCount
         }
        }

    data class HeaderFact(val factHeader:String,val isGridMode:Boolean,val onChangeLayoutCallBAck:() ->Unit
    )
        :ViewBindingKotlinModel<ModelFactsHeaderBinding>(R.layout.model_facts_header){
        override fun ModelFactsHeaderBinding.bind() {
            tvFacts.text=factHeader
            ivToggle.setOnClickListener {
                    onChangeLayoutCallBAck.invoke()
                }
            ivToggle.setImageResource(if(isGridMode) R.drawable.ic_round_grid_on_24 else R.drawable.ic_round_view_list_24)
            }
        override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
            //return full size of grid (in this option "2")
            return totalSpanCount
             }
        }

    data class Fact(val fact:String)
        :ViewBindingKotlinModel<ModelFactBinding>(R.layout.model_fact){
        override fun ModelFactBinding.bind() {
            tv.text=fact
            }
        }
}