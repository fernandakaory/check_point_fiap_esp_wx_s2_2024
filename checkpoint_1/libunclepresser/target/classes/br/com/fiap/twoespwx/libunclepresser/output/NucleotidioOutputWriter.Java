package br.com.fiap.twoespx.libuncleotidio.output;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

public class NucletidioOutputWriter {
    
    public void writeFromFileOutputStream(String filename, String output) {

        try {
            OutputStream os = new FileOutputStream(filename);
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter writer = new BufferedWriter(osw);
            writer.write(output);
            writer.close();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            System.out.println("Arquivo não encontrado: " + fnfe);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Linha mal formatada: " + ioe);
        }
    }

    public void writeWithPrintStream(String filename, String output) {
        try {
            PrintStream out = new PrintStream(filename);
            out.println(output);
            out.close();
        } catch (FileNotFoundException fnfe) {
            
            fnfe.printStackTrace();
            System.out.println("Arquivo não encontrado: " + fnfe);
        }
    }
}
