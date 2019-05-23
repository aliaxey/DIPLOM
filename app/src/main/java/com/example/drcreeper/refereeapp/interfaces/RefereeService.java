package com.example.drcreeper.refereeapp.interfaces;

import com.example.drcreeper.refereeapp.Answer;
import com.example.drcreeper.refereeapp.models.ContestFields;
import com.example.drcreeper.refereeapp.models.ContestInfo;

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
}
