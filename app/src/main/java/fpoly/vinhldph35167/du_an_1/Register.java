package fpoly.vinhldph35167.du_an_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fpoly.vinhldph35167.du_an_1.Dao.NhanVienDao;
import fpoly.vinhldph35167.du_an_1.Model.NhanVien;

public class Register extends AppCompatActivity {
    private NhanVienDao nhanVienDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText edUserDk = findViewById(R.id.edUserNameDK);
        EditText edPassDK = findViewById(R.id.edPasswordDk);
        EditText edRePassDk = findViewById(R.id.edRePasswordDK);
        EditText edFullnameDk = findViewById(R.id.edUserFullNameDK);
        Button btnRegisterDk = findViewById(R.id.btnRegisterDK);
        Button btnHuyDk = findViewById(R.id.btnHuyDK);

        nhanVienDao = new NhanVienDao(this);
        btnRegisterDk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edUserDk.getText().toString();
                String pass = edPassDK.getText().toString();
                String repass = edRePassDk.getText().toString();
                String fullname = edFullnameDk.getText().toString();

                // Kiểm tra lỗi
                if (user.isEmpty()) {
                    Toast.makeText(Register.this, "Vui lòng nhập username", Toast.LENGTH_SHORT).show();
                } else if (pass.isEmpty()) {
                    Toast.makeText(Register.this, "Vui lòng nhập password", Toast.LENGTH_SHORT).show();
                } else if (repass.isEmpty()) {
                    Toast.makeText(Register.this, "Vui lòng nhập lại password", Toast.LENGTH_SHORT).show();
                } else if (!pass.equals(repass)) {
                    Toast.makeText(Register.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                } else {
                    // Đăng ký người dùng
                    NhanVienDao nhanVienDao = new NhanVienDao(Register.this);
//                    NhanVien nhanVien = new NhanVien(user, fullname, pass, repass);
//                    nhanVien.setPassword(pass);
//                    if (nhanVienDao.capNhatNhanVien(nhanVien)) {
//                        Toast.makeText(Register.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(Register.this, MainActivity.class));
//                    } else {
//                        Toast.makeText(Register.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
//                    }
                }
            }
        });
        btnHuyDk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}