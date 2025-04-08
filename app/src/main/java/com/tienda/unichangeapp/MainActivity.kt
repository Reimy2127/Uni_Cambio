package com.tienda.unichangeapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.tienda.unichangeapp.Fragment.AnuncioFragment
import com.tienda.unichangeapp.Fragment.ChatFragment
import com.tienda.unichangeapp.Fragment.CuentaFragment
import com.tienda.unichangeapp.Fragment.InicoFragment
import com.tienda.unichangeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private  lateinit var firebaseAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        firebaseAuth=FirebaseAuth.getInstance()
        comprobarSesion()

        openInicio()

        binding.bottomnavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.Item_inicio -> {
                    openInicio()
                    true
                }
                R.id.items_chats -> {
                    openChat()
                    true
                }
                R.id.items_anuncios -> {
                    openAnuncios()
                    true
                }
                R.id.items_cuentas -> {
                    openCuenta()
                    true
                }
                else -> false
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun comprobarSesion(){
        if(firebaseAuth.currentUser==null){
            startActivity(Intent(this,opcioneslogin::class.java))
        finishAffinity()
        }
    }

    private fun openInicio() {
        binding.TituloRl.text = "Inicio"
        supportFragmentManager.beginTransaction().apply {
            replace(binding.framenl1.id, InicoFragment(), "InicoFragment")
            commit()
        }
    }

    private fun openChat() {
        binding.TituloRl.text = "Chat"
        supportFragmentManager.beginTransaction().apply {
            replace(binding.framenl1.id, ChatFragment(), "ChatFragment")
            commit()
        }
    }

    private fun openAnuncios() {
        binding.TituloRl.text = "Anuncios"
        supportFragmentManager.beginTransaction().apply {
            replace(binding.framenl1.id, AnuncioFragment(), "AnuncioFragment")
            commit()
        }
    }

    private fun openCuenta() {
        binding.TituloRl.text = "Cuenta"
        supportFragmentManager.beginTransaction().apply {
            replace(binding.framenl1.id, CuentaFragment(), "CuentaFragment")
            commit()
        }
    }
}
