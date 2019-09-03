package id.io.barcodescanner.main.server;

import java.util.List;

import id.io.barcodescanner.main.request.AssetRequest;
import id.io.barcodescanner.main.request.LoginRequest;
import id.io.barcodescanner.main.response.AssetDetails;
import id.io.barcodescanner.main.response.ServerResponse;
import id.io.barcodescanner.main.response.UserDetails;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface Api {

    @Headers("Content-Type: application/json")
    @POST("login/mobile")
    Call<ServerResponse> login(@Body LoginRequest input);

    @Headers("Content-Type: application/json")
    @POST("mobile/userdetails")
    Call<List<UserDetails>> getUserDetails(@Body LoginRequest  input);

    @Headers("Content-Type: application/json")
    @POST("mobile/asset")
    Call<List<AssetDetails>> getAssetDetails(@Body AssetRequest assetCode);

}
