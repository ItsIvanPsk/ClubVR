package com.itsydev.clubvr.presentation.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itsydev.clubvr.BearEncrypt
import com.itsydev.clubvr.UserDto
import com.itsydev.clubvr.domain.users.UserDao
import com.itsydev.clubvr.toDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userDao: UserDao
) : ViewModel() {

    private val users = MutableLiveData<List<UserDto>>()
    private val bear = BearEncrypt()

    fun updateUsers() {

    }

    fun getUsers() = users

}