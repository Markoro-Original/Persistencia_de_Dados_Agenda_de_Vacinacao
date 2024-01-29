package util;

import modelo.Alergia;
import modelo.Vacina;

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

    public Alergia buscar(int id) {
        return this.em.find(Alergia.class, id);
    }

    public void excluir(int id) {
        Alergia alergia = em.find(Alergia.class, id);
        em.remove(alergia);
    }

    public List<Alergia> listarAlergias() {
        return em.createQuery("SELECT u FROM Alergia u", Alergia.class).getResultList();
    }

    public List<Alergia> listarAlergiasMaisComuns() {
        return em.createQuery("SELECT a FROM Alergia a LEFT JOIN UsuarioAlergia u ON a.id = u.alergia.id GROUP BY a.id ORDER BY COUNT(u.alergia.id) DESC", Alergia.class).getResultList();
    }
}


