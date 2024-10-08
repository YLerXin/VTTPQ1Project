package processfile;
// Process the file. For every category, display the following:
// Category name(done)
// Highest rated app name and rating (done)
// Lowest rated app name and rating(done)
// Average rating for the category(done)
// Find the next word distribution
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Process {
    

    public static void main(String[] args) throws IOException{ 
        String fileloc = args[0];
        //int linecount = 0;
        //ArrayList<String> categories = new ArrayList<>();

        FileReader reader = new FileReader(fileloc);
        BufferedReader br = new BufferedReader(reader);

        //String[] headers = null;
        Float maxval = null;
        Float minval = null;
        String maxappname = null;
        String minappname = null;

        while (true) {
        String line = br.readLine();
        if (line == null) {
            break;
        }

        // For header
        // if (linecount == 0) {
        //     headers = line.split(",");
        //     linecount += 1;
        //     continue;
        // }
        //linecount += 1;

        String[] values = line.split(","); // Check app column
        // Clean up comma issues
        for (int i = 0; i < values.length; i++) {
            values[i] = values[i].replace(",", "").trim(); // Remove commas and trim whitespace
        }
        if (values[2].equalsIgnoreCase("NaN") || values[2].isEmpty()) {
            continue; // Skip this line
        }

        try {
            float value = Float.parseFloat(values[2]);
            if (maxappname == null && minappname == null) {
                maxval = value;
                minval = value;
                maxappname = values[0];
                minappname = values[0];
            }
            if (value > maxval) {
                maxval = value;
                maxappname = values[0];
            }
            if (value < minval) {
                minval = value;
                minappname = values[0];
            }
        } catch (NumberFormatException e) {
            continue; // Skip to the next line if parsing fails
        }
        }
        
        br.close();
        //print header
        // if (headers!= null){
        //     for (int i = 0; i < headers.length;i++){
        //         System.out.println("Header " + (i+1) + ": " + headers[i]);
        //     }
        // }
        System.out.printf("%s is the highest rated app with a score of %.2f%n",maxappname,maxval);
        System.out.printf("%s is the lowest rated app with a score of %.2f%n",minappname,minval);

    }
}
