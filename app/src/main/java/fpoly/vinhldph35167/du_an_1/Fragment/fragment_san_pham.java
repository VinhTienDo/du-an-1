package fpoly.vinhldph35167.du_an_1.Fragment;

import static java.lang.Integer.parseInt;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import fpoly.vinhldph35167.du_an_1.Adapter.LoaiHangSpinnerAdapter;
import fpoly.vinhldph35167.du_an_1.Adapter.SanPhamAdapter;
import fpoly.vinhldph35167.du_an_1.Dao.LoaiHangDao;
import fpoly.vinhldph35167.du_an_1.Dao.SanPhamDao;
import fpoly.vinhldph35167.du_an_1.Model.Loaihang;
import fpoly.vinhldph35167.du_an_1.Model.SanPham;
import fpoly.vinhldph35167.du_an_1.R;


public class fragment_san_pham extends Fragment {

   ListView lvSanPham;
   SanPhamDao sanPhamDao;
   SanPhamAdapter adapter;
   SanPham item;
   List<SanPham> list;

   FloatingActionButton fab;
   Dialog dialog;
   EditText edMasp, edTensp, edGiasp, edSoluongban;
   Spinner spinner;
   Button btnSave, btnCancel;

   LoaiHangSpinnerAdapter spinnerAdapter;
   ArrayList<Loaihang> listLoaiHang;
   LoaiHangDao loaiHangDao;
   Loaihang loaihang;
   int maloaiHang, position;
   private SearchView searchView;


   public fragment_san_pham(){

   }
   @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
       setHasOptionsMenu(true);
   }
   @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater){
       inflater.inflate(R.menu.menu2, menu);
      SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
      MenuItem searchItem = menu.findItem(R.id.search);
      searchView = (SearchView) searchItem.getActionView();
      searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
      searchView.setMaxWidth(Integer.MAX_VALUE);

      searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
         @Override
         public boolean onQueryTextSubmit(String query) {
            return true;
         }

         @Override
         public boolean onQueryTextChange(String newText) {
            handleSearch(newText);
            return true;
         }
      });
      super.onCreateOptionsMenu(menu, inflater);
   }
   @Override
   public View  onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
      View v = inflater.inflate(R.layout.fragment_san_pham, container, false);
      lvSanPham = v.findViewById(R.id.lvSanPham);
      sanPhamDao = new SanPhamDao(getActivity());
      capNhatLv();
      fab = v.findViewById(R.id.fab);
      fab.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              openDialog(getActivity(), 0);
          }
      });
      lvSanPham.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
          @Override
          public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
              item = list.get(position);
              openDialog(getActivity(), 1);
              return false;
          }
      });
      return v;
   }
   void capNhatLv(){
       list = (ArrayList<SanPham>) sanPhamDao.getAll();
       adapter = new SanPhamAdapter(getActivity(), this, list);
       lvSanPham.setAdapter(adapter);
   }
   public void xoa(final String Id){
       AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
       builder.setTitle("Delete");
       builder.setMessage("Bạn có muốn xoá không");
       builder.setCancelable(true);

       builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               sanPhamDao.delete(Id);
               capNhatLv();
               dialog.cancel();
               Toast.makeText(getContext(), "Đã xoá", Toast.LENGTH_SHORT).show();
           }
       });
       builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               dialog.cancel();
               Toast.makeText(getContext(), "Không xoá", Toast.LENGTH_SHORT).show();
           }
       });
       AlertDialog alert = builder.create();
       builder.show();
   }
   protected  void openDialog(final Context context, final int type){
       dialog = new Dialog(context);
       dialog.setContentView(R.layout.dialog_san_pham);
       edMasp = dialog.findViewById(R.id.edMaSP);
       edTensp = dialog.findViewById(R.id.edTenSP);
       edGiasp = dialog.findViewById(R.id.edGiaSP);
       edSoluongban = dialog.findViewById(R.id.edSoluonbanSP);
       spinner = dialog.findViewById(R.id.spLoaihang);
       btnSave = dialog.findViewById(R.id.btnSaveSanpham);
       btnCancel = dialog.findViewById(R.id.btnCancelSanpham);

       listLoaiHang = new ArrayList<Loaihang>();
       loaiHangDao = new LoaiHangDao(context);
       listLoaiHang = (ArrayList<Loaihang>) loaiHangDao.getAll();

       spinnerAdapter = new LoaiHangSpinnerAdapter(context, listLoaiHang);
       spinner.setAdapter(spinnerAdapter);

       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               maloaiHang = listLoaiHang.get(position).getMaloai();
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
//        kiem tra type insert hay update
       edMasp.setEnabled(false);
       if (type != 0){
           edMasp.setText(String.valueOf(item.getMasp()));
           edTensp.setText(item.getTensp());
           edGiasp.setText(String.valueOf(item.getGiasp()));
           edSoluongban.setText(String.valueOf(item.getSoluongban()));
           for (int i = 0; i < listLoaiHang.size(); i++){
               if (item.getMaloai() == (listLoaiHang.get(i).getMaloai())){
                   position = i;
               }
               Log.i("demo", "posSanPham" +position);
               spinner.setSelection(position);
           }
           btnCancel.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   dialog.dismiss();
               }
           });
           btnSave.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   item = new SanPham();
                   item.setTensp(edTensp.getText().toString());
                   item.setGiasp(parseInt(edGiasp.getText().toString(), 0));
                   item.setMaloai(maloaiHang);
                   item.setSoluongban(parseInt(edSoluongban.getText().toString(), 0));
                   if (validate() > 0){
                       if (type == 0){
                           if (sanPhamDao.insert(item)>0){
                               Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                           }else {
                               Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                           }
                       }else {
                           item.setMasp(Integer.parseInt(edMasp.getText().toString()));
                           if (sanPhamDao.update(item)>0){
                               Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                           }else {
                               Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                           }
                       }
                       capNhatLv();
                       dialog.dismiss();
                   }
               }
           });
           dialog.show();
       }
   }
   public int validate(){
       int check= 1;
       if (edTensp.getText().length() == 0 || edGiasp.getText().length() == 0 || edSoluongban.getText().length() == 0){
           Toast.makeText(getContext(), "Phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
           check = -1;
       }
       return check;
   }
    public static int parseInt(String string, int defaultValue) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    private void handleSearch(String query) {
        List<SanPham> listSearch = new ArrayList<>();
        for (SanPham sanPham : list) {
            if (sanPham.getTensp().toLowerCase().contains(query.toLowerCase())) {
                listSearch.add(sanPham);
            }
        }
        adapter = new SanPhamAdapter(getActivity(), this, listSearch);
        lvSanPham.setAdapter(adapter);

    }
    // Sắp xếp sp theo tên tăng dần
    private void sortBooksByNameAscending() {
        Collections.sort(list, new Comparator<SanPham>() {
            @Override
            public int compare(SanPham sanPham1, SanPham sanPham2) {
                return sanPham1.getTensp().compareTo(sanPham2.getTensp());

            }
        });
        adapter.notifyDataSetChanged();
    }
    // Sắp xếp sp theo tên giảm dần
    private void sortBooksByNameDescending() {
        Collections.sort(list, new Comparator<SanPham>() {
            @Override
            public int compare(SanPham sanPham1, SanPham sanPham2) {
                return sanPham1.getTensp().compareTo(sanPham2.getTensp());
            }
        });
        adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.asc){
            sortBooksByNameAscending();
            return true;
        }else if(id == R.id.desc){
            sortBooksByNameDescending();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}