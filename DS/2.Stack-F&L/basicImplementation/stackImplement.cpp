#include <iostream>
#include <vector>

using namespace std;

class stack
{
private:
    vector<int> arr;

    // int *arr2;
    // int maxSize;

    int tos;          // top of stack
    int elementCount; // total elements present in stack.

    void intializeValues(int size)
    {
        // this->arr2 = new int[size];
        // this->maxSize = size;

        this->arr.resize(size, 0);
        // this->arr.resize(n, 0);
        // If the given value of n is less than the size at present then extra elements are removed.
        // If n is more than current size of container then upcoming elements are appended at the end(default-0)
        this->tos = -1;
        this->elementCount = 0;
    }

    int capacity()
    {
        return this->arr.size();
        // return this->maxSize;
    }

public:
    stack()
    {
        intializeValues(10);
    }

    stack(int size)
    {
        intializeValues(size);
    }

    int size()
    {
        return this->elementCount;
    }

    bool isEmpty()
    {
        return this->elementCount == 0;
    }

    void push(int data)
    {
        this->arr[++this->tos] = data;
        this->elementCount++;
    }

    int peek()
    {
        return this->arr[this->tos];
    }

    int pop()
    {
        int rv = this->arr[this->tos];
        this->arr[this->tos--] = 0;
        this->elementCount--;

        return rv;
    }
};

int main()
{
    stack st;
    st.push(10);
    cout << st.peek() << endl;
    return 0;
}