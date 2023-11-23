package fpoly.vinhldph35167.du_an_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import fpoly.vinhldph35167.du_an_1.Fragment.fragment_khach_hang;
import fpoly.vinhldph35167.du_an_1.Model.KhachHang;
import fpoly.vinhldph35167.du_an_1.Model.Loaihang;
import fpoly.vinhldph35167.du_an_1.R;

public class KhachHangAdapter extends ArrayAdapter<KhachHang> {
    private Context context;
    fragment_khach_hang frg;
    private ArrayList<KhachHang> list;
    TextView tvmakh, tvtenkh, tvnamsinh, tvsdt;
    ImageView imgDelKH;

    public KhachHangAdapter(@NonNull Context context, fragment_khach_hang frg, ArrayList<KhachHang> list) {
        super(context, 0,list);
        this.context=context;
        this.frg=frg;
        this.list=list;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_khach_hang, null);
        }

        final KhachHang item = list.get(position);

        if (item != null) {
//
            tvmakh = v.findViewById(R.id.tvMaKH);
            tvmakh.setText("Mã Khách Hàng: " + item.getMakh());
            tvtenkh = v.findViewById(R.id.tvTenKH);
            tvtenkh.setText("Tên Khách Hàng :" + item.getHoten());
            tvnamsinh = v.findViewById(R.id.tvNamsinhKH);
            tvnamsinh.setText("Năm Sinh:" + item.getNamsinh());
            tvsdt = v.findViewById(R.id.tvSdtKH);
            tvsdt.setText("Số điện thoại:" + item.getSodienthoai());
            imgDelKH = v.findViewById(R.id.imgDeleteKH);
        }
        imgDelKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    return v;
}
}
