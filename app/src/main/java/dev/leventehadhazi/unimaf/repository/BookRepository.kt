package dev.leventehadhazi.unimaf.repository

import androidx.lifecycle.LiveData
import dev.leventehadhazi.unimaf.data.BookDao
import dev.leventehadhazi.unimaf.model.Book

class BookRepository(private val bookDao: BookDao) {
    val readAllData: LiveData<List<Book>> = bookDao.readAllData()

    suspend fun addBook(book: Book) {
        bookDao.addBook(book)
    }

    suspend fun deleteBook(book: Book) {
        bookDao.deleteBook(book)
    }

    suspend fun deleteAllBooks() {
        bookDao.deleteAllBooks()
    }

    suspend fun updateBook(book: Book) {
        bookDao.updateBook(book)
    }
}