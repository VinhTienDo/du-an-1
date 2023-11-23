package fpoly.vinhldph35167.du_an_1.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

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
//        capNhatLv();



return v;
    }
    void capnhatKH(){
//        list = (ArrayList<Loaihang>) dao.getAll();
//        adapter = new LoaiHangAdapter(getActivity(), this, list);
//        lvKhachHang.setAdapter((ListAdapter) adapter);
        list=(ArrayList<KhachHang>) dao.getAll();

    }
    public void xoa(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setTitle("Bạn có muốn xoá không?");
        builder.setCancelable(true);
    builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dao.delete(Id);
            capnhatKH();
        }
    })
    }
}