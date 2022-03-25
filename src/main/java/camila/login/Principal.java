package camila.login;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        DAO dao = new DAO();
        dao.conectar();
        Scanner teclado = new Scanner(System.in);
        boolean sair = false;

        while (sair == false) {
            switch (Opcao(teclado)) {
                case 1:
                    try {
                        Login[] logins = dao.getLogins();
                        for (int i = 0; i < logins.length; i++) {
                            System.out.println(logins[i].toString());
                        }
                    } catch (Exception e) {
                        System.out.println("==== Atencao ====\nVAZIO, favor listar novos itens\n==============");
                    }
                    break;

                case 2:
                    Login loginInserido = getLoginInfo(teclado);
                    if (dao.inserirLogin(loginInserido) == true)
                        System.out.println("Login inserido com sucesso: " + loginInserido.toString());
                    else
                        System.out.println("Erro ao inserir login");
                    break;

                case 3:
                    System.out.print("Id para excluir: ");
                    int id = teclado.nextInt();
                    if (dao.excluirLogin(id) == true)
                        System.out.println("Login excluído!");
                    else
                        System.out.println("Erro ao excluir login de id " + id);
                    break;

                case 4:
                    Login loginAtualizada = getLoginInfo(teclado);
                    if (dao.atualizarLogin(loginAtualizada) == true)
                        System.out.println("Login atualizado com sucesso: " + loginAtualizada.toString());
                    else
                        System.out.println("Erro ao atualizar o login.");
                    break;

                case 5:
                    sair = true;
                    break;

                default:
                    System.out.println("\nOpção inválida!!!");
            }
        }

        System.out.println("Fim do programa");
        teclado.close();
        dao.close();
        System.out.println("---");
    }

    public static int Opcao(Scanner teclado) {
        int opcao;
        System.out.print("Digite o que deseja realizar:\n" +
                "1) Listar\n2) Inserir\n3) Excluir\n4) Atualizar\n5) Sair\nDigite: ");
        opcao = teclado.nextInt();
        return opcao;
    }

    public static Login getLoginInfo(Scanner scan) {
        int id;
        String usuario;
        String senha;
        char sexo;
        String cidade;
        String estado;

        System.out.print("Digite o id: ");
        id = scan.nextInt();

        System.out.print("Digite o seu usuário: ");
        usuario = scan.nextLine();
        scan.nextLine();
        System.out.print("Digite sua senha: ");
        senha = scan.nextLine();

        System.out.print("Digite o seu sexo: ");
        sexo = scan.nextLine().charAt(0);

        System.out.print("Digite sua cidade: ");
        cidade = scan.nextLine();

        System.out.print("Digite seu estado: ");
        estado = scan.nextLine();

        Login login = new Login(id, usuario, senha, sexo, cidade, estado);

        return login;
    }
}
