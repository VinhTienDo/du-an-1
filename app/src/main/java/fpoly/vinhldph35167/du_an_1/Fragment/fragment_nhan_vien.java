package fpoly.vinhldph35167.du_an_1.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import fpoly.vinhldph35167.du_an_1.Adapter.LoaiHangAdapter;
import fpoly.vinhldph35167.du_an_1.Adapter.NhanVienAdaper;
import fpoly.vinhldph35167.du_an_1.Dao.LoaiHangDao;
import fpoly.vinhldph35167.du_an_1.Dao.NhanVienDao;
import fpoly.vinhldph35167.du_an_1.Model.Loaihang;
import fpoly.vinhldph35167.du_an_1.Model.NhanVien;
import fpoly.vinhldph35167.du_an_1.R;

public class fragment_nhan_vien extends Fragment {

//    NhanVienDao nhanVienDao;
//    RecyclerView recyclerView;
//    ArrayList<NhanVien> list;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_nhan_vien,container,false);
//        recyclerView = view.findViewById(R.id.rvNhanVien);
//        FloatingActionButton floatAdd = view.findViewById(R.id.fab);
//        loadData();
//        floatAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showdialog();
//            }
//        });
//        return view;
//    }
//    private void loadData() {
//        nhanVienDao = new NhanVienDao(getContext());
//        list = nhanVienDao.getDSNV();
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(linearLayoutManager);
//        NhanVienAdaper adaper= new NhanVienAdaper(list,getContext());
//        recyclerView.setAdapter(adaper);
//    }
//
//    private void showdialog(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        LayoutInflater inflater = getLayoutInflater();
//        View view = inflater.inflate(R.layout.dialog_nhan_vien,null);
//        builder.setView(view);
//
//        final EditText edtManv = view.findViewById(R.id.edMaNV);
//        final EditText edtHoten = view.findViewById(R.id.edTenNV);
//        final EditText edtSodt = view.findViewById(R.id.edSdtNV);
//        final EditText edtNamsinh = view.findViewById(R.id.edNamSinh);
//        final Button btnLuu = view.findViewById(R.id.btnSaveNV);
//
//        btnLuu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String manv = edtManv.getText().toString().trim();
//                String hoten = edtHoten.getText().toString().trim();
//                Integer sodt = Integer.valueOf(edtSodt.getText().toString().trim());
//                String namsinh = edtNamsinh.getText().toString().trim();
//
//                boolean check = nhanVienDao.themNhanVien(manv,hoten,sodt,namsinh);
//                if(check) {
//                    Toast.makeText(getContext(),"Thêm thành công",Toast.LENGTH_LONG).show();
//                    loadData();
//                }else {
//                    Toast.makeText(getContext(),"Thêm thất bại",Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//
//    }

    NhanVienDao nhanVienDao;
    RecyclerView recyclerView;
    ArrayList<NhanVien> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nhan_vien,container,false);
        recyclerView = view.findViewById(R.id.rvNhanVien);
        FloatingActionButton floatAdd = view.findViewById(R.id.fab);
        loadData();
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog();
            }
        });
        return view;
    }
    private void loadData() {
        nhanVienDao = new NhanVienDao(getContext());
        list = nhanVienDao.getDSNV();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        NhanVienAdaper adaper= new NhanVienAdaper(list,getContext());
        recyclerView.setAdapter(adaper);
    }

    private void showdialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_nhan_vien,null);
        builder.setView(view);

        final EditText edtManv = view.findViewById(R.id.edMaNV);
        final EditText edtHoten = view.findViewById(R.id.edTenNV);
        final EditText edtSodt = view.findViewById(R.id.edSdtNV);
        final EditText edtNamsinh = view.findViewById(R.id.edNamSinh);
        final Button btnLuu = view.findViewById(R.id.btnSaveNV);

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String manv = edtManv.getText().toString().trim();
                String hoten = edtHoten.getText().toString().trim();
                Integer sodt = Integer.valueOf(edtSodt.getText().toString().trim());
                String namsinh = edtNamsinh.getText().toString().trim();

                boolean check = nhanVienDao.themNhanVien(manv,hoten, sodt,namsinh);
                if(check) {
                    Toast.makeText(getContext(),"Thêm thành công",Toast.LENGTH_LONG).show();
                    loadData();
                }else {
                    Toast.makeText(getContext(),"Thêm thất bại",Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.create().show();
    }
}




