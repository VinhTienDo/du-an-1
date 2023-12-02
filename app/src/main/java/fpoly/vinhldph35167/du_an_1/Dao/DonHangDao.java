package fpoly.vinhldph35167.du_an_1.Dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fpoly.vinhldph35167.du_an_1.Database.Dbhelper;

public class DonHangDao {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public DonHangDao(Context context) {
        this.context = context;
        Dbhelper dbHelper = new Dbhelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //    Doanh thu
    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay) {
        String sqlDoanhThu = "SELECT SUM(tongtien) as doanhthu FROM DONHANG WHERE ngay BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<Integer>();
        Cursor cursor = db.rawQuery(sqlDoanhThu, new String[]{tuNgay, denNgay});
        while (cursor.moveToNext()) {
            try {
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhthu"))));
            } catch (Exception e) {
                list.add(0);
            }
        }
        return list.get(0);
    }
}
