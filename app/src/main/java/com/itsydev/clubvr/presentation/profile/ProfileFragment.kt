package com.itsydev.clubvr.presentation.profile

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.itsydev.clubvr.utils.BearEncrypt
import com.itsydev.clubvr.LoginActivity
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
        viewmodel.updateUsers()
        setupListeners()
        setupObservers()
        return binding.root
    }

    private fun setupListeners() = with(binding){
        profileLogout.setOnClickListener {
            showLogoutDialog().show()
        }
    }

    private fun setupObservers() = with(viewmodel){
        getUsers().observe(viewLifecycleOwner){
            if(it.isNotEmpty()){
                binding.profileIdValue.text = "VR_" + bear.decrypt(it[0].id)
                binding.profileUsernameValue.text = bear.decrypt(it[0].username)
                binding.profileSurnnameValue.text = bear.decrypt(it[0].surname)
                binding.profileNameValue.text = bear.decrypt(it[0].name)
                binding.profileContactTelfValue.text = bear.decrypt(it[0].telf.toString())
                binding.profileMailValue.text = bear.decrypt(it[0].mail)
            }
        }
    }

    private fun showLogoutDialog() : AlertDialog{
        return activity.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                builder.setTitle(R.string.dialog_logout_title)
                builder.setMessage(R.string.dialog_logout_message)
                setPositiveButton(
                    R.string.logout
                ) { _, _ ->
                    viewmodel.logout()
                    startActivity(Intent(requireContext(), LoginActivity::class.java))
                }
                setNegativeButton(R.string.cancel
                ) { dialog, _ ->
                    dialog.dismiss()
                }
            }

            builder.create()
        }
    }

}
