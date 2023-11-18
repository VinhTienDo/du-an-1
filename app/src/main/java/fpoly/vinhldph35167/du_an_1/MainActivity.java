package fpoly.vinhldph35167.du_an_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;

import fpoly.vinhldph35167.du_an_1.Fragment.fragment_change_pass;
import fpoly.vinhldph35167.du_an_1.Fragment.fragment_doanh_thu;
import fpoly.vinhldph35167.du_an_1.Fragment.fragment_don_hang;
import fpoly.vinhldph35167.du_an_1.Fragment.fragment_khach_hang;
import fpoly.vinhldph35167.du_an_1.Fragment.fragment_loai_hang;
import fpoly.vinhldph35167.du_an_1.Fragment.fragment_nhan_vien;
import fpoly.vinhldph35167.du_an_1.Fragment.fragment_san_pham;
import fpoly.vinhldph35167.du_an_1.Fragment.fragment_top;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        FrameLayout frameLayout = findViewById(R.id.flContent);
        NavigationView navigationView = findViewById(R.id.nvView);
        drawerLayout = findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_menu_24);
        
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if (item.getItemId() == R.id.mQLDonHang){
                    fragment = new fragment_don_hang();
                } else if (item.getItemId() == R.id.mQLKhachHang) {
                    fragment = new fragment_khach_hang();
                } else if (item.getItemId() == R.id.mQLSanPham) {
                    fragment = new fragment_san_pham();
                } else if (item.getItemId() == R.id.mQLNhanVien) {
                    fragment = new fragment_nhan_vien();
                } else if (item.getItemId() == R.id.mQLLoaiHang) {
                    fragment = new fragment_loai_hang();
                }else if (item.getItemId() == R.id.mDoanhThu){
                    fragment = new fragment_doanh_thu();
                } else if (item.getItemId() == R.id.mDoiMatKhau) {
                    fragment = new fragment_change_pass();
                } else if (item.getItemId() == R.id.mTop) {
                    fragment = new fragment_top();
                } else if (item.getItemId() == R.id.mThoat) {
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                if (fragment != null){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.flContent, fragment)
                            .commit();
                    toolbar.setTitle(item.getTitle());
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}