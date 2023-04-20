package com.example.studentmanager.FragmentsManager

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.studentmanager.R
import com.example.studentmanager.StudentModel
import com.example.studentmanager.databinding.FragmentAddStudentBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.io.ByteArrayOutputStream
import java.util.Base64

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddStudentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class AddStudentFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentAddStudentBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val let = arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddStudentBinding.inflate(inflater, container, false)
        innitUI()
        return binding.root
    }


    private fun innitUI() {
        binding.btnCheckImageStudent.setOnClickListener {
            addImage()
        }
        binding.btnAddStudent.setOnClickListener {
            addToFireBase()
        }
    }

    private fun addToFireBase() {
        val idStudent = binding.editIdStudent.text.toString().trim()
        //        Base64 image
        val bitmap = (binding.imageStudent.drawable as BitmapDrawable).bitmap
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        val encodedString : String = android.util.Base64.encodeToString(byteArray, 0)

        //        continue
        val nameStudent = binding.editNameStudent.text.toString().trim()
        val birthStudent = binding.editBirthStudent.text.toString().trim()
        val majrorStudent = binding.editMajrorStudent.text.toString().trim()
        val studentData = StudentModel(encodedString, idStudent, nameStudent, birthStudent, majrorStudent)
        FirebaseDatabase.getInstance("https://studentmanager-ae457-default-rtdb.asia-southeast1.firebasedatabase.app")
        val dbRef = Firebase.database
        val myRef = dbRef.getReference("Students")
        myRef.child(idStudent).setValue(studentData)
            .addOnSuccessListener {
                Toast.makeText(activity, "Thêm thành công", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { err ->
                Toast.makeText(activity, "Thêm thất bại" + err, Toast.LENGTH_LONG).show()
            }
    }


    private fun addImage() {
        val i = Intent(Intent.ACTION_PICK)
        i.type = "image/*"
        startActivityForResult(i, IMAGE_REQUSET_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUSET_CODE && resultCode == Activity.RESULT_OK) {
            binding.imageStudent.setImageURI(data?.data)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddStudentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        val IMAGE_REQUSET_CODE = 100
        fun newInstance(param1: String, param2: String) =
            AddStudentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}