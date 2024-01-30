package util;

import modelo.Agenda;
import modelo.SituacaoAgenda;
import modelo.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;

public class AgendaDAO {

    private EntityManager em;

    public AgendaDAO(EntityManager em) {
        this.em = em;
    }

    public void incluir(Agenda agenda) {
        this.em.persist(agenda);
    }

    public Agenda buscar(int id) {
        return this.em.find(Agenda.class, id);
    }

    public List<Agenda> buscarPorNomeUsuario(String nomeUsuario) {
        try {
            return em.createQuery("SELECT a FROM Agenda a WHERE lower(a.usuario.nome) = :nomeUsuario", Agenda.class)
                    .setParameter("nomeUsuario", nomeUsuario.toLowerCase())
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Agenda> buscarPorVacina(String vacina) {
        try {
            return em.createQuery("SELECT a FROM Agenda a WHERE lower(a.vacina.titulo) = :vacina", Agenda.class)
                    .setParameter("vacina", vacina.toLowerCase())
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void excluir(int id) {
        Agenda agenda = em.find(Agenda.class, id);
        em.remove(agenda);
    }

    public List<Agenda> listarAgendas() {
        return em.createQuery("SELECT u FROM Agenda u ORDER BY CASE u.situacao WHEN 'AGENDADO' THEN 1 WHEN 'REALIZADO' THEN 2 WHEN 'CANCELADO' THEN 3 ELSE 4 END, u.data ASC", Agenda.class).getResultList();
    }

    public List<Agenda> listarAgendasPorSituacao(String situacao) {
        return em.createQuery("SELECT u FROM Agenda u WHERE u.situacao = :situacao ORDER BY u.data ASC", Agenda.class)
                .setParameter("situacao", SituacaoAgenda.valueOf(situacao))
                .getResultList();
    }

    public List<Agenda> listarAgendasPorData(Date data){
        return em.createQuery("SELECT u FROM Agenda u WHERE cast(u.data as date) = :data ORDER BY CASE u.situacao WHEN 'AGENDADO' THEN 1 WHEN 'REALIZADO' THEN 2 WHEN 'CANCELADO' THEN 3 ELSE 4 END, u.data ASC", Agenda.class)
                .setParameter("data", data)
                .getResultList();
    }

    public List<Agenda> listaAgendasPorUserID(int id){
        return em.createQuery("SELECT u FROM Agenda u WHERE u.usuario.id = :id ORDER BY CASE u.situacao WHEN 'AGENDADO' THEN 1 WHEN 'REALIZADO' THEN 2 WHEN 'CANCELADO' THEN 3 ELSE 4 END, u.data ASC", Agenda.class)
                .setParameter("id", id)
                .getResultList();
    }
}
