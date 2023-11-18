package fpoly.vinhldph35167.du_an_1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbhelper extends SQLiteOpenHelper {
    public Dbhelper(Context context){super(context, "DANGKY", null, 3);}
    @Override
    public void onCreate(SQLiteDatabase db) {
        String dbNhanVien = "CREATE TABLE NHANVIEN(manv text primary key, hoten text, giotangca integer, namsinh text, gioditre integer, tongthunhap integer, sodienthoai integer, songaydilam integer, password text)";
        db.execSQL(dbNhanVien);

        String dbLoaiHang = "CREATE TABLE LOAIHANG(maloai text primary key, tenloai text, soluongnhap integer, soluongton integer)";
        db.execSQL(dbLoaiHang);

        String dbSanPham = "CREATE TABLE SANPHAM(masp text primary key, tensp text, giasp integer, soluongban integer references LOAIHANG(maloai))";
        db.execSQL(dbSanPham);


        db.execSQL("INSERT INTO NHANVIEN VALUES('nv1', 'Lê Đoàn Vinh', 0, '21/11/2004', 0, 4000000, 0327385335, 30, '123')");
        db.execSQL("INSERT INTO LOAIHANG VALUES('l1', 'Bánh', 300, 50)");
        db.execSQL("INSERT INTO SANPHAM VALUES('sp1', 'Bánh Solite', 20000, 50)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS LOAIHANG");
            db.execSQL("DROP TABLE IF EXISTS SANPHAM");
            db.execSQL("DROP TABLE IF EXISTS NHANVIEN");

            onCreate(db);
        }
    }
}
