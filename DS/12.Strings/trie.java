public class trie {

//208
class Node{
    char data;
    Node child[];
    boolean isend;
            
    Node(char data)
    {
        this.data=data;
        this.child=new Node[26];
    }
}

Node root;
public Trie() {
    root=new Node('*');
}

public void insert(String word) {
    Node curr=root;
    for(int i=0;i<word.length();i++)
    {
        char ch=word.charAt(i);
        int idx=ch-'a';
        if(curr.child[idx]==null)
            curr.child[idx]=new Node(ch);
        curr=curr.child[idx];
    }
    curr.isend=true;
}

public boolean search(String word) {
    Node curr=root;
    for(int i=0;i<word.length();i++)
    {
        char ch=word.charAt(i);
        if(curr.child[ch-'a']==null)return false;
        curr=curr.child[ch-'a'];
    }
    
    return curr.isend;
}

public boolean startsWith(String prefix) {
    Node curr=root;
    for(int i=0;i<prefix.length();i++)
    {
        char ch=prefix.charAt(i);
        if(curr.child[ch-'a']==null)return false;
        curr=curr.child[ch-'a'];
    }  
    return true;
}

//211
class Node{
    
    char ch;
    Node childs[];
    boolean isend;
    
    Node(char ch)
    {
        this.ch=ch;
        childs=new Node[26];
    }
    
    boolean find(String word,int idx)
    {
        if(idx==word.length())
            return this.isend;
        
        char ch=word.charAt(idx);
        boolean res=false;
        if(ch=='.'){
            for(Node vtx:this.childs)
            {
                if(vtx!=null)
                    res=res||vtx.find(word,idx+1);
                    if(res)return res;
            }
        }
        else{
            if(this.childs[ch-'a']!=null)
                res=res||this.childs[ch-'a'].find(word,idx+1);
        }
        return res;
    }
}

Node root;
public WordDictionary() {
    root=new Node('*');
}

public void addWord(String word) {
    Node curr=root;
    for(int i=0;i<word.length();i++)
    {
        char ch=word.charAt(i);
        if(curr.childs[ch-'a']==null)
            curr.childs[ch-'a']=new Node(ch);
        curr=curr.childs[ch-'a'];
    }
    curr.isend=true;    
}

public boolean search(String word) {
    return root.find(word,0);
}

//421
public class Node{
    int data;
    Node left;
    Node right;
    
    Node(int data){
        this.data=data;
    }
}

public class Trie{
    Node root=new Node(-1);
    
    void insert(int val)
    {
        Node curr=root;
        int bitIndex=30;
        while(bitIndex>=0)
        {
            int mask=1<<bitIndex;
            int bit=(val & mask)>0?1:0;  //check bit on or not
            if(bit==0)
            {
                if(curr.left==null)
                curr.left=new Node(bit);
                curr=curr.left;
            }
            else{
                if(curr.right==null)
                curr.right=new Node(bit);
                curr=curr.right;
            }
            bitIndex--;
        }
    }
    
    int query(int find)
    {
        Node curr=root;
        int bitIndex=30;
        int ans=0;
        while(bitIndex>=0)
        {
            int mask=1<<bitIndex;
            int bit=(find&mask)>0?1:0;
            
            if(bit==0)
            {
                if(curr.left!=null)
                curr=curr.left;
                else{
                    curr=curr.right;
                    ans|=mask;
                }
            }
            else{
                if(curr.right!=null)
                {
                    curr=curr.right;
                    ans|=mask;
                }
                else curr=curr.left;
            }
            bitIndex--;
        }
        return ans;
    }
}

public int findMaximumXOR(int[] nums) {
        
    Trie tr=new Trie();
    for(int val:nums)
        tr.insert(val);
    
    int max=0;
    for(int val:nums)
    {
        int find=Integer.MAX_VALUE^val;
        int res=tr.query(find);
        max=Math.max(max,res^val);
    }
    return max;
}

//212
public class Trie{
    Trie []child=new Trie[26];
    String str;     
    int count=0;
}

public void insert(Trie root,String str)
{
    Trie curr=root;
    for(int i=0;i<str.length();i++)
    {
        char ch=str.charAt(i);
        int idx=ch-'a';
        if(curr.child[idx]==null)
        {
            curr.child[idx]=new Trie();
            curr.count++;
        }
        curr=curr.child[idx]; 
    }
    curr.str=str;
}

public void findWords_helper(int x,int y,int [][]dirs,Trie root,List<String>ans,char [][]board)
{
    
    if(x<0 || x>=board.length || y<0 || y>=board[0].length || board[x][y]=='#' ||  root.child[board[x][y]-'a']==null || root.count==0 )
        return ;
    
    
    Trie child=root.child[board[x][y]-'a'];
    if(child.str!=null)
    {
        ans.add(child.str);
        child.str=null;  //isse duplicate ni aaynge pr kyunki trie pr exist krta hai tou call multiple tym lgegi
    }
    
    char ch=board[x][y];
    board[x][y]='#';
    for(int d=0;d<dirs.length;d++)
    {
        int i=x+dirs[d][0];
        int j=y+dirs[d][1];
        
        findWords_helper(i,j,dirs,child,ans,board);
    }
    board[x][y]=ch;
    
    if(child.count==0)
    {
        root.child[board[x][y]-'a']=null;  //to avoid call
        root.count--; 
    }           
}

public List<String> findWords(char[][] board, String[] words) {
    Trie root=new Trie();
    for(int i=0;i<words.length;i++)
        insert(root,words[i]);    
    
    List<String>ans=new ArrayList<>();
    int [][]dirs={{-1,0},{0,1},{1,0},{0,-1}};
    for(int i=0;i<board.length;i++)
    {
        for(int j=0;j<board[0].length;j++)
        {
            findWords_helper(i,j,dirs,root,ans,board);
        }
    }   
    return ans;
}

}
