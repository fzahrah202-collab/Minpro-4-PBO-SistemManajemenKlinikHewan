
package config;

import jakarta.persistence.EntityManager;
import model.PemilikORM;

public class TestHapusORM {

    public static void main(String[] args) {
        EntityManager em = null;

        try {
            em = JPAUtil.getFactory().createEntityManager();
            em.getTransaction().begin();

            PemilikORM pemilik = em.find(PemilikORM.class, 3);

            if (pemilik != null) {
                em.remove(pemilik);
                em.getTransaction().commit();

                System.out.println("Data pemilik berhasil dihapus!");
            } else {
                em.getTransaction().rollback();
                System.out.println("Data dengan ID 3 tidak ditemukan.");
            }

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
