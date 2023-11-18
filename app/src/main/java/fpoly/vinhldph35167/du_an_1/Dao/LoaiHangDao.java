package fpoly.vinhldph35167.du_an_1.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.vinhldph35167.du_an_1.Database.Dbhelper;
import fpoly.vinhldph35167.du_an_1.Model.Loaihang;

public class LoaiHangDao {
    Dbhelper dbhelper;
    public LoaiHangDao(Context context){dbhelper = new Dbhelper(context);}
//    lay ds loai hang
    public ArrayList<Loaihang> getDSLoaiHang(){
        ArrayList<Loaihang> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAIHANG", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new Loaihang(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3)));
            }while (cursor.moveToNext());
        }
        return list;
    }
//    them loai hang
    public boolean themLoaiHang(String maloai, String tenloai, int soluongnhap){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maloai", maloai);
        contentValues.put("tenloai", tenloai);
        contentValues.put("soluongnhap", soluongnhap);
        long check = sqLiteDatabase.insert("LOAIHANG", null, contentValues);
        if (check == -1)
            return false;
        return true;
    }
    public int xoaLoaiHang(int maloai){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("SELECT * FROM LOAIHANG WHERE maloai= ? ", new String[]{String.valueOf(maloai)});
        if (cursor.getCount() != 0){
            return -1;
        }
        long check = sqLiteDatabase.delete("LOAIHANG", "maloai=?", new String[]{String.valueOf(maloai)});
        if (check == -1)
            return 0;
        return 1;
    }
    public boolean thayDoiLoaiHang(String maloai, String tenloai, int soluongnhap, int soluongton){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maloai", maloai);
        contentValues.put("tenloai", tenloai);
        contentValues.put("soluongnhap", soluongnhap);
        contentValues.put("soluongton", soluongton);
        long check = sqLiteDatabase.update("LOAIHANG", contentValues, "maloai=?", new String[]{String.valueOf(maloai)});
        if (check == -1)
            return false;
        return true;
    }
}
