package br.com.gui336.liferpgtracker.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;
import br.com.gui336.liferpgtracker.data.UserRepositoryJDBC;
import br.com.gui336.liferpgtracker.domain.User;

public class RepositoryTest {

	public static void main(String[] args) {

		int op = 0;
		Scanner scan = new Scanner(System.in);
		UserRepositoryJDBC repository = new UserRepositoryJDBC();
		do {
			String menuInicial = "--------------------------------- \n" + "|             MENU              | \n"
					+ "--------------------------------- \n" + "|1.TESTE CONEXÃO                | \n"
					+ "|2.CRIAR NOVA CONTA             | \n" + "|3.BUSCAR CONTA POR ID          | \n"
					+ "|4.BUSCAR CONTA POR EMAIL       | \n" + "|5.ALTERAR NICKNAME             | \n"
					+ "|6.ENCERRAR                     | \n" + "--------------------------------- \n"
					+ "DIGITE SUA OPÇÃO: ";

			System.out.print(menuInicial);

			op = scan.nextInt();
			scan.nextLine();

			switch (op) {

			case 1:
				System.out.println("Tentando conectar ao banco de dados...");

		        try (Connection conn = DatabaseConnection.getConnection()) {
		            
		            // Se chegou até aqui sem disparar exceção, a conexão foi bem-sucedida!
		            if (conn != null && !conn.isClosed()) {
		                System.out.println("✅ Conexão estabelecida com sucesso!");
		                System.out.println("Banco de dados conectado: " + conn.getCatalog());
		            }

		        } catch (SQLException e) {
		            System.err.println("❌ Erro ao conectar ao banco de dados!");
		            System.err.println("Causa do erro: " + e.getMessage());
		            e.printStackTrace();
		        } catch (Exception e) {
		            System.err.println("❌ Ocorreu um erro inesperado!");
		            e.printStackTrace();
		        }
				break;
			case 2:
				String email;
				String nome;
				String nickName;
				System.out.print("Digite o EMAIL: ");
				email = scan.nextLine();

				System.out.print("Digite o NOME: ");
				nome = scan.nextLine();

				System.out.print("Digite o NICKNAME: ");
				nickName = scan.nextLine();

				try {
					User user = repository.create(email, nome, nickName);

					System.out.println("Usuário registrado com sucesso!");
					System.out.println("----ID: " + user.getId() + "---- \n" + "----NOME: " + user.getName() + "----\n"
							+ "----NICKNAME: " + user.getNickname() + "----\n" + "----EMAIL: " + user.getEmail()
							+ "----");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 3:
				int idBusca;
				System.out.println("Informe o id: ");
				idBusca = scan.nextInt();
				scan.nextLine();

				try {
					Optional<User> resultado = repository.findById(idBusca);

					if (resultado.isPresent()) {
						User user = resultado.get();

						System.out.println("Usuário encontrado com sucesso!");
						System.out.println(
								"----ID: " + user.getId() + "---- \n" + "----NICKNAME: " + user.getNickname() + "----");
					}

					else {
						System.out.println("USUÁRIO COM ID: " + idBusca + ", NÃO EXISTE!");
					}

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 4:
				String emailBusca;
				System.out.println("Informe o email: ");
				emailBusca = scan.nextLine();

				try {
					Optional<User> resultado = repository.findByEmail(emailBusca);

					if (resultado.isPresent()) {
						User user = resultado.get();

						System.out.println("Usuário encontrado com sucesso!");
						System.out.println(
								"----ID: " + user.getId() + "---- \n" + "----NICKNAME: " + user.getNickname() + "----\n" + "----EMAIL: " + user.getEmail()
								+ "----");
					}

					else {
						System.out.println("USUÁRIO COM EMAIL: " + emailBusca + ", NÃO EXISTE!");
					}

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				int idtroca;
				String nickNovo;
				
				System.out.println("Informe o seu id: ");
				idtroca= scan.nextInt();
				scan.nextLine();
				System.out.println("Informe o novo nickname: ");
				nickNovo = scan.nextLine();
				try {
					
					repository.updateNickname(idtroca, nickNovo);
					System.out.println("TROCA EALIZADA!!");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;

				
			case 6:
				System.out.println("TEXTE ENCERRADO...");
				break;
			default:
				System.out.println("OPÇÃO INVÁLIDA!!");
				
				
			}

		} while (op != 6);

		scan.close();
	}
}
