package com.example.trazacolor.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ResponseMaster(@SerializedName("item") val item: List<ClassItem>)

@Entity(tableName = "table_master")
data class Item(@PrimaryKey @SerializedName("id")
                val id: Int,
                @SerializedName("name")
                val name: String?,
                @SerializedName("amount")
                val amount: String?,
                @SerializedName("material")
                val material: String?,
                @SerializedName("small")
                val small: String?,
                @SerializedName("medium")
                val medium: String?,
                @SerializedName("big")
                val big: String?,
                @SerializedName("xl")
                val xl: String?,
                @SerializedName("price")
                val price: Int?,
                @SerializedName("urlImage")
                val urlImage: String?,
                @SerializedName("category")
                val category: String?, var carrito: Boolean, var cantTotal: Int, var total: Int)

data class ClassItem(val id: Int, val name: String, val amount: String, val material: String,
                     val small: String, val medium: String, val big: String, val xl: String,
                     val price: Int, val urlImage: String, val category: String,
                     var carrito: Boolean, var cantTotal: Int, var total: Int)

class TotalCarro() {
    var total: Int = 0
}