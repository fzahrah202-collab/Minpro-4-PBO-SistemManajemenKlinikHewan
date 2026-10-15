package model;

public abstract class Hewan {

    private final int idData;
    private String namaHewan;
    private String jenisHewan;
    private int umur;
    private Pemilik pemilik;
    private Pemeriksaan pemeriksaan;

    public Hewan(int idData, String namaHewan, String jenisHewan, int umur, Pemilik pemilik, Pemeriksaan pemeriksaan) {
        this.idData = idData;
        this.namaHewan = namaHewan;
        this.jenisHewan = jenisHewan;
        this.umur = umur;
        this.pemilik = pemilik;
        this.pemeriksaan = pemeriksaan;
    }

    public int getIdData() {
        return idData;
    }

    public String getNamaHewan() {
        return namaHewan;
    }

    public void setNamaHewan(String namaHewan) {
        this.namaHewan = namaHewan;
    }

    public String getJenisHewan() {
        return jenisHewan;
    }

    public void setJenisHewan(String jenisHewan) {
        this.jenisHewan = jenisHewan;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public Pemilik getPemilik() {
        return pemilik;
    }

    public void setPemilik(Pemilik pemilik) {
        this.pemilik = pemilik;
    }

    public Pemeriksaan getPemeriksaan() {
        return pemeriksaan;
    }

    public void setPemeriksaan(Pemeriksaan pemeriksaan) {
        this.pemeriksaan = pemeriksaan;
    }

    public abstract void tampilkanInfo();
}