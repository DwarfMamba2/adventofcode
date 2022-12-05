package day4;
import day4.Range;

public class RangePair {
    public Range firstRange;
    public Range secondRange;

    public RangePair(Range firstRange, Range secondRange){
        this.firstRange = firstRange;
        this.secondRange = secondRange;
    }

    public boolean fullyContains(){
        if(containsOther(firstRange, secondRange) || containsOther(secondRange, firstRange)){
            return true;
        }else{
            return false;
        }

    }

    private boolean containsOther(Range container, Range containee){
        if(container.lowerBound <= containee.lowerBound && container.upperBound >= containee.upperBound){
            return true;
        }else{
            return false;
        }
    }

    public boolean containsAny(){
        if(containsAnyOther(firstRange, secondRange) || containsAnyOther(secondRange, firstRange)){
            return true;
        }else{
            return false;
        }
        
    }
    
    private boolean containsAnyOther(Range lower, Range upper){
        if(lower.upperBound >= upper.lowerBound && lower.lowerBound <= upper.upperBound){
            return true;
        }else{
            return false;
        }
    }
}
