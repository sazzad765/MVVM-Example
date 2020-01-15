package com.example.mvvmexample.remote;
import com.example.mvvmexample.models.Note;
import com.example.mvvmexample.models.UserList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("note.php")
    Call<List<Note>> doGetListResources();

//    @POST("/api/users")
//    Call<User> createUser(@Body User user);

    @GET("api/users")
    Call<UserList> doGetUserList();
//
//    @FormUrlEncoded
//    @POST("/api/users?")
//    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);

}
