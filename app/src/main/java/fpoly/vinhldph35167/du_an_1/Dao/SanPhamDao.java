package fpoly.vinhldph35167.du_an_1.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.vinhldph35167.du_an_1.Database.Dbhelper;
import fpoly.vinhldph35167.du_an_1.Model.SanPham;

public class SanPhamDao {
    Dbhelper dbhelper;
    public SanPhamDao(Context context){dbhelper = new Dbhelper(context);}
//    lấy toàn bộ sản phẩm
    public ArrayList<SanPham> getDSSanPham(){
        ArrayList<SanPham> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT sp.masp, sp.tensp, sp.giasp, sp.soluongban, lo.tenloai FROM SANPHAM sp, LOAIHANG lo WHERE lo.maloai", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new SanPham(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public boolean themSanPhamMoi(String masp, String tensp, int gia, String maloai){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("masp", masp);
        contentValues.put("tensp", tensp);
        contentValues.put("gia", gia);
        contentValues.put("maloai", maloai);
        long check = sqLiteDatabase.insert("SACH", null, contentValues);
        if (check == -1)
            return false;
        return true;
    }
    public boolean capNhatThongTinSanPham(String masp, String tensp, int gia, int soluongban, String maloai){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("masp", masp);
        contentValues.put("tensp", tensp);
        contentValues.put("gia", gia);
        contentValues.put("soluongban", soluongban);
        contentValues.put("maloai", maloai);
        long check = sqLiteDatabase.update("SACH", contentValues, "masp = ?", new String[]{String.valueOf(masp)});
        if (check == -1)
            return false;
        return true;
    }
    public int xoaSanPham(int masp){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SANPHAM WHERE masp = ?", new String[]{String.valueOf(masp)});
        if (cursor.getCount() != 0){
            return -1;
        }
        long check = sqLiteDatabase.delete("SANPHAM", "masp = ?", new String[]{String.valueOf(masp)});
        if (check == -1)
            return 0;
        return 1;
    }
}
