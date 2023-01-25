package com.itsydev.clubvr.presentation.experiences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.itsydev.clubvr.databinding.FragmentAccesibilityBinding
import com.itsydev.clubvr.databinding.FragmentExperiencesBinding
import com.itsydev.clubvr.databinding.FragmentMainMenuBinding

class ExperiencesFragment : Fragment() {

    private lateinit var binding: FragmentExperiencesBinding
    private val viewmodel: ExperiencesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentExperiencesBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupListeners()
        setupObservers()
        viewmodel.updateExperiencies()
        return binding.root
    }

    private fun setupListeners() = with(binding){

    }

    private fun setupObservers() = with(viewmodel){
        getExperiencies().observe(viewLifecycleOwner){
            print(it)
        }
    }
}
