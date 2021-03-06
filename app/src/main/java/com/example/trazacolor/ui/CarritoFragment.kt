package com.example.trazacolor.ui

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trazacolor.R
import com.example.trazacolor.databinding.FragmentCarritoBinding
import com.example.trazacolor.local.Item
import com.example.trazacolor.local.TotalCarro
import com.example.trazacolor.ui.adapter.AdapterCarrito
import com.example.trazacolor.viewmodel.ViewModel
import java.text.DecimalFormat
import kotlin.system.exitProcess

class CarritoFragment : Fragment() {

    private lateinit var binding: FragmentCarritoBinding
    private val viewModel: ViewModel by activityViewModels()
    var total: Int = 0
    var totalCarro: Int = 0
    var totalTotal: Int = 0
    lateinit var item: Item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            totalCarro = requireArguments().getInt("total")
        }
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
        binding.rvCarrito.adapter = adapter
        binding.rvCarrito.layoutManager = GridLayoutManager(context, 2)
        binding.tvTitleCarrito.text = getString(R.string.carro_de_compras)

        viewModel.returnCarrito().observe(viewLifecycleOwner, {
            it?.let {
                if (it.isNotEmpty()) {
                    adapter.update(it)
                    total = it.sumOf { sum ->
                        sum.total
                    }
                } else {
                    showDialog()
                }
            }

            totalTotal = total + totalCarro
            binding.tvTotalCarro.text = getString(R.string.totalCarrito, formatter.format(totalTotal))
            Log.d("total2", "$totalCarro")
            binding.btnEmpty.setOnClickListener { _ ->
                if (it.isNotEmpty()) {
                    it.forEach { carr ->
                        if (carr.carrito) {
                            carr.carrito = false
                            carr.cantTotal = 0
                            carr.total = 0
                            viewModel.updateCarrito(carr)
                            Toast.makeText(context, "Carrito vac??o",
                                    Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        })
    }

    fun showDialog() {
        val dialogBuilder = context?.let { AlertDialog.Builder(it) }
        dialogBuilder?.setTitle("Carrito Vac??o")
        dialogBuilder?.setPositiveButton("Done") { _: DialogInterface, _: Int ->
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
        dialogBuilder?.setCancelable(false)
        dialogBuilder?.show()
    }
}