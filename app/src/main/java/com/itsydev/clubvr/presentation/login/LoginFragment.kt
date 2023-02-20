package com.itsydev.clubvr.presentation.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.itsydev.clubvr.MainActivity
import com.itsydev.clubvr.R
import com.itsydev.clubvr.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(){

    private lateinit var binding: FragmentLoginBinding
    private val viewmodel: LoginViewModel by viewModels()

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

}
