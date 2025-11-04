package com.example.el_buen_cafe

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ElBuenCafeApplication

fun main(args: Array<String>) {
	runApplication<ElBuenCafeApplication>(*args)
    println("Ejecutando el buen café...")
}
