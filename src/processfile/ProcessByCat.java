package processfile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProcessByCat {
    
    public static void main(String[] args) throws IOException {
        String fileloc = args[0];
        int linecount = 0;

        FileReader reader = new FileReader(fileloc);
        BufferedReader br = new BufferedReader(reader);
        Map<String,Functions> catStats = new HashMap<>();

        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            String[] values = line.split(",");
            //preprocessing
            if (linecount == 0) {
                linecount++;
                continue;
            }

            for (int i = 0; i < values.length; i++) {
                values[i] = values[i].replace(",", "").trim(); // Remove commas and clean spaces
            }
            if (values[2].equalsIgnoreCase("NaN") || values[2].isEmpty()) {
                continue;
            }
            try{
            String appName = values[0];
            String category = values[1];
            float rating = Float.parseFloat(values[2]);

            //putIfAbsent put specified key with value for Hashmap
            catStats.putIfAbsent(category, new Functions());
            Functions stats = catStats.get(category);
            stats.update(appName,rating);
            }catch(NumberFormatException e){
                continue;
            }
        }
        br.close();

        for (Map.Entry<String, Functions> entry : catStats.entrySet()) {
            String category = entry.getKey();
            Functions stats = entry.getValue();

            System.out.printf("Category: %s%n", category);
            System.out.printf("  Highest rated app: %s (%.2f)%n", stats.highApp, stats.highestRating);
            System.out.printf("  Lowest rated app: %s (%.2f)%n", stats.lowApp, stats.lowestRating);
            System.out.printf("  Average rating: %.2f%n%n", stats.getAvgRating());
            } 
        
    
    }
}
