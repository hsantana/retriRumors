
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hugo
 */
public class OutputHandler {
    
    public File outputFile;
    public PrintWriter out;
    public String outputFileName;
    
    public OutputHandler() throws IOException{
            this.outputFileName="tweetsTextV1.txt";
            this.out = new PrintWriter(new BufferedWriter(new FileWriter(this.outputFileName, true)));
            
            createOutputFile();
    }
        
    public void createOutputFile() throws IOException{
        this.outputFile = new File(outputFileName);
        
        if(! this.outputFile.exists()){
            this.outputFile.createNewFile();
            System.out.println("Creating new file for output");
        }
    }
    
    public void writeOutputFile(String line){
        out.println(line);
    }
    
    public void closeOutputHanlder(){
        out.close();
    }
}
