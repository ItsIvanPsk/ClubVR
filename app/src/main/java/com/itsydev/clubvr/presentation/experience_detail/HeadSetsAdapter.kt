package com.itsydev.clubvr.presentation.experience_detail

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.ImageLoader
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.itsydev.clubvr.R
import com.itsydev.clubvr.data.models.experiences.ExperienceBo
import com.itsydev.clubvr.data.models.experiences.ExperienceConstants
import com.itsydev.clubvr.data.models.headsets.HeadsetBo
import com.itsydev.clubvr.databinding.ExperienceHeadsetItemBinding
import com.itsydev.clubvr.databinding.ItemLayoutExperienceBinding

class HeadSetsAdapter(
    private val context: Context,
    private val listeners: HeadsetsListeners
) : ListAdapter<HeadsetBo, HeadSetsAdapter.HeadsetsViewHolder> (HeadsetsDiffCallBack)
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadsetsViewHolder {
        val binding = ExperienceHeadsetItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeadsetsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeadsetsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HeadsetsViewHolder(private val binding: ExperienceHeadsetItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HeadsetBo){
            binding.experienceHeadset.setOnClickListener {
                listeners.headsetsClicked(it, item.name)
            }
            binding.experienceHeadsetName.text = item.name

        }
    }
}

object HeadsetsDiffCallBack : DiffUtil.ItemCallback<HeadsetBo>() {
    override fun areItemsTheSame(oldItem: HeadsetBo, newItem: HeadsetBo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HeadsetBo, newItem: HeadsetBo): Boolean {
        return false
    }
}

interface HeadsetsListeners{
    fun headsetsClicked(view: View, name: String)
}