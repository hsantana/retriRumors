
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hugo
 */
public class SmartDownloaderStarter {
    public static void main(String[] args) throws IOException{
        String outputFileName="tweetsV2-Hugo.txt";
        String apiKey="X5QaYPl89YnwR9qjB2ro5mN4H";
        String apiSecret="xy0zCGHjhzyG9Tl96lD9CT1GtZuhKi9RSItIOZp8420KkDusZ7";
        String tokenValue="34494124-wZpEvlhYgFrISqCvq5ZiX8VLbPAQk386fX88uT2Ln";
        String tokenSecret="UUeshZc0CRsr0jDD1OSFqDJ2piVaiREaiQkueDJR7lIvQ";
        String filePath="/Users/Hugo/NetBeansProjects/smartDownloader/TwitterData";
        String topics="bombs, New York";
        int sleepTimeTweet=20000;
        int sleepTimeRequest=300000;
        final SmartDownloader smartDownloader = new SmartDownloader(outputFileName, apiKey, apiSecret, tokenValue, tokenSecret, filePath, topics);
        smartDownloader.start();
    }
}
