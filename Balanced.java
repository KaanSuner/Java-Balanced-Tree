
import java.io.*;
import java.util.*;
import java.util.logging.*;


/**
 *
 * @author Kaan Suner 
 * @version 1.8.0_111
 */
public class Balanced {
     /**
     * arraylist that we keep the values of nodes from reading user input file
     */
    static ArrayList<Integer> numbers = new ArrayList<>();
    /**
     * root node
     */
    static Node root;
     /**
     * Node class
     */
     static class Node { 
        int data; 
        Node left, right; 
        Node(int data) 
        { 
            this.data = data; 
            this.left = null; 
            this.right = null; 
        } 
    } 
    
    public static void main(String[] args) throws IOException {
        
    String inputPath=args[0];
    readFile(inputPath);
    FileWriter outputPath= new FileWriter(args[1]);
    PrintWriter pw = new PrintWriter(outputPath);
    Balanced tree = new Balanced(); 
    tree.root = tree.insertLevelOrder(numbers, tree.root, 0); 
     if (Balanced.isBalanced(tree.root)) 
            pw.println("True"); 
        else
            pw.println("False"); 
     
     pw.close();
    }
    /**
     * reads the user input file and store values int integer type in arraylist.
     * @param filename is given file path by user input file
     */
    public static void readFile(String filename){
            try {

            File f = new File(filename);
            Scanner s =new Scanner(f);

            while(s.hasNext()){
            String nextLine = s.next();
            String[] distinct = nextLine.split(" ");
            String single= distinct[0];
            if(single.equals("-"))
                single="0";
            int intnumbers = Integer.parseInt(single);
              numbers.add(intnumbers);
                
            }
            
            s.close();
            
            
         
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Queue.class.getName()).log(Level.SEVERE, null, ex);
        }
         
}
    /**
     * creates a binary tree using levelorder according to the values we get based on Arraylist.
     * @param list list that we keep values of nodes is given by user file
     * @param root root parameter is changing recursively. In base case, first root of tree
     * @param i integer parameter getting values from arraylist and setting the values to new node to create tree
     * @return root
     */
    public Node insertLevelOrder(ArrayList<Integer> list, Node root, int i) 
    { 
        // Base case for recursion 
        if (i < list.size()) { 
            if(list.get(i)!=0){
            Node temp = new Node(list.get(i)); 
            root = temp; 
  
            // insert left child 
            root.left = insertLevelOrder(list, root.left, 2 * i + 1); 
  
            // insert right child 
            root.right = insertLevelOrder(list, root.right, 2 * i + 2); 
            }else{ 
            Node temp = null;
            
            root = temp; 
 
            }
        } 
        return root; 
    }
    /**
     * checks whether the given tree is balanced.
     * @param node is the root node to check. It changes recursively
     * @return 
     */
   static boolean isBalanced(Node node) 
    { 
        /** for height of left subtree */
        int leftheight; 
        /** for height of right subtree */
        int rightheight; 
  
        /* If tree is empty then return true */
        if (node == null) 
            return true; 
  
        /* Get the height of left and right sub trees */
        leftheight = height(node.left); 
        rightheight = height(node.right); 
  
        if (Math.abs(leftheight - rightheight) <= 1) 
            return true; 
  
        /**If we reach here then tree is not height-balanced */
        return false; 
    } 
  
    
    /**  The function Compute the "height" of a tree. Height is the 
        number of nodes along the longest path from the root node 
        down to the farthest leaf node.*/
    static int height(Node node) 
    { 
        /* base case tree is empty */
        if (node == null) 
            return 0; 
  
        /** If tree is not empty then height = 1 + max of left 
         height and right heights */
        return 1 + Math.max(height(node.left), height(node.right)); 
    }
    
}
