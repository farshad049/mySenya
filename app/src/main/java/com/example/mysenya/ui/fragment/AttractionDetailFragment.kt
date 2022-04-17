package com.example.mysenya.ui.fragment

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import com.example.mysenya.R
import com.example.mysenya.databinding.FragmentAttractionDetailBinding
import com.squareup.picasso.Picasso


class AttractionDetailFragment:BaseFragment() {

    private var _binding: FragmentAttractionDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enable menu
        setHasOptionsMenu(true)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAttractionDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityViewModel.selectedAttractionLiveData.observe(viewLifecycleOwner){attraction->
            Picasso.get().load(attraction.image_url).into(binding.ivDetailImage)
            binding.tvDetailTitle.text=attraction.title
            binding.tvDescription.text=attraction.description
            binding.tvDetailTimeToVisit.text=attraction.months_to_visit
            binding.tvFacts.text="${attraction.facts.size} facts"
            //take each fact as a single string to show
            val stringBuilder=StringBuilder("")
            attraction.facts.forEach {
                stringBuilder.append("\u2022  $it")
                stringBuilder.append("\n\n")
            }
            //delete the last \n to prevent the last new line
            val factsToShow=stringBuilder.toString().substring(0,stringBuilder.toString().lastIndexOf("\n\n"))

            binding.tvFacts.setOnClickListener {
                AlertDialog.Builder(requireContext())
                    .setTitle("${attraction.title} facts")
                    .setMessage(factsToShow)
                    .setPositiveButton("ok") {dialog,which -> TODO()}
                    .setCancelable(false)
                    .show()
            }
        }
    }

    //set menu layout
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_attraction_detail,menu)
    }
    //set on click listener for each menu item
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.menuItemLocation ->{
                val attraction=activityViewModel.selectedAttractionLiveData.value ?:return  true
                //send the attraction id to locationLiveData in order to be able implement it in MainActivity
                activityViewModel.locationSelectedLIveData.postValue(attraction)
                true//this is just for  onOptionsItemSelected requirement
            }
            else -> super.onOptionsItemSelected(item)

        }

    }












    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}