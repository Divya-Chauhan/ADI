//package BST;

import java.util.*;
import java.lang.*;

public class AVL<T extends Comparable<T>>
{
    private class Node
    {
        int height;
        T val;
        Node left;
        Node right;

        public Node(T val)
        {
            height = 1;
            this.val = val;
            left = null;
            right = null;
        }
        
    }
    
    private Node root;

    public AVL()
    {
        root = null;
    }

    
    public void insert(T value)
    {
        root = insert(value,root);
    }

    private Node insert(T value, Node root)
    {
        int lh = 0, rh = 0; 
        if (root == null)
        {
            Node nn = new Node(value);
            return nn;
        }
        //T vall = root.val;        
        if ( value.compareTo(root.val) <= 0)
        {
            root.left = insert(value, root.left);
        }

        else if ( value.compareTo(root.val) > 0 )
        {
            root.right = insert(value, root.right);
        }

        
        //calculating height
        
        root.height = 1 + (heights(root.left) > heights(root.right) ? heights(root.left) : heights(root.right));     // calculating height

        root = balanceTree(root);// calculating balance 

        return root;         

    }

    private Node balanceTree(Node root)
    {

        int bal = heights(root.left) - heights(root.right);   //left - right

        if (bal < -1 && heights(root.right.right) > heights(root.right.left)) //right right condition
        {
            root = rotateLeft(root);
        }

        else if (bal < -1 && heights(root.right.left) > heights(root.right.right)) // right left condition
        {
            root.right = rotateRight(root.right);
            root = rotateLeft(root);
        }

        else if (bal > 1 && heights(root.left.right) < heights(root.left.left)) //left left condition
        {
            root = rotateRight(root);
        }

        else if (bal > 1 && heights(root.left.right) > heights(root.left.left) ) // left right condition
        {
            root.left = rotateLeft(root.left);
            root = rotateRight(root);
        }

        return root;

    }

    private int heights(Node node)
    {
        if (node == null)
            return 0;
        else
            return node.height;
    }
     
    private Node rotateLeft(Node root)
    {
        Node temp = root.right;
        root.right = temp.left;
        temp.left = root;
        
        root.height = 1 + (heights(root.left) > heights(root.right) ?  heights(root.left) : heights(root.right) );
        temp.height = 1 + (heights(temp.left) > heights(temp.right) ?  heights(temp.left) : heights(temp.right) );

        return temp;
     
    }

    private Node rotateRight(Node root)
    {
        Node temp = root.left;
        root.left = temp.right;
        temp.right = root;

        root.height = 1 + (root.left.height > root.right.height ?  root.left.height : root.right.height );
        temp.height = 1 + (temp.left.height > temp.right.height ?  temp.left.height : temp.right.height );

        return temp;

    } 

      
    public T largest(Node root)
    {
        while(root.right != null)
        {
            root = root.right;
        }
        return root.val;
    }

    public void delete(T value)
    {
        root = delete(value,root);
    }
    

    private Node delete(T value, Node root)
    {
 
        if (root == null)
            return root;

        if ( (value.compareTo(root.val) == 0) && root.left == null)
        {
            return root.right;
        } 
        else if (  (value.compareTo(root.val) == 0) && root.right == null )
        {
            return root.left;
        }
        else if(value.compareTo(root.val)==0)
        {
            T large = largest(root.left);
            root.val = large;
            root.left = delete(large, root.left);

        }
        else
        {
            if( (value.compareTo(root.val)) > 0)
            root.right = delete(value, root.right);

            else if( (value.compareTo(root.val)) < 0)
            root.left = delete(value, root.left);
        }
        
//        root = balanceTree(root);


        return root;
    }

    public void display()
    {
         Queue<Node> q = new LinkedList<>();
         Node node;
         q.add(root);
         q.add(null);
         while( q.size() > 1)
         {
             if (q.peek() == null)
             {
                 q.remove();
		 System.out.println();
                 q.add(null);
             }
             node = q.poll();
             System.out.print(" "+(node.val)+"  ");
             if (node != null && node.left != null)
             {
               
                 q.add(node.left);
             }
             if (node != null && node.right != null)
             {
                 q.add(node.right);
             }
             
         }

    }

    public static void main(String[] args)
    {
        AVL<Integer> tree = new AVL<>();
        for(int i = 1; i < 50; i++)
            tree.insert(i);

        tree.display();
        System.out.println();
 
        System.out.println("\nDELETE  : : ");
        for(int i = 0; i < 21 ; i++)
        tree.delete(i);
        tree.display();
        System.out.println();

    }    
}
