
package config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static final EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("KlinikHewanPU");

    public static EntityManagerFactory getFactory() {
        return factory;
    }

    public static void close() {
        if (factory.isOpen()) {
            factory.close();
        }
    }
}
