package fpoly.vinhldph35167.du_an_1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "MYSTORE";
    public static final int DB_VERSION = 21;

    public Dbhelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableNhanVien = "CREATE TABLE NHANVIEN(" +
                "manv text primary key, " +
                "hoten text NOT NULL, " +
                "giotangca integer NOT NULL, " +
                "namsinh text NOT NULL, " +
                "gioditre integer NOT NULL, " +
                "tongthunhap integer NOT NULL, " +
                "sodienthoai integer NOT NULL, " +
                "songaydilam integer NOT NULL, " +
                "password text not null)";
        db.execSQL(createTableNhanVien);

        String createTableLoaiHang = "CREATE TABLE LOAIHANG(" +
                "maloai INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenloai text NOT NULL, " +
                "soluongnhap integer NOT NULL, " +
                "soluongton integer NOT NULL)";
        db.execSQL(createTableLoaiHang);

        String createTableSanPham = "CREATE TABLE SANPHAM(" +
                "masp INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tensp text NOT NULL, " +
                "giasp integer NOT NULL, " +
                "maloai integer references LOAIHANG(maloai)," +
                "soluongban integer NOT NULL) ";
        db.execSQL(createTableSanPham);

        String createTableKhachHang = "CREATE TABLE KHACHHANG(" +
                "makh text PRIMARY KEY , " +
                "hoten text NOT NULL, " +
                "namsinh text NOT NULL, " +
                "sodienthoai integer NOT NULL, " +
                "password text )";
        db.execSQL(createTableKhachHang);

        String createTableDonhang = "CREATE TABLE DONHANG(" +
                "madh INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "masp integer references SANPHAM(masp), " +
                "tensp text references SANPHAM(tensp), " +
                "giasp integer references SANPHAM(giasp), " +
                "soluongban integer references SANPHAM(soluongban), " +
                "ngay date not null, " +
                "tongtien integer not null, " +
                "trangthai text not null) ";
        db.execSQL(createTableDonhang);


        db.execSQL("INSERT INTO NHANVIEN VALUES('nv1', 'Lê Đoàn Vinh', 0, '21/11/2004', 0, 4000000, 0327385335, 30, '123')");
        db.execSQL("INSERT INTO LOAIHANG VALUES(1, 'Bánh', 300, 50)");
        db.execSQL("INSERT INTO SANPHAM VALUES(1, 'Solite',25000, 1, 10)");
        db.execSQL("INSERT INTO KHACHHANG VALUES('kh1', 'Nguyễn Văn A','1999',0678935789, '123')");
        db.execSQL("INSERT INTO DONHANG VALUES(1, 1, 'Solite', 25000, 1, '2023/12/02', 25000, 'Da giao hang'), (2, 1, 'Solite', 25000, 2, '2023/12/02', 50000, 'Da giao hang')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i != i1) {

            db.execSQL("DROP TABLE IF EXISTS NHANVIEN");
            db.execSQL("DROP TABLE IF EXISTS LOAIHANG");
            db.execSQL("DROP TABLE IF EXISTS SANPHAM");
            db.execSQL("DROP TABLE IF EXISTS KHACHHANG");
            db.execSQL("DROP TABLE IF EXISTS DONHANG");
            onCreate(db);
        }
    }
}
