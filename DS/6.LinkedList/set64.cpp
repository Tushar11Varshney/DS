// delete node(head not given)
// delete duplicate(2ptr, removeFirst then addLast)
// delete all duplicates(remove from beginning then 2 ptr)
// remove node with specific value(removeFirst then 2 ptr)
// remove nth from end(getNodeAt, speed)

//It is guaranteed that the node to be deleted is not a tail node in the list.
void deleteNode4(ListNode *node) //sir
{
    ListNode *temp = node->next;
    node->val = temp->val;
    node->next = temp->next;
    temp->next = nullptr;
}

//leetcode 83
// ‘this’ pointer is not available in static member functions as static member functions can be called without any object (with class name).
ListNode *deleteDuplicates(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *first = head;
    ListNode *second = head->next;

    while (second != nullptr)
    {
        if (first->val == second->val)
        {
            first->next = second->next;
            second = first->next;
        }
        else
        {
            first = second;
            second = second->next;
        }
    }
    return head;

    // LinkedList res=new LinkedList();
    //         while(this.size()>0)    //linear time bcz har node ko ekbaar visit kiya
    //         {
    //                 int val=this.getFirst();
    //                 this.removeFirst();  //yahan pr extra space sirf 12 byte(h,t,s) lii joki 1lakh size waali list kelie bhi sirf 12 hi rhegi.yahan space constant because ek tym pr dono node zinda ni hai...purani list mein hum delete krdete hai fir nayi mein daal dete hai tou space balance.
    //             if(res.size()==0 || res.getLast()!=val)
    //             {
    //                 res.addLast(val);
    //             }
    //         }

    //         this.head=res.head;   //yahan pr ye isliye kiya kyunki platform pr vo purani this list hi display krrhe hai.this=res nhi krskte because this is a read only..we can change its properties.
    //         this.tail=res.tail;
    //         this.size=res.size;
}

// deleteDuplicate2-read code..isme waise socho pehle 1122 test case kelie then 2 3 4 4 4
//leetcode 82
ListNode *deleteDuplicates(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *temp = head->next;

    while (head != nullptr && temp != nullptr && head->val == temp->val) //[1122]
    {
        int val = head->val;
        temp = head;
        while (temp != nullptr && temp->val == val)
        {
            head = head->next;
            temp = temp->next;
        }
        if (head != nullptr)
            temp = head->next;
    }

    if (head != nullptr && head->next != nullptr)
    {
        ListNode *first = head;
        ListNode *second = head->next;
        ListNode *prev;
        while (second != nullptr)
        {
            if (first->val == second->val)
            {
                while (second->next != nullptr && (second->next)->val == first->val)
                {
                    second = second->next;
                }

                prev->next = second->next;
                first = prev->next;
                if (first != nullptr)
                    second = first->next;
                else
                    second = nullptr; //122
            }
            else
            {
                prev = first;
                first = second;
                second = second->next;
            }
        }
    }
    return head;
}

//leetcode 203
ListNode *removeFirstNode(ListNode *head)
{
    ListNode *temp = head;
    head = head->next;

    temp->next = nullptr;
    return head;
}

ListNode *removeElements(ListNode *head, int val)
{
    while (head != nullptr && head->val == val)
    {
        head = removeFirstNode(head);
    }
    if (head == nullptr)
        return head;
    ListNode *prev = head;
    ListNode *nxt = prev->next;  //prev next just like first-second
    while (nxt != nullptr)
    {
        if (nxt->val == val)
        {
            nxt = nxt->next;
            prev->next = nxt;
        }
        else
        {
            prev = nxt;
            nxt = nxt->next;
        }
    }
    return head;
}

//leetcode 19
ListNode *getNodeAt(ListNode *head, int idx)
{
    ListNode *temp = head;
    for (int i = 0; i < idx; i++)
        temp = temp->next;
    return temp;
}

ListNode *removeNthFromEnd(ListNode *head, int n)
{
    if (head == nullptr || head->next == nullptr)
        return nullptr; //return nullptr because question mein given hai ki agr ek bhi element hoga tou n 1 hoga
    ListNode *curr = head;
    int size = 0;
    while (curr != nullptr)
    {
        size++;
        curr = curr->next;
    }
    if (size - n - 1 < 0) //testCase [1,2] 2
        head = head->next;
    else
    {
        ListNode *prev = getNodeAt(head, size - n - 1); // n is 1 based index
        ListNode *toDelete = getNodeAt(head, size - n);
        prev->next = toDelete->next;
        toDelete->next = nullptr;
    }
    return head;
}

ListNode *removeNthFromEndRajneeshSir(ListNode *head, int n)
{
    if (head == nullptr || head->next == nullptr)
        return nullptr;

    ListNode *c1 = head;
    ListNode *c2 = head;
    ListNode *temp = c1;
    while (n-- > 0)
        c2 = c2->next;

    if (c2 == nullptr)
        head = head->next;
    else
    {
        while (c2->next != nullptr)
        {
            c1 = c1->next;
            c2 = c2->next;
        }
        temp = c1->next;
        c1->next = c1->next->next;
    }
    temp->next = nullptr;
    return head;
}

