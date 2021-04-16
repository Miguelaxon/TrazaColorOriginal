package com.example.trazacolor.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trazacolor.R
import com.example.trazacolor.databinding.FragmentFirstBinding
import com.example.trazacolor.ui.adapter.Adapter
import com.example.trazacolor.viewmodel.ViewModel

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private val viewModel: ViewModel by activityViewModels()
    val bundle = Bundle()

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
                bundle.putInt("id", it.id)
                bundle.putString("categoria", it.category)
                viewModel.listAll
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
            }
        })
    }
}