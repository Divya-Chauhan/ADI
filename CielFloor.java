//package BST;

import java.util.*;
import java.lang.*;

public class CielFloor //<T extends Comparable<T>>
{
    private class Node
    {
        Integer val;
        Node left;
        Node right;

        public Node(Integer val)
        {
            this.val = val;
            left = null;
            right = null;
        }
        
    }
    
    private Node root;

    public CielFloor()
    {
        root = null;
    }
    
    public void insert(Integer value)
    {
        root = insert(value,root);
    }

    private Node insert(Integer value, Node root)
    {
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

        return root;
        
    }
    
    public Integer largest(Node root)
    {
        while(root.right != null)
        {
            root = root.right;
        }
        return root.val;
    }
    

    public Integer floor(Integer val)
    {
        Integer n = floor(root,val);
        if (n > val)
            return val.compareTo(n);
        else
        {
            System.out.println("floor :"+n); 
            return val.compareTo(n);
        }
    }

    private Integer floor(Node root, Integer value)
    {
        if(root == null)
            return 9999999;

        if(root.val == value)
            return value;

        else if(root.val > value)
            return floor(root.left, value);
      
        else
        {
           Integer  f = floor(root.right, value);
           return f <= value ? f : root.val;
        }
          
    }


    public Integer ciel(Integer val)
    {
        Integer n = ciel(root,val);
        if (n < val)
            return val.compareTo(n);
        else
        {
            System.out.println("ciel ="+n);
            return val.compareTo(n);
        }
    }

    private Integer ciel(Node root,  Integer value)
    {
        if(root == null)
            return -1;

        if(root.val == value)
            return value;

        else if(root.val < value)
            return ciel(root.right, value);

        else //if(root.val > value)
        {
            Integer c = ciel(root.left, value);

           return c >= value ? c : root.val;
        }


    }

    public void delete(Integer value)
    {
        root = delete(value,root);
    }

    private Node delete(Integer value, Node root)
    {   
        if(root == null)
            return root;

        if (  (value.compareTo(root.val) == 0) && root.right == null && root.left == null)
        {
            return null;
        }
        else if ( (value.compareTo(root.val) == 0) && root.left == null)
        {
            return root.right;
        } 
        else if (  (value.compareTo(root.val) == 0) && root.right == null)
        {
            return root.left;
        }
        else if(value.compareTo(root.val)==0)
        {
            Integer large = largest(root.left);
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
        CielFloor tree = new CielFloor();
        tree.insert(50);
        tree.insert(40);
        tree.insert(80);
        tree.insert(30);
        tree.insert(45);
        tree.insert(70);
        tree.insert(90);
        tree.insert(25);
        tree.insert(35);
        tree.insert(65);
        tree.insert(75);
        tree.insert(85);
        tree.insert(95);

        tree.display();
        System.out.println();
        System.out.println("\nDELETE 3  : : ");
        tree.delete(5);
        tree.display();
        System.out.println();
        System.out.println("90 floor  (90,90) : "+tree.floor(90)+"  ciel : "+tree.ciel(90) );
        System.out.println("23 floor  (25,-1) : "+tree.floor(23)+"  ciel : "+tree.ciel(23) );
        System.out.println("78 floor  (-1,75) : "+tree.floor(78)+"  ciel : "+tree.ciel(78) );
        System.out.println();

    }    
}
