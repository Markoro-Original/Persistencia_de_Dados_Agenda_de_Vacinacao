package util;

import modelo.Agenda;
import modelo.Usuario;

import javax.persistence.EntityManager;
import java.util.List;

public class AgendaDAO {

    private EntityManager em;

    public AgendaDAO(EntityManager em) {
        this.em = em;
    }

    public void incluir(Agenda agenda) {
        this.em.persist(agenda);
    }

    public Agenda buscar(long id) {
        return this.em.find(Agenda.class, id);
    }

    public void excluir(long id) {
        Agenda agenda = em.find(Agenda.class, id);
        em.remove(agenda);
    }

    public List<Agenda> listarAgendas() {
        return em.createQuery("SELECT u FROM Agenda u", Agenda.class).getResultList();
    }
}
