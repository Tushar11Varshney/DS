import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


public class set4 {
    
// 1. Find Celebrity
// 2. Smallest Number following pattern 
// 3. Remove all adjacent duplicate(2) 
// 4. Remove all duplicate which occur k times
// 4. Merge overlapping interval(sort on starting pt)
// 5. Maximum Equal Stack Sum
// 6. Asteroid Collision (remove chotte positive, brabar waale, stack empty & upr negative, upr bada positive)
// 7. Remove k digit to make small number //1112 //10 k=2 //903 k=1 *
// 8. Remove duplicate letters to make smallest in lexographical order(remove till small and freq > 1)
// 10. First non repeating char in stream
// 11.Validate stack Sequence(2)
// 12. Calculate 

//7
// Make string great
// Backspace compare(2)
// .calculate *
// .simplify path *
// Build array by stack oprn 

// https://www.geeksforgeeks.org/problems/the-celebrity-problem/1 pc
public static void findCelebrity(int[][] arr) {
    Stack<Integer> st = new Stack<>();
    for (int i = 0; i < arr.length; i++)
        st.push(i);

    while (st.size() >= 2) {
        int i = st.pop();
        int j = st.pop();

        if (arr[i][j] == 1) {
            // i knows j so i cant be a celebrity
            st.push(j);
        } else {
            // i doest not know j so j cant be a celebrity
            st.push(i);
        }
    }

    int potnCeleb = st.pop();
    for (int i = 0; i < arr.length; i++) {
        if (i != potnCeleb) {
            if (arr[i][potnCeleb] != 1 || arr[potnCeleb][i] != 0) {
                System.out.println("none");
                return;
            }
        }
    }
    System.out.println(potnCeleb);
}

//2375 pc
public static void smallestNumfollowingPattern(String str) {
    Stack<Integer> st = new Stack<>();
    int count = 1;
    for (int i = 0; i < str.length(); i++) {
        char ch = str.charAt(i);
        if (ch == 'd') {
            st.push(count);
        } else if (ch == 'i') {
            st.push(count);
            while (st.size() != 0) {
                System.out.print(st.pop() + "");
            }
        }
        count++;
    }
    System.out.print(count);
    while (st.size() != 0)
        System.out.print(st.pop() + "");
}

//1047
string removeDuplicates(string s)
{
    int i = 0, j = 0;
    for (; j < s.length(); j++, i++)
    {
        s[i] = s[j];

        if (i > 0 && s[i] == s[i - 1])
            i -= 2;
    }
    return s.substr(0, i);

    // int i = 0, j = 1;
    // for (; j < s.length(); j++)
    // {
    //     if(i!=-1 && s[i]==s[j])
    //     {
    //         i=i-1;
    //     }
    //     else{
    //         i++;
    //         s[i]=s[j];
    //     }
    // }
    // return s.substr(0, i+1);
}

public String removeDuplicates(String s) {
    List<Character> l = new ArrayList<>();
    l.add(s.charAt(0));
    for (int i = 1; i < s.length(); i++) {
        l.add(s.charAt(i));
        if (l.size() > 1 && l.get(l.size() - 1) == l.get(l.size() - 2)) {
            l.remove(l.size() - 2);
            l.remove(l.size() - 1);
        }
    }

    String ans = "";
    for (int i = 0; i < l.size(); i++)
        ans += l.get(i);

    return ans;
}

// 1209 yc
class pair_ {
    char ch;
    int count;

    pair_(char ch, int count) {
        this.ch = ch;
        this.count = count;
    }
}

public String removeDuplicates(String s, int k) {
    Stack<pair_> st = new Stack<>();
    StringBuilder res = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
        if (st.size() > 0 && st.peek().ch == s.charAt(i)) {
            if (st.peek().count + 1 == k)
                st.pop();
            else {
                pair_ p = st.pop();
                p.count++;
                st.push(p);
            }
        } else {
            pair_ p = new pair_(s.charAt(i), 1);
            st.push(p);
        }
    }

    while (st.size() != 0) {
        pair_ p = st.pop();
        char ch = p.ch;
        int freq = p.count;
        {
            for (int i = 0; i < freq; i++)
                res.append(ch);
        }
    }

    return (res.reverse()).toString();
}


// 56
static class Pair implements Comparable<Pair> {
    int st;
    int et;

    Pair(int st, int et) {
        this.st = st;
        this.et = et;
    }

    public int compareTo(Pair other) {
        return this.st - other.st;
    }
}

public static void mergeOverlappingIntervals(int[][] arr) {
    Pair[] pr = new Pair[arr.length];
    for (int i = 0; i < arr.length; i++) {
        pr[i] = new Pair(arr[i][0], arr[i][1]);
    }

    Arrays.sort(pr);

    Stack<Pair> st = new Stack<>();
    st.push(pr[0]);

    for (int i = 1; i < arr.length; i++) {
        Pair top = st.peek();

        if (top.et >= pr[i].st) {
            Pair rp = st.pop();
            Pair np = new Pair(rp.st, Math.max(top.et, pr[i].et));
            st.push(np);
        } else {
            st.push(pr[i]);
        }
    }
    Stack<Pair> newSt = new Stack<>();
    while (st.size() != 0) {
        newSt.push(st.pop());
    }

    // pep
    while (newSt.size() != 0) {
        Pair rp = newSt.pop();
        System.out.println(rp.st + " " + rp.et);
    }

    // leetcode
    // int n = newSt.size();  //newst ki need ni last idx me daal do
    // int[][] ans = new int[n][2];
    // int i = 0;
    // while (newSt.size() != 0) {
    // Pair rp = newSt.pop();
    // ans[i][0] = rp.st;
    // ans[i][1] = rp.et;
    // i++;
    // }

    // return ans;
}

// https://www.codingninjas.com/codestudio/problems/maximum-equal-stack-sum_1062571?leftPanelTab=0
public static void inputFn(Stack<Integer> stk) {
    Scanner sc = new Scanner(System.in);
    while (true) {
        int val = sc.nextInt();
        if (val == -1)
            break;
        stk.push(val);
    }
}

public static int sum(Stack<Integer> stk) {
    int sz = stk.size();
    Stack<Integer> st = new Stack();
    int sum = 0;
    while (sz-- > 0) {
        int val = stk.pop();
        sum += val;
        st.push(val);
    }
    sz = st.size();
    while (sz-- > 0) {
        stk.push(st.pop());
    }
    return sum;
}

public static int maxSum(Stack<Integer> stk1, Stack<Integer> stk2, Stack<Integer> stk3) {
    int sum1 = sum(stk1), sum2 = sum(stk2), sum3 = sum(stk3);
    int ans = 0;
    while (true) {
        if (stk1.size() == 0 || stk2.size() == 0 || stk3.size() == 0) {
            ans = 0;
            break;
        } else if (sum1 == sum2 && sum2 == sum3) {
            ans = sum1;
            break;
        } else if (sum1 >= sum2 && sum1 >= sum3)
            sum1 -= stk1.pop();
        else if (sum2 >= sum3 && sum2 >= sum1)
            sum2 -= stk2.pop();
        else if (sum3 >= sum1 && sum3 >= sum2)
            sum3 -= stk3.pop();
    }
    return ans;
}

//735-Fatega(pop) only when jab stack mein +ve/for storing purpose only we push -ve num
vector<int> asteroidCollision(vector<int> &asteroids)
{
    stack<int> st;
    for (int ele : asteroids)
    {
        if (ele > 0)   //1
            st.push(ele);
        else
        {
            while (st.size() != 0 && st.top() > 0 && st.top() < -ele) //yahan = lgakr andar fir pop krke continue krke ni chlega //[1,3,2,1,-3]  on equal it will pop 1 also which is wrong
                st.pop();           //2

            if (st.size() != 0 && st.top() == -ele) // [ 8, -8 ]   //3
                st.pop();
            else if (st.size() == 0 || st.top() < 0)  //4
                st.push(ele);
            else
            { //negative asteroid will destroy  [3,-2]  //5
            }
        }
    }

    vector<int> ans(st.size());
    for (int i = st.size() - 1; i >= 0; i--)
    {
        ans[i] = st.top();
        st.pop();
    }
    return ans;
}



//402
string removeKdigits(string num, int k)
{
    int n = num.size();
    vector<int> st; //instead of ll use vector 
 
    for (int i = 0; i < n; i++)   //cannot run till k!=0
    {
        char ch = num[i];
        while (st.size() != 0 && k > 0 && st.back() > ch)
        {
            k--;
            st.pop_back();
        }

        if (st.size() == 0 && ch == '0')   //90301 when k=1//so in this case st size if 0 then only continue...nhi tou last mein answer bnate tym aayga 0301 we want 301
            continue;

        st.push_back(ch);
    }

    while (st.size() != 0 && k-- > 0)
        st.pop_back();

    String ans = "";
    for (int i = 0; i < st.size(); i++)
    {
        // if(st[i]=='0' && ans.length()==0)continue; ya tou check idhr ya upr leading zeroes
        ans += st[i];
    }

    return ans.length() == 0 ? "0" : ans;
}

// 316,1081-smallest subsequence of dist letter
/*string removeDuplicateLetters(string s){   
    int n = s.size();
    vector<int> freq(26, 0);
    vector<bool> visited(26, 0);
    
    for (int i = 0; i < n; i++)
    {
        char ch = s[i];
        freq[ch - 'a']++;
    }

    string str = ""; //string is array of characters
    for (int i = 0; i < n; i++)
    {
        char ch = s[i];
        freq[ch - 'a']--;

        if (visited[ch - 'a'])
            continue;

        while (str.size() != 0 && freq[str.back() - 'a'] > 0 && str.back() > ch)
        {
            visited[str.back() - 'a'] = false;
            str.pop_back();
        }

        visited[ch - 'a'] = true;
        str.push_back(ch);
    }
    return str;
}*/

//https://www.interviewbit.com/problems/first-non-repeating-character-in-a-stream-of-characters/ gc
string firstNonRepeatingCharacterInAStream(string A)
{
    unordered_map<char,int>map;
    queue<char>que;
    string ans="";
    for(int i=0;i<A.length();i++)
    {
        map[A[i]]++;
        if(map[A[i]]==1)
        que.push(A[i]);

        while(que.size()>0 && map[que.front()]>1)
        que.pop();
        
        if(que.size()>0)ans+=que.front();
        else ans+='#';
    }
    
    return ans;
}

//946
bool validateSequence(vector<int> &pushed, vector<int> &popped)
{
    int i = 0, j = 0;
    stack<int> st;
    while (i < pushed.size())
    {
        if (pushed[i] == popped[j])
        {
            i++;
            j++;
        }
        else if (st.size() != 0 && st.top() == popped[j])
        {
            st.pop();
            j++;
        }
        else
        {
            st.push(pushed[i]);
            i++;
        }
    }

    while (st.size() != 0)
    {
        if (st.top() == popped[j])
        {
            j++;
            st.pop();
        }
        else
            return false;
    }

    return true;
}

bool validateStackSequences2(vector<int> &pushed, vector<int> &popped) //sir
{
    stack<int> st;
    int i = 0;

    for (int ele : pushed)
    {
        st.push(ele);
        while (st.size() != 0 && st.top() == popped[i])
        {
            st.pop();
            i++;
        }
    }

    return st.size() == 0;
}

// 224 yc
// basic method fails because "-2+1" yahan jaise hi usko + milega tou wo
// calcualtion krega pr operand stack size only 1 so stackEmpty
// exception...
// 2147484647 mein 7 dega output aese hi agar check lgalia ki koi
// operator nhi h tou parse krke output return
// then..." 30" yahan dega output 0 wo
public int calculate(String s) {
    Stack<Integer> st = new Stack<>();
    int result = 0, num = 0, sign = 1;
    st.push(sign);

    for (int i = 0; i < s.length(); i++) {
        char ch = s.charAt(i);
        if (ch >= '0' && ch <= '9') {
            num = num * 10 + (ch - '0');
        } else if (ch == '+' || ch == '-') {
            result += sign * num;
            sign = st.peek() * (ch == '+' ? 1 : -1);
            num = 0;
        } else if (ch == '(') {
            st.push(sign);
        } else if (ch == ')') {
            st.pop();
        }
    }

    result += sign * num;
    return result;
}


}
