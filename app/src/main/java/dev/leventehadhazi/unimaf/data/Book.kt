package dev.leventehadhazi.unimaf.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val authorName: String,
    val publishDate: Int,
    val read: Boolean
)