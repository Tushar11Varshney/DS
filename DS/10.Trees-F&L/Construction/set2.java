import java.util.*;

public class set2 {

// 1.Size(bt/bst),Height,Maximum, Minimum(bt/bst-recursive,iterative), Find(bt/bst-recursive,iterative), Sum, Min depth(Root to leaf), Check tree complete or not, count node in complete tree((logn)^2)
// 2.Preorder, Postorder(recursive, iterative)
// 3.Node to root path-2, LCA(bt/bst-r/i), root to node distance, all node at distance-2, Step-By-Step Directions From a Binary Tree Node to Another, 
// 4.Diameter of tree(4,n^2,n,reference,without global)

// bst:insert node in bst(recursive/iterative),  delete node in bst, number of unique bst with n nodes

class Node
{
    Node left, right;
    int data;
    
    Node(int d)
    {
        data = d;
        left = right = null;
    } 
}

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public void display(ArrayList<TreeNode> list) {
    for (TreeNode node : list)
        System.out.print(node.val + " ");
    System.out.println();
}

// leetcode 110
// in term of nodes:node==null? 0
// in term of edges:node==null?-1
public int height(TreeNode node) {
    return node == null ? -1 : (Math.max(height(node.left), height(node.right))) + 1;
}

//leetcode 104-BT
public int maxDepth(TreeNode root) {
    if(root==null) return 0;
    return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
}

public int size(TreeNode node) {
    return node == null ? 0 : size(node.left) + size(node.right) + 1;
} 

public static int sum(Node node) {
    if(node==null)
    return 0;
    
    return sum(node.left)+sum(node.right)+node.data;
}

//leetcode 111 pc
//The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
public int minDepth(TreeNode root) {
    if(root==null)
        return 0;  //on basis of no of nodes.
    
    int leftHeight=minDepth(root.left);
    int rightHeight=minDepth(root.right);
    if(leftHeight!=0 && rightHeight!=0)
        return Math.min(leftHeight,rightHeight)+1;
    else if(rightHeight!=0)
        return rightHeight+1;
    else
        return leftHeight+1;
}

//bt
public int maximum(TreeNode node) {
    if (node == null)
        return -(int) 1e9;

    return Math.max(Math.max(maximum(node.left), maximum(node.right)), node.val);
}

//bst
public int maximumElement(TreeNode node)
{
    while(node.right!=null)
        node=node.right;
    return node.val;
}

public int maximumElement_(TreeNode node)
{
    if(node.right!=null)
    return maximumElement_(node.right);
    else return node.val;  //else lgana optional
}

public int minimumElement(TreeNode node)
{
    while(node.left!=null)
        node=node.left;
    return node.val;
}

public int minimumElement_(TreeNode node)
{
    if(node.left!=null)
    return minimumElement_(node.left);
    else return node.val;
}

public boolean find(TreeNode node, int data) {
    if (node == null)
        return false;

    if (node.val == data)
        return true;

    return find(node.left, data) || find(node.right, data);
}

// bst
public TreeNode searchBST(TreeNode root, int val) {
    if(root==null)
        return null;
    if(root.val==val)
        return root;
    else if(val<root.val)
         return searchBST(root.left,val);
    else return searchBST(root.right,val);
}

public TreeNode searchBST_(TreeNode root, int val) { 
    while(root!=null)
    {
        if(root.val==val)
            return root;
        else if(root.val>val)
            root=root.left;
        else
        root=root.right;
    }
    return null;
} 

//958
public boolean isCompleteTree_(TreeNode root,int i,int n) {
    if(root==null)return true;
    else if(i>=n)return false;
    return isCompleteTree_(root.left,2*i+1,n) && isCompleteTree_(root.right,2*i+2,n);
}

private int size(TreeNode root)
{
    if(root==null)return 0;
    return 1+size(root.left)+size(root.right);
} 

public boolean isCompleteTree(TreeNode root) {
    return isCompleteTree_(root,0,size(root));
}

//222
public int countNodes(TreeNode root) {
    if(root==null)return 0;
    
    int lh=leftHeight(root.left);
    int rh=rightHeight(root.right);
    
    if(lh==rh)
        return (1<<lh)-1;
    
    return 1+countNodes(root.left)+countNodes(root.right);
}

int leftHeight(TreeNode root)
{
    int lh=1;
    while(root!=null)
    {
        lh++;
        root=root.left;
    }
    return lh;
}

int rightHeight(TreeNode root)
{
    int rh=1;
    while(root!=null)
    {
        rh++;
        root=root.right;
    }
    return rh;
}


// leetcode 144
public void preorderTraversal(TreeNode root, List<Integer> result) {
    if (root == null)
        return;
    result.add(root.val);
    preorderTraversal(root.left, result);
    preorderTraversal(root.right, result);
}

public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    preorderTraversal(root, result);
    return result;
}

// leetcode 145
public void postorderTraversal(TreeNode root, List<Integer> result) {
    if (root == null)
        return;
    postorderTraversal(root.left, result);
    postorderTraversal(root.right, result);
    result.add(root.val);
}

public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    postorderTraversal(root, result);
    return result;
}

// https://www.pepcoding.com/resources/online-java-foundation/binary-tree/iterative-pre-post-in-binary-tree-official/ojquestion
public static void iterativePrePostInTraversal(Node node) {
    Stack<Pair> st = new Stack<>();
    Pair root = new Pair(node, 1);
    st.push(root);
    String pre = "";
    String in = "";
    String post = "";
    while (st.size() > 0) {
        Pair p = st.peek();
        if (p.state == 1) {
            pre += p.node.data + " ";
            p.state++;

            if (p.node.left != null) {
                st.push(new Pair(p.node.left, 1));
            }
        } else if (p.state == 2) {
            in += p.node.data + " ";
            p.state++;

            if (p.node.right != null) {
                st.push(new Pair(p.node.right, 1));
            }
        } else {
            post += p.node.data + " ";
            st.pop();
        }
    }
    System.out.println(pre);
    System.out.println(in);
    System.out.println(post);
}

// leetcode 144
static class Pair_ {
    TreeNode node;
    int state;

    Pair_(TreeNode node, int state) {
        this.node = node;
        this.state = state;
    }
}

public List<Integer> preorderTraversalIterative(TreeNode root) {
    List<Integer> preorder = new ArrayList<>();
    if (root == null)
        return preorder;
    Stack<Pair_> st = new Stack<>();
    Pair_ rootNode = new Pair_(root, 1);
    st.push(rootNode);
    while (st.size() > 0) {
        Pair_ p = st.peek();
        if (p.state == 1) {
            preorder.add(p.node.val);
            p.state++;

            if (p.node.left != null) 
                st.push(new Pair_(p.node.left, 1));
        } else if (p.state == 2) {
            p.state++;
            if (p.node.right != null)
                st.push(new Pair_(p.node.right, 1));
        } else st.pop();
    }
    return preorder;
}


// void type recursion-yahan void type isliye bolrhe hain kyunki hume chahiye arrayList pr hum vo return ni krwarhe
public boolean NodeToRootPath(TreeNode node, int data, ArrayList<TreeNode> res) {
    if (node == null)
        return false;

    if (node.val == data) {
        res.add(node);
        return true;
    }

    boolean ans = NodeToRootPath(node.left, data, res) || NodeToRootPath(node.right, data, res);
    if (ans)
        res.add(node);

    return ans;
} 

public ArrayList<TreeNode> NodeToRootPath2(TreeNode node, TreeNode data) // agar nodes ki value same ho tou answer same aa skte hain..tou can take then address instead of integer data...because address tou unique hai..
{
    if (node == null)
        return new ArrayList<>();

    if (node == data) {
        ArrayList<TreeNode> base = new ArrayList<>();
        base.add(node);
        return base;
    }

    ArrayList<TreeNode> left = NodeToRootPath2(node.left, data);
    if (left.size() > 0) {
        left.add(node);
        return left;
    }
    ArrayList<TreeNode> right = NodeToRootPath2(node.right, data);
    if (right.size() > 0) {
        right.add(node);
        return right;
    }
    return new ArrayList<>();
}

public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

    // ArrayList<TreeNode>list1=new ArrayList<>();
    // ArrayList<TreeNode>list2=new ArrayList<>();

    // NodeToRootPath(root, p.val, list1); //left skew tree ya right skew tree mein,NodeToRootpath mein space time o(n)
    // NodeToRootPath(root, q.val, list2);
    // display(list1);display(list2);
    ArrayList<TreeNode> list1 = NodeToRootPath2(root, p);
    ArrayList<TreeNode> list2 = NodeToRootPath2(root, q);
    int i = list1.size() - 1;
    int j = list2.size() - 1;

    TreeNode ans = null;
    while (i >= 0 && j >= 0) {
        if (list1.get(i) != list2.get(j))
            break;

        ans = list1.get(i);
        i--;
        j--;
    }
    return ans;
}

//leetcode 235 BST
//yahan constraint mein given hai ki dono p & q exist krenge if given that both may not exist then find that if present or not if using normal find then O(n) if skew else log(n)+log(n) for both element.
TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
    if(root->val>p->val && root->val>q->val)
        return lowestCommonAncestor(root->left,p,q);
    else if(root->val<p->val && root->val<q->val)
        return lowestCommonAncestor(root->right,p,q);
    else return root;
}

public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
{  
    TreeNode curr=root;
    while(curr!=null)
    {
        if(p.val<curr.val && q.val<curr.val)    //= waale leetcode pr consider nhi kree..all value unique
        curr=curr.left;
        else if(p.val>curr.val && q.val>curr.val)
        curr=curr.right;
        else break;
    }
    return curr;
}

public int rootToNodeDistance(TreeNode node, TreeNode data) {
    if (node == null)
        return -1;

    if (node.val == data.val)
        return 0;

    int lans = rootToNodeDistance(node.left, data);
    if (lans != -1) {
        return lans + 1;        
    }
    int rans = rootToNodeDistance(node.right, data);
    if (rans != -1) {
        return rans + 1;        
    }

    return -1;
}

public void printDown(TreeNode node,int depth,TreeNode block,Arraylist<TreeNode>res){ 
    if (node == null || depth < 0 || node == block)
        return;

    if (depth == 0)
        res.add(node.val);

    printDown(node.left, depth - 1, block, res);
    printDown(node.right, depth - 1, block, res);
}

public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {

    List<TreeNode> map = new ArrayList<>();
    map = NodeToRootPath2(root, target); // save this space in optimization

    TreeNode prev = null;
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < map.size(); i++) {
        printDown(map.get(i), K - i, prev, res);
        prev = map.get(i);
    }
    return res;
}

// optimized Solution
public int distanceK2Optimization(TreeNode node, TreeNode target, int K, List<Integer> res) {
    if (node == null)
        return -1;

    if (node == target) {
        printDown(node, K, null, res);
        return 1;
    }

    int lans = distanceK2Optimization(node.left, target, K, res);
    if (lans != -1) {
        printDown(node, K - lans, node.left, res);
        return lans + 1;
    }

    int rans = distanceK2Optimization(node.right, target, K, res);
    if (rans != -1) {
        printDown(node, K - rans, node.right, res);
        return rans + 1;
    }

    return -1;
}

public List<Integer> distanceK2(TreeNode root, TreeNode target, int K) { 
    List<Integer> res = new ArrayList<>();
    distanceK2Optimization(root, target, K, res);
    return res;
}

//2096 nc
public boolean getNodeToRootPath(TreeNode root,int val,List<TreeNode>result)
{
    if(root==null)return false;
    if(root.val==val){
        result.add(root);
        return true;
    }
    
    boolean res=getNodeToRootPath(root.left,val,result) || getNodeToRootPath(root.right,val,result);
    if(res)
        result.add(root);
    
    return res;
}

public boolean getPath(StringBuilder sb,TreeNode root,int target)
{
    if(root==null)return false;
    if(root.val==target)
        return true;
    
    boolean left=getPath(sb,root.left,target);
    if(left)
    {
        sb.append("L");
        return true;
    }
    
    boolean right=getPath(sb,root.right,target);
    if(right)
    {
        sb.append("R");
        return true;
    }
    
    return false;
}

public String getDirections(TreeNode root, int startValue, int destValue) {
    List<TreeNode> path1=new ArrayList<>();
    List<TreeNode> path2=new ArrayList<>();
    
    getNodeToRootPath(root,startValue,path1);
    getNodeToRootPath(root,destValue,path2);
    TreeNode lca=null;
    int i=path1.size()-1,j=path2.size()-1;
    while(i>=0 && j>=0)
    {
        if(path1.get(i).val==path2.get(j).val)
        {
            lca=path1.get(i);
            i--;
            j--;
        }
        else break;
    }
    
    StringBuilder sbl=new StringBuilder();
    StringBuilder sbr=new StringBuilder();
    
    if(startValue!=lca.val)
    {
        for(;i>=0;i--)
            sbl.append('U');
    }
    
    if(destValue!=lca.val)
    {
        getPath(sbr,lca,destValue);
        sbr.reverse();
    }
    return sbl.toString()+sbr.toString();
}


// leetcode 543
public int diameterOfBinaryTree_01(TreeNode root) {
    if (root == null)
        return -1;
    int diameterLeftSubTree = diameterOfBinaryTree(root.left);
    int diameterRightSubTree = diameterOfBinaryTree(root.right);

    int heightLeftSubTree = height(root.left);
    int heightRightSubTree = height(root.right);

    return Math.max(Math.max(diameterLeftSubTree, diameterRightSubTree),
            heightLeftSubTree + heightRightSubTree + 2);
}

public int[] diameterOfBinaryTree_02(TreeNode root) {
    if (root == null)
        return new int[] { -1, -1 }; // {dia,height}

    int[] LeftSubTree = diameterOfBinaryTree_02(root.left);
    int[] RightSubTree = diameterOfBinaryTree_02(root.right);

    int[] res = new int[2];
    res[0] = Math.max(Math.max(LeftSubTree[0], RightSubTree[0]), LeftSubTree[1] + RightSubTree[1] + 2); 
    // We are doing LeftSubTree[1] + RightSubTree[1] + 2 this calculation always so we can do this calculation and take diameter as a refrence.
    res[1] = Math.max(LeftSubTree[1], RightSubTree[1]) + 1;
    return res;
}

int maxDia = 0;
public int diameterOfBinaryTree_03(TreeNode root) {
    if (root == null)
        return -1;

    int heightLeftSubTree = diameterOfBinaryTree_03(root.left);
    int heightRightSubTree = diameterOfBinaryTree_03(root.right);

    maxDia = Math.max(maxDia, heightLeftSubTree + heightRightSubTree + 2);

    return Math.max(heightLeftSubTree, heightRightSubTree) + 1;
}

public int diameterOfBinaryTree_04(TreeNode root, int[] arr) {
    if (root == null)
        return -1;

    int heightLeftSubTree = diameterOfBinaryTree_04(root.left, arr);
    int heightRightSubTree = diameterOfBinaryTree_04(root.right, arr);

    arr[0] = Math.max(arr[0], heightLeftSubTree + heightRightSubTree + 2);

    return Math.max(heightLeftSubTree, heightRightSubTree) + 1;
}

public int diameterOfBinaryTree(TreeNode root) {
    if (root == null)
        return 0;
    // return diameterOfBinaryTree_01(root);
    // return diameterOfBinaryTree_02(root)[0];
    // diameterOfBinaryTree_03(root);
    int arr[] = new int[1];
    diameterOfBinaryTree_04(root, arr);
    return arr[0];
    // return maxDia;
}

//pc
// https://www.geeksforgeeks.org/burn-the-binary-tree-starting-from-the-target-node/
public void BurningTree(TreeNode root, int target) {
    List<TreeNode> path = new ArrayList<>();   //my
    path = NodeToRootPath2(root, target);

    int h = height(root);
    for (int d = 0; d <= h; d++) {
        TreeNode prev=null;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            if(d-i>=0)
            {
                printDown(path.get(i), d - i, prev, res);
                prev = path.get(i);
            }
            else break;
        }
        for (int x : res)
            System.out.print(x + " ");
        System.out.println();
    }
}

public void kdown(TreeNode blockNode,int depth,TreeNode root,List<List<Integer>>result)
{
    if(root==blockNode || root==null)return;

    if(depth==result.size())
    result.add(new ArrayList<>());

    result.get(depth).add(root.val);
    kdown(blockNode, depth+1, root.left, result);
    kdown(blockNode, depth+1, root.right, result);
}

public int burningTree2(TreeNode root,int target,List<List<Integer>>result)
{
    if(root==null)return -1;
    if(root.val==target)
    {
        kdown(null,0,root,result);
        return 1;
    }

    int ldepth=burningTree2(root.left, target,result);
    if(ldepth!=-1)
    {   
        kdown(root.left,ldepth,root,result);
        return ldepth+1;
    }

    int rdepth=burningTree2(root.right, target,result);
    if(rdepth!=-1)
    {
        kdown(root.right,rdepth,root,result);
        return rdepth+1;
    }

    return -1;
}

//leetcode 701
public TreeNode insertIntoBST(TreeNode root, int val) {   //adding in btw will be a costly operation so we add on leaf.decide for equal node whether they will be on left or right or we can take a hashmap and have a frequency.
    if(root==null)
        return new TreeNode(val);
    
    if(val<=root.val)
    root.left=insertIntoBST(root.left,val);
    else
    root.right=insertIntoBST(root.right,val);
    
    return root;
}

public TreeNode insertIntoBST_(TreeNode root, int val) {
    TreeNode curr=root;        
    TreeNode prev=null;        
    TreeNode newNode=new TreeNode(val);
    while(curr!=null)
    {
        prev=curr;
        if(val<=curr.val)
        curr=curr.left;
        else
        curr=curr.right;
    }
    if(prev==null)
    root=newNode;
    else if(prev.val<val)
    prev.right=newNode;
    else prev.left=newNode;

    return root;
}

//leetcode 450
public TreeNode deleteNode(TreeNode root, int key) { 
    if(root==null)return null;    //1st case
    else if(key<root.val)
        root.left=deleteNode(root.left,key);
    else if(key>root.val)
        root.right=deleteNode(root.right,key);
    else
    {
        if(root.left==null||root.right==null)          //2nd 3rd 4th case
            return root.left!=null?root.left:root.right;
        else
            {
                int max=maximumElement(root.left);    //5th case
                root.val=max;
                root.left=deleteNode(root.left, max);  //cant give root because baar then again again ussi node pr pahuchega fir....here can be catched in right also..and yahan return ni likhskte
            }    
    }
    return root;
}

// 96 & catalan number
public int numTrees(int n) {  //bst
    int totalWays = 0;
    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = 1;

    for (int i = 2; i <= n; i++) {
        for (int j = 0; j <i; j++) {
            dp[i] += dp[j] * dp[i - j - 1];
        }
    }
    return dp[n];
}
