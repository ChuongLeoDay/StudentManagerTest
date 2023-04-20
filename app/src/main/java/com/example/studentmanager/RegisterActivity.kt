package com.example.studentmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.studentmanager.databinding.ActivityRegisterBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private  var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        saveUserResgister()
    }

    private fun initUI() {
        binding.backToWelcome.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }
    private fun saveUserResgister() {
        binding.btnRegister.setOnClickListener {
            val editUser = binding.editUser.text.toString().trim()
            val editPass = binding.editPass.text.toString().trim()
            val editRePass = binding.editRePass.text.toString().trim()
            if (editUser != "" && editPass != "" && editRePass != "") {
                // Create a new user with a first, middle, and last name
                if(editPass.equals(editRePass) === true) {
                    db.collection("users").whereEqualTo("user", editUser)
                        .get()
                        .addOnSuccessListener { doc ->
                            if (doc.isEmpty) {
                                val user = hashMapOf(
                                    "user" to editUser,
                                    "pass" to editPass,
                                )

                                db.collection("users").add(user)
                                    .addOnSuccessListener {
                                        Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_LONG).show()
                                        binding.editUser.text.clear()
                                        binding.editPass.text.clear()
                                        binding.editRePass.text.clear()
                                    }
                                    .addOnFailureListener {
                                        Toast.makeText(this, "Đăng ký thất bại", Toast.LENGTH_LONG).show()

                                    }
                            }
                            else {
                                Toast.makeText(this, "Tài khoản đã được đăng ký", Toast.LENGTH_LONG).show()
                            }

                        }

                }
                else {
                    Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_LONG).show()
                }
            }
            else {
                Toast.makeText(this, "Vui lòng nhập đầy đủ trường thông tin!!!", Toast.LENGTH_SHORT).show()
            }

        }
    }

}