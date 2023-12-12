package fpoly.vinhldph35167.du_an_1.Adapter;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.vinhldph35167.du_an_1.Dao.NhanVienDao;
import fpoly.vinhldph35167.du_an_1.Fragment.fragment_nhan_vien;
import fpoly.vinhldph35167.du_an_1.Model.Loaihang;
import fpoly.vinhldph35167.du_an_1.Model.NhanVien;
import fpoly.vinhldph35167.du_an_1.R;

public class NhanVienAdaper extends RecyclerView.Adapter<NhanVienAdaper.ViewHolder> {

    private ArrayList<NhanVien> list;
    private Context context;

    public NhanVienAdaper(ArrayList<NhanVien> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public NhanVienAdaper.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_nhan_vien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NhanVienAdaper.ViewHolder holder, int position) {
        holder.txtmaNV.setText("Mã nhân viên:" + list.get(position).getManv());
        holder.txthoTen.setText("Tên nhân viên:" + list.get(position).getHoten());
        holder.txtSDT.setText("Số điện thoại:" + list.get(position).getSodienthoai());
        holder.txtnamSinh.setText("Năm sinh:" + list.get(position).getNamsinh());

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc muốn xóa nhân viên không?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String manv = list.get(holder.getAdapterPosition()).getManv();
                        NhanVienDao nhanVienDao = new NhanVienDao(context);
                        boolean kiemTra = nhanVienDao.deleteNhanVien(Integer.parseInt(manv));
                        if (kiemTra) {
                            list.remove(holder.getAdapterPosition());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtmaNV, txthoTen, txtSDT, txtnamSinh;
        ImageView imgDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmaNV = itemView.findViewById(R.id.tvMaNV);
            txthoTen = itemView.findViewById(R.id.tvTenNV);
            txtSDT = itemView.findViewById(R.id.tvSdtNV);
            txtnamSinh = itemView.findViewById(R.id.tvNamSinhNV);
            imgDelete = itemView.findViewById(R.id.imgDeleteLS);
        }
    }
}
