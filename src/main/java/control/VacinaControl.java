package control;

import modelo.Vacina;
import util.AlergiaDAO;
import util.JPAUtil;
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

@WebServlet("/vacinacontrol")
public class VacinaControl extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EntityManagerFactory emf;

    @Override
    public void init() {
        emf = Persistence.createEntityManagerFactory("vacinacao");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vacinasMaisAgendadas = request.getParameter("vacinasMaisAgendadas");
        List<Vacina> listaVacinas;

        EntityManager em = emf.createEntityManager();

        try {
            VacinaDAO vacinaDAO = new VacinaDAO(em);

            if(vacinasMaisAgendadas != null && Integer.parseInt(vacinasMaisAgendadas) != 0){
                listaVacinas = vacinaDAO.listarVacinasMaisAgendadas();
            } else {
                listaVacinas = vacinaDAO.listarVacinas();
            }

            request.setAttribute("listaVacinas", listaVacinas);

            RequestDispatcher dispatcher = request.getRequestDispatcher("vacinaListagem.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
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
                adicionarVacina(request, response);
            } catch (ParseException e){
                throw new RuntimeException(e);
            }
        }
        if(acao.equals("delete")){
            try {
                deletarVacina(request, response);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void adicionarVacina(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {

        String titulo = request.getParameter("titulo");
        String descricao = request.getParameter("descricao");
        int doses = Integer.parseInt(request.getParameter("doses"));

        Vacina vacina = new Vacina(titulo, descricao, doses);

        if (doses != 1) {
            int periodicidade = Integer.parseInt(request.getParameter("periodicidade"));
            int intervalo = Integer.parseInt(request.getParameter("intervalo"));

            vacina.setPeriodicidade(periodicidade);
            vacina.setIntervalo(intervalo);
        }

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            VacinaDAO vacinaDAO = new VacinaDAO(em);
            vacinaDAO.incluir(vacina);
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

    public void deletarVacina(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("deleteId"));

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            VacinaDAO vacinaDAO = new VacinaDAO(em);
            vacinaDAO.excluir(id);
            transaction.commit();

            response.sendRedirect("vacinacontrol");

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
