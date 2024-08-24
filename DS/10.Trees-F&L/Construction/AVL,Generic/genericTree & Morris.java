package Done;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

public class genericTree {
    public static class Node {
        int val = 0;
        ArrayList<Node> children;

        Node(int val) {
            this.val = val;
            this.children = new ArrayList<>();
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

    public int size(Node node) {
        // if(node==null)
        // return 0; //check constraint if size cannot be 0 then no need of this check because ye check hi sirf tab kelie jab node null pass ki gyi ho.aur isko ek alg fn mein daal skte hain because nhi tou har baar ye condn recursion mein check hogi.
        int sz = 0;
        for (Node child : node.children) {
            sz += size(child);
        }
        return sz + 1;
    }

    // height in terms of nodes
    // leetcode 559
    public int height(Node node) {
        if (node == null)
            return 0;
        // int ht=0;
        int ht = -1;
        for (Node child : node.children) {
            ht = Math.max(ht, height(child));
        }
        return ht + 1;
    }

    public static int max(Node node) {
        if(node==null)return 0;
        
        int maximum=node.data;
        for(Node child:node.children)
        maximum=Math.max(maximum,max(child));
        
        return maximum;
    }

    // 589
    public void preorderTraversal(Node root, List<Integer> result) {
        result.add(root.val);
        for (Node child : root.children)
            preorderTraversal(child, result);
    }

    public List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;
        preorderTraversal(root, result);
        return result;
    }

    // 590
    public void postorderTraversal(Node root, List<Integer> result) {
        for (Node child : root.children)
            postorderTraversal(child, result);
        result.add(root.val);
    }

    public List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;
        postorderTraversal(root, result);
        return result;
    }

    public static boolean find(Node node, int data) {
        if (node.val == data)
            return true;

        // boolean res=false;
        for (Node child : node.children) {
            // res=find(child,data); if(res)return res;
            if (find(child, data))return true;
        }
        return false; // return res;
    }

    public static int find01(Node node, int data) {
        if (node.val == data)
            return 0;

        int depth = -1;
        for (Node child : node.children) {
            depth = find01(child, data);
            if (depth != -1)break;
        }

        if (depth != -1)depth++;

        return depth;
    }

    public static boolean NodetoRootpath_helper(Node node, int data, ArrayList<Node> ans) {
        if (node.val == data) {
            ans.add(node);
            return true;
        }

        boolean res = false;
        for (Node child : node.children) {
            res = res || NodetoRootpath_helper(child, data, ans);
            if (res)break;
        }

        if (res)
            ans.add(node);

        return res;
    }

    public static ArrayList<Node> NodetoRootpath(Node node, int data) {
        ArrayList<Node> ans = new ArrayList<>();
        NodetoRootpath_(node, data, ans);
        return ans;
    }

    public static Node LCA(Node node, int d1, int d2) {
        ArrayList<Node> ans1 = new ArrayList<>();
        NodetoRootpath_(node, d1, ans1);

        ArrayList<Node> ans2 = new ArrayList<>();
        NodetoRootpath_(node, d2, ans2);

        int i = ans1.size() - 1;
        int j = ans2.size() - 1;

        Node res = null;
        while (i >= 0 && j >= 0) {
            if (ans1.get(i) == ans2.get(j)) {
                res = ans1.get(i);
                i--;
                j--;
            }
            else break;
        }
        return res;
    }

    // only left view and right view psble in generic tree..other not psble because in generic tree we dont know how many child a parent can have.
    public static void levelOrderLevelWiseM_3(Node root) {
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(root);
        int level = 0;
        while (que.size() != 0) {
            System.out.print("Level " + level + ":");
            int size = que.size();
            while (size-- > 0) {
                Node rn = que.removeFirst();
                System.out.print(rn.val + " ");

                for (Node child : rn.children)
                    que.addLast(child);
            }
            level++;
            System.out.println();
        }
    }

    // leetcode 429
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null)
            return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(root);
        while (que.size() != 0) {
            int size = que.size();
            List<Integer> level = new ArrayList<>();
            while (size-- > 0) {
                Node rn = que.removeFirst();
                level.add(rn.val);

                for (Node child : rn.children)
                    que.addLast(child);
            }
            result.add(level);
        }
        return result;
    }

    public static boolean isMirror(Node node1, Node node2) {
        if (node1.children.size() != node2.children.size())
            return false;

        if (node1.val != node2.val) // remove this for isomorphic
            return false;

        for (int i = 0, j = node2.children.size() - 1; j >= 0; i++, j--) {
            Node n1 = node1.children.get(i);
            Node n2 = node2.children.get(j);

            if (!isMirror(n1, n2))
                return false;
        }

        return true;
    }

    public static Node flattern(Node node) {
        if (node.children.size() == 0)
            return node;

        int n = node.children.size();
        Node lastChild = node.children.get(n - 1);
        Node gTail = flattern(lastChild);

        for (int i = n - 2; i >= 0; i--) {
            Node tempTail = flattern(node.children.get(i));
            tempTail.children.add(node.children.get(i + 1));
            node.children.remove(i + 1);
        }

        return gTail;
    }

    public void kDown(int time, Node node, Node blockNode, List<List<Integer>> ans) {
        if (node == blockNode)
            return;

        if (ans.size() == time)
            ans.add(new ArrayList<>());

        ans.get(time).add(node.val);
        for (Node child : node.children)
            kDown(time + 1, child, blockNode, ans);
    }

    public List<List<Integer>> BurningTree01(Node node, int data) { 
        List<Node> path = NodetoRootpath(node, data);

        List<List<Integer>> ans = new ArrayList<>();
        Node blockNode = null;

        for (int i = 0; i < path.size(); i++) {
            kDown(i, path.get(i), blockNode, ans);
            blockNode = path.get(i);
        }

        return ans;
    }

    public int BurningTree02(Node node, int data, List<List<Integer>> ans) {
        if (node.val == data) {
            kDown(0, node, null, ans);
            return 1;
        }

        int time = -1;
        Node blockNode = null;
        for (Node child : node.children) {
            time = BurningTree02(child, data, ans);
            if (time != -1) {
                blockNode = child;
                break;
            }
        }

        if (time != -1) {
            kDown(time, node, blockNode, ans);
            time++;
        }

        return time;
    }

}

// Generic Tree
// Structure-change ArrayList<Node>
// 16.size-1.check constraint if root=null r->0
//         2.sz=0,traverse root.children and sz+=call for size of children 
//         3.return sz+1

// 17.height-1.if node=null r->-1/0
//         2.ht=-1/0,traverse root.children and ht=max(ht,call for height of children) 
//         3.return ht+1

// 18.maximum-1.check constraint if node=null r->0 (depend on constraint)
//         2.maxi=node.data,traverse root.children and maxi=max(maxi,call for max of children) 
//         3.return maxi

// 19.PreOrder,postOrder-1.Make ans array
//                       2.if root null then return ans else call fn
//                       3.fn-add then (traverse and call) for pre,fn-(traverse and call) then add for postorder
//                       4.return in main fn ans

// 20(2).Find_1-1.dta found r->true
//           2.traverse and call find for children if true r->true(can take variable also)
//           3.loop finish ->return false
//    Find_2-1.dta found r->0
//           2.depth=-1 and traverse and call find if depth!=-1 break;
//           3.if depth!=-1 depth++ return depth
//           4.r->-1/depth

// 21.NodeToRootPath-same as find just also add in ArrayList

// 22.LCA-1.Take two arrayList and find path for both d1 and d2
//         2.Take two pointer and traverse from last and wherever both value unequal break 
//         3.return stored answer

// 23.LevelOrderTraversal-1.take queue and add root and level=0
//                        2.While queu.size!=0 print level and take size
//                         2.1 run loop till size-->0 and print value and push all child
//                         2.2increase level after size loop
//                      (agr arraylist mein answer then har level kelie alg arraylist)

// 24.IsoMorphic/isMirror-1.if size unequal of child r->f
//                        2.if value unequal of node r->f
//                        3.Traverse first tree from beg and second tree from end of children bcz mirror and take n1,n2 and check if(!mirror(n1,n2))r->f
//                        4.r->t

// 25.Flattern(flattern+return tail)-1.if node children size 0 return node
//                                   2.take size and get gTail by calling flattern on last child
//                                   3.Traverse from n-2 till i>=0 and call flatterning on i and get tempTail
//                                     3.2 add tempTail.children.add(node.children.get(i+1))
//                                     3.3 node.children.remove(i+1)
//                                   4. return gTail

// 26.(2)Burning tree-space(n) skew tree 1.Get path arrayList
//                                    2.take blockNode=null 
//                                    3.traverse arrayList 3.1 call kDown(blockNode,i,root)
//                                                         3.2 blockNode=arrayList(i)
//                                     kDown-i)if node==blockNode return
//                                           ii)if ans.size==time ans.add(new al)
//                                           iii)ans.get(time).add(root.val)
//                                           iv)traverse on children and call kDown(bn,time+1,children)
//                 M2-structure as find2 1.if data found call kdown(null,0,root) and return 1
//                                       2.time=-1 traverse children time=BurningTree(child)
//                                         2.1 if time!=-1 blockNode=child and break;
//                                       3.Loop exit if(time!=-1)kDown(bn,time,root) and time++
//                                       4.r->time