package com.itsydev.clubvr.presentation.myclub

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.itsydev.clubvr.ExperiencesActivity
import com.itsydev.clubvr.MainActivity
import com.itsydev.clubvr.data.models.bundles.BundlesBo
import com.itsydev.clubvr.databinding.FragmentMyclubBinding
import com.itsydev.clubvr.presentation.experiences.ExperiencesAdapter
import com.itsydev.clubvr.presentation.login.LoginViewModel
import com.itsydev.clubvr.utils.BearEncrypt
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyClubFragment : Fragment(), BundleListeners {

    private lateinit var binding: FragmentMyclubBinding
    private val viewmodel: MyClubViewModel by activityViewModels()
    private val bear: BearEncrypt = BearEncrypt()
    lateinit var adapter: BundlesAdapter


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
        setupAdapter()
        viewmodel.updateRoomUsername()
        (requireActivity() as MainActivity).getActivityBinding().mainFloatingButton.visibility = View.VISIBLE
        (requireActivity() as MainActivity).getActivityBinding().bottomAppBar.visibility = View.VISIBLE
        return binding.root
    }

    private fun setupAdapter() {
        adapter = BundlesAdapter(requireContext(), this)
        val recyclerView: RecyclerView = binding.myclubBundles
        recyclerView.adapter = adapter
    }

    private fun setupListeners() = with(binding){

    }

    private fun setupObservers() = with(viewmodel){
        getRoomUsername().observe(viewLifecycleOwner){
            updateUserPoints(it)
        }
        getActiveUser().observe(viewLifecycleOwner){
            binding.myclubPointsBarValue.progress = bear.decrypt(it.userPoints).toInt()
            binding.myclubPointsUserValue.text = bear.decrypt(it.userPoints)
            Log.d("5cos", "MyClub points updated")
            viewmodel.updateBundles(it.userLevel)
        }
        viewmodel.getAvaiableBundles().observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

    }

    override fun bundleClicked(view: View, name: String) {
        Log.d("5cos", name)
    }


}
