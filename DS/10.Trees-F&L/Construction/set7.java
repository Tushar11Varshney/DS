package 3.Trees-F&L.Construction;

public class set7 {
    
// replace with sum of larger bst
// print in range bst
// valid bst-2(treat like array, )
// convert binary tree to dll
// Increasing Order Search Tree
// bst iterator
// recover bst
    
//https://www.pepcoding.com/resources/online-java-foundation/binary-search-tree/replace-with-sum-of-larger-official/ojquestion
//1038 todo
static int sum = 0;
public static void rwsol(Node node) {   //replace with sum of larger
    if(node==null)return;
    rwsol(node.right);
    
    int originalValue=node.val;
    node.val=sum;
    sum+=originalValue;
    
    rwsol(node.left);
}

// https://www.pepcoding.com/resources/online-java-foundation/binary-search-tree/pir-bst-official/ojquestion
public void printInRange(TreeNode node, int d1, int d2) {   //my soln
    if(node==null)return;
    printInRange(node.left,d1,d2);
    if(node.val>=d1 && node.val<=d2)
    // System.out.println(node.data);
    sum+=node.val;
    printInRange(node.right,d1,d2);
}

//leetcode 938
int sum1=0;   //static bnane se dusre test case kelie bhi vhi purani value ke saath add hota hai.
public void pir_(TreeNode node, int d1, int d2) {   
    if(node==null)return;
    if(node.val>d1 && node.val>d2)       //= lgane se printing galat
    pir_(node.left,d1,d2);
    else if(node.val<d1 && node.val<d2)
    pir_(node.right,d1,d2);
    else{
        pir_(node.left,d1,d2);
        // System.out.println(node.val);
        sum1+=node.val;
        pir_(node.right,d1,d2);
    }
}

public int pir(TreeNode root, int low, int high) {
    pir_(root,low,high);
    return sum1;
} 

//98
public class BstPair{
    boolean isBst=true;
    long max=-(long)1e13;
    long min=(long)1e13;

    BstPair(){}
}

public BstPair isValidBST_(TreeNode root)
{
    if(root==null)return new BstPair();

    BstPair leftTree=isValidBST_(root.left);
    BstPair rightTree=isValidBST_(root.right);

    BstPair res=new BstPair();
    res.isBst=leftTree.isBst && rightTree.isBst && leftTree.max<root.val && root.val<rightTree.min;
    if (!res.isBst)
            return res;
    res.max=Math.max(root.val,rightTree.max);
    res.min=Math.min(root.val,leftTree.min);
    return res;
}

public boolean isValidBST(TreeNode root) {
    if(root==null)return false;
    return isValidBST_(root).isBst;
}

long prevNode = -(long) 1e13;
public boolean isValidBST2(TreeNode root) {

    if (root == null)
        return true;
    if (!isValidBST2(root.left))
        return false;

    if (prevNode >= root.val)
        return false;
    prevNode = root.val;

    if (!isValidBST2(root.right))
        return false;

    return true;
}

public boolean isValidBST(TreeNode root) {
    if (root == null)
        return false;
    // return isValidBST_(root).isBst;
    return isValidBST2(root);
}

//897 gc
TreeNode current=null;
public void increasingBST_(TreeNode root)
{
    if(root==null)return;
    
    increasingBST_(root.left);
    root.left=null;
    current.right=root;
    current=root;
    increasingBST_(root.right);
}

public TreeNode increasingBST(TreeNode root) {
    TreeNode dummy=new TreeNode(-1);
    current=dummy;
    increasingBST_(root);
    return dummy.right;
}

//426
//https://practice.geeksforgeeks.org/problems/binary-tree-to-dll/1#
Node dummy=new Node(-1);
Node prev=dummy;
void bToDLL_(Node root)
{
    if(root==null)return;
    
    bToDLL_(root.left);
    
    prev.right=root;
    root.left=prev;
    prev=root;
    bToDLL_(root.right);
}

Node bToDLL(Node root)
{
    if(root==null)return null;
    bToDLL_(root);
    Node head=dummy.right;
    dummy.right=null;
    head.left=null;
    return head;
}

// leetcode 173
class BSTIterator {

    Stack<TreeNode>st=new Stack<>();
    public BSTIterator(TreeNode root) {
        addAllLeft(root);
    }
    
    public void addAllLeft(TreeNode root)
    {
        while(root!=null)
        {
            st.push(root);
            root=root.left;
        }    
    }
    
    public int next() {
        TreeNode rn=st.pop();
        if(rn.right!=null)
            addAllLeft(rn.right);   
        return rn.val;
    }
    
    public boolean hasNext() {
        return st.size()!=0;    
    }
}

// [7,-3,12,-1,6,-9,13,0,2,4,null,8,-11,null,-15,null,null,null,null,null,-5,null,null,10,null,19]
// leetcode 99
TreeNode prev = null, a = null, b = null;
public boolean recoverTree_(TreeNode root) {
    if (root == null)
        return true;

    if (!recoverTree_(root.left))  //traversal work like loop
        return false;

    if (prev != null && prev.val > root.val) {
        b = root;
        if (a == null)
            a = prev;
        else
            return false;   //2 jgah galti milgyi ab aage iterate krne ka fayda ni return false
    }
    prev = root;

    if (!recoverTree_(root.right))  //this works like break in loop
        return false;

    return true;
}

public void recoverTree(TreeNode root) {
    if (root == null)
        return;
    recoverTree_(root);
    int temp = a.val;
    a.val = b.val;
    b.val = temp;
}


}
