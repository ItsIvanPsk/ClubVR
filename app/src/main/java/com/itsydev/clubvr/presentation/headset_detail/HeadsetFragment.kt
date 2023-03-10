package com.itsydev.clubvr.presentation.headset_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.itsydev.clubvr.ExperiencesActivity
import com.itsydev.clubvr.databinding.FragmentExperiencesBinding
import com.itsydev.clubvr.databinding.FragmentHeadsetDetailBinding
import com.itsydev.clubvr.presentation.experiences.ExperienceListeners
import com.itsydev.clubvr.presentation.experiences.ExperiencesAdapter
import com.itsydev.clubvr.presentation.experiences.ExperiencesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeadsetFragment : Fragment() {

    lateinit var binding: FragmentHeadsetDetailBinding
    val viewmodel: ExperiencesViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHeadsetDetailBinding.inflate(layoutInflater)
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

    private fun setupObservers() {

    }

    private fun setupListeners() {

    }

}