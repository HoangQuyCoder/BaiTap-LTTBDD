package com.example.librarymanagementsystem

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.librarymanagementsystem.databinding.ItemBorrowedBinding
import com.example.librarymanagementsystem.models.BorrowedBook

class BorrowedBookAdapter(private val borrowedBooks: List<BorrowedBook>) :
    RecyclerView.Adapter<BorrowedBookAdapter.BorrowedBookViewHolder>() {

    inner class BorrowedBookViewHolder(private val binding: ItemBorrowedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(borrowedBook: BorrowedBook) {
            binding.tvUserName.text = "Người mượn: ${borrowedBook.borrowerName}"
            binding.tvBorrowedBooks.text = "Tên sách: " + borrowedBook.books.joinToString { it.name }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BorrowedBookViewHolder {
        val binding = ItemBorrowedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BorrowedBookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BorrowedBookViewHolder, position: Int) {
        holder.bind(borrowedBooks[position])
    }

    override fun getItemCount(): Int = borrowedBooks.size
}

