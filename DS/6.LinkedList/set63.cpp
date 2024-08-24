// copy node with random ptr
// flatten a LL

//leetcode 138
class Node
{
public:
    int val;
    Node *next;
    Node *random;

    Node(int _val)
    {
        val = _val;
        next = NULL;
        random = NULL;
    }
};

void copyNode(Node *head)
{
    Node *curr = head;
    while (curr != nullptr)
    {
        Node *forward = curr->next;  //backup
        Node *newNode = new Node(curr->val);
        curr->next = newNode; //link
        newNode->next = forward;
        curr = forward; //move
    }
}

void setRandom(Node *head)
{
    Node *curr = head;
    while (curr != nullptr)
    {
        if (curr->random != nullptr)
            curr->next->random = curr->random->next;
        curr = curr->next->next;
    }
}

Node *extractLL(Node *head)
{
    Node *dummy = new Node(-1);
    Node *copycurr = dummy;
    Node *curr = head;

    while (curr != nullptr)
    {
        copycurr->next = curr->next;
        curr->next = curr->next->next;
        copycurr = copycurr->next;
        curr = curr->next;
    }

    return dummy->next;
}

Node *copyRandomList(Node *head)
{
    if (head == nullptr)
        return head;

    copyNode(head);
    setRandom(head);
    return extractLL(head);
}

//https://practice.geeksforgeeks.org/problems/flattening-a-linked-list/1
Node mergeTwoList(Node root1,Node root2)
    {
        Node dummy=new Node(-1);
        Node prev=dummy;
        
        while(root1!=null && root2!=null)
        {   
            if(root1.data<root2.data)
            {
                prev.bottom=root1;
                root1=root1.bottom;
            }
            else{
                prev.bottom=root2;
                root2=root2.bottom;
            }
            prev=prev.bottom;
        }
        prev.bottom=(root1!=null?root1:root2);
        return dummy.bottom;
    }
    
Node flatten(Node root)
{
    Node curr=root.next;
    Node mergelist=root;
    while(curr!=null)
    {
        mergelist=mergeTwoList(mergelist,curr);
        curr=curr.next;
    }
    return mergelist;
}

