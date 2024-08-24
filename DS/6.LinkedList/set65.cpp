// reverse list(2-iterative, recursive)
// Again Reorder List
// Data reverse(Relative posn of node should not change)
// reverse node in k group, swap pairs
// Reverse between

//leetcode 206
ListNode *reverseList(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *prev = nullptr, *curr = head;
    while (curr != nullptr)
    {
        ListNode *forward = curr->next; // store the address of curr.next before pointing curr.next to previous.

        curr->next = prev;

        prev = curr;
        curr = forward;
    }

    return prev;
}

ListNode *reverseList(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;
    ListNode *temp1 = head->next;
    head->next = nullptr;
    ListNode *reverseListHead = reverseList(temp1);
    //head->next=nullptr;
    temp1->next = head;
    return reverseListHead;
}

void dataReverse(ListNode *head)
{
    //li ri pass kro then unka data swap li++ ri--.
    //int li = 0, ri = size - 1;
    //while (li < ri) {
    //    Node leftNode = getNodeAt(li);
    //    Node rightNode = getNodeAt(ri);
    //    int temp = leftNode.data;
    //    leftNode.data = rightNode.data;
    //    rightNode.data = temp;
    //    li++;
    //    ri--;
    //}

    if (head == nullptr || head->next == nullptr)
        return;
    ListNode *mid = midNode(head);
    ListNode *newListHead = mid->next;
    mid->next = nullptr;

    newListHead = reverseList(newListHead);

    ListNode *curr1 = head;
    ListNode *curr2 = newListHead;

    while (curr1 != nullptr && curr2 != nullptr)
    {
        int temp = curr1->val;
        curr1->val = curr2->val;
        curr2->val = temp;

        curr1 = curr1->next;
        curr2 = curr2->next;
    }

    newListHead = reverseList(newListHead);
    mid->next = newListHead;
}

void againReorderlist(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return;

    ListNode *h1 = head;
    ListNode *h2 = head->next;

    ListNode *c1 = h1;
    ListNode *c2 = h2;

    while (c2 != nullptr && c2->next != nullptr)
    {
        ListNode *f1 = c2->next;

        c1->next = f1;
        c2->next = f1->next;

        c1 = c1->next;
        c2 = c2->next;
    }

    c1->next = nullptr;
    h2 = reverseList(h2);
    c1->next = h2;
}


//leetcode 25
ListNode *th = nullptr;
ListNode *tt = nullptr;
void addFirstNode(ListNode *node)
{
    if (th == nullptr)
    {
        th = node;
        tt = node;
    }
    else
    {
        node->next = th; //node->next=tt; it can be only for second node.
        th = node;
    }
}

int length(ListNode *node)
{
    if (node == nullptr)
        return 0;
    int size = 0;
    while (node != nullptr)
    {
        size++;
        node = node->next;
    }
    return size;
}

ListNode *reverseKGroup(ListNode *head, int k)
{
    if (head == nullptr || k == 1 || head->next == nullptr)
        return head;

    int size = length(head);
    ListNode *originalHead = nullptr, *originalTail = nullptr, *curr = head;
    // ListNode *forward = head->next; 
    while (size >= k)
    {
        int tempK = k;
        while (tempK-- > 0)
        {
            ListNode *forward=curr->next;
            // curr->next = nullptr; //pehle null krenge then add agr pehle add kra then usko null kra tou wo akeli node hi reh jaygi..can do without doing null too
            addFirstNode(curr);
            curr = forward;
            // if (forward != nullptr)
            //     forward = forward->next;
        }

        if (originalHead == nullptr)
        {
            originalHead = th;
            originalTail = tt;
        }
        else
        {
            originalTail->next = th;
            originalTail = tt;
        }
        th = nullptr;
        tt = nullptr;
        size -= k;
    }
    originalTail->next = curr;
    return originalHead;
}

//24
ListNode *swapPairs(ListNode *head)
{
    return reverseKGroup(head, 2);
}


public void kReverse(int k)
{
    int li = 0;
    while (li < this.size())
    {
        if (li + k - 1 < this.size())
            reverseDIKreverse(li, li + k - 1);
        li = li + k;
    }
}

public void kReverse2(int k)
{
    LinkedList prev = null;
    while (this.size() > 0)
    {
        LinkedList curr = new LinkedList();
        if (this.size() >= k)
        {
            for (int i = 0; i < k; i++)
            {
                int val = this.getFirst();
                this.removeFirst(); //yahan removeFirst mein this.size() kam horha hai.
                curr.addFirst(val);
            }
        }
        else
        {
            int s = this.size();
            for (int i = 0; i < s; i++) //cant write this.size()because i bhi bdrha hai aur size ghtrha h removeFirst ki wjh se
            {
                int val = this.getFirst();
                this.removeFirst();
                curr.addLast(val);
            }
        }

        if (prev == null)
            prev = curr;
        else
        {
            prev.tail.next = curr.head;
            prev.tail = curr.tail;
            prev.size += curr.size;
        } 
    }

    this.head = prev.head;
    this.tail = prev.tail;
    this.size = prev.size;
}

//leetcode 92
void addFirst(ListNode *node)
{
    if (th == nullptr)
        th = tt = node;
    else
    {
        node->next = th;
        th = node;
    }
}

ListNode *th=nullptr;  
ListNode *tt=nullptr;
ListNode *reverseBetween(ListNode *head, int m, int n)
{
    if (head == nullptr || head->next == nullptr || m == n)
        return head;
    int idx = 1;
    ListNode *prev = nullptr;
    ListNode *curr = head;

    while (idx < n)
    {
        while (idx >= m && idx <= n)
        {
            ListNode *forward = curr->next;
            // curr->next = nullptr; no need
            addFirstNode(curr);
            curr = forward;
            idx++;
        }
        if (idx > n)
        {
            if (prev != nullptr)   //[3,6]  handles mid and last
            {
                prev->next = th;
                tt->next = curr;
                return head;
            }
            else
            {
                tt->next = curr; //12345 mein 1,4 reverse...handles beginning
                return th;
            }
        }
        else
        {
            prev = curr;
            curr = curr->next;
            idx++;
        }
    }
    return head;
}

//ib
ListNode *reverseAlternateKnodes(ListNode *A, int B)   
{
    ListNode *curr = A;
    int size = 0;
    while (curr != nullptr)
    {
        size++;
        curr = curr->next;
    }

    if (size < B)
        return A;
    curr = A;
    bool skip = false;
    ListNode *ot = nullptr, *oh = nullptr;
    while (size >= B)
    {
        if (!skip)
        {
            int noOfTime = B;
            while (noOfTime-- > 0)
            {
                ListNode *f = curr->next;
                addFirst(curr);
                curr = f;
            }
            skip = true;

            if (oh == nullptr && ot == nullptr)
            {
                oh = th;
                ot = tt;
            }
            else
            {
                ot->next = th;
                ot = tt;
            }
            th = nullptr;
            tt = nullptr;
        }
        else
        {
            ot->next = curr;
            int jump = 0;
            while (jump < B - 1)
            {
                curr = curr->next;
                jump++;
            }

            ot = curr;
            curr = curr->next;
            skip = false;
        }
        size -= B;
    }
    ot->next = curr;
    return oh;
}



