
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
    
    public OutputHandler(String outputFileNameText, String outputFileNameBasic, int selection) throws IOException{
            this.outputFileNameText=outputFileNameText;
            this.outputFileNameBasic=outputFileNameBasic;
            this.outText = new PrintWriter(new BufferedWriter(new FileWriter(this.outputFileNameText, true)));
            this.outBasic = new PrintWriter(new BufferedWriter(new FileWriter(this.outputFileNameBasic, true)));
            
            //0=AvancedFeatures
            if(selection==0){
                createOutputFileText();
            }else{
                createOutputFileBasic();
            }
    }
    
    public OutputHandler() throws IOException{

    }
        
    public void createOutputFileText() throws IOException{
        this.outputFileText = new File(outputFileNameText);
        this.outputFileText.createNewFile();
    }
    
    public void createOutputFileBasic() throws IOException{
        this.outputFileBasic = new File(outputFileNameBasic);
        this.outputFileBasic.createNewFile();
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
    
    public String getFileNameFromKeywordsBasic(String keywordsOriginal){
        String[] splitted=keywordsOriginal.split(" ");
        String nameBasic="";
        
        for(int i = 0; i<splitted.length; i++){
            String s=splitted[i].trim();
            nameBasic=nameBasic+s+"_";
        }
        nameBasic=nameBasic+"Basic.txt";
        
        return nameBasic;
    }
    public String getFileNameFromKeywordsText(String keywordsOriginal){
        String[] splitted=keywordsOriginal.split(" ");
        String nameText="";
        
        for(int i = 0; i<splitted.length; i++){
            String s=splitted[i].trim(); 
            nameText=nameText+s+"_";
        }
        nameText=nameText+"Advance.txt";
        return nameText;
    }
}
