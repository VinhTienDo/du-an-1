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

import fpoly.vinhldph35167.du_an_1.Adapter.LoaiHangAdapter;
import fpoly.vinhldph35167.du_an_1.Dao.LoaiHangDao;
import fpoly.vinhldph35167.du_an_1.Model.Loaihang;
import fpoly.vinhldph35167.du_an_1.R;


public class fragment_loai_hang extends Fragment {

    ListView lvLoaiHang;
    ArrayList<Loaihang> list;
    static LoaiHangDao dao;
    LoaiHangAdapter adapter;
    Loaihang item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaLoaihang, edTenLoaihang, edSoLuongNhap, edSoLuongTon;
    Button btnSave, btnCancel;

    public fragment_loai_hang() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_loai_hang, container, false);
        lvLoaiHang = v.findViewById(R.id.lvLoaiHang);
        fab = v.findViewById(R.id.fab);
        dao = new LoaiHangDao(getActivity());
        capNhatLv();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lvLoaiHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(),1);
                return false;
            }
        });
        return v;
    }
    void capNhatLv(){
        list = (ArrayList<Loaihang>) dao.getAll();
        adapter = new LoaiHangAdapter(getActivity(), this, list);
        lvLoaiHang.setAdapter((ListAdapter) adapter);
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
                capNhatLv();
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
        dialog.setContentView(R.layout.dialog_loai_hang);
        edMaLoaihang = dialog.findViewById(R.id.edMaLoaiHang);
        edTenLoaihang = dialog.findViewById(R.id.edTenLoaiHang);
        edSoLuongNhap = dialog.findViewById(R.id.edSoLuongNhapLoaiHang);
        edSoLuongTon = dialog.findViewById(R.id.edSoLuongTonLoaiHang);
        btnCancel = dialog.findViewById(R.id.btnCancelLS);
        btnSave = dialog.findViewById(R.id.btnSaveLS);

        edMaLoaihang.setEnabled(false);
        if (type != 0){
            edMaLoaihang.setText(String.valueOf(item.getMaloai()));
            edTenLoaihang.setText(item.getTenloai());
            edSoLuongNhap.setText(String.valueOf(item.getSoluongnhap()));
            edSoLuongTon.setText(String.valueOf(item.getSoluongton()));
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new Loaihang();
                item.setTenloai(edTenLoaihang.getText().toString());
                item.setSoluongnhap(Integer.parseInt(edSoLuongNhap.getText().toString()));
                item.setSoluongton(Integer.parseInt(edSoLuongTon.getText().toString()));
                if(validate() > 0){
                    if (type == 0){
                        if (dao.insert(item) > 0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        item.setMaloai(Integer.parseInt(edMaLoaihang.getText().toString()));
                        if (dao.update(item) > 0){
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
    public int validate(){
        int check = 1;
        if (edTenLoaihang.getText().length() == 0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}