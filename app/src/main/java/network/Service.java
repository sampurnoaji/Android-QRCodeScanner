package network;

import model.LoginRequest;
import model.ResponseType;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Dibuat oleh petersam
 */
public interface Service {
    @FormUrlEncoded
    @POST("/api/login/mobile")
    Call<ResponseType> loginRequest(@Field("uname") String uname, @Field("password") String password );
}
