package com.example.conversortemperatura_a2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import java.lang.Math.round
import java.math.BigDecimal
import java.math.RoundingMode

/* Grupo 4
Kelly Beitia 8-1023-152
Mariam Harris 1-756-2331
Jorge Sarmiento 3-757-1758
Leonardo Castro 8-1032-1264
 */
class MainActivity : AppCompatActivity() {
    private lateinit var btnF: Button
    private lateinit var btnK: Button
    private lateinit var btnR: Button
    private lateinit var tvResultado: TextView
    private lateinit var inputTemp: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tvResultado = findViewById(R.id.tvResultado);
        inputTemp = findViewById(R.id.inputTemp);

        btnF = findViewById(R.id.btnF)
        btnK = findViewById(R.id.btnK)
        btnR = findViewById(R.id.btnR)

        btnF.isEnabled = false
        btnK.isEnabled = false
        btnR.isEnabled = false

        inputTemp.addTextChangedListener(object : android.text.TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val value = s.toString().toDoubleOrNull()

                val inRange = value != null && value > -273.15
                val isValid = inRange

                btnF.isEnabled = isValid
                btnK.isEnabled = isValid
                btnR.isEnabled = isValid

                if (value == null) {
                    tvResultado.text = ""
                } else if (!inRange) {
                    tvResultado.text = "⚠ Valor por debajo del mínimo (-273.15)"
                } else {
                    tvResultado.text = ""
                }
            }

            override fun afterTextChanged(s: android.text.Editable?) {}
        })

        btnF.setOnClickListener {
            tvResultado.text = "Resultado: ${conversorTemperatura("F")}°F"
        }

        btnK.setOnClickListener {
            tvResultado.text = "Resultado: ${conversorTemperatura("K")}°K"
        }

        btnR.setOnClickListener {
            tvResultado.text = "Resultado: ${conversorTemperatura("R")}°R"
        }
    }

    fun conversorTemperatura(opcion: String): Double {
        val celsius = inputTemp.text.toString().toDoubleOrNull() ?: 0.0
        val temp = when (opcion) {
            "F" -> (celsius * 1.8) + 32
            "K" -> celsius + 273.15
            "R" -> (celsius + 273.15) * 1.8
            else -> 0.0
        }
        return BigDecimal(temp)
            .setScale(2, RoundingMode.HALF_UP)
            .toDouble()
    }
}