package Algorytmy;

import java.io.*;
import javax.swing.JOptionPane;
public class Main {

	private static int tab[];
	private static int ile_liczb;
	
		public static int readInt()
		{
			String line = null;
		    int val = 0;
		    try {
		      BufferedReader is = new BufferedReader(
		        new InputStreamReader(System.in));
		      line = is.readLine();
		      val = Integer.parseInt(line);
		    } catch (NumberFormatException ex) {
		      System.err.println("niepoprawny wybor: " + line);
		    } catch (IOException e) {
		      System.err.println("Unexpected IO ERROR: " + e);
		    }
		    return val;
		}
	
        public static void main(String[] args) {
		 
		int i;

		System.out.println("Ile liczb chesz posortowac?\n");
		ile_liczb = readInt();
               
		System.out.println("\n Podaj liczby:");
		tab = new int[ile_liczb];
		for(i=0; i<ile_liczb; i++) {
			tab[i] = readInt();
		}
       

		System.out.println("Tablica przed posortowaniem:");
		for(i=0; i<ile_liczb; i++)
			System.out.println(tab[i]);
		babel(tab,ile_liczb);
		System.out.println("Tablica po posortowaniu:");
		for(i=0; i<ile_liczb; i++)
			System.out.println(tab[i]);
		
	}
        
	private static void babel(int tab[], int ile_liczb) {
		int temp,i,zmiana;
		do {
			zmiana=0;
			i=ile_liczb-1;
			do {
				i--;
				if (tab[i+1]< tab[i]) {
					temp=tab[i];	         
					tab[i]=tab[i+1];
					tab[i+1]=temp;
					zmiana=1;
				}
			} while (i!=0);
	   } while (zmiana!=0); 
	}
}
