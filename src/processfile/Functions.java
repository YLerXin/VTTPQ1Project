package processfile;

public class Functions {
    float totalRating = 0;
    int numberofapps = 0;


    String highApp;
    String lowApp;
    float highestRating = -100;
    float lowestRating = 100;

    public void update(String name,float rating){
        if (rating > highestRating){
            highestRating = rating;
            highApp = name;
        }
        else if (rating < lowestRating){
            lowestRating = rating;
            lowApp = name;
        }
        totalRating += rating;
        numberofapps += 1;
    }

    public float getAvgRating(){
        if (numberofapps > 0){
        return totalRating/numberofapps;
        }else{
            return 0;
        }
    }



}
