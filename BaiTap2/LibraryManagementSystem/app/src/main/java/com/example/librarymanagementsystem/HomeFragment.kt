package com.example.librarymanagementsystem

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.librarymanagementsystem.databinding.DialogAddBookBinding
import com.example.librarymanagementsystem.databinding.FragmentHomeBinding
import com.example.librarymanagementsystem.models.Book
import com.example.librarymanagementsystem.models.Employee

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!  // Sử dụng binding an toàn
    private lateinit var bookAdapter: BookAdapter

    private val employeeList = listOf(
        Employee("Nguyen Van A", "20"),
        Employee("Tran Thi B", "21"),
        Employee("Le Van C", "22"),
        Employee("Pham Thi D", "23")
    )

    private val bookList = mutableListOf(
        Book("Lập trình Android"),
        Book("Kotlin cơ bản"),
        Book("Java nâng cao"),
        Book("Phát triển ứng dụng di động"),
        Book("Cấu trúc dữ liệu & Giải thuật")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Thiết lập RecyclerView
        binding.rvBooks.layoutManager = LinearLayoutManager(requireContext())
        binding.rvBooks.adapter = BookAdapter(bookList)

        // Xử lý sự kiện nút "Đổi"
        binding.btnChange.setOnClickListener {
            showChangeEmployeeDialog()
        }

        // Xử lý sự kiện nút "Thêm"
        binding.btnAdd.setOnClickListener {
            showAddBookDialog()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null  // Giải phóng binding để tránh memory leak
    }

    private fun showChangeEmployeeDialog() {
        val builder = AlertDialog.Builder(requireContext())
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
        val builder = AlertDialog.Builder(requireContext())
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