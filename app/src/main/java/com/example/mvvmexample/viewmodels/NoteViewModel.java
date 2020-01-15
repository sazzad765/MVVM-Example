package com.example.mvvmexample.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmexample.models.Note;
import com.example.mvvmexample.repositories.NoteRepository;
;import java.util.List;

public class NoteViewModel extends ViewModel {
    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel() {
        super();
        repository = NoteRepository.getInstance();
        allNotes = repository.getAllNotes();
    }

//    public void insert(Note note) {
//        repository.insert(note);
//    }
//
//    public void update(Note note) {
//        repository.update(note);
//    }
//
//    public void delete(Note note) {
//        repository.delete(note);
//    }
//
//    public void deleteAllNotes() {
//        repository.deleteAllNotes();
//    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}