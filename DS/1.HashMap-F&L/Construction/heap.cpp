#include <iostream>
#include <vector>
using namespace std;
class heap
{
private:
    vector<int> ownHeap;
    bool heapType;
    void constructHeap();
    void downHeapify(int pi,int n); //o(logn)
    void upHeapify(int val); //o(logn)

public:
    heap(int arr[], int size, bool heapType);
    int size();
    int isEmpty();
    int peek();       //o(1)
    int remove();
    void add(int ci);   //o(logn)
    int isCompare(int x, int y);
    void print();
    void heapSort();
};

int heap::size()
{
    return this->ownHeap.size();
}

int heap::isEmpty()
{
    return size() == 0;
}

int heap::peek()
{
    return this->ownHeap[0];
}

void heap::print()
{
    for (int ele : this->ownHeap)
        cout << ele << " ";
    cout << endl;
}

heap::heap(int arr[], int size, bool heapType)
{
    for (int i = 0; i < size; i++) //dont use sizeof here because when u pass array to a function it turns to a pointer pointing to the first element of the array hence size will be (int *)/(int) which is 1.
        this->ownHeap.push_back(arr[i]);
    this->heapType = heapType;
    constructHeap();
}

void heap::constructHeap()
{
    int sz = size()-1;     //idx of last node
    for (int n = size()/2-1; n >= 0; n--)
        downHeapify(n,sz);
}

void heap::downHeapify(int pi,int n)
{
    int maxidx = pi;
    int lci = 2 * pi + 1;
    int rci = 2 * pi + 2;

    // if (lci < size() && this->ownHeap[lci] > this->ownHeap[maxidx])
    //     maxidx = lci;
    // if (rci < size() && this->ownHeap[rci] > this->ownHeap[maxidx])
    //     maxidx = rci;

    if (lci <= n && isCompare(this->ownHeap[maxidx], this->ownHeap[lci]))
        maxidx = lci;
    if (rci <= n && isCompare(this->ownHeap[maxidx], this->ownHeap[rci]))
        maxidx = rci;

    if (maxidx != pi)
    {
        swap(this->ownHeap[maxidx], this->ownHeap[pi]);
        downHeapify(maxidx,n);
    }
}

int heap::isCompare(int x, int y)
{
    if (heapType)
        return x > y;   //in java comparison on basis of integer in lambda fn...here it is not lamda here it is simple comparison
    return x<y;
}

int heap::remove()
{
    int n = this->ownHeap[0];
    swap(this->ownHeap[0], this->ownHeap[size() - 1]);
    this->ownHeap.pop_back();
    downHeapify(0,size() - 1);
    return n;
}

void heap::add(int val)
{
    this->ownHeap.push_back(val);
    upHeapify(this->ownHeap.size() - 1);
}

void heap::upHeapify(int ci)
{
    int pi = (ci - 1) / 2;
    if (pi >= 0 && isCompare(this->ownHeap[pi],this->ownHeap[ci]))
    {
        swap(this->ownHeap[pi], this->ownHeap[ci]);
        upHeapify(pi);
    }
}

void heap::heapSort()
{
    int n=this->ownHeap.size()-1;
    int size=n;
    for(int i=0;i<=size;i++)
    {
        cout<<peek()<<" ";
        swap(ownHeap[0],ownHeap[n--]);
        downHeapify(0,n);
    }
    cout<<endl;
}

main()
{
    int arr[] = {10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 3};
    // bool heapType = true;        //min heap
    bool heapType = false;    //max heap
    int size = sizeof(arr) / sizeof(arr[0]);
    heap h(arr, size, heapType);

    h.print();
    // h.heapSort();
    h.remove();
    h.print();
    h.add(40);
    h.print();
}
