package fpoly.vinhldph35167.du_an_1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "MYSTORE";
    public static final int DB_VERSION = 5;

    public Dbhelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableNhanVien = "CREATE TABLE NHANVIEN(" +
                "manv text primary key, "+
                "hoten text NOT NULL, "+
                "giotangca integer NOT NULL, "+
                "namsinh text NOT NULL, "+
                "gioditre integer NOT NULL, "+
                "tongthunhap integer NOT NULL, "+
                "sodienthoai integer NOT NULL, "+
                "songaydilam integer NOT NULL, "+
                "password text NOT NULL)";
        db.execSQL(createTableNhanVien);

        String createTableLoaiHang = "CREATE TABLE LOAIHANG(" +
                "maloai INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "tenloai text NOT NULL, "+
                "soluongnhap integer NOT NULL, "+
                "soluongton integer NOT NULL)";
        db.execSQL(createTableLoaiHang);

        String createTableSanPham = "CREATE TABLE SANPHAM(" +
                "masp INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "tensp text NOT NULL, "+
                "giasp integer NOT NULL, "+
                "maloai integer references LOAIHANG(maloai),"+
                "soluongban integer NOT NULL) ";
        db.execSQL(createTableSanPham);

String createTableKhachHang = "CREATE TABLE KHACHHANG(" +
        "makh INTEGER PRIMARY KEY AUTOINCREMENT, "+
        "hoten text NOT NULL, "+
        "namsinh text NOT NULL, "+
        "sodienthoai integer NOT NULL )";
db.execSQL(createTableKhachHang);



        db.execSQL("INSERT INTO NHANVIEN VALUES('nv1', 'Lê Đoàn Vinh', 0, '21/11/2004', 0, 4000000, 0327385335, 30, '123')");
        db.execSQL("INSERT INTO LOAIHANG VALUES(1, 'Bánh', 300, 50)");
        db.execSQL("INSERT INTO SANPHAM VALUES(1, 'Solite',25000, 1, 10)");
        db.execSQL("INSERT INTO KHACHHANG VALUES(1, 'Nguyễn Văn A','1999',0678935789)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i != i1){
            db.execSQL("DROP TABLE IF EXISTS NHANVIEN");
            db.execSQL("DROP TABLE IF EXISTS LOAIHANG");
            db.execSQL("DROP TABLE IF EXISTS SANPHAM");
            db.execSQL("DROP TABLE IF EXISTS KHACHHANG");
            onCreate(db);
        }
    }
}
