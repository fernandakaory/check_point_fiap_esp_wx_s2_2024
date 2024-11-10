/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Grupo: Abobrinha
 * Autores: 
 *      - David Denunci (RM98603)     -   rm98603@fiap.com.br
 *      - Fernanda Saito (RM551105)     -   rm551104@fiap.com.br
 *      - Lucas de Toledo (RM97913)   -   rm97913@fiap.com.br
 *      - Pedro Gava (RM551043)   -   rm551043@fiap.com.br
 * 
 * 
 */

package br.com.fiap.twoespwx.libunclepresser;

import java.io.*;

public class App {
    public static void main(String[] args) {
        System.out.println("LIB UNCLE PRESSER - GRUPO ABOBRINHA");

        // gera e salva a sequencia de nucleotideos
        // definição dos caminhos dos txts de entrada e saída
        String nucleotides = Compressor.generate();
        String inputFilePath = "checkpoint_1\\libunclepresser\\inputs\\input1.txt";
        String outputFilePath = "checkpoint_1\\libunclepresser\\outputs\\OUTPUT1.TXT";

        // tratamento de erros obrigatório para casos de erro nos arquivos de entrada ou saída
        try {
            // chama o método de escrita da saída para inserir a sequencia gerada no arquivo de input
            Compressor.outputWriter(inputFilePath, nucleotides);

            // nucleotideo agora recebe a sequencia de entrada 
            nucleotides = Compressor.inputReader(inputFilePath);

            // atribuição dos dados compressados a uma variável
            String compressedData = Compressor.compressRLE(nucleotides);

            // insere a saída no arquivo
            Compressor.outputWriter(outputFilePath, compressedData);
            
            // exibe o resumo
            Compressor.displaySummary(inputFilePath, outputFilePath, nucleotides, compressedData);

        } catch (IOException e) {
            System.out.println("Erro ao na leitura ou escrita do seguinte arquivo: " + e.getMessage());
        }
    }
}