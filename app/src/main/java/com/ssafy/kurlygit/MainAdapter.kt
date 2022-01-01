package com.ssafy.kurlygit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.kurlygit.databinding.ViewholderItemBinding
import com.ssafy.kurlygit.databinding.ViewholderLoadingBinding

class MainAdapter
    : ListAdapter<Repository,RecyclerView.ViewHolder>(Diff) {
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).name) {
            "" -> VIEW_TYPE_LOADING
            else -> VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder  {
        return when (viewType) {
            VIEW_TYPE_ITEM -> {
                val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_item, parent, false)
                MainViewHolder(ViewholderItemBinding.bind(inflatedView))
            }
            else -> {
                val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_loading, parent, false)
                LoadingViewHolder(ViewholderLoadingBinding.bind(inflatedView))
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if(holder is MainViewHolder){
            holder.apply {
                bind(item)
            }
        }
    }

    inner class LoadingViewHolder(private val binding: ViewholderLoadingBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun submitList(list: List<Repository>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

}