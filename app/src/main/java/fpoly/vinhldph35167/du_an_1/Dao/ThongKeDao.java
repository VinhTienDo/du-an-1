package fpoly.vinhldph35167.du_an_1.Dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fpoly.vinhldph35167.du_an_1.Database.Dbhelper;
import fpoly.vinhldph35167.du_an_1.Model.SanPham;
import fpoly.vinhldph35167.du_an_1.Model.Top;

public class ThongKeDao {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public ThongKeDao(Context context){
        this.context = context;
        Dbhelper dbhelper = new Dbhelper(context);
        db = dbhelper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public List<Top> getTop() {
        String sqlTop = "SELECT masp,count(masp) as soLuongban FROM DONHANG GROUP BY masp ORDER BY soLuongban DESC LIMIT 10";
        List<Top> list = new ArrayList<Top>();
        SanPhamDao sanPhamDao = new SanPhamDao(context);
        Cursor cursor = db.rawQuery(sqlTop, null);
        while (cursor.moveToNext()) {
            Top top = new Top();
            @SuppressLint("Range") SanPham sanPham = sanPhamDao.getID(cursor.getString(cursor.getColumnIndex("masp")));
            top.setTensp(sanPham.getTensp());
            top.setSoLuongban(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuongban"))));
            list.add(top);

        }
        return list;
    }
    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay){
        String sqlDoanhThu = "SELECT SUM(tongtien) as doanhthu FROM DONHANG WHERE ngay BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<Integer>();
        Cursor cursor = db.rawQuery(sqlDoanhThu, new String[]{tuNgay, denNgay});
        while (cursor.moveToNext()){
            try {
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhthu"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }
}
