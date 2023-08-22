package com.example.logintask1.ui.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.logintask1.R
import com.example.logintask1.R.drawable.baseline_account_box_24
import com.example.logintask1.data.ListItem

class myListAdapter: ListAdapter<ListItem, myListAdapter.MyViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MyViewHolder(itemView: View) : ViewHolder(itemView){
        fun bind(item: ListItem){
            itemView.findViewById<TextView>(R.id.textViewTitle).text = item.title
//            itemView.findViewById<TextView>(R.id.imageViewThumbnail).background

        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem == newItem
    }
}