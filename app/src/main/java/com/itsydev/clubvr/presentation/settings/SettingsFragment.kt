package com.itsydev.clubvr.presentation.settings

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import com.itsydev.clubvr.databinding.FragmentSettingsBinding
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.itsydev.clubvr.ExperiencesActivity
import com.itsydev.clubvr.MainActivity
import com.itsydev.clubvr.R
import com.itsydev.clubvr.databinding.FragmentMainMenuBinding
import com.itsydev.clubvr.presentation.login.LoginViewModel
import com.itsydev.clubvr.utils.ApplicationConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private val viewmodel: SettingsViewModel by viewModels()

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
        (requireActivity() as MainActivity).getActivityBinding().mainFloatingButton.visibility = View.VISIBLE
        (requireActivity() as MainActivity).getActivityBinding().bottomAppBar.visibility = View.VISIBLE
        return binding.root
    }

    private fun setupListeners() = with(binding){
        settingsAppAccesibility.setOnClickListener {
            it.findNavController().navigate(R.id.action_settingsFragment_to_accesibilityFragment)
        }
        settingsItemsDiscord.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(ApplicationConstants.discordLink))
            startActivity(intent)
        }
        settingsItemsEdit.setOnClickListener {
            showCustomDialog()
        }
    }

    private fun setupObservers(){

    }

    private fun showCustomDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.change_password_dialog)

        dialog.show()

        val oldPassword: EditText = dialog.findViewById(R.id.change_password_old)
        val newPassword: EditText = dialog.findViewById(R.id.change_password_new)
        val reNewPassword: EditText = dialog.findViewById(R.id.change_password_confirm)
        val confirm: Button = dialog.findViewById(R.id.change_password_save)

        confirm.setOnClickListener {
            Log.d("5cos", "Pressed!")
            var result = viewmodel.changePassword(oldPassword.text.toString(), newPassword.text.toString(), reNewPassword.text.toString())
            Log.d("5cos", result.toString())
            when (result) {
                0 -> {
                    Log.d("5cos", "Salido 0")
                    oldPassword.setText("")
                    newPassword.setText("")
                    reNewPassword.setText("")
                    Toast.makeText(requireContext(), "The fields are empty", Toast.LENGTH_LONG).show()
                }
                1 -> {
                    Log.d("5cos", "Salido 1")
                    oldPassword.setText("")
                    newPassword.setText("")
                    reNewPassword.setText("")
                    Toast.makeText(requireContext(), "The old password and the new password are the same", Toast.LENGTH_LONG).show()
                }
                2 -> {
                    Log.d("5cos", "Salido 2")
                    oldPassword.setText("")
                    newPassword.setText("")
                    reNewPassword.setText("")
                    Toast.makeText(requireContext(), "The password is not a valid password", Toast.LENGTH_LONG).show()
                }
                5 -> {
                    Log.d("5cos", "Salido 5")
                    Toast.makeText(requireContext(), "You have been changed the password!", Toast.LENGTH_LONG).show()
                    dialog.dismiss()
                }
                else -> {
                    Log.d("5cos", "Salido else")
                    oldPassword.setText("")
                    newPassword.setText("")
                    reNewPassword.setText("")
                    dialog.dismiss()
                    Toast.makeText(requireContext(), "You didnt change the password", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}
