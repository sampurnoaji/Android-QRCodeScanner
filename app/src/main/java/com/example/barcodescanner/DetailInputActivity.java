package com.example.barcodescanner;

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

public class DetailInputActivity extends AppCompatActivity {
    private EditText productId, productName, brand, vendor, latitude, longitude;
    private Button btnSend;
    private String productIdValue;
    private double latitudeValue, longitudeValue;
    private ImageView img1, img2, img3;
    private final int requestCode1 = 1;
    private final int requestCode2 = 2;
    private final int requestCode3 = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_input);
        bindView();
        setLatLng();
        setDetail();
        setPhoto();
    }

    private void bindView() {
        productId = findViewById(R.id.input_product_id);
        productName = findViewById(R.id.input_product_name);
        brand = findViewById(R.id.input_brand);
        vendor = findViewById(R.id.input_vendor);
        latitude = findViewById(R.id.input_latitude);
        longitude = findViewById(R.id.input_longitude);
        img1 = findViewById(R.id.input_img1);
        img2 = findViewById(R.id.input_img2);
        img3 = findViewById(R.id.input_img3);
        btnSend = findViewById(R.id.input_btn_send);
    }

    private void setDetail(){
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            productIdValue = bundle.getString("productId");
            latitudeValue = bundle.getDouble("lat");
            longitudeValue = bundle.getDouble("lng");

            productId.setText(productIdValue);
            latitude.setText(String.valueOf(latitudeValue));
            longitude.setText(String.valueOf(longitudeValue));
        }
    }

    private void setLatLng(){
        setDetail();
        latitude.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent intent = new Intent(DetailInputActivity.this, MapsActivity.class);
                intent.putExtra("productId", productIdValue);
                startActivity(intent);
                return true;
            }
        });
        longitude.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent intent = new Intent(DetailInputActivity.this, MapsActivity.class);
                intent.putExtra("productId", productIdValue);
                startActivity(intent);
                return true;
            }
        });
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
