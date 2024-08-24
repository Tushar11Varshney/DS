#include <iostream>
#include <queue>
using namespace std;

void set1(vector<int> &arr)      //sorting using pq
{
    // priority_queue<int> pq; // maxPQ
    priority_queue<int, vector<int>, greater<int>> pq; // minPQ

    for(int ele : arr)
        pq.push(ele);
    while (pq.size() != 0)
    {
        cout << pq.top() << " ";
        pq.pop();
    }
}

class pair_   //Code looks more clean than array and used when want to compare class members. 
{
public:
    int val1 = 0;
    int val2 = 0;

    pair_(int val1, int val2)
    {
        this->val1 = val1;
        this->val2 = val2;
    }
};

struct comp          //use comparable when you have a fixed comparising process and use comparator when you dont have a fixed comparising process.
{
public:
    bool operator()(pair_ const &p, pair_ const &q) const   //& used so that we can pass address as it is fast.const lgane se jab tak ye comparison chlrha hai...uski value change ni hoskti...multithreading ke jariye change hoskti hai.
    {
        // return p.val1 > q.val1; // minPQ.
        return p.val1 < q.val1;  // maxPQ.
    }
};

void set2(vector<vector<int>> &arr)
{
    priority_queue<pair_, vector<pair_>, comp> pq;
    for (vector<int>ele : arr)
        pq.push(pair_(ele[0], ele[1]));
    while (pq.size() != 0)
    { 
        cout << pq.top().val1 << " ";
        pq.pop();
    }
}

int main()
{
    // vector<int> arr{10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13};
    vector<vector<int>> arr = {{10, 20}, {30, -2}, {-3, -4}, {5, 6}, {7, 8}, {9, 22}, {11, 13}};
    // set1(arr);
    set2(arr);
    return 0;
}
