package 3.Trees-F&L;

public class set5 {

// invert tree
// create left cloned tree(pre/post)
// transform back from left cloned tree(pre/post)
// remove leaves
// remove leaves with specific value..also which becomes leaves after removal of their child
// populating next ptr(2)
// Flattern tree(2 n^2, n)
// delete node return forest(enter delete node in hs, check in postorder if needed to delete)
// sorted array to bst(2-pre/post)

// 226
public TreeNode invertTree(TreeNode root) {
    if(root==null)
        return null;
    if (root.left == null && root.right == null)
        return root;
    invertTree(root.left);
    invertTree(root.right);
    TreeNode temp = root.left;
    root.left = root.right;
    root.right = temp;
    return root;
}

// https://www.pepcoding.com/resources/online-java-foundation/binary-tree/transform-to-left-cloned-tree-official/ojquestion
public static Node createLeftCloneTree(Node node) {
    if (node == null)
        return null;

    Node newNode = new Node(node.data, node.left, null);
    node.left = newNode;
    createLeftCloneTree(node.left.left);
    createLeftCloneTree(node.right);

    return node;
} 

public static Node createLeftCloneTree_(Node node) {
    if (node == null)
        return null;

    Node lcr = createLeftCloneTree_(node.left); // left cloned root
    Node rcr = createLeftCloneTree_(node.right);

    Node newNode = new Node(node.data, lcr, null);
    node.left = newNode;
    node.right = rcr;

    return node;
}

public static Node transBackFromLeftClonedTree(Node node){
    if (node == null)
       return null;
   
   node.left=node.left.left;
   transBackFromLeftClonedTree(node.left);
   transBackFromLeftClonedTree(node.right);
   return node;
}

public static Node transBackFromLeftClonedTree_(Node node) {
    if (node == null)
        return null;

    Node lcr = transBackFromLeftClonedTree_(node.left.left);
    Node rcr = transBackFromLeftClonedTree_(node.right);

    node.left = lcr;
    node.right = rcr;
    return node;
}

// https://www.pepcoding.com/resources/online-java-foundation/binary-tree/remove-leaves-binary-tree-official/ojquestion  pc
public static Node removeLeaves(Node node) {
    if (node == null)
        return null;
    if (node.left == null && node.right == null)
        return null;

    node.left = removeLeaves(node.left);
    node.right = removeLeaves(node.right);

    return node;
}

//1325 nc
TreeNode* removeLeafNodes(TreeNode* root, int target) 
{
    if(root==nullptr)
    return nullptr;
    
    root->left=removeLeafNodes(root->left,target);
    root->right=removeLeafNodes(root->right,target);

    // use postorder wapis aate hue bhi kaam krna h
    if(root->left==nullptr && root->right==nullptr && root->val==target)
        return nullptr;
    return root;
}

//116,117
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

public Node connect(Node root) {
    if(root==null)return root;
    LinkedList<Node>que=new LinkedList<>();
    que.add(root);
    while(que.size()!=0)
    {
        int size=que.size();
        while(size>0)
        {
            Node rn=que.remove();
            if(size==1)
            rn.next=null;
            else  rn.next=que.peek();

            if(rn.left!=null)
            que.add(rn.left);

            if(rn.right!=null)
            que.add(rn.right);

            size--;
        }
    }
    return root;
}

//116 a perfect binary tree where all leaves are on the same level, and every parent has two children.
public void populatingNextRight(Node root)
{
    // if(root==null)return;  //no need
    
    if(root.left==null && root.right==null)return;
    
    root.left.next=root.right;
    if(root.next!=null)root.right.next=root.next.left;
    
    populatingNextRight(root.left);
    populatingNextRight(root.right);
}

//117
public Node fun(Node node)
{
    if(node==null)return null;
    else if(node.left!=null)return node.left;    
    else if(node.right!=null)return node.right;  
    else return fun(node.next);
}

public void populatingNextRight2(Node root)
{
    if(root==null)return;
    if(root.left==null && root.right==null)return;
    
    if(root.left!=null)
    {
        if(root.right!=null)root.left.next=root.right;  
        else root.left.next=fun(root.next);   
    }
    
    if(root.right!=null)root.right.next=fun(root.next); 
    
    populatingNextRight2(root.right);
    populatingNextRight2(root.left);
}

public Node connect(Node root) {
    if(root==null)return null;
    populatingNextRight2(root);
    return root;
}

//leetcode 114
public TreeNode returnTail(TreeNode node)   //T->(O(n^2))
{   
    if(node==null)return null;
    while(node.right!=null)
        node=node.right;
    return node;
}

public void flatten(TreeNode root) {
    if(root==null || (root.left==null && root.right==null))return;
    
    flatten(root.left);
    flatten(root.right);
    
    TreeNode tail=returnTail(root.left);
    if(tail!=null)
    {   tail.right=root.right;
        root.right=root.left;
        root.left=null;
    } 
}

public TreeNode flatten_(TreeNode root) { 
    if(root==null || (root.left==null && root.right==null))
    return root;

    TreeNode leftTail=flatten_(root.left);
    TreeNode rightTail=flatten_(root.right);

    if(leftTail!=null)
    {
        leftTail.right=root.right;
        root.right=root.left;
        root.left=null;
    }

    return rightTail!=null?rightTail:leftTail;
}

public void flatten1(TreeNode root) {
    if(root==null)return;
    flatten_(root);
}

//1110
private TreeNode removeNodes(Set<Integer>hs,TreeNode root,List<TreeNode>result)
{
    if(root==null)return null;
    
    root.left=removeNodes(hs,root.left,result);
    root.right=removeNodes(hs,root.right,result);
    if(hs.contains(root.val))
    {
        if(root.left!=null)
        result.add(root.left);
        if(root.right!=null)
        result.add(root.right);
        return  null;
    }
    return root;
}
 
public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
    List<TreeNode>result=new ArrayList<>();
    if(root==null)return result;
    Set<Integer>hs=new HashSet<>();  //take set to delet in o(1)
    for(int x:to_delete)
        hs.add(x);
    removeNodes(hs,root,result);
    if(!hs.contains(root.val))
        result.add(root);
    return result;
}

//leetcode 108
public TreeNode sortedArrayToBSTHelper(int[] nums,int li,int ri)
{
    if(li>ri)return null;
    int mid=(li+ri)/2;
    TreeNode midNode=new TreeNode(nums[mid]);
    midNode.left=sortedArrayToBSTHelper(nums,li,mid-1);
    midNode.right=sortedArrayToBSTHelper(nums,mid+1,ri);
    return midNode;
}

/*public TreeNode sortedArrayToBSTHelper(int[] nums,int li,int ri)
{
    if(li>ri)return null;
    int mid=(li+ri)/2;
    TreeNode left=sortedArrayToBSTHelper(nums,li,mid-1);
    TreeNode right=sortedArrayToBSTHelper(nums,mid+1,ri);
    
    TreeNode newNode=new TreeNode(nums[mid],left,right);
    return newNode;
}*/

public TreeNode sortedArrayToBST(int[] nums) {
    return sortedArrayToBSTHelper(nums,0,nums.length-1);
}

