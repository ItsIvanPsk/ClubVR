package com.itsydev.clubvr.presentation.experience_detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.room.util.query
import coil.load
import coil.transform.CircleCropTransformation
import com.itsydev.clubvr.databinding.FragmentExperienceDetailBinding
import com.itsydev.clubvr.databinding.FragmentMainMenuBinding
import com.itsydev.clubvr.presentation.experiences.ExperiencesViewModel
import dagger.hilt.android.AndroidEntryPoint

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
            binding.experienceDetailHeaderImage.load(it.img[1].url){
                crossfade(true)
            }
            binding.experienceDetailDescriptionValue.text = it.description[0]
        }
    }

}
