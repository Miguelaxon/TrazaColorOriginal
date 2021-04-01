package com.example.trazacolor.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.trazacolor.local.BaseDatos
import com.example.trazacolor.local.Item
import com.example.trazacolor.model.RepoTrazaColor
import kotlinx.coroutines.launch

class ViewModel(application: Application): AndroidViewModel(application) {
    private val repo: RepoTrazaColor
    val listAll: LiveData<List<Item>>

    fun selected(): LiveData<List<Item>> = repo.getAll()

    init {
        val bd = BaseDatos.getDataBase(application).getIDAO()
        repo = RepoTrazaColor(bd)
        viewModelScope.launch { repo.getFetchCorountines() }
        listAll = repo.listAll
    }

    fun returnCarrito(): LiveData<List<Item>> = repo.getCarrito()

    fun updateCarrito(item: Item) = viewModelScope.launch { repo.updateCarrito(item) }
}