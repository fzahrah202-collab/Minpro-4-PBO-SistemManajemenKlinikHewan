package main;

import java.util.Scanner;
import controller.Service;
import view.KlinikView;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        KlinikView view = new KlinikView();

        Service service = new Service(view, scanner);

        boolean berjalan = true;

        while (berjalan) {

            System.out.println("\n=================================");
            System.out.println("   SISTEM MANAJEMEN KLINIK HEWAN");
            System.out.println("=================================");
            System.out.println("1. Tambah Data Hewan");
            System.out.println("2. Tampilkan Data Hewan");
            System.out.println("3. Cari Data Hewan");
            System.out.println("4. Update Data Hewan");
            System.out.println("5. Hapus Data Hewan");
            System.out.println("6. Keluar");
            System.out.println("=================================");

            System.out.print("Pilih menu : ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {

                case 1:
                    service.tambahHewan();
                    break;

                case 2:
                    service.tampilkanSemuaHewan();
                    break;

                case 3:
                    service.cariData();
                    break;

                case 4:
                    service.updateHewan();
                    break;

                case 5:
                    service.hapusHewan();
                    break;

                case 6:
                    berjalan = false;
                    System.out.println("Selamat Tinggal ^^!");
                    break;

                default:
                    System.out.println("Pilihan menu tidak valid!.");
            }
        }

        scanner.close();
    }
}