package util;

import modelo.Agenda;
import modelo.SituacaoAgenda;
import modelo.Usuario;
import modelo.Vacina;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

    public List<Usuario> buscarPorNome(String nome) {
        try {
            return em.createQuery("SELECT a FROM Usuario a WHERE lower(a.nome) = :nome", Usuario.class)
                    .setParameter("nome", nome.toLowerCase())
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
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

