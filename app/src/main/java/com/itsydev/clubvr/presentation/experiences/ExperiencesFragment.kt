package com.itsydev.clubvr.presentation.experiences

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.itsydev.clubvr.R
import com.itsydev.clubvr.databinding.FragmentExperiencesBinding
import com.itsydev.clubvr.ExperiencesActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExperiencesFragment : Fragment(), ExperienceListeners {

    lateinit var binding: FragmentExperiencesBinding
    val viewmodel: ExperiencesViewModel by activityViewModels()
    lateinit var adapter: ExperiencesAdapter

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
        (requireActivity() as ExperiencesActivity).getActivityBinding().experiencesFloatingButton.visibility = View.GONE
        (requireActivity() as ExperiencesActivity).getActivityBinding().bottomAppBar.visibility = View.GONE
        return binding.root
    }

    private fun setupExperienceAdapter() {
        adapter = ExperiencesAdapter(requireContext(), this)
        val recyclerView: RecyclerView = binding.experiencesRecycler
        recyclerView.adapter = adapter
    }

    private fun setupListeners() = with(binding){
        experienceSearchValue.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) { }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
                viewmodel.filterByName(s, requireContext())
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) { }
        })
    }

    private fun setupObservers() = with(viewmodel){
        getExperiencies().observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    override fun experienceClicked(view: View, name: String){
        view.findNavController().navigate(R.id.action_experiencesFragment_to_experienceDetail)
        viewmodel.setExperienceDetail(name)
    }

}
