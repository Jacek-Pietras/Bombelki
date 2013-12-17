package Huffman;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class FileHandler {

        private FileHandler(){}
      
        public static String readFile(String url, boolean withLineWrap){
                String result = "";
                try {
                        FileReader fr = new FileReader(url);
                        BufferedReader br = new BufferedReader(fr);
                        String temp = null;
                      
                        if ( withLineWrap ) {
                                while ( ( temp=br.readLine() ) !=null ){
                                        result += (temp+"\n");
                                }
                        }
                        else {
                                while ( ( temp=br.readLine() ) !=null ){
                                        result += temp;
                                }
                        }
                      
                } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                return result;
        }

      
        public static void writeFile(String url, String text, boolean withLineWrap){
                try {
                        FileWriter fw = new FileWriter(url);
                        BufferedWriter bw = new BufferedWriter(fw);
                        char[] str = text.toCharArray();
                      
                        for ( int i=0; i<str.length; i++ ) {
                                if ( withLineWrap && i%100==0 && i!=0 )
                                        bw.append('\n');
                                bw.append(str[i]);
                        }
                        bw.close();
                        fw.close();
                      
                       // System.out.println("Writing file completed!");
                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        }

     
      
}