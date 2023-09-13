package com.example.notesapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapplication.R
import com.example.notesapplication.databinding.FragmentListNotesBinding
import com.example.notesapplication.model.Note
import com.example.notesapplication.view.SeeNoteFragment.Companion.PARAM_NOTE_ID
import com.example.notesapplication.view.adapters.NotesAdapter
import com.example.notesapplication.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListNotesFragment : Fragment(),NotesAdapter.OnItemClickListener {

    private var _binding: FragmentListNotesBinding? = null
    private val binding get() = _binding!!

    private val notesViewModel: NotesViewModel by viewModels()
    private lateinit var notaAdapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListNotesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }

    fun initRecyclerView() {
        notaAdapter = NotesAdapter(emptyList())
        notaAdapter.setOnClickListener(this)
        binding.listNotes.adapter = NotesAdapter(arrayListOf())
        binding.listNotes.layoutManager = LinearLayoutManager(requireContext())
        binding.listNotes.adapter = notaAdapter

    }

    override fun onResume() {
        super.onResume()
        observerNotes()
    }

    private fun observerNotes() {
        notesViewModel.findAllNotes()
        notesViewModel.notes.observe(viewLifecycleOwner) { list ->
            try {
                notaAdapter.submitList(list)
                notaAdapter.notes = list.also {
                    binding.emptyText.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
                    binding.listNotes.visibility = if (it.isEmpty()) View.GONE else View.VISIBLE
                }
                notaAdapter.notifyDataSetChanged()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onItemSelected(id: Long, note: Note) {
        val bundle = Bundle()
        bundle.putLong(PARAM_NOTE_ID, id)
        findNavController().navigate(R.id.seeNoteFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object{
        private const val TAG = "ListNotesFragment"
    }
}