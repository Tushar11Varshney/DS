import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class set2 {
// 1. Stack to Queue(remove/add efficient)
// 2. Queue to Stack(remove/add efficient)
// 3. Reverse FIRST k queue element
// 4. Minstack(2){1-initially ms 1e9}

//4
// TwoStack                                               {full condn same}
// Stack with increment operation                         (list as stack)
// Stack(Normal and Dynamic)
// Queue(Normal and Dynamic)                        //make,copy,change ref and variable

// Adapter Set
// 232
class MyQueue {    //remove eff
    Stack<Integer> mainS;
    Stack<Integer> helperS;

    public MyQueue() {
        mainS = new Stack<>();
        helperS = new Stack<>();
    }

    public void push(int val) {
        while (mainS.size() > 0)
            helperS.add(mainS.pop());

        mainS.add(val);

        while (helperS.size() > 0)
            mainS.add(helperS.pop());
    }

    public int pop() {
        return mainS.pop();
    }

    public int peek() {
        return mainS.peek();
    }

    public boolean empty() {
        if (mainS.size() == 0)
            return true;
        return false;
    }
}

public static class StackToQueueAdapter {
    Stack<Integer> mainS;
    Stack<Integer> helperS;

    public StackToQueueAdapter() {
        mainS = new Stack<>();
        helperS = new Stack<>();
    }

    int size() {
        return mainS.size();
    }

    void add(int val) {
        while (mainS.size() > 0)
            helperS.add(mainS.pop());

        mainS.add(val);

        while (helperS.size() > 0)
            mainS.add(helperS.pop());
    }

    int remove() {
        if (size() == 0) {
            System.out.println("Queue underflow");
            return -1;
        }
        return mainS.pop();
    }

    int peek() {
        if (size() == 0) {
            System.out.println("Queue underflow");
            return -1;
        }
        return mainS.peek();
    }

    int remove2() { // push efficient
        if (size() == 0) {
            System.out.println("Queue underflow");
            return -1;
        } else {
            while (mainS.size() != 1)
                helperS.push(mainS.pop());

            int val = mainS.pop();

            while (helperS.size() != 0)
                mainS.push(helperS.pop());

            return val;
        }
    }

    int peek2() { // push efficient
        if (size() == 0) {
            System.out.println("Queue underflow");
            return -1;
        } else {
            while (mainS.size() != 1)
                helperS.push(mainS.pop());

            int val = mainS.peek();
            while (helperS.size() != 0)
                mainS.push(helperS.pop());

            return val;
        }
    }
}

// 225
class MyStack {
    Queue<Integer> mainQ;
    Queue<Integer> helperQ;

    /** Initialize your data structure here. */
    public MyStack() {
        mainQ = new ArrayDeque<>();
        helperQ = new ArrayDeque<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        while (mainQ.size() > 0)
            helperQ.add(mainQ.remove());

        mainQ.add(x);

        while (helperQ.size() > 0)
            mainQ.add(helperQ.remove());
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return mainQ.remove();
    }

    /** Get the top element. */
    public int top() {
        return mainQ.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return mainQ.size() == 0;
    }
}

public static class QueueToStackAdapter {
    Queue<Integer> mainQ;
    Queue<Integer> helperQ;

    public QueueToStackAdapter() {
        mainQ = new ArrayDeque<>();
        helperQ = new ArrayDeque<>();
    }

    int size() {
        return mainQ.size();
    }

    void push(int val) { // pop efficient bnane kelie
        while (mainQ.size() > 0)
            helperQ.add(mainQ.remove());

        mainQ.add(val);

        while (helperQ.size() > 0)
            mainQ.add(helperQ.remove());
    }

    int pop() {
        if (size() == 0) {
            System.out.println("Stack underflow");
            return -1;
        }
        return mainQ.remove();
    }

    int top() {
        if (size() == 0) {
            System.out.println("Stack underflow");
            return -1;
        }
        return mainQ.peek();
    }

    int pop2() { // push efficient
        if (size() == 0) {
            System.out.println("Stack underflow");
            return -1;
        }
        while (mainQ.size() != 1)
            helperQ.add(mainQ.remove());

        int val = mainQ.remove();

        while (helperQ.size() != 0)
            mainQ.add(helperQ.remove());

        return val;
    }

    int top2() { // push efficient
        if (size() == 0) {
            System.out.println("Stack underflow");
            return -1;
        }
        while (mainQ.size() != 1)
            helperQ.add(mainQ.remove());

        int val = mainQ.remove();   //bcz queue mein ek trf se addn ek trf se deln
        helperQ.add(val);

        while (helperQ.size() != 0)
            mainQ.add(helperQ.remove());

        return val;
    }
}

//https://practice.geeksforgeeks.org/problems/reverse-first-k-elements-of-queue/1#
queue<int> reverseFirstKofQueue(queue<int> que, int k)
{
    stack<int> st;
    queue<int> helper;
    int size = que.size();
    for (int i = 0; i < size; i++)
    {
        if (i < k)
        {
            st.push(que.front());
            que.pop();
        }
        else
        {
            helper.push(que.front());
            que.pop();
        }
    }

    for (int i = 0; i < k; i++)
    {
        que.push(st.top());
        st.pop();
    }

    while (helper.size() != 0)
    {
        que.push(helper.front());
        helper.pop();
    }

    return que;
}



}

public static class MinStack {
    Stack<Integer> allData;
    Stack<Integer> minData;

    public MinStack() {
        allData = new Stack<>();
        minData = new Stack<>();
        minData.push((int) 1e9);   //use in push if dont want to use then in push check if minstack size>0 before comparing and also agr minstack size 0 then push
    }

    int size() {
        return allData.size();
    }

    void push(int val) {
        if (val <= minData.peek()) { // = pr bhi daalenge maanlo push 2,2 then agr pop krenge and minstack se bhi htaenge aur agr ek hi baar daala hoga tou neeche hoga (int)1e9 stack underflow though stack has one more 2.
            minData.push(val);
        }

        allData.push(val);
    }

    int pop() {
        if (size() == 0) {
            System.out.println("Stack underflow");
            return -1;
        }
        int val = allData.pop();
        if (val == minData.peek())
            minData.pop();
        return val;
    }

    int top() {
        if (size() == 0) {
            System.out.println("Stack underflow");
            return -1;
        }
        return allData.peek();
    }

    int min() {
        if (minData.peek() == (int) 1e9) {  //size()==0
            System.out.println("Stack underflow");
            return -1;
        }
        return minData.peek();
    }
}

public static class MinStack2 {
    Stack<Integer> data;
    int min;

    public MinStack2() {
        data = new Stack<>();
    }

    int size() {
        return data.size();
    }

    void push(int val) {
        if (size() == 0) {
            min = val;  // is time pr hi min val brabar honge bs
            data.push(val);
        } else if (val < min) {
            data.push(val + val - min); // phle push then min update
            min = val;
        } else
            data.push(val);
    }

    int pop() {
        if (size() == 0) {
            System.out.println("Stack Underflow");
            return -1;
        } else if (data.peek() >= min) { 
            return data.pop();
        } else {
            int min_YahanBadlaTha = min;
            min = 2 * min - data.pop();  //2*min-(2*val-min)
            return min_YahanBadlaTha;
        }
    }

    int top() {
        if (size() == 0) {
            System.out.println("Stack Underflow");
            return -1;
        } else if (data.peek() >= min) {
            return data.peek();
        } else {
            return min;
        }
    }

    int min() {
        if (size() == 0) {
            System.out.println("Stack Underflow");
            return -1;
        }
        return min;
    }
}

//155
class MinStack
{
    public:
        stack<long> st; //-2^31 <= val <= 2^31 - 1
        long minEle = 0;
        MinStack()
        {
        }

        void push(int val)
        {
            if (st.size() == 0)
            {
                st.push(val);
                minEle = val;
                return;
            }

            if (val >= minEle)
            {
                st.push(val);
            }
            else
            {
                //why cant store val-m
                //["MinStack","push","push","push","getMin","pop","top","getMin"]
                //[[],[-2],[0],[-3],[],[],[],[]]
                // val+val-minEle..ye ftega kyunki val integer h ..ya tou long val1=val krdo
                //overflow: -2147483648 + -2147483648 cannot be represented in type 'int'
                st.push((val - minEle) + val);
                minEle = val;
            }
        }

        void pop()
        {
            if (st.top() < minEle)
            {
                minEle = 2*minEle - st.top();
            }

            st.pop();
        }

        int top()
        {
            if (st.top() < minEle)
                return (int)minEle;

            return (int)st.top();
        }

        int getMin()
        {
            return (int)minEle;
        }
};

