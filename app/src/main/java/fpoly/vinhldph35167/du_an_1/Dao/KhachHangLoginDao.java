package fpoly.vinhldph35167.du_an_1.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fpoly.vinhldph35167.du_an_1.Database.Dbhelper;

public class KhachHangLoginDao {
    Dbhelper dbhelper;
    public KhachHangLoginDao(Context context){
        dbhelper = new Dbhelper(context);
    }
    public boolean checkLogin(String makh, String password){
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM KHACHHANG WHERE makh = ? AND password = ?", new String[]{makh, password});
        if (cursor.getCount() != 0){
            return true;
        }else {
            return false;
        }
    }
    public boolean Register(String username, String password, String hoten){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("makh", username);
        contentValues.put("password", password);
        contentValues.put("hoten", hoten);

        long check = sqLiteDatabase.insert("KHACHHANG", null, contentValues);
        if (check != -1){
            return true;
        }else {
            return false;
        }
    }
}
