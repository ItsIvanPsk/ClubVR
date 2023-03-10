package com.itsydev.clubvr.presentation.settings

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.itsydev.clubvr.MainActivity
import com.itsydev.clubvr.R
import com.itsydev.clubvr.databinding.FragmentSettingsBinding
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
        setupLenguageAdapter()
        binding.settingsAppVersion.text = ApplicationConstants.version
        (requireActivity() as MainActivity).getActivityBinding().mainFloatingButton.visibility = View.VISIBLE
        (requireActivity() as MainActivity).getActivityBinding().bottomAppBar.visibility = View.VISIBLE
        viewmodel.isUserAdmin()
        return binding.root
    }

    private fun setupLenguageAdapter() {
        val lenNameList = mutableListOf<String>()
        ApplicationConstants.lenguajes.forEach { lenNameList.add(getString(it)) }
        val lenAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            lenNameList
        )
        lenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.settingsLenguageSelector.adapter = lenAdapter
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
            //showChangePasswordDialog()
            showDialog(
                "VR IETI App",
                "This functionality is not available in this version of the app.",
                "Okay",
                ""
            ).show()
        }
        settingsFabCreateUser.setOnClickListener {
            //showCreateUserDialog()
        }
        settingsItemsStar.setOnClickListener {
            showDialog(
                "VR IETI App",
                "This functionality is not available in this version of the app.",
                "Okay",
                ""
            ).show()
        }
        settingsChangelog.setOnClickListener {
            it.findNavController().navigate(R.id.action_settingsFragment_to_changelog)
        }
        settingsLenguageSelector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                Toast.makeText(requireContext(), "Seleccionaste: $selectedItem", Toast.LENGTH_SHORT).show()
                val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putInt("lenguage", position)
                editor.apply()
            }

            override fun onNothingSelected(parent: AdapterView<*>) { }
        }

    }

    private fun setupObservers() = with(viewmodel){
        getUserAdmin().observe(viewLifecycleOwner){
            binding.settingsFabCreateUser.isVisible = it
        }
        getUserCreationState().observe(viewLifecycleOwner){
            Toast.makeText(context, "The user has been created!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showChangePasswordDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.change_password_dialog)
        dialog.show()

        val oldPassword: EditText = dialog.findViewById(R.id.change_password_old)
        val newPassword: EditText = dialog.findViewById(R.id.change_password_new)
        val reNewPassword: EditText = dialog.findViewById(R.id.change_password_confirm)
        val confirm: Button = dialog.findViewById(R.id.change_password_save)

        confirm.setOnClickListener {
            val result = viewmodel.changePassword(oldPassword.text.toString(), newPassword.text.toString(), reNewPassword.text.toString())
            Log.d("5cos", result.toString())
            when (result) {
                0 -> {
                    oldPassword.setText("")
                    newPassword.setText("")
                    reNewPassword.setText("")
                    Toast.makeText(requireContext(), "The fields are empty", Toast.LENGTH_LONG).show()
                }
                1 -> {
                    oldPassword.setText("")
                    newPassword.setText("")
                    reNewPassword.setText("")
                    Toast.makeText(requireContext(), "The old password and the new password are the same", Toast.LENGTH_LONG).show()
                }
                2 -> {
                    oldPassword.setText("")
                    newPassword.setText("")
                    reNewPassword.setText("")
                    Toast.makeText(requireContext(), "The password is not a valid password", Toast.LENGTH_LONG).show()
                }
                5 -> {
                    Toast.makeText(requireContext(), "You have been changed the password!", Toast.LENGTH_LONG).show()
                    dialog.dismiss()
                }
                else -> {
                    oldPassword.setText("")
                    newPassword.setText("")
                    reNewPassword.setText("")
                    dialog.dismiss()
                    Toast.makeText(requireContext(), "You didnt change the password", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun showCreateUserDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.create_user_dialog)
        dialog.show()
        val name: EditText = dialog.findViewById(R.id.create_user_name)
        val surname: EditText = dialog.findViewById(R.id.create_user_surname)
        val username: EditText = dialog.findViewById(R.id.create_user_username)
        val mail: EditText = dialog.findViewById(R.id.create_user_mail)
        val phone: EditText = dialog.findViewById(R.id.create_user_phone)
        val password: EditText = dialog.findViewById(R.id.create_user_password)
        val confirm: Button = dialog.findViewById(R.id.create_user_create)

        confirm.setOnClickListener {
            viewmodel.createUser(
                name.text.toString(),
                surname.text.toString(),
                username.text.toString(),
                mail.text.toString(),
                phone.text.toString(),
                password.text.toString()
            )
            dialog.dismiss()
        }
    }

    private fun showDialog(title: String, message: String, firstOpt: String, secondOpt: String) : AlertDialog {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(firstOpt) { dialog, which ->
            dialog.dismiss()
        }
        builder.setNegativeButton(secondOpt) { dialog, which ->

        }
        return builder.create()
    }

}
