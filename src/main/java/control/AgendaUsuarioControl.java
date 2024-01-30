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

@WebServlet("/agendausuariocontrol")
public class AgendaUsuarioControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userID = request.getParameter("id");
        List<Agenda> listaAgendas;
        String nome;

        EntityManager em = JPAUtil.getEntityManager();

        try {
            AgendaDAO agendaDAO = new AgendaDAO(em);
            UsuarioDAO usuarioDAO = new UsuarioDAO(em);

            listaAgendas = agendaDAO.listaAgendasPorUserID(Integer.parseInt(userID));
            nome = usuarioDAO.buscar(Integer.parseInt(userID)).getNome();

            request.setAttribute("listaAgendas", listaAgendas);
            request.setAttribute("nome", nome);

            RequestDispatcher dispatcher = request.getRequestDispatcher("agendaUsuarioListagem.jsp");
            dispatcher.forward(request, response);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
