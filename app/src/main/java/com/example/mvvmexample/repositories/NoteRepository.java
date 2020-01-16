package com.example.mvvmexample.repositories;
import android.os.AsyncTask;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.mvvmexample.models.Note;
import com.example.mvvmexample.remote.APIClient;
import com.example.mvvmexample.remote.APIInterface;
import java.util.List;
import java.util.Observable;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class NoteRepository extends Observable {
    private APIInterface apiInterface;
    private MutableLiveData<List<Note>> allNotes = new MutableLiveData<>();
    private static NoteRepository noteRepository;

    public static NoteRepository getInstance() {
        if (noteRepository == null) {
            noteRepository = new NoteRepository();
        }
        return noteRepository;
    }

    private NoteRepository() {
        apiInterface = APIClient.getRetrofitInstance().create(APIInterface.class);
    }

    public void insert(Note note) {
        new InsertNoteAsyncTask(apiInterface).execute(note);
    }

//    public void update(Note note) {
//        new UpdateNoteAsyncTask(noteDao).execute(note);
//    }

    public void delete(Note note) {
        new DeleteNoteAsyncTask(apiInterface).execute(note);
    }

//    public void deleteAllNotes() {
//        new DeleteAllNotesAsyncTask(noteDao).execute();
//    }

    public LiveData<List<Note>> getAllNotes() {
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
        return allNotes;
    }


    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private APIInterface apiInterface;
        private InsertNoteAsyncTask(APIInterface apiInterface) {
            this.apiInterface = apiInterface;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            Call<ResponseBody> call = apiInterface.createNote(notes[0].getTitle(),notes[0].getDescription(),notes[0].getPriority());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    String res = response.body().toString();
                    Log.d(TAG, res);
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
            return null;
        }
    }

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

    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
    private APIInterface apiInterface;

        private DeleteNoteAsyncTask(APIInterface apiInterface) {
            this.apiInterface = apiInterface;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            Call<Void> call = apiInterface.deleteNote(notes[0].getId());
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {

                }
                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });
            return null;
        }
    }
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