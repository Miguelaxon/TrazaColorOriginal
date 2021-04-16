package com.example.trazacolor.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trazacolor.R
import com.example.trazacolor.databinding.FragmentCarritoBinding
import com.example.trazacolor.local.Item
import com.example.trazacolor.ui.adapter.AdapterCarrito
import com.example.trazacolor.viewmodel.ViewModel
import java.text.DecimalFormat

class CarritoFragment : Fragment() {

    private lateinit var binding: FragmentCarritoBinding
    private val viewModel: ViewModel by activityViewModels()
    var total: Int = 0
    lateinit var item: Item

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentCarritoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val formatter = DecimalFormat("$#,###")
        val adapter = AdapterCarrito()
        binding.rv2.adapter = adapter
        binding.rv2.layoutManager = GridLayoutManager(context, 2)
        binding.tvTitleCarrito.text = getString(R.string.carro_de_compras)


        viewModel.returnCarrito().observe(viewLifecycleOwner, {
            it?.let {
                adapter.update(it)
                total = it.sumOf { sum ->
                    sum.total
                }
                binding.btnEmpty.setOnClickListener {
                    item.carrito = false
                }
            }
            binding.tvTotalCarro.text = getString(R.string.totalCarrito, formatter.format(total))


        })
    }
}