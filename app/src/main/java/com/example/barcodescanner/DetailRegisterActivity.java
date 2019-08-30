package com.example.barcodescanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class DetailRegisterActivity extends AppCompatActivity {
    private EditText productID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_register);
        getProductID();
    }

    private void getProductID() {
        productID = findViewById(R.id.detail_product_id);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            String scanResult = bundle.getString("scanResult");
            productID.setText(scanResult);
        }
        productID.setEnabled(false);
    }
}
