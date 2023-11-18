package fpoly.vinhldph35167.du_an_1.Model;

public class SanPham {
    private String masp;
    private String tensp;
    private int giasp;
    private int soluongban;
    private String maloai;
    private String tenloai;

    public SanPham(String masp, String tensp, int giasp, int soluongban, String maloai, String tenloai) {
        this.masp = masp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.soluongban = soluongban;
        this.maloai = maloai;
        this.tenloai = tenloai;
    }

    public SanPham(String masp, String tensp, int giasp, int soluongban, String tenloai) {
        this.masp = masp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.soluongban = soluongban;
        this.tenloai = tenloai;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGiasp() {
        return giasp;
    }

    public void setGiasp(int giasp) {
        this.giasp = giasp;
    }

    public int getSoluongban() {
        return soluongban;
    }

    public void setSoluongban(int soluongban) {
        this.soluongban = soluongban;
    }

    public String getMaloai() {
        return maloai;
    }

    public void setMaloai(String maloai) {
        this.maloai = maloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }
}
