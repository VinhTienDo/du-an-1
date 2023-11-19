package fpoly.vinhldph35167.du_an_1.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpoly.vinhldph35167.du_an_1.Database.Dbhelper;
import fpoly.vinhldph35167.du_an_1.Model.SanPham;

public class SanPhamDao {
    private SQLiteDatabase db;
    public SanPhamDao(Context context){
        Dbhelper dbhelper = new Dbhelper(context);
        db = dbhelper.getWritableDatabase();
    }
    public long insert(SanPham obj){
        ContentValues values = new ContentValues();
        values.put("tensp", obj.getTensp());
        values.put("giasp", obj.getGiasp());
        values.put("maloai", obj.getMaloai());
        values.put("soluongban", obj.getSoluongban());
        return db.insert("SANPHAM", null, values);
    }
    public long update(SanPham obj){
        ContentValues values = new ContentValues();
        values.put("tensp", obj.getTensp());
        values.put("giasp", obj.getGiasp());
        values.put("maloai", obj.getMaloai());
        values.put("soluongban", obj.getSoluongban());
        return db.update("SANPHAM", values, "masp=?", new String[]{String.valueOf(obj.getMasp())});
    }
    public long delete(String id){
        return db.delete("SANPHAM", "masp=?", new String[]{String.valueOf(id)});
    }
    public List<SanPham> getAll(){
        String sql = "SELECT * FROM SANPHAM";
        return getData(sql);
    }
    public SanPham getID(String id){
        String sql = "SELECT * FROM SANPHAM WHERE masp=?";
        List<SanPham> list = getData(sql, id);
        return list.get(0);
    }
    @SuppressLint("Range")
    private List<SanPham> getData(String sql, String... selectionArgs){
        List<SanPham> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()){
            SanPham obj = new SanPham();
            obj.setMasp(Integer.parseInt(cursor.getString(cursor.getColumnIndex("masp"))));
            obj.setTensp(cursor.getString(cursor.getColumnIndex("tensp")));
            obj.setGiasp(Integer.parseInt(cursor.getString(cursor.getColumnIndex("giasp"))));
            obj.setMaloai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maloai"))));
            obj.setSoluongban(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soluongban"))));
            list.add(obj);
        }
        return list;
    }
}
