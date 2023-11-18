package fpoly.vinhldph35167.du_an_1.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import fpoly.vinhldph35167.du_an_1.Dao.SanPhamDao;
import fpoly.vinhldph35167.du_an_1.Model.SanPham;
import fpoly.vinhldph35167.du_an_1.R;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder>{
    private Context context;
    private ArrayList<SanPham> list;
    private ArrayList<HashMap<String, Object>> listHM;
    private SanPhamDao sanPhamDao;

    public SanPhamAdapter(Context context, ArrayList<SanPham> list, ArrayList<HashMap<String, Object>> listHM, SanPhamDao sanPhamDao){
        this.context = context;
        this.list = list;
        this.listHM = listHM;
        this.sanPhamDao = sanPhamDao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_san_pham, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvMasanpham.setText(list.get(position).getMasp());
        holder.tvTensanpham.setText(list.get(position).getTensp());
        holder.tvTenloai.setText(list.get(position).getTenloai());
        holder.tvGiasanpham.setText(list.get(position).getGiasp());
        holder.tvSoluongban.setText(list.get(position).getSoluongban());

        holder.imgThemSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog(list.get(holder.getAdapterPosition()));
            }
        });
        holder.imgDeleteSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = sanPhamDao.xoaSanPham(Integer.parseInt(list.get(holder.getAdapterPosition()).getMasp()));
                switch (check){
                    case 1:
                        Toast.makeText(context, "Xoá thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                        break;
                    case 0:
                        Toast.makeText(context, "Xoá không thành công", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvMasanpham, tvTensanpham, tvTenloai, tvGiasanpham, tvSoluongban;
        ImageView imgThemSp, imgDeleteSp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMasanpham = itemView.findViewById(R.id.tvMasanpham);
            tvTensanpham = itemView.findViewById(R.id.tvTensanpham);
            tvTenloai = itemView.findViewById(R.id.tvTenloai);
            tvGiasanpham = itemView.findViewById(R.id.tvGiasanpham);
            tvSoluongban = itemView.findViewById(R.id.tvSoluongban);
            imgThemSp = itemView.findViewById(R.id.imgThemSp);
            imgDeleteSp = itemView.findViewById(R.id.imgDeleteSp);
        }
    }
    private void showdialog(SanPham sanPham){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_san_pham, null);
        builder.setView(view);

        EditText edMasp = view.findViewById(R.id.edMaSP);
        EditText edTensp = view.findViewById(R.id.edTenSP);
        EditText edGia = view.findViewById(R.id.edGiaSP);
        EditText edSoluongban = view.findViewById(R.id.edSoluonbanSP);
        Spinner spnloaihang = view.findViewById(R.id.spLoaihang);

        edMasp.setText(sanPham.getMasp());
        edTensp.setText(sanPham.getTensp());
        edGia.setText(sanPham.getGiasp());
        edSoluongban.setText(sanPham.getSoluongban());

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                context,
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"tenloai"},
                new int[]{android.R.id.text1}
        );
        spnloaihang.setAdapter(simpleAdapter);

        int index = 0;
        int position = -1;
        for (HashMap<String, Object> item : listHM){
            if (item.get("maloai") == sanPham.getMaloai()){
                position = index;
            }
            index++;
        }
        spnloaihang.setSelection(position);
    }
    private void loadData(){
        list.clear();
        list = sanPhamDao.getDSSanPham();
        notifyDataSetChanged();
    }
}
