package com.itsydev.clubvr.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.itsydev.clubvr.MainActivity
import com.itsydev.clubvr.R
import com.itsydev.clubvr.databinding.FragmentLoginBinding

class LoginFragment : Fragment(){

    private lateinit var binding: FragmentLoginBinding
    private val viewmodel: LoginViewModel by activityViewModels()


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
        return binding.root
    }

    private fun setupListeners() = with(binding){
        loginButton.setOnClickListener {
            viewmodel.checkUsername(
                binding.usernameInput.text.toString(),
                binding.passwordInput.text.toString()
            )
        }
        rememberMeCheckbox.setOnClickListener {
            viewmodel.changeRememberMe()
        }
    }

    private fun setupObservers() = with(viewmodel){
        getLoggedIn().observe(viewLifecycleOwner){
            if(it){
                Toast.makeText(context, R.string.user_success_login, Toast.LENGTH_LONG).show()
                val intent: Intent = Intent(context, MainActivity::class.java)
                intent.putExtra("mail", binding.usernameInput.text.toString())
                startActivity(intent)
            } else if (!it) {
                Toast.makeText(context, R.string.user_success_login, Toast.LENGTH_LONG).show()
                binding.passwordInput.setText("")
            }
        }
    }

}
