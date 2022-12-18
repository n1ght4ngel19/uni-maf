package dev.leventehadhazi.unimaf.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.leventehadhazi.unimaf.R
import dev.leventehadhazi.unimaf.data.Book
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var bookList = emptyList<Book>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentBook = bookList[position]
        holder.itemView.idTextView.text = currentBook.id.toString()
        holder.itemView.titleTextView.text = currentBook.title
        holder.itemView.authorNameTextView.text = currentBook.authorName
        holder.itemView.publishDateTextView.text = currentBook.publishDate.toString()
        holder.itemView.readCheckBox.isChecked = currentBook.read
    }

    fun setData(books: List<Book>) {
        this.bookList = books
        notifyDataSetChanged()
    }
}