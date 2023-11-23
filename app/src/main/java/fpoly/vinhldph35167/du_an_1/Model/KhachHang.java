package fpoly.vinhldph35167.du_an_1.Model;

public class KhachHang {
    private int makh;
    private String hoten;
    private String namsinh;
    private int sodienthoai;

    public KhachHang(int makh, String hoten, String namsinh, int sodienthoai) {
        this.makh = makh;
        this.hoten = hoten;
        this.namsinh = namsinh;
        this.sodienthoai = sodienthoai;
    }

    public KhachHang() {
    }

    public int getMakh() {
        return makh;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(String namsinh) {
        this.namsinh = namsinh;
    }

    public int getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(int sodienthoai) {
        this.sodienthoai = sodienthoai;
    }
}
