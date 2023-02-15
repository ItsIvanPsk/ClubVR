package com.itsydev.clubvr.presentation.settings

import com.itsydev.clubvr.databinding.FragmentSettingsBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itsydev.clubvr.databinding.FragmentMainMenuBinding
import com.itsydev.clubvr.utils.ApplicationConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSettingsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupListeners()
        setupObservers()
        binding.settingsAppVersion.text = ApplicationConstants.version
        return binding.root
    }

    private fun setupListeners() = with(binding){

    }

    private fun setupObservers(){

    }
}
