package com.itsydev.clubvr.presentation.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itsydev.clubvr.data.models.users.UserEntity
import com.itsydev.clubvr.domain.users.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val users = MutableLiveData<List<UserEntity>>()

    fun updateUsers() {
        viewModelScope.launch {
           repository.getAllUsers.collect{
               users.value = it
           }
        }
    }

    fun getUsers() = users

    fun logout() {
        viewModelScope.launch{
            delay(1000)
            repository.deleteUser()
        }
    }

}