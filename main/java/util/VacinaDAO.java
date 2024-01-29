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

    public Vacina buscar(int id) {
        return this.em.find(Vacina.class, id);
    }

    public void excluir(int id) {
        Vacina vacina = em.find(Vacina.class, id);
        em.remove(vacina);
    }

    public List<Vacina> listarVacinas() {
        return em.createQuery("SELECT u FROM Vacina u", Vacina.class).getResultList();
    }

    public List<Vacina> listarVacinasMaisAgendadas() {
        return em.createQuery("SELECT v FROM Vacina v LEFT JOIN Agenda a ON v.id = a.vacina.id GROUP BY v.id ORDER BY COUNT(a.vacina.id) DESC", Vacina.class).getResultList();
    }
}



