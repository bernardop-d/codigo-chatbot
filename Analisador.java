import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsavel por analisar codigo Java e Python
 * Detecta erros comuns e da dicas de como corrigir
 */
public class Analisador {

    /**
     * Analisa o codigo e retorna lista de erros encontrados
     */
    public static List<String> analisar(String codigo, String linguagem) {
        List<String> erros = new ArrayList<>();

        // Verificacoes comuns
        verificarBalanceamento(codigo, '(', ')', "Parenteses", erros);
        verificarBalanceamento(codigo, '[', ']', "Colchetes", erros);

        // Verificacoes especificas
        if (linguagem.equals("java")) {
            analisarJava(codigo, erros);
        } else {
            analisarPython(codigo, erros);
        }

        return erros;
    }

    /**
     * Verifica se caracteres de abertura e fechamento estao balanceados
     */
    private static void verificarBalanceamento(String codigo, char abre, char fecha, 
                                                String nome, List<String> erros) {
        int contador = 0;

        for (char c : codigo.toCharArray()) {
            if (c == abre) {
                contador++;
            } else if (c == fecha) {
                contador--;
            }
        }

        if (contador > 0) {
            erros.add(nome + ": falta fechar " + contador + " '" + fecha + "'");
        } else if (contador < 0) {
            erros.add(nome + ": " + Math.abs(contador) + " '" + fecha + "' sobrando");
        }
    }

    /**
     * Verificacoes especificas para Java
     */
    private static void analisarJava(String codigo, List<String> erros) {
        // Chaves
        verificarBalanceamento(codigo, '{', '}', "Chaves", erros);

        // System.out errado
        if (codigo.contains("system.out") || codigo.contains("System.Out")) {
            erros.add("System.out.println esta escrito errado (Java diferencia maiusculas)");
        }

        // Falta de ponto e virgula em linhas comuns
        String[] linhas = codigo.split("\n");
        for (int i = 0; i < linhas.length; i++) {
            String linha = linhas[i].trim();
            
            if (deveTerPontoVirgula(linha) && !linha.endsWith(";")) {
                erros.add("Linha " + (i + 1) + ": pode estar faltando ';'");
            }
        }

        // main sem args
        if (codigo.contains("public static void main") && 
            !codigo.contains("String[] args") && !codigo.contains("String args[]")) {
            erros.add("Metodo main deve receber String[] args");
        }

        // Comparacao de String com ==
        if (codigo.contains("== \"") || codigo.contains("==\"")) {
            erros.add("Use .equals() para comparar Strings, nao ==");
        }
    }

    /**
     * Verifica se uma linha Java deveria terminar com ponto e virgula
     */
    private static boolean deveTerPontoVirgula(String linha) {
        if (linha.isEmpty()) return false;
        if (linha.startsWith("//")) return false;
        if (linha.startsWith("import ")) return true;
        if (linha.endsWith("{") || linha.endsWith("}")) return false;
        if (linha.startsWith("if") || linha.startsWith("for") || 
            linha.startsWith("while") || linha.startsWith("else")) return false;
        if (linha.startsWith("public") || linha.startsWith("private") || 
            linha.startsWith("class")) return false;
        
        // Linhas com atribuicao ou chamadas de metodo
        if (linha.contains("=") || linha.contains("System.out") || 
            linha.startsWith("return ")) {
            return true;
        }
        
        return false;
    }

    /**
     * Verificacoes especificas para Python
     */
    private static void analisarPython(String codigo, List<String> erros) {
        // Ponto e virgula desnecessario
        if (codigo.contains(";")) {
            erros.add("Python nao precisa de ponto e virgula");
        }

        // Chaves ao inves de indentacao
        if (codigo.contains("{") || codigo.contains("}")) {
            erros.add("Python usa indentacao, nao chaves {}");
        }

        // print sem parenteses (Python 2)
        String[] linhas = codigo.split("\n");
        for (int i = 0; i < linhas.length; i++) {
            String linha = linhas[i].trim();
            
            if (linha.startsWith("print ") && !linha.startsWith("print(")) {
                erros.add("Linha " + (i + 1) + ": Em Python 3, use print() com parenteses");
            }
        }

        // Booleanos errados
        if (codigo.contains("true") || codigo.contains("false")) {
            erros.add("Em Python, booleanos sao True e False (com maiuscula)");
        }

        // Operadores errados
        if (codigo.contains("&&")) {
            erros.add("Em Python, use 'and' ao inves de '&&'");
        }
        if (codigo.contains("||")) {
            erros.add("Em Python, use 'or' ao inves de '||'");
        }

        // else if ao inves de elif
        if (codigo.contains("else if")) {
            erros.add("Em Python, use 'elif' ao inves de 'else if'");
        }

        // Falta de dois pontos apos if/for/while/def
        for (int i = 0; i < linhas.length; i++) {
            String linha = linhas[i].trim();
            
            if ((linha.startsWith("if ") || linha.startsWith("for ") || 
                 linha.startsWith("while ") || linha.startsWith("def ") ||
                 linha.startsWith("elif ") || linha.equals("else")) 
                && !linha.endsWith(":")) {
                erros.add("Linha " + (i + 1) + ": Falta ':' no final");
            }
        }
    }
}