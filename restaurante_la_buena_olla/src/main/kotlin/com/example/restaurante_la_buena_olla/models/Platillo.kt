package com.example.restaurante_la_buena_olla.models

data class Platillo(
    var id: Int,
    var nombre: String,
    var precio: Double,
    var descripcion: String,
    var categoria: String,
    var disponible: Boolean,
    var imagen: String
)
