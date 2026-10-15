
package config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import model.PemilikORM;

public class TestORM {

    public static void main(String[] args) {
        EntityManager em = null;

        try {
            em = JPAUtil.getFactory().createEntityManager();

            TypedQuery<PemilikORM> query = em.createQuery("SELECT p FROM PemilikORM p", PemilikORM.class);

            List<PemilikORM> daftarPemilik = query.getResultList();

            for (PemilikORM p : daftarPemilik) {
                System.out.println("ID Pemilik: " + p.getIdPemilik());
                System.out.println("Nama: " + p.getNamaPemilik());
                System.out.println("Telepon: " + p.getNoTelepon());
                System.out.println("----------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }

            JPAUtil.close();
        }
    }
}
