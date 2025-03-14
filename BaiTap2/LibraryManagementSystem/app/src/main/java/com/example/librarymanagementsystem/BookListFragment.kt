package com.example.librarymanagementsystem

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.librarymanagementsystem.databinding.FragmentBookListBinding
import com.example.librarymanagementsystem.models.Book
import com.example.librarymanagementsystem.models.BorrowedBook

class BookListFragment : Fragment() {

    private var _binding: FragmentBookListBinding? = null
    private val binding get() = _binding!!

    private lateinit var bookAdapter: BookAdapter
    private lateinit var borrowedAdapter: BorrowedBookAdapter

    private val bookList = mutableListOf(
        Book("Lập trình Android"),
        Book("Kotlin cơ bản"),
        Book("Java nâng cao"),
        Book("Phát triển ứng dụng di động"),
        Book("Cấu trúc dữ liệu & Giải thuật")
    )

    private val borrowedBooks = mutableListOf<BorrowedBook>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookAdapter = BookAdapter(bookList)
        borrowedAdapter = BorrowedBookAdapter(borrowedBooks)

        binding.rvBooks.layoutManager = LinearLayoutManager(requireContext())
        binding.rvBooks.adapter = bookAdapter


        binding.rvBorrowedBooks.layoutManager = LinearLayoutManager(requireContext())
        binding.rvBorrowedBooks.adapter = borrowedAdapter

        binding.btnAdd.setOnClickListener {
            showBorrowDialog()
        }
    }

    private fun showBorrowDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_borrow, null)
        val etUserName = dialogView.findViewById<EditText>(R.id.etUserName)

        AlertDialog.Builder(requireContext())
            .setTitle("Nhập tên người mượn")
            .setView(dialogView)
            .setPositiveButton("Xác nhận") { _, _ ->
                val userName = etUserName.text.toString().trim()
                if (userName.isNotEmpty()) {
                    processBorrowing(userName)
                } else {
                    Toast.makeText(requireContext(), "Vui lòng nhập tên!", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Hủy", null)
            .show()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun processBorrowing(userName: String) {
        val selectedBooks = bookList.filter { it.isChecked }
        if (selectedBooks.isNotEmpty()) {
            borrowedBooks.add(BorrowedBook(userName, selectedBooks))
            borrowedAdapter.notifyDataSetChanged()
        } else {
            Toast.makeText(requireContext(), "Chưa chọn sách nào!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}