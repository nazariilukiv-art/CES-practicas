package com.example.calculadoraapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadoraapp.databinding.ActivityMainBinding
import kotlin.math.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var expresionActual: String = "0"
    private var limpiarPantallaAlSiguienteNumero: Boolean = true

    private var operando1: Double = 0.0

    private var operador: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (savedInstanceState != null) {
            expresionActual = savedInstanceState.getString("KEY_EXPRESION", "0")
            operando1 = savedInstanceState.getDouble("KEY_OPERANDO1", 0.0)
            operador = savedInstanceState.getString("KEY_OPERADOR", "")
            limpiarPantallaAlSiguienteNumero = savedInstanceState.getBoolean("KEY_LIMPIAR", true)
        }

        binding.textViewResultado.text = expresionActual
        acciones()
    }

    private fun acciones() {


        binding.botonC.setOnClickListener {
            expresionActual = "0"
            limpiarPantallaAlSiguienteNumero = true
            operando1 = 0.0
            operador = ""
            binding.textViewResultado.text = expresionActual
        }

        binding.boton0.setOnClickListener { numeroPulsado("0") }
        binding.boton1.setOnClickListener { numeroPulsado("1") }
        binding.boton2.setOnClickListener { numeroPulsado("2") }
        binding.boton3.setOnClickListener { numeroPulsado("3") }
        binding.boton4.setOnClickListener { numeroPulsado("4") }
        binding.boton5.setOnClickListener { numeroPulsado("5") }
        binding.boton6.setOnClickListener { numeroPulsado("6") }
        binding.boton7.setOnClickListener { numeroPulsado("7") }
        binding.boton8.setOnClickListener { numeroPulsado("8") }
        binding.boton9.setOnClickListener { numeroPulsado("9") }

        binding.botonPunto.setOnClickListener { puntoPulsado() }

        binding.botonSumar.setOnClickListener { operadorPulsado("+") }
        binding.botonRestar.setOnClickListener { operadorPulsado("-") }
        binding.botonMultiplicar.setOnClickListener { operadorPulsado("*") }
        binding.botonDividir.setOnClickListener { operadorPulsado("/") }

        binding.botonIgual.setOnClickListener { calcularResultado() }

        binding.botonSin?.setOnClickListener { operacionUnariaPulsada("sin") }
        binding.botonCos?.setOnClickListener { operacionUnariaPulsada("cos") }
        binding.botonTan?.setOnClickListener { operacionUnariaPulsada("tan") }
        binding.botonLog?.setOnClickListener { operacionUnariaPulsada("log") }
        binding.botonLn?.setOnClickListener { operacionUnariaPulsada("ln") }
        binding.botonSqrt?.setOnClickListener { operacionUnariaPulsada("sqrt") }

        binding.botonPow?.setOnClickListener { operadorPulsado("pow") }

        binding.botonPi?.setOnClickListener {
            expresionActual = Math.PI.toString() // Math.PI es 3.14159...
            limpiarPantallaAlSiguienteNumero = false
            binding.textViewResultado.text = expresionActual
        }

        binding.botonBackspace.setOnClickListener { backspacePulsado() }
    }

    private fun puntoPulsado() {

        if (!expresionActual.contains(".")){
            if (limpiarPantallaAlSiguienteNumero){
                expresionActual = "0."
                limpiarPantallaAlSiguienteNumero = false
            } else {
                expresionActual += "."
            }
            binding.textViewResultado.text = expresionActual
        }
    }

    private fun numeroPulsado(digito: String) {

        if (limpiarPantallaAlSiguienteNumero){
            expresionActual = digito
            limpiarPantallaAlSiguienteNumero = false
        } else{
            expresionActual += digito
        }
        binding.textViewResultado.text = expresionActual
    }

    private fun operadorPulsado(op: String) {

        try {
            operando1 = expresionActual.toDouble()
        } catch (e: Exception) {
            operando1 = 0.0
        }
        operador = op
        limpiarPantallaAlSiguienteNumero = true
    }

    private fun calcularResultado() {

        val operando2: Double
        try {
            operando2 = expresionActual.toDouble()
        } catch (e: Exception) {
            return
        }

        if (operador.isEmpty()){
            return
        }

        var resultado = 0.0

        when (operador){
            "+" -> { resultado = operando1 + operando2 }
            "-" -> { resultado = operando1 - operando2 }
            "*" -> { resultado = operando1 * operando2 }
            "/" -> {
                if (operando2 == 0.0) {
                    binding.textViewResultado.text = "Error"
                    expresionActual = "0"
                    limpiarPantallaAlSiguienteNumero = true
                    operador = ""
                    return
                } else {
                    resultado = operando1 / operando2
                }
            }
            "pow" -> { resultado = operando1.pow(operando2) }
        }
        expresionActual = resultado.toString()
        binding.textViewResultado.text = expresionActual

        limpiarPantallaAlSiguienteNumero = true
        operador =""

    }

    private fun operacionUnariaPulsada (op: String) {

        val numero: Double

        try {
            numero = expresionActual.toDouble()
        } catch (e: Exception) {
            return
        }

        var resultado = 0.0

        val numeroEnRadianes = Math.toRadians(numero)

        when (op){

            "sin" -> resultado = sin(numeroEnRadianes)
            "cos" -> resultado = cos(numeroEnRadianes)
            "tan" -> resultado = tan(numeroEnRadianes)
            "log" -> resultado = log10(numero)
            "ln" -> resultado = ln(numero)
            "sqrt" -> resultado = sqrt(numero)
        }

        expresionActual = resultado.toString()
        binding.textViewResultado.text = expresionActual
        limpiarPantallaAlSiguienteNumero = true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState) // No olvides llamar a "super"

        // "outState" es la "caja" (el 'paquete').
        // Vamos a meter nuestras 4 variables de "memoria" dentro.
        // Usamos "etiquetas" (Keys) para saber qué es cada cosa.
        outState.putString("KEY_EXPRESION", expresionActual)
        outState.putDouble("KEY_OPERANDO1", operando1)
        outState.putString("KEY_OPERADOR", operador)
        outState.putBoolean("KEY_LIMPIAR", limpiarPantallaAlSiguienteNumero)
    }

    private fun backspacePulsado() {
        if (expresionActual.length > 1) {
            // Si es "123", lo cortamos a "12"
            expresionActual = expresionActual.dropLast(1)

        } else if (expresionActual.length == 1 && expresionActual != "0") {
            // Si es "7", lo reiniciamos a "0"
            expresionActual = "0"
            limpiarPantallaAlSiguienteNumero = true

        } else if (expresionActual == "0") {
            // Si ya es "0", no hacemos nada
        }
        binding.textViewResultado.text = expresionActual
    }
}
































