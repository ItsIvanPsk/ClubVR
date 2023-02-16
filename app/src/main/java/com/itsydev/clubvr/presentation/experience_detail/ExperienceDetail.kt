package com.itsydev.clubvr.presentation.experience_detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.marginLeft
import androidx.core.view.marginStart
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import coil.transform.CircleCropTransformation
import com.itsydev.clubvr.R
import com.itsydev.clubvr.data.models.experiences.ExperienceConstants
import com.itsydev.clubvr.databinding.FragmentExperienceDetailBinding
import com.itsydev.clubvr.presentation.experiences.ExperiencesViewModel
import com.itsydev.clubvr.utils.ApplicationConstants
import dagger.hilt.android.AndroidEntryPoint
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

@AndroidEntryPoint
class ExperienceDetail : Fragment(){

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
        return binding.root
    }

    private fun setupListeners() = with(binding){

    }

    private fun setupObservers() = with(viewmodel){
        getExperienceData().observe(viewLifecycleOwner){
            binding.experienceDetailHeaderGameName.text = it.name
            binding.experienceDetailHeaderGameIcon.load(it.img[0].url) {
                crossfade(true)
                transformations(
                    CircleCropTransformation()
                )
            }
            binding.experienceDetailDescriptionValue.text = it.description[0]

            for(item in it.categories.indices){
                val cardContainer = CardView(requireContext())
                cardContainer.elevation = resources.getDimension(R.dimen.card_corner_elevation)
                cardContainer.radius = resources.getDimension(R.dimen.card_corner_radius)
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
            Log.d("5cos", it.rating.toString())
            var ratingStar: ImageView
            for(item in 1..it.rating.toInt()){
                ratingStar = ImageView(requireContext())
                ratingStar.setImageDrawable(resources.getDrawable(R.drawable.ic_round_star_24))
                ratingStar.maxHeight = 75
                ratingStar.maxWidth = 75
                binding.experienceDetailRatingContainer.addView(ratingStar)
            }

            val photoList = mutableListOf<CarouselItem>()
            it.img.forEach { experiencePhoto ->
                photoList.add(
                    CarouselItem(
                        experiencePhoto.url,
                        ""
                    )
                )
            }

            binding.experienceDetailImagesCarousel.setData(photoList)
        }
    }

}
