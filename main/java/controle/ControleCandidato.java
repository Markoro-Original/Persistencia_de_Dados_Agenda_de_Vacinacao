package controle;

import biblioteca.CandidatoDAO;
import negocio.Candidato;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/controlecandidato")
public class ControleCandidato extends HttpServlet {

    public static Candidato selectCandidato;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().append("Server at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String acao = request.getParameter("acao");
        if(acao.equals("adicionar")){
            try {
                adicinarCandidato(request, response);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else if(acao.equals("excluir")){
            try {
                excluirCandidato(request, response);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else if(acao.equals("selecionar")){
            try {
                selecionarCandidato(request, response);
            } catch (ParseException e){
                throw new RuntimeException(e);
            }
        } else if(acao.equals("alterar")){
            try {
                alterarCandidato(request, response);
            } catch (ParseException e){
                throw new RuntimeException(e);
            }
        }
    }

    public void adicinarCandidato(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {

        int codigo = Integer.parseInt(request.getParameter("txtCodigo"));
        String nome = request.getParameter("txtNome");
        String sexo = request.getParameter("selSexo");
        Date dataNasc = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dtDataNasc"));
        String cargoPretendido = request.getParameter("txtCargoPretendido");
        String textoCurriculo = request.getParameter("txtTextoCurriculo");

        Candidato candidato = new Candidato(codigo, nome, sexo.charAt(0), dataNasc, cargoPretendido, textoCurriculo);
        CandidatoDAO cdao = new CandidatoDAO();
        cdao.adicionar(candidato);

        response.sendRedirect("listagem.jsp");
    }

    public void excluirCandidato(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {

        int codigo = Integer.parseInt(request.getParameter("txtCodigo"));

        CandidatoDAO cdao = new CandidatoDAO();
        cdao.excluir(codigo);

        response.sendRedirect("listagem.jsp");
    }

    public void selecionarCandidato(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {

        int codigo = Integer.parseInt(request.getParameter("txtCodigo"));

        CandidatoDAO cdao = new CandidatoDAO();
        this.selectCandidato = cdao.getCandidato(codigo);

        response.sendRedirect("alterarCandidato.jsp");
    }

    public void alterarCandidato(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {

        int codigo = Integer.parseInt(request.getParameter("txtCodigo"));
        String nome = request.getParameter("txtNome");
        String sexo = request.getParameter("selSexo");
        Date dataNasc = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dtDataNasc"));
        String cargoPretendido = request.getParameter("txtCargoPretendido");
        String textoCurriculo = request.getParameter("txtTextoCurriculo");

        Candidato candidato = new Candidato(codigo, nome, sexo.charAt(0), dataNasc, cargoPretendido, textoCurriculo);
        CandidatoDAO cdao = new CandidatoDAO();
        cdao.alterar(candidato);

        response.sendRedirect("listagem.jsp");
    }
}

