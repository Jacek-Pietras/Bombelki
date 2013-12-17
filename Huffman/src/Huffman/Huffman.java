package Huffman;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;



public class Huffman extends JFrame{
        Node nNode;
        Node root;
        char[] codeStr;
        ArrayList<Character> alreadyExist;
        ArrayList<Node> nodeList;
        String tempCode = "";
        JTextArea text1;
        JTextArea text2;
        JTextArea text3;
        static JTextArea text4;
        JLabel a;
        JLabel b;
       
        static String line1="";
        static String line2="";
        static String line3="";
       
        Boolean Enco=false;
        Boolean start=true;
        JButton Encode;
        JButton Decode;
       
        public Huffman(){
            super("HUFFMAN CODING");
            setLayout(new FlowLayout());
           
            text1=new JTextArea(5,30);
            text2=new JTextArea(5,30);
            text3=new JTextArea(5,30);
            text4=new JTextArea(5,30);
            a=new JLabel("Enter Text for Encode");
            b=new JLabel("Enter Text for Decode");
           
            Encode= new JButton("Encode");
            Decode= new JButton("Decode");
            text3.setEditable(false);
            text4.setEditable(false);
           
            //scroll = new JScrollPane(display);
            //scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

           
                add(a);
               
                add(text1);
                add(new JScrollPane(text1));
                add(b);
                add(text2);
                add(new JScrollPane(text2));
                add(Encode);
                add(Decode);
                add(text3);
                add(new JScrollPane(text3));
                add(text4);
                add(new JScrollPane(text4));
               
                           
            Encode.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae2) {
                    //System.out.print("s");
                    try{
                    String s = text1.getText();
                    File f = new File("C:\\Users\\Dom\\workspace\\Huffman\\encode.txt");
                    FileWriter fw = new FileWriter(f);
                    fw.write(s);
                    fw.close();
                   
                    String text = FileHandler.readFile("C:\\Users\\Dom\\workspace\\Huffman\\encode.txt", true);
                    Huffman ah = new Huffman( text.toCharArray() );
                    ArrayList<String> codeStr = ah.encode();
                    FileHandler.writeFile("C:\\Users\\Dom\\workspace\\Huffman\\decode.txt", catStr(codeStr), true);
                  
                    calCompRate(text, codeStr);
                   
                    FileReader fr = new FileReader("C:\\Users\\Dom\\workspace\\Huffman\\decode.txt");
                    BufferedReader myInput = new BufferedReader(fr);

                    String st;
                    StringBuffer b = new StringBuffer();
                    while ((s = myInput.readLine()) != null) {
                    b.append(s);
                    b.append("\n");
                    }

                    text3.setText(b.toString());
                    text4.setText(String.format("%s\n%s\n%s\n", line1,line2,line3));
                    }
                    catch(IOException e) {
                        System.out.println("Error in writing file");
                    }
                   
                   
                      
                   }
            });
            Decode.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae2) {
                    try{
                        String s = text2.getText();
                        File f = new File("C:\\Users\\Dom\\workspace\\Huffman\\decode1.txt");
                        FileWriter fw = new FileWriter(f);
                        fw.write(s);
                        fw.close();
                       
                         String codeStr = FileHandler.readFile("C:\\Users\\Dom\\workspace\\Huffman\\decode1.txt", false);
                         Huffman ah = new Huffman( codeStr.toCharArray() );
                         String result = ah.decode();
                         FileHandler.writeFile("C:\\Users\\Dom\\workspace\\Huffman\\encode1.txt", result, false);
                        
                         FileReader fr = new FileReader("C:\\Users\\Dom\\workspace\\Huffman\\encode1.txt");
                         BufferedReader myInput = new BufferedReader(fr);

                         String st;
                         StringBuffer b = new StringBuffer();
                         while ((s = myInput.readLine()) != null) {
                         b.append(s);
                         b.append("\n");
                         }

                         text3.setText(b.toString());
                         text4.setText("successfully converted!!");
                        
                    }
                    catch(IOException e) {
                        System.out.println("Error in writing file");
                    }
                   
                   
                }});
           
           
           
            }
public Huffman(char[] codeStr){
                this.codeStr = codeStr;
                alreadyExist = new ArrayList<Character>();
                nodeList = new ArrayList<Node>();
                nNode = new Node("NEW", 0);
                nNode.parent = null;
                root = nNode;
                nodeList.add(nNode);
        }
public ArrayList<String> encode(){
                ArrayList<String> result = new ArrayList<String>();
                result.add("0");//Represent NEW
                char temp = 0;
                for ( int i=0; i<codeStr.length; i++ ) {
                        temp = codeStr[i];
                        result.add(getCode(temp));
                        updateTree(temp);
                }
                return result;
        }
public String decode(){
                String result = "";
                String symbol = null;
                char temp = 0;
                Node p = root;
                symbol = getByAsc(0);
                result += symbol;
                updateTree( symbol.charAt(0) );
                p = root;
              
                for ( int i=9; i<codeStr.length; i++ ) {
                        temp = codeStr[i];
                      
                        if ( temp=='0' ){
                                p = p.left;
                        }
                        else
                                p = p.right;
                      
                        symbol = visit(p);
                        if ( symbol!=null ){
                                if ( symbol=="NEW" ){
                                        symbol = getByAsc(i);
                                        i+=8;
                                }
                                result+=symbol;
                                updateTree( symbol.charAt(0) );
                                p = root;
                        }
                }
              
                return result;
        }
 void updateTree(char c){
                Node toBeAdd = null;
                if ( !isAlreadyExist(c) ){
                        Node innerNode = new Node(null, 1);
                        Node newNode = new Node(String.valueOf(c), 1);
                        innerNode.left = nNode;
                        innerNode.right = newNode;
                        innerNode.parent = nNode.parent;
                        if ( nNode.parent!=null )
                                nNode.parent.left = innerNode;
                        else {
                                root = innerNode;
                        }
                        nNode.parent = innerNode;
                        newNode.parent = innerNode;
                        nodeList.add(1, innerNode);
                        nodeList.add(1, newNode);
                        alreadyExist.add(c);
                        toBeAdd = innerNode.parent;
                } else {
                        toBeAdd = findNode(c);
                }
                while ( toBeAdd!=null ) {
                        Node bigNode = findBigNode(toBeAdd.frequency);
                        if ( toBeAdd!=bigNode && toBeAdd.parent!=bigNode && bigNode.parent!=toBeAdd)
                                swapNode(toBeAdd, bigNode );
                        toBeAdd.frequency++;
                        toBeAdd = toBeAdd.parent;
                }
        }

boolean isAlreadyExist(char temp) {
                for ( int i=0; i<alreadyExist.size(); i++ ) {
                        if ( temp==alreadyExist.get(i) )
                                return true;
                }
                return false;
        }
      
String getByAsc(int index) {
                int asc = 0;
                int tempInt = 0;
                for ( int i=7; i>=0; i-- ) {
                        tempInt = codeStr[++index] - 48;
                        asc += tempInt * Math.pow(2, i);
                }
              
                char ret = (char) asc;
                return String.valueOf(ret);
        }
 String visit(Node p) {
                if ( p.letter!=null ){
                        return p.letter;
                }
                return null;
        }
 String getCode(char c){
                tempCode = "";
              
                getCodeByTree(root, String.valueOf(c), "");
                String result = tempCode;
                if ( result=="" ) {
                        getCodeByTree(root, "NEW", "");
                        result = tempCode;
                        result += toBinary( getAscii(c) );
                }
                return result;
        }
 Node findNode(char c) {
                String temp = String.valueOf(c);
                Node tempNode = null;
                for ( int i=0; i<nodeList.size(); i++ ) {
                        tempNode = nodeList.get(i);
                        if ( tempNode.letter!=null && tempNode.letter.equals(temp) )
                                return tempNode;
                }
                return null;
        }
 void swapNode(Node n1, Node n2) {
                int i1 = nodeList.indexOf(n1);
                int i2 = nodeList.indexOf(n2);
                nodeList.remove(n1);
                nodeList.remove(n2);
                nodeList.add( i1, n2);
                nodeList.add( i2, n1);
                Node p1 = n1.parent;
                Node p2 = n2.parent;
                if ( p1!=p2 ) {
                        if ( p1.left==n1 ) {
                                p1.left = n2;
                        } else {
                                p1.right = n2;
                        }

                        if ( p2.left==n2 ) {
                                p2.left = n1;
                        } else {
                                p2.right = n1;
                        }
                } else {
                        p1.left = n2;
                        p1.right = n1;
                }
                n1.parent = p2;
                n2.parent = p1;
      
        }
 Node findBigNode(int frequency) {
                Node temp = null;
                for ( int i=nodeList.size()-1; i>=0; i--) {
                        temp = nodeList.get(i);
                        if ( temp.frequency==frequency )
                                break;
                }
                return temp;
        }
 void getCodeByTree(Node node, String letter, String code) {
                if ( node.left==null && node.right==null ) {
                        if ( node.letter!=null && node.letter.equals(letter) )
                                tempCode = code;
                } else {
                        if ( node.left!=null ) {
                                getCodeByTree(node.left, letter, code + "0");
                        }
                        if ( node.right!=null ) {
                                getCodeByTree(node.right, letter, code + "1");
                        }
                }
        }

public static int getAscii(char c){
                return (int)c;
        }

public static String toBinary(int decimal){
                String result = "";
                for ( int i=0; i<8; i++ ) {
                        if ( decimal%2==0 )
                                result = "0" + result;
                        else
                                result = "1" + result;
                        decimal /= 2;
                }
                return result;
        }
 public static double calCompRate(String text, ArrayList<String> codeStr){
                double compRate = 0;
                double preNum = 8*text.length();
                double postNum = 0;
                for ( String s: codeStr) {
                        postNum += s.length();
                }
              
                compRate = preNum/postNum;
                line1=String.format("If we use ASCII codeStr, there are in total "+preNum + " bits");
                line2=String.format("In huffman coding, there are in total "+postNum + " bits");
                line3=String.format("The compress rate is: " + compRate);
               
               
                return compRate;
        }
      
public static void displayList(ArrayList<String>  l){
                for ( int i=0; i<l.size(); i++ ) {
                        System.out.println( l.get(i) );
                }
        }
      
 static String catStr(ArrayList<String> l) {
                String result = "";
                for ( String s: l ){
                        result += s;
                }
                return result;
        }

        public static void main(String[] args) {
                // TODO Auto-generated method stub
            Huffman GUI= new Huffman();
                GUI.setSize(400,500);
                GUI.setVisible(true);
                GUI.setDefaultCloseOperation(EXIT_ON_CLOSE);
                
        }
}
