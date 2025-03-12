package com.example.librarymanagementsystem.models

data class BorrowedBook(
    val borrowerName: String,
    val books: List<Book> // Danh sách sách đã mượn
)
