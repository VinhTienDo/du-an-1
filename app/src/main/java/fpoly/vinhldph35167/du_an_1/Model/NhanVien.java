package fpoly.vinhldph35167.du_an_1.Model;

public class NhanVien {
    private String manv;
    private String hoten;
    private int giotangca;
    private String namsinh;
    private int gioditre;
    private int tongthunhap;
    private int sodienthoai;
    private int songaydilam;
    private String password;

    public NhanVien() {
    }

    public NhanVien(String manv, String hoten, String password) {
        this.manv = manv;
        this.hoten = hoten;
        this.password = password;
    }

    public NhanVien(String manv, String hoten, int giotangca, String namsinh, int gioditre, int tongthunhap, int sodienthoai, int songaydilam, String password) {
        this.manv = manv;
        this.hoten = hoten;
        this.giotangca = giotangca;
        this.namsinh = namsinh;
        this.gioditre = gioditre;
        this.tongthunhap = tongthunhap;
        this.sodienthoai = sodienthoai;
        this.songaydilam = songaydilam;
        this.password = password;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public int getGiotangca() {
        return giotangca;
    }

    public void setGiotangca(int giotangca) {
        this.giotangca = giotangca;
    }

    public String getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(String namsinh) {
        this.namsinh = namsinh;
    }

    public int getGioditre() {
        return gioditre;
    }

    public void setGioditre(int gioditre) {
        this.gioditre = gioditre;
    }

    public int getTongthunhap() {
        return tongthunhap;
    }

    public void setTongthunhap(int tongthunhap) {
        this.tongthunhap = tongthunhap;
    }

    public int getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(int sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public int getSongaydilam() {
        return songaydilam;
    }

    public void setSongaydilam(int songaydilam) {
        this.songaydilam = songaydilam;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
