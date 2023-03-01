package com.itsydev.clubvr.presentation.profile

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.itsydev.clubvr.utils.BearEncrypt
import com.itsydev.clubvr.MainActivity
import com.itsydev.clubvr.R
import com.itsydev.clubvr.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewmodel: ProfileViewModel by viewModels()
    private val bear: BearEncrypt = BearEncrypt()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentProfileBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //setupListeners()
        //setupObservers()
        //viewmodel.updateUsers()
        (requireActivity() as MainActivity).getActivityBinding().mainFloatingButton.visibility = View.VISIBLE
        (requireActivity() as MainActivity).getActivityBinding().bottomAppBar.visibility = View.VISIBLE
        showDialog("VR IETI App", "This functionality is not available in this version of the app.", "Okay", "",).show()
        return binding.root
    }

    private fun setupListeners() = with(binding){ }

    private fun setupObservers() = with(viewmodel){
        getUsers().observe(viewLifecycleOwner){
            Log.d("5cos", it.toString())
            if(it.isNotEmpty()){
                binding.profileIdValue.text = bear.decrypt(it[0].id)
                binding.profileUsernameValue.text = bear.decrypt(it[0].username)
                binding.profileSurnnameValue.text = bear.decrypt(it[0].surname)
                binding.profileNameValue.text = bear.decrypt(it[0].name)
                binding.profileContactTelfValue.text = bear.decrypt(it[0].telf)
                binding.profileMailValue.text = bear.decrypt(it[0].mail)
            }
        }
    }

    private fun showDialog(title: String, message: String, firstOpt: String, secondOpt: String) : AlertDialog {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(firstOpt) { dialog, which ->
            dialog.dismiss()
            view?.findNavController()?.navigate(R.id.action_profileFragment_to_mainMenu)
        }
        builder.setNegativeButton(secondOpt) { dialog, which ->

        }
        return builder.create()
    }

}
