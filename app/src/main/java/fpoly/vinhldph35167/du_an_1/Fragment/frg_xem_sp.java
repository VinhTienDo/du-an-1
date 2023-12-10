package fpoly.vinhldph35167.du_an_1.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fpoly.vinhldph35167.du_an_1.Adapter.SanPhamAdapter;
import fpoly.vinhldph35167.du_an_1.Dao.SanPhamDao;
import fpoly.vinhldph35167.du_an_1.Model.SanPham;
import fpoly.vinhldph35167.du_an_1.R;


public class frg_xem_sp extends Fragment {
    ListView lvXemSanPham;
    SanPhamDao sanPhamDao;
    SanPhamAdapter adapter;
    SanPham item;
    List<SanPham> list;


    public frg_xem_sp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_frg_xem_sp, container, false);
        lvXemSanPham = v.findViewById(R.id.lvXemSanPham);
        sanPhamDao = new SanPhamDao(getActivity());
        capNhatLv();

        return v;
    }
    void capNhatLv() {
        list = (ArrayList<SanPham>) sanPhamDao.getAll();
        adapter = new SanPhamAdapter(getContext(), list,getActivity(), sanPhamDao);
        lvXemSanPham.setAdapter(adapter);
    }


}