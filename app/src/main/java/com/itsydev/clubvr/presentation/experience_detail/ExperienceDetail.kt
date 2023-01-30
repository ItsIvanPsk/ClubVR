package com.itsydev.clubvr.presentation.experience_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.itsydev.clubvr.databinding.FragmentExperienceDetailBinding
import com.itsydev.clubvr.databinding.FragmentMainMenuBinding
import com.itsydev.clubvr.presentation.experiences.ExperiencesViewModel

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

    private fun setupObservers(){

    }

}
