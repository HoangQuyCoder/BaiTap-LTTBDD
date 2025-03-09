package com.example.librarymanagementsystem

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.librarymanagementsystem.databinding.ActivityMainBinding
import com.example.librarymanagementsystem.databinding.DialogAddBookBinding
import com.example.librarymanagementsystem.models.Book
import com.example.librarymanagementsystem.models.Employee

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bookAdapter: BookAdapter

    private val bookList = mutableListOf(
        Book("Lập trình Android"),
        Book("Kotlin cơ bản"),
        Book("Java nâng cao"),
        Book("Phát triển ứng dụng di động"),
        Book("Cấu trúc dữ liệu & Giải thuật")
    )

    private val employeeList = listOf(
        Employee("Nguyen Van A", 20),
        Employee("Tran Thi B", 21),
        Employee("Le Van C", 22),
        Employee("Pham Thi D", 23)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cài đặt RecyclerView
        binding.rvBooks.layoutManager = LinearLayoutManager(this)
        // Khởi tạo RecyclerView và Adapter
        bookAdapter = BookAdapter(bookList)
        binding.rvBooks.adapter = bookAdapter

        // Xử lý sự kiện khi nhấn nút "Đổi"
        binding.btnChange.setOnClickListener {
            showChangeEmployeeDialog()
        }

        // Thiết lập sự kiện cho nút Thêm
        binding.btnAdd.setOnClickListener {
            showAddBookDialog()
        }
    }

    private fun showChangeEmployeeDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Chọn Nhân Viên")

        // Lấy danh sách tên nhân viên để hiển thị
        val employeeNames = employeeList.map { it.name }.toTypedArray()

        builder.setItems(employeeNames) { _, which ->
            binding.etEmployeeName.text =
                employeeList[which].name // Cập nhật TextView với tên nhân viên
        }

        builder.setNegativeButton("Hủy") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showAddBookDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Thêm Sách Mới")

        // Sử dụng ViewBinding cho hộp thoại
        val dialogBinding = DialogAddBookBinding.inflate(layoutInflater)
        builder.setView(dialogBinding.root)

        // Truy cập các view từ binding
        val etBookTitle = dialogBinding.etBookTitle
        val cbIsChecked = dialogBinding.cbIsChecked

        builder.setPositiveButton("Thêm") { dialog, _ ->
            val bookTitle = etBookTitle.text.toString()
            val isChecked = cbIsChecked.isChecked

            val newBook = Book(bookTitle, isChecked)
            bookList.add(newBook)
            bookAdapter.notifyDataSetChanged()

            dialog.dismiss()
        }

        builder.setNegativeButton("Hủy") { dialog, _ ->
            dialog.dismiss()
        }

        builder.create().show()
    }
}
