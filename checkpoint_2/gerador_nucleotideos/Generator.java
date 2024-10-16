import java.util.Random;
import java.util.Scanner;

public class Generator {
    public static String gera_sequencia() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.print("Digite a quantidade de nucleotídeos que deseja gerar: ");
        int quantidade = scanner.nextInt();
        
        char[] sequencia = new char[quantidade];
        char[] nucleotideos = {'A', 'C', 'G', 'T'};
        
        // Primeiro nucleotídeo é escolhido aleatoriamente
        sequencia[0] = nucleotideos[random.nextInt(nucleotideos.length)];
        
        // Definindo uma probabilidade de repetir o nucleotídeo anterior
        double probabilidadeDeRepetir = 0.7;  // 70% de chance de repetir o nucleotídeo anterior

        for (int i = 1; i < quantidade; i++) {
            if (random.nextDouble() < probabilidadeDeRepetir) {
                // Repetir o nucleotídeo anterior
                sequencia[i] = sequencia[i - 1];
            } else {
                // Escolher um novo nucleotídeo aleatório
                sequencia[i] = nucleotideos[random.nextInt(nucleotideos.length)];
            }
        }

        // Exibir a sequência gerada (apenas para debug)
        System.out.print("Sequência de nucleotídeos gerada: ");
        for (char nucleo : sequencia) {
            System.out.print(nucleo);
        }
        System.out.println(); // Nova linha após a sequência gerada

        scanner.close();
        return new String(sequencia);
    }

}