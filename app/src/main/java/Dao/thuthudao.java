package Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import CSDL.database;
import model.thuthu;

public class thuthudao {
    private database helper;
    public thuthudao(Context context){
        helper = new database(context);
    }
    public thuthudao() {
    }
    public ArrayList<thuthu> selectAll(String sql,String...select){
        ArrayList<thuthu> listLC = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
         sql = "SELECT * FROM thuthu";
        Cursor cs = db.rawQuery(sql,null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            thuthu tt= new thuthu();
            tt.setMaTT(cs.getString(0));
            tt.setHoTen(cs.getString(1));
            tt.setMatKhau(cs.getString(2));
            listLC.add(tt);
            cs.moveToNext();
        }
        return listLC;
    }
    public long insert(thuthu tv ){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("Matt",tv.getMaTT());
        value.put("tenTT",tv.getHoTen());
        value.put("matkhau",tv.getMatKhau());
        return db.insert("thuthu",null,value);

    }
    public long update( thuthu tv){
        ContentValues values = new ContentValues();
        SQLiteDatabase db = helper.getWritableDatabase();
        values.put("matv",tv.getMaTT());
        values.put("tenTT",tv.getHoTen());
        values.put("matkhau",tv.getMatKhau());
       return db.update("thuthu",values,"id=?",new String[]{String.valueOf((tv.getMaTT()))});

    }
    public boolean delete(int Matt){
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "DELETE FROM thuthu where Matt=?";
        int row = db.delete("thuthu","Matt=?",new String[]{Matt+""});
        return (row>0);
    }
    public int checklogin(String id,String password){
        String sql="SELECT * FROM thuthu WHERE Matt=? AND PASSWORD=?";
        List<thuthu> list = selectAll(sql,id,password);
        if (list.size()==0)
            return -1;
        return 1;
    }

    public thuthu getIdTT (String id) {
        String sql = "SELECT * FROM  thuthu  WHERE maTT=?";
        List<thuthu> listTT = selectAll(sql, id);
        return listTT.get(0);
    }




}
