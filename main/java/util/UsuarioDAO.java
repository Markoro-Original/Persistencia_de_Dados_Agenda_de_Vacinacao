package util;

import modelo.Agenda;
import modelo.SituacaoAgenda;
import modelo.Usuario;

import javax.persistence.EntityManager;
import java.util.List;

public class UsuarioDAO {

    private EntityManager em;

    public UsuarioDAO(EntityManager em) {
        this.em = em;
    }

    public void incluir(Usuario cur) {
        this.em.persist(cur);
    }

    public Usuario buscar(int id) {
        return this.em.find(Usuario.class, id);
    }

    public void excluir(int id) {
        Usuario usuario = em.find(Usuario.class, id);
        em.remove(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }

    public List<Usuario> listarAgendasPorUF(String uf) {
        return em.createQuery("SELECT u FROM Usuario u WHERE u.uf = :uf", Usuario.class)
                .setParameter("uf", uf)
                .getResultList();
    }
}

