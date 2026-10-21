import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    public void cadastrar(Reserva reserva) throws SQLException {
        String sql = "INSERT INTO reserva (id_cliente, entrada, saida, descricao, tipo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, reserva.getIdCliente());
            comando.setTimestamp(2, Timestamp.valueOf(reserva.getEntrada()));
            comando.setTimestamp(3, Timestamp.valueOf(reserva.getSaida()));
            comando.setString(4, reserva.getDescricao());
            comando.setString(5, reserva.getTipo());

            comando.executeUpdate();
        }
    }

    public List<Reserva> listar() throws SQLException {
        String sql = "SELECT id, id_cliente, entrada, saida, descricao, tipo FROM reserva";
        List<Reserva> reservas = new ArrayList<>();

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            while (resultado.next()) {
                Reserva reserva = new Reserva(
                        resultado.getInt("id"),
                        resultado.getInt("id_cliente"),
                        resultado.getTimestamp("entrada").toLocalDateTime(),
                        resultado.getTimestamp("saida").toLocalDateTime(),
                        resultado.getString("descricao"),
                        resultado.getString("tipo"));

                reservas.add(reserva);
            }
        }
        return reservas;
    }

    public int cancelar(int id) throws SQLException {

    String sql = "DELETE FROM reserva WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql))
        { comando.setInt(1, id);
          return comando.executeUpdate();
        }
    }

    public int remarcar(int id, LocalDateTime entrada, LocalDateTime saida) throws SQLException {
        String sql = "UPDATE reserva SET entrada = ?, saida = ? WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql))
        {
            comando.setTimestamp(1, Timestamp.valueOf(entrada));
            comando.setTimestamp(2, Timestamp.valueOf(saida));
            comando.setInt(3, id);
            return comando.executeUpdate();
        }
    }
}