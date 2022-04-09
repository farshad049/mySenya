package com.example.mysenya.ui.fragment

import androidx.fragment.app.Fragment
import com.example.mysenya.ui.MainActivity

abstract class BaseFragment:Fragment() {
    val mainActivity by lazy { activity as MainActivity }

     protected val navController by lazy{
         (activity as MainActivity).navController
     }
}