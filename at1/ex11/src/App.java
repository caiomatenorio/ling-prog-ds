import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        boolean success;

        var scanner = new Scanner(System.in);
        Map<String, String> users = new HashMap<>();

        try {
            while (true) {
                int operation = chooseOperation(scanner);

                switch (operation) {
                    case 1:
                        success = login(scanner, users);

                        if (success)
                            return;
                        break;
                    case 2:
                        cadastro(scanner, users);
                        break;
                    default:
                        System.out.println("Adeus!");
                        return;
                }
            }
        } finally {
            scanner.close();
        }
    }

    private static int chooseOperation(Scanner scanner) {
        int operation;

        System.out.println("Escolha a operação a ser feita:");
        System.out.println("1 - Login");
        System.out.println("2 - Cadastro");
        System.out.println("0 - Sair" + System.lineSeparator());

        while (true) {
            System.out.print("Insira a opção: ");

            if (scanner.hasNextInt()) {
                operation = scanner.nextInt();

                if (operation == 0 || operation == 1 || operation == 2) {
                    System.out.println();
                    return operation;
                }
            }

            System.out.println("Insira 0, 1 ou 2." + System.lineSeparator());
        }
    }

    private static boolean login(Scanner scanner, Map<String, String> users) {
        System.out.print("Insira o nome de usuário: ");
        String username = scanner.next();

        System.out.print("Insira a senha: ");
        String password = scanner.next();

        if (!(users.containsKey(username) && users.get(username).equals(password))) {
            System.out.println("Nome de usuário ou senha incorretos." + System.lineSeparator());
            return false;
        }

        System.out.println("Usuário logado com sucesso!");
        return true;
    }

    private static void cadastro(Scanner scanner, Map<String, String> users) {
        String username, password;

        while (true) {
            System.out.print("Insira o nome de usuário: ");
            username = scanner.next();

            if (username.matches("^[a-zA-Z0-9]{3,32}$")) {
                System.out.println();
                break;
            } else if (users.containsKey(username)) {
                System.out.println("Este nome de usuário já está em uso." + System.lineSeparator());
            } else {
                System.out.println(
                        "O nome de usuário deve possuir de 3 a 32 caracteres alfanuméricos." + System.lineSeparator());
            }
        }

        while (true) {
            System.out.print("Insira a senha: ");
            password = scanner.next();

            if (password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{8,64}$")) {
                System.out.println();
                break;
            }

            System.out.println(
                    "A senha deve possuir de 8 a 64 caracteres, contendo pelo menos uma letra minúscula, uma letra "
                            + "maiúscula, um número e um caractere especial."
                            + System.lineSeparator());
        }

        users.put(username, password);
    }
}
