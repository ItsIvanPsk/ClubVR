package com.itsydev.clubvr.presentation.experience_detail

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.itsydev.clubvr.ExperiencesActivity
import com.itsydev.clubvr.R
import com.itsydev.clubvr.data.models.experiences.ExperienceConstants
import com.itsydev.clubvr.databinding.FragmentExperienceDetailBinding
import com.itsydev.clubvr.presentation.experiences.ExperiencesViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


@AndroidEntryPoint
class ExperienceDetail : Fragment(), HeadsetsListeners{

    private lateinit var binding: FragmentExperienceDetailBinding
    private val viewmodel: ExperiencesViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentExperienceDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupListeners()
        setupObservers()
        (requireActivity() as ExperiencesActivity).getActivityBinding().experiencesFloatingButton.visibility = View.GONE
        (requireActivity() as ExperiencesActivity).getActivityBinding().bottomAppBar.visibility = View.GONE

        return binding.root
    }

    private fun setupListeners() = with(binding){
        experienceDetailGoBack.setOnClickListener {
            it.findNavController().navigate(R.id.action_experienceDetail_to_experiencesFragment)
        }
    }

    private fun setupObservers() = with(viewmodel){
        getExperienceData().observe(viewLifecycleOwner){ it ->
            var len = 0;
            binding.experienceDetailHeaderGameName.text = it.name
            binding.experienceDetailHeaderGameIcon.load(it.img[0].url) {
                crossfade(true)
                transformations(
                    CircleCropTransformation()
                )
            }
            binding.experienceDetailDescriptionValue.text = it.description[len]
            for(item in it.categories.indices){
                val cardContainer = CardView(requireContext())
                cardContainer.elevation = resources.getDimension(com.itsydev.clubvr.R.dimen.card_corner_elevation)
                cardContainer.radius = resources.getDimension(com.itsydev.clubvr.R.dimen.card_corner_radius)
                val categoryName = TextView(requireContext())
                categoryName.text = resources.getText(
                    ExperienceConstants.CATEGORY[it.categories[item].id]
                )
                categoryName.setPadding(30,20,30,20)

                cardContainer.addView(categoryName)
                binding.experienceDetailCategoriesContainer.addView(cardContainer)

                val layoutParams = cardContainer.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.setMargins(30, 20, 30, 20)
                cardContainer.layoutParams = layoutParams
            }

            binding.experienceDetailCategoryScroll.isHorizontalScrollBarEnabled = false;
            var ratingStar: ImageView
            for(item in 1..it.rating.toInt()){
                ratingStar = ImageView(requireContext())
                ratingStar.setImageDrawable(resources.getDrawable(R.drawable.ic_round_star_24))
                ratingStar.maxHeight = 75
                ratingStar.maxWidth = 75
                binding.experienceDetailRatingContainer.addView(ratingStar)
            }

            binding.experienceDetailHeadsetsScroll.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            val adapter = HeadSetsAdapter(requireContext(), this@ExperienceDetail)
            val recyclerView: RecyclerView = binding.experienceDetailHeadsetsScroll
            recyclerView.adapter = adapter

            val headsetsListInt = mutableListOf<Int>()
            it.headsets_compatible.forEach {
                headsetsListInt.add(it.id)
            }
            adapter.submitList(viewmodel.getAvaiableHeadsets(_context = requireContext(), headsetIds = headsetsListInt))

            val photoList = mutableListOf<CarouselItem>()
            for (i in 0..it.img.size - 1) {
                if (i != 0) {
                    photoList.add(
                        CarouselItem(
                            it.img[i].url,
                            ""
                        )
                    )
                }
            }

            binding.experienceDetailImagesCarousel.setData(photoList)
        }
    }

    override fun headsetsClicked(view: View, name: String) {
        Log.d("5cos", name)
        view.findNavController().navigate(R.id.action_experienceDetail_to_headsetFragment)
        viewmodel.setupHeadsetInfo(name)
    }

}
