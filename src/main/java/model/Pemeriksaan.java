package model;

public class Pemeriksaan {

    private String keluhan;
    private String hasil;

    public Pemeriksaan(String keluhan, String hasil) {
        this.keluhan = keluhan;
        this.hasil = hasil;
    }

    public String getKeluhan() {
        return keluhan;
    }

    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }

    public String getHasil() {
        return hasil;
    }

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }
}