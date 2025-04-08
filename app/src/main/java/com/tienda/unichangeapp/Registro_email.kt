package com.tienda.unichangeapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.tienda.unichangeapp.databinding.ActivityRegistroEmailBinding
import org.intellij.lang.annotations.Pattern

class Registro_email : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroEmailBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: android.app.ProgressDialog // Usa tu clase si es personalizada

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistroEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        // Configurar ProgressDialog
        progressDialog = android.app.ProgressDialog(this)
        progressDialog.setTitle("Espere por favor")
        progressDialog.setCanceledOnTouchOutside(false)
        binding.BtnRegistrar.setOnClickListener{
            validarInfo()
        }

        // Ajuste de barras del sistema (opcional)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private var email = ""
    private var password = ""
    private var repetirP = ""

    private fun validarInfo() {
        email = binding.EtEmail.text.toString().trim()
        password = binding.EtPassword.text.toString().trim()
        repetirP = binding.EtRPassword.text.toString().trim()

     if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
        binding.EtEmail.error="Email Invalido"
        binding.EtEmail.requestFocus()}
        else if(email.isEmpty()){
            binding.EtEmail.error="El campo Email esta vacio"
         binding.EtEmail.requestFocus()
        } else if(password.isEmpty()){

            binding.EtPassword.error="Agregue contraseña"
         binding.EtPassword.requestFocus()
        } else if (repetirP.isEmpty()){
            binding.EtRPassword.error="Repetir contaseña"

        }else if (password !=repetirP){
            binding.EtRPassword.error="lAS CONTRASEÑA MNO COINCIDE"
         binding.EtRPassword.requestFocus()

        }


     }
    private fun registrarUser(){
        progressDialog.setMessage("Creando cuenta")
        progressDialog.show()
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {

           llenarInfo()
            }
            .addOnFailureListener{
                e-> progressDialog.dismiss()
                Toast.makeText(this, "no se registro ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
    private fun llenarInfo(){
     progressDialog.setMessage(" Guardando Informacion")
        val tiempo= Constantes.obtenerTiempo()
        val emailuser=firebaseAuth.currentUser!!.email
        val  uiuser= firebaseAuth.uid

        val  hashMap=HashMap<String,Any>()
        hashMap["nombres"]=""
        hashMap["codigo"]=""
        hashMap["telefono"]=""
        hashMap["urlperfi"]=""
        hashMap["escribiendo"]=""
        hashMap["tiempo"]=tiempo
        hashMap["online"]=true
        hashMap["emaul"]="${emailuser}"
        hashMap["uid"]="${uiuser}"
        hashMap["fecha_na"]=""

        val ref = FirebaseDatabase.getInstance().getReference("usuarios")
        ref.child(uiuser!!).setValue(hashMap).addOnSuccessListener {
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        }
            .addOnFailureListener{
                    e-> progressDialog.dismiss()
                Toast.makeText(this, "no se registro debido a ${e.message}", Toast.LENGTH_SHORT).show()
            }






    }


    }






