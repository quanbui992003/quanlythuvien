package model;

import android.content.Context;

import java.util.Date;

public class phieumuon {
    private int maPM;
    private String maTT;
    private int maTV;
    private int masach;
    private int tienthue;
    private int trasach;
    private Date ngay;

    public phieumuon() {
    }

    public phieumuon(int maPM, String maTT, int maTV, int masach, int tienthue, int trasach, Date ngay) {
        this.maPM = maPM;
        this.maTT = maTT;
        this.maTV = maTV;
        this.masach = masach;
        this.tienthue = tienthue;
        this.trasach = trasach;
        this.ngay = ngay;
    }

    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public int getTienthue() {
        return tienthue;
    }

    public void setTienthue(int tienthue) {
        this.tienthue = tienthue;
    }

    public int getTrasach() {
        return trasach;
    }

    public void setTrasach(int trasach) {
        this.trasach = trasach;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }
}
