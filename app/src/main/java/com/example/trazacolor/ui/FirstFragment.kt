package com.example.trazacolor.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trazacolor.R
import com.example.trazacolor.databinding.FragmentFirstBinding
import com.example.trazacolor.ui.adapter.Adapter
import com.example.trazacolor.viewmodel.ViewModel

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = Adapter()
        binding.rv.adapter = adapter
        binding.rv.layoutManager = GridLayoutManager(context, 1)


        viewModel.selected().observe(viewLifecycleOwner, {
            it?.let {
                adapter.update(it)
            }
        })

        adapter.selected().observe(viewLifecycleOwner, {
            it?.let {
                val bundle = Bundle()
                bundle.putInt("id", it.id)
                bundle.putString("categoria", it.category)
                viewModel.listAll
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_carritoCompras ->
                findNavController().navigate(R.id.action_FirstFragment_to_carritoComprasFragment)
        }
        return super.onOptionsItemSelected(item)
    }
}