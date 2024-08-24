package Done;

import java.util.LinkedList;

// find tilt
// duplicate subtree
// longest consecutive
// longest consectuive 2
// longest path in terms of edge of univalue node
// Count univalue subtree
// largest bst
// house robber
// binary tree cameras
// longest zigzag path

public class set6{

public class TreeNode {
                int val;
                TreeNode left;
                TreeNode right;
                TreeNode() {}
                TreeNode(int val) { this.val = val; }
                TreeNode(int val, TreeNode left, TreeNode right) {
                    this.val = val;
                    this.left = left;
                    this.right = right;
                }
    }

//563
int tilt=0;
public int findTilt_(TreeNode root) {
    if(root==null)return 0;
    
    int leftSum=findTilt_(root.left);
    int rightSum=findTilt_(root.right);
    
    int localTilt=Math.abs(leftSum-rightSum);
    tilt+=localTilt;
    return leftSum+rightSum+root.val;
}
public int findTilt(TreehjNode root){
    findTilt_(root);
    return tilt;
}

//652
List<TreeNode>ans=new ArrayList<>();
Map<String,Integer>map=new HashMap<>();
public String duplicates(TreeNode root)
{
    if(root==null)return "X";
    
    String leftSubTree=duplicates(root.left);
    String rightSubTree=duplicates(root.right);
    String res=root.val + "-" + leftSubTree + "-" + rightSubTree;
    
    map.put(res,map.getOrDefault(res,0)+1);
    if(map.get(res)==2)
        ans.add(root);   
    
    return res;
}

public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
    if(root==null)return ans;
    duplicates(root);
    return ans;
}


//https://www.lintcode.com/problem/binary-tree-longest-consecutive-sequence/
class info{   //stack overflow
    int LCSleft;
    int LCSright;
    int LCSme;

    info(int LCSleft,int LCSright,int LCSme)
    {
        this.LCSleft=LCSleft;
        this.LCSright=LCSright;
        this.LCSme=LCSme;
    }
};

public info longestConsecutive_(TreeNode root) {
    if(root==null)return new info(0,0,0);
    if(root.left==null && root.right==null)return new info(0,0,1);

    info result=new info(0,0,1);
    info leftSubTree=longestConsecutive_(root.left);
    info rightSubTree=longestConsecutive_(root.right);
    
    result.LCSleft=Math.max(leftSubTree.LCSleft,leftSubTree.LCSme);

    result.LCSright=Math.max(rightSubTree.LCSright,rightSubTree.LCSme);

    if(root.left!=null && root.val+1==root.left.val)
        result.LCSme=Math.max(result.LCSme,leftSubTree.LCSme+1);
    if(root.right!=null && root.val+1==root.right.val) 
        result.LCSme=Math.max(result.LCSme,rightSubTree.LCSme+1);    

    System.out.println(result.LCSleft+" "+result.LCSright+" "+result.LCSme);
    return result;
}

public int longestConsecutive(TreeNode root)
{
    if(root==null)return 0;
    info result=longestConsecutive_(root);
    return Math.max(result.LCSleft,Math.max(result.LCSright,result.LCSme));
}

public void longestConsecutive_(TreeNode root,int count,int target,int []max)  //parent to child+inc
{
    if(root==null)return;
    else if(root.val==target)count++;
    else count=1;

    max[0]=Math.max(max[0],count);
    longestConsecutive_(root.left,count,root.val+1,max);
    longestConsecutive_(root.right,count,root.val+1,max);

}

public int longestConsecutive(TreeNode root) {
    if(root==null)return 0;
    int []max=new int[1];
    longestConsecutive_(root,0,root.val,max);
    return max[0];
}

//https://www.lintcode.com/problem/binary-tree-longest-consecutive-sequence-ii/
int maxlength=0;
public int[] longestConsecutive2_(TreeNode root)
{
    if(root==null)return new int[]{0,0};
    if(root.left==null && root.right==null)return new int[]{1,1};

    int []ans=new int[2];  //increase,decrease
    ans[0]=1;ans[1]=1;
    int []left=longestConsecutive2_(root.left);
    int []right=longestConsecutive2_(root.right);

    if(root.left!=null)
    {
        if(root.left.val+1==root.val)
        ans[1]=left[1]+1;
        else if(root.left.val-1==root.val)
        ans[0]=left[0]+1;
    }
    if(root.right!=null)
    {
        if(root.right.val+1==root.val)
        ans[1]=Math.max(ans[1],right[1]+1);
        else if(root.right.val-1==root.val)
        ans[0]=Math.max(ans[0],right[0]+1);
    }

    maxlength=Math.max(maxlength,ans[0]+ans[1]-1);
    return ans;
}

public int longestConsecutive2(TreeNode root) {
    longestConsecutive2_(root);
    return maxlength;
}

//687 nc
public int max=1;
public int longestUnivaluePath_helper(TreeNode root)
{
    if(root==null)return 0;
    if(root.left==null && root.right==null)return 1;
    
    int l=longestUnivaluePath_helper(root.left);
    int r=longestUnivaluePath_helper(root.right);
    
    int height=1;
    if(root.left!=null || root.right!=null)
    {
        if(root.left!=null && root.right!=null && root.left.val==root.val && root.right.val==root.val)
        {
            max=Math.max(max,l+r+1); 
            height=Math.max(height,Math.max(l,r)+1);
        }
        else if(root.left!=null && root.val==root.left.val)
        {
            max=Math.max(max,l+1); 
            height=Math.max(height,l+1);
        }
        else if(root.right!=null && root.val==root.right.val)
        {
            max=Math.max(max,r+1);
            height=Math.max(height,r+1);
        }
    }
    
    return height;
}

public int longestUnivaluePath(TreeNode root) {
    if(root==null)return 0;
        longestUnivaluePath_helper(root);
    return max-1;  //edge btani h
}

// {1,1,1,#,#,1,1,#,#,-1,-1}
//https://www.lintcode.com/problem/count-univalue-subtrees/
class info_{
    int univalueTree;
    boolean amIuniValue;
    info_(){}
    info_(int univalueTree,boolean amIuniValue)
    {
        this.univalueTree=univalueTree;
        this.amIuniValue=amIuniValue;
    }
}

public info_ UnivalSubTree(TreeNode root)
{
    if(root==null)
    return new info_(0,true);
    if(root.left==null && root.right==null)
    return new info_(1,true);

    info_ res=new info_();
    info_ leftSubTree=UnivalSubTree(root.left);
    info_ rightSubTree=UnivalSubTree(root.right);

    if(root.left!=null || root.right!=null)
    {
        if(root.left!=null && root.right!=null)
        {
            if(leftSubTree.amIuniValue && rightSubTree.amIuniValue && root.val==root.left.val && root.val==root.right.val)
            {
                res.univalueTree=leftSubTree.univalueTree+rightSubTree.univalueTree+1;
                res.amIuniValue=true;
            }    
            else 
            {
                res.univalueTree=leftSubTree.univalueTree+rightSubTree.univalueTree;
                res.amIuniValue=false;
            }    
        }
        else if(root.left!=null) {
            if(root.val==root.left.val){
                res.amIuniValue=true;
                res.univalueTree=leftSubTree.univalueTree+1;
            }
            else{
                res.amIuniValue=false;
                res.univalueTree=leftSubTree.univalueTree;
            }    
        }    
        else if(root.right!=null){
            if(root.val==root.right.val){
                res.amIuniValue=true;
                res.univalueTree=rightSubTree.univalueTree+1;
            }
            else{
                res.amIuniValue=false;
                res.univalueTree=rightSubTree.univalueTree;
            }   
        }    
    }

    return res;    
}

public int countUnivalSubtrees(TreeNode root) {
    if(root==null)return 0;
    return UnivalSubTree(root).univalueTree;
}

public static class BstPair {
    boolean isBst = true;
    long max = -(long) 1e13;
    long min = (long) 1e13;

    Node root = null;
    int size = 0;

    BstPair() {
    }
}

public static BstPair largestBst(Node root) {
    if (root == null)
        return new BstPair();

    BstPair leftTree = largestBst(root.left);
    BstPair rightTree = largestBst(root.right);

    BstPair res = new BstPair();
    res.isBst = leftTree.isBst && rightTree.isBst && leftTree.max < root.data && root.data < rightTree.min;
    res.max = Math.max(root.data, max(leftTree.max,rightTree.max));
    res.min = Math.min(root.data, min(leftTree.min,rightTree.min));  
    // or res.min = Math.min(root.data, leftTree.min);

    if (res.isBst) {
        res.root = root;
        res.size = leftTree.size + rightTree.size + 1;
    } else if (leftTree.size > rightTree.size) {
        res.root = leftTree.root;
        res.size = leftTree.size;
    } else {
        res.root = rightTree.root;
        res.size = rightTree.size;
    }
    return res;
}


// leetcode 337
public int[] rob_(TreeNode root) {
    if (root == null) {
        int base[] = { 0, 0 };
        return base;
    }

    int lans[] = rob_(root.left);
    int rans[] = rob_(root.right);

    int myAns[] = new int[2];
    myAns[0] = lans[1] + root.val + rans[1];
    myAns[1] = Math.max(lans[0], lans[1]) + Math.max(rans[0], rans[1]);

    return myAns;
}

public int rob(TreeNode root) {
    if (root == null)return 0;
    int ans[] = rob_(root);
    return Math.max(ans[0], ans[1]);
}      

// leetcode 968
int cameras = 0;
public int minCameraCover_(TreeNode root) {
    if (root == null)
        return 0;

    int lans = minCameraCover_(root.left);
    int rans = minCameraCover_(root.right);

    if (lans == -1 || rans == -1) {
        cameras++;
        return 1;
    }

    if (lans == 1 || rans == 1)
        return 0;

    return -1;
}

public int minCameraCover(TreeNode root) {
    int answer = minCameraCover_(root);
    if (answer == -1)
        return cameras + 1; // not cameras++
    return cameras;
}


// leetcode 1372
//Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0)
public int[] longestZigZagPath(TreeNode root)
{
    if(root==null)
    {
        int []base={-1,-1,-1};   //forward,backward,maxLen
        return base;
    }

    int leftans[]=longestZigZagPath(root.left);
    int rightans[]=longestZigZagPath(root.right);

    int []myAns=new int[3];
    myAns[0]=leftans[1]+1;
    myAns[1]=rightans[0]+1;
    myAns[2]=Math.max(Math.max(leftans[2],rightans[2]),Math.max(myAns[0],myAns[1]));  //path thru root not considered

    return myAns;
}

public int longestZigZag(TreeNode root) {
    if(root==null)return 0;
    int []ans=longestZigZagPath(root);
    return ans[2];
}

}