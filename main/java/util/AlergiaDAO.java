package util;

import modelo.Alergia;


import javax.persistence.EntityManager;
import java.util.List;

public class AlergiaDAO {

    private EntityManager em;

    public AlergiaDAO(EntityManager em) {
        this.em = em;
    }

    public void incluir(Alergia alergia) {
        this.em.persist(alergia);
    }

    public Alergia buscar(long id) {
        return this.em.find(Alergia.class, id);
    }

    public void excluir(long id) {
        Alergia alergia = em.find(Alergia.class, id);
        em.remove(alergia);
    }

    public List<Alergia> listarAlergias() {
        return em.createQuery("SELECT u FROM Alergia u", Alergia.class).getResultList();
    }
}


