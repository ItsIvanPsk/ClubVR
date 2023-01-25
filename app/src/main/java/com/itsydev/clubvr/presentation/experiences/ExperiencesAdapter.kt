package com.itsydev.clubvr.presentation.experiences

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.itsydev.clubvr.databinding.LayoutExperienceItemBinding
import com.itsydev.clubvr.utils.ExperienceBo

class ExperiencesAdapter : ListAdapter<ExperienceBo, ExperiencesAdapter.ExperiencesViewHolder>
    (ExperiencesDiffCallBack)
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperiencesViewHolder {
        val binding = LayoutExperienceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExperiencesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExperiencesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ExperiencesViewHolder(private val binding: LayoutExperienceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ExperienceBo){
            binding.experienceImage.load(item.img){
                crossfade(true)
                transformations(CircleCropTransformation())
            }
            binding.experienceName.text = item.name
            binding.experienceCategory.text = item.category
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
