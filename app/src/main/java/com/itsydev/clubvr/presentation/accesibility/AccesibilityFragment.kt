package com.itsydev.clubvr.presentation.accesibility

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.itsydev.clubvr.MainActivity
import com.itsydev.clubvr.R
import com.itsydev.clubvr.databinding.FragmentAccesibilityBinding
import com.itsydev.clubvr.databinding.FragmentMainMenuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccesibilityFragment : Fragment() {

    private lateinit var binding: FragmentAccesibilityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentAccesibilityBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupListeners()
        setupObservers()
        (requireActivity() as MainActivity).getActivityBinding().mainFloatingButton.visibility = View.GONE
        (requireActivity() as MainActivity).getActivityBinding().bottomAppBar.visibility = View.GONE
        return binding.root
    }

    private fun setupListeners() = with(binding){
        accesibilityFabGoBack.setOnClickListener {
            it.findNavController().navigate(R.id.action_accesibilityFragment_to_settingsFragment)
        }
    }

    private fun setupObservers(){

    }
}
