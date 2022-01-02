package com.ssafy.kurlygit.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.kurlygit.util.Diff
import com.ssafy.kurlygit.R
import com.ssafy.kurlygit.ui.view.data.model.RepositoryModel
import com.ssafy.kurlygit.databinding.ViewholderItemBinding
import com.ssafy.kurlygit.databinding.ViewholderLoadingBinding

class MainAdapter
    : ListAdapter<RepositoryModel,RecyclerView.ViewHolder>(Diff) {

    /* 현재 아이템이 로딩 중인지 데이터를 가진 아이템인지 분기 처리합니다.*/
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position].name) {
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
        val item = currentList[position]
        if(holder is MainViewHolder){
            holder.apply {
                bind(item)
            }
        }
    }

    inner class LoadingViewHolder(private val binding: ViewholderLoadingBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    /*  diffUtil을 사용하면 notify에서 모든 것을 알아차리지 않아도,
    *   범위를 지정해줄 필요도 없이, 변경사항을 적용할 수 있습니다. */
    override fun submitList(list: List<RepositoryModel>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

}