package day8;

import java.util.ArrayList;

public class TreeHouse {
    int height;

    ArrayList<Integer[]> directions = new ArrayList<>();


    public TreeHouse(int height, ArrayList<Integer> up, ArrayList<Integer> left, ArrayList<Integer> down, ArrayList<Integer> right){
        this.height = height;

        directions.add(up.toArray(new Integer[0]));
        directions.add(left.toArray(new Integer[0]));
        directions.add(down.toArray(new Integer[0]));
        directions.add(right.toArray(new Integer[0]));
    }

    public int scenicScore(){
        for(Integer[] direction : directions){
            if(direction.length == 0){
                return 0;
            }
        }

        int score = 1;

        for(Integer[] direction : directions){            
            int count = 0;
            for(int i = 0; i < direction.length; i++){
                if(direction[i] >= height){
                    count++;
                    break;
                }else{
                    count++;
                }
            }
            score = score*count;
        }
        return score;
    }
    
}
