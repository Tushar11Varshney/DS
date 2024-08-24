// kth node from middle
// middle of linked list(first, second in even length)
// Is LL palindrome
// Reorder List(backup, link, move) backup in loop/ outside loop

//gc
int kthNodefromMiddle(ListNode *A, int B)
{
    int size = 0;
    ListNode *curr = A;
    while (curr != nullptr)
    {
        size++;
        curr = curr->next;
    }
    int middleNode = (size / 2) + 1;
    int distanceFromBeg = middleNode - B;
    if (distanceFromBeg <= 0)
        return -1;

    curr = A;
    for (int jump = 1; jump < distanceFromBeg; jump++)
        curr = curr->next;
    return curr->val;
}


//leetcode 876
// for even list mein dusra waala mid
ListNode *middleNode(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *fast = head, *slow = head;
    while (fast != nullptr && fast->next != nullptr)
    {
        fast = fast->next->next;
        slow = slow->next;
        // if(fast->next==nullptr)break; fast=fast->next; //agr ek ek se speed bdayi..tou fast!=null ni lgana pdega 4 node se sochlo
    }
    return slow;
}

ListNode *midNode(ListNode *head) //_IfEvenListmeinPehlaWaalaChahiye
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *fast = head, *slow = head;
    while (fast->next != nullptr && fast->next->next != nullptr)
    {
        fast = fast->next->next;  //agr 1 se then check f->n->n=null and no need of 1st condn 
        slow = slow->next;
    }
    return slow;
}

//leetcode 234
bool isPalindrome(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return true;
    ListNode *mid = midNode(head);
    ListNode *newListHead = mid->next;
    mid->next = nullptr;

    newListHead = reverseList(newListHead);

    ListNode *curr1 = head;
    ListNode *curr2 = newListHead;

    bool res = true;
    while (curr1 != nullptr && curr2 != nullptr)
    {
        if (curr1->val != curr2->val)
        {
            res = false;
            break;
        }

        curr1 = curr1->next;
        curr2 = curr2->next;
    }

    //It is good to unchange the changes we have done with the data.
    newListHead = reverseList(newListHead);
    mid->next = newListHead;
    return res;
}


//leetcode 143
void reorderList(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return;
    ListNode *mid = midNode(head);
    ListNode *secondHalfHead = mid->next;
    mid->next = nullptr;

    secondHalfHead = reverseList(secondHalfHead);

    ListNode *c1 = head;
    ListNode *c2 = secondHalfHead;
    // ListNode *forward1 = c1->next;
    // ListNode *forward2 = c2->next;
    while (c1 != nullptr && c2 != nullptr)
    {
        ListNode *forward1 = c1->next; //backup
        ListNode *forward2 = c2->next;

        c1->next = c2; //links
        c2->next = forward1;

        c1 = forward1; //move
        c2 = forward2;

        // if(forward1!=nullptr)forward1=forward1->next;  //ya fir if(c1!=nullptr)forward1=c1->next
        // if(forward2!=nullptr)forward2=forward2->next;
    }
} 

