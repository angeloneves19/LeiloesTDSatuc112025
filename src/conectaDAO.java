import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/uc11?useSSL=false";
    private static final String USUARIO = "root";
    private static final String SENHA = "Coring@zuei656";

    public Connection connectDB() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO: " + erro.getMessage());
        }
        return conn;
    }
}