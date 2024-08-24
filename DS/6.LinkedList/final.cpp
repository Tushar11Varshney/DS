#include <iostream>
#include <vector>
#include <math.h>
#include <stack>
using namespace std;
struct ListNode
{
    int val;
    ListNode *next;
    ListNode()
    {
        val = 0;
        next = nullptr; 
    }
    ListNode(int x)
    {
        val = x;
        next = nullptr;
    }
    ListNode(int x, ListNode *next)
    {
        val = x;
        this->next = next;
    }
};
/*
agar use krenge midNode tou stack overflow krjaygi because mergeSort mein hum tail aur head pass krenge and in midNode and mergeTwoSortedLists mein hum null se checking krrhe hain
agr null ke respect mein tou ek hi mid baar baar pass.

//agr global allow ni ho then pass array of two node for tt and th.cant do by passing listNode references in fn because they are made on stack.

gyaan-1.big integer jaisa kaam array mein ni hoskta na ki string mein kyunki string bhi character array hi hai...so for big integer itni continuous memory ni hai..then linkedlist ka concept aaya fragmented memory ko use kro...
3.aur recursion se isliye ni krte ll ke qs because stack bhi continuous memory leti hai aur recursion stack pr chlta hai.
always socho basic se addfirst,addlast,removefirst in qs of ll.

//code ko humesha function mein todo..aur agr if else mein same hi kaam hona hai bs value alg alg use so use fn..and in merge two sorted list mein pehli node kelie..alg se handle krne ki zrurat ni hai...addLast fn ka use kro...agr alg se handle krne ki koshish kri then agr ek list bhi null hui check bdjaenge....aur jo fn link bnayga usme null exception aayga kyunki pehla element set hi ni hopaega agr list null hui koi bhi aur check shi se ni lgaye tou
*/

// 1290
int getDecimalValue(ListNode *head)
{
    ListNode *curr = head;
    int size = 0, decimalVal = 0;
    while (curr != nullptr)
    {
        size++;
        curr = curr->next;
    }
    curr = head;
    while (curr != nullptr)
    {
        decimalVal += curr->val * ((int)pow(2, (size - 1))); //size-1=placevalue
        size--;
        curr = curr->next;
    }
    return decimalVal;
}


//61
ListNode *rotateRight(ListNode *head, int k)
{
    if(head==nullptr || head==nullptr)return head;

    ListNode *curr = head;
    int size = 0, jump;
    while (curr != nullptr)
    {
        size++;
        curr = curr->next;
    }

    k = (k % size + size) % size;
    if (k == 0)
        return head;

    curr = head;
    jump = size - k - 1;
    while (jump-- > 0)
        curr = curr->next;
    ListNode *forward = curr->next;
    ListNode *newHead = forward;
    curr->next = nullptr;
    while (forward->next != nullptr)
        forward = forward->next;
    forward->next = head;

    return newHead;
}

// display ll reverse-faith ye rkho ki aage ki list pass krdo aur waapsi mein vo print ho kr aajaygi.
public void displayReverseHelper(Node node)
{
    if (node != null)
    {
        displayReverseHelper(node.next);
        System.out.print(node.data + " ");
    }
}


public int kthFromLast(int k)
{
    // return getAt(size-k-1); //given in constraint size parameter not to use.
    // reverseDI();
    // return getAt(k); //works only 1 time

    Node fast = head, slow = head;
    for (int i = 0; i < k; i++)
        fast = fast.next;

    while (fast != tail)
    {
        fast = fast.next;
        slow = slow.next;
    }
    return slow.data;
}

//1721
ListNode *swapNodes(ListNode *head, int k)
{ //by me
    ListNode *forward = head;
    ListNode *back = head;
    ListNode *curr = head;
    int fjump = 0, bjump = 0, size = 0;
    while (curr != nullptr)
    {
        size++;
        curr = curr->next;
    }
    if (k - 1 == size - k)
        return head;
    while (fjump < k - 1)
    {
        forward = forward->next;
        fjump++;
    }
    while (bjump < size - k)
    {
        back = back->next;
        bjump++;
    }
    int temp = forward->val;
    forward->val = back->val;
    back->val = temp;
    return head;
}

ListNode *swapNodes(ListNode *head, int k)
{
    ListNode *p1 = head, *p2 = head, *kth = nullptr;
    while (--k > 0)
        p1 = p1->next;

    kth = p1;
    p1 = p1->next;

    while (p1 != nullptr)
    {
        p1 = p1->next;
        p2 = p2->next; //now p2 reaches the kth node from end
    }

    swap(kth->val, p2->val);
    return head;
}


//445
void addFirst(int val, ListNode *&head)
{
    ListNode *temp = new ListNode();
    temp->val = val;
    if (head == nullptr)
        head = temp;
    else
    {
        temp->next = head;
        head = temp;
    }
}

int addListHelper(ListNode *&one, int pv1, ListNode *&two, int pv2, ListNode *&res)
{
    if (one == nullptr && two == nullptr)
        return 0;
    int carry = 0, data = 0;
    if (pv1 > pv2)
    {
        carry = addListHelper(one->next, pv1 - 1, two, pv2, res);
        data = one->val + carry;
    }
    else if (pv2 > pv1)
    {
        carry = addListHelper(one, pv1, two->next, pv2 - 1, res);
        data = two->val + carry;
    }
    else
    {
        carry = addListHelper(one->next, pv1 - 1, two->next, pv2 - 1, res);
        data = one->val + two->val + carry;
    }
    int digit = data % 10;
    addFirst(digit, res);
    int digitCarry = data / 10;

    return digitCarry;
}

ListNode *addTwoNumbers2(ListNode *l1, ListNode *l2)
{
    ListNode *first = l1;
    ListNode *second = l2;
    ListNode *res = nullptr;
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
    first = l1, second = l2;
    int carry = addListHelper(first, size1, second, size2, res);
    if (carry > 0)
        addFirst(carry, res);
    return res;
}

//leetcode 2-Here linked list stored in reverse order so reverse first then normal..then reverse result
int size(ListNode *l1)
{
    ListNode* curr=l1;
    int s=0;
    while(curr!=nullptr)
    {
        s++;
        curr=curr->next;
    }
    return s;
}

ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
    if(size(l2)>size(l1))return addTwoNumbers(l2,l1);
    // ListNode* dummy=new ListNode(-1);
    // ListNode* prev=dummy;
    
    int carry=0;
    ListNode *first=l1,*prev=nullptr;
    while(first!=nullptr || carry!=0)
    {
        int sum=carry+ (first!=nullptr?first->val:0) + (l2!=nullptr?l2->val:0);
        int digit=sum%10;
        carry=sum/10;
        
        if(first!=nullptr)
        {
            first->val=digit;
            prev=first;
        }
        else{
            ListNode* temp=new ListNode(digit);
            prev->next=temp;
        }
        
        if(first!=nullptr)first=first->next;
        if(l2!=nullptr)l2=l2->next;
    }
    return l1;
}

//SubtractTwoNum
public static ListNode subtractTwoNumbers(ListNode l1, ListNode l2)
{
    if (l2 == null)
        return l1;
    else if (l1 == null)
    {
        l2.val = -l2.val;
        return l2;
    }

    l1 = reverseList(l1);
    l2 = reverseList(l2);
    ListNode dummy = new ListNode(-1);
    ListNode prev = dummy;
    while (l1 != null)
    {
        int difference = l1.val - (l2 != null ? l2.val : 0);
        ListNode newNode = new ListNode(difference);
        if (difference < 0)
            newNode.val += 10;
        prev.next = newNode;
        prev = prev.next;

        l1 = l1.next;
        if (l1 != null && difference < 0)  //for this question subtraction result always positive
            l1.val -= 1;
        if (l2 != null)
            l2 = l2.next;
    }
    return (reverseList(dummy.next));
}

//Multiply
//Sum 2 list
public static void sumTwoList(ListNode l1, ListNode l2)
{
    int carry = 0;
    while (l1 != null || carry != 0)
    {
        int sum = carry + (l1 != null ? l1.val : 0) + (l2.next != null ? l2.next.val : 0);

        int digit = sum % 10;
        carry = sum / 10;

        if (l2.next != null)
            l2.next.val = digit;
        else l2.next = new ListNode(digit);

        if (l1 != null)
            l1 = l1.next;
        l2 = l2.next;
    }
}

//multiplication in ll
public static ListNode multiplyWithoneDigit(ListNode l1, int digit)
{
    ListNode dummy = new ListNode(-1);
    ListNode prev = dummy;
    int carry = 0;
    while (l1 != null || carry != 0)
    {
        int sum = carry + (l1 != null ? l1.val : 0) * digit;
        carry = sum / 10;
        int dig = sum % 10;

        ListNode newNode = new ListNode(dig);
        prev.next = newNode;
        prev = prev.next;

        if (l1 != null)
            l1 = l1.next;
    }

    return dummy.next;
}

public static ListNode multiplyTwoLL(ListNode l1, ListNode l2)  
{
    l1 = reverseList(l1);
    l2 = reverseList(l2);

    ListNode dummy = new ListNode(-1);
    ListNode prev = dummy;
    while (l2 != null)
    {
        ListNode product = multiplyWithoneDigit(l1, l2.val);
        l2 = l2.next;
        sumTwoList(product, prev);
        prev= prev.next;
    }

    return reverseList(dummy.next);
}


//1019
vector<int> nextLargerNodes(ListNode *head)
{
    ListNode *curr = head;
    int size = 0, count = 0;
    while (curr != nullptr)
    {
        curr = curr->next;
        size++;
    }
    vector<int> ans(size, 0);
    stack<pair<int, int>> st;
    curr = head;
    while (curr != nullptr)
    {
        while (st.size() != 0 && st.top().first < curr->val)
        {
            pair<int, int> p = st.top();
            st.pop();

            ans[p.second] = curr->val;
        }
        st.push({curr->val, count});
        count++;
        curr = curr->next;
    }

    return ans;
}

// 817
public int numComponents(ListNode head, int[] G)
{
    Set<Integer> hs = new HashSet<>();
    for (int ele : G)
        hs.add(ele);

    ListNode prev = null, curr = head;
    int newComponents = 0;

    while (curr != null)
    {
        if (prev == null)
        {
            if (hs.contains(curr.val))
                newComponents++;
        }
        else if (!hs.contains(prev.val) && hs.contains(curr.val))
            newComponents++;

        prev = curr;
        curr = curr.next;
    }

    return newComponents;
}

// 147
ListNode *insertionSortList(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *dummy = new ListNode(-5001);
    ListNode *curr = head;
    while (curr != nullptr)
    {
        ListNode *f = curr->next;
        ListNode *p = dummy;
        while (p->next != nullptr && p->next->val < curr->val)
            p = p->next;

        curr->next = p->next;
        p->next = curr;
        curr = f;
    }

    return dummy->next; 
}

//725
vector<ListNode*> splitListToParts(ListNode* head, int k) {
    vector<ListNode*>ans(k);
    if(head==nullptr)return ans;
    
    int size=0;
    ListNode *curr=head;
    while(curr!=nullptr)
    {
        size++;curr=curr->next;
    }
    int exp=size%k,i=0;  //extra parts
    int ep=size/k;  //equal parts
    while(k-->0)
    {
        int tt=ep;
        if(exp>0){
            tt++;exp--;
        }
        
        ListNode *node1=head,*node2=head;
        while(tt-->1)
            node2=node2->next;
        
        ListNode *f=node2->next;
        node2->next=nullptr;
        ans[i++]=node1;
        head=f;
        if(head==nullptr)return ans;
    }
    
    return ans;
}

