package id.io.barcodescanner.main.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.barcodescanner.R;

public class UserActivity extends AppCompatActivity {
    private TextView uname, password;
    private Button btnSwitchUser, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        bindView();
        setupButton();
    }

    private void bindView() {
        uname = findViewById(R.id.user_uname);
        password = findViewById(R.id.user_password);
        btnSwitchUser = findViewById(R.id.user_btn_switch);
        btnNext = findViewById(R.id.user_btn_next);
    }

    private void setupButton(){
        btnSwitchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
