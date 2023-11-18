package fpoly.vinhldph35167.du_an_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fpoly.vinhldph35167.du_an_1.Dao.NhanVienDao;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText edUser = findViewById(R.id.edUserName);
        EditText edPass = findViewById(R.id.edPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);

        NhanVienDao nhanVienDao = new NhanVienDao(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edUser.getText().toString();
                String pass = edPass.getText().toString();
                if (nhanVienDao.checkLogin(user,pass)){
//                    Luu Sharedpreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("manv", user);
                    editor.commit();

                    startActivity(new Intent(Login.this, MainActivity.class));
                }else {
                    Toast.makeText(Login.this, "Username hoặc passwoed không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
    }
}