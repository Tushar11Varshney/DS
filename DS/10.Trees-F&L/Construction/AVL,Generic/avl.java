package AVL;
import java.util.Random;
public class avl {

    public static class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }

        int bal = 0;
        int height = 0; // height agr 0 lenge tou no need to call getRotation() function because wo height ko 0 set krega...height -1 isliye lete kyunki jab tak koi node nhi bni matlab null hai..tou uski height -1
        // int height = -1; 
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

        updateBalandHeight(A);
        updateBalandHeight(B);

        return B;
    }

    public static TreeNode rightRotation(TreeNode A) {
        TreeNode B = A.left;
        TreeNode BkaRight = B.right;

        B.right = A;
        A.left = BkaRight;

        updateBalandHeight(A);
        updateBalandHeight(B);

        return B;
    }

    public static TreeNode getRotation(TreeNode node) {
        updateBalandHeight(node);
        if (node.bal == 2) {
            if (node.left.bal == 1) {     //ll
                return rightRotation(node);
            } else {                       //lr
                node.left = leftRotation(node.left);
                return rightRotation(node);
            }
        }

        else if (node.bal == -2) {
            if (node.right.bal == -1) {          //rr
                return leftRotation(node);
            } else {
                node.right = rightRotation(node.right);   //rl
                return leftRotation(node);
            }
        }

        return node; //agar koi rotation nhi honi tou return node.
    }

    public static TreeNode insertNodeinBST(TreeNode root, int key) {
        if (root == null)
            // return getRotation(new TreeNode(key));  //kaam sirf update height and balance ka h but vo void type ka hai.
            return new TreeNode(key);  // if height initialised with 0
        else if (key < root.val)
            root.left = insertNodeinBST(root.left, key);
        else
            root.right = insertNodeinBST(root.right, key);

        return getRotation(root);
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
        int numberofNodes=15;
        TreeNode root=null;
        for(int i=1;i<=numberofNodes;i++)
        {
            root=insertNodeinBST(root, i*10);
            display(root);
            System.out.println("================================================================");
            System.out.println();
        }
        // root=deleteNode(root,20);
        // display(root);
    }

    public static int maximumElement(TreeNode node)
    {
        TreeNode curr=node;
        while(curr.right!=null)
            curr=curr.right;
        return curr.val;
    }

    public static TreeNode deleteNode(TreeNode root, int key) { 
        if(root==null)return null;    //1st case
        else if(key<root.val)
            root.left=deleteNode(root.left,key);
        else if(key>root.val)
            root.right=deleteNode(root.right,key);
        else
        {
            if(root.left==null||root.right==null)          //2nd 3rd case
                return root.left!=null?root.left:root.right;
            else
                {
                    int max=maximumElement(root.left);    //4th case
                    root.val=max;
                    root.left=deleteNode(root.left, max);  //cant give root because baar then agin again ussi node pr pahuchega fir.
                }    
        }
        return getRotation(root);
    }

}

// AVL
// 14.1.Node-val,left,right,bf,height=-1/0
//2.Insert same as in bst just call getRotation(root) //if height -1 then in base case call getRotation else not
//    3.Update balance and Height-same just calculate height(max(leftH,rightH)+1) and bf
//    4.leftRotn rightRotn make diagram of left and right skew tree and also make subtree and UpdateBalandHeight(A) then UBH(B) then return B.
//    5.getRotn fn-4.1Update bal and Height
//                 4.2 bf of node==2 -> bf of node.left==1 r->rr rotn 
//                                 -> bf of node.left==-1 node.left=leftRotn(node.left) return right rotn(node)
//                 4.3 bf of node==-2 -> bf of node.left==1 r->ll rotn 
//                             -> bf of node.right==-1 node.right=rightRotn(node.right) return  left rotn(node)
//                 4.4 return node
//     6.Delete Node-same just at last call getRotn(root)

