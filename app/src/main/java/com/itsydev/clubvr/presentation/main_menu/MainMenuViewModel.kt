package com.itsydev.clubvr.presentation.main_menu

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.itsydev.clubvr.data.models.main_menu.MainMenuItemBo
import com.itsydev.clubvr.data.models.users.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainMenuViewModel @Inject constructor() : ViewModel() {

    private var mm_items: MutableLiveData<List<MainMenuItemBo>> = MutableLiveData<List<MainMenuItemBo>>()

    fun updateMainMenuItems() {
        val database = Firebase.firestore
        val collectionReference = database.collection("news")
        collectionReference.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val data = document.data
                    val mm_list = mutableListOf<MainMenuItemBo>()

                }
            }
            .addOnFailureListener {
                Log.d("5cos", "Listener failed!!")
            }
    }

    fun getMainMenuItems() : LiveData<List<MainMenuItemBo>> = mm_items

}
