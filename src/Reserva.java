import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reserva {

    // Atributos

    private int id;
    private int idCliente;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private String descricao;
    private String tipo;

    // Construtor

    public Reserva(int id, int idCliente, LocalDateTime entrada, LocalDateTime saida, String descricao, String tipo) {
        this.id = id;
        this.idCliente = idCliente;
        this.entrada = entrada;
        this.saida = saida;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    // Métodos especiais

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return "Reserva " + id + " | cliente " + idCliente
                + " | " + entrada.format(formato) + " até " + saida.format(formato)
                + " | " + descricao + " (" + tipo + ")";
    }
}