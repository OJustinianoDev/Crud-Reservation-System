import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexao {

    public static Connection conectar() throws SQLException {
        Properties config = new Properties();

        try (FileInputStream arquivo = new FileInputStream("db.properties")) {
            config.load(arquivo);
        } catch (IOException e) {
            throw new SQLException("Não foi possível ler o db.properties: " + e.getMessage());
        }

        String url = config.getProperty("url");
        String usuario = config.getProperty("usuario");
        String senha = config.getProperty("senha");

        return DriverManager.getConnection(url, usuario, senha);
    }
}