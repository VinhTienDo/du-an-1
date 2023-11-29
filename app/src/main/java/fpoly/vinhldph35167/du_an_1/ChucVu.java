package fpoly.vinhldph35167.du_an_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChucVu extends AppCompatActivity {

    Button btnQuanLy, btnKhachHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuc_vu);
        btnQuanLy = findViewById(R.id.btnQuanLy);
        btnKhachHang = findViewById(R.id.btnKhachHang);

        btnQuanLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChucVu.this, Login.class));
            }
        });
        btnKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChucVu.this, LoginKhachHang.class));
            }
        });
    }
}