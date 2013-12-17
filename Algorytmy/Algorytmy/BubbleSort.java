package Algorytmy;

import java.util.Arrays;



public class BubbleSort {
 
    public static void main(String[] args) {
    	long startTime = System.currentTimeMillis();
    	int arrayList[] = { 50 ,67 ,89 ,33 ,73 ,31 ,63 ,69 ,92 ,33 ,19 ,99 ,68 ,17 ,40 ,29 ,1 ,10 ,70 ,32 ,72 ,66 ,98 ,33 ,4 ,50 ,53 ,78 ,39 ,8 ,26 ,90 ,75 ,15 ,24 ,49 ,47 ,87 ,18 ,40 ,21 ,37 ,39 ,89 ,54 ,80 ,18 ,56 ,91 ,89 };
        
    	System.out.println("\nOstateczny wynik:"+Arrays.toString(BubbleSortMethod(arrayList)));
       
    	System.out.println("Sortowanie zakoñczone! Czas dzialania: "+(System.currentTimeMillis() - startTime)+" milisekund.");
    }
 
    public static int[] BubbleSortMethod(int[] arr){
    	long startTime = System.currentTimeMillis();
    	int temp;
        for(int i=0; i < arr.length-1; i++){
 
            for(int j=1; j < arr.length-i; j++){
                if(arr[j-1] > arr[j]){
                    temp=arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                
                }
            }
            System.out.println((i+1)+" sortowanie: "+Arrays.toString(arr));
        
        }
        return arr;
    
    }
}