package com.tienda.unichangeapp.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tienda.unichangeapp.Registro_email
import com.tienda.unichangeapp.databinding.ActivityLoginEmailBinding

class LoginEmail : AppCompatActivity() {

    private lateinit var binding: ActivityLoginEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.TxtRegistrarme.setOnClickListener {
            // Ir a la pantalla de registro
            val intent = Intent(this@LoginEmail, Registro_email::class.java)
            startActivity(intent)
        }
    }
}

