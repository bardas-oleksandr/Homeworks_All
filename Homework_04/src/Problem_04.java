public class Problem_04 implements IWhatToDo {
    public void solve(){
        System.out.println("WORKING WITH RECURSIVE METHOD");
        System.out.print("Set the integer boundary for calculation:");
        int maxValue = IConsole.getInteger();
        System.out.print("Set the count of nesting levels for recursive method:");
        int levels;
        do{
            levels = IConsole.getInteger();
            if(levels < 0){
                System.out.println("Incorrect input. Positive integer is expected.");
                System.out.print("Try again:");
            }
        }while(levels < 0);
        if(maxValue >= 0){
            for(int i = 1; i <= maxValue; i++){
                System.out.println("The sum of numbers from the range 0.." + i + " is equal " + Sum(0, i, levels));
            }
        }else{
            for(int i = 1; i >= maxValue; i--){
                System.out.println("The sum of numbers from the range " + i + "..0 is equal " + Sum(i, 0, levels));
            }
        }
    }

    //Method finds the sum of integer numbers from the range [start..end]
    //Start and end can be either positive or negative
    static int Sum(int start, int end, int levels){
        int result = 0;
        if(levels > 1 && end - start > 0){
            result += Sum(start,(start+end)/2,levels-1);
            result += Sum((start+end)/2+1,end,levels-1);
        }
        else{
            while(start <= end){
                result += start++;
            }
        }
        return result;
    }
}
