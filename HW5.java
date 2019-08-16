import java.io.*;
import java.util.*;


public class HW5
{
    public static PriorityQueue<Node> queue;
    public static HashMap<Character,String> charToCode = new HashMap<Character, String>();  
        //charToCode: For translation table.
    public static HashMap<Character,String> temp = new HashMap<Character,String>();
    public static HashMap<Character,Integer> dictionary = new HashMap<Character, Integer>();

    public static Node huffmanCoding(int n)
    {
        for(int i =0;i<n-1;i++){
            Node node = new Node();
            node.left = queue.poll();
            node.right = queue.poll();
            node.frequency = node.right.frequency+node.left.frequency;
            queue.add(node);
        }
        return queue.poll();
    }
    
    public static String encode(String message)
    {
        
        for(int i=0; i<message.length();i++)
        {
            char temp = message.charAt(i);
            if(dictionary.containsKey(temp))
            dictionary.put(temp, dictionary.get(temp)+1);
            else
            dictionary.put(temp,1);
        }
        
        queue = new PriorityQueue<Node>();
        
        int number = 0;
        
        for(Character c:dictionary.keySet())
        {
            Node temp = new Node();
            temp.value = c;
            temp.frequency = dictionary.get(c);
            queue.add(temp);
            number++;
        }
        
        Node root = huffmanCoding(number);
        
        traversal(root, new String());
        
        //System.out.println(charToCode);
        
        
                
        String result = new String();
        for(int i = 0; i < message.length(); i++)
        {
            result = result + charToCode.get(message.charAt(i));
        }
        // System.out.println(result); //print encoded message
        
        String finalResult = new String(); //store traslation table
        for(Character c: charToCode.keySet())
        {
            finalResult += c + "," + charToCode.get(c) + "; ";
        }
        finalResult += "\n" + result;
        
        // System.out.println("This is final result");
        // System.out.println(finalResult); //print translation table and encoded message
        
        String decoded = decode(result); //Test for decode method
        System.out.println(decoded); //Test for decode method
        //finalResult += "\n"+ decoded; // to test whether decoding goes properly into the file
        
        return finalResult;
    }
    
    public static String decode(String message)
    {
        // Since decode method can only be used after encode method has been called, 
        // I used hashtable created from encode method to decode the String message.
        
        String decodedStr = "";
        for (int i = 0; i<message.length(); i++)
        {
            for(int j = i; j<message.length();j++)
            {
                String test = message.substring(i,j+1);                
                if(charToCode.containsValue(test))
                {                    
                    for(Character c: charToCode.keySet())
                    {
                        if(charToCode.get(c).equals(test))
                        {
                           decodedStr += c;
                           //System.out.println(decodedStr);
                           break;
                        }
                     }
                    //decodedStr += test.getKey(charToCode);
                    i = j+1;
                }
            }
        }
        return decodedStr;
        //System.out.println(decodedStr);
    }
    
    
    

    public static void traversal(Node n,String s){
        if(n==null)
        return;
        traversal(n.left,s+"0");
        traversal(n.right,s+"1");
        if(n.value != '\0'){
            //System.out.println(n.value+": "+s);
            charToCode.put(n.value,s);
        }       
        
    }
    
    //public static String encode(String message) throws Exception
    //{
     //   return Huffman.encode(message);
    //}
    //public static String decode(String message) throws Exception
    //{
    //    return Huffman.decode(message);
    //}
    
    public static void main(String args[]) throws Exception
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("enter your option: 1-> encode 2->decode");
        int option = Integer.parseInt(in.readLine());
        if( option == 1)
        {
            System.out.println("enter your option: 1-> read file 2-> enter your message");
            option = Integer.parseInt(in.readLine());
            String message = "";
            if(option == 1)
            {
                System.out.println("input file name:");
                String archivo_entrada = in.readLine();
                BufferedReader text = new BufferedReader(new FileReader(archivo_entrada));
                String line = text.readLine();
                while( line != null)
                {
                    message = message + line + '\n';
                    line = text.readLine();
                }
                text.close();
            }
            else if(option == 2)
            {
                System.out.println("enter your text:");
                message = in.readLine();
            }
            System.out.println("output file name:");
            String fileOut = in.readLine();
            String encryptedMessage = encode(message);
            PrintWriter out = new PrintWriter(new FileWriter(fileOut));
            out.println(encryptedMessage);
            out.close();
        }
        else if( option == 2)
        {
            System.out.println("input file name:");
            String archivo_entrada = in.readLine();
            String message = "";
            BufferedReader text = new BufferedReader(new FileReader(archivo_entrada));
            String line = text.readLine();
            while( line != null)
            {
                message = message + line + '\n';
                line = text.readLine();
            }
            text.close();
            System.out.println("output file name:");
            String fileOut = in.readLine();
            String encryptedMessage = decode(message);
            PrintWriter out = new PrintWriter(new FileWriter(fileOut));
            out.println(encryptedMessage);
            out.close();
        }
        in.close();
        System.out.println("prooces finished");
    }
    
    
}
