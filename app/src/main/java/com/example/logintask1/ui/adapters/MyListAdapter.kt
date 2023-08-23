package com.example.logintask1.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.logintask1.data.ListItem
import com.example.logintask1.databinding.ListItemBinding

class MyListAdapter : ListAdapter<ListItem, MyListAdapter.ListItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {

        val currentItem = getItem(position)
        holder.bind(currentItem)
//        holder.setListener(currentItem)
        holder.itemView.setListener(currentItem, position)
    }

    private fun View.setListener(item: ListItem, position: Int) {
        this.setOnClickListener {
            item.isExpanded = !item.isExpanded
            notifyItemChanged(position)
        }
    }

    class ListItemViewHolder(private val binding: ListItemBinding) : ViewHolder(binding.root) {
        fun bind(item: ListItem) {
            binding.textViewTitle.text = item.title
            binding.textViewDetails.text = item.details
            binding.textViewDetails.visibility = if (item.isExpanded) View.VISIBLE else View.GONE

//            binding.imageViewThumbnail = item.image

        }
    }


}


class DiffCallback : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem == newItem
    }
}