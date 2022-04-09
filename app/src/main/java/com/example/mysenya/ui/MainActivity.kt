package com.example.mysenya.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.mysenya.R
import com.example.mysenya.data.Attraction
import com.example.mysenya.data.AttractionsResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MainActivity : AppCompatActivity() {
    lateinit var navController:NavController
    val attractionsList:List<Attraction> by lazy {
        parseAttractions()

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
         navController = navHostFragment.navController




    }//FUN

    private fun parseAttractions():List<Attraction>{
        val textFormatFile=resources.openRawResource(R.raw.croatia).bufferedReader().use { it.readText() }
        val moshi= Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val adapter:JsonAdapter<AttractionsResponse> = moshi.adapter(AttractionsResponse::class.java)
        return adapter.fromJson(textFormatFile)!!.attractions

}





}