package fpoly.vinhldph35167.du_an_1.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import fpoly.vinhldph35167.du_an_1.Adapter.DonHangAdapter;
import fpoly.vinhldph35167.du_an_1.Dao.DonHangDao;
import fpoly.vinhldph35167.du_an_1.Dao.SanPhamDao;
import fpoly.vinhldph35167.du_an_1.Model.DonHang;
import fpoly.vinhldph35167.du_an_1.Model.SanPham;
import fpoly.vinhldph35167.du_an_1.R;


public class fragment_don_hang extends Fragment {

    ListView lvDonHang;
    ArrayList<DonHang> list;
    static DonHangDao dao;
    DonHangAdapter adapter;
    DonHang item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMadh, tvTensp, tvNgay, tvGia, tvSoluong, tvTrangThai;

    Button btnsave, btncancel;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    ArrayList<SanPham> listSanPham;
    SanPhamDao sanPhamDao;
    SanPham sanPham;
    int gia, soluong, masp;
    int posittionTV, posittionSanPham;



    public fragment_don_hang() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_don_hang, container, false);
        lvDonHang = v.findViewById(R.id.lvDonHang);
        fab = v.findViewById(R.id.fab);
        dao = new DonHangDao(getActivity());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lvDonHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1); // update
                return false;
            }
        });
        capnhatlv();
        return v;
    }
    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_don_hang);
        edMadh = dialog.findViewById(R.id.edMaDonHang);
        tvTensp = dialog.findViewById(R.id.tvTenSpDHdia);
        tvGia = dialog.findViewById(R.id.tvGiaDHdia);
        tvNgay = dialog.findViewById(R.id.tvNgayDHdia);
        tvSoluong = dialog.findViewById(R.id.tvSoLuongDHdia);
        tvTrangThai = dialog.findViewById(R.id.tvTrangThaiDHdia);
        btncancel = dialog.findViewById(R.id.btnCancelDH);
        btnsave = dialog.findViewById(R.id.btnSaveDH);

//        set ngay mua
        tvNgay.setText(sdf.format(new Date()));
        edMadh.setEnabled(false);
        sanPhamDao = new SanPhamDao(context);
        listSanPham = new ArrayList<SanPham>();
        listSanPham = (ArrayList<SanPham>) sanPhamDao.getAll();

//        edit
        if (type != 0){
            edMadh.setText(String.valueOf(item.getMadh()));
            tvTensp.setText(item.getTensp());
            tvNgay.setText(sdf.format(item.getNgay()));
            tvGia.setText(String.valueOf(item.getGia()));
            tvSoluong.setText(String.valueOf(item.getSoluongban()));
            tvTrangThai.setText(item.getTrangThai());
        }
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new DonHang();

                item.setTensp(tvTensp.getText().toString());
                item.setNgay(new Date());
                item.setGia(parseInt(tvGia.getText().toString(),0));
                item.setSoluongban(parseInt(tvSoluong.getText().toString(),0));
                item.setTrangThai(tvTrangThai.getText().toString());
                if (type == 0){
//                    insert
                    long insert = dao.insert(item);
                    if (insert > 0){
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                    capnhatlv();
                    dialog.dismiss();
                }else {
//                    update
                    item.setMadh(Integer.parseInt(edMadh.getText().toString()));
                    if (dao.update(item) > 0){
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                    capnhatlv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
    public static int parseInt(String string, int defaultValue) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    public void xoa(final String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xoá không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(id);
                capnhatlv();
                dialog.cancel();
                Toast.makeText(getContext(), "Đã xoá", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Toast.makeText(getContext(), "Không xoá", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }
    void capnhatlv(){
        list = (ArrayList<DonHang>) dao.getAll();
        adapter = new DonHangAdapter(getActivity(), this, list);
        lvDonHang.setAdapter(adapter);
    }
}