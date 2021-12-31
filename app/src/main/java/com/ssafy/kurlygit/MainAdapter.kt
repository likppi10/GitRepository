package com.ssafy.kurlygit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.kurlygit.databinding.ViewholderItemBinding

class MainAdapter(var itemList : List<Repository>)
    : RecyclerView.Adapter<MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_item, parent, false)
        return MainViewHolder(ViewholderItemBinding.bind(inflatedView))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = itemList[position]
        holder.apply {
            bind(item)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}