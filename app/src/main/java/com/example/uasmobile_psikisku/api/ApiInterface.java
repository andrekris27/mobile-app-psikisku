package com.example.uasmobile_psikisku.api;



import com.example.uasmobile_psikisku.model.login.Login;
import com.example.uasmobile_psikisku.model.register.Register;
import com.example.uasmobile_psikisku.model.update.Update;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("Login.php")
    Call<Login> loginResponse(
            @Field("email") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("Register.php")
    Call<Register> registerResponse(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("update_user.php")
    Call<Update> updateResponse(
            @Field("id") String id,
            @Field("username") String username,
            @Field("email") String email
    );
}
