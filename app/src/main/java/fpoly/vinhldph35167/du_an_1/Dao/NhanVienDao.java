package fpoly.vinhldph35167.du_an_1.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import fpoly.vinhldph35167.du_an_1.Database.Dbhelper;
import fpoly.vinhldph35167.du_an_1.Model.NhanVien;

public class NhanVienDao {
    Dbhelper dbhelper;
    public NhanVienDao(Context context){
         dbhelper = new Dbhelper(context);

    }
    public boolean checkLogin(String manv, String password){
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NHANVIEN WHERE manv = ? AND password = ?", new String[]{manv, password});
        if (cursor.getCount() != 0){
            return true;
        }else {
            return false;
        }
    }
    public boolean Register(String username, String password, String hoten){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("manv", username);
        contentValues.put("password", password);
        contentValues.put("hoten", hoten);

        long check = sqLiteDatabase.insert("NHANVIEN", null, contentValues);
        if (check != -1){
            return true;
        }else {
            return false;
        }
    }
    public boolean capnhatPass(String username, String oldPass, String newPass){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NHANVIEN WHERE manv = ? AND matkhau = ? ", new String[]{username, oldPass, newPass});
        if (cursor.getCount() > 0){
            ContentValues contentValues = new ContentValues();
            contentValues.put("password", newPass);
            long check = sqLiteDatabase.update("NHANVIEN", contentValues, "manv = ?", new String[]{username});
            if (check == -1)
                return false;
            return true;
        }
        return false;
    }
}
