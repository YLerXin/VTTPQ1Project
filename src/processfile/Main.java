package processfile;

import java.io.IOException;

public class Main {
        public static void main(String[] args) throws IOException {
        String fileloc = args[0]; // Specify the path to the text file
        String wordchoice = args[1];

        Nextword predictor = new Nextword();
        predictor.process(fileloc); // Process the text file

        // Example predictions:
        System.out.println("Next word prediction for "+wordchoice+": " + predictor.predictNextWord(wordchoice));
    }
}
//input txt file and the word to be predicted
