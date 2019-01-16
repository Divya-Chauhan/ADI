//package BST;

import java.util.*;
import java.lang.*;

public class BinaryTree <T extends Comparable<T>>
{
    private class Node
    {
        T val;
        Node left;
        Node right;

        public Node(T val)
        {
            this.val = val;
            left = null;
            right = null;
        }
        
    }
    
    private Node root;

    public BinaryTree()
    {
        root = null;
    }
    
    public void insert(T value)
    {
        root = insert(value,root);
    }

    private Node insert(T value, Node root)
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
        if (  (value.compareTo(root.val) == 0) && root.right == null && root.left == null)
        {
            return null;
        }
        else if ( (value.compareTo(root.val) == 0) && root.right != null && root.left == null)
        {
            return root.right;
        } 
        else if (  (value.compareTo(root.val) == 0) && root.right == null && root.left != null)
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

    private void leafNodes(Node root)
    {

        if (root == null)
            return ;

        if (root.left == null && root.right == null)
            System.out.print(root.val+"  ");

        leafNodes(root.left);

        leafNodes(root.right);

    }
    
    private void leftCircum(Node root)
    {
        
        if (root ==  null)
            return ;
        if (root.left != null || root.right != null) 
            System.out.print(root.val+"  ");

        if (root.left != null)
            leftCircum(root.left);
        else if (root.right != null)
            leftCircum (root.right);
        
    }

    private void rightCircum(Node root)
    {

        if (root ==  null)
            return ;

        if (root.right != null)
            leftCircum(root.right);

        else if (root.left != null)
            leftCircum (root.left);
       
        if (root.right != null || root.left != null)
            System.out.print(root.val+"  ");

    }

    
    public void circum()
    {
        if(root == null)
            return ;
        else
        {
            System.out.print(root.val+"  ");

            leftCircum(root.left);

            leafNodes(root);

            rightCircum(root.right);

        }
            
    } 

    public void LCA(T a, T b)
    {
	Node n = LCA(a, b, root);
        if ( n == null)
        {
            System.out.println("NO ANCESTOR ");
            return;
        }
        System.out.println(n.val);
    }

    private Node LCA(T a, T b, Node node)
    {
        if (node == null)
            return null;

        if ( (node.val).compareTo(a) == 0 || (node.val).compareTo(b) == 0 )
            return node; 

        if ( LCA(a,b,node.left) != null && LCA(a,b,node.right) != null)
            return node;

        if ( LCA(a,b,node.left) != null || LCA(a,b,node.right) != null)
            return LCA(a,b,node.left) != null ? node.left: node.right ;

        return null;
        
    }
    
    public static void main(String[] args)
    {
        BinaryTree<Integer> tree = new BinaryTree<>();
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
        System.out.println("CIRCUMFERENCE : ");
        tree.circum();
        
        System.out.println("\n LCA : ");
        tree.LCA(45,25);
        tree.LCA(45,70);
        System.out.println("\nDELETE 3  : : ");
        tree.delete(5);
        tree.display();
        System.out.println();

    }    
}
