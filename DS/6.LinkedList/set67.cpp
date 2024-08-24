// Merge two list(2-dummy, addLast)
// Merge k list(nk*k, nk*logK)
// Sort List(nlogn)

//leetcode 21
ListNode *mergeTwoLists(ListNode *l1, ListNode *l2)
{
    ListNode *prev = new ListNode(-1);
    ListNode *dummy = prev;

    ListNode *first = l1;
    ListNode *second = l2;

    while (first != nullptr && second != nullptr)
    {
        if (first->val < second->val)
        {
            prev->next = first;
            first = first->next;
        }
        else
        {
            prev->next = second;
            second = second->next;
        }
        prev = prev->next;
    }

    prev->next = (first != nullptr ? first : second);
    return dummy->next;

    // Node first = l1.head;   //original list must stay as they were...else we can use removeFirst
    //         Node second = l2.head;
    //         LinkedList l3 = new LinkedList();
    //         while(first!=null && second!=null)
    //         {
    //             if(first.data<second.data)
    //             {
    //                 l3.addLast(first.data);
    //                 first=first.next;
    //             }
    //             else{
    //                     l3.addLast(second.data);
    //                     second=second.next;
    //             }
    //         }
    //         while(first!=null)
    //         {
    //             l3.addLast(first.data);
    //             first=first.next;
    //         }
    //         while(second!=null)
    //         {
    //             l3.addLast(second.data);
    //             second=second.next;
    //         }
    //         return l3;
}

//nk^2 approach
ListNode *mergeKLists(vector<ListNode *> &lists)
{

    if (lists.size() == 0)
        return nullptr;

    ListNode *refList = nullptr;
    for (int i = 0; i < lists.size(); i++)
        refList = mergeTwoLists(refList, lists[i]);

    return refList;
}

//23
ListNode *mergeKLists(vector<ListNode *> &lists, int si, int ei)
{
    if (si == ei)
        return lists[si];

    int mid = (si + ei) / 2;
    ListNode *first = mergeKLists(lists, si, mid);
    ListNode *second = mergeKLists(lists, mid + 1, ei);

    return mergeTwoLists(first, second);
}

ListNode *mergeKLists(vector<ListNode *> &lists)
{

    if (lists.size() == 0)
        return nullptr;

    return mergeKLists(lists, 0, lists.size() - 1);
}

//148
ListNode *sortList(ListNode *head) 
{
    if (head == nullptr || head->next == nullptr)
        return head;
    ListNode *mid = midNode(head);
    ListNode *newHead = mid->next;
    mid->next = nullptr;

    ListNode *firstList=sortList(head);
    ListNode *secondList=sortList(newHead);
    
    return mergeTwoLists(firstList, secondList);
}


