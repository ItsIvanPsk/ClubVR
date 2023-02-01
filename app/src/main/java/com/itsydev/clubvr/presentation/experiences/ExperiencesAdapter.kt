package com.itsydev.clubvr.presentation.experiences

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.itsydev.clubvr.data.models.experiences.ExperienceBo
import com.itsydev.clubvr.data.models.experiences.ExperienceConstants
import com.itsydev.clubvr.databinding.ItemLayoutExperienceBinding
import com.itsydev.clubvr.databinding.ItemMainMenuBinding

class ExperiencesAdapter(
    private val context: Context,
    private val experienceListeners: ExperienceListeners
) : ListAdapter<ExperienceBo, ExperiencesAdapter.ExperiencesViewHolder> (ExperiencesDiffCallBack)
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperiencesViewHolder {
        val binding = ItemLayoutExperienceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExperiencesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExperiencesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ExperiencesViewHolder(private val binding: ItemLayoutExperienceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ExperienceBo){
            if(item.img.isNotEmpty()){
                binding.experienceImage.load(item.img[0].url){
                    crossfade(true)
                    transformations(CircleCropTransformation())
            }
            binding.experienceName.text = item.name
            var expCategory = ""
            for (i in 0 until item.categories.size) {
                if(i == item.categories.size - 1){
                    expCategory += context.resources.getString(ExperienceConstants.CATEGORY.get(item.categories[i].id))
                    break
                }
                expCategory += context.resources.getString(ExperienceConstants.CATEGORY.get(item.categories[i].id)) + ", "
            }
            binding.experienceCategory.text = expCategory
            binding.experienceBg.setOnClickListener {
                experienceListeners.experienceClicked(it, item.name)
            }
        }
        }
    }
}

object ExperiencesDiffCallBack : DiffUtil.ItemCallback<ExperienceBo>() {
    override fun areItemsTheSame(oldItem: ExperienceBo, newItem: ExperienceBo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ExperienceBo, newItem: ExperienceBo): Boolean {
        return false
    }
}

interface ExperienceListeners{
    fun experienceClicked(view: View, name:String)
}