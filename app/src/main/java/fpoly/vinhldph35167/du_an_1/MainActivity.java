package fpoly.vinhldph35167.du_an_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import fpoly.vinhldph35167.du_an_1.Dao.NhanVienDao;
import fpoly.vinhldph35167.du_an_1.Fragment.fragment_change_pass;
import fpoly.vinhldph35167.du_an_1.Fragment.fragment_doanh_thu;
import fpoly.vinhldph35167.du_an_1.Fragment.fragment_don_hang;
import fpoly.vinhldph35167.du_an_1.Fragment.fragment_khach_hang;
import fpoly.vinhldph35167.du_an_1.Fragment.fragment_loai_hang;
import fpoly.vinhldph35167.du_an_1.Fragment.fragment_nhan_vien;
import fpoly.vinhldph35167.du_an_1.Fragment.fragment_san_pham;
import fpoly.vinhldph35167.du_an_1.Fragment.fragment_top;
import fpoly.vinhldph35167.du_an_1.Model.NhanVien;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private List<Integer> imageList;
    private int currentPosition;
    private Handler handler;
    private Runnable runnable;

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

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
                if (item.getItemId() == R.id.nav_DonHang){
                    fragment = new fragment_don_hang();
                } else if (item.getItemId() == R.id.sub_AddCustomer) {
                    fragment = new fragment_khach_hang();
                } else if (item.getItemId() == R.id.nav_SanPham) {
                    fragment = new fragment_san_pham();
                } else if (item.getItemId() == R.id.nav_NhanVien) {
                    fragment = new fragment_nhan_vien();
                } else if (item.getItemId() == R.id.nav_LoaiHang) {
                    fragment = new fragment_loai_hang();
                }else if (item.getItemId() == R.id.sub_DoanhThu){
                    fragment = new fragment_doanh_thu();
                } else if (item.getItemId() == R.id.sub_Pass) {
                    fragment = new fragment_change_pass();
                } else if (item.getItemId() == R.id.sub_Top) {
                    fragment = new fragment_top();
                } else if (item.getItemId() == R.id.sub_Logout) {
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
        slideShow();
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
    public void slideShow(){
        // Thay đổi danh sách hình ảnh thành các tài nguyên hợp lệ của bạn
        imageList = new ArrayList<>();
        imageList.add(R.drawable.banner1);
        imageList.add(R.drawable.banner2);
        imageList.add(R.drawable.banner1);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                // Chuyển đổi hình ảnh hiện tại
                currentPosition++;
                if (currentPosition >= imageList.size()) {
                    currentPosition = 0;
                }
                imageView.setImageResource(imageList.get(currentPosition));

                // Lập lịch chạy lại Runnable sau 3 giây
                handler.postDelayed(this, 2000);
            }
        };
    }
    @Override
    protected void onResume() {
        super.onResume();
        currentPosition = -1;
        handler.postDelayed(runnable, 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
}