package Done;
import java.util.*;

public class constructBT {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static class Pair {
        Node node;
        int state;

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static Node construct(Integer arr[]) {
        Stack<Pair> st = new Stack<>();
        Node root = new Node(arr[0], null, null);
        st.push(new Pair(root, 1));

        int index = 0;
        while (st.size() > 0) {
            Pair p = st.peek();
            if (p.state == 1) {
                index++;
                if (arr[index] != null) { 
                    p.node.left = new Node(arr[index], null, null);
                    st.push(new Pair(p.node.left, 1));
                } else p.node.left = null;
                p.state++;
            } else if (p.state == 2) {
                index++;
                if (arr[index] != null) {
                    p.node.right = new Node(arr[index], null, null);
                    st.push(new Pair(p.node.right, 1));
                } else p.node.right = null;
                p.state++;
            } else { 
                st.pop();
            }
        }
        return root;
    }
    
    public static void display(Node node) {
        if (node == null)
            return;
        StringBuilder sb = new StringBuilder();
        sb.append(node.left == null ? "." : node.left.data);
        sb.append(" <- " + node.data + " <- ");
        sb.append(node.right == null ? "." : node.right.data);
        System.out.println(sb.toString());

        display(node.left);
        display(node.right);
    }

    public static void traversal(Node node) {     //euler path
        if (node == null)
            return;
        System.out.println(node.data + " in preorder");
        traversal(node.left);
        System.out.println(node.data + " in inorder");
        traversal(node.right);
        System.out.println(node.data + " in postorder");
    }

    public static void main(String args[]) {
        Integer arr[] = { 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null,
                null };
        Node root = construct(arr);
        // display(root);
        traversal(root);
    }

    //https://www.pepcoding.com/resources/online-java-foundation/binary-tree/is-balanced-binary-tree-official/ojquestion
    public boolean updateBalandHeight(TreeNode node) {
        int leftHeight = -1;
        int rightHeight = -1;

        if (node.left != null)
            leftHeight = height(node.left);
        if (node.right != null)
            rightHeight = height(node.right);

        int bal = leftHeight - rightHeight;
        if (bal >= -1 && bal <= 1)
            return true;
        else return false;
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        // boolean left=isBalanced(root.left);
        // boolean right=isBalanced(root.right);

        // if(left==true && right==true)
        // return updateBalandHeight(root);
        // else
        // return false;
        return isBalanced(root.left) && isBalanced(root.right) && updateBalandHeight(root);
    }

}
