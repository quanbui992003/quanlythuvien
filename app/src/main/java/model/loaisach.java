package model;

public class loaisach {
    private int maloai;
    private String tenloai;
    private int giathue;

    public loaisach() {
    }

    public loaisach(int maloai, String tenloai, int giathue) {
        this.maloai = maloai;
        this.tenloai = tenloai;
        this.giathue = giathue;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public int getGiathue() {
        return giathue;
    }

    public void setGiathue(int giathue) {
        this.giathue = giathue;
    }
}
