import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        int opcao;
        Scanner input = new Scanner(System.in);
        ReservaDAO reservaDAO = new ReservaDAO();

        do {
            System.out.println("------------------------------");
            System.out.println("[ 1 ] Listar reservas");
            System.out.println("[ 2 ] Cancelar reserva");
            System.out.println("[ 3 ] Remarcar reserva");
            System.out.println("[ 4 ] Criar reserva");
            System.out.println("[ 0 ] Sair");
            System.out.print("Escolha uma opção: ");

            opcao = Integer.parseInt(input.nextLine());
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
                int id = Integer.parseInt(input.nextLine());

                try {
                    int resultado = reservaDAO.cancelar(id);

                    if (resultado == 1) {
                        System.out.println("Reserva cancelada com sucesso!");
                    } else {
                        System.out.println("Reserva não encontrada!");
                    }
                } catch (SQLException e) {
                    System.out.println("ERRO: " + e.getMessage());
                }
            }
            else if (opcao == 3) {
                System.out.println("Remarcando reserva...");
            }
            else if (opcao == 4) {
                System.out.println("Criando nova reserva...");
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