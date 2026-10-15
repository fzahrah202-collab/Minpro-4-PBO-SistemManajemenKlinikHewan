
package config;

import jakarta.persistence.EntityManager;
import model.PemilikORM;

public class TestSimpanORM {

    public static void main(String[] args) {
        EntityManager em = null;

        try {
            em = JPAUtil.getFactory().createEntityManager();

            em.getTransaction().begin();

            PemilikORM pemilik = new PemilikORM();
            pemilik.setIdPemilik(3);
            pemilik.setNamaPemilik("Pemilik Uji ORM");
            pemilik.setNoTelepon("089876543210");

            em.persist(pemilik);

            em.getTransaction().commit();

            System.out.println("Data berhasil disimpan melalui ORM!");

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace();

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }

            JPAUtil.close();
        }
    }
}
