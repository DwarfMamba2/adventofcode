package day8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import day8.TreeHouse;

public class Day8 {

    private static Integer forest[][];

    private static Boolean visible[][];

    private static Integer scenicScores[][];

    private static Integer maxScore = 0;

    public static void main(String[] args) {
        try {
            fillForest();
        } catch (Exception e) {
            e.printStackTrace();
        }

        visible  = new Boolean[forest.length][forest[0].length];

        for(int i = 0; i < forest.length; i++){
            Arrays.fill(visible[i], false);            
        }

        //printForest();

        fillVisible();
        invertForest();
        invertVisible();
        fillVisible();
        invertForest();
        invertVisible();

        //System.out.println("");
        //printVisible();

        int count = 0;
        
        for(int i = 0; i < visible.length; i++){
            for(int j = 0; j < visible[0].length; j++){
                if(visible[i][j]){
                    count++;
                }
            }
        }

        System.out.println(count);

        fillScores();

        System.out.println(maxScore);
        
    }

    private static void fillScores(){
        scenicScores = new Integer[forest.length][forest[0].length];

        for(int i = 0; i < forest.length; i++){
            for(int j = 0; j < forest[i].length; j++){
                //up
                ArrayList<Integer> up = new ArrayList<>();
                for(int k = i-1; k >= 0; k--){
                    up.add(forest[k][j]);                    
                }

                //left
                ArrayList<Integer> left = new ArrayList<>();
                for(int k = j-1; k >= 0; k--){
                    left.add(forest[i][k]);                    
                }

                //down
                ArrayList<Integer> down = new ArrayList<>();
                for(int k = i+1; k < forest.length; k++){
                    down.add(forest[k][j]);                    
                }

                //right
                ArrayList<Integer> right = new ArrayList<>();
                for(int k = j+1; k < forest[i].length; k++){
                    right.add(forest[i][k]);                   
                }
                
                TreeHouse treeHouse = new TreeHouse(forest[i][j], up, left, down, right);

                Integer score = treeHouse.scenicScore();

                if(score > maxScore){
                    maxScore = score;
                }

                scenicScores[i][j] = score;
            }
        }
    }

    private static void invertForest(){
        for(int i = 0; i < forest.length; i++){
            List<Integer> lineList = Arrays.asList(forest[i]);
            Collections.reverse(lineList);
            forest[i] = lineList.toArray(new Integer[forest.length]);
        }

        List<Integer[]> rowList = Arrays.asList(forest);
        Collections.reverse(rowList);
        forest = rowList.toArray(new Integer[forest.length][forest[0].length]);
    }

    private static void invertVisible(){
        for(int i = 0; i < visible.length; i++){
            List<Boolean> lineList = Arrays.asList(visible[i]);
            Collections.reverse(lineList);
            visible[i] = lineList.toArray(new Boolean[visible.length]);
        }

        List<Boolean[]> rowList = Arrays.asList(visible);
        Collections.reverse(rowList);
        visible = rowList.toArray(new Boolean[visible.length][visible[0].length]);
    }

    private static void fillVisible(){
        int height = forest.length;
        int width = forest[0].length;

        int[] maxFromTop = new int[width];
        int[] maxFromLeft = new int[height];

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){

                if(j == 0 || j == width-1 || i == 0 || i == height-1){
                    visible[i][j] = true;
                }

                int treeHeight = forest[i][j];


                if(treeHeight > maxFromTop[j] || treeHeight > maxFromLeft[i]){
                    //System.out.println("tree height: " + treeHeight + " left " + maxFromLeft[i] + " top " + maxFromTop[j]);
                    visible[i][j] = true;  
                }

                if(treeHeight > maxFromLeft[i]){
                    maxFromLeft[i] = treeHeight;
                }

                if(treeHeight > maxFromTop[j]){
                    maxFromTop[j] = treeHeight;
                }
            }
        }
    }



    private static void fillForest() throws IOException{
        File forestFile = new File("day8/forest.txt");

        FileReader fr = new FileReader(forestFile);
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
             
        fillForest(lines);
    }

    private static void fillForest(ArrayList<String> input){
        forest = new Integer[input.size()][getLength(input)];

        for(int i = 0; i < input.size(); i++){
            String line = input.get(i);
            for(int j = 0; j < line.length(); j++){
                forest[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));               
            }
        }
    }


    private static int getLength(ArrayList<String> strings){
        int maxLength = 0;
        for(String string : strings){
            int newLength = string.length();
            if(newLength > maxLength){
                maxLength = newLength;
            } 
        }
        return maxLength;
    }

    private static void printVisible(){
        for(int i = 0; i < visible.length; i++){
            String line = "";
            for(int j = 0; j < visible[0].length; j++){
                String sign = "0";
                if(visible[i][j]){
                    sign = "1";
                }
                line = line + sign;
            }
            System.out.println(line);
        }
    }

    private static void printForest(){
        for(int i = 0; i < forest.length; i++){
            String line = "";
            for(int j = 0; j < forest[0].length; j++){
                line = line + forest[i][j];
            }
            System.out.println(line);
        }
    }
}
