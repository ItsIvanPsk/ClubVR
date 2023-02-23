package com.itsydev.clubvr.presentation.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.itsydev.clubvr.MainActivity
import com.itsydev.clubvr.R
import com.itsydev.clubvr.databinding.FragmentLoginBinding
import com.itsydev.clubvr.utils.BearEncrypt
import com.itsydev.clubvr.utils.getSharedPreference
import com.itsydev.clubvr.utils.setSharedPreference
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment(){

    private lateinit var binding: FragmentLoginBinding
    private val viewmodel: LoginViewModel by viewModels()
    private val bear = BearEncrypt()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLoginBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupListeners()
        setupObservers()
        binding.loginAppIcon.background = null
        binding.loginProgressBar.visibility = View.GONE
        return binding.root
    }

    private fun setupListeners() = with(binding){
        loginButton.setOnClickListener {
            loginProgressBar.visibility = View.VISIBLE
            viewmodel.checkUsername(
                usernameInput.text.toString(),
                passwordInput.text.toString()
            )
        }
    }

    private fun setupObservers() = with(viewmodel){
        getLoggedIn().observe(viewLifecycleOwner){
            Log.d("5cos", it.toString())
            if(it){
                binding.loginProgressBar.visibility = View.VISIBLE
                Toast.makeText(context, R.string.user_success_login, Toast.LENGTH_SHORT).show()
                viewmodel.getUsernameByMail(binding.usernameInput.text.toString())
                startActivity(Intent(context, MainActivity::class.java))
            } else if (!it) {
                binding.passwordInput.setText("")
                Toast.makeText(context, R.string.user_failed_login, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setupUserPreferences(len: String) {
        var id = 0
        id = when (bear.decrypt(len)) {
            "en" -> { 0 }
            "es" -> { 1 }
            "cat" -> { 2 }
            else -> { -1 }
        }
    }

}
