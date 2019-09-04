package id.io.barcodescanner.main.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barcodescanner.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import id.io.barcodescanner.main.repository.SqliteDatabase;
import id.io.barcodescanner.main.request.LoginRequest;
import id.io.barcodescanner.main.response.UserDetails;
import id.io.barcodescanner.main.server.Api;
import id.io.barcodescanner.main.server.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
    private TextView txtUname, txtAlias, txtRole, txtMemberCode, txtEmail, txtImage, txtLevel, txtDepartment;
    private Button btnLogout, btnNext;
    Context mContext;
    Api mApiService;
    ProgressDialog loading;
    private List<UserDetails> list;
    private UserDetails userDetails;
    private SqliteDatabase sqliteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        bindView();
        getUserData();
        setupButton();
    }

    private void bindView() {
        txtUname = findViewById(R.id.user_uname);
        txtAlias = findViewById(R.id.user_alias);
        txtRole = findViewById(R.id.user_role);
        txtMemberCode = findViewById(R.id.user_memberCode);
        txtEmail = findViewById(R.id.user_email);
        txtImage = findViewById(R.id.user_image);
        txtLevel = findViewById(R.id.user_level);
        txtDepartment = findViewById(R.id.user_department);
        btnLogout = findViewById(R.id.user_btn_logout);
        btnNext = findViewById(R.id.user_btn_next);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        sqliteDatabase = new SqliteDatabase(mContext);
    }

    private void getUserData(){
        Bundle bundle = getIntent().getExtras();
        String userData = bundle.getString("userData");
        String[] userDataSplit = userData.split("-");

        LoginRequest request = new LoginRequest(userDataSplit[0], userDataSplit[1]);
        loading = ProgressDialog.show(mContext, null, "Getting User Details...", false, false);
        Call<List<UserDetails>> call = mApiService.getUserDetails(request);
        call.enqueue(new Callback<List<UserDetails>>() {
            @Override
            public void onResponse(Call<List<UserDetails>> call, Response<List<UserDetails>> response) {
                if (response.isSuccessful()){
                    list = response.body();
                    userDetails = list.get(0);
                    txtUname.setText(userDetails.getUsername());
                    txtAlias.setText(list.get(0).getAlias());
                    txtRole.setText(list.get(0).getRole());
                    txtMemberCode.setText(list.get(0).getMemberCode());
                    txtEmail.setText(list.get(0).getEmail());
                    txtImage.setText(list.get(0).getImage());
                    txtLevel.setText(list.get(0).getLevel());
                    txtDepartment.setText(list.get(0).getDepartment());
                    sqliteDatabase.insertData(userDetails);
                } else {
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.errorBody().string());
                        String message = jsonRESULTS.getString("message");
                        Toast.makeText(UserActivity.this, message, Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                loading.dismiss();
            }

            @Override
            public void onFailure(Call<List<UserDetails>> call, Throwable t) {
                Toast.makeText(UserActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
    }

    private void setupButton(){
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqliteDatabase.deleteData();
                startActivity(new Intent(UserActivity.this, LoginActivity.class));
                finish();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserActivity.this, QRCodeScannerActivity.class));
                finish();
            }
        });
    }
}
