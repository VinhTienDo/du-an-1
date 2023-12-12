package fpoly.vinhldph35167.du_an_1.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.vinhldph35167.du_an_1.Database.Dbhelper;
import fpoly.vinhldph35167.du_an_1.Model.NhanVien;

public class NhanVienDao {
    Dbhelper dbhelper;
    SQLiteDatabase sqLiteDatabase;
        public NhanVienDao(Context context){
         dbhelper = new Dbhelper(context);
    }

    public ArrayList<NhanVien> getDSNV() {
            ArrayList<NhanVien> list = new ArrayList<>();
            sqLiteDatabase = dbhelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NHANVIEN",null);
            if(cursor.getCount() != 0) {
                cursor.moveToFirst();
                do {
                    list.add((new NhanVien(cursor.getString(0),
                            cursor.getString(1),
                            cursor.getInt(2),
                            cursor.getString(3))));
                }while (cursor.moveToNext());
            }
            return list;
    }

    public boolean deleteNhanVien(int manv) {
            sqLiteDatabase = dbhelper.getWritableDatabase();
            long check = sqLiteDatabase.delete("NHANVIEN","manv = ?",new String[]{String.valueOf(manv)});
            return check != -1;
    }

    public boolean themNhanVien(String manv, String hoten, int sodt, String namsinh) {
            sqLiteDatabase = dbhelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("manv",manv);
            contentValues.put("hoten",hoten);
            contentValues.put("sodienthoai",sodt);
            contentValues.put("namsinh",namsinh);
            long check = sqLiteDatabase.insert("NHANVIEN",null,contentValues);
            if(check == -1)
                return false;
            return true;
    }

    public boolean capNhatNhanVien(String manv, String hoten, String sdt, String namsinh) {
        sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("manv", manv);
        contentValues.put("hoten", hoten);
        contentValues.put("sodienthoai", sdt);
        contentValues.put("namsinh", namsinh);

        String whereClause = "manv=?";
        String[] whereArgs = {manv};

        long check = sqLiteDatabase.update("NHANVIEN", contentValues, whereClause, whereArgs);
        if (check == -1)
            return false;
        return true;
    }

    //    public long insert(NhanVien obj){
//
//        ContentValues values = new ContentValues();
//        values.put("manv", obj.getManv());
//        values.put("hoten", obj.getHoten());
//        values.put("namsinh", obj.getNamsinh());
//        values.put("sodienthoai", obj.getSodienthoai());
//        return sqLiteDatabase.insert("NHANVIEN", null, values);
//    }
//    public long update(NhanVien obj){
//        ContentValues values = new ContentValues();
//        values.put("manv", obj.getManv());
//        values.put("hoten",obj.getHoten());
//        values.put("namsinh",obj.getNamsinh());
//        values.put("sodienthoai",obj.getSodienthoai());
//        return sqLiteDatabase.update("NHANVIEN",values, "manv=?", new String[]{String.valueOf(obj.getManv())});
//    }
//    public long delete(String id){
//        return  sqLiteDatabase.delete("NHANVIEN","manv=?", new String[]{String.valueOf(id)});
//    }

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
