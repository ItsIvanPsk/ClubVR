package com.itsydev.clubvr.presentation.experiences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.itsydev.clubvr.R
import com.itsydev.clubvr.databinding.FragmentExperiencesBinding
import com.itsydev.clubvr.presentation.main_menu.ExperienceListeners
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExperiencesFragment : Fragment(), ExperienceListeners {

    private lateinit var binding: FragmentExperiencesBinding
    private val viewmodel: ExperiencesViewModel by activityViewModels()
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
        viewmodel.updateExperiences(requireContext(), "json/experiences.json")
        return binding.root
    }

    private fun setupExperienceAdapter() {
        adapter = ExperiencesAdapter(requireContext(), this)
        val recyclerView: RecyclerView = binding.experiencesRecycler
        recyclerView.adapter = adapter
    }

    private fun setupListeners() = with(binding){

    }

    private fun setupObservers() = with(viewmodel){
        getExperiencies().observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    override fun experienceClicked(view: View, position: Int){
        view.findNavController().navigate(R.id.action_experiencesFragment_to_experienceDetail)
        viewmodel.setExperienceDetail(position-1)
    }

}
