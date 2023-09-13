package com.example.notesapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.notesapplication.databinding.FragmentCreateNoteBinding
import com.example.notesapplication.model.Note
import com.example.notesapplication.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class CreateNoteFragment : Fragment() {

    private var _binding: FragmentCreateNoteBinding? = null
    private val binding get() = _binding!!

    private val notesViewModel: NotesViewModel by viewModels()

    private val _isReadyNote = MutableLiveData<Boolean>()
    val isReadyNote: LiveData<Boolean> get() = _isReadyNote

    var isReadyTitle = false
    var isReadyContent = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateNoteBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.editTextTitle.addTextChangedListener {
            isReadyTitle = !it.toString().isNullOrEmpty()
            _isReadyNote.value = isReadyTitle && isReadyContent
        }

        binding.editTextContent.addTextChangedListener {
            isReadyContent = !it.toString().isNullOrEmpty()
            _isReadyNote.value = isReadyTitle && isReadyContent
        }

        binding.btnAddNote.setOnClickListener {
            //Agregar Nota
            notesViewModel.insertOrUpdate(
                Note(
                    title = binding.editTextTitle.text.toString(),
                    description = binding.editTextContent.text.toString()
                )
            )
            findNavController().navigateUp()
        }
        isReadyNote.observe(viewLifecycleOwner) {
            binding.btnAddNote.isEnabled = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}