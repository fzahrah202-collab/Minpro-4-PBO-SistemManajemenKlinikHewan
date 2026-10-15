package controller;

import java.util.ArrayList;
import java.util.Scanner;
import model.Anjing;
import model.Hewan;
import model.HewanLain;
import model.Kucing;
import model.Pemeriksaan;
import model.Pemilik;
import model.HewanDAO;
import view.KlinikView;

public class Service {

    private final ArrayList<Hewan> daftarHewan;
    private final KlinikView view;
    private final Scanner scanner;
    private final HewanDAO hewanDAO;

    public Service(KlinikView view, Scanner scanner) {
        this.daftarHewan = new ArrayList<>();
        this.view = view;
        this.scanner = scanner;
        this.hewanDAO = new HewanDAO();

        // Dummy data
        Pemilik pemilik = new Pemilik("Fatim", "081234567890");

        Pemeriksaan pemeriksaan = new Pemeriksaan("Tidak mau makan", "Gangguan pencernaan");

        daftarHewan.add(new Kucing(1, "Milo", 2, "Sudah", pemilik, pemeriksaan));
    }

    // =========================
    // VALIDASI INPUT
    // =========================

    private String bacaStringTidakKosong(String pesan) {

        while (true) {

            System.out.print(pesan);
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                System.out.println(">> Input tidak boleh kosong!");
            } 
            else {
                return input;
            }
        }
    }

    private int bacaAngkaPositif(String pesan) {

        while (true) {

            System.out.print(pesan);

            if (scanner.hasNextInt()) {

                int angka = scanner.nextInt();
                scanner.nextLine();

                if (angka < 0) {
                    System.out.println(">> Angka tidak boleh negatif!");
                } 
                else {
                    return angka;
                }

            } 
            else {

                System.out.println(">> Input harus berupa angka!");
                scanner.nextLine();
            }
        }
    }

    private String bacaNomorTelepon(String pesan) {

        while (true) {

            System.out.print(pesan);
            String noTelepon = scanner.nextLine();

            if (noTelepon.isEmpty()) {

                System.out.println(">> No. telepon tidak boleh kosong!");

            } 
            else if (!noTelepon.matches("\\d+")) {

                System.out.println(">> No. telepon harus berupa angka!");

            } 
            else if (noTelepon.length() < 10) {

                System.out.println(">> No. telepon minimal 10 digit!");

            } 
            else if (noTelepon.length() > 13) {

                System.out.println(">> No. telepon maksimal 13 digit!");

            } 
            else {

                return noTelepon;
            }
        }
    }

    private int cariIndexById(int idTarget) {

        for (int i = 0; i < daftarHewan.size(); i++) {

            if (daftarHewan.get(i).getIdData() == idTarget) {
                return i;
            }
        }

        return -1;
    }

    private boolean idSudahAda(int idData) {

        return cariIndexById(idData) != -1;
    }

    // =========================
    // TAMBAH DATA
    // =========================

    public void tambahHewan() {

        System.out.println("\n=== TAMBAH DATA KLINIK ===");

        int idData;

        while (true) {
            idData = bacaAngkaPositif("ID Data: ");

            if (idSudahAda(idData)) {
                System.out.println(">> ID sudah terdaftar! Silahkan gunakan ID lain!");
            } 
            else {
                break;
            }
        }

        System.out.println("\n--- Data Pemilik ---");
        String namaPemilik = bacaStringTidakKosong("Nama Pemilik: ");
        String noTelepon = bacaNomorTelepon("No. Telepon: ");

        Pemilik pemilik = new Pemilik(namaPemilik, noTelepon);

        System.out.println("\n--- Data Hewan ---");
        String namaHewan = bacaStringTidakKosong("Nama Hewan: ");

        System.out.println("Jenis Hewan:");
        System.out.println("1. Kucing");
        System.out.println("2. Anjing");
        System.out.println("3. Hewan Lain");

        int pilihanJenis;

        while (true) {

            pilihanJenis = bacaAngkaPositif("Pilih jenis hewan (1-3): ");

            if (pilihanJenis >= 1 && pilihanJenis <= 3) {
                break;
            }

            System.out.println(">> Pilihan harus 1-3!");
        }

        int umur = bacaAngkaPositif("Umur Hewan: ");
        Hewan hewanBaru;

        if (pilihanJenis == 1) {

            System.out.println("\n--- Vaksin Kucing ---");

            String statusVaksinF3 = bacaStringTidakKosong("Status Vaksin F3: ");

            Pemeriksaan pemeriksaan = buatPemeriksaan();

            hewanBaru = new Kucing(idData, namaHewan, umur, statusVaksinF3, pemilik, pemeriksaan);

        } 
        else if (pilihanJenis == 2) {

            System.out.println("\n--- Vaksin Anjing ---");

            String statusVaksinRabies = bacaStringTidakKosong("Status Vaksin Rabies: ");

            Pemeriksaan pemeriksaan = buatPemeriksaan();

            hewanBaru = new Anjing(idData, namaHewan, umur, statusVaksinRabies, pemilik, pemeriksaan);

        } 
        else {

            String jenisHewan = bacaStringTidakKosong("Jenis Hewan: ");

            Pemeriksaan pemeriksaan = buatPemeriksaan();

            hewanBaru = new HewanLain(idData, namaHewan, jenisHewan, umur, pemilik, pemeriksaan);
        }

        if (hewanDAO.tambahHewan(hewanBaru)) {
            daftarHewan.add(hewanBaru);
            System.out.println(">> Data klinik berhasil ditambahkan!");
        }
        else {
            System.out.println(">> Data klinik gagal ditambahkan!");
        }
    }

    private Pemeriksaan buatPemeriksaan() {

        System.out.println("\n--- Data Pemeriksaan ---");

        String keluhan = bacaStringTidakKosong("Keluhan: ");

        String hasil = bacaStringTidakKosong("Diagnosa: ");

        return new Pemeriksaan(keluhan, hasil);
    }

    // =========================
    // TAMPILKAN DATA
    // =========================

    public void tampilkanSemuaHewan() {
        daftarHewan.clear();
        daftarHewan.addAll(hewanDAO.tampilkanSemuaHewan());

        if (daftarHewan.isEmpty()) {
            System.out.println("\nBelum ada data klinik.");
            return;
        }

        System.out.println("\n=== DATA KLINIK HEWAN ===");

        for (Hewan h : daftarHewan) {

            System.out.println("\n=========================");
            System.out.println("ID Data: " + h.getIdData());

            System.out.println("\n--- Data Pemilik ---");
            System.out.println("Nama Pemilik: " + h.getPemilik().getNamaPemilik());
            System.out.println("No. Telepon: " + h.getPemilik().getNoTelepon());

            System.out.println("\n--- Data Hewan ---");
            System.out.println("Nama Hewan: " + h.getNamaHewan());
            System.out.println("Umur Hewan: " + h.getUmur());

            // Polymorphism
            view.cetakDetailHewan(h);

            System.out.println("--- Data Pemeriksaan ---");
            System.out.println("Keluhan: " + h.getPemeriksaan().getKeluhan());
            System.out.println("Diagnosa: " + h.getPemeriksaan().getHasil());

            System.out.println("=========================");
        }
    }

    // =========================
    // POLYMORPHISM OVERLOADING
    // =========================

    public Hewan cariHewan(int idData) {

        for (Hewan h : daftarHewan) {

            if (h.getIdData() == idData) {
                return h;
            }
        }

        return null;
    }

    public Hewan cariHewan(String namaHewan) {

        for (Hewan h : daftarHewan) {

            if (h.getNamaHewan().equalsIgnoreCase(namaHewan)) {

                return h;
            }
        }

        return null;
    }

    public void cariData() {

        System.out.println("\n=== CARI DATA HEWAN ===");
        System.out.println("1. Cari berdasarkan ID");
        System.out.println("2. Cari berdasarkan Nama");

        int pilihan = bacaAngkaPositif("Pilih pencarian (1-2): ");

        if (pilihan == 1) {

            int id = bacaAngkaPositif("Masukkan ID Data: ");

            Hewan h = cariHewan(id);

            if (h != null) {

                view.cetakDetailHewan(h);

            } else {

                System.out.println(">> Data tidak ditemukan!");
            }

        } else if (pilihan == 2) {

            String nama = bacaStringTidakKosong("Masukkan Nama Hewan: ");

            Hewan h = cariHewan(nama);

            if (h != null) {

                view.cetakDetailHewan(h);

            } else {

                System.out.println(">> Data tidak ditemukan!");
            }

        } else {

            System.out.println(">> Pilihan harus 1-2!");
        }
    }

    // =========================
    // UPDATE DATA
    // =========================

    public void updateHewan() {

        System.out.println("\n=== UPDATE DATA KLINIK ===");

        int idTarget = bacaAngkaPositif("Masukkan ID Data: ");

        Hewan h = cariHewan(idTarget);

        if (h == null) {

            System.out.println(">> Data tidak ditemukan!");

            return;
        }

        Pemilik p = h.getPemilik();
        Pemeriksaan pm = h.getPemeriksaan();

        // Update Pemilik

        System.out.println("\n--- Data Pemilik ---");

        String namaPemilikBaru = bacaStringTidakKosong("Nama Pemilik Baru: ");

        String noTeleponBaru = bacaNomorTelepon("No. Telepon Baru: ");

        p.setNamaPemilik(namaPemilikBaru);
        p.setNoTelepon(noTeleponBaru);

        // Update Hewan

        System.out.println("\n--- Data Hewan ---");

        String namaHewanBaru = bacaStringTidakKosong("Nama Hewan Baru: ");

        int umurBaru = bacaAngkaPositif("Umur Baru: ");

        h.setNamaHewan(namaHewanBaru);
        h.setUmur(umurBaru);

        // Update Vaksin Kucing

        if (h instanceof Kucing) {

            String statusVaksinF3Baru = bacaStringTidakKosong("Status Vaksin F3 Baru: ");

            Kucing kucing = (Kucing) h;

            kucing.setStatusVaksinF3(statusVaksinF3Baru);
        }

        // Update Vaksin Anjing

        else if (h instanceof Anjing) {

            String statusVaksinRabiesBaru = bacaStringTidakKosong("Status Vaksin Rabies Baru: ");

            Anjing anjing = (Anjing) h;

            anjing.setStatusVaksinRabies(statusVaksinRabiesBaru);
        }

        // Update Pemeriksaan

        System.out.println("\n--- Data Pemeriksaan ---");

        String keluhanBaru = bacaStringTidakKosong("Keluhan Baru: ");

        String diagnosaBaru = bacaStringTidakKosong("Diagnosa Baru: ");

        pm.setKeluhan(keluhanBaru);
        pm.setHasil(diagnosaBaru);

        if (hewanDAO.updateHewan(h)) {
            System.out.println(">> Data klinik berhasil diperbarui!");
        } else {
            System.out.println(">> Data klinik gagal diperbarui!");
}
    }

    // =========================
    // HAPUS DATA
    // =========================

    
    public void hapusHewan() {
        System.out.println("\n=== HAPUS DATA ===");

        int idTarget = bacaAngkaPositif("Masukkan ID Data: ");

        int index = cariIndexById(idTarget);

        if (index == -1) {
            System.out.println(">> Data tidak ditemukan!");
            return;
        }

        if (hewanDAO.hapusHewan(idTarget)) {
            daftarHewan.remove(index);
            System.out.println(">> Data klinik berhasil dihapus!");
        } else {
            System.out.println(">> Data klinik gagal dihapus!");
        }
    }
}