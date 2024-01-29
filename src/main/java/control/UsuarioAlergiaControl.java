package control;

import modelo.Alergia;
import modelo.Usuario;
import util.AlergiaDAO;
import util.UsuarioDAO;

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

@WebServlet("/usuarioalergiacontrol")
public class UsuarioAlergiaControl extends HttpServlet {
    private EntityManagerFactory emf;

    @Override
    public void init(){
        emf = Persistence.createEntityManagerFactory("vacinacao");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Alergia> listaAlergias;

        String userID = request.getParameter("id");

        EntityManager em = emf.createEntityManager();

        try {
            AlergiaDAO alergiaDAO = new AlergiaDAO(em);
            UsuarioDAO usuarioDAO = new UsuarioDAO(em);

            listaAlergias = alergiaDAO.listarAlergias();
            Usuario usuario = usuarioDAO.buscar(Integer.parseInt(userID));

            request.setAttribute("listaAlergias", listaAlergias);
            request.setAttribute("usuario", usuario);

            RequestDispatcher dispatcher = request.getRequestDispatcher("novoUsuarioAlergia.jsp");
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
                adicionarUsuarioAlergia(request, response);
            } catch (ParseException e){
                throw new RuntimeException(e);
            }
        }
    }

    public void adicionarUsuarioAlergia(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {

        int userID = Integer.parseInt(request.getParameter("id"));
        int alergiaId = Integer.parseInt(request.getParameter("alergia"));

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Usuario usuario = new UsuarioDAO(em).buscar(userID);
            Alergia alergia = new AlergiaDAO(em).buscar(alergiaId);

            List<Alergia> alergias = usuario.getAlergias();
            alergias.add(alergia);
            usuario.setAlergias(alergias);

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

    @Override
    public void destroy() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
