package com.airongomes.eventsapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airongomes.eventsapi.databinding.ItemEventBinding
import com.airongomes.eventsapi.domain.model.Event
import com.airongomes.eventsapi.extension.loadImage

class EventAdapter: ListAdapter<Event, EventAdapter.ViewHolder>(Comparator()) {

    var onClick: ((eventId: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemEventBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Event) {
            binding.eventTitle.text = item.title
            binding.eventDate.text = item.date.toString()//TODO
            binding.image.loadImage(item.image)
            binding.root.setOnClickListener { onClick?.invoke(item.id) }
        }
    }
}

class Comparator: DiffUtil.ItemCallback<Event>() {
    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem == newItem
    }
}