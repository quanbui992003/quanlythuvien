package Dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import CSDL.database;
import model.sach;
import model.top;

public class thongkedao {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");

    public thongkedao() {
    }

    public thongkedao(Context context) {
        this.db = db;
        this.context = context;
        database database= new database(context);
        db = database.getWritableDatabase();
    }
    @SuppressLint("Range")
    public List<top> getTop() {
        String sqlTop = "SELECT masach, count(maSach) as soluong FROM phieumuon GROUP BY masach ORDER BY soluong DESC LIMIT 10";
        List<top> list = new ArrayList<>();
        sachdao sachDAO = new sachdao(context);
        Cursor cursor = db.rawQuery(sqlTop, null);
        while (cursor.moveToNext()) {
            top top = new top();
            @SuppressLint("Range") sach sach = sachDAO.getIdSach(cursor.getString(cursor.getColumnIndex("masach")));
            top.tenSach = sach.getTenSach();
            top.soLuong = Integer.parseInt(cursor.getString(cursor.getColumnIndex("soluong")));
            list.add(top);
        }
        return list;
    }

        //thong ke doanh thu
        @SuppressLint("Range")
        public int getDoanhThu(String tungay, String denNgay) {
            String sqlDoanhThu = "SELECT SUM (tienthue) as doanhThu FROM phieumuon WHERE ngay BETWEEN ? AND ?";
            List<Integer> list = new ArrayList<>();
            Cursor cursor = db.rawQuery(sqlDoanhThu, new String[] {tungay, denNgay});

            while (cursor.moveToNext()) {
                try {
                    list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThu"))));
                } catch (Exception e) {
                    list.add(0);
                }
            }
            return list.get(0);
        }


}
