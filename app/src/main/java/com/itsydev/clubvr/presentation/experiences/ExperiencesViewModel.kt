package com.itsydev.clubvr.presentation.experiences

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.itsydev.clubvr.utils.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class ExperiencesViewModel : ViewModel(){

    val db = Firebase.firestore

    private var experiences = MutableLiveData<List<ExperienceBo>>()
    private var rememberMe = MutableLiveData<List<ExperienceBo>>()

    fun getExperiencies(): LiveData<List<ExperienceBo>> = experiences

    fun updateExperiences(_context: Context, _fileName: String) {
        val inputStream = _context.assets.open(_fileName)
        val json = inputStream.bufferedReader().use { it.readText() }
        val jsonObject = JSONObject(json)
        val experienceList = mutableListOf<ExperienceBo>()


        for (key in jsonObject.keys()) {
            val itemJson = jsonObject.getJSONObject(key)
            val imageList = mutableListOf<ExperienceImages>()
            val warningList = mutableListOf<Warning>()
            val categoryList = mutableListOf<Category>()
            val headsetList = mutableListOf<CompatibleHeadset>()
            val descriptionList = mutableListOf<String>()

            for (i in 0 until itemJson.getJSONArray("img").length()) {
                val image = itemJson.getJSONArray("img").get(i)
                imageList.add(
                    ExperienceImages(i, image.toString())
                )
            }

            for (i in 0 until itemJson.getJSONArray("description").length()) {
                val description = itemJson.getJSONArray("description").get(i)
                descriptionList.add(
                    description.toString()
                )
            }

            for (i in 0 until itemJson.getJSONArray("warnings").length()) {
                val warning = itemJson.getJSONArray("warnings").get(i).toString()
                warningList.add(
                    Warning(
                        id = warning.toInt()
                    )
                )
                Log.d("5coos", warning)
            }

            for (i in 0 until itemJson.getJSONArray("categories").length()) {
                val category = itemJson.getJSONArray("categories").get(i).toString()
                categoryList.add(
                    Category(category.toInt())
                )
            }

            for (i in 0 until itemJson.getJSONArray("headsets_compatible").length()) {
                val compatibleHeadset = itemJson.getJSONArray("headsets_compatible").get(i).toString()
                headsetList.add(
                    CompatibleHeadset(compatibleHeadset.toInt())
                )
            }

            experienceList.add(
                ExperienceBo(
                    id = key.toInt(),
                    name = itemJson.getString("name"),
                    img = imageList.toList(),
                    description = descriptionList.toList(),
                    warnings = warningList.toList(),
                    rating = itemJson.getDouble("rating"),
                    categories = categoryList.toList(),
                    headsets_compatible = headsetList.toList()
                )
            )
        }
        experiences.value = experienceList
    }
}