package Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import CSDL.database;
import model.phieumuon;
import model.sach;

public class phieumuondao {
    database helper;
    public phieumuondao(Context context){
        helper = new database(context);}
SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
    public ArrayList<phieumuon> selectAll(String sql, String...selectionArgs){
        ArrayList<phieumuon> listLC = new ArrayList<phieumuon>();
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cs = db.rawQuery(sql,selectionArgs);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
         phieumuon tv= new phieumuon();
            tv.setMaPM(cs.getInt(0));
            tv.setMaTT(cs.getString(1));
            tv.setMaTV(cs.getInt(2));
            tv.setMasach(cs.getInt(3));
            tv.setTienthue(cs.getInt(4));
            try {
                tv.setNgay(format.parse(cs.getString(5)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            listLC.add(tv);
            tv.setTrasach(cs.getInt(6));

            cs.moveToNext();
        }
        return listLC;
    }
    public boolean insert(phieumuon tv ){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues value = new ContentValues();
//        value.put("Maphieumuon",tv.getMaPM());
        value.put("masach",tv.getMasach());
        value.put("matt",tv.getMaTT());
        value.put("matv",tv.getMaTV());
        value.put("tienthue",tv.getTienthue());
        value.put("trasach",tv.getTrasach());
        value.put("ngay",format.format(tv.getNgay()));
        long row = db.insert("phieumuon",null,value);
        return (row>0);
    }
    public boolean update(phieumuon tv){
        ContentValues value = new ContentValues();
        SQLiteDatabase db = helper.getWritableDatabase();
        value.put("Maphieumuon",tv.getMaPM());
        value.put("masach",tv.getMasach());
        value.put("matt",tv.getMaTT());
        value.put("matv",tv.getMaTV());
        value.put("tienthue",tv.getTienthue());
        value.put("trasach",tv.getTrasach());
        value.put("ngay",format.format(tv.getNgay()));
        int result = db.update("phieumuon",value,"Maphieumuon=?",new String[]{String.valueOf((tv.getMaPM()))});
        return result>0;

    }
    public boolean delete(int Maphieumuon){
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "DELETE FROM phieumuon where Maphieumuon=?";
        int row = db.delete("phieumuon","Maphieumuon=?",new String[]{Maphieumuon+""});
        return (row>0);
    }
    public List<phieumuon> getAllPhieuMuon() {
        String sql = "SELECT * FROM phieumuon"  ;
        return selectAll(sql);
    }


    public phieumuon getIdPhieuMuon(String id) {
        String sql = "SELECT * FROM phieumuon WHERE Maphieumuon=?";
        List<phieumuon> listPhieuMuon = selectAll(sql,id);
        return listPhieuMuon.get(0);
    }

}
