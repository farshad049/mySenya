package com.example.mysenya.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.dmp.senya.data.Attraction
import com.example.mysenya.databinding.FragmentAttractionDetailBinding
import com.squareup.picasso.Picasso


class AttractionDetailFragment:BaseFragment() {

    private var _binding: FragmentAttractionDetailBinding? = null
    private val binding get() = _binding!!

    private val safeArgs:AttractionDetailFragmentArgs by navArgs()
    private val attraction:Attraction by lazy {
        attractions.find { it.id == safeArgs.attractionId}!!
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAttractionDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Picasso.get().load(attraction.image_url).into(binding.ivDetailImage)
        binding.tvDetailTitle.text=attraction.title
        binding.tvDescription.text=attraction.description
        binding.tvDetailTimeToVisit.text=attraction.months_to_visit
        binding.tvFacts.text="${attraction.facts.size} facts"
        binding.tvFacts.setOnClickListener {

        }

    }












    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}