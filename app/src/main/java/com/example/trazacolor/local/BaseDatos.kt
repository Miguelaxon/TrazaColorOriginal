package com.example.trazacolor.local

import android.content.Context
import androidx.room.Database
import androidx.room.*

@Database(entities = [Item::class], version = 1)
abstract class BaseDatos: RoomDatabase() {
    abstract fun getIDAO(): IDao
    companion object{
        @Volatile
        private var INSTANCE: BaseDatos? = null
        fun getDataBase(context: Context): BaseDatos {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    BaseDatos::class.java, "basedatosTC").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}