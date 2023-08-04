package br.com.AdrianoDev.dynamoxquiz.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class nome(

    @PrimaryKey (autoGenerate = true) var id: Int = 0,

    @ColumnInfo(name = "type") var type: String,

    @ColumnInfo(name = "nomeProduto") var nome: String,


)
