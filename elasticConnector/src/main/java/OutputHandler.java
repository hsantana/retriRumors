
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
    
    public File outputFileText;
    public File outputFileBasic;
    public PrintWriter outText;
    public PrintWriter outBasic;
    public String outputFileNameText;
    public String outputFileNameBasic;
    
    public OutputHandler() throws IOException{
            this.outputFileNameText="tweetsTextV1.txt";
            this.outputFileNameBasic="tweetsBasicV1.txt";
            this.outText = new PrintWriter(new BufferedWriter(new FileWriter(this.outputFileNameText, true)));
            this.outBasic = new PrintWriter(new BufferedWriter(new FileWriter(this.outputFileNameBasic, true)));
            
            createOutputFileText();
            createOutputFileBasic();
    }
        
    public void createOutputFileText() throws IOException{
        this.outputFileText = new File(outputFileNameText);
        
        if(! this.outputFileText.exists()){
            this.outputFileText.createNewFile();
            System.out.println("Creating new file for output");
        }
    }
    
    public void createOutputFileBasic() throws IOException{
        this.outputFileBasic = new File(outputFileNameBasic);
        
        if(! this.outputFileBasic.exists()){
            this.outputFileBasic.createNewFile();

            System.out.println("Creating new file for output");
        }
    }
    
    public void writeOutputFileText(String line){
        outText.println(line);
    }
    
    public void writeOutputFileBasic(String line){
        outBasic.println(line);
    }
    
    public void closeOutputHanlder(){
        outText.close();
        outBasic.close();
    }
}
