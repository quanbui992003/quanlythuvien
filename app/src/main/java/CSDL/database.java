package CSDL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {
    public static final String dbname="quanbhph20506_dam.db";
    public database(Context context) {
        super(context, dbname, null, 3);

    }
    public static final String thuthu = "thuthu";
    public static final String thanhvien = "thanhvien";
    public static final String phieumuon = "phieumuon";
    public static final String loaisach = "loaisach";
    public static final String sach = "sach";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableThuthu=
                "CREATE TABLE thuthu(" +
                        "Matt TEXT PRIMARY KEY , " +
                        "tenTT TEXT NOT NULL," +
                        "matkhau TEXT NOT NULL);";
        db.execSQL(tableThuthu);
        String tablethanhvien=
                " CREATE TABLE thanhvien(" +
                        "mathanhvien integer  PRIMARY KEY AUTOINCREMENT," +
                        "tenThanhvien text NOT NULL," +
                        "namsinh text NOT NULL);";
        db.execSQL(tablethanhvien);
        String tableloaisach=
                " CREATE TABLE loaisach(" +
                        "mals INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " tenloaisach TEXT NOT NULL);";
        db.execSQL(tableloaisach);
        String tablesach=
                "CREATE TABLE sach(masach INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "tensach text NOT NULL," +
                        "giathue int not null," +
                        "mals INTEGER REFERENCES loaisach(mals));";
        db.execSQL(tablesach);
        String tablephieumuon=
                "CREATE TABLE phieumuon(" +
                        "Maphieumuon integer PRIMARY KEY AUTOINCREMENT, " +
                        "matt text REFERENCES thuthu(matt)," +
                        "matv INTEGER REFERENCES thanhvien(matv)," +
                        "masach INTEGER REFERENCES sach(masach)," +
                        "tienthue INTEGER NOT NULL," +
                        "ngay INTEGER not NULL," +
                        "trasach INTEGER not NULL);";
        db.execSQL(tablephieumuon);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String droptablethuthu="drop table if exists thuthu";
        db.execSQL(droptablethuthu);
        String droptablethanhvien="drop table if exists thanhvien";
        db.execSQL(droptablethanhvien);
        String droptableloasach="drop table if exists loaisach";
        db.execSQL(droptableloasach);
        String droptablesach="drop table if exists sach";
        db.execSQL(droptablesach);
        String droptablephieumuon="drop table if exists phieumuon";
        db.execSQL(droptablephieumuon);
        onCreate(db);
    }
}
