package com.example.mvvmexample.repositories;

import android.app.Application;
import android.util.Log;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmexample.models.Note;
import com.example.mvvmexample.remote.APIClient;
import com.example.mvvmexample.remote.APIInterface;

import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoteRepository extends Observable {
    APIInterface apiInterface;
    private MutableLiveData<List<Note>> allNotes = new MutableLiveData<>();
    private static NoteRepository noteRepository;
    public static NoteRepository getInstance(){
        if (noteRepository == null){
            noteRepository = new NoteRepository();
        }
        return noteRepository;
    }

    public NoteRepository() {
        apiInterface = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<List<Note>> call2 = apiInterface.doGetListResources();
        call2.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                allNotes.postValue(response.body());
            }
            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {
                call.cancel();
            }
        });
    }

//    public void insert(Note note) {
//        new InsertNoteAsyncTask(noteDao).execute(note);
//    }
//
//    public void update(Note note) {
//        new UpdateNoteAsyncTask(noteDao).execute(note);
//    }
//
//    public void delete(Note note) {
//        new DeleteNoteAsyncTask(noteDao).execute(note);
//    }
//
//    public void deleteAllNotes() {
//        new DeleteAllNotesAsyncTask(noteDao).execute();
//    }

    public LiveData<List<Note>> getAllNotes() {

        return allNotes;
    }


//    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
//        private NoteDao noteDao;
//
//        private InsertNoteAsyncTask(NoteDao noteDao) {
//            this.noteDao = noteDao;
//        }
//
//        @Override
//        protected Void doInBackground(Note... notes) {
//            noteDao.insert(notes[0]);
//            return null;
//        }
//    }
//
//    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
//        private NoteDao noteDao;
//
//        private UpdateNoteAsyncTask(NoteDao noteDao) {
//            this.noteDao = noteDao;
//        }
//
//        @Override
//        protected Void doInBackground(Note... notes) {
//            noteDao.update(notes[0]);
//            return null;
//        }
//    }
//
//    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
//        private NoteDao noteDao;
//
//        private DeleteNoteAsyncTask(NoteDao noteDao) {
//            this.noteDao = noteDao;
//        }
//
//        @Override
//        protected Void doInBackground(Note... notes) {
//            noteDao.delete(notes[0]);
//            return null;
//        }
//    }
//
//    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
//        private NoteDao noteDao;
//
//        private DeleteAllNotesAsyncTask(NoteDao noteDao) {
//            this.noteDao = noteDao;
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            noteDao.deleteAllNotes();
//            return null;
//        }
//    }
}