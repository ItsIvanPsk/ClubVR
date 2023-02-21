package com.itsydev.clubvr.presentation.experiences

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itsydev.clubvr.data.models.experiences.*
import com.itsydev.clubvr.data.models.headsets.HeadsetBo
import com.itsydev.clubvr.data.models.headsets.HeadsetImages
import com.itsydev.clubvr.domain.users.GetExperiencesByNameUseCase
import com.itsydev.clubvr.domain.users.GetExperiencesByNameUseCaseImpl
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class ExperiencesViewModel @Inject constructor(
) : ViewModel() {

    private var experiences = MutableLiveData<List<ExperienceBo>>()
    private var experienceToDetail = MutableLiveData<ExperienceBo>()

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

    fun setExperienceDetail(name: String){

        experiences.value?.forEach {
             if (it.name == name) {
                 experienceToDetail.value = ExperienceBo(
                     it.id,
                     it.name,
                     it.description,
                     it.categories,
                     it.img,
                     it.rating,
                     it.warnings,
                     it.headsets_compatible
                 )
             }
        }
    }

    fun getExperienceData() : LiveData<ExperienceBo>{
        return experienceToDetail
    }

    fun filterByName(s: CharSequence, context: Context) {
        if(s.isEmpty()){
            updateExperiences(context, "json/experiences.json")
        } else {
            viewModelScope.launch {
                experiences.value = GetExperiencesByNameUseCaseImpl().getExperiencesByName(s, context)
            }
        }
    }

    fun getAvaiableHeadsets(_context: Context, _fileName: String = "json/headsets.json", headsetIds : List<Int>) : List<HeadsetBo> {
        val inputStream = _context.assets.open(_fileName)
        val json = inputStream.bufferedReader().use { it.readText() }
        val jsonObject = JSONObject(json)
        val headsetsList = mutableListOf<HeadsetBo>()

        Log.d("5cos", headsetIds.toString())

        for (key in jsonObject.keys()) {
            val itemJson = jsonObject.getJSONObject(key)
            val imageList = mutableListOf<HeadsetImages>()
            val descriptionList = mutableListOf<String>()

            Log.d("5cos", "json -> $itemJson")

            headsetIds.forEach {
                if( key == it.toString()) {
                    for (i in 0 until itemJson.getJSONArray("description").length()) {
                        val image = itemJson.getJSONArray("description").get(i)
                        descriptionList.add(image.toString())
                    }

                    for (i in 0 until itemJson.getJSONArray("img").length()) {
                        val image = itemJson.getJSONArray("img").get(i)
                        imageList.add(
                            HeadsetImages(i, image.toString())
                        )
                    }

                    Log.d("5cos", "description -> $descriptionList")
                    Log.d("5cos", "images -> $imageList")

                    headsetsList.add(
                        HeadsetBo(
                            id = key.toInt(),
                            name = itemJson.getString("name"),
                            description = descriptionList,
                            img = imageList,
                        )
                    )
                    Log.d("5cos", "images -> $headsetsList")
                }
            }

        }
        return headsetsList
    }
}