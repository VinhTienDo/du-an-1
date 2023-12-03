package fpoly.vinhldph35167.du_an_1.Model;

import java.util.Date;

public class DonHang {
    private int madh;
    private int masp;
    private String tensp;
    private int gia;
    private int soluongban;
    private Date ngay;
    private int tongtien;
    private String trangThai;

    public DonHang() {
    }

    public DonHang(int madh, int masp, String tensp, int gia, int soluongban, Date ngay, int tongtien, String trangThai) {
        this.madh = madh;
        this.masp = masp;
        this.tensp = tensp;
        this.gia = gia;
        this.soluongban = soluongban;
        this.ngay = ngay;
        this.tongtien = tongtien;
        this.trangThai = trangThai;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public int getMadh() {
        return madh;
    }

    public void setMadh(int madh) {
        this.madh = madh;
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

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoluongban() {
        return soluongban;
    }

    public void setSoluongban(int soluongban) {
        this.soluongban = soluongban;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
