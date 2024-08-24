import java.util.Stack;

public class set1 {
    

// 1. Balanced bracket(2-bracket ascii, match)
// 2. Minimum insertion to balance a par string(2-count unbalanced or push in stack)
// 3. Min Bracket reversal to make exp balanced
// 4. Minimum remove to make valid string
// 5. Score of Parentheses
// 6. Longest valid Parentheses(Length concept, dont check -1 index, in stack unbalanced elements)
// 7. Remove outer Parentheses(2)-add ( and ) when size>1
// 8. Min insertion ()) 
// 9. decode string  


//2
// Reverse Paren
// Redundant braces 

//20
bool isValid(string s)
{
    stack<char> st;
    for (int i = 0; i < s.length(); i++)
    {
        if (s[i] == '{' || s[i] == '[' || s[i] == '(')
        {
            st.push(s[i]);
        }
        else
        {
            if (st.size() == 0)
                return false;
            else if (s[i] == '}' && st.top() != '{')
                return false;
            else if (s[i] == ']' && st.top() != '[')
                return false;
            else if (s[i] == ')' && st.top() != '(')
                return false;
            else
                st.pop();
        }
    }
    if (st.size() != 0)
        return false;
    return true;
}

public static boolean handleClosing(Stack<Character> st, char ch) // sumeet sir
{
    if (st.size() == 0)
        return false;
    else if (st.peek() != ch)
        return false;
    else {
        st.pop();
        return true;
    }
}

public static void balancedBracket2(String str) {
    Stack<Character> st = new Stack<>();
    for (int i = 0; i < str.length(); i++) {
        char ch = str.charAt(i);
        // if (ch == '{' || ch == '[' || ch == '(')
        // st.push(ch);
        // [->91 ]->93 {->123 }->125 (->40 )->41
        // else if (ch == '}' || ch == ']' || ch == ')') {
        //if (st.size() != 0 && ((ch - st.peek() == 1) || (ch -st.peek() == 2))) {
        // st.pop();
        // } else
        // return false;
        // }
        if (ch == '(' || ch == '{' || ch == '[')
            st.push(ch);
        else if (ch == ')') {
            boolean val = handleClosing(st, '(');
            if (val == false) {
                System.out.println("false");
                return;
            }
        } else if (ch == '}') {
            boolean val = handleClosing(st, '{');
            if (val == false) {
                System.out.println("false");
                return;
            }
        } else if (ch == ']') {
            boolean val = handleClosing(st, '[');
            if (val == false) {
                System.out.println("false");
                return;
            }
        }
    }

    if (st.size() == 0)
        System.out.println("true");
    else
        System.out.println("false");
}

// 921
public int minAddToMakeValid(String str) {
    Stack<Character> st = new Stack<>();
    int count = 0;
    for (int i = 0; i < str.length(); i++) {
        char ch = str.charAt(i);
        if (ch == '{' || ch == '[' || ch == '(')
            st.push(ch);
        else if (ch == '}' || ch == ']' || ch == ')') {
            if (st.size() != 0 && ((ch - st.peek() == 1) || ch - st.peek() == 2)) {
                st.pop();
            } else
                count += 1;  //ya fir stack mein push and last mein return st size
        }
    }
    if (st.size() != 0)
        count += st.size();
    return count;
}

//https://practice.geeksforgeeks.org/problems/count-the-reversals0401/1
int countRev(string s)
{
    stack<char> st;
    int unBalancedClosing = 0, unBalancedOpening = 0;
    for (int i = 0; i < s.length(); i++)
    {
        if (s[i] == '{')
            st.push(s[i]);
        else if (s[i] == '}')
        {
            if (st.size() == 0)
                unBalancedClosing++;
            else if (st.top() == '{')
                st.pop();
        }
    }

    unBalancedOpening = st.size();

    int r1 = unBalancedClosing / 2;
    int re1 = unBalancedClosing % 2; //re:remaining

    int r2 = unBalancedOpening / 2;
    int re2 = unBalancedOpening % 2;

    if (re1 == re2)
        return r1 + r2 + re1 + re2;
    return -1;
}

//1249
string minRemoveValidString(string s)
{
    string res = "";
    stack<int> st; //stack char ki bnadi islie rte
    for (int i = 0; i < s.length(); i++)
    {
        char ch = s[i];

        if (ch == '(')
            st.push(i);

        else if (ch == ')')
        {
            if (st.size() != 0)
                st.pop();
            else
                s[i] = '#';
        }
    }

    while (st.size() != 0)
    {
        s[st.top()] = '#';
        st.pop();
    }

    for (int i = 0; i < s.length(); i++)
    {
        if (s[i] != '#')
            res += s[i];
    }

    return res;
}


//856  ((()())) 4+4=8
int scoreOfParentheses(string s)
{
    stack<char> st;
    int i = 0, score = 0;
    while (i < s.length())
    {
        if (s[i] == '(')
        {
            st.push(s[i]);
            i++;
        }
        else
        {
            st.pop();
            score += (int)(pow(2, st.size())); //acc to gfg pow fn do not work sometime well on integers
            while (++i < s.length() && s[i] == ')')
                st.pop();
        }
    }
    return score;
}

//32
//https://practice.geeksforgeeks.org/problems/valid-substring0624/1
int longestValidParentheses(string s)
{
    stack<int> st;
    st.push(-1);
    int maxLen = 0;
    for (int i = 0; i < s.length(); i++)
    {
        if (st.top() != -1 && s[st.top()] == '(' && s[i] == ')')
        {
            st.pop();
            maxLen = max(maxLen, i - st.top());
        }
        else
            st.push(i);
    }

    return maxLen;
}

//1021
string removeOuterParentheses(string s)
{
    stack<char> st;
    string ans = "";
    for (int i = 0; i < s.length(); i++)
    {
        if (s[i] == '(')
        {
            st.push(s[i]);
            if (st.size() > 1)
                ans += s[i];
        }
        else
        {
            if (st.size() > 1)
                ans += s[i];
            st.pop();
        }
    }
    return ans;
}

string removeOuterParentheses(string s) //do by stack also
{
    string ans = "";
    int count = 0;
    for (int i = 0; i < s.length(); i++)
    {
        char ch = s[i];

        if (ch == '(' && count++ > 0)
            ans += ch;
        else if (ch == ')' && count-- > 1)
            ans += ch;
    }
    return ans;
}

//1541
int minInsertions(string s)
{
    int ans = 0;
    int open = 0;

    for (int i = 0; i < s.length(); i++)
    {
        if (s[i] == '(')
            open++;
        else
        {
            if (i + 1 == s.length() || s[i + 1] != ')')
                ans++;
            else
                i++;

            if (open > 0)
                open--;
            else
                ans++;
        }
    }

    return 2 * open + ans;
}

//394
string decodeString(string s) {
    stack<string>st;
    stack<int>numst;
    string res="";
    int i=0;
    while(i<s.length())
    {
        char ch=s[i];
        if(ch>=48 && ch<=57)
        {
            int num=0;
            while(i<s.length() && s[i]>=48 && s[i]<=57)
            {
                num=num*10+(s[i]-'0');
                i++;
            }
            numst.push(num);
        }
        else if(ch=='[')
        {
            string t={ch};
            st.push(t);
            i++;
        }
        else{
            if(ch>=97 && ch<=122 && st.size()==0)
                res+=ch;
            else if(ch>=97 && ch<=122)
            {
                string t={ch};
                st.push(t);
            }
            else{
                string subans1="",subans2="";
                while(st.top()!="[")
                {
                    subans1=st.top()+subans1;
                    st.pop();
                }
                st.pop();
                int repeat=numst.top();numst.pop();
                for(int i=0;i<repeat;i++)
                {
                    for(char ch:subans1)
                        subans2+=ch;
                }
                if(st.size()!=0)
                    st.push(subans2);
                else
                    res+=subans2;
            }
            i++;
        }
    }
    
    return res;
}


}
