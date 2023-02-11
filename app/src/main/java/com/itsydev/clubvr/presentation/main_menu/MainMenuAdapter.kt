package com.itsydev.clubvr.presentation.main_menu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.itsydev.clubvr.databinding.LayoutExperienceItemBinding
import com.itsydev.clubvr.data.models.experiences.ExperienceConstants
import com.itsydev.clubvr.data.models.experiences.ExperienceBo
import com.itsydev.clubvr.data.models.news.NewsBo
import com.itsydev.clubvr.presentation.experiences.ExperienceListeners
import com.itsydev.clubvr.presentation.experiences.ExperiencesAdapter
import com.itsydev.clubvr.presentation.experiences.ExperiencesDiffCallBack

class MainMenuAdapter(
    private val context: Context
) : ListAdapter<NewsBo, MainMenuAdapter.NewsViewHolder> (NewDiffCallback)
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = LayoutExperienceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NewsViewHolder(private val binding: LayoutExperienceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ExperienceBo){

        }
    }
}

object NewDiffCallback : DiffUtil.ItemCallback<ExperienceBo>() {
    override fun areItemsTheSame(oldItem: ExperienceBo, newItem: ExperienceBo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ExperienceBo, newItem: ExperienceBo): Boolean {
        return false
    }
}

interface NewsListeners{
    fun newPressed(view: View, position:Int)
}