package id.io.barcodescanner.main.server;

import id.io.barcodescanner.main.request.LoginRequest;
import id.io.barcodescanner.main.response.ServerResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface Api {

    @Headers("Content-Type: application/json")
    @POST("login/mobile")
    Call<ServerResponse> login(@Body LoginRequest input);



}
