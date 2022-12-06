package day6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day6 {
    public static String signal;
    public static void main(String[] args) {
        
        try {
            readSignal();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int markerIndex = findMarker(0, new ArrayList<String>());
        System.out.println(markerIndex);

        System.out.println(signal.charAt(1812));
        System.out.println(signal.charAt(1813));
        System.out.println(signal.charAt(1814));
        System.out.println(signal.charAt(1815));

        
    }

    private static void readSignal() throws IOException{
        File start = new File("day6/signal.txt");

        FileReader fr = new FileReader(start);
        BufferedReader br = new BufferedReader(fr);

        ArrayList<String> lines = new ArrayList<>();
        String line;
        System.out.println(
            "Reading text file using FileReader");
        while ((line = br.readLine()) != null) {
            //System.out.println(line);
            lines.add(line);
        }
        fr.close();
        br.close();

        signal = lines.get(0);
    }


    private static int findMarker(int start, ArrayList<String> sequence){
        String currentChar = "" + signal.charAt(start);
        sequence.add(currentChar);
        if(sequence.size() <= 14){
            return findMarker(start+1, sequence);
        }
        sequence.remove(0);
        if(isUnique(sequence)){
            return start;
        }else{
            return findMarker(start+1, sequence);
        }
    }






    private static boolean isUnique(ArrayList<String> set){
        boolean isUnique = true;
        for(int i = 0; i < set.size(); i++){
            for(int j = 0; j < set.size(); j++){
                if(j != i){
                    if(set.get(i).equals(set.get(j))){
                        isUnique = false;
                    }
                }
            }
        }
        return isUnique;
    }
    
}
