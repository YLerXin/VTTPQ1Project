package transform;

// Remove stop words(Done)
// Print the list of unique words in alphabetical order(Done)
// Convert the deck of cards to use List
// Process the file. For every category, display the following:
// Category name
// Highest rated app name and rating
// Lowest rated app name and rating
// Average rating for the category
// Find the next word distribution

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class Read{

    public static void main(String[] args) throws IOException{ 
        String fileloc = args[0];
        String stopwordloc = "C:\\Users\\yongl\\VTTP\\Wk1Project\\stopwords.txt";

        FileReader reader = new FileReader(fileloc);
        BufferedReader br = new BufferedReader(reader);
        //Stop word file location
        FileReader swreader = new FileReader(stopwordloc);
        BufferedReader swbr = new BufferedReader(swreader);
        
        //get array of SW
        ArrayList<String> swarray = new ArrayList<String>();
        while(true){
            String stopword = swbr.readLine();
            if (stopword == null){
                break;
            }
            swarray.add(stopword);
            // System.out.println(stopword); just to see if stop words read
            // System.out.println(swarray.size());
        }
        swbr.close();

        Set<String> uniqueWords = new HashSet<>();

        while (true){
            String line = br.readLine();
            if (line == null){
                break;
            }
            //Remove punctuations
            String transformedLine = line.replaceAll("\\p{Punct}", "").toLowerCase().trim();
            // Split line into individual words and create new array to add non stopwords
            String[] words = transformedLine.split("\\s+");
            ArrayList<String> filteredline = new ArrayList<String>();
            for (String word: words){
                if (!swarray.contains(word.toLowerCase())){
                    filteredline.add(word);
                }
            }
            //hashset makes it so unique words are obtained
            for (String word: words){
                uniqueWords.add(word);
            }

            //Cut back together
            String finalLine = String.join(" ",filteredline);
            // System.out.println(finalLine);
        }

        br.close();
        // for (String word: uniqueWords){
        //     System.out.printf("%s, ", word);
        // }

        //for alphabatical sort
        List<String> list = new ArrayList<>(uniqueWords);
        Collections.sort(list);
        System.out.println("Sorted List: " + list);




    }

}
