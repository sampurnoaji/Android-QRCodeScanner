package id.io.barcodescanner.main.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.barcodescanner.R;

import java.io.Serializable;

import id.io.barcodescanner.main.request.SendAssetRequest;

public class DetailInputActivity extends AppCompatActivity {
    private EditText txtAssetCode, txtLocationName, txtBuildingName, txtMemberCode,
            txtRate, txtLatitude, txtLongitude, txtNote;
    private String txtAssetCodeValue, txtLocationNameValue, txtBuildingNameValue, txtMemberCodeValue,
            txtRateValue, txtLatitudeValue, txtLongitudeValue, txtNoteValue;
    private Button btnSend;
    private double latitudeValue, longitudeValue;
    private ImageView img1, img2, img3;
    private final int requestCode1 = 1;
    private final int requestCode2 = 2;
    private final int requestCode3 = 3;
    private SendAssetRequest assetModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_input);
        bindView();
        setPhoto();
        getAssetCode();
        toMapsActivity();
    }

    private void bindView() {
        txtAssetCode = findViewById(R.id.input_assetCode);
        txtLocationName = findViewById(R.id.input_locationName);
        txtBuildingName = findViewById(R.id.input_buildingName);
        txtMemberCode = findViewById(R.id.input_memberCode);
        txtRate = findViewById(R.id.input_rate);
        txtNote = findViewById(R.id.input_note);
        txtLatitude = findViewById(R.id.input_latitude);
        txtLongitude = findViewById(R.id.input_longitude);
        img1 = findViewById(R.id.input_img1);
        img2 = findViewById(R.id.input_img2);
        img3 = findViewById(R.id.input_img3);
        btnSend = findViewById(R.id.input_btn_send);
    }

    private void setAssetModel() {
        getEditTextValue();
        assetModel.setAssetCode(txtAssetCodeValue);
        assetModel.setLocationName(txtLocationNameValue);
        assetModel.setBuildingName(txtBuildingNameValue);
        if (txtMemberCodeValue.equals("")){
            assetModel.setMemberCode(0);
        } else {
            assetModel.setMemberCode(Integer.valueOf(txtMemberCodeValue));
        }
        if (txtRateValue.equals("")){
            assetModel.setRate(0);
        } else {
            assetModel.setRate(Integer.valueOf(txtRateValue));
        }
        assetModel.setNote(txtNoteValue);
        assetModel.setGeoLocation(txtLatitudeValue + ", " + txtLongitudeValue);
    }

    private void getAssetCode(){
        getEditTextValue();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            String flag = bundle.getString("flag");
            if (flag.equals("fromRegisterActivity")){
                txtAssetCodeValue = bundle.getString("assetCode");
                txtAssetCode.setText(txtAssetCodeValue);
            } else {
                assetModel = (SendAssetRequest) bundle.getSerializable("newModel");
                latitudeValue = bundle.getDouble("lat");
                longitudeValue = bundle.getDouble("lng");
                setEditTextValue(assetModel, latitudeValue, longitudeValue);
            }
        }
    }

    private void toMapsActivity(){
        assetModel = new SendAssetRequest();
        txtLatitude.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent intent = new Intent(DetailInputActivity.this, MapsActivity.class);
                setAssetModel();
                intent.putExtra("model", assetModel);
                startActivity(intent);
                return true;
            }
        });
        txtLongitude.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent intent = new Intent(DetailInputActivity.this, MapsActivity.class);
                setAssetModel();
                intent.putExtra("model", assetModel);
                startActivity(intent);
                return true;
            }
        });
    }

    private void getEditTextValue(){
        txtAssetCodeValue = txtAssetCode.getText().toString();
        txtLocationNameValue = txtLocationName.getText().toString();
        txtBuildingNameValue = txtBuildingName.getText().toString();
        txtMemberCodeValue = txtMemberCode.getText().toString();
        txtRateValue = txtRate.getText().toString();
        txtNoteValue = txtNote.getText().toString();
        txtLatitudeValue = txtLatitude.getText().toString();
        txtLongitudeValue = txtLongitude.getText().toString();
    }

    private void setEditTextValue(SendAssetRequest assetModel, double lat, double lng){
        txtAssetCode.setText(assetModel.getAssetCode());
        txtLocationName.setText(assetModel.getLocationName());
        txtBuildingName.setText(assetModel.getBuildingName());
        txtMemberCode.setText(""+ assetModel.getMemberCode());
        txtRate.setText(""+ assetModel.getRate());
        txtNote.setText(assetModel.getNote());
        txtLatitude.setText(""+ lat);
        txtLongitude.setText(""+ lng);
    }

    private void setPhoto(){
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoIntent, requestCode1);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoIntent, requestCode2);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoIntent, requestCode3);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (this.requestCode1 == requestCode && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            img1.setImageBitmap(bitmap);
        }
        if (this.requestCode2 == requestCode && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            img2.setImageBitmap(bitmap);
        }
        if (this.requestCode3 == requestCode && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            img3.setImageBitmap(bitmap);
        }
    }
}
