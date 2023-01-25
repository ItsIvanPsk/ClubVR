package com.itsydev.clubvr.presentation.experiences

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.itsydev.clubvr.utils.ExperienceBo

class ExperiencesViewModel : ViewModel(){

    private val db = Firebase.firestore

    private var experiences = MutableLiveData<List<ExperienceBo>>()

    fun getExperiencies(): LiveData<List<ExperienceBo>> = experiences

    fun updateExperiencies(){
        db.collection("experiences")
            .get()
            .addOnSuccessListener { result ->
                var experienceList = mutableListOf<ExperienceBo>()
                for (document in result) {
                    Log.d("5cos", "${document.id} => ${document.data}")
                    experienceList.add(
                        ExperienceBo(
                            document.id.toInt(),
                            document.data.get("name").toString(),
                            document.data.get("description").toString(),
                            document.data.get("img").toString()
                        )
                    )
                }
                experiences.value = experienceList
            }
            .addOnFailureListener { exception ->
                Log.w("5cos", "Error getting documents.", exception)
            }
    }

}