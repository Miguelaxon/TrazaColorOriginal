package com.example.trazacolor.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.trazacolor.databinding.ItemCarritoComprasBinding
import com.example.trazacolor.local.Item
import java.text.DecimalFormat

class AdapterCarrito: RecyclerView.Adapter<AdapterCarrito.ViewHolderCarrito>() {
    private var lista = listOf<Item>()
    private var selected = MutableLiveData<Item>()

    fun selected(): LiveData<Item> = selected

    fun update(list: List<Item>) {
        lista = list
        notifyDataSetChanged()
    }

    inner class ViewHolderCarrito(private val binding: ItemCarritoComprasBinding)
        : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(item: Item) {
            val formatter = DecimalFormat("$#,###")
            if (item.carrito) {
                binding.tvProductos.text = item.name
                binding.tvPrecioCarro.text = "Precio:\n" + formatter.format(item.price)
                binding.tvCantidadCarro.text = "Cantidad:\n\t\t" + item.cantTotal.toString()
                binding.tvSubTotal.text = "SubTotal: " + formatter.format(item.total)
            }
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            selected.value = lista[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCarrito {
        return ViewHolderCarrito(ItemCarritoComprasBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolderCarrito, position: Int) {
        val listaM = lista[position]
        holder.bind(listaM)
    }

    override fun getItemCount(): Int = lista.size
}