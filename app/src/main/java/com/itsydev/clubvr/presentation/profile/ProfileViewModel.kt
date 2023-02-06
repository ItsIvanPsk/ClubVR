package com.itsydev.clubvr.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itsydev.clubvr.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private var _userLiveData = MutableLiveData<UserEntity>()

    fun updateUser(){  }

    fun getUser(): LiveData<UserEntity> = _userLiveData

}