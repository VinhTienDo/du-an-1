package fpoly.vinhldph35167.du_an_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fpoly.vinhldph35167.du_an_1.Dao.NhanVienDao;

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

                if (!pass.equals(repass)){
                    Toast.makeText(Register.this, "Pass không khớp", Toast.LENGTH_SHORT).show();
                }else {
                    boolean check = nhanVienDao.Register(user, pass, fullname);
                    if (check){
                        Toast.makeText(Register.this, "Register Success", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(Register.this, "Register Fail", Toast.LENGTH_SHORT).show();
                    }
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