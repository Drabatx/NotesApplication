package com.example.notesapplication.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.notesapplication.R
import com.example.notesapplication.databinding.FragmentSeeNoteBinding
import com.example.notesapplication.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeeNoteFragment : Fragment() {

    private var _binding : FragmentSeeNoteBinding?=null
    private val binding get() = _binding!!

    private val notesViewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSeeNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idNote = arguments?.getLong(PARAM_NOTE_ID)

        idNote?.let {
            notesViewModel.findByIdNote(it)
            notesViewModel.selectNote.observe(viewLifecycleOwner){ note->
                binding.tvTitleNote.setText(note.title)
                binding.tvDescNote.setText(note.description)
            }
        }
        Log.i(TAG, "onViewCreated: "+idNote)
    }
    companion object{
        private const val TAG = "SeeNoteFragment"
        val PARAM_NOTE_ID = "PARAM_ID_NOTE"

    }
}