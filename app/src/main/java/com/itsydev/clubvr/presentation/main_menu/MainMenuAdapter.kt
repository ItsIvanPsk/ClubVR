package com.itsydev.clubvr.presentation.main_menu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.itsydev.clubvr.data.models.main_menu.MainMenuItemBo
import com.itsydev.clubvr.databinding.ItemMainMenuBinding

class MainMenuAdapter(
    private val context: Context,
    private val mainMenuItemListener: MainMenuItemListener
) : ListAdapter<MainMenuItemBo, MainMenuAdapter.MainMenuItemViewHolder> (MainMenuItemDiffCallBack)
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainMenuItemViewHolder {
        val binding = ItemMainMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainMenuItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainMenuItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MainMenuItemViewHolder(private val binding: ItemMainMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MainMenuItemBo) = with(binding){
            mmItemTitle.text = item.main_title[0]
            mmItemSubtitle.text = item.main_subtitle[0]
            mmItemDescription.text = item.sections[0].description[0]
            mmItemBg.setOnClickListener {
                mainMenuItemListener.newPressed(it, item.id)
            }
        }
    }
}

object MainMenuItemDiffCallBack : DiffUtil.ItemCallback<MainMenuItemBo>() {
    override fun areItemsTheSame(oldItem: MainMenuItemBo, newItem: MainMenuItemBo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MainMenuItemBo, newItem: MainMenuItemBo): Boolean {
        return false
    }
}

interface MainMenuItemListener{
    fun newPressed(view: View, itemId:Int)
}