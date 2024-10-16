package br.com.fiap.twoespwx.libunclepresser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.Random;

public class Compressor {

    // Método para gerar uma sequência aleatória de nucleotídeos
    public static String generateSequence() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.print("Digite a quantidade de nucleotídeos que deseja gerar: ");
        int quantity = scanner.nextInt();
        
        char[] sequence = new char[quantity];
        char[] nucleotides = {'A', 'C', 'G', 'T'};
        
        // Primeiro nucleotídeo é escolhido aleatoriamente
        sequence[0] = nucleotides[random.nextInt(nucleotides.length)];
        
        // Definindo uma probabilidade de repetir o nucleotídeo anterior
        double repeatProbability = 0.7;  // 70% de chance de repetir o nucleotídeo anterior

        for (int i = 1; i < quantity; i++) {
            if (random.nextDouble() < repeatProbability) {
                // Repetir o nucleotídeo anterior
                sequence[i] = sequence[i - 1];
            } else {
                // Escolher um novo nucleotídeo aleatório
                sequence[i] = nucleotides[random.nextInt(nucleotides.length)];
            }
        }

        // Exibir a sequência gerada (apenas para debug)
        System.out.print("Sequência de nucleotídeos gerada: ");
        for (char nucleo : sequence) {
            System.out.print(nucleo);
        }
        System.out.println(); // Nova linha após a sequência gerada

        scanner.close();
        return new String(sequence);
    }

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

    // Método para contar a frequência de nucleotídeos
    public static void countFrequency(String input) {
        freqA = 0;
        freqC = 0;
        freqG = 0;
        freqT = 0;

        String[] inputSplit = input.split("");

        for (int i = 0; i < inputSplit.length; i++) {
            String nucleotide = inputSplit[i];

            if (nucleotide.equals("A")) {
                freqA++;
            }

            if (nucleotide.equals("C")) {
                freqC++;
            }

            if (nucleotide.equals("G")) {
                freqG++;
            }

            if (nucleotide.equals("T")) {
                freqT++;
            }
        }
    }

    // Método para leitura usando InputStreamReader e BufferedReader
    public static String inputReader(String path) throws IOException {
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
    public static void outputWriter(String path, String content) throws IOException {
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
        countFrequency(originalData);

        // Exibindo as frequências calculadas
        System.out.printf("| A: %d (%.2f%%)                                              |\n", freqA, (freqA * 100.0 / originalData.length()));
        System.out.printf("| C: %d (%.2f%%)                                              |\n", freqC, (freqC * 100.0 / originalData.length()));
        System.out.printf("| T: %d (%.2f%%)                                              |\n", freqT, (freqT * 100.0 / originalData.length()));
        System.out.printf("| G: %d (%.2f%%)                                              |\n", freqG, (freqG * 100.0 / originalData.length()));
        System.out.println("|                                                           |");

        double compressionRate = (compressedData.length() * 1.0) / originalData.length();
        System.out.printf("| COMPRESSION RATE: ~%.2f%%                                   |\n", (1 - compressionRate) * 100);
        System.out.printf("| OUTPUT FILE SIZE: %d BYTES                                  |\n", compressedData.length());
        System.out.println("|                                                           |");
        System.out.println(" -----------------------------------------------------------");
        System.out.println("| SCORE: WELL-DONE                                          |");
        System.out.println(" -----------------------------------------------------------");
    }

    // Atributos para contagem de nucleotídeos
    public static int freqA;
    public static int freqC;
    public static int freqG;
    public static int freqT;
}
