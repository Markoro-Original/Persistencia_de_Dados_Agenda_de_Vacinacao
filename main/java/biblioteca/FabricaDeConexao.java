package biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class FabricaDeConexao {
    static String driver = "org.postgresql.Driver";
    static String url = "jdbc:postgresql://localhost:5432/javaweb";
    static String user = "postgres";
    static String senha = "postgres";

    public static Connection connection(){
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, senha);
            return con;
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}
