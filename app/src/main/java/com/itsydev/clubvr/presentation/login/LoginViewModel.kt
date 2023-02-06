package com.itsydev.clubvr.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.itsydev.clubvr.BearEncrypt
import com.itsydev.clubvr.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private var loggedIn: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val bear = BearEncrypt()

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

    private fun logUser(userEntity: UserEntity) {}

    fun getUsernameByMail(mail: String){
        val database = Firebase.firestore
        val collectionReference = database.collection("profiles")

        collectionReference.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val data = document.data
                    if(data.get("mail") == mail){
                        logUser(
                            UserEntity(
                                id = bear.decrypt(data.get("id").toString()),
                                username = bear.decrypt(data.get("username").toString()),
                                name = bear.decrypt(data.get("name").toString()),
                                surname = bear.decrypt(data.get("surname").toString()),
                                mail = bear.decrypt(data.get("mail").toString()),
                                telf = bear.decrypt(data.get("telf").toString()),
                                lenguage = bear.decrypt(data.get("len").toString()),
                                userLevel = bear.decrypt(data.get("userLevel").toString()),
                                userPoints = bear.decrypt(data.get("userPoints").toString()),
                            )
                        )
                        break
                    }
                }
            }
            .addOnFailureListener {  }
        println("back")
        viewModelScope.launch {
            delay(5000)
        }
    }

}