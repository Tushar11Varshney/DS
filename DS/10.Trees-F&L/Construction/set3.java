public class set3 {
 
// get all paths from root to leaf
// is tree univalue
// sum of left leaves
// 2 same tree
// merge tree
// is tree symmetric(mirror image)
// sum root to leaf number
// Second min value
// Cousin in bt
// deepest level sum
// print single child nodes - 2(with and without use of parent)
// flip eqvnt bt(2) - (parent same and size same / should be same or mirror)

// leetcode 257
public void getAllPaths(List<String> result, TreeNode root, String str) {
    if(root==null)return;
    if(root.left==null && root.right==null)
    {
        str+=root.val;
        result.add(str);
        return;
    }
    str+=root.val+"->";
    getAllPaths(result,root.left,str);
    getAllPaths(result,root.right,str);
}

public List<String> binaryTreePaths(TreeNode root) {
    List<String> result = new ArrayList<>();
    if (root == null)
        return result;
    getAllPaths(result, root, "");
    return result;
}

//leetcode 965
public boolean isUniValTree_helper(TreeNode root,int data)
{
    if(root==null)return true;
    
    if(root.val!=data)return false;

    return isUniValTree_helper(root.left,data) && isUniValTree_helper(root.right,data);

}

public boolean isUnivalTree(TreeNode root) {
    return isUniValTree_helper(root,root.val);
}

// leetcode 404
int leftSum = 0;   //ye bhi heap pr hi h
public boolean isNodeLeftleaf(TreeNode node) {
    if (node == null)
        return false;
    if (node.left == null && node.right == null)
        return true;

    return false;
}

public int sumOfLeftLeaves(TreeNode root) {
    if (root == null)
        return 0;
    if (isNodeLeftleaf(root.left))
        leftSum += root.left.val;
    sumOfLeftLeaves(root.left);
    sumOfLeftLeaves(root.right);
    return leftSum;
}

//100
public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null || q == null)
        return p == q;

    if (p.val != q.val)
        return false;

    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
}

// 617
public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
    if (root1 == null)
        return root2;
    if (root2 == null)
        return root1;
    root1.val += root2.val;
    root1.left = mergeTrees(root1.left, root2.left);
    root1.right = mergeTrees(root1.right, root2.right);
    return root1;
}

// 101
public boolean isSymmetric(TreeNode p1, TreeNode p2) {
    // if(p1==null && p2==null)
    // return true;

    // if(p1==null && p2!=null || p1!=null && p2==null)
    // return false;

    if (p1 == null || p2 == null)
        return p1 == p2;

    if (p1.val != p2.val)
        return false;

    return isSymmetric(p1.left,p2.right) && isSymmetric(p1.right,p2.left);
}

public boolean isSymmetric(TreeNode root) {
    TreeNode p1 = root, p2 = root;
    return isSymmetric(p1, p2);
}

// 129 yc
int sum = 0;
public void sumNumbersHelper(List<Integer>path,TreeNode root)
{   
    if(root==null)return;
    if(root.left==null && root.right==null)    
    {
        path.add(root.val);
        int smallSum=0;
        for(int i=0;i<path.size();i++)
            smallSum=smallSum*10+path.get(i);  //Integer.parseInt(path);
        sum+=smallSum;
        path.remove(path.size()-1);
        return;
    } 
    path.add(root.val);   //path+=root.val;
    sumNumbersHelper(path,root.left);
    sumNumbersHelper(path,root.right);
    path.remove(path.size()-1);
}

public int sumNumbers(TreeNode root) {
    List<Integer> path = new ArrayList<>();
    sumNumbersHelper(path, root);
    return sum;
}

//671 yc
void preorder(TreeNode root,long []small)
{
    if(root==null)return;
    
    if(small[0]<root.val && root.val<small[1])
        small[1]=root.val;

    preorder(root.left,small);
    preorder(root.right,small);
}


public int findSecondMinimumValue(TreeNode root) {
    long []small= new long[2];
    small[0]=root.val;
    small[1]=Long.MAX_VALUE;  //root sbse chota

    preorder(root, small);
    return  (int)(small[1]==Long.MAX_VALUE?-1:small[1]);
}

//993 nc
public boolean isCousins_helper(TreeNode root, TreeNode par,int[]arr,int val,int depth)
{
    if(root==null)return false;
    if(root.val==val){
        arr[0] = (par==null)?-1:par.val;
        arr[1] = depth;
        return true;
    }
    
    boolean res=false;
    res=isCousins_helper(root.left,root,arr,val,depth+1)||isCousins_helper(root.right,root,arr,val,depth+1);
    return res;
}

public boolean isCousins(TreeNode root, int x, int y) {
    int []arr1=new int[]{-1,-1};
    int []arr2=new int[]{-1,-1};
    
    isCousins_helper(root,null,arr1,x,0);
    isCousins_helper(root,null,arr2,y,0);
    
    if(arr1[1]==arr2[1] && arr1[0]!=arr2[0])  //same depth diff parent
    return true;
    return false;
}

//1302
// or by level order traversal
public void dls_helper(TreeNode root,int level,int []ht)
{
    if(root==null)return;
    if(level>ht[0])
    {
        ht[0]=level;
        ht[1]=root.val;
    }
    else if(level==ht[0]){
        ht[1]+=root.val;
    }
    
    dls_helper(root.left,level+1,ht);
    dls_helper(root.right,level+1,ht);
}

public int deepestLeavesSum(TreeNode root) {
    int []ht=new int[2];
    dls_helper(root,1,ht);
    return ht[1];
}   

public static void printSingleChildNodes(Node node) {
    if (node == null)
        return;
    if ((node.left == null && node.right != null) || (node.left != null && node.right == null)) {
        if (node.right != null)
            System.out.println(node.right.data);
        else
            System.out.println(node.left.data);  
        return;
    }

    printSingleChildNodes(node.left);
    printSingleChildNodes(node.right);
}

// sir
public static void printSingleChildNodes(Node node, Node parent) {
    if (node == null)
        return;
    if (parent != null && parent.left == node && parent.right == null)
    {
        System.out.println(node.data);
        return;
    }
    else if (parent != null && parent.right == node && parent.left == null)
    {
        System.out.println(node.data);
        return;
    }
    printSingleChildNodes(node.left, node);
    printSingleChildNodes(node.right, node);

    // printSingleChildNodes(root, null);
}

//951
private void addToMap(TreeNode root,Map<Integer,Integer>map)
{
    if(root==null)return;
    if(root.left==null && root.right==null)return;
    
    if(root.left!=null)
    map.put(root.left.val,root.val);
    if(root.right!=null)
    map.put(root.right.val,root.val);
    
    addToMap(root.left,map);
    addToMap(root.right,map);
}

public boolean flipEquiv(TreeNode root1, TreeNode root2) {
    if(root1==null && root2==null)return true;
    else if(root1==null || root2==null)return false;
    Map<Integer,Integer>map1=new HashMap<>();
    addToMap(root1,map1);
    
    Map<Integer,Integer>map2=new HashMap<>();
    addToMap(root2,map2);

    if(map1.size()!=map2.size())return false;
    for(Integer i:map1.keySet())
    {
        int parent=map1.get(i);
        if(parent!=map2.get(i))return false;
    }
    
    return true;
}

public boolean flipEquiv(TreeNode root1, TreeNode root2) {
    if(root1==null && root2==null)return true;
    else if(root1==null || root2==null)return false;
    else if(root1.val!=root2.val)return false;
    else return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)) || (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
}

}
