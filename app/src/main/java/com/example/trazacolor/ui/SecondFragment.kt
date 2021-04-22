package com.example.trazacolor.ui

import android.app.ActionBar
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.trazacolor.R
import com.example.trazacolor.databinding.FragmentSecondBinding
import com.example.trazacolor.local.Item
import com.example.trazacolor.viewmodel.ViewModel
import java.text.DecimalFormat

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private val viewModel: ViewModel by activityViewModels()
    var idS: Int = 0
    var cant: Int = 0
    var subT: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            idS = requireArguments().getInt("id")
        }
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val formatter = DecimalFormat("$#,###")
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
            binding.tvCantidadProducto.text = it[idS].cantTotal.toString()
            buttons()
            it[idS].cantTotal = binding.tvCantidadProducto.text.toString().toInt()
            if (it[idS].name.isNullOrEmpty()) {
                binding.tvNameS.isVisible = false
            }
            if (it[idS].material.isNullOrEmpty()) {
                binding.tvMaterialS.isVisible = false
            }
            if (it[idS].small.isNullOrEmpty()) {
                binding.tvDimensionChic.isVisible = false
            }
            if (it[idS].medium.isNullOrEmpty()) {
                binding.tvDimensionMed.isVisible = false
            }
            if (it[idS].big.isNullOrEmpty()) {
                binding.tvDimensionGran.isVisible = false
            }
            if (it[idS].xl.isNullOrEmpty()) {
                binding.tvDimensionXL.isVisible = false
            }
            if (it[idS].amount.isNullOrEmpty()) {
                binding.tvCantidadS.isVisible = false
            }
            if (it[idS].price.toString().isEmpty()) {
                binding.tvPrecioS.isVisible = false
            }

            binding.btnAgregarCarrito.setOnClickListener { _ ->
                if (!it[idS].carrito) {
                    it[idS].carrito = true
                    it[idS].cantTotal = binding.tvCantidadProducto.text.toString().toInt()
                    it[idS].total = it[idS].price?.times(it[idS].cantTotal) ?: it[idS].total

                    subT = it[idS].total
                    if (it[idS].cantTotal != 0) {
                        viewModel.updateCarrito(it[idS])
                        Toast.makeText(context, "Se Agregó al Carrito con Éxito",
                                Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Sin agregar, cantidad en cero (0)",
                                Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun buttons() {
        binding.btnMas.setOnClickListener {
            cant++
            binding.tvCantidadProducto.text = cant.toString()
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
}