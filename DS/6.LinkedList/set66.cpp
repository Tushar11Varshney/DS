// LL has cycle
// Detect cycle
// intersection node (2)


//leetcode 141
bool hasCycle(ListNode *head)
{
    ListNode *slow = head;
    ListNode *fast = head;

    while (fast != nullptr && fast->next != nullptr)
    {
        fast = fast->next->next;
        slow = slow->next;

        if (fast == slow)
            return true;
    }
    return false;
}

//leetcode 142
ListNode *detectCycle(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return nullptr;
    ListNode *slow = head, *fast = head;

    while (fast != nullptr && fast->next != nullptr)
    {
        fast = fast->next->next;
        slow = slow->next;

        if (fast == slow)
            break;
    }

    if (fast != slow)
        return nullptr;
    slow = head;
    ListNode *ref = fast;
    int cycle = 0, cycleLen = 0, a = 0;
    while (fast != slow)
    {
        slow = slow->next;
        fast = fast->next;

        if (fast == ref)
        {
            cycle++;
            if (cycleLen == 0)
                cycleLen = a;
        }
        a++;
    }
    //tail length=a+1
    //m=cycle+1        //m no of rotation before meeting pt
    // A=C+(B+C)(m-1)
    //c=a-(cycleLen)*cycle
    //b=((a-c)/cycle)-c
    //cyclenLen=b+c
    return slow;
}

ListNode *getIntersectionNode(ListNode *headA, ListNode *headB)
{
    ListNode *first = headA;
    ListNode *second = headB;
    int size1 = 0, size2 = 0;
    while (first != nullptr)
    {
        size1++;
        first = first->next;
    }
    while (second != nullptr)
    {
        size2++;
        second = second->next;
    }
    first = headA;
    second = headB;
    if (size1 > size2)
    {
        for (int i = 0; i < (size1 - size2); i++)
        {
            first = first->next;
        }
    }
    else if (size1 < size2)
    {
        for (int i = 0; i < (size2 - size1); i++)
        {
            second = second->next;
        }
    }

    while (first != nullptr && second != nullptr)
    {
        if (first == second)
        {
            return first;
        }
        else
        {
            first = first->next;
            second = second->next;
        }
    }
    return nullptr;
}

//leetcode 160
ListNode *getIntersectionNodeRajnesshSir(ListNode *headA, ListNode *headB)
{
    if (headA == nullptr || headB == nullptr)
        return nullptr;

    ListNode *tail = headB;
    while (tail->next != nullptr)
        tail = tail->next;

    tail->next = headA;
    ListNode *ans = detectCycle(headB);
    tail->next = nullptr;
    return ans;
}

