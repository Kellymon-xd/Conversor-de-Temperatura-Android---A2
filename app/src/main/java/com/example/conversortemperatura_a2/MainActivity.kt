/** Integrantes:
 * KELLY BEITIA, 8-1023-152 (COORDINADORA)
 * JORGE SARMIENTO, 3-757-1758
 * LEONARDO CASTRO, 8-1032-1264
 * MARIAM HARRIS 1-756-2331
 */

package com.example.conversortemperatura_a2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btnF: Button
    private lateinit var btnK: Button
    private lateinit var btnR: Button
    private lateinit var tvResultado: TextView
    private lateinit var inputTemp: EditText

    private val conversionesTemp = ConversionesTemp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvResultado = findViewById(R.id.tvResultado)
        inputTemp = findViewById(R.id.inputTemp)

        btnF = findViewById(R.id.btnF)
        btnK = findViewById(R.id.btnK)
        btnR = findViewById(R.id.btnR)

        btnF.isEnabled = false
        btnK.isEnabled = false
        btnR.isEnabled = false

        inputTemp.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val value = s.toString().toDoubleOrNull()
                val isValid = conversionesTemp.esValida(value)

                btnF.isEnabled = isValid
                btnK.isEnabled = isValid
                btnR.isEnabled = isValid

                if (value == null) {
                    tvResultado.text = ""
                } else if (!isValid) {
                    tvResultado.text = "⚠ Valor por debajo del mínimo (-273.15)"
                } else {
                    tvResultado.text = ""
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        btnF.setOnClickListener {
            val celsius = inputTemp.text.toString().toDoubleOrNull() ?: return@setOnClickListener
            tvResultado.text = "Resultado: ${conversionesTemp.convertirDesdeCelsius(celsius, "F")}°F"
        }

        btnK.setOnClickListener {
            val celsius = inputTemp.text.toString().toDoubleOrNull() ?: return@setOnClickListener
            tvResultado.text = "Resultado: ${conversionesTemp.convertirDesdeCelsius(celsius, "K")}°K"
        }

        btnR.setOnClickListener {
            val celsius = inputTemp.text.toString().toDoubleOrNull() ?: return@setOnClickListener
            tvResultado.text = "Resultado: ${conversionesTemp.convertirDesdeCelsius(celsius, "R")}°R"
        }
    }
}
