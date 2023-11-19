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

import java.util.ArrayList;

import fpoly.vinhldph35167.du_an_1.Fragment.fragment_loai_hang;
import fpoly.vinhldph35167.du_an_1.Model.Loaihang;
import fpoly.vinhldph35167.du_an_1.R;

public class LoaiHangAdapter extends ArrayAdapter<Loaihang> {
    private Context context;
    fragment_loai_hang fragment;
    private ArrayList<Loaihang> list;
    TextView tvMaloai, tvTenloai, tvSoluongnhap, tvSoluongton;
    ImageView imgdel;

    public LoaiHangAdapter(@NonNull Context context, fragment_loai_hang fragment, ArrayList<Loaihang> list) {
        super(context, 0 ,list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_loai_hang, null);
        }
        final Loaihang item = list.get(position);
        if (item != null){
            tvMaloai = v.findViewById(R.id.tvMaLoaiHang);
            tvMaloai.setText("Mã Loại: "+ item.getMaloai());
            tvTenloai = v.findViewById(R.id.tvTenLoaiHang);
            tvTenloai.setText("Tên Loại: "+ item.getTenloai());
            tvSoluongnhap = v.findViewById(R.id.tvSoLuongNhap);
            tvSoluongnhap.setText("Số lượng nhập: "+ item.getSoluongnhap());
            tvSoluongton = v.findViewById(R.id.tvSoLuongTon);
            tvSoluongton.setText("Số Lượng tồn: "+ item.getSoluongton());

            imgdel = v.findViewById(R.id.imgDeleteLS);
        }
        imgdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.getMaloai()));
            }
        });
        return v;
    }
}
