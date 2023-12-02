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

import fpoly.vinhldph35167.du_an_1.Fragment.fragment_top;
import fpoly.vinhldph35167.du_an_1.Model.Top;
import fpoly.vinhldph35167.du_an_1.R;

public class TopAdapter extends ArrayAdapter<Top> {
    private Context context;
    fragment_top fragment;
    ArrayList<Top> list;
    TextView tvTenSp,tvSoLuongBan;
    ImageView imgDel;

    public TopAdapter(Context context, fragment_top fragment, ArrayList<Top> list) {
        super(context, 0 ,list);
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
            v = inflater.inflate(R.layout.item_top,null);
        }
        final Top item = list.get(position);
        if (item != null){
            tvTenSp = v.findViewById(R.id.tvTensanphamtop);
            tvTenSp.setText("Sản Phẩm: "+item.getTensp());

            tvSoLuongBan = v.findViewById(R.id.tvSL);
            tvSoLuongBan.setText("Số lượng: "+item.getSoLuongban());
        }
        return v;
    }
}
