package com.example.mysenya.arch

import android.content.Context
import com.dmp.senya.data.Attraction
import com.dmp.senya.data.AttractionsResponse
import com.example.mysenya.R
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class AttractionsRepository {

    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    fun parseAttractions(context:Context): ArrayList<Attraction> {
        //pull  data from raw/croatia.json
        val textFromFile = context.resources.openRawResource(R.raw.croatia).bufferedReader().use { it.readText() }
        val adapter: JsonAdapter<AttractionsResponse> = moshi.adapter(AttractionsResponse::class.java)

        return adapter.fromJson(textFromFile)!!.attractions as ArrayList<Attraction>
    }
}