package Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import CSDL.database;
import model.loaisach;

public class loaisachdao {
    database helper;
    public loaisachdao(Context context){
        helper = new database(context);}

    public ArrayList<loaisach> selectAll(String sql, String...selectionArgs){
        ArrayList<loaisach> listLC = new ArrayList<loaisach>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cs = db.rawQuery(sql,selectionArgs);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            loaisach tv = new loaisach();
            tv.setMaloai(Integer.parseInt(String.valueOf(cs.getInt(0))));
            tv.setTenloai(cs.getString(1));
//            tv.setGiathue(cs.getInt(2));
            listLC.add(tv);
            cs.moveToNext();
        }
        return listLC;
    }
    public boolean insert(loaisach tv ){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues value = new ContentValues();
//        value.put("mals",tv.getMaloai());
        value.put("tenloaisach",tv.getTenloai());
//        value.put("giathue",tv.getGiathue());
        long row = db.insert("loaisach",null,value);
        return (row>0);
    }
    public boolean update( loaisach tv){
        ContentValues values = new ContentValues();
        SQLiteDatabase db = helper.getWritableDatabase();
//        values.put("mals",tv.getMaloai());
        values.put("tenloaisach",tv.getTenloai());
//        values.put("giathue",tv.getGiathue());
        int result = db.update("loaisach",values,"mals=?",new String[]{String.valueOf((tv.getMaloai()))});
        return result>0;

    }
    public boolean delete(int mals){
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "DELETE FROM loaisach where mals=?";
        int row = db.delete("loaisach","mals=?",new String[]{mals+""});
        return (row>0);
    }
    public List<loaisach> getAllLoaiSach() {
        String sql = "SELECT * FROM  loaisach";
        return selectAll(sql);
    }

    public loaisach getIdLoaiSach (String id) {
        String sql = "SELECT * FROM  loaisach  WHERE mals=?";
        List<loaisach> listLoaiSach = selectAll(sql, id);
        return listLoaiSach.get(0);
    }
}
