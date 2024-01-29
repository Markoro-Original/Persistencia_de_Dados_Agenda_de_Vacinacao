package control;

import modelo.Alergia;
import modelo.Vacina;
import util.AlergiaDAO;
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
import java.util.List;

@WebServlet("/alergiacontrol")
public class AlergiaControl extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private EntityManagerFactory emf;

    @Override
    public void init() {
        emf = Persistence.createEntityManagerFactory("vacinacao");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String alergiasMaisComuns = request.getParameter("alergiasMaisComuns");
        List<Alergia> listaAlergias;

        EntityManager em = emf.createEntityManager();

        try {
            AlergiaDAO alergiaDAO = new AlergiaDAO(em);

            if(alergiasMaisComuns != null && Integer.parseInt(alergiasMaisComuns) != 0){
                listaAlergias = alergiaDAO.listarAlergiasMaisComuns();
            } else {
                listaAlergias = alergiaDAO.listarAlergias();
            }

            request.setAttribute("listaAlergias", listaAlergias);

            RequestDispatcher dispatcher = request.getRequestDispatcher("alergiaListagem.jsp");
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
                adicionarAlergia(request, response);
            } catch (ParseException e){
                throw new RuntimeException(e);
            }
        }
        if(acao.equals("delete")){
            try {
                deletarAlergia(request, response);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void adicionarAlergia(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {

        String nome = request.getParameter("nome");
        Alergia alergia = new Alergia(nome);

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            AlergiaDAO alergiaDAO = new AlergiaDAO(em);
            alergiaDAO.incluir(alergia);
            transaction.commit();

            response.sendRedirect("index.jsp");
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

    public void deletarAlergia(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("deleteId"));

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            AlergiaDAO alergiaDAO = new AlergiaDAO(em);
            alergiaDAO.excluir(id);
            transaction.commit();

            response.sendRedirect("alergiacontrol");

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
