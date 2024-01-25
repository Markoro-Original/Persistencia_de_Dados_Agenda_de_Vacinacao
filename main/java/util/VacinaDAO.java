package util;

import modelo.Vacina;


import javax.persistence.EntityManager;
import java.util.List;

public class VacinaDAO {

    private EntityManager em;

    public VacinaDAO(EntityManager em) {
        this.em = em;
    }

    public void incluir(Vacina vacina) {
        this.em.persist(vacina);
    }

    public Vacina buscar(long id) {
        return this.em.find(Vacina.class, id);
    }

    public void excluir(long id) {
        Vacina vacina = em.find(Vacina.class, id);
        em.remove(vacina);
    }

    public List<Vacina> listarVacinas() {
        return em.createQuery("SELECT u FROM Vacina u", Vacina.class).getResultList();
    }
}



