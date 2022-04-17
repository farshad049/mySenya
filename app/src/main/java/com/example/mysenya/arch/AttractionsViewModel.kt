package com.example.mysenya.arch

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmp.senya.data.Attraction
import java.lang.StringBuilder

class AttractionsViewModel:ViewModel() {
    private val repository= AttractionsRepository()

    // for he fragment
    val attractionListLiveData=MutableLiveData<List<Attraction>>()

    // for AttractionDetailFragment
    val selectedAttractionLiveData=MutableLiveData<Attraction>()

    //for MainActivity
    val locationSelectedLIveData=MutableLiveData<Attraction>()

    fun init(context:Context){
        val attractionLIst=repository.parseAttractions(context)
        //set data in mutable list in main thread
        attractionListLiveData.postValue(attractionLIst)
        //set data in mutable list in which thread this function in running on it
        //attractionListLiveData.value=attractionLIst
    }

    fun onAttractionSelected(attractionId :String){
        val attraction=attractionListLiveData.value?.find {
            it.id==attractionId
        } ?: return
        selectedAttractionLiveData.postValue(attraction)
    }

}