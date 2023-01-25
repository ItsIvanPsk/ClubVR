package com.itsydev.clubvr.presentation.experiences

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.itsydev.clubvr.databinding.FragmentAccesibilityBinding
import com.itsydev.clubvr.databinding.FragmentExperiencesBinding
import com.itsydev.clubvr.databinding.FragmentMainMenuBinding

class ExperiencesFragment : Fragment() {

    private lateinit var binding: FragmentExperiencesBinding
    private val viewmodel: ExperiencesViewModel by viewModels()
    private lateinit var adapter: ExperiencesAdapter


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
        setupExperienceAdapter()
        viewmodel.updateExperiencies()
        return binding.root
    }

    private fun setupExperienceAdapter() {
        adapter = ExperiencesAdapter()
        val recyclerView: RecyclerView = binding.experiencesRecycler
        recyclerView.adapter = adapter
    }

    private fun setupListeners() = with(binding){

    }

    private fun setupObservers() = with(viewmodel){
        getExperiencies().observe(viewLifecycleOwner){
            Log.d("5cosdos", it.toString())
            adapter.submitList(it)
        }
    }

}
