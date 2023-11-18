package fpoly.vinhldph35167.du_an_1.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import fpoly.vinhldph35167.du_an_1.Adapter.SanPhamAdapter;
import fpoly.vinhldph35167.du_an_1.Dao.LoaiHangDao;
import fpoly.vinhldph35167.du_an_1.Dao.SanPhamDao;
import fpoly.vinhldph35167.du_an_1.Model.Loaihang;
import fpoly.vinhldph35167.du_an_1.Model.SanPham;
import fpoly.vinhldph35167.du_an_1.R;


public class fragment_san_pham extends Fragment {

    SanPhamDao sanPhamDao;
    RecyclerView recyclerSanpham;
    ArrayList<SanPham> list;
    EditText edSearch;
    SanPhamAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_san_pham, container, false);
        recyclerSanpham = view.findViewById(R.id.recycleSanPham);
        FloatingActionButton floatAdd = view.findViewById(R.id.floatAdd);
        sanPhamDao = new SanPhamDao(getContext());
        loadData();
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        return view;
    }
    private void loadData(){
        list = sanPhamDao.getDSSanPham();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerSanpham.setLayoutManager(linearLayoutManager);
        adapter = new SanPhamAdapter(getContext(), list, getDSLoaiHang(), sanPhamDao);
        recyclerSanpham.setAdapter(adapter);
    }
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_san_pham, null);
        builder.setView(view);

        EditText edMasp = view.findViewById(R.id.edMaSP);
        EditText edTensp = view.findViewById(R.id.edTenSP);
        EditText edGia = view.findViewById(R.id.edGiaSP);
        Button btnSaveSp = view.findViewById(R.id.btnSaveSanpham);
        Button btnUpdateSp = view.findViewById(R.id.btnUpdateSanpham);
        Button btnCancelSp = view.findViewById(R.id.btnCancelSanpham);
        Spinner spnLoaiHang = view.findViewById(R.id.spLoaihang);

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                getDSLoaiHang(),
                android.R.layout.simple_list_item_1,
                new String[]{"tenloai"},
                new int[]{android.R.id.text1}
        );
        spnLoaiHang.setAdapter(simpleAdapter);
        btnSaveSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String masp = edMasp.getText().toString();
                String tensp = edTensp.getText().toString();
                int gia = Integer.parseInt(edGia.getText().toString());
                HashMap<String, Object> hs = (HashMap<String, Object>) spnLoaiHang.getSelectedItem();
                String maloai = (String) hs.get("maloai");
                boolean check = sanPhamDao.themSanPhamMoi(masp, tensp, gia, maloai);
                if (check){
                    Toast.makeText(getContext(), "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                }else {
                    Toast.makeText(getContext(), "Thêm sản phẩm không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private ArrayList<HashMap<String, Object>> getDSLoaiHang(){
        LoaiHangDao loaiHangDao = new LoaiHangDao(getContext());
        ArrayList<Loaihang> list = loaiHangDao.getDSLoaiHang();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();

        for (Loaihang loai: list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maloai", loai.getMaloai());
            hs.put("tenloai", loai.getTenloai());
            listHM.add(hs);
        }
        return listHM;
    }
}