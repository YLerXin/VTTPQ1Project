package processfile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Nextword{

    private Map<String, Map<String, Integer>> wordMap = new HashMap<>();
    String previousWord = null;


    public void process(String filePath) throws IOException{

        FileReader reader = new FileReader(filePath);
        BufferedReader br = new BufferedReader(reader);

        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            StringTokenizer tokenizer = new StringTokenizer(line);
            while (tokenizer.hasMoreTokens()){
                String currentWord = tokenizer.nextToken().toLowerCase();
                if (previousWord != null){
                    wordMap.putIfAbsent(previousWord, new HashMap<>());
                    Map<String,Integer> nextWordMap = wordMap.get(previousWord);
                    //put word into map<name,count>
                    nextWordMap.put(currentWord, nextWordMap.getOrDefault(currentWord, 0) + 1);
                }
                previousWord = currentWord;

            }

        }
        br.close();

    }

    public String predictNextWord(String currentWord){
        currentWord = currentWord.toLowerCase();
        Map<String,Integer> nextWords = wordMap.get(currentWord);
        if (nextWords == null || nextWords.isEmpty()) {
            return "No prediction available.";
        }
        String predictedWord = null;
        int maxCount = 0;

        for (Map.Entry<String,Integer> entry:nextWords.entrySet()){
            if (entry.getValue() > maxCount){
                predictedWord= entry.getKey();
                maxCount = entry.getValue();
            }
        }
        return predictedWord;


    }

}




