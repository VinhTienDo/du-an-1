package fpoly.vinhldph35167.du_an_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fpoly.vinhldph35167.du_an_1.Dao.KhachHangLoginDao;

public class RegisterKhachHang extends AppCompatActivity {
    private KhachHangLoginDao khachHangLoginDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_khach_hang);

        EditText edUserDKKH = findViewById(R.id.edUserFullNameDKKH);
        EditText edPassDKKH = findViewById(R.id.edPasswordDkKH);
        EditText edRePassDKKH = findViewById(R.id.edRePasswordDKKH);
        EditText edFullnameDKKH = findViewById(R.id.edUserFullNameDKKH);
        Button btnRegisterDKKH = findViewById(R.id.btnRegisterDKKH);
        Button btnHuyDKKH = findViewById(R.id.btnHuyDKKH);

        khachHangLoginDao = new KhachHangLoginDao(this);
        btnRegisterDKKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edUserDKKH.getText().toString();
                String pass = edPassDKKH.getText().toString();
                String repass = edRePassDKKH.getText().toString();
                String fullname = edFullnameDKKH.getText().toString();

                if (!pass.equals(repass)){
                    Toast.makeText(RegisterKhachHang.this, "Pass không khớp", Toast.LENGTH_SHORT).show();
                }else {
                    boolean check = khachHangLoginDao.Register(user, pass, fullname);
                    if (check){
                        Toast.makeText(RegisterKhachHang.this, "Register Success", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(RegisterKhachHang.this, "Register Fail", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnHuyDKKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}