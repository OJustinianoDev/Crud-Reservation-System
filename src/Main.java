import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        int opcao;
        Scanner input = new Scanner(System.in);
        ReservaDAO reservaDAO = new ReservaDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        do {
            System.out.println("------------------------------");
            System.out.println("[ 1 ] Listar reservas");
            System.out.println("[ 2 ] Cancelar reserva");
            System.out.println("[ 3 ] Remarcar reserva");
            System.out.println("[ 4 ] Criar reserva");
            System.out.println("[ 0 ] Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            System.out.println("------------------------------");

            if (opcao == 1) {
                System.out.println("Listando reservas...");

                try {
                    List<Reserva> listarReservas = reservaDAO.listar();

                    if (listarReservas == null || listarReservas.isEmpty()) {
                        System.out.println("Nenhuma reserva encontrada.");
                    } else {
                        for (Reserva r : listarReservas) {
                            System.out.println(r);
                        }
                    }
                } catch (SQLException e) {
                    System.out.println("ERRO: " + e.getMessage());
                }
            }
            else if (opcao == 2) {
                System.out.print("Digite o id da reserva: ");

                try {
                    int id = Integer.parseInt(input.nextLine());
                    int resultado = reservaDAO.cancelar(id);

                    if (resultado == 1) {
                        System.out.println("Reserva cancelada com sucesso!");
                    } else {
                        System.out.println("Reserva não encontrada!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ID inválido! Digite apenas números");
                } catch (SQLException e) {
                    System.out.println("ERRO: " + e.getMessage());
                }
            }
            else if (opcao == 3) {
                System.out.println("Remarcando reserva...");

                try {
                    System.out.print("Digite o ID da reserva: ");
                    int idReserva = Integer.parseInt(input.nextLine());

                    System.out.print("Nova entrada (dd/MM/yyyy HH:mm): ");
                    String texto = input.nextLine();
                    LocalDateTime entrada = LocalDateTime.parse(texto, formato);

                    System.out.print("Nova saída (dd/MM/yyyy HH:mm): ");
                    texto = input.nextLine();
                    LocalDateTime saida = LocalDateTime.parse(texto, formato);

                    int resultado = reservaDAO.remarcar(idReserva, entrada, saida);

                    if (resultado == 1) {
                        System.out.println("Reserva remarcada com sucesso!");
                    } else {
                        System.out.println("Reserva não encontrada!");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("ID inválido! Digite apenas números");
                } catch (DateTimeException e) {
                    System.out.println("Data inválida! Use o formato dd/MM/yyyy HH:mm");
                } catch (SQLException e) {
                    System.out.println("ERRO: " + e.getMessage());
                }
            }
            else if (opcao == 4) {
                try {
                    System.out.println("Criando nova reserva...");

                    System.out.print("Digite seu CPF: ");
                    String cpfCliente = input.nextLine();

                    String cpfClienteLimpo = cpfCliente.replaceAll("\\D", "");

                    Cliente cliente = clienteDAO.buscarPorCpf(cpfClienteLimpo);

                    if (cliente == null) {
                        System.out.println("Você ainda não é cliente, vamos fazer seu cadastro!");

                        System.out.print("Digite seu nome: ");
                        String nome = input.nextLine();

                        System.out.print("Digite seu telefone: ");
                        String telefone = input.nextLine();

                        String telefoneLimpo = telefone.replaceAll("\\D", "");

                        clienteDAO.cadastrar(nome, cpfClienteLimpo, telefoneLimpo);
                        cliente = clienteDAO.buscarPorCpf(cpfClienteLimpo);
                    }

                    System.out.print("Digite a descrição: ");
                    String descricaoReserva = input.nextLine();

                    System.out.print("Digite o tipo: ");
                    String tipoReserva = input.nextLine();

                    System.out.print("Digite a entrada (dd/MM/yyyy HH:mm): ");
                    String texto = input.nextLine();
                    LocalDateTime entrada = LocalDateTime.parse(texto, formato);

                    System.out.print("Digite a saída (dd/MM/yyyy HH:mm): ");
                    texto = input.nextLine();
                    LocalDateTime saida = LocalDateTime.parse(texto, formato);

                    Reserva reserva = new Reserva(0, cliente.getId(), entrada, saida, descricaoReserva, tipoReserva);
                    reservaDAO.cadastrar(reserva);

                    System.out.println("Reserva criada com sucesso!");

                } catch (DateTimeException e) {
                    System.out.println("Data inválida! Use o formato dd/MM/yyyy HH:mm");
                } catch (SQLException e) {
                    System.out.println("ERRO: " + e.getMessage());
                }
            }
            else if (opcao == 0) {
                System.out.println("Saindo do sistema...");
            }
            else {
                System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }
}