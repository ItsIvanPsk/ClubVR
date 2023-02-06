package com.itsydev.clubvr.presentation.main_menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.itsydev.clubvr.BearEncrypt
import com.itsydev.clubvr.MainActivity
import com.itsydev.clubvr.databinding.FragmentMainMenuBinding
import com.itsydev.clubvr.presentation.experiences.ExperiencesViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.nio.charset.Charset

@AndroidEntryPoint
class MainMenuFragment : Fragment(){

    private lateinit var binding: FragmentMainMenuBinding
    private val viewmodel: MainMenuViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMainMenuBinding.inflate(layoutInflater)
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
