package br.com.fiap.twoespwx.libunclepresser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.Random;

public class Compressor {
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


    // referencia : https://www.dca.fee.unicamp.br/~martino/disciplinas/ea978/na4.pdf
    // Método que realiza a compressão usando Run-Length Encoding (RLE)
    public static String compressRLE(String data) {
        StringBuilder compressed = new StringBuilder();

        int length = data.length();
        for (int i = 0; i < length; i++) {
            int count = 1;
            while (i + 1 < length && data.charAt(i) == data.charAt(i + 1)) {
                count++;
                i++;
            }
            compressed.append(data.charAt(i));
            // Adiciona o número de repetições apenas se for maior que 1
            if (count > 1) {
                compressed.append(count);
            }
        }

        return compressed.toString();
    }

    public static int[] CountFrequency(String entrada) {
        int freqA = 0;
        int freqG = 0;
        int freqT = 0;
        int freqC = 0;
        
        

        String[] entradaSplitada = entrada.split("");

        for (int i = 0; i < entradaSplitada.length; i++) {
            String caracter = entradaSplitada[i];

            if (caracter.equals("A")) {
                freqA++;
            }

            if (caracter.equals("G")) {
                freqC++;
            }

            if (caracter.equals("T")) {
                freqT++;
            }

            if (caracter.equals("C")) {
                freqG++;
            }
        }

        // Retorna as frequências como um array de inteiros
        return new int[]{freqA, freqC, freqT, freqG};
    }

    // referencia: https://www.devmedia.com.br/amp/leitura-e-escrita-de-arquivos-de-texto-em-java/25529
    // Método para leitura usando InputStreamReader e BufferedReader
    public static String InputReader(String path) throws IOException {
        BufferedReader buffRead = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = buffRead.readLine()) != null) {
            sb.append(line);
        }
        buffRead.close();
        return sb.toString();
    }

    // Método para escrita usando OutputStreamWriter e BufferedWriter
    public static void OutputWriter(String path, String content) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8));
        buffWrite.write(content);
        buffWrite.close();
    }

    // Método para exibir um resumo da compressão
    public static void displaySummary(String inputFile, String outputFile, String originalData, String compressedData) {
        System.out.println(" -----------------------------------------------------------");
        System.out.println("|           LIB UNCLE PRESSER - GRUPO ABOBRINHA             |");
        System.out.println("|-----------------------------------------------------------|");
        System.out.printf("| INPUT  FILENAME: %s                                        |\n", inputFile);
        System.out.printf("| OUTPUT FILENAME: %s                                        |\n", outputFile);
        System.out.println("|                                                           |");
        System.out.println(" -----------------------------------------------------------");
        System.out.printf("| SEQUÊNCIA GERADA: %s                                       |\n", originalData);
        System.out.printf("| SEQUÊNCIA COMPRESSADA: %s                                  |\n", compressedData);
        System.out.printf("| INPUT FILE SIZE: %d KB                                     |\n", originalData.length() / 1024);
        System.out.printf("| TOTAL INPUT CHARACTERS: %d                                 |\n", originalData.length());
        System.out.println("|                                                           |");

        // Chamada ao método de contagem de frequências
        int[] frequencias = CountFrequency(originalData);

        // Exibindo as frequências calculadas
        System.out.printf("| A: %d (%.2f%%)                                              |\n", frequencias[0], (frequencias[0] * 100.0 / originalData.length()));
        System.out.printf("| C: %d (%.2f%%)                                              |\n", frequencias[1], (frequencias[1] * 100.0 / originalData.length()));
        System.out.printf("| T: %d (%.2f%%)                                              |\n", frequencias[2], (frequencias[2] * 100.0 / originalData.length()));
        System.out.printf("| G: %d (%.2f%%)                                              |\n", frequencias[3], (frequencias[3] * 100.0 / originalData.length()));
        System.out.println("|                                                           |");

        double compressionRate = (compressedData.length() * 1.0) / originalData.length();
        System.out.printf("| COMPRESSION RATE: ~%.2f%%                                   |\n", (1 - compressionRate) * 100);
        System.out.printf("| OUTPUT FILE SIZE: %d BYTES                                  |\n", compressedData.length());
        System.out.println("|                                                           |");
        System.out.println(" -----------------------------------------------------------");
        System.out.println("| SCORE: WELL-DONE                                          |");
        System.out.println(" -----------------------------------------------------------");
    }
}    

