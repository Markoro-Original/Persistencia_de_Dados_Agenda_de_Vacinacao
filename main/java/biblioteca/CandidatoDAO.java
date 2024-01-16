package biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import negocio.Candidato;

public class CandidatoDAO {

    private Connection connection;

    public CandidatoDAO(){
        connection = FabricaDeConexao.connection();
    }

    public void adicionar(Candidato candidato){
        try {
            String sql = "INSERT INTO candidato(codigo, nome, sexo, data_nasc, cargo_pretendido, texto_curriculo) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = this.connection.prepareStatement(sql);

            stmt.setInt(1, candidato.getCodigo());
            stmt.setString(2, candidato.getNome());
            stmt.setString(3, String.valueOf(candidato.getSexo()));
            stmt.setDate(4, new java.sql.Date(candidato.getDataNasc().getTime()));
            stmt.setString(5, candidato.getCargoPretendido());
            stmt.setString(6, candidato.getTextoCurriculo());
            stmt.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void excluir(int codigo){
        try {
            String sql = "DELETE FROM candidato WHERE codigo = ?";

            PreparedStatement stmt = this.connection.prepareStatement(sql);

            stmt.setInt(1, codigo);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Candidato> getCandidatos(){
        List<Candidato> listaCandidatos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM candidato ORDER BY codigo ASC";
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Candidato candidato = new Candidato(
                        rs.getInt("codigo"),
                        rs.getString("nome"),
                        rs.getString("sexo").charAt(0),
                        rs.getDate("data_nasc"),
                        rs.getString("cargo_pretendido"),
                        rs.getString("texto_curriculo")
                );
                listaCandidatos.add(candidato);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return listaCandidatos;
    }

    public Candidato getCandidato(int codigo){
        try {
            String sql = "SELECT * FROM candidato WHERE codigo = ?";

            PreparedStatement stmt = this.connection.prepareStatement(sql);

            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Candidato candidato = new Candidato(
                        rs.getInt("codigo"),
                        rs.getString("nome"),
                        rs.getString("sexo").charAt(0),
                        rs.getDate("data_nasc"),
                        rs.getString("cargo_pretendido"),
                        rs.getString("texto_curriculo")
                );
                return candidato;
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public void alterar(Candidato candidato){
        try {
            String sql = "UPDATE candidato SET codigo = ?, nome = ?, sexo = ?, data_nasc = ?, cargo_pretendido = ?, texto_curriculo = ? WHERE codigo = ?";

            PreparedStatement stmt = this.connection.prepareStatement(sql);

            stmt.setInt(7, candidato.getCodigo());
            stmt.setInt(1, candidato.getCodigo());
            stmt.setString(2, candidato.getNome());
            stmt.setString(3, String.valueOf(candidato.getSexo()));
            stmt.setDate(4, new java.sql.Date(candidato.getDataNasc().getTime()));
            stmt.setString(5, candidato.getCargoPretendido());
            stmt.setString(6, candidato.getTextoCurriculo());

            stmt.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
