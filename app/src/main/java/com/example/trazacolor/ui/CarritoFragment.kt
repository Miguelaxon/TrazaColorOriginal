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
import com.example.trazacolor.databinding.FragmentCarritoComprasBinding
import com.example.trazacolor.ui.adapter.AdapterCarrito
import com.example.trazacolor.viewmodel.ViewModel
import java.text.DecimalFormat

class CarritoFragment : Fragment() {

    private lateinit var binding: FragmentCarritoComprasBinding
    private val viewModel: ViewModel by activityViewModels()
    var total: Int = 0
    var idS: Int = 0
    var contador: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
        activity?.actionBar?.title = "Carrito de Compras"
        if (arguments != null) {
            idS = requireArguments().getInt("id")
            total = requireArguments().getInt("total")
        }
    }

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
                adapter.update(it)
                adapter.selected().observe(viewLifecycleOwner, { it2 ->
                    it2?.let {
                        if (it2.carrito) contador ++
                    }
                })
            }
        })
        Toast.makeText(context, "Carrito: $contador", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_carritoCompras ->
                Toast.makeText(context, "Gracias por su visita.", Toast.LENGTH_SHORT).show()
            R.id.action_exit -> {
                Toast.makeText(context, "Gracias por su visita.", Toast.LENGTH_SHORT).show()
                activity?.finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}