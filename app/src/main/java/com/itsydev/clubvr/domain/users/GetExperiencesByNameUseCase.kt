package com.itsydev.clubvr.domain.users

import android.content.Context
import android.util.Log
import com.itsydev.clubvr.data.models.experiences.*
import org.json.JSONObject

interface GetExperiencesByNameUseCase {
    fun getExperiencesByName(name:CharSequence,context: Context): List<ExperienceBo>
}

class GetExperiencesByNameUseCaseImpl : GetExperiencesByNameUseCase{

    override fun getExperiencesByName(name: CharSequence, context: Context): List<ExperienceBo> {
        val experiences = loadExperienceJson(context)
        val filteredList = mutableListOf<ExperienceBo>()

        for (key in experiences) {
            if(key.name.contains(name)){
                filteredList.add(key)
            }
        }

        return filteredList

    }

    private fun loadExperienceJson(_context: Context) : List<ExperienceBo> {
        val inputStream = _context.assets.open("json/experiences.json")
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
        return experienceList
    }

}