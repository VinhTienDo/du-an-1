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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fpoly.vinhldph35167.du_an_1.Adapter.KhachHangAdapter;
import fpoly.vinhldph35167.du_an_1.Adapter.LoaiHangAdapter;
import fpoly.vinhldph35167.du_an_1.Dao.KhachHangDao;
import fpoly.vinhldph35167.du_an_1.Dao.LoaiHangDao;
import fpoly.vinhldph35167.du_an_1.Model.KhachHang;
import fpoly.vinhldph35167.du_an_1.Model.Loaihang;
import fpoly.vinhldph35167.du_an_1.R;


public class fragment_khach_hang extends Fragment {

    ListView lvKhachHang;
ArrayList<KhachHang> list;
static KhachHangDao dao;
KhachHangAdapter adapter;
KhachHang item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaKH, edTenKH, edNamSinhKH, edSdtKH;
    Button btnSaveKH, btnCancelKH;

    public fragment_khach_hang() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_khach_hang, container, false);
        lvKhachHang = v.findViewById(R.id.lvKhachHang);
        fab = v.findViewById(R.id.fab);
        dao = new KhachHangDao(getActivity());
        capnhatKH();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lvKhachHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });
        return v;
    }
    void capnhatKH(){
        list = (ArrayList<KhachHang>) dao.getAll();
        adapter = new KhachHangAdapter(getActivity(), this, list);
        lvKhachHang.setAdapter((ListAdapter) adapter);

    }
    public void xoa(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setTitle("Bạn có muốn xoá không?");
        builder.setCancelable(true);
    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dao.delete(Id);
            capnhatKH();
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
    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_khach_hang);
        edMaKH = dialog.findViewById(R.id.edMaKH);
        edTenKH = dialog.findViewById(R.id.edTenKH);
        edSdtKH = dialog.findViewById(R.id.edSdtKH);
        edNamSinhKH = dialog.findViewById(R.id.edNamSinhKH);
        btnCancelKH = dialog.findViewById(R.id.btnCancelKH);
        btnSaveKH = dialog.findViewById(R.id.btnSaveKh);


        if (type != 0){
            edMaKH.setText(String.valueOf(item.getMakh()));
            edTenKH.setText(item.getHoten());
            edNamSinhKH.setText(item.getNamsinh());
            edSdtKH.setText(String.valueOf(item.getSodienthoai()));
        }
        btnCancelKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSaveKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new KhachHang();
                item.setMakh(edMaKH.getText().toString());
                item.setHoten(edTenKH.getText().toString());
                item.setNamsinh(edNamSinhKH.getText().toString());
                item.setSodienthoai(Integer.parseInt(edSdtKH.getText().toString()));
                if (validate()>0){
                    if (type == 0){
                        if (dao.insert(item)>0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        item.setMakh(String.valueOf(edMaKH.getText().toString()));
                        if (dao.update(item)>0){
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capnhatKH();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
    public int validate(){
        int check = 1;
        if (edMaKH.getText().length() == 0 || edTenKH.getText().length() == 0 || edSdtKH.getText().length() == 0 || edNamSinhKH.getText().length() ==0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}