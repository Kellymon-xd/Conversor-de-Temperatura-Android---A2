package com.example.conversortemperatura_a2

import java.math.BigDecimal
import java.math.RoundingMode

class ConversionesTemp {

    private val limiteMinimo = -273.15

    fun esValida(temperaturaCelsius: Double?): Boolean {
        return temperaturaCelsius != null && temperaturaCelsius >= limiteMinimo
    }

    fun convertirDesdeCelsius(temperaturaCelsius: Double, opcion: String): Double {
        val resultado = when (opcion) {
            "F" -> (temperaturaCelsius * 1.8) + 32
            "K" -> temperaturaCelsius + 273.15
            "R" -> (temperaturaCelsius + 273.15) * 1.8
            else -> 0.0
        }

        return BigDecimal(resultado)
            .setScale(2, RoundingMode.HALF_UP)
            .toDouble()
    }
}
