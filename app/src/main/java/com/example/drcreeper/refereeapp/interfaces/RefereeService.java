package com.example.drcreeper.refereeapp.interfaces;

import com.example.drcreeper.refereeapp.Answer;
import com.example.drcreeper.refereeapp.RefereeApp;
import com.example.drcreeper.refereeapp.models.ContestFields;
import com.example.drcreeper.refereeapp.models.ContestInfo;
import com.example.drcreeper.refereeapp.models.ContestTab;
import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RefereeService {
    @POST("/api/register.php")
    Call<Answer> registerUser(@Body RequestBody body);
    @POST("/api/login.php")
    Call<Answer> loginUser(@Body RequestBody body);
    @POST("/api/contestcheck.php")
    Call<Answer> checkContest(@Body RequestBody body);
    @POST("/api/contestnew.php")
    Call<Answer> newContest(@Body RequestBody body);
    @POST("/api/getinfo.php")
    Call<ContestInfo> getInfo(@Body RequestBody body);
    @POST("/api/getfields.php")
    Call<ContestFields> getFields(@Body RequestBody body);
    @POST("/api/addinfo.php")
    Call<Answer> addInfo(@Body RequestBody body);
    @POST("/api/addfield.php")
    Call<Answer> addField(@Body RequestBody body);
    @POST("/api/addteam.php")
    Call<Answer> addTeam(@Body RequestBody body);
    @POST("/api/getcontest.php")
    Call<ContestTab> getContest(@Body RequestBody body);
    @POST("api/sendmarks.php")
    Call<Answer> sendMarks(@Body RequestBody body);

}
