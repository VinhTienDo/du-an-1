package fpoly.vinhldph35167.du_an_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fpoly.vinhldph35167.du_an_1.Dao.KhachHangDao;
import fpoly.vinhldph35167.du_an_1.Dao.KhachHangLoginDao;

public class LoginKhachHang extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_khach_hang);
        EditText edUserKH = findViewById(R.id.edUserNameKH);
        EditText edPassKH = findViewById(R.id.edPasswordKH);
        Button btnLoginKH = findViewById(R.id.btnLoginKH);
        Button btnRegisterKH = findViewById(R.id.btnRegisterKH);

        KhachHangLoginDao khachHangLoginDao = new KhachHangLoginDao(this);
        btnLoginKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edUserKH.getText().toString();
                String pass = edPassKH.getText().toString();
                if (khachHangLoginDao.checkLogin(user, pass)){
                    SharedPreferences sharedPreferences = getSharedPreferences("THONGTINKH", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("makh", user);
                    editor.commit();

                    startActivity(new Intent(LoginKhachHang.this, ManHinhKhachHangDemo.class));
                }else {
                    Toast.makeText(LoginKhachHang.this, "Username hoặc passwoed không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnRegisterKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginKhachHang.this, RegisterKhachHang.class));
            }
        });
    }
}