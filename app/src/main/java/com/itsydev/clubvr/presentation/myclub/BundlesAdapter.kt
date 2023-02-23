package com.itsydev.clubvr.presentation.myclub

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.itsydev.clubvr.data.models.bundles.BundlesBo
import com.itsydev.clubvr.data.models.experiences.ExperienceBo
import com.itsydev.clubvr.data.models.experiences.ExperienceConstants
import com.itsydev.clubvr.databinding.ItemBundleBinding
import com.itsydev.clubvr.databinding.ItemLayoutExperienceBinding

class BundlesAdapter(
    private val context: Context,
    private val bundleListener: BundleListeners
) : ListAdapter<BundlesBo, BundlesAdapter.BundlesViewHolder> (BundlesDiffCallBack)
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BundlesViewHolder {
        val binding = ItemBundleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BundlesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BundlesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BundlesViewHolder(private val binding: ItemBundleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BundlesBo){
            binding.bundleTitle.text = item.name[0]
            binding.bundleSubtitle.text = item.description[0]
        }
    }
}

object BundlesDiffCallBack : DiffUtil.ItemCallback<BundlesBo>() {
    override fun areItemsTheSame(oldItem: BundlesBo, newItem: BundlesBo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BundlesBo, newItem: BundlesBo): Boolean {
        return false
    }
}

interface BundleListeners{
    fun bundleClicked(view: View, name: String)
}