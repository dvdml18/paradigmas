package ProjetoParadigmas;
import java.util.ArrayList;
import java.util.Scanner;

public class JogoForca {
    private String palavraOriginal;
    private ArrayList<Character> letrasDescobertas;
    private ArrayList<Character> letrasTentadas;
    private int tentativasRestantes;
    private boolean jogoEncerrado;

    public JogoForca() {
        letrasTentadas = new ArrayList<>();
        tentativasRestantes = 6;
        jogoEncerrado = false;
    }

    public void executar() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Digite a palavra para o jogo: ");
        String palavra = scanner.nextLine();
        
        iniciarJogo(palavra);
        
        while (!jogoEncerrado) {
            System.out.print("Digite uma letra: ");
            String input = scanner.nextLine();
            
            if (!input.isEmpty()) {
                tentarLetra(input.charAt(0));
            }
        }
        
        scanner.close();
    }

    private void iniciarJogo(String palavra) {
        palavraOriginal = palavra.toLowerCase();
        letrasDescobertas = new ArrayList<>();
        
        for (int i = 0; i < palavraOriginal.length(); i++) {
            letrasDescobertas.add('_');
        }
        
        limparTela();
        System.out.println("Jogo da Forca iniciado!");
        exibirSituacao();
    }

    private void tentarLetra(char letra) {
        letra = Character.toLowerCase(letra);

        if (letrasTentadas.contains(letra)) {
            System.out.println("Letra já tentada!");
            return;
        }

        letrasTentadas.add(letra);
        boolean acertou = false;

        for (int i = 0; i < palavraOriginal.length(); i++) {
            if (palavraOriginal.charAt(i) == letra) {
                letrasDescobertas.set(i, letra);
                acertou = true;
            }
        }

        if (!acertou) {
            tentativasRestantes--;
            System.out.println("Letra errada! Tentativas restantes: " + tentativasRestantes);
        }

        exibirSituacao();
        verificarFimDeJogo();
    }

    private void exibirSituacao() {
        System.out.print("\nPalavra: ");
        for (char c : letrasDescobertas) {
            System.out.print(c + " ");
        }
        
        System.out.println("\nLetras tentadas: " + letrasTentadas);
        System.out.println("Tentativas restantes: " + tentativasRestantes);
        System.out.println();
    }

    private void verificarFimDeJogo() {
        if (!letrasDescobertas.contains('_')) {
            System.out.println("Parabéns! Você acertou a palavra!");
            jogoEncerrado = true;
        } else if (tentativasRestantes <= 0) {
            System.out.println("Fim de jogo! A palavra era: " + palavraOriginal);
            jogoEncerrado = true;
        }
    }

    private void limparTela() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}

