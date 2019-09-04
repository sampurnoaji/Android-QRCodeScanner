package id.io.barcodescanner.main.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.barcodescanner.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import id.io.barcodescanner.main.request.LoginRequest;
import id.io.barcodescanner.main.response.ServerResponse;
import id.io.barcodescanner.main.server.Api;
import id.io.barcodescanner.main.server.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText txtUsername, txtPassword;
    private Button btnLogin;

    Context mContext;
    Api mApiService;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindView();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateLogin(txtUsername.getText().toString(), txtPassword.getText().toString())) {
                    doLogin(txtUsername.getText().toString(), txtPassword.getText().toString());
                }
            }
        });
    }

    private void bindView() {
        txtUsername = findViewById(R.id.login_emailid);
        txtPassword = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.loginBtn);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

    }

    private boolean validateLogin(String username, String password) {
        if (username == null || username.trim().length() == 0) {
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password == null || password.trim().length() == 0) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void doLogin(final String username, final String password) {
        LoginRequest request = new LoginRequest(username, password);
        loading = ProgressDialog.show(mContext, null, "Logging in ...", true, false);
        Call<ServerResponse> call = mApiService.login(request);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                    intent.putExtra("userData", getUserDetails(username, password));
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                } else {
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.errorBody().string());
                        String message = jsonRESULTS.getString("message");
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                loading.dismiss();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
    }

    private String getUserDetails(String username, String password) {
        String userData = username + "-" + password;
        return userData;
    }
}
