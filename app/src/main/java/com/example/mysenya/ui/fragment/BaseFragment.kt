package com.example.mysenya.ui.fragment

import androidx.fragment.app.Fragment
import com.dmp.senya.data.Attraction
import com.example.mysenya.arch.AttractionsViewModel
import com.example.mysenya.ui.MainActivity

abstract class BaseFragment:Fragment() {
    val mainActivity by lazy { activity as MainActivity }

    protected val navController by lazy {
        (activity as MainActivity).navController
    }


    //take initialized  viewModel in order to use it anywhere
    protected val activityViewModel:AttractionsViewModel
    get() = (activity as MainActivity).viewModel
}