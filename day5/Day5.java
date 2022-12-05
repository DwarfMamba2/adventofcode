package day5;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class Day5 {
    static ArrayList<ArrayList<String>> stacks;
    static ArrayList<Move> moves;
    public static void main(String[] args) {

        try {
            readStart();
            readMoves();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //printStacks();
        //printMoves();

        for(Move move : moves){
            moveMultipleCrates(move.origin-1, move.destination-1, move.quantity);
        }

        printStacks();

        for(ArrayList<String> stack : stacks){
            System.out.print(stack.get(0));
        }

    }

    private static void moveMultipleCrates(int originStackIndex, int destinationStackIndex, int quantity){
        ArrayList<String> originStack = stacks.get(originStackIndex);
        ArrayList<String> destinationStack = stacks.get(destinationStackIndex);

        ArrayList<String> pile = new ArrayList<>();

        for(int i = 0; i < quantity; i++){
            pile.add(originStack.get(0));
            originStack.remove(0);                    
        }

        Collections.reverse(destinationStack);

        for(int i = 0; i < quantity; i++){
            destinationStack.add(pile.get(pile.size()-1));
            pile.remove(pile.size()-1);                    
        }
        Collections.reverse(destinationStack);

        
        
    }

    private static boolean moveCrates(int originStackIndex, int destinationStackIndex, int quantity){
        ArrayList<String> originStack = stacks.get(originStackIndex);
        ArrayList<String> destinationStack = stacks.get(destinationStackIndex);

        for(int i = 0; i < quantity; i++){
            if(!moveCrate(originStack, destinationStack)){
                return false;
            }        
        }
        return true;
    }

    private static boolean moveCrate(ArrayList<String> originStack, ArrayList<String> destinationStack){
        if(originStack.size() >= 1){
            String crate = originStack.get(0);
            originStack.remove(crate);
            
            Collections.reverse(destinationStack);
            destinationStack.add(crate);
            Collections.reverse(destinationStack);
            return true;
        }else{
            return false;
        }
    }


    private static void readStart() throws IOException{
        stacks = new ArrayList<>();
        File start = new File("start.txt");

        FileReader fr = new FileReader(start);
        BufferedReader br = new BufferedReader(fr);

        ArrayList<String> lines = new ArrayList<>();
        String line;
        System.out.println(
            "Reading text file using FileReader");
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            lines.add(line);
        }
        fr.close();
        br.close();
        while(getLength(lines) > 0){ 
            if(isLetterLine(lines)){
                stacks.add(getStack(lines));
            }

            for(int i = 0; i < lines.size(); i++){
                String string = lines.get(i);
                //System.out.println("i is: " + i + " string is: " + string);
                String subString = string.substring(1);
                //System.out.println("i is: " + i + " substring is: " + subString);
                lines.set(i, subString);                
            }
        }   
    }

    public static void readMoves() throws IOException{
        moves = new ArrayList<>();

        File start = new File("moves.txt");

        FileReader fr = new FileReader(start);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {
            moves.add(getMove(line));
        }
        fr.close();
        br.close();


    }

    public static Move getMove(String string){
        ArrayList<Integer> digits = new ArrayList<>();
        
        char[] charArray = string.toCharArray();
        for(int i = 0; i < charArray.length; i++){
            boolean hasSibling = false;
            if(Character.isDigit(charArray[i])){
                if(i<charArray.length-1){
                    if(Character.isDigit(charArray[i+1])){
                        char first = charArray[i];
                        char second = charArray[i+1];

                        String concat = "" + first + second;

                        digits.add(Integer.parseInt(concat));
                        i++;
                        hasSibling = true;
                    }
                }
                
                if(!hasSibling){
                    digits.add(Character.getNumericValue(charArray[i]));
                }
            }
        }
        return(new Move(digits.get(0), digits.get(1), digits.get(2)));
    }




    private static boolean isLetterLine(ArrayList<String> strings){
        boolean isLetterLine = false;
        for(String string : strings){
            char nextChar = string.charAt(0);
            if(Character.isLetter(nextChar)){
                isLetterLine = true;
            }
            
        }
        return isLetterLine;
    }

    private static ArrayList<String> getStack(ArrayList<String> lines){
        ArrayList<String> stack = new ArrayList<>();

        for(String string : lines){
            char nextChar = string.charAt(0);
            if(Character.isLetter(nextChar)){
                stack.add(String.valueOf(nextChar));
            } 
        }
        return stack;
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

    private static void printStacks(){
        for(ArrayList<String> stack : stacks){
            System.out.println(stack.toString());
        }
    }

    private static void printMoves(){
        for(Move move : moves){
            System.out.println("move " + move.quantity + " from " + move.origin + " to " + move.destination);
        }
    }
}
