package com.example.studentmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.studentmanager.databinding.ActivityLoginBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        loginLogic()
    }

    private fun initUI() {
        binding.backToWelcome.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }
    private fun loginLogic() {
        binding.btnLogin.setOnClickListener {
            val textUser = binding.editUser.text.toString().trim()
            val passUser = binding.editPass.text.toString().trim()
            if (textUser != "" && passUser != "") {
                db.collection("users").whereEqualTo("user", textUser).get()
                    .addOnSuccessListener { document ->
                        if(!document.isEmpty) {
                            val doc = document.first()
                            val docPassword = doc.getString("pass")
                            if (docPassword == passUser) {
                                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT)
                                val i = Intent(this, ManagerActivity::class.java)
                                startActivity(i)
                                finish()
                            }
                        }
                        else {
                            Toast.makeText(this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
            else {
                Toast.makeText(this, "Vui lòng nhập đầy đủ các trường thông tin", Toast.LENGTH_SHORT).show()
            }
        }
    }

}