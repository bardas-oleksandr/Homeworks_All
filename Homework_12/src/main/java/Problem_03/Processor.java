package Problem_03;

import Problem_01.GenericArray;

public enum Processor {
    ;
    public static <T extends Comparable<T>> T min(GenericArray<T> arr){
        int imin = 0;
        for(int i = 0; i < arr.length(); i++){
            if(arr.get(imin).compareTo(arr.get(i)) > 0 ){
                imin = i;
            }
        }
        return arr.get(imin);
    }

    public static <T extends Comparable<T>> T max(GenericArray<T> arr){
        int imax = 0;
        for(int i = 0; i < arr.length(); i++){
            if(arr.get(imax).compareTo(arr.get(i)) < 0 ){
                imax = i;
            }
        }
        return arr.get(imax);
    }

    public static <T extends Comparable<T>> void sort(GenericArray<T> arr){
        quickSort(arr, 0, arr.length() - 1);
    }

    private static <T extends Comparable<T>> void quickSort(GenericArray<T> arr, int first, int last){
        int i = first, j = last;
        int mid = (first + last)/2;
        do{
            while(arr.get(i).compareTo(arr.get(mid)) < 0){
                i++;
            }
            while(arr.get(j).compareTo(arr.get(mid)) > 0){
                j--;
            }
            if(i <= j){
                T tmp = arr.get(i);
                arr.set(i, arr.get(j)) ;
                arr.set(j, tmp);
                i++;
                j--;
            }
        }while(i <= j);
        if(first < j){
            quickSort(arr, first, j);
        }
        if(i < last){
            quickSort(arr, i, last);
        }
    }

    public static <T extends Number> double sum(GenericArray<T> arr){
        double result = 0;
        for(int i = 0; i < arr.length(); i++){
            result += arr.get(i).doubleValue();
        }
        return result;
    }
}
