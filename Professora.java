import java.util.List;
import java.util.Scanner;

/**
 * Professora Anna - Assistente de programacao para iniciantes
 * Auxilia estudantes com revisao de codigo Java e Python
 * 
 * @author Bernardo Pereira Dutra
 */
public class Professora {

    private static Scanner scanner = new Scanner(System.in);
    private static final String NOME = "Prof. Anna";

    public static void main(String[] args) {
        exibirBoasVindas();

        boolean executando = true;

        while (executando) {
            System.out.print("\nVoce: ");
            String comando = scanner.nextLine().trim().toLowerCase();

            switch (comando) {
                case "revisar":
                    revisarCodigo();
                    break;
                case "conceito":
                    aprenderConceito();
                    break;
                case "dica":
                    Conceitos.mostrarDica();
                    break;
                case "ajuda":
                    exibirAjuda();
                    break;
                case "sair":
                    System.out.println("\n" + NOME + ": Ate a proxima! Bons estudos!");
                    executando = false;
                    break;
                default:
                    System.out.println(NOME + ": Comando invalido. Digite 'ajuda' para ver as opcoes.");
            }
        }

        scanner.close();
    }

    private static void exibirBoasVindas() {
        System.out.println("\n==========================================");
        System.out.println("  Ola! Eu sou a " + NOME);
        System.out.println("  Sua assistente de programacao!");
        System.out.println("==========================================");
        System.out.println("\n+----------+------------------------------+");
        System.out.println("| Comando  | Descricao                    |");
        System.out.println("+----------+------------------------------+");
        System.out.println("| revisar  | Analiso seu codigo           |");
        System.out.println("| conceito | Explico um tema              |");
        System.out.println("| dica     | Dica de boas praticas        |");
        System.out.println("| ajuda    | Mostra este menu             |");
        System.out.println("| sair     | Encerra o programa           |");
        System.out.println("+----------+------------------------------+");
        System.out.println("\nLinguagens suportadas: Java e Python");
    }

    private static void exibirAjuda() {
        System.out.println("\n" + NOME + ": Posso te ajudar com:\n");
        System.out.println("+----------+------------------------------+");
        System.out.println("| Comando  | Descricao                    |");
        System.out.println("+----------+------------------------------+");
        System.out.println("| revisar  | Analiso seu codigo           |");
        System.out.println("| conceito | Explico um tema              |");
        System.out.println("| dica     | Dica de boas praticas        |");
        System.out.println("| sair     | Encerra o programa           |");
        System.out.println("+----------+------------------------------+");
    }

    private static void revisarCodigo() {
        // Escolher linguagem
        System.out.println("\n" + NOME + ": Qual linguagem?\n");
        System.out.println("+--------+----------+");
        System.out.println("| Opcao  | Linguagem|");
        System.out.println("+--------+----------+");
        System.out.println("|   1    | Java     |");
        System.out.println("|   2    | Python   |");
        System.out.println("+--------+----------+");
        System.out.print("\nEscolha: ");

        String opcao = scanner.nextLine().trim();
        String linguagem;

        if (opcao.equals("1")) {
            linguagem = "java";
        } else if (opcao.equals("2")) {
            linguagem = "python";
        } else {
            System.out.println(NOME + ": Opcao invalida.");
            return;
        }

        // Ler codigo
        System.out.println("\n" + NOME + ": Cole seu codigo. Digite 'FIM' para encerrar:");
        StringBuilder codigo = new StringBuilder();

        while (true) {
            String linha = scanner.nextLine();
            if (linha.equalsIgnoreCase("FIM")) {
                break;
            }
            codigo.append(linha).append("\n");
        }

        if (codigo.toString().trim().isEmpty()) {
            System.out.println(NOME + ": Voce nao digitou nenhum codigo.");
            return;
        }

        // Analisar
        List<String> erros = Analisador.analisar(codigo.toString(), linguagem);

        System.out.println("\n" + NOME + ": Resultado da analise:");
        if (erros.isEmpty()) {
            System.out.println("  Nenhum problema encontrado! Bom trabalho!");
        } else {
            for (String erro : erros) {
                System.out.println("  - " + erro);
            }
        }
    }

    private static void aprenderConceito() {
        System.out.println("\n" + NOME + ": O que voce quer aprender?\n");
        System.out.println("+-------+-------------------+");
        System.out.println("| Opcao | Tema              |");
        System.out.println("+-------+-------------------+");
        System.out.println("|   1   | Variaveis         |");
        System.out.println("|   2   | Condicionais      |");
        System.out.println("|   3   | Loops             |");
        System.out.println("|   4   | Arrays/Listas     |");
        System.out.println("|   5   | Funcoes/Metodos   |");
        System.out.println("+-------+-------------------+");
        System.out.print("\nEscolha: ");

        String opcao = scanner.nextLine().trim();
        Conceitos.explicar(opcao);
    }
}