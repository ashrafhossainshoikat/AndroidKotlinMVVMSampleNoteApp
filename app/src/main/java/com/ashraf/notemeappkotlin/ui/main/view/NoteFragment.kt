package com.ashraf.notemeappkotlin.ui.main.view


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ashraf.notemeappkotlin.R
import com.ashraf.notemeappkotlin.data.model.Note
import com.ashraf.notemeappkotlin.ui.main.adapter.NoteClickDeleteInterface
import com.ashraf.notemeappkotlin.ui.main.adapter.NoteClickInterface
import com.ashraf.notemeappkotlin.ui.main.adapter.NoteRVAdapter
import com.ashraf.notemeappkotlin.ui.main.viewmodel.NoteViewModel
import com.ashraf.notemeappkotlin.ui.main.viewmodel.NoteViewModelFactory


class NoteFragment : Fragment() , NoteClickInterface, NoteClickDeleteInterface {
    lateinit var factory: NoteViewModelFactory
    lateinit var noteViewModel: NoteViewModel
    lateinit var notesRV: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView:View =inflater.inflate(R.layout.fragment_note, container, false)
        notesRV=rootView.findViewById(R.id.notesRV)
        notesRV.layoutManager = LinearLayoutManager(context)
        val noteRVAdapter = context?.let { NoteRVAdapter(it, this, this) }
        notesRV.adapter = noteRVAdapter
        factory=NoteViewModelFactory(requireContext())
        noteViewModel=ViewModelProvider(this, factory).get(NoteViewModel::class.java)
        noteViewModel.allNotes.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
            noteRVAdapter!!.updateList(it)
        }
        })
        return rootView
    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(context, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDescription)
        intent.putExtra("noteId", note.id)
        startActivity(intent)

    }

    override fun onDeleteIconClick(note: Note) {
        noteViewModel.deleteNote(note)
        Toast.makeText(context, "${note.noteTitle} Deleted", Toast.LENGTH_LONG).show()
    }


}