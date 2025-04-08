package com.tienda.unichangeapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.tienda.unichangeapp.databinding.ActivityOpcionesloginBinding
import com.tienda.unichangeapp.login.LoginEmail


class opcioneslogin : AppCompatActivity() {
    private lateinit var binding: ActivityOpcionesloginBinding
    private  lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpcionesloginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth=FirebaseAuth.getInstance()
        comprobarsec()

        binding.Ingresarconemail.setOnClickListener{
            startActivity(Intent(this@opcioneslogin,LoginEmail::class.java))
        }
    }
    private fun comprobarsec(){
        if(firebaseAuth.currentUser!=null){
startActivity(Intent(this,MainActivity::class.java))
            finishAffinity()

        }
    }
}
