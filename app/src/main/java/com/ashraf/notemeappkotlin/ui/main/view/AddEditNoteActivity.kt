package com.ashraf.notemeappkotlin.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ashraf.notemeappkotlin.R
import com.ashraf.notemeappkotlin.data.model.Note
import com.ashraf.notemeappkotlin.ui.main.viewmodel.NoteViewModel
import com.ashraf.notemeappkotlin.ui.main.viewmodel.NoteViewModelFactory
import com.ashraf.notemeappkotlin.utils.Constants
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {
    //on below line we are creating variables for our UI components.
    lateinit var etTaskName: EditText
    lateinit var etDescription: EditText

    lateinit var tvDeadLine: TextView
    lateinit var tvTitle: TextView

    lateinit var spStatus: Spinner
    lateinit var btnSubmit: Button
    lateinit var btnDateSelect: ImageButton
    lateinit var btnEmail:ImageButton
    lateinit var btnPhone:ImageButton
    lateinit var btnUrl:ImageButton

    lateinit var factory: NoteViewModelFactory
    lateinit var noteViewModel: NoteViewModel
    lateinit var note: Note
    var noteID = -1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)
        factory=NoteViewModelFactory(this,"")
        noteViewModel=ViewModelProvider(this, factory).get(NoteViewModel::class.java)

        etTaskName = findViewById(R.id.etTaskName)
        etDescription = findViewById(R.id.etDescription)
        tvDeadLine = findViewById(R.id.tvDeadLine)
        tvTitle = findViewById(R.id.tvTitle)
        spStatus = findViewById(R.id.spStatus)

        val stausList: MutableList<String> = ArrayList()
        stausList.add(Constants.STATUS_OPEN)
        stausList.add(Constants.STATUS_IN_PROGRESS)
        stausList.add(Constants.STATUS_TEST)
        stausList.add(Constants.STATUS_DONE)
        val dataAdapter = ArrayAdapter(
            this,
            R.layout.spinner_dropdown_item, stausList
        )
        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item)

        spStatus.adapter = dataAdapter



        btnDateSelect = findViewById(R.id.btnDateSelect)

        btnEmail = findViewById(R.id.btnEmail)
        btnPhone = findViewById(R.id.btnPhone)
        btnUrl = findViewById(R.id.btnUrl)
        btnSubmit = findViewById(R.id.btnSubmit)

        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {
            //on below line we are setting data to edit text.
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDescription = intent.getStringExtra("noteDescription")
            noteID = intent.getIntExtra("noteId", -1)
            btnSubmit.setText("Update")
            etTaskName.setText(noteTitle)
            etDescription.setText(noteDescription)
        } else {
            btnSubmit.setText("Save")
        }

        //on below line we are adding click listner to our save button.
        btnSubmit.setOnClickListener {
            //on below line we are getting title and desc from edit text.
            val noteTitle = etTaskName.text.toString()
            val noteDescription = etDescription.text.toString()
            //on below line we are checking the type and then saving or updating the data.
            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    val updatedNote = Note(noteTitle, noteDescription, currentDateAndTime, Constants.STATUS_TEST)
                    updatedNote.id = noteID
                    noteViewModel.updateNote(updatedNote)
                    Toast.makeText(this, "Note Updated..", Toast.LENGTH_LONG).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    //if the string is not empty we are calling a add note method to add data to our room database.
                    noteViewModel.addNote(Note(noteTitle, noteDescription, currentDateAndTime,Constants.STATUS_IN_PROGRESS))
                    Toast.makeText(this, "$noteTitle Added", Toast.LENGTH_LONG).show()
                }
            }
            //opening the new activity on below line
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }
}