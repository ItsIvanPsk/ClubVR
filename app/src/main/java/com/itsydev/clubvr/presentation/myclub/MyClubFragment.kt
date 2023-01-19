package com.itsydev.clubvr.presentation.myclub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itsydev.clubvr.databinding.FragmentMyclubBinding

class MyClubFragment : Fragment() {

    private lateinit var binding: FragmentMyclubBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMyclubBinding.inflate(layoutInflater)
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
