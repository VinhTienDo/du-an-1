package fpoly.vinhldph35167.du_an_1.Model;

public class SanPham {
    private int masp;
    private String tensp;
    private int giasp;
    private int maloai;
    private int soluongban;
    private String mota;
    private int khoHang;

    public SanPham() {
    }

    public SanPham(int masp, String tensp, int giasp, int maloai, int soluongban, String mota, int khoHang) {
        this.masp = masp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.maloai = maloai;
        this.soluongban = soluongban;
        this.mota = mota;
        this.khoHang = khoHang;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
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

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public int getSoluongban() {
        return soluongban;
    }

    public void setSoluongban(int soluongban) {
        this.soluongban = soluongban;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getKhoHang() {
        return khoHang;
    }

    public void setKhoHang(int khoHang) {
        this.khoHang = khoHang;
    }
}
