package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    private static final EntityManagerFactory FABRICA = Persistence.createEntityManagerFactory("vacinacao");

    public static EntityManager getEntityManager() {
        return FABRICA.createEntityManager();
    }

}
