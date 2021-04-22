package com.example.trazacolor.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trazacolor.R
import com.example.trazacolor.databinding.ItemTrazaColorBinding
import com.example.trazacolor.local.Item

class Adapter: RecyclerView.Adapter<Adapter.ViewHolderTrazaColor>() {
    private var lista = listOf<Item>()
    private val selected = MutableLiveData<Item>()

    fun selected(): LiveData<Item> = selected

    fun update(list: List<Item>) {
        lista = list
        notifyDataSetChanged()
    }

    inner class ViewHolderTrazaColor(private val binding: ItemTrazaColorBinding)
        : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        override fun onClick(v: View?) {
            selected.value = lista[adapterPosition]
        }
        fun bind(item: Item) {
            binding.tvCategory.text = item.category.toString().toUpperCase()
            Glide.with(binding.ivPortada)
                    .load(item.urlImage).placeholder(R.drawable.ic_pregunta)
                    .into(binding.ivPortada)
            binding.tvName.text = item.name
            itemView.setOnClickListener(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTrazaColor {
        return ViewHolderTrazaColor(ItemTrazaColorBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolderTrazaColor, position: Int) {
        val listaM = lista[position]
        holder.bind(listaM)
    }

    override fun getItemCount(): Int = lista.size
}