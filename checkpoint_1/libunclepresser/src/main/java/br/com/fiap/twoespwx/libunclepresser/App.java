package br.com.fiap.twoespwx.libunclepresser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) {
        System.out.println("LIB UNCLE PRESSER - GRUPO ABOBRINHA");

        // Caminhos fixos para entrada e saída
        String inputFilePath = "checkpoint_1\\libunclepresser\\inputs\\input1.txt";
        String outputFilePath = "checkpoint_1\\libunclepresser\\outputs\\OUTPUT1.TXT";

        try {
            // Ler o arquivo de entrada
            String nucleotides = new String(Files.readAllBytes(Paths.get(inputFilePath)), StandardCharsets.UTF_8);

            // Remove quebras de linha e espaços em branco, se houver
            nucleotides = nucleotides.replaceAll("\\s", "");

            // Compressão usando o algoritmo RLE
            String compressedData = compressRLE(nucleotides);

            // Escrever a saída no arquivo de saída
            Files.write(Paths.get(outputFilePath), compressedData.getBytes(StandardCharsets.UTF_8));

            // Exibir informações sobre o processo
            displaySummary(inputFilePath, outputFilePath, nucleotides, compressedData);

        } catch (IOException e) {
            System.out.println("Error reading or writing files: " + e.getMessage());
        }
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
            compressed.append(data.charAt(i)).append(count);
        }

        return compressed.toString();
    }

    // Método para exibir um resumo da compressão
    public static void displaySummary(String inputFile, String outputFile, String originalData, String compressedData) {
        System.out.println(" -----------------------------------------------------------");
        System.out.println("|           LIB UNCLE PRESSER - GRUPO ABOBRINHA             |");
        System.out.println("|-----------------------------------------------------------|");
        System.out.printf("| INPUT  FILENAME: %-45s |\n", inputFile);
        System.out.printf("| OUTPUT FILENAME: %-45s |\n", outputFile);
        System.out.println("|                                                           |");
        System.out.println(" -----------------------------------------------------------");
        System.out.printf("| INPUT FILE SIZE: %-43d KB |\n", originalData.length() / 1024);
        System.out.printf("| TOTAL INPUT CHARACTERS: %-37d |\n", originalData.length());
        System.out.println("|                                                           |");

        // Contagem de frequências de caracteres
        long countA = originalData.chars().filter(ch -> ch == 'A').count();
        long countC = originalData.chars().filter(ch -> ch == 'C').count();
        long countT = originalData.chars().filter(ch -> ch == 'T').count();
        long countG = originalData.chars().filter(ch -> ch == 'G').count();

        System.out.printf("| A: %-12d (%6.2f%%)                               |\n", countA,
                (countA * 100.0 / originalData.length()));
        System.out.printf("| C: %-12d (%6.2f%%)                               |\n", countC,
                (countC * 100.0 / originalData.length()));
        System.out.printf("| T: %-12d (%6.2f%%)                               |\n", countT,
                (countT * 100.0 / originalData.length()));
        System.out.printf("| G: %-12d (%6.2f%%)                               |\n", countG,
                (countG * 100.0 / originalData.length()));
        System.out.println("|                                                           |");

        double compressionRate = (compressedData.length() * 1.0) / originalData.length();
        System.out.printf("| COMPRESSION RATE: ~%.2f%%                                   |\n",
                (1 - compressionRate) * 100);
        System.out.printf("| OUTPUT FILE SIZE: %-43d BYTES |\n", compressedData.length());
        System.out.println("|                                                           |");
        System.out.println(" -----------------------------------------------------------");
        System.out.println("| SCORE: WELL-DONE                                          |");
        System.out.println(" -----------------------------------------------------------");
    }
}
