package Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import CSDL.database;
import model.loaisach;
import model.sach;
import model.thanhvien;

public class sachdao {
    database helper;
    public sachdao(Context context){
        helper = new database(context);}

    public ArrayList<sach> selectAll(String sql, String...selectionArgs){
        ArrayList<sach> listLC = new ArrayList<sach>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cs = db.rawQuery(sql,selectionArgs);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            sach sach = new sach();
            sach.setMaSach(Integer.parseInt(String.valueOf(cs.getInt(0))));
            sach.setTenSach(cs.getString(1));
            sach.setGiathue(Integer.parseInt(String.valueOf(cs.getInt(2))));
            sach.setMaLoai(Integer.parseInt(String.valueOf(cs.getInt(3))));
            listLC.add(sach);
            cs.moveToNext();
        }
        return listLC;
    }
    public long insert(sach tv ){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues value = new ContentValues();
//        value.put("masach",tv.getMaSach());
        value.put("tensach",tv.getTenSach());
        value.put("giathue",tv.getGiathue());
        value.put("mals",tv.getMaLoai());
        return db.insert("sach",null,value);

    }
    public boolean update(sach tv){
        ContentValues values = new ContentValues();
        SQLiteDatabase db = helper.getWritableDatabase();
//        values.put("masach",tv.getMaSach());
        values.put("tensach",tv.getTenSach());
        values.put("giathue",tv.getGiathue());
        values.put("mals",tv.getMaLoai());
        int result = db.update("sach",values,"masach=?",new String[]{String.valueOf((tv.getMaSach()))});
        return result>0;

    }
    public boolean delete(int masach){
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "DELETE FROM sach where masach=?";
        int row = db.delete("sach","masach=?",new String[]{masach+""});
        return (row>0);
    }
    public sach getIdSach(String id) {
        String sql = "SELECT * FROM  sach WHERE masach=?";
        List<sach> listSach = selectAll(sql, id);
        return listSach.get(0);
    }

    public List<sach>getAllsach() {
        String sql = "SELECT * FROM  sach";
        return selectAll(sql);
    }
}
