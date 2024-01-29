package control;

import modelo.Usuario;
import modelo.Vacina;
import util.JPAUtil;
import util.UsuarioDAO;
import util.VacinaDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/usuariocontrol")
public class UsuarioControl extends HttpServlet {
    private EntityManagerFactory emf;

    @Override
    public void init(){
        emf = Persistence.createEntityManagerFactory("vacinacao");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String filtroUF = request.getParameter("filtroUF");
        List<Usuario> listaUsuarios;

        EntityManager em = emf.createEntityManager();

        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO(em);

            if (filtroUF != null && !filtroUF.isEmpty()) {
                listaUsuarios = usuarioDAO.listarAgendasPorUF(filtroUF);
            } else {
                listaUsuarios = usuarioDAO.listarUsuarios();
            }

            request.setAttribute("listaUsuarios", listaUsuarios);

            RequestDispatcher dispatcher = request.getRequestDispatcher("usuarioListagem.jsp");
            dispatcher.forward(request, response);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String acao = request.getParameter("acao");

        if(acao.equals("adicionar")){
            try {
                adicionarUsuario(request, response);
            } catch (ParseException e){
                throw new RuntimeException(e);
            }
        }
        if(acao.equals("delete")){
            try {
                deletarUsuario(request, response);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void adicionarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {

        String nome = request.getParameter("nome");
        Date data_nascimento = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("data_nascimento"));
        String sexo = request.getParameter("sexo");
        String logradouro = request.getParameter("logradouro");
        int numero = Integer.parseInt(request.getParameter("numero"));
        String setor = request.getParameter("setor");
        String cidade = request.getParameter("cidade");
        String uf = request.getParameter("uf");

        Usuario usuario = new Usuario(nome, data_nascimento, sexo.charAt(0), logradouro, numero, setor, cidade, uf);

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            UsuarioDAO usuarioDAO = new UsuarioDAO(em);
            usuarioDAO.incluir(usuario);
            transaction.commit();

            String tipoSalvar = request.getParameter("tipoSalvar");

            if(tipoSalvar.equals("Salvar")){
                response.sendRedirect("index.jsp");
            } else {
                response.sendRedirect("usuarioalergiacontrol?id=" + usuario.getId());
            }

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public void deletarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("deleteId"));

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            UsuarioDAO usuarioDAO = new UsuarioDAO(em);
            usuarioDAO.excluir(id);
            transaction.commit();

            response.sendRedirect("usuariocontrol");

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void destroy() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
