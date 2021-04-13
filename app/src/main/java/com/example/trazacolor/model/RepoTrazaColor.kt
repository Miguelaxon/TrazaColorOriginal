package com.example.trazacolor.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.trazacolor.local.*
import com.example.trazacolor.remote.RetrofitClient

class RepoTrazaColor(private val iDao: IDao) {
    val listAll: LiveData<List<Item>> = iDao.getAll()

    fun converter(listI: List<ClassItem>): List<Item> {
        val list: MutableList<Item> = mutableListOf()
        listI.map {
            list.add(Item(id = it.id, name = it.name, amount = it.amount, material = it.material,
                    small = it.small, medium = it.medium, big = it.big, xl = it.xl, price = it.price,
                    urlImage = it.urlImage, category = it.category, carrito = false,
                    cantTotal = it.cantTotal, total = it.total))
        }
        return list
    }

    suspend fun getFetchCorountines() {
        try {
            val response = RetrofitClient.getApiClient().getFetchIndividuales()
            when (response.isSuccessful) {
                true -> response.body()?.let {
                    iDao.insertAll(converter(it.item))
                }
                false -> Log.d("ERROR", "${response.code()}: ${response.errorBody()}")
            }
        } catch (t: Throwable){
            Log.d("Error Coroutine", t.message.toString())
        }
    }

    fun getAll(): LiveData<List<Item>> = iDao.getAll()

    fun getCarrito(): LiveData<List<Item>> = iDao.getCarrito()

    suspend fun updateCarrito(item: Item) = iDao.updateCarrito(item)
}