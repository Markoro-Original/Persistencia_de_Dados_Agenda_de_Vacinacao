package control;

import modelo.Agenda;
import modelo.SituacaoAgenda;
import modelo.Usuario;
import modelo.Vacina;
import util.AgendaDAO;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/agendacontrol")
public class AgendaControl extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EntityManagerFactory emf;

    @Override
    public void init() {
        emf = Persistence.createEntityManagerFactory("vacinacao");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userID = request.getParameter("id");
        String filtroSituacao = request.getParameter("filtroSituacao");
        List<Agenda> listaAgendas;

        EntityManager em = emf.createEntityManager();

        try {
            AgendaDAO agendaDAO = new AgendaDAO(em);

            if (userID != null){
                listaAgendas = agendaDAO.listaAgendasPorUserID(Integer.parseInt(userID));
            } else if (filtroSituacao != null && !filtroSituacao.isEmpty()) {
                if(filtroSituacao.equals("REALIZADO") || filtroSituacao.equals("CANCELADO")){
                    listaAgendas = agendaDAO.listarAgendasPorSituacao(filtroSituacao);
                }else{
                    listaAgendas = agendaDAO.listarAgendasPorData(new Date());
                }
            } else {
                listaAgendas = agendaDAO.listarAgendas();
            }

            request.setAttribute("listaAgendas", listaAgendas);

            RequestDispatcher dispatcher = request.getRequestDispatcher("agendaListagem.jsp");
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
                adicionarAgenda(request, response);
            } catch (ParseException e){
                throw new RuntimeException(e);
            }
        }
        if(acao.equals("realizar")){
            try {
                marcarRealizado(request, response);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        if(acao.equals("cancelar")){
            try {
                marcarCancelado(request, response);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        if(acao.equals("delete")){
            try {
                deletarAgenda(request, response);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void adicionarAgenda(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {

        Date data = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("data"));
        String hora = request.getParameter("hora");
        String observacao = request.getParameter("observacao");

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Usuario usuario = new UsuarioDAO(em).buscar(Integer.parseInt(request.getParameter("usuarioId")));
            Vacina vacina = new VacinaDAO(em).buscar(Integer.parseInt(request.getParameter("vacinaId")));

            AgendaDAO agendaDAO = new AgendaDAO(em);

            if(vacina.getDoses() > 1){

                for(int i = 0; i < vacina.getDoses(); i++){
                    switch (vacina.getPeriodicidade()){
                        case 1:
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(data);
                            calendar.add(Calendar.DAY_OF_MONTH, vacina.getIntervalo()*i);
                            Date novaData = calendar.getTime();
                            Agenda agenda = new Agenda(novaData, hora, SituacaoAgenda.AGENDADO, observacao, usuario, vacina);
                            agendaDAO.incluir(agenda);

                            break;

                        case 2:
                            calendar = Calendar.getInstance();
                            calendar.setTime(data);
                            calendar.add(Calendar.WEEK_OF_YEAR, vacina.getIntervalo()*i);
                            novaData = calendar.getTime();
                            agenda = new Agenda(novaData, hora, SituacaoAgenda.AGENDADO, observacao, usuario, vacina);
                            agendaDAO.incluir(agenda);

                            break;

                        case 3:
                            calendar = Calendar.getInstance();
                            calendar.setTime(data);
                            calendar.add(Calendar.MONTH, vacina.getIntervalo()*i);
                            novaData = calendar.getTime();
                            agenda = new Agenda(novaData, hora, SituacaoAgenda.AGENDADO, observacao, usuario, vacina);
                            agendaDAO.incluir(agenda);

                            break;

                        case 4:
                            calendar = Calendar.getInstance();
                            calendar.setTime(data);
                            calendar.add(Calendar.YEAR, vacina.getIntervalo()*i);
                            novaData = calendar.getTime();
                            agenda = new Agenda(novaData, hora, SituacaoAgenda.AGENDADO, observacao, usuario, vacina);
                            agendaDAO.incluir(agenda);

                            break;
                    }
                }
            } else {
                Agenda agenda = new Agenda(data, hora, SituacaoAgenda.AGENDADO, observacao, usuario, vacina);
                agendaDAO.incluir(agenda);
            }

            transaction.commit();

            response.sendRedirect("agendacontrol");

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

    public void deletarAgenda(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("deleteId"));

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            AgendaDAO agendaDAO = new AgendaDAO(em);
            agendaDAO.excluir(id);
            transaction.commit();

            response.sendRedirect("agendacontrol");

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

    public void marcarRealizado(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("realizarId"));

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            AgendaDAO agendaDAO = new AgendaDAO(em);
            Agenda agenda = agendaDAO.buscar(id);
            agenda.setSituacao(SituacaoAgenda.REALIZADO);
            agenda.setDataSituacao(new Date());
            transaction.commit();

            response.sendRedirect("agendacontrol");

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

    public void marcarCancelado(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("cancelarId"));

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            AgendaDAO agendaDAO = new AgendaDAO(em);
            Agenda agenda = agendaDAO.buscar(id);
            agenda.setSituacao(SituacaoAgenda.CANCELADO);
            agenda.setDataSituacao(new Date());
            transaction.commit();

            response.sendRedirect("agendacontrol");

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












































