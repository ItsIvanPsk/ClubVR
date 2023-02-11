package com.itsydev.clubvr.presentation.myclub

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.itsydev.clubvr.databinding.FragmentMyclubBinding
import com.itsydev.clubvr.presentation.login.LoginViewModel
import com.itsydev.clubvr.utils.BearEncrypt
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyClubFragment : Fragment() {

    private lateinit var binding: FragmentMyclubBinding
    private val viewmodel: MyClubViewModel by activityViewModels()
    private val bear: BearEncrypt = BearEncrypt()


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
        viewmodel.updateRoomUsername()
        return binding.root
    }

    private fun setupListeners() = with(binding){

    }

    private fun setupObservers() = with(viewmodel){
        getRoomUsername().observe(viewLifecycleOwner){
            updateUserPoints(it)
        }
        getActiveUser().observe(viewLifecycleOwner){
            binding.myclubPointsUserValue.text = bear.decrypt(it.userPoints)
            binding.myclubPointsBarValue.progress = bear.decrypt(it.userPoints).toInt()
            Log.d("5cos", "MyClub points updated")
        }
    }

}
