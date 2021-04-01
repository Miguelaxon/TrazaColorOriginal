package com.example.trazacolor.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.GridLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.trazacolor.R
import com.example.trazacolor.databinding.FragmentCarritoComprasBinding
import com.example.trazacolor.databinding.FragmentSecondBinding
import com.example.trazacolor.ui.adapter.Adapter
import com.example.trazacolor.ui.adapter.AdapterCarrito
import com.example.trazacolor.viewmodel.ViewModel
import java.text.DecimalFormat

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private val viewModel: ViewModel by activityViewModels()
    var idS: Int = 0
    var categoria: String = ""
    var cant: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            idS = requireArguments().getInt("id")
            categoria = requireArguments().getString("categoria", "")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val formatter = DecimalFormat("$#,###")
        val adapter = AdapterCarrito()
        val adapter2 = Adapter()
        viewModel.listAll.observe(viewLifecycleOwner, {
            binding.tvNameS.text = it[idS].name.toString().toUpperCase()
            Glide.with(binding.ivPortadaS).load(it[idS].urlImage).placeholder(R.drawable.ic_pregunta)
                .into(binding.ivPortadaS)
            binding.tvMaterialS.text = getString(R.string.material, it[idS].material)
            binding.tvDimensionChic.text = getString(R.string.dimensionChica, it[idS].small)
            binding.tvDimensionMed.text = getString(R.string.dimensionMediana, it[idS].medium)
            binding.tvDimensionGran.text = getString(R.string.dimensionGrande, it[idS].big)
            binding.tvDimensionXL.text = getString(R.string.dimensionXL, it[idS].xl)
            binding.tvCantidadS.text = getString(R.string.cantidad, it[idS].amount)
            binding.tvPrecioS.text =
                getString(R.string.precio, formatter.format(it[idS].price).toString())
            buttons()
            it[idS].cantTotal = binding.tvCantidadProducto.text.toString().toInt()
            if (it[idS].name.isNullOrEmpty()) { binding.tvNameS.isVisible = false }
            if (it[idS].material.isNullOrEmpty()) { binding.tvMaterialS.isVisible = false }
            if (it[idS].small.isNullOrEmpty()) { binding.tvDimensionChic.isVisible = false }
            if (it[idS].medium.isNullOrEmpty()) { binding.tvDimensionMed.isVisible = false }
            if (it[idS].big.isNullOrEmpty()) { binding.tvDimensionGran.isVisible = false }
            if (it[idS].xl.isNullOrEmpty()) { binding.tvDimensionXL.isVisible = false }
            if (it[idS].amount.isNullOrEmpty()) { binding.tvCantidadS.isVisible = false }
            if (it[idS].price.toString().isEmpty()) { binding.tvPrecioS.isVisible = false }

            binding.btnAgregarCarrito.setOnClickListener { _ ->
                if (!it[idS].carrito) {
                    it[idS].carrito = true
                    viewModel.updateCarrito(it[idS])
                    Toast.makeText(context, "Se Agregó al Carrito con Éxito",
                            Toast.LENGTH_SHORT).show()
                }
            }

            /*binding.ibAgregarCarrito.setOnClickListener {
                findNavController().navigate(R.id.action_SecondFragment_to_carritoComprasFragment)
            }*/
        })
    }

    private fun buttons() {
        binding.tvCantidadProducto.text = cant.toString()
        binding.btnMas.setOnClickListener {
            if (cant < 5){
                cant++
                binding.tvCantidadProducto.text = cant.toString()
            } else {
                cant = 5
                binding.tvCantidadProducto.text = cant.toString()
            }
        }
        binding.btnMenos.setOnClickListener {
            if (cant > 0) {
                cant--
                binding.tvCantidadProducto.text = cant.toString()
            } else {
                cant = 0
                binding.tvCantidadProducto.text = cant.toString()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_carritoCompras ->
                findNavController().navigate(R.id.action_SecondFragment_to_carritoComprasFragment)
        }
        return super.onOptionsItemSelected(item)
    }
}