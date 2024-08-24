package 3.Trees-F&L.Construction;

public class set4 {
// Target sum - has target, get all target, path to root from leaf with specific sum range(using array, string)
// Num of path from any node to node to achieve target(2-recursion, hashmap)
// Max path sum between two 
// Max path sum between any node to node(2)
// BST Target sum(4)

// leetcode 112
public boolean hasPathSum(TreeNode root, int targetSum) {
    if (root == null)
        return false;
    if (root.left == null && root.right == null && targetSum - root.val == 0)
        return true;
    boolean res = false;
    res = hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    return res;
}

// leetcode 113
public void pathSumHelper(TreeNode root, List<List<Integer>> res, int targetSum, List<Integer> smallAns) {
    if (root == null)
        return;
    if (root.left == null && root.right == null) {
        if (targetSum - root.val == 0) {
            List<Integer> base = new ArrayList<>(smallAns);
            base.add(root.val);
            res.add(base);
        }
        return;
    }
    smallAns.add(root.val);
    pathSumHelper(root.left, res, targetSum - root.val, smallAns);
    pathSumHelper(root.right, res, targetSum - root.val, smallAns);
    smallAns.remove(smallAns.size() - 1);
}

public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    List<List<Integer>> res = new ArrayList<>();
    pathSumHelper(root, res, targetSum, new ArrayList<>());
    return res;
}

// https://www.pepcoding.com/resources/online-java-foundation/binary-tree/binary-tree-path-to-leaves-from-root-official/ojquestion
public static void pathSumHelper(Node root,int lo,int hi,int sum,List<Integer> smallAns) {
    if (root == null)
        return;
    if (root.left == null && root.right == null) {
        sum+=root.data;
        if (sum>=lo && sum<=hi) {
            smallAns.add(root.data);
            for(int i=0;i<smallAns.size();i++)
                System.out.print(smallAns.get(i)+" ");
            System.out.println();
            smallAns.remove(smallAns.size()-1);
        }
        return;
    }
    smallAns.add(root.data);
    pathSumHelper(root.left, lo, hi, sum +  root.data, smallAns);
    pathSumHelper(root.right, lo, hi, sum +  root.data, smallAns);
    smallAns.remove(smallAns.size() - 1);
}

// using string
public static void pathSumHelper(Node root, int lo, int hi, int sum, String path) {
    if (root == null)
        return;
    if (root.left == null && root.right == null) {
        sum += root.data;
        if (sum >= lo && sum <= hi) {
            System.out.println(path + root.data);
        }
        return;
    }
    pathSumHelper(root.left, lo, hi, sum + root.data, path + root.data + " ");
    pathSumHelper(root.right, lo, hi, sum + root.data, path + root.data + " ");
}

// leetcode 437
public int pathSumHelper_(TreeNode root, int targetSum) {
    if (root == null)
        return 0;
    int res = 0;
    if (targetSum - root.val==0)
        res++;
    res += pathSumHelper_(root.left, targetSum - root.val);
    res += pathSumHelper_(root.right, targetSum - root.val);
    return res;
}

public int pathSum437(TreeNode root, int sum) {
    if (root == null)
        return 0;
    return pathSumHelper_(root, sum) + pathSum437(root.left, sum) + pathSum437(root.right, sum);
}

// leetcode 437
int ans = 0;
public void pathSum_(HashMap<Integer, Integer> map, TreeNode root, int prefixSum, int tar) {
    if (root == null)
        return;
    prefixSum += root.val;
    ans += map.getOrDefault(prefixSum - tar, 0);
    map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);

    pathSum_(map, root.left, prefixSum, tar);
    pathSum_(map, root.right, prefixSum, tar);

    map.put(prefixSum, map.get(prefixSum) - 1);
    if (map.get(prefixSum) == 0)
        map.remove(prefixSum);
}

public int pathSum(TreeNode root, int sum) {
    if (root == null)return 0;
    HashMap<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    pathSum_(map, root, 0, sum);
    return ans;
}


// https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/
int maxleafToleaf = -(int) 1e9;   //-100
public int maxPathSumHelper(Node root) {
    if (root == null)
        return -(int) 1e9;  //-100
    if (root.left == null && root.right == null)
        return root.data;

    int leftNodetoleafmax = maxPathSumHelper(root.left); // isme humne leftsubTree se bola apne se lekar apni kisi bhi leaf tak ka max path sum.
    int rightNodetoleafmax = maxPathSumHelper(root.right);

    if (root.left != null && root.right != null) // ek akela leaf..leaf to leaf nhi hota....but agr ye check na lagaye aur -100 bhi lele -(int)1e9 ki jgh then tab bhi chlega
    //because question mein unhone badi values ni li hai..but recommended use -(int)1e9.
    maxleafToleaf = Math.max(maxleafToleaf, leftNodetoleafmax + root.data + rightNodetoleafmax);

    return Math.max(leftNodetoleafmax, rightNodetoleafmax) + root.data;
}

public int maxPathSum(Node root) {
    if (root == null)
        return Integer.MIN_VALUE;
    maxPathSumHelper(root);
    return maxleafToleaf;
}

// leetcode 124
int maxNTN = -(int) 1e9; // Max sum from node to node  //6 case
public int maxPathSum_(TreeNode root) {
    if (root == null)
        return 0; // ya -(int)1e9

    //if (root.left == null && root.right == null)  //[0]->ans 0 but hoga -1e9 agr ye lgaya
        //return root.val;

    int leftNTN = maxPathSum_(root.left);
    int rightNTN = maxPathSum_(root.right);

    //yahan maxNTN me leftNTN,rightNTN isliye ni likha because global variable tha tou already vo consider ho chuke the
    int maxNodetoRoot = Math.max(leftNTN, rightNTN) + root.val;
    maxNTN = Math.max(Math.max(maxNTN, maxNodetoRoot),
            Math.max(root.val, leftNTN + root.val + rightNTN));

    return Math.max(maxNodetoRoot, root.val);
} 

// {nodeTonode,rootTonode}
class pair {

    int nodeTonode = -(int) 1e9;
    int rootTonode = -(int) 1e9;

    pair() {
    }
}

int max_(int... arr) {
    int max = arr[0];
    for (int ele : arr) {
        max = Math.max(max, ele);
    }
    return max;
}

public pair maxPathSum_class(TreeNode root) {
    if (root == null)
        return new pair();

    pair leftSubtreeMax = maxPathSum_class(root.left);
    pair rightSubtreeMax = maxPathSum_class(root.right);

    pair result = new pair();

    result.rootTonode = max_(max_(leftSubtreeMax.rootTonode, rightSubtreeMax.rootTonode) + root.val, root.val);
    
    result.nodeTonode=max_(leftSubtreeMax.nodeTonode,rightSubtreeMax.nodeTonode,result.rootTonode,leftSubtreeMax.rootTonode+root.val+rightSubtreeMax.rootTonode);

    return result;
}

public int maxPathSum(TreeNode root) {
    if (root == null)
        return 0;
    // maxPathSum_(root);
    // return maxNTN;
    return maxPathSum_class(root).nodeTonode;
}

// BST Target sum
public static void travelandPrint(Node root,Node node,int targetSum)
{
    if(node==null)
    return;
    travelandPrint(root,node.left,targetSum);
    
    int complement=targetSum-node.data;
    if(node.data<complement)
    {
        if(find(root,complement))
        System.out.println(node.data+" "+complement);
    }
    travelandPrint(root,node.right,targetSum);
}
 
public static void inorder(Node root,ArrayList<Integer>result)
{
    if(root==null)return;
    inorder(root.left, result);
    result.add(root.data);
    inorder(root.right, result);
}

public static void targetSumPair(Node root,int target)
{
    ArrayList<Integer>result=new ArrayList<>();
    inorder(root, result);
    int li=0,ri=result.size()-1,sum=0;
    while(li<ri)
    {
        sum=result.get(li)+result.get(ri);
        if(sum<target)
        li++;
        else if(sum>target)
        ri--;
        else{
            System.out.println(result.get(li)+" "+result.get(ri));
            li++;
            ri--;
        }
    }
}

static class valStatePair{
    Node node;
    int state;

    valStatePair(Node node,int state)
    {
        this.node=node;
        this.state=state;
    }
}    

public static Node leftInorderTraversal(Stack<valStatePair>st)
{
    while(st.size()>0)
    {
        valStatePair p=st.peek();
        if(p.state==1)
        {
            if(p.node.left!=null)
            st.push(new valStatePair(p.node.left,1));
            p.state++;
        }
        else if(p.state==2)
        {
            if(p.node.right!=null)
            st.push(new valStatePair(p.node.right,1));
            p.state++;
            return p.node;
        }
        else{
            st.pop();
        }
    }
    return null;
}

public static Node rightInorderTraversal(Stack<valStatePair>st)
{
    while(st.size()>0)
    {
        valStatePair p=st.peek();
        if(p.state==1)
        {
            if(p.node.right!=null)
            st.push(new valStatePair(p.node.right,1));
            p.state++;
        }
        else if(p.state==2)
        {
            if(p.node.left!=null)
            st.push(new valStatePair(p.node.left,1));
            p.state++;
            return p.node;
        }
        else{
            st.pop();
        }
    }

    return null;
}

public static void targetSumPair2(Node root,int target)
{
    Stack<valStatePair>Inorder=new Stack<>();
    Stack<valStatePair>ReverseInorder=new Stack<>();

    Inorder.push(new valStatePair(root,1));
    ReverseInorder.push(new valStatePair(root,1));

    Node li=leftInorderTraversal(Inorder);
    Node ri=rightInorderTraversal(ReverseInorder);

    while(li.data<ri.data)  
    {
        if(li.data+ri.data==target)
        {
            System.out.println(li.data+" "+ri.data);
            li=leftInorderTraversal(Inorder);
            ri=rightInorderTraversal(ReverseInorder);
        }
        else if(li.data+ri.data<target)
            li=leftInorderTraversal(Inorder);
        else
            ri=rightInorderTraversal(ReverseInorder);
    }
}

// 653-rajneesh sir
public void insertAllLeft(LinkedList<TreeNode>st,TreeNode node){
    while(node!=null)
    {
        st.addFirst(node);
        node=node.left;
    }
}

public void insertAllRight(LinkedList<TreeNode> st, TreeNode node) {
    while (node != null) {
        st.addFirst(node);
        node = node.right;
    }
}

public boolean findTarget(TreeNode root, int k) {

    LinkedList<TreeNode> st1 = new LinkedList<>();
    LinkedList<TreeNode> st2 = new LinkedList<>();

    insertAllLeft(st1, root);
    insertAllRight(st2, root);

    int sum = 0;
    while (st1.getFirst().val < st2.getFirst().val) {
        sum = st1.getFirst().val + st2.getFirst().val;
        if (sum == k)
            return true;
        else if (sum > k) {
            TreeNode np = st2.removeFirst();
            insertAllRight(st2, np.left);
        } else {
            TreeNode np = st1.removeFirst();
            insertAllLeft(st1, np.right);
        }
    }
    return false;
}

}
