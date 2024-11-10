package br.com.fiap.twoespwx.libunclepresser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.Random;

public class Compressor {
    // declaração de atributos para calculo de frequencia
    public static int freqA;
    public static int freqC;
    public static int freqG;
    public static int freqT;

    // gera a sequeência de nucleotídeo de entrada
    public static String generate() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.print("Digite a quantidade de nucleotídeos que deseja gerar: ");
        int quantity = scanner.nextInt();
        
        char[] sequence = new char[quantity];
        char[] nucleotides = {'A', 'C', 'G', 'T'};
        
        sequence[0] = nucleotides[random.nextInt(nucleotides.length)];
        
        //definindo uma probabilidade de repetir o nucleotídeo anterior, para tornar mais parecido com as sequências reais e as usadas de exemplo
        double repeatProbability = 0.7;  

        for (int i = 1; i < quantity; i++) {
            if (random.nextDouble() < repeatProbability) {
                sequence[i] = sequence[i - 1];
            } else {
                sequence[i] = nucleotides[random.nextInt(nucleotides.length)];
            }
        }

        System.out.print("Sequência de nucleotídeos gerada: ");
        for (char nucleo : sequence) {
            System.out.print(nucleo);
        }
        System.out.println();

        scanner.close();
        return new String(sequence);
    }

    // método que faz a compressão rle
    // referencia https://www.dca.fee.unicamp.br/~martino/disciplinas/ea978/na4.pdf
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
            if (count > 1) {
                compressed.append(count);
            }
        }

        return compressed.toString();
    }

    // método para contar a frequencia dos caracteres da sequencia
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

    //método de para ler uma entrada usando buffread
    // referencia: https://www.devmedia.com.br/amp/leitura-e-escrita-de-arquivos-de-texto-em-java/25529
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

    // método de escrita de saída usando buffwrite
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

        countFrequency(originalData);

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


}
