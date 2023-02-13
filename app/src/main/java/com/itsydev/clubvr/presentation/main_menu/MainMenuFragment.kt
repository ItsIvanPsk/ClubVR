package com.itsydev.clubvr.presentation.main_menu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.itsydev.clubvr.databinding.FragmentMainMenuBinding
import com.itsydev.clubvr.utils.BearEncrypt
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMenuFragment : Fragment(), MainMenuItemListener{

    private lateinit var binding: FragmentMainMenuBinding
    private val viewmodel: MainMenuViewModel by viewModels()
    private val bear: BearEncrypt = BearEncrypt()

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
        viewmodel.updateMainMenuItems()
        return binding.root
    }

    private fun setupListeners() = with(binding){

    }

    private fun setupObservers(){
        viewmodel.getMainMenuItems().observe(viewLifecycleOwner){
            Log.d("5cos", it.toString())
        }
    }

    override fun newPressed(view: View, itemId: Int) {
        Log.d("5cos", itemId.toString())
    }

}
