import java.util.Random;

/**
 * Classe com explicacoes de conceitos basicos de programacao
 * Mostra exemplos em Java e Python lado a lado
 */
public class Conceitos {

    private static final String NOME = "Prof. Anna";
    private static Random random = new Random();

    private static final String[] DICAS = {
        "Use nomes descritivos: 'idade' e melhor que 'x'.",
        "Teste seu codigo com valores extremos: 0, negativos, vazios.",
        "Divida problemas grandes em partes menores.",
        "Leia a mensagem de erro com calma, ela ajuda!",
        "Comente o 'por que', nao o 'o que'. O codigo ja mostra o que faz.",
        "Um metodo deve fazer apenas uma coisa.",
        "Indentacao correta deixa o codigo mais facil de ler.",
        "Antes de otimizar, faca funcionar.",
        "Se esta copiando codigo, talvez precise de uma funcao.",
        "Pratique todo dia, nem que seja 15 minutos."
    };

    /**
     * Mostra uma dica aleatoria de programacao
     */
    public static void mostrarDica() {
        String dica = DICAS[random.nextInt(DICAS.length)];
        System.out.println("\n" + NOME + " (Dica): " + dica);
    }

    /**
     * Explica um conceito baseado na opcao escolhida
     */
    public static void explicar(String opcao) {
        switch (opcao) {
            case "1":
                explicarVariaveis();
                break;
            case "2":
                explicarCondicionais();
                break;
            case "3":
                explicarLoops();
                break;
            case "4":
                explicarArrays();
                break;
            case "5":
                explicarFuncoes();
                break;
            default:
                System.out.println(NOME + ": Opcao invalida.");
        }
    }

    private static void explicarVariaveis() {
        System.out.println("\n=== VARIAVEIS ===");
        System.out.println("Variaveis guardam dados na memoria do computador.");
        System.out.println("Cada variavel tem um nome e um tipo.\n");

        System.out.println("JAVA:");
        System.out.println("  int idade = 25;");
        System.out.println("  String nome = \"Maria\";");
        System.out.println("  double preco = 19.99;");
        System.out.println("  boolean ativo = true;\n");

        System.out.println("PYTHON:");
        System.out.println("  idade = 25");
        System.out.println("  nome = \"Maria\"");
        System.out.println("  preco = 19.99");
        System.out.println("  ativo = True");
    }

    private static void explicarCondicionais() {
        System.out.println("\n=== CONDICIONAIS (if/else) ===");
        System.out.println("Permitem executar codigo baseado em condicoes.\n");

        System.out.println("JAVA:");
        System.out.println("  if (nota >= 7) {");
        System.out.println("      System.out.println(\"Aprovado\");");
        System.out.println("  } else {");
        System.out.println("      System.out.println(\"Reprovado\");");
        System.out.println("  }\n");

        System.out.println("PYTHON:");
        System.out.println("  if nota >= 7:");
        System.out.println("      print(\"Aprovado\")");
        System.out.println("  else:");
        System.out.println("      print(\"Reprovado\")");
    }

    private static void explicarLoops() {
        System.out.println("\n=== LOOPS (for/while) ===");
        System.out.println("Repetem um bloco de codigo varias vezes.\n");

        System.out.println("JAVA (for):");
        System.out.println("  for (int i = 0; i < 5; i++) {");
        System.out.println("      System.out.println(i);");
        System.out.println("  }\n");

        System.out.println("PYTHON (for):");
        System.out.println("  for i in range(5):");
        System.out.println("      print(i)\n");

        System.out.println("JAVA (while):");
        System.out.println("  while (x > 0) {");
        System.out.println("      x--;");
        System.out.println("  }\n");

        System.out.println("PYTHON (while):");
        System.out.println("  while x > 0:");
        System.out.println("      x -= 1");
    }

    private static void explicarArrays() {
        System.out.println("\n=== ARRAYS / LISTAS ===");
        System.out.println("Guardam varios valores em uma unica variavel.\n");

        System.out.println("JAVA:");
        System.out.println("  int[] numeros = {1, 2, 3, 4, 5};");
        System.out.println("  System.out.println(numeros[0]);  // Imprime 1");
        System.out.println("  System.out.println(numeros.length);  // Tamanho: 5\n");

        System.out.println("PYTHON:");
        System.out.println("  numeros = [1, 2, 3, 4, 5]");
        System.out.println("  print(numeros[0])  # Imprime 1");
        System.out.println("  print(len(numeros))  # Tamanho: 5");
    }

    private static void explicarFuncoes() {
        System.out.println("\n=== FUNCOES / METODOS ===");
        System.out.println("Blocos de codigo reutilizaveis com nome.\n");

        System.out.println("JAVA:");
        System.out.println("  public static int somar(int a, int b) {");
        System.out.println("      return a + b;");
        System.out.println("  }");
        System.out.println("  // Uso: int resultado = somar(3, 5);\n");

        System.out.println("PYTHON:");
        System.out.println("  def somar(a, b):");
        System.out.println("      return a + b");
        System.out.println("  # Uso: resultado = somar(3, 5)");
    }
}