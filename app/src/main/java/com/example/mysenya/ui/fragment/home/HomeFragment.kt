package com.example.mysenya.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.mysenya.R
import com.example.mysenya.databinding.FragmentHomeBinding
import com.example.mysenya.ui.fragment.BaseFragment

class HomeFragment: BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //when u click on an item it will pass the string to adapter and send you to attractionDetailFragment
        //"val epoxyController" is the prepared Epoxy controller with given data , so we can use it another where
        val epoxyController = HomeFragmentController() { attractionId ->
            //get id from liveData
            activityViewModel.onAttractionSelected(attractionId)
            //move to attraction activity
            navController.navigate(R.id.action_homeFragment_to_attractionDetailFragment)
        }

        binding.epoxyRecyclerView.addItemDecoration(DividerItemDecoration(requireActivity(), RecyclerView.VERTICAL))
        epoxyController.isLoading=true

        binding.epoxyRecyclerView.setController(epoxyController)
        //observe changes from list data
        activityViewModel.attractionListLiveData.observe(viewLifecycleOwner){attractions ->
            epoxyController.attractions=attractions
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}