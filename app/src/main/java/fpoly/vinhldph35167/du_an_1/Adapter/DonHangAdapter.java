package fpoly.vinhldph35167.du_an_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import fpoly.vinhldph35167.du_an_1.Dao.SanPhamDao;
import fpoly.vinhldph35167.du_an_1.Fragment.fragment_don_hang;
import fpoly.vinhldph35167.du_an_1.Model.DonHang;
import fpoly.vinhldph35167.du_an_1.Model.SanPham;
import fpoly.vinhldph35167.du_an_1.R;

public class DonHangAdapter extends ArrayAdapter<DonHang> {
    private Context context;
    fragment_don_hang fragment;
    private ArrayList<DonHang> list;
    TextView tvMaDH, tvTenSP, tvGia, tvSoluong, tvTrangThai, tvNgayMua;
    ImageView imgDel;
    SanPhamDao sanPhamDao;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public DonHangAdapter(@NonNull Context context, fragment_don_hang fragment, ArrayList<DonHang> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_don_hang, null);
        }
        final DonHang item = list.get(position);
        if (item != null){
            tvMaDH = v.findViewById(R.id.tvMadonhang);
            tvMaDH.setText("Mã Đơn: " + item.getMadh());

            sanPhamDao = new SanPhamDao(context);
            SanPham sanPham = sanPhamDao.getID(String.valueOf(item.getMasp()));

            tvTenSP = v.findViewById(R.id.tvTenspDH);
            tvTenSP.setText("Tên Sản Phẩm: " + sanPham.getTensp());
            tvGia = v.findViewById(R.id.tvGiaDH);
            tvGia.setText("Giá: " + item.getGia());
            tvSoluong = v.findViewById(R.id.tvSoluongDH);
            tvSoluong.setText("Số Luọng: " + item.getSoluongban());
            tvNgayMua = v.findViewById(R.id.tvNgayMua);
            tvNgayMua.setText("Ngày Mua: " + sdf.format(item.getNgay()));
            tvTrangThai = v.findViewById(R.id.tvTrangThaiDH);
            tvTrangThai.setText("Trạng Thái: " + item.getTrangThai());
            imgDel = v.findViewById(R.id.imgDeleteDH);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.getMadh()));
            }
        });
        return v;
    }
}
