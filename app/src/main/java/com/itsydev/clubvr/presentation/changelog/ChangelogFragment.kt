package com.itsydev.clubvr.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.itsydev.clubvr.MainActivity
import com.itsydev.clubvr.R
import com.itsydev.clubvr.databinding.FragmentChangelogBinding
import com.itsydev.clubvr.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangelogFragment : Fragment() {

    private lateinit var binding: FragmentChangelogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentChangelogBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupListeners()
        (requireActivity() as MainActivity).getActivityBinding().mainFloatingButton.visibility = View.GONE
        (requireActivity() as MainActivity).getActivityBinding().bottomAppBar.visibility = View.GONE

        return binding.root
    }

    private fun setupListeners() = with(binding){
        changelogGoBack.setOnClickListener {
            it.findNavController().navigate(R.id.action_changelog_to_settingsFragment)
        }
    }

}
