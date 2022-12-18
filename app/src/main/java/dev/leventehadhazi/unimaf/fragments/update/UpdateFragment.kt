package dev.leventehadhazi.unimaf.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dev.leventehadhazi.unimaf.R
import dev.leventehadhazi.unimaf.model.Book
import dev.leventehadhazi.unimaf.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mBookViewModel: BookViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mBookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)

        view.updateTitle.setText(args.currentBook.title)
        view.updateAuthor.setText(args.currentBook.authorName)
        view.updatePublishDate.setText(args.currentBook.publishDate.toString())
        view.updateReadCheckBox.isChecked = args.currentBook.read

        view.updateButton.setOnClickListener {
            updateBook()
        }

        // Add delete menu
//        setHasOptionsMenu(true)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_delete, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.action_delete) {
                    deleteBook()
                }

                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        super.onViewCreated(view, savedInstanceState)
    }

    private fun updateBook() {
        val title = updateTitle.text.toString()
        val authorName = updateAuthor.text.toString()
        val publishDate = Integer.parseInt(updatePublishDate.text.toString())
        val isRead = updateReadCheckBox.isChecked

        if (isInputValid(title, authorName, updatePublishDate.text)) {
            // Create Book object
            val updatedBook = Book(args.currentBook.id, title, authorName, publishDate, isRead)

            // Update current Book
            mBookViewModel.updateBook(updatedBook)
            Toast.makeText(requireContext(), "Updated book!", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateFragment_to_ListFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isInputValid(bookTitle: String, bookAuthor: String, publishDate: Editable): Boolean {
        return !(TextUtils.isEmpty(bookTitle) && TextUtils.isEmpty(bookAuthor) && publishDate.isEmpty())
    }

    private fun deleteBook() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mBookViewModel.deleteBook(args.currentBook)
            Toast.makeText(
                requireContext(),
                "Removed book: ${args.currentBook.title}",
                Toast.LENGTH_SHORT
            ).show()

            findNavController().navigate(R.id.action_updateFragment_to_ListFragment)
        }
        builder.setNegativeButton("No") {
            _, _ ->
        }
        builder.setTitle("Delete ${args.currentBook.title}?")
        builder.setMessage("Are you sure you want to delete ${args.currentBook.title} by ${args.currentBook.authorName}?")
        builder.create().show()
    }
}