package br.com.fiap.twoespwx.libunclepresser;

import java.io.*;

public class App {
    public static void main(String[] args) {
        System.out.println("LIB UNCLE PRESSER - GRUPO ABOBRINHA");

        // Gerar e salvar a sequência de nucleotídeos
        String nucleotides = Compressor.gera_sequencia();
        String inputFilePath = "checkpoint_1\\libunclepresser\\inputs\\input1.txt";
        String outputFilePath = "checkpoint_1\\libunclepresser\\outputs\\OUTPUT1.TXT";

        try {
            // Escrever a sequência de nucleotídeos no arquivo de entrada
            Compressor.OutputWriter(inputFilePath, nucleotides);

            // Ler o arquivo de entrada usando BufferedReader
            nucleotides = Compressor.InputReader(inputFilePath);

            // Compressão usando o algoritmo RLE
            String compressedData = Compressor.compressRLE(nucleotides);

            // Escrever a saída no arquivo de saída
            Compressor.OutputWriter(outputFilePath, compressedData);

            // Exibir informações sobre o processo
            Compressor.displaySummary(inputFilePath, outputFilePath, nucleotides, compressedData);

        } catch (IOException e) {
            System.out.println("Erro ao na leitura ou escrita do seguinte arquivo: " + e.getMessage());
        }
    }
}