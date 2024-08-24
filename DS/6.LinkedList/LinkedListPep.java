package LinkedList;
public class LinkedListPep {
   
    public static class Node {  //agr is class ko linkedlist ke andar define krte ho then no need of static. 
        int data;
        Node next;
    }
    //jis variable ko hum chahte h ki wo backtrack kre usko stack mein rkhlo mtlb pass as a argument and jis variable ko aap chahte ho ki usko puraani value hi rhe backtrack krte tym aur call khtm hone ke baad usme changes kr ske then usko heap mein rkhlo.

    public static class LinkedList {
        Node head;
        Node tail;
        int size;

        public void reverseData_NotNextHelper(Node node,int floor)
        {
            if(node==null)
            return;
            reverseData_NotNextHelper(node.next,floor+1);

            if(floor>=size/2)
            {
                int data=left.data;
                left.data=node.data;
                node.data=data;

                left=left.next;
            }
        }

        Node left;
        public void reverseData_NotNext()
        {
            left=head;
            reverseData_NotNextHelper(head,0);
        }

        public boolean IsPalindromeHelper(Node node, int floor) {
            if (node == null) {
                return true;
            }
            boolean res = IsPalindromeHelper(node.next, floor + 1);
            // if(floor>=size/2)
            // {
                if(res==false)return res;
                else if(leftNode.data != node.data) {
                    return false;
                }
                else {
                    leftNode = leftNode.next;
                    return true;  //here not given return res because hum btarhe h usse ki agr if na chle tou return else 
                }       
            // }
            // return res;  //needed bcz if agr execute hi ni hua tou ye return hoga
        }

        Node leftNode;
        public boolean IsPalindrome() {
            leftNode=head;
            return IsPalindromeHelper(head,0);
        }

        public void foldHelper(Node right,int floor)
        {
            if(right==null)
            return;
            
            foldHelper(right.next,floor+1);
            if(floor>size/2)
            {
                Node temp=fleft.next;
                right.next=fleft.next;
                fleft.next=right;
                fleft=temp;
            }
            else if(floor==size/2)
            {
                tail=right;
                tail.next=null;
            }
        }
        
        Node fleft;
        public void fold() {
            fleft=head;
            foldHelper(head,0);
        }

    }
    
    public static void main(String []args)
    {
        LinkedList l1=new LinkedList();
        l1.addLast(10);l1.addLast(20);l1.addLast(30);l1.addLast(40);l1.addLast(50);l1.addLast(60);
        l1.reverseData_NotNext();
        l1.display();
    }
}

// leetcode 23-1.take min pq.
// 2.add all node of list in pq.
// 3.till pq size not 0 remove node and join and add nextNode if list still has...r->d->next 
public ListNode mergeKLists(ListNode[] lists) {
    PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> {
        return a.val - b.val;
    });

    for (ListNode node : lists)   //klogk in inbuilt pq and O(k) in our 
        if (node != null)
            pq.add(node);

    ListNode dummy = new ListNode(-1);
    ListNode prev = dummy;
    while (pq.size() != 0) // n*k loop
    {
        ListNode nextNode = pq.remove();

        prev.next = nextNode;
        prev = prev.next;
        if (nextNode.next != null)
            pq.add(nextNode.next);
    }

    return dummy.next;
}
