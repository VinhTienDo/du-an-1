package fpoly.vinhldph35167.du_an_1.Dao;

import static android.provider.Settings.System.getString;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpoly.vinhldph35167.du_an_1.Database.Dbhelper;
import fpoly.vinhldph35167.du_an_1.Model.KhachHang;
import fpoly.vinhldph35167.du_an_1.Model.Loaihang;

public class KhachHangDao {
    private SQLiteDatabase db;

    public KhachHangDao(Context context){
        Dbhelper dbhelper = new Dbhelper(context);
        db = dbhelper.getWritableDatabase();
    }


    public long insert(KhachHang obj){
        ContentValues values = new ContentValues();
        values.put("makh", obj.getMakh());
        values.put("hoten", obj.getHoten());
        values.put("namsinh", obj.getNamsinh());
        values.put("sodienthoai", obj.getSodienthoai());
        return db.insert("KHACHHANG",null,values);

    }
    public long update(KhachHang obj){
        ContentValues values = new ContentValues();
        values.put("makh", obj.getMakh());
        values.put("hoten",obj.getHoten());
        values.put("namsinh",obj.getNamsinh());
        values.put("sodienthoai",obj.getSodienthoai());
return db.update("KHACHHANG",values, "makh=?", new String[]{String.valueOf(obj.getMakh())});
    }
    public long delete(String id){
        return  db.delete("KHACHHANG","makh=?", new String[]{String.valueOf(id)});
    }
    public List<KhachHang> getAll(){
        String sql = "SELECT * FROM KHACHHANG";
        return getData(sql);
    }
    public KhachHang getID(String id){
        String sql = "SELECT * FROM KHACHHANG WHERE makh=?";
        List<KhachHang> list = getData(sql, id);
        return list.get(0);
    }
    @SuppressLint("Range")
    private List<KhachHang> getData(String sql, String...selectionArgs) {
        List<KhachHang> list = new ArrayList<>();
        Cursor cursor= db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()){
            KhachHang obj = new KhachHang();
            obj.setMakh(cursor.getString(cursor.getColumnIndex("makh")));
            obj.setHoten(cursor.getString(cursor.getColumnIndex("hoten")));
            obj.setNamsinh(cursor.getString(cursor.getColumnIndex("namsinh")));
            obj.setSodienthoai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("sodienthoai"))));
            list.add(obj);
    }
        return list;


}


}
