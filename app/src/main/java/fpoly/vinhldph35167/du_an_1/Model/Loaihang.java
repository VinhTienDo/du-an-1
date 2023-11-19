package fpoly.vinhldph35167.du_an_1.Model;

public class Loaihang {
    private int maloai;
    private String tenloai;
    private int soluongnhap;
    private int soluongton;

    public Loaihang() {

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

    public int getSoluongnhap() {
        return soluongnhap;
    }

    public void setSoluongnhap(int soluongnhap) {
        this.soluongnhap = soluongnhap;
    }

    public int getSoluongton() {
        return soluongton;
    }

    public void setSoluongton(int soluongton) {
        this.soluongton = soluongton;
    }

    public Loaihang(int maloai, String tenloai, int soluongnhap, int soluongton) {
        this.maloai = maloai;
        this.tenloai = tenloai;
        this.soluongnhap = soluongnhap;
        this.soluongton = soluongton;
    }
}
