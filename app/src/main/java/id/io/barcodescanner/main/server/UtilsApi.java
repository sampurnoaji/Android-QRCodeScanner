package id.io.barcodescanner.main.server;

public class UtilsApi {

    private static final String BASE_URL_API = "http://128.199.213.155:1337/api/";

    // Mendeklarasikan Interface BaseApiService
    public static Api getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(Api.class);
    }
}
