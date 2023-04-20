package com.example.studentmanager


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studentmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intitUI()
    }

    private fun intitUI() {
        binding.btnGoLogin.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
        }
        binding.btnGoRegister.setOnClickListener {
            val i2 = Intent(this, RegisterActivity::class.java)
            startActivity(i2)
            finish()
        }
    }


}