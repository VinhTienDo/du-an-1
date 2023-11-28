package fpoly.vinhldph35167.du_an_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import fpoly.vinhldph35167.du_an_1.Fragment.fragment_change_pass;
import fpoly.vinhldph35167.du_an_1.Fragment.fragment_don_hang;
import fpoly.vinhldph35167.du_an_1.Fragment.fragment_top;
import fpoly.vinhldph35167.du_an_1.Fragment.frg_gio_hang;
import fpoly.vinhldph35167.du_an_1.Fragment.frg_xem_sp;

public class ManHinhKhachHangDemo extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    FrameLayout frameLayout;
    NavigationView navigationView;
    ActionBar actionBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_khach_hang_demo);

        drawerLayout = findViewById(R.id.drawerLayout_KH);
        toolbar = findViewById(R.id.toolbar_KH);
        frameLayout = findViewById(R.id.framelayout_KH);
        navigationView = findViewById(R.id.navigationView_KH);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if (item.getItemId() == R.id.recycler_XemSP) {
                    fragment = new frg_xem_sp();
                    Toast.makeText(getApplicationContext(), "Xem danh sách sản phẩm", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.recycler_XemGH) {
                    fragment = new frg_gio_hang();
                    Toast.makeText(getApplicationContext(), "Giỏ hàng", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.nav_DonHang) {
                    fragment = new fragment_don_hang();
                } else if (item.getItemId() == R.id.sub_Top) {
                    fragment = new fragment_top();
                } else if (item.getItemId() == R.id.sub_Pass) {
                    fragment = new fragment_change_pass();
                } else if (item.getItemId() == R.id.sub_Logout) {
                    Intent intent = new Intent(ManHinhKhachHangDemo.this, Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.framelayout_KH, fragment).commit();
                    toolbar.setTitle(item.getTitle());
                }
                drawerLayout.closeDrawer(GravityCompat.START);

                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }


}