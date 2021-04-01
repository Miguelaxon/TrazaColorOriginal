package com.example.trazacolor.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trazacolor.R
import com.example.trazacolor.databinding.FragmentCarritoComprasBinding
import com.example.trazacolor.ui.adapter.AdapterCarrito
import com.example.trazacolor.viewmodel.ViewModel
import java.text.DecimalFormat

class CarritoComprasFragment : Fragment() {
    private lateinit var binding: FragmentCarritoComprasBinding
    private val viewModel: ViewModel by activityViewModels()
    var total: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentCarritoComprasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val formatter = DecimalFormat("$#,###")
        val adapter = AdapterCarrito()
        binding.rv2.adapter = adapter
        binding.rv2.layoutManager = GridLayoutManager(context, 2)
        binding.tvTitleCarrito.text = getString(R.string.carro_de_compras)
        binding.tvTotalCarro.text = getString(R.string.totalCarrito, formatter.format(total))

        viewModel.returnCarrito().observe(viewLifecycleOwner, {
            it?.let {
                Log.d("carrito","$it")
                adapter.update(it)
            }
        })
    }
}