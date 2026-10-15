package model;

public class HewanLain extends Hewan {

    public HewanLain(int idData, String namaHewan, String jenisHewan, int umur, Pemilik pemilik, Pemeriksaan pemeriksaan) {
        super(idData, namaHewan, jenisHewan, umur, pemilik, pemeriksaan);
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Jenis Hewan: " + getJenisHewan());
    }
}