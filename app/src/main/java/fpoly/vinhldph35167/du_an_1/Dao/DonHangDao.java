package fpoly.vinhldph35167.du_an_1.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fpoly.vinhldph35167.du_an_1.Database.Dbhelper;
import fpoly.vinhldph35167.du_an_1.Model.DonHang;
import fpoly.vinhldph35167.du_an_1.Model.SanPham;
import fpoly.vinhldph35167.du_an_1.Model.Top;

public class DonHangDao {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public DonHangDao(Context context) {
        this.context = context;
        Dbhelper dbHelper = new Dbhelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(DonHang obj){
        ContentValues values = new ContentValues();
        values.put("masp", obj.getMasp());
        values.put("tensp", obj.getTensp());
        values.put("giasp", obj.getGia());
        values.put("soluongban", obj.getSoluongban());
        values.put("ngay", sdf.format(obj.getNgay()));
        values.put("tongtien", obj.getTongtien());
        values.put("trangthai", obj.getTrangThai());
        return db.insert("DONHANG", null, values);
    }
    public long update(DonHang obj){
        ContentValues values = new ContentValues();
        values.put("masp", obj.getMasp());
        values.put("tensp", obj.getTensp());
        values.put("giasp", obj.getGia());
        values.put("soluongban", obj.getSoluongban());
        values.put("ngay", sdf.format(obj.getNgay()));
        values.put("tongtien", obj.getTongtien());
        values.put("trangthai", obj.getTrangThai());
        return db.update("DONHANG", values, "madh = ?", new String[]{String.valueOf(obj.getMadh())});
    }
    public long delete(String id){
        return db.delete("DONHANG", "madh = ?", new String[]{String.valueOf(id)});
    }

    public List<DonHang> getAll(){
        String sql = "SELECT * FROM DONHANG";
        return getData(sql);
    }
    public DonHang getID(String id){
        String sql = "SELECT * FROM DONHANG WHERE madh = ?";
        List<DonHang> list = getData(sql, id);
        return list.get(0);
    }
    @SuppressLint("Range")
    private List<DonHang> getData(String sql, String... selectionArg){
        List<DonHang> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArg);
        while (cursor.moveToNext()){
            DonHang obj = new DonHang();
            obj.setMadh(Integer.parseInt(cursor.getString(cursor.getColumnIndex("madh"))));
            obj.setMasp(Integer.parseInt(cursor.getString(cursor.getColumnIndex("masp"))));
            obj.setTensp(cursor.getString(cursor.getColumnIndex("tensp")));
            obj.setGia(Integer.parseInt(cursor.getString(cursor.getColumnIndex("giasp"))));
            obj.setSoluongban(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soluongban"))));
            try {
                obj.setNgay(sdf.parse(cursor.getString(cursor.getColumnIndex("ngay"))));
            }catch (ParseException e){
                e.printStackTrace();
            }
            obj.setTongtien(Integer.parseInt(cursor.getString(cursor.getColumnIndex("tongtien"))));
            obj.setTrangThai(cursor.getString(cursor.getColumnIndex("trangthai")));
            list.add(obj);
        }
        return list;
    }

    //    Top
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

    //    Doanh thu
    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay) {
        String sqlDoanhThu = "SELECT SUM(tongtien) as doanhthu FROM DONHANG WHERE ngay BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<Integer>();
        Cursor cursor = db.rawQuery(sqlDoanhThu, new String[]{tuNgay, denNgay});
        while (cursor.moveToNext()) {
            try {
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhthu"))));
            } catch (Exception e) {
                list.add(0);
            }
        }
        return list.get(0);
    }
}
