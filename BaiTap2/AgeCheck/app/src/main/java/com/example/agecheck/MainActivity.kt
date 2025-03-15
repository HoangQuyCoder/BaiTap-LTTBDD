package com.example.agecheck

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtName = findViewById<EditText>(R.id.edtName)
        val edtAge = findViewById<EditText>(R.id.edtAge)
        val btnCheck = findViewById<Button>(R.id.btnCheck)

        btnCheck.setOnClickListener {
            val name = edtName.text.toString()
            val ageText = edtAge.text.toString()

            if (name.isEmpty() || ageText.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val age = ageText.toIntOrNull()
            if (age == null || age < 0) {
                Toast.makeText(this, "Tuổi không hợp lệ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val message = when {
                age > 65 -> "$name, bạn là người già."
                age in 6..65 -> "$name, bạn là người lớn."
                age in 2..5 -> "$name, bạn là trẻ em."
                age <= 2 -> "$name, bạn là em bé."
                else -> "$name, không xác định được độ tuổi."
            }

            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}