package com.example.studentmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.studentmanager.FragmentsManager.AddStudentFragment
import com.example.studentmanager.FragmentsManager.ListFragment
import com.example.studentmanager.FragmentsManager.UpdateStudentsFragment
import com.example.studentmanager.databinding.ActivityManagerBinding

class ManagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityManagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val subList = ListFragment()
        val addList = AddStudentFragment()
        replaceFragment(subList)

        binding.viewMenu.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.listManagerStudents -> replaceFragment(subList)
                R.id.addManagerStudents -> replaceFragment(addList)
            }
            true
        }
    }
    private fun replaceFragment(fragment : Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.apply {
            replace(R.id.frame_layout, fragment)
            commit()
        }
    }
}