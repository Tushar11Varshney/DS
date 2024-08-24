import java.util.*;
class LRUCacheArray{
    static class ListNode {
        int key=0;
        int val=0;
        ListNode next;
        ListNode prev;

    public ListNode(int key,int val) {
            this.key=key;
            this.val = val;
            this.next = null;
            this.prev=null;
        }
    }
    private int maxSize=0;
    private ListNode[]map;
    private int currSize=0;
    ListNode head=null,tail=null;
    
    public LRUCacheArray(int capacity) {
            this.maxSize=capacity;
            map=new ListNode[10000+1];
    }   
    
    public void addFirstNode(ListNode temp)  //size 1 or else
    {
        if(this.head==null)
        this.head=this.tail=temp;
        else{
            this.head.next=temp;
            temp.prev=head;
            this.head=temp;
        }
        currSize++;
    }

    public void removeLastNode()  //size 1 or else
    {
        if(this.maxSize==1)
        {
            this.head=null;
            this.tail=null;
        }
        else{
            this.tail=this.tail.next;
            this.tail.prev.next=null;
            this.tail.prev=null;
        }
        currSize--;
    }

    public void removeNode(ListNode node)  //tail or else
    {
        if(node==this.tail)
        {
            removeLastNode();
            return;
        }    
        else{
            ListNode prev=node.prev;
            ListNode forward=node.next;
            node.next=null;node.prev=null;
            prev.next=forward;
            forward.prev=prev;
        }
        currSize--;
    }
    
    public void makeMostRecent(ListNode node)
    {
        if(node==head)return;
        removeNode(node);
        addFirstNode(node);
    }
    
    public int get(int key) {
        if(map[key]==null)
            return -1;
        
        ListNode node=map[key];
        makeMostRecent(node);
        return node.val;
    }

    public void put(int key, int value) {
        if(map[key]!=null)
        {
            ListNode node=map[key];
            node.val=value;
            get(key);
        }   
        else{
            if(currSize==maxSize)
            {
                map[tail.key]=null;
                removeLastNode();
            }    
            ListNode temp=new ListNode(key,value);
            addFirstNode(temp);
            map[key]=temp;
        }     
    }
}
