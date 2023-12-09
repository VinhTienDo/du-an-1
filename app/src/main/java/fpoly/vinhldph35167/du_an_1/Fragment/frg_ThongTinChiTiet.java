package fpoly.vinhldph35167.du_an_1.Fragment;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;

import fpoly.vinhldph35167.du_an_1.Dao.LoaiHangDao;
import fpoly.vinhldph35167.du_an_1.Model.Loaihang;
import fpoly.vinhldph35167.du_an_1.Model.SanPham;
import fpoly.vinhldph35167.du_an_1.R;


public class frg_ThongTinChiTiet extends Fragment {

    SanPham item;
    Toolbar toolbar;
    ImageView imageViewSP;
    TextView tenspct, giaspct, soluongbanSPCT, loaihangSPCT, motaspct, khohang;

LoaiHangDao lhDao;

  Loaihang loaihang;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            item = (SanPham) getArguments().getSerializable("sanPhamChiTiet");
            Log.d("UpdateFragment", "sanphachitiet: " + item);
        }
    }



    public frg_ThongTinChiTiet() {
        // Required empty public constructor
    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frg__thong_tin_chi_tiet, container, false);

        requireActivity().findViewById(R.id.nvView).setVisibility(View.GONE);

        NumberFormat numberFormat = NumberFormat.getNumberInstance();

        toolbar = view.findViewById(R.id.toolbarSanPham);

        AppCompatActivity appCompatActivity= (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appCompatActivity.getSupportActionBar().setTitle("Thông tin sản phẩm");

        imageViewSP= view.findViewById(R.id.imageViewSanPham);
        tenspct= view.findViewById(R.id.tvTenSPCT);
        giaspct = view.findViewById(R.id.tvGiaSPCT);
        soluongbanSPCT= view.findViewById(R.id.tvSoluongbanSPCT);
        loaihangSPCT= view.findViewById(R.id.tvLoaiHangSPCT);
        khohang= view.findViewById(R.id.tvKhoHang);
        motaspct= view.findViewById(R.id.tvMotaSPCT);

        if(item!= null){
            tenspct.setText(item.getTensp());
            giaspct.setText(String.valueOf(item.getGiasp()));

            soluongbanSPCT.setText(String.valueOf(item.getSoluongban()));

            lhDao = new LoaiHangDao(getContext());
            Loaihang loaihang = lhDao.getID(String.valueOf(item.getMaloai()));
            loaihangSPCT.setText("Loại hàng: "+loaihang.getMaloai());

            motaspct.setText(item.getMota());
            khohang.setText("Kho hàng" +item.getKhoHang());

        }

        // Inflate the layout for this fragment
        return view;

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }
}