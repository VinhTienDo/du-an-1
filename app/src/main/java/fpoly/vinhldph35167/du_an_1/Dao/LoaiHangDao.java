package fpoly.vinhldph35167.du_an_1.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpoly.vinhldph35167.du_an_1.Database.Dbhelper;
import fpoly.vinhldph35167.du_an_1.Model.Loaihang;

public class LoaiHangDao {
    private SQLiteDatabase db;
    public LoaiHangDao(Context context){
        Dbhelper dbhelper = new Dbhelper(context);
        db = dbhelper.getWritableDatabase();
    }
    public long insert(Loaihang obj){
        ContentValues values = new ContentValues();
        values.put("tenloai", obj.getTenloai());
        values.put("soluongnhap", obj.getSoluongnhap());
        values.put("soluongton", obj.getSoluongton());
        return db.insert("LOAIHANG", null, values);
    }
    public long update(Loaihang obj){
        ContentValues values = new ContentValues();
        values.put("tenloai", obj.getTenloai());
        values.put("soluongnhap", obj.getSoluongnhap());
        values.put("soluongton", obj.getSoluongton());
        return db.update("LOAIHANG", values, "maloai=?", new String[]{String.valueOf(obj.getMaloai())});
    }
    public long delete(String id){
        return db.delete("LOAIHANG", "maloai=?", new String[]{String.valueOf(id)});
    }
    public List<Loaihang> getAll(){
        String sql = "SELECT * FROM LOAIHANG";
        return getData(sql);
    }
    public Loaihang getID(String id){
        String sql = "SELECT * FROM LOAIHANG WHERE maloai=?";
        List<Loaihang> list = getData(sql, id);
        return list.get(0);
    }
    @SuppressLint("Range")
    private List<Loaihang> getData(String sql, String... selectionArgs){
        List<Loaihang> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()){
            Loaihang obj = new Loaihang();
            obj.setMaloai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maloai"))));
            obj.setTenloai(cursor.getString(cursor.getColumnIndex("tenloai")));
            obj.setSoluongnhap(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soluongnhap"))));
            obj.setSoluongton(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soluongton"))));
            list.add(obj);
        }
        return list;
    }
}
