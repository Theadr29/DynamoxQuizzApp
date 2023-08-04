package br.com.AdrianoDev.dynamoxquiz.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities =[nome::class], version = 4)


abstract class AppDataBase : RoomDatabase () {

    abstract fun nomeDao(): nomeDao

    companion object {

        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase {
           return if (INSTANCE == null) {

                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java, "DynamoxQuiz"
                    ).build()

                }

               INSTANCE as AppDataBase

           } else {

               INSTANCE as AppDataBase

           }

        }

    }
}