package id.io.barcodescanner.main.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.barcodescanner.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import id.io.barcodescanner.main.request.AssetRequest;
import id.io.barcodescanner.main.response.AssetDetails;
import id.io.barcodescanner.main.server.Api;
import id.io.barcodescanner.main.server.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailRegisterActivity extends AppCompatActivity {
    private EditText  txtAssetCode, txtAssetName, txtAssetType, txtManufacture, txtModel,
            txtVendor, txtNote, txtCreatedDate;
    private Button btnRegister;
    private String scanResult;

    Context context;
    Api api;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_register);
        bindView();

        getAssetDetails();


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailRegisterActivity.this, DetailInputActivity.class);
                intent.putExtra("productId", scanResult);
//                startActivity(intent);
                getAssetDetails();
            }
        });
    }

    private void bindView() {
        txtAssetCode = findViewById(R.id.detail_assetCode);
        txtAssetName = findViewById(R.id.detail_assetName);
        txtAssetType = findViewById(R.id.detail_assetType);
        txtManufacture = findViewById(R.id.detail_manufacture);
        txtModel = findViewById(R.id.detail_model);
        txtVendor = findViewById(R.id.detail_vendor);
        txtNote = findViewById(R.id.detail_note);
        txtCreatedDate = findViewById(R.id.detail_createdDate);
        btnRegister = findViewById(R.id.detail_btn_register);


        context = this;
        api = UtilsApi.getAPIService();
    }

    private void getAssetDetails() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            scanResult = bundle.getString("scanResult");
        }

        AssetRequest request = new AssetRequest(scanResult);
        loading = ProgressDialog.show(context, null, "Getting User Details...", false, false);
        Call<List<AssetDetails>> call = api.getAssetDetails(request);
        call.enqueue(new Callback<List<AssetDetails>>() {
            @Override
            public void onResponse(Call<List<AssetDetails>> call, Response<List<AssetDetails>> response) {
                if (response.isSuccessful()){
                    List<AssetDetails> list = response.body();
                    txtAssetCode.setText(list.get(0).getCode());
                    txtAssetName.setText(list.get(0).getName());
                    txtAssetType.setText(list.get(0).getType());
                    txtManufacture.setText(list.get(0).getManufacture());
                    txtModel.setText(list.get(0).getModel());
                    txtVendor.setText(list.get(0).getVendor());
                    txtNote.setText(list.get(0).getNote());
                    txtCreatedDate.setText(list.get(0).getRegister_date());
                } else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String message = jsonObject.getString("message");
                        Toast.makeText(DetailRegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                loading.dismiss();
            }

            @Override
            public void onFailure(Call<List<AssetDetails>> call, Throwable t) {
                Toast.makeText(DetailRegisterActivity.this, "ERROR : " + t.toString(), Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
    }
}
