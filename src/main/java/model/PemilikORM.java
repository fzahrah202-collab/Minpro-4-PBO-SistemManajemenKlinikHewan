
package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "pemilik")
public class PemilikORM {

    @Id
    @Column(name = "id_pemilik")
    private int idPemilik;

    @Column(name = "nama_pemilik")
    private String namaPemilik;

    @Column(name = "no_telepon")
    private String noTelepon;

    public PemilikORM() {
    }

    public int getIdPemilik() {
        return idPemilik;
    }

    public void setIdPemilik(int idPemilik) {
        this.idPemilik = idPemilik;
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
