package com.leoleo2.myokashi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class OkashiListAdapter(
    private val onItemClick: (APIResponse.Item) -> Unit
) : ListAdapter<APIResponse.Item, OkashiListAdapter.VH>(ITEM_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.okashi_item, parent, false)
        return VH(item)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: APIResponse.Item) {
            val textName = itemView.findViewById<TextView>(R.id.name)
            textName.text = item.name
            val imageView = itemView.findViewById<ImageView>(R.id.image)
            imageView.setImageUrl(item.image)
            itemView.rootView.setOnClickListener {
                onItemClick(item)
            }
        }
    }
}

private val ITEM_CALLBACK = object : DiffUtil.ItemCallback<APIResponse.Item>() {
    override fun areItemsTheSame(oldItem: APIResponse.Item, newItem: APIResponse.Item): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: APIResponse.Item, newItem: APIResponse.Item): Boolean {
        return oldItem == newItem
    }
}