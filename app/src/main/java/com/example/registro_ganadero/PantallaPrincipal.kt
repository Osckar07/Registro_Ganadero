package com.example.registro_ganadero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class PantallaPrincipal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal)
    }
    fun agregarganado(view: View){
        startActivity(Intent(this, RegistroGanado::class.java))
    }
}
