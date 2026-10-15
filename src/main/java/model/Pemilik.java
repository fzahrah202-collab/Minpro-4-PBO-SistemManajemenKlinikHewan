package model;

public class Pemilik {

    private String namaPemilik;
    private String noTelepon;

    public Pemilik(String namaPemilik, String noTelepon) {
        this.namaPemilik = namaPemilik;
        this.noTelepon = noTelepon;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }
}