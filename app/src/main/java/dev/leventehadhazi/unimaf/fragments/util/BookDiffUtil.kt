package dev.leventehadhazi.unimaf.fragments.util

import androidx.recyclerview.widget.DiffUtil
import dev.leventehadhazi.unimaf.model.Book

class BookDiffUtil(
    private val oldList: List<Book>,
    private val newList: List<Book>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].publishDate == newList[newItemPosition].publishDate
                && oldList[oldItemPosition].read == newList[newItemPosition].read
                && oldList[oldItemPosition].title == newList[newItemPosition].title
                && oldList[oldItemPosition].authorName == newList[newItemPosition].authorName
    }
}