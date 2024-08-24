package 3.Trees-F&L.Construction;

public class set8 {
    
    //bst inorder predecessor successor
    //bt successor, direct node given and parent
    //bt prdecessor, successor, ceil, floor

    //11 february-https://www.lintcode.com/problem/inorder-successor-in-bst/
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode curr=root;
        TreeNode pred=null;
        TreeNode succ=null;
        while(curr!=null)
        {
            if(curr.val==p.val)
            {
                if(curr.left!=null)
                {
                    pred=curr.left;
                    while(pred.right!=null)
                        pred=pred.right;
                }

                if(curr.right!=null)
                {
                    succ=curr.right;
                    while(succ.left!=null)
                        succ=succ.left;
                }
                break;
            }
            else if(curr.val<p.val)
            {
                pred=curr;
                curr=curr.right;
            }
            else{
                succ=curr;
                curr=curr.left;
            }
        }
        return succ;
    }

    // leetcode 510 bst-2 locked
    static class TreeNode2 {
        int key;
        TreeNode2 left;
        TreeNode2 right;
        TreeNode2 parent;
    }

    public static TreeNode2 inorderSuccessor(TreeNode2 node) {
            TreeNode2 succ = null;
            if (node.right != null) {
                succ = node.right;
                while (succ.left != null)
                    succ = succ.left;
            } else {
                while (node.parent != null && node.parent.left != node)
                    node = node.parent;

                succ = node.parent;
            }
            return succ;
    }

    //pre succ in binary tree 
    public static class allSolPair{
        TreeNode prev=null;
        TreeNode succ=null;
        TreeNode pred=null;

        int ceil=(int)1e9;
        int floor=-(int)1e9;
    }

    public void getPre_SuccBT(TreeNode root,int key,allSolPair pair)
    {
        if(root==null)return;

        if(root.val<key)
        pair.floor=Math.max(root.val,pair.floor);

        if(root.val>key)
        pair.ceil=Math.min(root.val,pair.ceil);

        getPre_SuccBT(root.left, key, pair);  //this code above for preorder successor predecessor

        if(root.val==key && pair.pred==null)
        pair.pred=pair.prev;
        if(pair.prev!=null && pair.prev.val==key && pair.succ==null)  //pair.prev!=null for checking if the soln asked for first value of inorder.
        pair.succ=root;

        pair.prev=root;

        getPre_SuccBT(root.right, key, pair);
    }

    

}
