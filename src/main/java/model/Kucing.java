package model;

public class Kucing extends Hewan implements Vaksinable {

    private String statusVaksinF3;

    public Kucing(int idData, String namaHewan, int umur, String statusVaksinF3, Pemilik pemilik, Pemeriksaan pemeriksaan) {
        super(idData, namaHewan, "Kucing", umur, pemilik, pemeriksaan);
        this.statusVaksinF3 = statusVaksinF3;
    }

    public String getStatusVaksinF3() {
        return statusVaksinF3;
    }

    public void setStatusVaksinF3(String statusVaksinF3) {
        this.statusVaksinF3 = statusVaksinF3;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Jenis Hewan: " + getJenisHewan());
        System.out.println("Status Vaksin F3: " + statusVaksinF3);
    }

    @Override
    public String cekVaksin() {
        return "F3 - " + statusVaksinF3;
    }
}