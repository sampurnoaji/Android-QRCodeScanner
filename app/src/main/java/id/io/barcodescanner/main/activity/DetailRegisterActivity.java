package id.io.barcodescanner.main.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.barcodescanner.R;

public class DetailRegisterActivity extends AppCompatActivity {
    private EditText productId, productName, brand, vendor;
    private Button btnRegister;
    private String scanResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_register);
        bindView();
        getProductID();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailRegisterActivity.this, DetailInputActivity.class);
                intent.putExtra("productId", scanResult);
                startActivity(intent);
            }
        });
    }

    private void bindView() {
        productId = findViewById(R.id.detail_product_id);
        productName = findViewById(R.id.detail_product_name);
        brand = findViewById(R.id.detail_brand);
        vendor = findViewById(R.id.detail_vendor);
        btnRegister = findViewById(R.id.detail_btn_register);
    }

    private void getProductID() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            scanResult = bundle.getString("scanResult");
            productId.setText(scanResult);
        }
        productId.setEnabled(false);
    }
}
