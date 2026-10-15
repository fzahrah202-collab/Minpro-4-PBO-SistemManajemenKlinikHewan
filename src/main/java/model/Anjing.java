package model;

public class Anjing extends Hewan implements Vaksinable {

    private String statusVaksinRabies;

    public Anjing(int idData, String namaHewan, int umur, String statusVaksinRabies, Pemilik pemilik,Pemeriksaan pemeriksaan) {

        super(idData, namaHewan, "Anjing", umur, pemilik, pemeriksaan);
        this.statusVaksinRabies = statusVaksinRabies;
    }

    public String getStatusVaksinRabies() {
        return statusVaksinRabies;
    }

    public void setStatusVaksinRabies(String statusVaksinRabies) {
        this.statusVaksinRabies = statusVaksinRabies;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Jenis Hewan: " + getJenisHewan());
        System.out.println("Status Vaksin Rabies: " + statusVaksinRabies);
    }

    @Override
    public String cekVaksin() {
        return "Rabies - " + statusVaksinRabies;
    }
}