package com.itsydev.clubvr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class AppViewModel : ViewModel() {
    private var destination = MutableLiveData<Int>()

    fun getDestination() : LiveData<Int> {
        return destination
    }
    fun setDestination(_dest: Int){
        destination.value = _dest
    }

}