package com.example.notesapplication.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapplication.R
import com.example.notesapplication.model.Note
import com.example.notesapplication.utils.UiUtils

class NotesAdapter(var notes: List<Note>) : RecyclerView.Adapter<NotesAdapter.NotaViewHolder>() {

    lateinit var listener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemSelected(id: Long,note: Note)
    }

    fun setOnClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    fun submitList(notes: List<Note>){
        this.notes = notes
    }


    inner class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.title_note)
        val contenido: TextView = itemView.findViewById(R.id.contenido_note)
        val fecha: TextView = itemView.findViewById(R.id.fecha_note)
        val contentNote: ConstraintLayout = itemView.findViewById(R.id.contentNote)

        init {
            contentNote.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    listener.onItemSelected(notes[position].id, notes[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notes, parent, false)
        return NotaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val nota = notes[position]
        holder.titulo.text = nota.title
        holder.contenido.text = nota.description
        UiUtils.bindFormattedDate(holder.fecha, nota.timeStamp)
    }

    override fun getItemCount() = notes.size
}