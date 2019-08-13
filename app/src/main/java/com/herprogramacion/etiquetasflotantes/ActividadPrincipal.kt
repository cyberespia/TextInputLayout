package com.herprogramacion.etiquetasflotantes

import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import java.util.regex.Pattern

class ActividadPrincipal : AppCompatActivity() {

    private var tilNombre: TextInputLayout? = null
    private var tilTelefono: TextInputLayout? = null
    private var tilCorreo: TextInputLayout? = null
    private var campoNombre: EditText? = null
    private var campoTelefono: EditText? = null
    private var campoCorreo: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_principal)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Referencias TILs
        tilNombre = findViewById<TextInputLayout>(R.id.til_nombre)
        tilTelefono = findViewById<TextInputLayout>(R.id.til_telefono)
        tilCorreo = findViewById<TextInputLayout>(R.id.til_correo)

        // Referencias ETs
        campoNombre = findViewById<EditText>(R.id.campo_nombre)
        campoTelefono = findViewById<EditText>(R.id.campo_telefono)
        campoCorreo = findViewById<EditText>(R.id.campo_correo)

        campoNombre!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                tilNombre!!.error = null
            }

            override fun afterTextChanged(s: Editable) {

            }
        })

        campoTelefono!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                tilTelefono!!.error = null
            }

            override fun afterTextChanged(s: Editable) {

            }
        })

        campoCorreo!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                esCorreoValido(s.toString())
            }

            override fun afterTextChanged(s: Editable) {

            }
        })

        // Referencia Botón
        val botonAceptar = findViewById<Button>(R.id.boton_aceptar)
        botonAceptar!!.setOnClickListener { validarDatos() }

    }

    private fun esNombreValido(nombre: String): Boolean {
        val patron = Pattern.compile("^[a-zA-Z ]+$")
        if (!patron.matcher(nombre).matches() || nombre.length > 30) {
            tilNombre!!.error = "Nombre inválido"
            return false
        } else {
            tilNombre!!.error = null
        }

        return true
    }

    private fun esTelefonoValido(telefono: String): Boolean {
        if (!Patterns.PHONE.matcher(telefono).matches()) {
            tilTelefono!!.error = "Teléfono inválido"
            return false
        } else {
            tilTelefono!!.error = null
        }

        return true
    }

    private fun esCorreoValido(correo: String): Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            tilCorreo!!.error = "Correo electrónico inválido"
            return false
        } else {
            tilCorreo!!.error = null
        }

        return true
    }

    private fun validarDatos() {
        val nombre = tilNombre!!.editText!!.text.toString()
        val telefono = tilTelefono!!.editText!!.text.toString()
        val correo = tilCorreo!!.editText!!.text.toString()

        val a = esNombreValido(nombre)
        val b = esTelefonoValido(telefono)
        val c = esCorreoValido(correo)

        if (a && b && c) {
            // OK, se pasa a la siguiente acción
            Toast.makeText(this, "Se guarda el registro", Toast.LENGTH_LONG).show()
        }

    }

}
