package dev.leventehadhazi.unimaf.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dev.leventehadhazi.unimaf.R
import dev.leventehadhazi.unimaf.data.Book
import dev.leventehadhazi.unimaf.data.BookViewModel
import dev.leventehadhazi.unimaf.databinding.FragmentAddBinding
import kotlinx.android.synthetic.main.fragment_add.*
import java.nio.file.attribute.UserDefinedFileAttributeView

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null

    private lateinit var mBookViewModel: BookViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddBinding.inflate(inflater, container, false)

        mBookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)

        binding.addButton.setOnClickListener {
            insertDataToDatabase()
        }

        return binding.root
    }

    private fun insertDataToDatabase() {
        val bookTitle = addTitle.text.toString()
        val bookAuthor = addAuthor.text.toString()
        val publishDate = addPublishDate.text
        val isBookRead = addReadCheckBox.isChecked

        if (isInputValid(bookTitle, bookAuthor, publishDate)) {
            // Create Book object
            val book = Book(0, bookTitle, bookAuthor, Integer.parseInt(publishDate.toString()), isBookRead)
            // Add data to database
            mBookViewModel.addBook(book)
            Toast.makeText(requireContext(), "Successfully added book!", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_AddFragment_to_ListFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_LONG).show()
        }
    }

    private fun isInputValid(bookTitle: String, bookAuthor: String, publishDate: Editable): Boolean {
        return !(TextUtils.isEmpty(bookTitle) && TextUtils.isEmpty(bookAuthor) && publishDate.isEmpty())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}