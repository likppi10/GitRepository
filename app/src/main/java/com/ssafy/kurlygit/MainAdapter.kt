package com.ssafy.kurlygit

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
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

//    fun setList(list: MutableList<Repository>) {
//        itemList.addAll(list)
//        //itemList.add(Repository(0,"",true,"",0,0))
//    }
//
//    fun clearList() {
//        itemList = mutableListOf()
//    }
//
//    override fun getItemCount(): Int {
//        return itemList.size
//    }

//    fun deleteLoading(){
//        // 예상할 수 없는 예외를 차단하기 위해 레파지토리 명을 ""로 지정해준 로딩 아이템만 지우도록 조건을 줍니다.
//        if(itemList[itemList.lastIndex].name==""){
//            itemList.removeAt(itemList.lastIndex)
//        }
//    }


    inner class LoadingViewHolder(private val binding: ViewholderLoadingBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun submitList(list: List<Repository>?) {

        super.submitList(list?.let { ArrayList(it) })
    }

}