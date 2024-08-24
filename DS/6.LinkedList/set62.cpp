// Parititon list
// sort binary list
// odd even linked list(removeFirst,addLast) / Segregate even and odd nodes in a Linked List

// 1
// even reverse

// 86
ListNode *partition(ListNode *head, int x)
{
    ListNode *dsmall = new ListNode(-1);
    ListNode *p1 = dsmall;
    ListNode *dgreater = new ListNode(-1);
    ListNode *p2 = dgreater;

    ListNode *curr = head;
    while (curr != nullptr)
    {
        if (curr->val < x)
        {
            p1->next = curr;
            p1 = curr;
        }
        else
        {
            p2->next = curr;
            p2 = curr;
        }

        curr = curr->next;
    }
    p2->next = nullptr;
    p1->next = dgreater->next;

    return dsmall->next;
}

//ib
ListNode *sortBinaryList(ListNode *A)
{
    ListNode *dummyZero = new ListNode(-1);
    ListNode *prevZero = dummyZero;
    ListNode *dummyOne = new ListNode(-1);
    ListNode *prevOne = dummyOne;

    ListNode *curr = A;
    while (curr != nullptr)
    {
        if (curr->val == 0)
        {
            prevZero->next = curr;
            prevZero = curr;
        }
        else
        {
            prevOne->next = curr;
            prevOne = curr;
        }

        curr = curr->next;
    }

    prevZero->next = dummyOne->next;
    prevOne->next = nullptr;

    return dummyZero->next;
}

//leetcode 328 bc
void removeFirst(ListNode *&head, int &size)
{
    if (size == 0)return;
    else
    {
        head = head->next;
        size--;
    }
}

void addLast(int &size, int value, ListNode *&head, ListNode *&tail)
{
    ListNode *temp = new ListNode();
    temp->val = value;
    temp->next = nullptr;
    if (size == 0)
        head = tail = temp;
    else
    {
        tail->next = temp;
        tail = temp;
    }
    size++;
}

// first odd then even
ListNode *oddEvenList(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *curr = head;
    int size = 0, i = 1, oddSize = 0, evenSize = 0, osize = 0;
    while (curr != nullptr)
    {
        size++;
        curr = curr->next;
    }
    osize = size;
    ListNode *odd = nullptr, *oddTail = nullptr, *even = nullptr, *evenTail = nullptr;

    while (i <= osize)
    {
        int val = head->val;
        removeFirst(head, size);

        if (i % 2 != 0)
            addLast(oddSize, val, odd, oddTail);
        else
            addLast(evenSize, val, even, evenTail);

        i++;
    }

    oddTail->next = even;
    return odd;
}

// https://practice.geeksforgeeks.org/problems/segregate-even-and-odd-nodes-in-a-linked-list/0
public class questions {
    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    static Node head;
    static Node tail;

    public static void display(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static Node segregateEvenOdd(Node head) {
        Node dummyEven = new Node(-1);
        Node dummyOdd = new Node(-1);
        Node prevOdd = dummyOdd;
        Node prevEven = dummyEven;
        Node curr = head;
        while (curr != null) {
            if (curr.val % 2 == 0) {
                prevEven.next = curr;
                prevEven = curr;
            } else {
                prevOdd.next = curr;
                prevOdd = curr;
            }
            curr = curr.next;
        }
        prevOdd.next = null;
        prevEven.next = dummyOdd.next;
        return dummyEven.next;
    }

    public static void segregategfg() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            // head=null;
            // tail=null;
            Node dummyNode = new Node(-1);
            Node prev = dummyNode;
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                Node temp = new Node(sc.nextInt());
                prev.next = temp;
                prev = temp;
                // addLast(val);
            }
            Node head = segregateEvenOdd(dummyNode.next);
            // head=segregateEvenOdd(head);
            display(head);
        }
        

        //first odd then tail
        // LinkedList oddList=new LinkedList();
        //     LinkedList evenList=new LinkedList();
        //     while(this.size()>0)   
        //     {
        //         int val=this.getFirst();
        //         this.removeFirst();
                
        //         if(val%2!=0)
        //             oddList.addLast(val);
        //         else evenList.addLast(val);
        //     }
        //     if(evenList.head!=null && oddList.tail!=null)  //connect
        //     (oddList.tail).next=evenList.head;
    
        //     if(oddList.head!=null)  //set prop
        //     this.head=oddList.head;
        //     else
        //     this.head=evenList.head;
        //     if(evenList.tail!=null)
        //     this.tail=evenList.tail;
        //     else
        //     this.tail=oddList.tail;
            
        //     this.size=oddList.size()+evenList.size();
    }
}


//ib
ListNode *evenReverse(ListNode *A)
// Reverse the order of all nodes at even position
{
    ListNode *dummyEven = new ListNode(-1);
    ListNode *prevEven = dummyEven;
    ListNode *dummyOdd = new ListNode(-1);
    ListNode *prevOdd = dummyOdd;

    ListNode *curr = A;
    int posn = 1;
    while (curr != nullptr)
    {
        if (posn % 2 == 0)
        {
            prevEven->next = curr;
            prevEven = curr;
        }
        else
        {
            prevOdd->next = curr;
            prevOdd = curr;
        }

        posn++;
        curr = curr->next;
    }

    prevEven->next = nullptr;
    prevOdd->next = nullptr;

    //reverse even list
    ListNode *prev = nullptr;
    curr = dummyEven->next;
    while (curr != nullptr)
    {
        ListNode *forward = curr->next;
        curr->next = prev;
        prev = curr;
        curr = forward;
    }
    dummyEven->next = prev;
    ListNode *currOdd = dummyOdd->next;
    ListNode *currEven = dummyEven->next;

    posn = 1;
    A = currOdd;
    while (currOdd != nullptr && currEven != nullptr)
    {
        if (posn % 2 == 0)
        {
            ListNode *f = currEven->next;
            currEven->next = currOdd;
            currEven = f;
        }
        else
        {
            ListNode *f = currOdd->next;
            currOdd->next = currEven;
            currOdd = f;
        }
        posn++;
    }

    return A;
}
