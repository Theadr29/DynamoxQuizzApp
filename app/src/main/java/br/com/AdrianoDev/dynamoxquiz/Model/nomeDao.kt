package br.com.AdrianoDev.dynamoxquiz.Model

import androidx.room.*

@Dao

interface nomeDao {

    @Insert
    fun insert (lista: nome)

    @Query("SELECT * FROM nome WHERE type = :type")
    fun getRegisterByType(type: String) : List<nome>

    @Query("SELECT * FROM nome")
    fun getALL() : List<nome>



}