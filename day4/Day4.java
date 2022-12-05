package day4;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import day4.RangePair;

public class Day4 {
    public static ArrayList<RangePair> pairs;
    public static int containsCount = 0;

    public static void main(String[] args) {
        try {
            readPairs();
        } catch (IOException e) {
            e.printStackTrace();
        }

       
        for(RangePair pair : pairs){
            //System.out.println("lower: " + pair.firstRange.lowerBound + " upper: " + pair.firstRange.upperBound + " - lower: " + pair.secondRange.lowerBound + " upper: " + pair.secondRange.upperBound);
            if(pair.containsAny()){
                containsCount++;
            }
        }

        System.out.println(containsCount);
    }

    private static void readPairs() throws IOException{
        pairs = new ArrayList<>();
        File start = new File("day4/pairs.txt");

        FileReader fr = new FileReader(start);
        BufferedReader br = new BufferedReader(fr);

        ArrayList<String> lines = new ArrayList<>();
        String line;
        System.out.println(
            "Reading text file using FileReader");
        while ((line = br.readLine()) != null) {
            pairs.add(getRangePair(line));
        }
        fr.close();
        br.close();  
    }

    private static RangePair getRangePair(String string){
        Range firstRange = new Range(-1, -1);
        Range secondRange = new Range(-1, -1);

        String[] stringPairs = string.split(",");

        boolean foundFirst = false;
        for(String stringPair : stringPairs){
            String[] sections = stringPair.split("-");

            int lowerBound = Integer.parseInt(sections[0]);
            int upperBound = Integer.parseInt(sections[1]);

            if(!foundFirst){
                firstRange = new Range(lowerBound, upperBound);
                foundFirst = true;
            }else{
                secondRange = new Range(lowerBound, upperBound);
            }
        }
        return new RangePair(firstRange, secondRange);
    }
}
