package com.waro.aku

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waro.aku.databinding.ActivityMainBinding
import com.waro.aku.databinding.ItemRowOshiBinding

class ListOshiAdapter(private val listOshi: ArrayList<Oshi>) : RecyclerView.Adapter<ListOshiAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Oshi)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemRowOshiBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowOshiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listOshi.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, Description, photo) = listOshi[position]
        holder.binding.tvItemName.text = name
        holder.binding.tvItemDescription.text = Description
        holder.binding.imgItemPhoto.setImageResource(photo)

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listOshi[holder.adapterPosition]) }
    }

}