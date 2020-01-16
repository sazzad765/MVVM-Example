package com.example.mvvmexample.remote;
import com.example.mvvmexample.models.Note;
import java.util.List;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("note.php")
    Call<List<Note>> doGetListResources();

    @FormUrlEncoded
    @POST("noteAdd.php")
    Call<ResponseBody> createNote(
            @Field("title") String title,
            @Field("description") String description,
            @Field("priority") int priority
    );

//    @PATCH("gists/{id}")
//    Call<ResponseBody> patchGist(@Path("id") String id, @Body Gist gist);
//
//    @PUT("gists/{id}")
//    Call<ResponseBody> replaceGist(@Path("id") String id, @Body Gist gist);

    @DELETE("noteDelete.php")
    Call<Void> deleteNote(@Query("id") int id);

}
