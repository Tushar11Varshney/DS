package AVL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class  constructAVLfromBst {

    public static class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }

        int bal = 0;
        int height = -1;
    }

    public static void updateBalandHeight(TreeNode node) {
        if (node == null)
            return;

        int leftHeight = -1;
        int rightHeight = -1;

        if (node.left != null)
            leftHeight = node.left.height;
        if (node.right != null)
            rightHeight = node.right.height;

        node.bal = leftHeight - rightHeight;
        node.height = Math.max(leftHeight, rightHeight) + 1;
    }

    public static TreeNode leftRotation(TreeNode A) {
        TreeNode B = A.right;
        TreeNode Bkaleft = B.left;

        B.left = A;
        A.right = Bkaleft;

        B.left=getRotation(A);
        return getRotation(B);
    }

    public static TreeNode rightRotation(TreeNode A) {
        TreeNode B = A.left;
        TreeNode BkaRight = B.right;

        B.right = A;
        A.left = BkaRight;

        B.right=getRotation(A);
        return getRotation(B);
    }

    public static TreeNode getRotation(TreeNode node) {
        updateBalandHeight(node);
        if (node.bal >= 2) {   //ll,lr
            if (node.left.bal >= 1) {  //ll
                return rightRotation(node);
            } else {  //lr
                node.left = leftRotation(node.left);
                return rightRotation(node);
            }
        }

        else if (node.bal <= -2) {  //rr,rl
            if (node.right.bal <= -1) {  //rr
                return leftRotation(node);
            } else {  //rl
                node.right = rightRotation(node.right);
                return leftRotation(node);
            }
        }

        return node;
    }

    public static TreeNode postOrder(TreeNode root)
    {
        if(root==null)
        return null;

        root.left=postOrder(root.left);
        root.right=postOrder(root.right);

        return getRotation(root);
    }

    public static TreeNode insertNodeinBST(TreeNode root, int key) {
        if (root == null)
            // return getRotation(new TreeNode(key));   //no need even if height set -1 because we are doing this in postOrder.
            return new TreeNode(key);
        else if (key < root.val)
            root.left = insertNodeinBST(root.left, key);
        else
            root.right = insertNodeinBST(root.right, key);

        // updateBalandHeight(root);   //in postorder fn we are calling getRotation so no need to call this while making tree
        return root;
    }

    public static void display(TreeNode node) {
        if(node==null)return;
        StringBuilder sb=new StringBuilder();

        sb.append(node.left==null?".":node.left.val);
        sb.append(" <- " + node.val +"(" + node.bal + ")" + " <- " );
        sb.append(node.right==null?".":node.right.val);
        System.out.println(sb.toString());

        display(node.left);
        display(node.right);
    }
    
    public static void main(String[] args) {
        TreeNode root=null;
        // Random rand=new Random();
        for(int i=5;i<=9;i++)
        {
            // root=insertNodeinBST(root, rand.nextInt(1000));
            root=insertNodeinBST(root, i);
        }
        display(root);
        root=postOrder(root);
        System.out.println();
        display(root);

        // deleteNode(root, 3);
        // System.out.println();
        // display(root);
    }

    public static int maximumElement(TreeNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node.val;
    }

    public static TreeNode deleteNode(TreeNode root, int key) { 
        if(root==null)return null;  
        else if(key<root.val)
            root.left=deleteNode(root.left,key);
        else if(key>root.val)
            root.right=deleteNode(root.right,key);
        else
        {
            if(root.left==null||root.right==null)        
                return root.left!=null?root.left:root.right;
            else
                {
                    int max=maximumElement(root.left);    
                    root.val=max;
                    root.left=deleteNode(root.left, max);  
                }    
        }
        return getRotation(root);
    }
}

// Construct AVL from BST
// 15.1.Given:unbalanced tree so in insertNode fn now dont call getRotn(root) just r->root and in base case also even if -1
//    2.leftRotn-B.left=getRotn(A) return getRotn(B) rightRotn-B.right=getRotn(A) return getRotn(B)
//    3.getRotn- use >= sign(first if) and <= sign(else if)
//    4.After making tree in main call root=postOrder(root)
//    5.postOrder f-5.1if root==null return null.
//                 5.2 root.left=postOrder(root.left) and root.right=po(root.right) return getRotn(root)
