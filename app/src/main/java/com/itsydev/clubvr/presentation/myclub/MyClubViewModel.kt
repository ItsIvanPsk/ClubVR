package com.itsydev.clubvr.presentation.myclub

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.itsydev.clubvr.data.models.users.UserEntity
import com.itsydev.clubvr.domain.users.UserRepository
import com.itsydev.clubvr.utils.BearEncrypt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyClubViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private var activeUser: MutableLiveData<UserEntity> = MutableLiveData<UserEntity>()
    private var username: MutableLiveData<String> = MutableLiveData<String>()
    private var bear = BearEncrypt()

    fun getActiveUser() = activeUser


    fun updateUserPoints(_username:String){
        val database = Firebase.firestore
        val collectionReference = database.collection("profiles")
        collectionReference.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val data = document.data
                    Log.d("5cos", bear.decrypt(data["username"].toString()))
                    if(_username == bear.decrypt(data["username"].toString())){
                        activeUser.value = UserEntity(
                            id = data["id"].toString(),
                            username = data["username"].toString(),
                            name = data["name"].toString(),
                            surname = data["surname"].toString(),
                            mail = data["mail"].toString(),
                            telf = data["telf"].toString(),
                            lenguage = data["len"].toString(),
                            userLevel = data["userLeve"].toString(),
                            userPoints = data["userPoints"].toString()
                        )
                    }
                }
            }
            .addOnFailureListener {
                Log.d("5cos", "Listener failed!!")
            }
    }

    fun updateRoomUsername(){
        viewModelScope.launch {
            userRepository.getAllUsers.collect {
                username.value = it[0].username
            }
        }
    }

    fun getRoomUsername() : LiveData<String>{
        return username
    }

}