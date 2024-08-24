package 3.Trees-F&L.Construction;

public class set10 {
    
    
// 16 feb
public static TreeNode rightMostNode(TreeNode next, TreeNode curr) {
    while (next.right != null && next.right != curr)
        next = next.right;
    return next;
}

public static void morrisInorderTraversal(TreeNode root) {
    TreeNode curr = root;
    while (curr != null) {
        TreeNode next = curr.left;
        if (next == null) {
            System.out.println(curr.val + " ");
            curr = curr.right;
        } else {
            TreeNode rightMost = rightMostNode(next, curr);
            if (rightMost.right == null) {  //thread create
                rightMost.right = curr;
                curr = curr.left;
            } else {
                rightMost.right = null; //break thread it mean we have traversed whole left subtree
                System.out.println(curr.val + " ");
                curr = curr.right;
            }
        }
    }
}

public static void morrisPreorderTraversal(TreeNode root) {
    TreeNode curr = root;
    while (curr != null) {
        TreeNode next = curr.left;
        if (next == null) {
            System.out.println(curr.val + " ");
            curr = curr.right;
        } else {
            TreeNode rightMost = rightMostNode(next, curr);
            if (rightMost.right == null) {
                System.out.println(curr.val + " ");
                rightMost.right = curr;
                curr = curr.left;
            } else {
                rightMost.right = null;
                curr = curr.right;
            }
        }
    }
}

// leetcode 230
public void insertAllLeft(LinkedList<TreeNode> st, TreeNode node) {
    while (node != null) {
        st.addFirst(node);
        node = node.left;
    }
}

public int kthSmallest(TreeNode root, int k) {
    LinkedList<TreeNode> st = new LinkedList<>();
    insertAllLeft(st, root);

    while (k-- > 1) {
        TreeNode rn = st.getFirst();
        st.removeFirst();
        insertAllLeft(st, rn.right);
    }

    return st.getFirst().val;
}

public static TreeNode rightMostNode(TreeNode next, TreeNode curr) {
    while (next.right != null && next.right != curr)
        next = next.right;

    return next;
}

public static int kthSmallest2(TreeNode root, int k) {
    TreeNode curr = root;
    while (curr != null) {
        TreeNode next = curr.left;
        if (next == null) {
            if (k == 1)
                return curr.val;
            k--;
            curr = curr.right;
        } else {
            TreeNode rightMost = rightMostNode(next, curr);
            if (rightMost.right == null) {
                rightMost.right = curr;
                curr = curr.left;
            } else {
                rightMost.right = null;
                if (k == 1)
                    return curr.val;
                k--;
                curr = curr.right;
            }
        }
    }
    return -1;
}

//https://practice.geeksforgeeks.org/problems/median-of-bst/1#
public static Node rightMostNode(Node node,Node curr)
{
    while(node.right!=null && node.right!=curr)
    {
        node=node.right;
    }
    return node;
}

public static int inorder(Node root,int k)
{
    Node curr=root;
    int ans=-1;
    while(curr!=null)
    {
        Node left=curr.left;
        if(left==null)
        {
            if(k==1)
                ans=curr.data;  //cant return poora chlana pdega vrna agli baar jab chlaynge galat answer
            k--;
            curr=curr.right;
        }
        else{
            Node rightnode=rightMostNode(left,curr);
            if(rightnode.right==null) //thread create
            {
                rightnode.right=curr;
                curr=curr.left;
            }
            else{
                rightnode.right=null;
                if(k==1)
                    ans=curr.data;
                k--;
                curr=curr.right;
            }
        }
    } 
    return ans;
}

public static int size(Node root)
{
    return root==null?0:size(root.left)+size(root.right)+1;    
}

public static float findMedian(Node root)
{
    int sz=size(root);
    float v1,v2;
    if(sz%2!=0)
    {
        v1=inorder(root,sz/2+1);
        return v1;
    }
    else{
        v2=inorder(root,sz/2+1);
        v1=inorder(root,sz/2);
        return (v1+v2)/2;
    }
}

}
