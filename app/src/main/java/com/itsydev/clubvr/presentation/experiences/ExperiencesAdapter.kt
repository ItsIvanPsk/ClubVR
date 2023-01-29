package com.itsydev.clubvr.presentation.experiences

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.bumptech.glide.Glide
import com.itsydev.clubvr.databinding.LayoutExperienceItemBinding
import com.itsydev.clubvr.utils.ExperienceBo

class ExperiencesAdapter(private val context: Context) : ListAdapter<ExperienceBo, ExperiencesAdapter.ExperiencesViewHolder>
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
            Glide.with(context)
                .load(item.img.get(0).url.toString())
                .into(binding.experienceImage)
            binding.experienceName.text = item.name
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
