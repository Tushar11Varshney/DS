package 3.Trees-F&L.Construction;

public class set9 {

// Serialize deserialize bt
// sorted list to bst
// construct maximum bt
// copy node with random ptr
// generate tree from preorder,inorder
// generate tree from postorder,inorder
// generate tree from postorder, preorder(bcz we have to find psi+1 in postorder so if psi==pei return node)
// bst from preorder
// level order to bst
// genrate all bst with value 1 to n
// full bt


//leetcode 297
public void serialize_(TreeNode root,StringBuilder sb)
{
    if(root==null)
    {
        sb.append("-1001 ");         //-1000 <= Node.val <= 1000
        return;
    }

    sb.append(root.val+" ");
    serialize_(root.left,sb);
    serialize_(root.right,sb);
}

public String serialize(TreeNode root) {
    StringBuilder sb=new StringBuilder();
    serialize_(root,sb);
    return sb.toString();
}

int idx=0;
public TreeNode deserialize_(int arr[])
{   
    if(arr[idx]==-1001)
    {
        idx++;
        return null;
    }

    TreeNode node=new TreeNode(arr[idx++]);
    node.left=deserialize(arr);
    node.right=deserialize(arr);

    return node;
}

public TreeNode deserialize(String data) {
    String arr[]=data.split(" ");
    int num[]=new int[arr.length];
    int i=0;
    for(String str:arr)
    {
        int x=Integer.parseInt(str);
        num[i++]=x;
    }
    return deserialize_(num);
}


//109 yc
public TreeNode sortedListToBST(ListNode head) {
    if(head==null)return null;
    if(head.next==null)return new TreeNode(head.val);

    ListNode slow=head,fast=head,mid=null;
    while(fast!=null && fast.next!=null)
    {
        mid=slow;
        slow=slow.next;
        fast=fast.next.next;
    }

    mid.next=null;
    TreeNode root=new TreeNode(slow.val);
    root.left=sortedListToBST(head); //isme pass horha h head..aur upr connection todna pdega taaki root consider na ho
    root.right=sortedListToBST(slow.next); //isme pass hi slow.next horha h so root consider ni horha

    return root;
}

// 654 yc
public TreeNode constructMBT_Helper(int []nums,int si,int ei)
{
    if(si>ei)return null;
    if(si==ei)return new TreeNode(nums[si]);
    int maxIndex=si;
    for(int i=si+1;i<=ei;i++)
    {
        if(nums[i]>nums[maxIndex])
            maxIndex=i;
    }

    TreeNode root=new TreeNode(nums[maxIndex],null,null);
    root.left=constructMBT_Helper(nums,si,maxIndex-1);
    root.right=constructMBT_Helper(nums,maxIndex+1,ei);
        
    return root;
}

public TreeNode constructMaximumBinaryTree(int[] nums) {
    int n=nums.length;
    TreeNode root=constructMBT_Helper(nums,0,n-1);
    return root;
}

//https://practice.geeksforgeeks.org/problems/clone-a-binary-tree/1#
public static void fillMap(Tree curr,HashMap<Tree,Tree>map)
{
    if(curr==null)return;
    
    Tree newNode=new Tree(curr.data);
    map.put(curr,newNode);
    fillMap(curr.left,map);
    fillMap(curr.right,map);
}

public static void connectClonedNodes(Tree curr,HashMap<Tree,Tree>map)
{
    if(curr==null)return;
    
    Tree copyNode=map.get(curr);
    copyNode.left=curr.left!=null?map.get(curr.left):null;
    copyNode.right=curr.right!=null?map.get(curr.right):null;
    copyNode.random=curr.random!=null?map.get(curr.random):null;
    
    connectClonedNodes(curr.left,map);
    connectClonedNodes(curr.right,map);
}

public static Tree cloneTree(Tree tree){
    HashMap<Tree,Tree>map=new HashMap<>();
    Tree curr=tree;
    fillMap(curr,map);
    
    curr=tree;
    connectClonedNodes(curr,map);
    return map.get(tree);
}

// leetcode 105
public TreeNode construct(int[] preorder, int[] inorder, int isi, int iei, int psi, int pei) {
    if (psi > pei)
        return null; // ya isi>iei
    int idx = isi;
    while (preorder[psi] != inorder[idx])idx++;

    TreeNode node = new TreeNode(preorder[psi]);

    int tnel = idx - isi;
    node.left = construct(preorder, inorder, isi, idx - 1, psi + 1, psi + tnel);
    node.right = construct(preorder, inorder, idx + 1, iei, psi + tnel + 1, pei);

    return node;
}

public TreeNode buildTree(int[] preorder, int[] inorder) {
    return construct(preorder, inorder, 0, inorder.length - 1, 0, preorder.length - 1);
}

// 106
public TreeNode construct_(int[] postorder, int[] inorder, int isi, int iei, int psi, int pei) {
    if (psi > pei)
        return null;
    int idx = isi;
    while (postorder[pei] != inorder[idx])
        idx++;

    TreeNode node = new TreeNode(inorder[idx]);
    //TreeNode node=new TreeNode(postorder[pei]);

    int tnel = idx - isi;
    node.left = construct_(postorder, inorder, isi, idx - 1, psi, psi + tnel - 1);
    node.right = construct_(postorder, inorder, idx + 1, iei, psi + tnel, pei - 1);

    return node;
}

public TreeNode buildTree_(int[] inorder, int[] postorder) { 
    return construct_(postorder, inorder, 0, inorder.length - 1, 0, postorder.length - 1);
}

// leetcode 889
public TreeNode construct2(int[] postorder, int[] preorder, int psi, int pei, int posi, int poei) {
    if (psi > pei)
        return null;

    TreeNode node = new TreeNode(preorder[psi]);

    if (psi == pei)  //ya posi,poei
        return node;

    int idx = posi;
    while (preorder[psi + 1] != postorder[idx])idx++;

    int tnel = idx - posi + 1;
    node.left = construct_(postorder, preorder, psi + 1, psi + tnel, posi, idx);
    node.right = construct_(postorder, preorder, psi + tnel + 1, pei, idx + 1, poei - 1);

    return node;
}

public TreeNode constructFromPrePost(int[] pre, int[] post) {
    return construct2(post, pre, 0, pre.length - 1, 0, post.length - 1);
}

//1008
int idx=0;
public TreeNode bst_preorder_helper(int []preorder,int n,int lr,int rr)
{
    if(idx>=n || !(preorder[idx]>=lr && preorder[idx]<rr))
        return null;
    
    TreeNode node=new TreeNode(preorder[idx++]);
    node.left=bst_preorder_helper(preorder,n,lr,node.val);
    node.right=bst_preorder_helper(preorder,n,node.val,rr);
    return node;
}

public TreeNode bstFromPreorder(int[] preorder) {
    int n=preorder.length;
    
    return bst_preorder_helper(preorder,n,(int)-1e8,(int)1e8);
}

    
// //https://practice.geeksforgeeks.org/problems/convert-level-order-traversal-to-bst/1# pc
public class pair{
    int lr;
    int rr;
    Node node;
    
    pair(int lr,int rr,Node node)
    {
        this.lr=lr;
        this.rr=rr;
        this.node=node;
    }
}
    
public Node constructBST(int[] arr)
{
    LinkedList<pair>queue=new LinkedList<>();
    int idx=0,n=arr.length;
    Node root=null;
    queue.add(new pair(-(int)1e8,(int)1e8,null));
    while(idx<n)
    {
        pair p=queue.pollFirst();
        if(!(arr[idx]>=p.lr && arr[idx]<p.rr))continue;
        Node node=new Node(arr[idx]);
        if(p.node==null)
        {
            root=node;
        }
        else{
            Node par=p.node;
            if(arr[idx]<=par.data)
            par.left=node;
            else par.right=node;
        }
        queue.add(new pair(p.lr,arr[idx],node));
        queue.add(new pair(arr[idx],p.rr,node));
        idx++;
    }
    return root;
}

// 95
public List<TreeNode> generateTrees(int n) {
    return generateTrees(1, n);
}

public List<TreeNode> generateTrees(int start, int end) {
    List<TreeNode> list = new ArrayList<>();
    if (start > end) {
        list.add(null);
        return list;
    }

    for (int i = start; i <= end; i++) {
        List<TreeNode> leftTreeList = generateTrees(start, i - 1);
        List<TreeNode> rightTreeList = generateTrees(i + 1, end);

        for (TreeNode l : leftTreeList) {
            for (TreeNode r : rightTreeList) {
                TreeNode root=new TreeNode(i,l,r);

                list.add(root);
            }
        }
    }
    return list;
}

// 894
public List<TreeNode> allPossibleFBT_(int si,int ei)
{
    List<TreeNode>res=new ArrayList<>();
    if(si==ei)
    {
        res.add(new TreeNode(0));
        return res;
    }
    
    for(int i=si+1;i<ei;i+=2)
    {
        List<TreeNode>leftSubTree=allPossibleFBT_(si,i-1);
        List<TreeNode>rightSubTree=allPossibleFBT_(i+1,ei);
        for(TreeNode l:leftSubTree)
        {
            for(TreeNode r:rightSubTree)
            {
                TreeNode root=new TreeNode(0);
                root.left=l;
                root.right=r;
                res.add(root);
            }
        }
    }
    return res;
}

public List<TreeNode> allPossibleFBT(int N) {
    if(N == 0 || N % 2 == 0) return new ArrayList<>();
    return allPossibleFBT_(0,N-1);
}


}
