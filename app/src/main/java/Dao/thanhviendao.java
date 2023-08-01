package Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import CSDL.database;
import FARMENT.thanhvien.tabthanhvien;
import model.thanhvien;

public class thanhviendao {
    private SQLiteDatabase db;
    Context context;

    public thanhviendao(Context context) {
        database helper = new database(context);
        db = helper.getWritableDatabase();
        this.context = context;
    }

    public static final String thanhvien = database.thanhvien;

    public ArrayList<thanhvien> selectAll(String sql, String...selectionArgs) {
        ArrayList<thanhvien> listLC = new ArrayList<>();
        Cursor cs = db.rawQuery(sql, selectionArgs);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            thanhvien tv = new thanhvien();
            tv.setMaTv(Integer.parseInt(cs.getString(0)));
            tv.setHoTen(cs.getString(1));
            tv.setNamSinh(cs.getString(2));
            listLC.add(tv);
            cs.moveToNext();
        }
        return listLC;
    }

    public boolean insert(thanhvien tv) {
        ContentValues values = new ContentValues();
        Log.e("insert", tv.getHoTen());
        values.put("tenThanhvien", tv.getHoTen());
        values.put("namsinh", tv.getNamSinh());
        long row = db.insert("thanhvien", null, values);
        return (row > 0);

    }

    public boolean update(thanhvien tv) {
        ContentValues values = new ContentValues();
        values.put("tenThanhvien", tv.getHoTen());
        values.put("namsinh", tv.getNamSinh());
        long row = db.update("thanhvien", values, "mathanhvien=?", new String[]{String.valueOf((tv.getMaTv()))});
        return (row > 0);


    }

    public boolean delete(int mathanhvien) {
        String sql = "DELETE FROM thanhvien where matv=?";
        int row = db.delete("thanhvien", "mathanhvien=?", new String[]{mathanhvien + ""});
        return (row > 0);
    }

    public thanhvien getIdTV(String id) {
        String sql = "SELECT * FROM thanhvien WHERE mathanhvien=?";
        List<thanhvien> listTV = selectAll(sql,id);
        return listTV.get(0);
    }

    public List<thanhvien> getAllThanhVien() {
        String sql = "SELECT * FROM " + thanhvien;
        return selectAll(sql);
    }
}
