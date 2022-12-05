package day4;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import day4.RangePair;

public class Day4 {
    public static ArrayList<RangePair> pairs;

    public static void main(String[] args) {
        
    }

    private static void readPairs() throws IOException{
        pairs = new ArrayList<>();
        File start = new File("pairs.txt");

        FileReader fr = new FileReader(start);
        BufferedReader br = new BufferedReader(fr);

        ArrayList<String> lines = new ArrayList<>();
        String line;
        System.out.println(
            "Reading text file using FileReader");
        while ((line = br.readLine()) != null) {
            System.out.println(line);
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
            }else{
                secondRange = new Range(lowerBound, upperBound);
            }
        }

        return new RangePair(firstRange, secondRange);

        
        
    }
}
