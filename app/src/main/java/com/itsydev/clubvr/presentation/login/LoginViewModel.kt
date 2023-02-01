package com.itsydev.clubvr.presentation.login

import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.itsydev.clubvr.utils.BearEncrypt
import com.itsydev.clubvr.data.models.users.UserEntity
import com.itsydev.clubvr.domain.users.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private var loggedIn: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val bear = BearEncrypt()
    private val users = MutableLiveData<List<UserEntity>>()

    fun checkUsername(_username: String, _passowrd: String) {
        if(_username.isNotEmpty()){
            mAuth.signInWithEmailAndPassword(_username, _passowrd)
                .addOnCompleteListener { task ->
                    loggedIn.value = task.isSuccessful
                }
        }
    }

    fun checkAutoLogin(){
        viewModelScope.launch {
            repository.getAllUsers.collect{
                loggedIn.value = true
            }
        }
    }

    private fun addUser(_user : UserEntity){
        viewModelScope.launch {
            repository.addUser(_user)
        }
    }

    fun getLoggedIn(): LiveData<Boolean>{
        return loggedIn
    }

    fun getUsernameByMail(mail: String){
        val database = Firebase.firestore
        val collectionReference = database.collection("profiles")
        collectionReference.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val data = document.data
                    if(data["mail"] == bear.encrypt(mail)){
                        addUser(
                            UserEntity(
                                id = data["id"].toString(),
                                username = data["username"].toString(),
                                name = data["name"].toString(),
                                surname = data["surname"].toString(),
                                mail = data["mail"].toString(),
                                telf = data["telf"].toString(),
                                lenguage = data["len"].toString(),
                                userLevel = data["userLeve"].toString(),
                                userPoints = data["userPoints"].toString(),
                                admin = data["admin"].toString()
                            )
                        )
                        break
                    }
                }
            }
            .addOnFailureListener {
                Log.d("5cos", "Listener failed!!")
            }
    }

}