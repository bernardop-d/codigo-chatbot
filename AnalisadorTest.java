import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Testes unitários da classe Analisador.
 * Verifica se a detecção de erros em Java e Python funciona corretamente.
 */
public class AnalisadorTest {

    // =============================================
    // Testes gerais
    // =============================================

    @Test
    public void testCodigoCorretoJavaSemErros() {
        // Arrange: código Java sem erros óbvios
        String codigo = "System.out.println(\"Ola\");";

        // Act: analisa como Java
        List<String> erros = Analisador.analisar(codigo, "java");

        // Assert: não deve encontrar erros
        assertTrue(erros.isEmpty(), "Codigo correto nao deve gerar erros");
    }

    @Test
    public void testCodigoVazioNaoGeraErros() {
        // Arrange: string vazia
        String codigo = "";

        // Act: analisa como Java
        List<String> erros = Analisador.analisar(codigo, "java");

        // Assert: sem erros para código vazio
        assertTrue(erros.isEmpty(), "Codigo vazio nao deve gerar erros");
    }

    // =============================================
    // Testes para Java
    // =============================================

    @Test
    public void testDetectaSystemOutErrado() {
        // Arrange: system.out com letra minúscula (errado em Java)
        String codigo = "system.out.println(\"ola\");";

        // Act
        List<String> erros = Analisador.analisar(codigo, "java");

        // Assert: deve apontar o erro de maiúsculas
        assertTrue(
            erros.stream().anyMatch(e -> e.contains("System.out")),
            "Deve detectar system.out em minuscula"
        );
    }

    @Test
    public void testDetectaMainSemArgs() {
        // Arrange: método main sem String[] args
        String codigo = "public static void main() {}";

        // Act
        List<String> erros = Analisador.analisar(codigo, "java");

        // Assert: deve pedir String[] args
        assertTrue(
            erros.stream().anyMatch(e -> e.contains("String[] args")),
            "Deve detectar main sem parametros"
        );
    }

    @Test
    public void testDetectaComparacaoStringComIgual() {
        // Arrange: comparando String com == (errado em Java)
        String codigo = "if (nome == \"Ana\") {}";

        // Act
        List<String> erros = Analisador.analisar(codigo, "java");

        // Assert: deve sugerir usar .equals()
        assertTrue(
            erros.stream().anyMatch(e -> e.contains(".equals()")),
            "Deve detectar comparacao de String com =="
        );
    }

    @Test
    public void testDetectaParentesesDesbalanceados() {
        // Arrange: abre parêntese sem fechar
        String codigo = "System.out.println(\"Ola\";";

        // Act
        List<String> erros = Analisador.analisar(codigo, "java");

        // Assert: deve detectar parêntese não fechado
        assertTrue(
            erros.stream().anyMatch(e -> e.contains("Parenteses")),
            "Deve detectar parentese aberto sem fechar"
        );
    }

    // =============================================
    // Testes para Python
    // =============================================

    @Test
    public void testDetectaPontoVirgulaPython() {
        // Arrange: código Python com ponto e vírgula (desnecessário em Python)
        String codigo = "print(\"Ola\");";

        // Act
        List<String> erros = Analisador.analisar(codigo, "python");

        // Assert: deve avisar sobre o ponto e vírgula
        assertTrue(
            erros.stream().anyMatch(e -> e.contains("ponto e virgula")),
            "Deve detectar ponto e virgula em Python"
        );
    }

    @Test
    public void testDetectaChavesPython() {
        // Arrange: código Python usando chaves (sintaxe de Java, não Python)
        String codigo = "if True { print(\"oi\") }";

        // Act
        List<String> erros = Analisador.analisar(codigo, "python");

        // Assert: deve avisar para usar indentação
        assertTrue(
            erros.stream().anyMatch(e -> e.contains("indentacao")),
            "Deve detectar uso de chaves em Python"
        );
    }

    @Test
    public void testDetectaBooleanoErradoPython() {
        // Arrange: true em minúsculo (Java), mas em Python deve ser True
        String codigo = "ativo = true";

        // Act
        List<String> erros = Analisador.analisar(codigo, "python");

        // Assert: deve avisar sobre o booleano errado
        assertTrue(
            erros.stream().anyMatch(e -> e.contains("True") || e.contains("booleanos")),
            "Deve detectar booleano em minusculo no Python"
        );
    }

    @Test
    public void testDetectaElseIfPython() {
        // Arrange: else if em vez de elif (sintaxe de Java)
        String codigo = "if x > 0:\n    pass\nelse if x < 0:\n    pass";

        // Act
        List<String> erros = Analisador.analisar(codigo, "python");

        // Assert: deve sugerir usar elif
        assertTrue(
            erros.stream().anyMatch(e -> e.contains("elif")),
            "Deve detectar 'else if' e sugerir 'elif'"
        );
    }
}
