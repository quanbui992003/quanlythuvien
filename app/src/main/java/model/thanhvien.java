package model;

public class thanhvien{
    private int maTv;
    private String hoTen;
    private String namSinh;

    public thanhvien() {
    }

    public thanhvien(int maTv, String hoTen, String namSinh) {
        this.maTv = maTv;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
    }

    public int getMaTv() {
        return maTv;
    }

    public void setMaTv(int maTv) {
        this.maTv = maTv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }
}
