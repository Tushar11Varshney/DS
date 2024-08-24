import java.util.*;


// Evaluate RPN
// Infix Evaluation(number single digit(w/o ( ) ) or multiple digit)   //Num vs Char
// Infix conversion to prefix postfix              //stack types
// Postfix/prefix eval and conversion   //no ()


public class stack {

    //ib and pep(duplicate bracket)
// https://www.interviewbit.com/problems/redundant-braces/
int redundantBraces(string A)
{
    stack<char> st;
    for (int i = 0; i < A.length(); i++)
    {
        if (A[i] == '(' || A[i] == '+' || A[i] == '*' || A[i] == '/' || A[i] == '-')
            //if (ch != ')')  ye operand bhi daaldega  
            st.push(A[i]);
        else if (A[i] == ')')
        {
            if (st.top() == '(')
                return 1;

            while (st.top() != '(')
                st.pop();
            st.pop();
        }
    }
    return 0;
}

    //1190
string reverseParentheses(string s)
{
    int i = 0;
    stack<char> st;
    string ans = "", inBracket = "";

    for (; i < s.length(); i++)
    {
        char ch = s[i];
        if (ch == '(')
            st.push(ch);
        else if (ch >= 97 && ch <= 122)
        {
            if (st.size() == 0)
                ans += ch;
            else st.push(ch);
        }
        else
        {
            while (st.top() != '(')
            {
                inBracket += st.top();
                st.pop();
            }
            st.pop();
            if (st.size() == 0)
                ans += inBracket;
            else
            {
                for (int j = 0; j < inBracket.length(); j++)
                    st.push(inBracket[j]);
            }
            inBracket = "";
        }
    }

    return ans;
}

public static class TwoStack {
    int[] data;
    int tos1;
    int tos2;

    public TwoStack(int cap) {
        data = new int[cap];
        tos1 = -1;
        tos2 = data.length;
    }

    int size1() {
        return tos1 + 1;
    }

    int size2() {
        return data.length - tos2;
    }

    void push1(int val) {
        if (tos1 + 1 == tos2) {
            System.out.println("Stack overflow");
        } else {
            tos1++;
            data[tos1] = val;
        }
    }

    void push2(int val) {
        if (tos1 + 1 == tos2) {
            System.out.println("Stack overflow");
        } else {
            tos2--;
            data[tos2] = val;
        }
    }

    int pop1() {
        if (size1() == 0) {
            System.out.println("Stack underflow");
            return -1;
        } else {
            return data[tos1--];
        }
    }

    int pop2() {
        if (size2() == 0) {
            System.out.println("Stack underflow");
            return -1;
        } else {
            return data[tos2++];
        }
    }

    int top1() {
        if (size1() == 0) {
            System.out.println("Stack underflow");
            return -1;
        } else
            return data[tos1];
    }

    int top2() {
        if (size2() == 0) {
            System.out.println("Stack underflow");
            return -1;
        } else
            return data[tos2];
    }
}

// 1381-design stack with increment oprn
class CustomStack_ {

    int maxSize = 0;
    List<Integer> st;

    public CustomStack_(int maxSize) {
        st = new ArrayList<>();
        this.maxSize = maxSize;
    }

    public void push(int x) {
        if (st.size() < maxSize) {
            st.add(x);
        }
    }

    public int pop() {
        if (st.size() == 0)
            return -1;
        return st.remove(st.size() - 1);
    }

    public void increment(int k, int val) {
        for (int i = 0; i < Math.min(k, st.size()); i++) {
            st.set(i, st.get(i) + val); 
        }
    }
}

public static class CustomStack {
    int[] data;
    int tos;

    public CustomStack(int cap) {
        data = new int[cap];
        tos = -1;
    }

    int size() {
        return tos + 1;
    }

    void display() {
        if (size() == 0) {
            System.out.println();
            return;
        } else {
            int top = tos;
            while (top != -1) {
                System.out.print(data[top--] + " ");
            }
            System.out.println();
        }
    }

    void push(int val) {
        // if(size()==data.length)
        // {
        // System.out.println("Stack overflow");
        // }
        if (size() == data.length) // dynamic stack
        {
            int[] newData = new int[data.length * 2]; 
            tos = -1;
            for (int i = 0; i < data.length; i++) {
                tos++;
                newData[tos] = data[i];
            }
            tos++;
            newData[tos] = val;
            data = newData;
        } else {
            tos++;
            data[tos] = val;
        }
    }

    int pop() {
        if (size() == 0) {
            System.out.println("Stack underflow");
            return -1;
        } else {
            return data[tos--];
        }
    }

    int top() {
        if (size() == 0) {
            System.out.println("Stack underflow");
            return -1;
        } else {
            return data[tos];
        }
    }
}

public static class CustomQueue {
    int[] data;
    int front;
    int size;

    public CustomQueue(int cap) {
        data = new int[cap];
        front = 0;
        size = 0;
    }

    int size() {
        return size;
    }

    void display() {
        for (int i = 0; i < size; i++) {
            int idx = (front + i) % data.length;
            System.out.print(data[idx] + " ");
        }
        System.out.println();
    }

    void add(int val) {
        if (size() == data.length) {
            // System.out.println("Queue overflow");
            int newdata[] = new int[2 * data.length];
            int newsize = 0;
            for (int i = 0; i < size; i++) {
                int idx = (front + i) % data.length;
                newdata[newsize++] = data[idx];
            }
            newdata[newsize++] = val;  
            data = newdata;
            size = newsize;
            front = 0;
        } else {
            int rear = (front + size) % data.length;
            data[rear] = val;
            size++;
        }
    }

    int remove() {
        if (size() == 0) {
            System.out.println("Queue underflow");
            return -1;
        } else {
            int val = data[front];
            front = (front + 1) % data.length;
            size--;
            return val;
        }
    }

    int peek() {
        if (size() == 0) {
            System.out.println("Queue underflow");
            return -1;
        } else {
            return data[front];
        }
    }
}


//150
void calculate(stack<int> &st, string str)
{
    int s2 = st.top();
    st.pop();
    int s1 = st.top();
    st.pop();

    if (str == "+")
        st.push(s1 + s2);
    else if (str == "-")
        st.push(s1 - s2);
    else if (str == "*")
        st.push(s1 * s2);
    else
        st.push(s1 / s2);
}

int evaluateRPN(vector<string> &tokens)
{
    stack<int> st;
    for (int i = 0; i < tokens.size(); i++)
    {
        string str = tokens[i];
        if (str == "+" || str == "-" || str == "*" || str == "/")
            calculate(st, str);
        else
        {
            int num = stoi(str); //string to integer(stoi) 
            st.push(num);
        }
    }
    return st.top();
}

public static int precedence(char ch) {
    if (ch == '+')
        return 1;
    else if (ch == '-')
        return 1;
    else if (ch == '/')
        return 2;
    else if (ch == '*')
        return 2;

    return -1;
}

public static int operation(int o1, int o2, char optor) {
    if (optor == '+')
        return o1 + o2;
    else if (optor == '-')
        return o1 - o2;
    else if (optor == '*')
        return o1 * o2;
    else if (optor == '/')
        return o1 / o2;

    return 0;
}

public static void fun(Stack<Integer> operand, Stack<Character> operator) {
    int val2 = operand.pop();
    int val1 = operand.pop();
    char optor = operator.pop();
    int res = operation(val1, val2, optor);
    operand.push(res);
}

public static void infixEvaluation(String str) {
    Stack<Integer> operand = new Stack<>();
    Stack<Character> operator = new Stack<>();

    for (int i = 0; i < str.length(); i++) {
        char ch = str.charAt(i);
        if (ch == '(') {
            operator.push(ch);
        } else if (ch == ')') {
            //operator.size() > 0 not rqd if string bal
            while (operator.size() > 0 && operator.peek() != '(') {
                fun(operand, operator);
            }
            operator.pop();
        }
        else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
            while (operator.size() > 0 && operator.peek() != '(' && precedence(operator.peek()) >= precedence(ch)) {
                fun(operand, operator);
            }
            operator.push(ch);
        }
        else if (Character.isDigit(ch)) { // space bar ki wjh se digit check krna pdega
            operand.push(ch - '0');
        }
    }

    while (operator.size() != 0) {
        fun(operand, operator);
    }

    System.out.println(operand.peek());
}

// 227
//Infix Evaluation-space ho tou i++ ho hojaayga but ek digit se bda number then while loop make num and reinitialise num=0
int precedence(char ch)
{
    if (ch == '+')
        return 1;
    else if (ch == '-')
        return 1;
    else if (ch == '/')
        return 2;
    else if (ch == '*')
        return 2;

    return -1;
}

int operation(int o1, int o2, char optor)
{
    if (optor == '+')
        return o1 + o2;
    else if (optor == '-')
        return o1 - o2;
    else if (optor == '*')
        return o1 * o2;
    else if (optor == '/')
        return o1 / o2;

    return 0;
}

void fun(stack<int> &operand, stack<char> &operators)
{
    int val2 = operand.top();
    operand.pop();
    int val1 = operand.top();
    operand.pop();
    char optor = operators.top();
    operators.pop();
    int res = operation(val1, val2, optor);
    operand.push(res);
}

int calculate(string s)
{
    stack<int> operand;
    stack<char> operators;
    int num = 0, i = 0;

    while (i < s.length())
    {

        if (s[i] == '+' || s[i] == '-' || s[i] == '*' || s[i] == '/')
        {
            while (operators.size() > 0 && precedence(operators.top()) >= precedence(s[i]))
                fun(operand, operators);
            operators.push(s[i]);
            i++;
        }

        else if (s[i] != ' ')
        {
            while (i < s.length() && s[i] >= '0' && s[i] <= '9')
            {
                num = num * 10 + (s[i] - '0');
                i++;
            }
            operand.push(num);
            num = 0;
        }
        else
            i++;
    }

    while (operators.size() != 0)
        fun(operand, operators);

    return operand.top();
}

//Infix Conversion
public static void fun2(Stack<String> prefix, Stack<Character> operator, Stack<String> postfix) {
    String val2 = prefix.pop();
    String val1 = prefix.pop();
    char oprtor = operator.pop();
    String pre = oprtor + val1 + val2;

    String val3 = postfix.pop();
    String val4 = postfix.pop();
    String post = val4 + val3 + oprtor;

    prefix.push(pre);
    postfix.push(post);
}

public static void prefixPostfix(String str) {
    Stack<String> prefix = new Stack<>();
    Stack<Character> operator = new Stack<>();
    Stack<String> postfix = new Stack<>();

    for (int i = 0; i < str.length(); i++) {
        char ch = str.charAt(i);

        if (ch == '(')
            operator.push(ch);
        else if (ch >= 'a' && ch <= 'z' || ch >= '0' && ch <= '9' || ch >= 'A' && ch <= 'Z') {
            prefix.push(Character.toString(ch));  
            postfix.push(Character.toString(ch));
        } else if (ch == ')') {
            while (operator.size() > 0 && operator.peek() != '(') {
                fun2(prefix, operator, postfix);
            }
            operator.pop();
        } else if (ch == '+' || ch == '-' || ch == '/' || ch == '*') {
            while (operator.size() > 0 && operator.peek() != '(' && precedence(operator.peek()) >= precedence(ch)) {
                fun2(prefix, operator, postfix);
            }
            operator.push(ch);
        }
    }
    while (operator.size() != 0) {
        fun2(prefix, operator, postfix);
    }

    System.out.println(postfix.peek());
    System.out.println(prefix.peek());
}

public static void postfixEvalAndConv(String str) {
    Stack<Integer> eval = new Stack<>();
    Stack<String> infix = new Stack<>();
    Stack<String> prefix = new Stack<>();

    for (int i = 0; i < str.length(); i++) {
        char ch = str.charAt(i);
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
            int val2 = eval.pop();
            int val1 = eval.pop();
            eval.push(operation(val1, val2, ch));

            String is2 = infix.pop();
            String is1 = infix.pop();
            String ires = "(" + is1 + ch + is2 + ")";
            infix.push(ires);

            String ps2 = prefix.pop();
            String ps1 = prefix.pop();
            String pres = ch + ps1 + ps2;
            prefix.push(pres);
        } 
        else {
            eval.push(ch - '0');
            infix.push(ch + "");
            prefix.push(ch + "");
        }
    }

        System.out.println(eval.pop());
        System.out.println(infix.pop());
        System.out.println(prefix.pop());
}

public static void prefixConversionEval(String str) {
    Stack<Integer> eval = new Stack<>();
    Stack<String> infix = new Stack<>();
    Stack<String> postfix = new Stack<>();

    for (int i = str.length() - 1; i >= 0; i--) {
        char ch = str.charAt(i);
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
            int val2 = eval.pop();
            int val1 = eval.pop();
            eval.push(operation(val2, val1, ch));

            String is2 = infix.pop();
            String is1 = infix.pop();
            String ires = "(" + is2 + ch + is1 + ")";
            infix.push(ires);

            String ps2 = postfix.pop();
            String ps1 = postfix.pop();
            String pres = ps2 + ps1 + ch;
            postfix.push(pres);
        } else {
            eval.push(ch - '0');
            infix.push(ch + "");
            postfix.push(ch + "");
        }
    }

    System.out.println(eval.pop());
    System.out.println(infix.pop());
    System.out.println(postfix.pop());
}

// 1544
public String makeGood(String s) {
    LinkedList<Character> ll = new LinkedList<>();
    String res = "";
    ll.add(s.charAt(0));
    for (int i = 1; i < s.length(); i++) {
        if (ll.size() > 0 && (Math.abs(s.charAt(i) - ll.peekLast()) == 32)) {
            ll.pollLast();
        } else
            ll.add(s.charAt(i));
    }

    while (ll.size() > 0) {
        res += ll.peekFirst();
        ll.pollFirst();
    }

    return res;
}

// 844
string simplify(string s)
{
    stack<char>st;
    for(int i=0;i<s.length();i++)
    {
        char ch=s[i];
        if(ch=='#')
        {
            if(st.size()!=0)
                st.pop();
        }
        else
            st.push(ch);
    }
    
    string ans="";
    while(st.size()!=0)
    {
        ans+=st.top();
        st.pop();
    }
    return ans;
}

string simplify(string s)
{
    int bs=0;
    string ans="";
    for(int i=s.length()-1;i>=0;i--)
    {
        if(s[i]=='#')
            bs++;
        else{
            if(bs>0){
                bs--;
            }
            else 
                ans+=s[i];
        }
    }
    return ans;
}

bool backspaceCompare(string s, string t) {
    return simplify(s)==simplify(t);    
}

//71
public String simplifyPath(String path) {z
    String strArray[] = path.split("/");
    StringBuilder sb = new StringBuilder("/");
    LinkedList<String> st = new LinkedList<>();

    for (String str : strArray) {
        if (str.equals("..")) {
            if (st.size() != 0)
                st.pollLast();
        } else if (!str.equals("") && !str.equals(".")) {
            st.addLast(str);
        }
    }
    for (String str : st)  
        sb.append(str + "/");

    // if(flag==1) //cant do by stack ko empty krke and flag rkhkr
    if (!st.isEmpty())
        sb.deleteCharAt(sb.length() - 1);

    // flag se kaam ni chelga flag /../ kelie istmaal kraa tha but..yahan flag fail
    // "/home/../../.."
    return sb.toString();
}



//https://practice.geeksforgeeks.org/problems/sort-a-stack/1/
public Stack<Integer> sort(Stack<Integer> s)
{
    Stack<Integer>helper_stack1=new Stack<>();
    Stack<Integer>helper_stack2=new Stack<>();
    while(s.size()>0)
    {
        int ele=s.pop();
        while(helper_stack1.size()>0 && helper_stack1.peek()>ele)
            helper_stack2.add(helper_stack1.pop());
        helper_stack1.add(ele);
        while(helper_stack2.size()>0)
            helper_stack1.add(helper_stack2.pop());
    }
    for(int ele:helper_stack1)
        s.add(ele);
    return s;
}


//1441 yc
public List<String> buildArrayWithStackOprn(int[] target, int n) {

    List<String>result=new ArrayList<>();
    int i=0,ele=1;
    while(i<target.length)
    {
        if(ele>target[i])break;  // target = [1,2], n = 4 ["push","push"]
        else if(target[i]==ele)
        {
            result.add("Push");
            i++;
        }
        else
        {
            result.add("Push");
            result.add("Pop");
        }
        ele++;
    }
    
    return result;
}



// Application

// 1598 crawler log folder
public int minOperations(String[] logs) {
    Stack<Character> st = new Stack<>();
    for (int i = 0; i < logs.length; i++) {
        String str = logs[i];
        char ch1 = str.charAt(0);
        char ch2 = str.charAt(1);
        if ((ch1 >= 97 && ch1 <= 122) || (ch1 >= 48 && ch1 <= 57))
            st.push('f');
        else if (ch1 == '.') {
            if (ch2 == '.' && st.size() > 0)
                st.pop();
        }
    }
    return st.size();
}
// putting data member and fn in a class is called encapsulation
// person p1=new Person(); //p1 is refrence
}

//456
bool find132pattern(vector<int>& nums) {
    vector<int>minimum(nums.size());
    stack<int>st;
    minimum[0]=nums[0];
    for(int i=1;i<nums.size();i++)
        minimum[i]=min(nums[i],minimum[i-1]);
    
    bool flag=false;
    for(int j=nums.size()-1;j>=0;j--)
    {
        while(st.size()>0 && minimum[j]>=st.top())
            st.pop();
        if(st.size()>0 && nums[j]>st.top())
        {
            flag=true;break;
        }
        st.push(nums[j]);
    }
    
    return flag;
}

// 636
class pair1 {
    int id;
    int st; // start time
    int ct;
}

public int[] exclusiveTime(int n, List<String> logs) {

    int[] ans = new int[n];
    Stack<pair1> st = new Stack<>();
    for (int i = 0; i < logs.size(); i++) {
        String[] data = logs.get(i).split(":");
        if (data[1].equals("start")) {
            pair1 p = new pair1();
            p.id = Integer.parseInt(data[0]);
            p.st = Integer.parseInt(data[2]);
            st.push(p);
        } else {
            pair1 p = st.pop();
            int interval = Integer.parseInt(data[2]) - p.st + 1;
            ans[p.id] += interval - p.ct;

            if (st.size() > 0) {
                pair1 t = st.pop();
                t.ct += interval;
                st.push(t);
            }
        }
    }

    return ans;
}

// k stack in single array
static class kStack {

    int[] data;
    int[] top;
    int[] next;
    int free;

    kStack(int n, int k) {
        data = new int[n];
        top = new int[k];
        next = new int[n];
        free = 0;
    }

    public void initialise() {
        for (int i = 0; i < next.length - 1; i++) {
            next[i] = i + 1;
        }
        Arrays.fill(top, -1);
    }

    public void push(int stnum, int val) {
        if (free == -1) {
            System.out.println("Stack Overflow");
            return;
        }
        int idx = free;
        free = next[idx];
        data[idx] = val;
        next[idx] = top[stnum];
        top[stnum] = idx;
    }

    public int pop(int stnum) {
        if (top[stnum] == -1) {
            return -1;
        }
        int idx = top[stnum];
        top[stnum] = next[idx];
        next[idx] = free;
        free = idx;
        return data[idx];
    }

    public void display() {
        for (int i = 0; i < data.length; i++)
            System.out.print(data[i] + " ");
        System.out.println();
    }
}

/*
    * public static void kStackFunction() { kStack ks = new kStack(12, 3);
    * ks.initialise(); ks.push(2, 15); ks.push(2, 45); ks.push(2, 25); ks.push(1,
    * 5); ks.push(1, 7); ks.push(1, 19);
    * 
    * System.out.println(ks.pop(2)); ks.push(0, 50); System.out.println(ks.pop(0));
    * System.out.println(ks.pop(0)); System.out.println(ks.pop(1)); ks.push(1, 90);
    * ks.pop(2); ks.pop(2); ks.push(1, 100); ks.push(2, 200); ks.push(2, 400);
    * ks.push(1, 1200); ks.push(0, 2200); ks.push(2, 2300); ks.push(0, 2020);
    * ks.push(2, 1230); ks.push(0, 8920); ks.push(1, 82920); ks.push(1, 900);
    * ks.push(1, 20); ks.push(1, 9200); ks.push(1, 9270); ks.display(); }
    */

// https://www.geeksforgeeks.org/efficiently-implement-k-queues-single-array/
static class kQueue {

    int[] data;
    int[] front;
    int[] rear;
    int[] next;
    int free;

    kQueue(int n, int k) {
        data = new int[n];
        front = new int[k];
        rear = new int[k];
        next = new int[n];
        free = 0;
    }

    public void initialise() {
        for (int i = 0; i < next.length - 1; i++) {
            next[i] = i + 1;
        }
        Arrays.fill(front, -1);
        Arrays.fill(rear, -1);
        free = 0;
    }

    public boolean isEmpty(int quenum) {
        return front[quenum] == -1;
    }

    public void push(int quenum, int val) {
        if (free == -1) {
            System.out.println("Queue Overflow");
            return;
        }
        int idx = free;
        free = next[idx];
        if (isEmpty(quenum)) {
            front[quenum] = rear[quenum] = idx;
        } else {
            next[rear[quenum]] = idx;
            rear[quenum] = idx;
        }
        next[idx] = -1;
        data[idx] = val;
    }

    public int pop(int quenum) {
        if (isEmpty(quenum)) {
            return -1;
        }
        int idx = front[quenum];
        front[quenum] = next[idx];
        next[idx] = free;
        free = idx;
        return data[idx];
    }

    public void display() {
        for (int i = 0; i < data.length; i++)
            System.out.print(data[i] + " ");
        System.out.println();
    }
    // kQueue kq=new kQueue(10,3);
    // kq.initialise();
    // kq.push(2,15);kq.push(2,45);kq.push(2,50);kq.push(1,17);kq.push(1,49);kq.push(1,39);
    // kq.display();
    // System.out.println(kq.pop(1));
    // System.out.println(kq.pop(1));
    // kq.push(2,99);
    // kq.push(2,900);
/    // kq.display();
}