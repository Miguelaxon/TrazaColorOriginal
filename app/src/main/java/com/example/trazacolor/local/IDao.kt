package com.example.trazacolor.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface IDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Item>)

    @Query("SELECT * FROM table_master")
    fun getAll(): LiveData<List<Item>>

    @Query("SELECT * FROM table_master WHERE carrito = 1")
    fun getCarrito(): LiveData<List<Item>>

    @Update
    suspend fun updateCarrito(item: Item)
}