// valid palindrome
// after deleting atmost 1 char
// longest common prefix
// 

// 443 bc
int compress(vector<char> &chars)
{
    if (chars.size() <= 1)
        return chars.size();
    int len = 0;
    for (int i = 0; i < chars.size(); i++)
    {
        int j = i + 1;
        while (j < chars.size() && chars[i] == chars[j])
            j++;
        chars[len++]=chars[i];
        if (j - i > 1)
        {
            string str = to_string(j - i);  //["a","b","b","b","b","b","b","b","b","b","b","b","b"]
            for (int k = 0; k < str.length(); k++)
                chars[len++] = str[k];
        }
        i = j - 1;
    }
    return len;
}

public String arrangeWords(String text) {
    String []str1=text.split(" ");
    Arrays.sort(str1, (a,b)->Integer.compare(a.length(), b.length()));

    StringBuilder sb=new StringBuilder();
    for(int i=0;i<str1.length;i++)
    {
        StringBuilder tempSb = new StringBuilder(str1[i]);
        if(i==0)
            tempSb.setCharAt(0, Character.toUpperCase(str1[i].charAt(0)));
        else
            tempSb.setCharAt(0, Character.toLowerCase(str1[i].charAt(0)));
        tempSb.append(" ");
        sb.append(tempSb.toString());
    }

    sb.deleteCharAt(sb.length()-1);
    return sb.toString();    
}

//1328
public boolean isPalindrome(String p, int i, int j)
{
    while(i<j)
    {
        if(p.charAt(i)==p.charAt(j))
        {
            i++;
            j--;
        }
        else return false;
    }
    return true;
}


public String breakPalindrome(String p) {
    
    for(int i=0;i<p.length();i++)
    {
        for(int j=97; j<(int)(p.charAt(i)); j++)
        {
            System.out.println((char)j);
            StringBuilder sb=new StringBuilder(p);
            sb.setCharAt(i, (char)(j));
            if(!isPalindrome(sb.toString(), 0, p.length()-1))
                return sb.toString();
        }
    }

    for(int i=p.length()-1;i>=0;i--)
    {
        for(int j=((int)p.charAt(i))+1; j<123; j++)
        {
            StringBuilder sb=new StringBuilder(p);
            sb.setCharAt(i, (char)(j));
            if(!isPalindrome(sb.toString(), 0, p.length()-1))
                return sb.toString();
        }
    }

    return new StringBuilder("").toString();
}

public String breakPalindrome(String p) {
    if(p.length()<=1)
        return "";

    //Since the first half and the second half of the palindrome string are symmetric, so we only need to be traverse the first half.
    for(int i=0;i<p.length()/2;i++)
    {
        if(p.charAt(i)!='a')
        {
            StringBuilder sb=new StringBuilder(p);
            sb.setCharAt(i, 'a');
            return sb.toString();
        }
    }

    StringBuilder sb=new StringBuilder(p);
    sb.setCharAt(sb.length()-1, 'b');
    return sb.toString();
}

//https://practice.geeksforgeeks.org/problems/longest-prefix-suffix2527/1#
int lps(string s)
{
    int i = 1, len = 0;
    vector<int> LPS(s.length());
    while (i < s.length())
    {
        if (s[i] == s[len])
        {
            len++;
            LPS[i] = len;
            i++;
        }
        else
        {
            if (len > 0)len = LPS[len - 1];
            else
            {
                LPS[i] = 0;
                i++;
            }
        }
    }
    return len;
}

//1392
int lps(string s)
{
    vector<int>ans(s.length());
    int i=1,len=0;
    while(i<s.length())
    {
        if(s[len]==s[i])
        {
            len++;
            ans[i]=len;
            i++;
        }
        else{
            if(len>0)
                len=ans[len-1];
            else{
                ans[i]=0;
                i++;
            }
        }
    }
    return ans[s.length()-1];
}

string longestPrefix(string s) {
    return s.substr(0,lps(s));
}

// 125
bool isAlphabet(char ch)
{
    if (ch >= 97 && ch <= 122)
        return true;
    if (ch >= 65 && ch <= 90)
        return true;
    return false;
}

bool isNumber(char ch)
{
    if (ch >= 48 && ch <= 57)
        return true;
    return false;
}

bool isPalindrome(string s) // test case:0p
{
    int i = 0, j = s.length() - 1;
    while (i < j)
    {
        char ch1 = s[i];
        char ch2 = s[j];

        if ((!isAlphabet(ch1) && !isNumber(ch1)) && (!isNumber(ch2) && !isAlphabet(ch2)))
        {
            i++;
            j--;
        }
        else if (!isAlphabet(ch1) && !isNumber(ch1))
            i++;
        else if (!isAlphabet(ch2) && !isNumber(ch2))
            j--;
        else
        {
            if (ch1 == ch2 || (isAlphabet(ch1) && isAlphabet(ch2) && abs(ch1 - ch2) == 32))
            {
                i++;
                j--;
            }
            else return false;
        }
    }

    return true;
}

// 680
bool validPalindrome(string s)  
{
    int i = 0, j = s.length() - 1;
    while (i < j)
    {
        if (s[i] == s[j])
        {
            i++;
            j--;
        }
        else
            return isPalindrome(s, i, j - 1) || isPalindrome(s, i + 1, j);
    }
    return true;
}

bool isPalindrome(string s, int i, int j)
{
    while (i < j)
    {
        if (s[i] == s[j])
        {
            i++;
            j--;
        }
        else return false;
    }
    return true;
}


// 14
public String longestCommonPrefix(String[] strs) {
    if (strs.length == 1)
        return strs[0];

    StringBuilder sb = new StringBuilder(strs[0]);
    // String s=new String(strs[0]);
    for (int i = 1; i < strs.length; i++) {
        String str = strs[i];
        int j = 0, k = 0;
        while (j < sb.length() && k < str.length()) {
            if (sb.charAt(j) != str.charAt(k))
                break;
            else {
                j++;
                k++;
            }
        }
        // s=s.substring(0,j);
        sb.delete(j, sb.length()); 
    }

    return sb.toString();
}

public String longestCommonPrefix2(String[] strs) {
    if (strs.length == 1)
        return strs[0];

    Arrays.sort(strs);  //['flower','flu','flight']  //sorted->['flight','flower','flu']

    String ans = "";

    String s1 = strs[0];
    String s2 = strs[strs.length - 1];

    int i = 0, j = 0;
    while (i < s1.length()) {
        if (s1.charAt(i) == s2.charAt(j)) {
            ans += s1.charAt(i);
            i++;
            j++;
        } else break;
    }
    return ans;
}

// 796 gc
public boolean rotateString_(String A, String B) {
    return A.length() == B.length() && (A + A).contains(B); 
}

public boolean rotateString(String s, String goal) {
    if (s.equals(goal))
        return true;

    StringBuilder sb = new StringBuilder(s);
    int n = s.length();

    for (int i = 1; i < n; i++) {
        char ch = sb.charAt(0);
        sb.deleteCharAt(0);
        sb.append(ch);
        if (goal.equals(sb.toString()))return true;
    }
    return false;
}

//151
public String reverseWords(String s) {
    StringBuilder ans = new StringBuilder();
    int i = s.length() - 1;
    while (i >= 0) {
        StringBuilder word = new StringBuilder();
        while (i >= 0 && s.charAt(i) == ' ')
            i--;
        while (i >= 0 && s.charAt(i) != ' ') {
            word.insert(0, s.charAt(i));
            i--;
        }
        word.append(' ');
        ans.append(word);
    }
    ans.deleteCharAt(ans.length() - 1);
    if (ans.charAt(ans.length() - 1) == ' ') 
        ans.deleteCharAt(ans.length() - 1); // at max yahan pr do space rhengi tou pehle meine loop chalay tha wo bekar ...do space tab jab shuruat mein bhot sarii space ho..
    return ans.toString();
}

public String reverseWords(String s) {
    StringBuilder sb = new StringBuilder();
    int i=0, j=s.length()-1;
    //remove leading space
    while(s.charAt(i)==' ')
    i++;
    // remove trailing space
    while(s.charAt(j)==' ')
    j--;

    while(i<=j)
    {
        int k=i;
        StringBuilder word = new StringBuilder();
        // skip in between space
        while(k<=j && s.charAt(k)==' ')
        k++;
        while(k<=j && s.charAt(k)!=' ')
        {
            word.append(s.charAt(k));
            k++;
        }
        word.insert(0, ' ');
        sb.insert(0, word);
        i=k+1;
    }
    // delete space added by last word
    sb.deleteCharAt(0);
    return sb.toString();
}

//1002 yc
public List<String> commonChars(String[] A) {

    List<String> result = new ArrayList<>();
    int minArray[] = new int[26];
    String str = A[0];

    for (int i = 0; i < str.length(); i++) {
        minArray[str.charAt(i) - 'a']++;
    }

    for (int i = 1; i < A.length; i++) {
        str = A[i];
        int freqArr[] = new int[26];
        for (int j = 0; j < str.length(); j++) {
            freqArr[str.charAt(j) - 'a']++;
        }

        for (int j = 0; j < 26; j++) {
            minArray[j] = Math.min(minArray[j], freqArr[j]);
        }
    }

    for (int i = 0; i < 26; i++) {
        int val = minArray[i];
        if (val > 0) {
            for (int j = 0; j < val; j++)
                result.add((char) (i + 'a') + "");
        }
    }
    return result;
}

//yc
//https://www.interviewbit.com/problems/remove-consecutive-characters/
public String removeConsecutiveCharacter(String A, int B) { // ib

    int si = 0, ei = 0;
    StringBuilder sb = new StringBuilder();
    while (ei < A.length()) {
        char lc = A.charAt(si);
        while (ei < A.length() && A.charAt(ei) == lc)
            ei++;

        if (ei - si == B) {
            si = ei;
        } else {
            int noOftime = ei - si;
            while (noOftime-- > 0) {
                sb.append(lc);
            }
            si = ei;
        }
    }

    return sb.toString();  
}






