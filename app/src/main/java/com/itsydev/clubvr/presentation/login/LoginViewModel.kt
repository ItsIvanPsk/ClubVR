package com.itsydev.clubvr.presentation.login

import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.itsydev.clubvr.R
import com.itsydev.clubvr.utils.UsernameDto
import kotlin.coroutines.coroutineContext
import kotlin.math.log

class LoginViewModel : ViewModel(){

    private var loggedIn: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun checkUsername(_username: String, _passowrd: String) {
        mAuth.signInWithEmailAndPassword(_username, _passowrd)
            .addOnCompleteListener { task ->
                loggedIn.value = task.isSuccessful
            }
    }

    fun changeRememberMe() {

    }

    fun getLoggedIn(): LiveData<Boolean>{
        return loggedIn
    }

}