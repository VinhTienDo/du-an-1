package fpoly.vinhldph35167.du_an_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import fpoly.vinhldph35167.du_an_1.Model.Loaihang;
import fpoly.vinhldph35167.du_an_1.R;

public class LoaiHangSpinnerAdapter extends ArrayAdapter<Loaihang> {
    private Context context;
    ArrayList<Loaihang> list;
    TextView tvMaLoaiHang, tvTenLoaihang;

    public LoaiHangSpinnerAdapter(@NonNull Context context, ArrayList<Loaihang> list){
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_loai_hang_spinner, null);

        }
        final Loaihang item = list.get(position);
        if (item != null){
            tvMaLoaiHang= v.findViewById(R.id.tvMaLoaihangSp);
            tvMaLoaiHang.setText(item.getMaloai() + ". ");

            tvTenLoaihang = v.findViewById(R.id.tvtensanphamSp);
            tvTenLoaihang.setText(item.getTenloai());

        }
        return v;
    }
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_loai_hang_spinner, null);
        }
        final Loaihang item = list.get(position);
        if (item != null){
            tvMaLoaiHang = v.findViewById(R.id.tvMaLoaihangSp);
            tvMaLoaiHang.setText(item.getMaloai() + ". ");

            tvTenLoaihang = v.findViewById(R.id.tvTenLoaiHangsp);
            tvTenLoaihang.setText(item.getTenloai());
        }
        return v;
    }
}
