package model;

public class sach {
    private int maSach;
    private String tenSach;
    private int giathue;
    private int maLoai;

    public sach() {
    }

    public sach(int maSach, String tenSach, int giathue, int maLoai) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giathue = giathue;
        this.maLoai = maLoai;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getGiathue() {
        return giathue;
    }

    public void setGiathue(int giathue) {
        this.giathue = giathue;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }
}
