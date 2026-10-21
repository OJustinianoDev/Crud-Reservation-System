import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {

    public void cadastrar(String nome, String cpf, String telefone) throws SQLException {
        String sql = "INSERT INTO cliente (nome, cpf, telefone) VALUES (?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, nome);
            comando.setString(2, cpf);
            comando.setString(3, telefone);

            comando.executeUpdate();
        }
    }

    public Cliente buscarPorCpf(String cpf) throws SQLException {
        String sql = "SELECT id, nome, cpf, telefone FROM cliente WHERE cpf = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, cpf);

            try (ResultSet resultado = comando.executeQuery()) {
                if (resultado.next()) {
                    return new Cliente(
                            resultado.getInt("id"),
                            resultado.getString("nome"),
                            resultado.getString("cpf"),
                            resultado.getString("telefone"));
                }
            }
        }
        return null;
    }
}