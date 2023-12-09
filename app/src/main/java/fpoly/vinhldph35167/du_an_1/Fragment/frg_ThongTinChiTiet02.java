package fpoly.vinhldph35167.du_an_1.Fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import fpoly.vinhldph35167.du_an_1.Dao.LoaiHangDao;
import fpoly.vinhldph35167.du_an_1.Dao.SanPhamDao;
import fpoly.vinhldph35167.du_an_1.Model.SanPham;
import fpoly.vinhldph35167.du_an_1.R;


public class frg_ThongTinChiTiet02 extends Fragment {
    SanPham item;
    Toolbar toolbar;
    ImageView imageViewSP;
    TextView tenspct, giaspct, tentknd, mauspct, hangspct, motaspct, khohang1;
    ImageButton ibnguoidung;

    LoaiHangDao loaihangDAO;
    ImageView imageViewSanPham;


    private int matknd;

    SanPhamDao dao;

    RecyclerView recyclerView;
    ArrayList<SanPham> list;

    public frg_ThongTinChiTiet02() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frg__thong_tin_chi_tiet02, container, false);
    }
}