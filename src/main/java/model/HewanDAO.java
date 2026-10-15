package model;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HewanDAO {

    // READ
    public ArrayList<Hewan> tampilkanSemuaHewan() {
        ArrayList<Hewan> daftarHewan = new ArrayList<>();

        String sql = "SELECT h.id_hewan, h.nama_hewan, "
                + "h.jenis_hewan, h.umur, "
                + "h.status_vaksin_f3, h.status_vaksin_rabies, "
                + "p.nama_pemilik, p.no_telepon, "
                + "pm.keluhan, pm.diagnosis "
                + "FROM hewan h "
                + "JOIN pemilik p ON h.id_pemilik = p.id_pemilik "
                + "LEFT JOIN pemeriksaan pm ON h.id_hewan = pm.id_hewan";

        Connection conn = Koneksi.getConnection();

        if (conn == null) {System.out.println("Koneksi database gagal.");
            return daftarHewan;
        }

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pemilik pemilik = new Pemilik(rs.getString("nama_pemilik"), rs.getString("no_telepon"));

                Pemeriksaan pemeriksaan = new Pemeriksaan(rs.getString("keluhan"), rs.getString("diagnosis"));

                int id = rs.getInt("id_hewan");
                String nama = rs.getString("nama_hewan");
                String jenis = rs.getString("jenis_hewan");
                int umur = rs.getInt("umur");

                Hewan hewan;

                if (jenis.equalsIgnoreCase("Kucing")) {
                    hewan = new Kucing(id, nama, umur, rs.getString("status_vaksin_f3"), pemilik, pemeriksaan);
                } else if (jenis.equalsIgnoreCase("Anjing")) {
                    hewan = new Anjing(id, nama, umur, rs.getString("status_vaksin_rabies"), pemilik, pemeriksaan);
                } else {
                    hewan = new HewanLain(id, nama, jenis, umur, pemilik, pemeriksaan);
                }

                daftarHewan.add(hewan);
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data hewan: " + e.getMessage());
        }

        return daftarHewan;
    }

    // CREATE
    public boolean tambahHewan(Hewan hewan) {
        Connection conn = Koneksi.getConnection();

        if (conn == null) {
            System.out.println("Koneksi database gagal.");
            return false;
        }

        String sqlPemilik = "INSERT INTO pemilik "
                + "(id_pemilik, nama_pemilik, no_telepon) "
                + "VALUES (?, ?, ?)";

        String sqlHewan = "INSERT INTO hewan "
                + "(id_hewan, nama_hewan, jenis_hewan, umur, "
                + "id_pemilik, status_vaksin_f3, status_vaksin_rabies) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        String sqlPemeriksaan = "INSERT INTO pemeriksaan "
                + "(id_pemeriksaan, id_hewan, keluhan, diagnosis) "
                + "VALUES (?, ?, ?, ?)";

        try {
            conn.setAutoCommit(false);

            int idPemilik;
            int idPemeriksaan;

            String sqlIdPemilik = "SELECT COALESCE(MAX(id_pemilik), 0) + 1 FROM pemilik";

            try (PreparedStatement ps = conn.prepareStatement(sqlIdPemilik);
                 ResultSet rs = ps.executeQuery()) {
                rs.next();
                idPemilik = rs.getInt(1);
            }

            String sqlIdPemeriksaan = "SELECT COALESCE(MAX(id_pemeriksaan), 0) + 1 " + "FROM pemeriksaan";

            try (PreparedStatement ps = conn.prepareStatement(sqlIdPemeriksaan);
                 ResultSet rs = ps.executeQuery()) {
                rs.next();
                idPemeriksaan = rs.getInt(1);
            }

            // Simpan pemilik
            try (PreparedStatement ps = conn.prepareStatement(sqlPemilik)) {
                ps.setInt(1, idPemilik);
                ps.setString(2, hewan.getPemilik().getNamaPemilik());
                ps.setString(3, hewan.getPemilik().getNoTelepon());
                ps.executeUpdate();
            }

            String statusF3 = null;
            String statusRabies = null;

            if (hewan instanceof Kucing) {
                statusF3 = ((Kucing) hewan).getStatusVaksinF3();
            } else if (hewan instanceof Anjing) {
                statusRabies = ((Anjing) hewan).getStatusVaksinRabies();
            }

            // Simpan hewan
            try (PreparedStatement ps = conn.prepareStatement(sqlHewan)) {
                ps.setInt(1, hewan.getIdData());
                ps.setString(2, hewan.getNamaHewan());
                ps.setString(3, hewan.getJenisHewan());
                ps.setInt(4, hewan.getUmur());
                ps.setInt(5, idPemilik);
                ps.setString(6, statusF3);
                ps.setString(7, statusRabies);
                ps.executeUpdate();
            }

            // Simpan pemeriksaan
            try (PreparedStatement ps = conn.prepareStatement(sqlPemeriksaan)) {
                ps.setInt(1, idPemeriksaan);
                ps.setInt(2, hewan.getIdData());
                ps.setString(3, hewan.getPemeriksaan().getKeluhan());
                ps.setString(4, hewan.getPemeriksaan().getHasil());
                ps.executeUpdate();
            }

            conn.commit();
            System.out.println("Data berhasil disimpan ke database.");
            return true;

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.out.println("Gagal membatalkan transaksi: " + ex.getMessage());
            }

            System.out.println("Gagal menambah data: " + e.getMessage());
            return false;

        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Gagal mengatur koneksi: " + e.getMessage());
            }
        }
    }

    // UPDATE
    public boolean updateHewan(Hewan hewan) {
        Connection conn = Koneksi.getConnection();

        if (conn == null) {
            return false;
        }

        String sqlPemilik = "UPDATE pemilik p JOIN hewan h "
                + "ON p.id_pemilik = h.id_pemilik "
                + "SET p.nama_pemilik = ?, p.no_telepon = ? "
                + "WHERE h.id_hewan = ?";

        String sqlHewan = "UPDATE hewan SET nama_hewan = ?, umur = ?, "
                + "status_vaksin_f3 = ?, status_vaksin_rabies = ? "
                + "WHERE id_hewan = ?";

        String sqlPemeriksaan = "UPDATE pemeriksaan SET keluhan = ?, diagnosis = ? "
                + "WHERE id_hewan = ?";

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement psPemilik = conn.prepareStatement(sqlPemilik);
                 PreparedStatement psHewan = conn.prepareStatement(sqlHewan);
                 PreparedStatement psPemeriksaan = conn.prepareStatement(sqlPemeriksaan)) {

                Pemilik pemilik = hewan.getPemilik();
                Pemeriksaan pemeriksaan = hewan.getPemeriksaan();

                psPemilik.setString(1, pemilik.getNamaPemilik());
                psPemilik.setString(2, pemilik.getNoTelepon());
                psPemilik.setInt(3, hewan.getIdData());
                psPemilik.executeUpdate();

                String statusF3 = null;
                String statusRabies = null;

                if (hewan instanceof Kucing) {
                    statusF3 = ((Kucing) hewan).getStatusVaksinF3();
                } else if (hewan instanceof Anjing) {
                    statusRabies = ((Anjing) hewan).getStatusVaksinRabies();
                }

                psHewan.setString(1, hewan.getNamaHewan());
                psHewan.setInt(2, hewan.getUmur());
                psHewan.setString(3, statusF3);
                psHewan.setString(4, statusRabies);
                psHewan.setInt(5, hewan.getIdData());
                psHewan.executeUpdate();

                psPemeriksaan.setString(1, pemeriksaan.getKeluhan());
                psPemeriksaan.setString(2, pemeriksaan.getHasil());
                psPemeriksaan.setInt(3, hewan.getIdData());
                psPemeriksaan.executeUpdate();

                conn.commit();
                return true;
            }

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback gagal: " + ex.getMessage());
            }

            System.out.println("Gagal update: " + e.getMessage());
            return false;

        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Gagal mengatur koneksi: " + e.getMessage());
            }
        }
    }

    // DELETE
    public boolean hapusHewan(int idData) {
        Connection conn = Koneksi.getConnection();

        if (conn == null) {
            return false;
        }

        String sqlCariPemilik = "SELECT id_pemilik FROM hewan WHERE id_hewan = ?";

        String sqlHapusPemeriksaan = "DELETE FROM pemeriksaan WHERE id_hewan = ?";

        String sqlHapusHewan = "DELETE FROM hewan WHERE id_hewan = ?";

        String sqlHapusPemilik = "DELETE FROM pemilik WHERE id_pemilik = ? "
                + "AND NOT EXISTS "
                + "(SELECT 1 FROM hewan "
                + "WHERE hewan.id_pemilik = pemilik.id_pemilik)";

        try {
            conn.setAutoCommit(false);

            int idPemilik;

            try (PreparedStatement ps = conn.prepareStatement(sqlCariPemilik)) {
                ps.setInt(1, idData);

                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        conn.rollback();
                        return false;
                    }

                    idPemilik = rs.getInt("id_pemilik");
                }
            }

            try (PreparedStatement ps = conn.prepareStatement(sqlHapusPemeriksaan)) {
                ps.setInt(1, idData);
                ps.executeUpdate();
            }

            int jumlah;

            try (PreparedStatement ps = conn.prepareStatement(sqlHapusHewan)) {
                ps.setInt(1, idData);
                jumlah = ps.executeUpdate();
            }

            if (jumlah == 0) {
                conn.rollback();
                return false;
            }

            try (PreparedStatement ps = conn.prepareStatement(sqlHapusPemilik)) {
                ps.setInt(1, idPemilik);
                ps.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback gagal: " + ex.getMessage());
            }

            System.out.println("Gagal hapus: " + e.getMessage());
            return false;

        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Gagal mengatur koneksi: " + e.getMessage());
            }
        }
    }
}
