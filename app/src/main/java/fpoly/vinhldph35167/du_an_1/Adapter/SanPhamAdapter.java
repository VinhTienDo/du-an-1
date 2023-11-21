package fpoly.vinhldph35167.du_an_1.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fpoly.vinhldph35167.du_an_1.Dao.LoaiHangDao;
import fpoly.vinhldph35167.du_an_1.Dao.SanPhamDao;
import fpoly.vinhldph35167.du_an_1.Fragment.fragment_san_pham;
import fpoly.vinhldph35167.du_an_1.Model.Loaihang;
import fpoly.vinhldph35167.du_an_1.Model.SanPham;
import fpoly.vinhldph35167.du_an_1.R;

public class SanPhamAdapter extends ArrayAdapter<SanPham> {
    private Context context;
    fragment_san_pham fragment;
    List<SanPham> list;
    TextView tvMasp, tvTensp, tvGiasp, tvLoai, tvSoluongban;
    ImageView imgDel;

    public SanPhamAdapter(@NonNull Context context,fragment_san_pham fragment, List<SanPham> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_san_pham, null);
        }
        final SanPham item = list.get(position);
        if (item != null){
            LoaiHangDao loaiHangDao = new LoaiHangDao(context);
            Loaihang loaihang = loaiHangDao.getID(String.valueOf(item.getMaloai()));
            tvMasp = v.findViewById(R.id.tvMasanpham);
            tvMasp.setText("Mã Sản phẩm: "+ item.getMasp());
            tvTensp = v.findViewById(R.id.tvTensanpham);
            tvTensp.setText("Tên Sản Phẩm: "+ item.getTensp());
            tvGiasp = v.findViewById(R.id.tvGiasanpham);
            tvGiasp.setText("Giá sản phẩm: "+ item.getGiasp());
            tvLoai = v.findViewById(R.id.tvTenloai);
            tvLoai.setText("Loại Hàng: "+ item.getMaloai());
            tvSoluongban = v.findViewById(R.id.tvSoluongban);
            tvSoluongban.setText("Số lượng bán: "+ item.getSoluongban());

            imgDel = v.findViewById(R.id.imgDeleteSp);
        }

        return v;
    }
}
