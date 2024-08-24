// String:
// Palindromic substring - count, longest
// Palindromic subseq - longest, print-(3-2d dp, LL, string)
// Count Palindromic subseq
// Number of distinct subseq
// Longest common subsequence
// IsSubsequence
// Uncrossed Lines
// Min number of oprn to make string equal
// Longest common substring 
// wildcard matching

#include <iostream>
#include <vector>
#include <math.h>
#include <list>
using namespace std;

void print(vector<int> arr)
{
    for (int ele : arr)
        cout << ele << " ";
    cout << endl;
}

void print2d(vector<vector<int>> arr)
{
    for (vector<int> ar : arr)
        print(ar);
}

void palindromicsubstring(string str, vector<vector<bool>> &dp)
{
    int n = str.length();
    for (int gap = 0; gap < n; gap++)
    {
        for (int i = 0, j = gap; j < n; j++, i++)
        {
            if (gap == 0)
                dp[i][j] = true;
            else if (gap == 1)
                dp[i][j] = str[i] == str[j] ? true : false;
            else
                dp[i][j] = str[i] == str[j] && dp[i + 1][j - 1] ? true : false;
        }
    }
}    

//647
int countpalindromicsubstring(string str, vector<vector<bool>> &dp)
{
    int n = str.length();
    int count = 0;
    for (int gap = 0; gap < n; gap++)
    {
        for (int i = 0, j = gap; j < n; j++, i++)
        {
            if(gap==0)dp[i][j]=true;
            else if(gap==1)dp[i][j]=str[i]==str[j];
            else dp[i][j]=str[i]==str[j] && dp[i+1][j-1];
            
            if(dp[i][j]==true)count++;
        }
    }
    return count;
}

void countpalindromicsubstringsprob()
{
    // string s = "abab";
    string s = "aaa";
    int n = s.length();
    vector<vector<bool>> dp(n, vector<bool>(n));
    cout << countpalindromicsubstring(s, dp) << endl;
}

//5
string longestpalindromicsubstring(string str, vector<vector<int>> &dp)
{
    int n = str.length();
    int si = 0, ei = 0, length = 0;
    for (int gap = 0; gap < n; gap++)
    {
        for (int i = 0, j = gap; j < n; j++, i++)
        {
            if (gap == 0)
                dp[i][j] = 1;
            else if (gap == 1 && str[i] == str[j])
                dp[i][j] = 2;

            else if (str[i] == str[j] && dp[i + 1][j - 1] != -1)  //ya 0 se initialse krke 0 se check
                dp[i][j] = dp[i + 1][j - 1] + 2;

            if (dp[i][j] > length)
            {
                si = i;
                ei = j;
                length = dp[i][j];
            }
        }
    }
    cout << "length is " << length;
    return str.substr(si, ei - si + 1); //(si,length) ye chalta hi si+length se hai so no need to write (si,si+length)
}

void longestpalindromicsubstringProb()
{
    string str = "bbbab";
    // string str = "abcd";
    int n = str.length();
    vector<vector<int>> dp(n, vector<int>(n, -1));
    cout << longestpalindromicsubstring(str, dp) << endl;
    // print2d(dp);
}

//516
int longestpalindromeSUBSEQUENCE(string s, int i, int j, vector<vector<int>> &dp)
{
    if (i == j || i > j)         //"aa"..mein socho i>j
        return dp[i][j] = i > j ? 0 : 1;

    if (dp[i][j] != -1)
        return dp[i][j];

    int length = 0;
    if (s[i] == s[j])
        length = longestpalindromeSUBSEQUENCE(s, i + 1, j - 1, dp) + 2;
    else
        length = max(longestpalindromeSUBSEQUENCE(s, i, j - 1, dp), longestpalindromeSUBSEQUENCE(s, i + 1, j, dp));

    return dp[i][j] = length;
}

int longestpalindromeSUBSEQUENCEDP(string s, int I, int J, vector<vector<int>> &dp, vector<vector<string>> &sdp)
{
    int n = s.length();
    for (int gap = 0; gap < n; gap++)
    {
        for (int i = 0, j = gap; j < n; j++, i++)
        {
            if (i == j || i > j)
            {
                if (i == j)
                {
                    dp[i][j] = 1;
                    sdp[i][j] = s[i];   //string mein ni dalta charcter vector mein dal jaata hai
                }
                continue;
            }

            int length = 0;
            if (s[i] == s[j])
            {
                length = dp[i + 1][j - 1] + 2;
                sdp[i][j] = s[i] + sdp[i + 1][j - 1] + s[i];
            }
            else
            {
                if (dp[i][j - 1] > dp[i + 1][j])
                {
                    length = dp[i][j - 1];
                    sdp[i][j] = sdp[i][j - 1];
                }
                else
                {
                    length = dp[i + 1][j];
                    sdp[i][j] = sdp[i + 1][j]; //This solution takes too much space.
                }
            }
            dp[i][j] = length;
        }
    }
    return dp[I][J];
}

void showANS(list<char> &l)
{
    list<char>::iterator itr = l.begin(); 
    for (; itr != l.end(); itr++)
        cout << *itr << "";
}

void generateString(vector<vector<int>> &dp, int i, int j, string s, list<char> &fl, list<char> &bl)
{
    if (i == j || i > j)
    {
        if (i == j)
            bl.push_back(s[i]);

        showANS(bl);
        showANS(fl);
        cout << endl;

        if (i == j)
            bl.pop_back(); //agar hum pop nhi krenge aur maanlo ek aur call gi tou ye answer isi meinadd krdega aur pura answer bigadd jaayga

        return;
    }

    if (s[i] == s[j])
    {
        bl.push_back(s[i]);
        fl.push_front(s[i]);

        generateString(dp, i + 1, j - 1, s, fl, bl);

        bl.pop_back();
        fl.pop_front();
    }
    else
    {
        if (dp[i][j - 1] > dp[i + 1][j])
        {
            generateString(dp, i, j - 1, s, fl, bl);
        }
        else if (dp[i][j - 1] < dp[i + 1][j])
        // else
        {
            generateString(dp, i + 1, j, s, fl, bl);
        }
        else
        {
            generateString(dp, i, j - 1, s, fl, bl); //for only 1 remove this
            generateString(dp, i + 1, j, s, fl, bl);
        }
    }
}

void generateString2(vector<vector<int>> &dp, int i, int j, string s, string s1, string s2)
{
    if (i == j || i > j)
    {
        if (i == j)
            s1 = s1 + s[i];

        cout << s1;
        cout << s2;
        cout << endl;
        return;
    }

    if (s[i] == s[j])
    {
        s1 = s1 + s[i];
        s2 = s[i] + s2;
        generateString2(dp, i + 1, j - 1, s, s1, s2);
    }
    else
    {
        if (dp[i][j - 1] > dp[i + 1][j])
        {
            generateString2(dp, i, j - 1, s, s1, s2);
        }
        else if (dp[i][j - 1] < dp[i + 1][j])
        {
            generateString2(dp, i + 1, j, s, s1, s2);
        }
        else
        {
            generateString2(dp, i, j - 1, s, s1, s2); //for only 1 remove this
            generateString2(dp, i + 1, j, s, s1, s2);
        }
    }
}

void longestpalindromeSUBSEQUENCEProb()
{
    string str = "abcbdfabc";
    // string str = "badaf";
    // string str = "abc";

    int n = str.length();
    int i = 0, j = n - 1;
    list<char> fl;
    list<char> bl;
    vector<vector<int>> dp(n, vector<int>(n, -1));
    vector<vector<string>> sdp(n, vector<string>(n, ""));
    cout << longestpalindromeSUBSEQUENCE(str, i, j, dp) << endl;
    // cout << longestpalindromeSUBSEQUENCEDP(str, 0, n - 1, dp, sdp) << endl;
    // print2d(dp);
    // cout<<sdp[0][n-1];
    generateString(dp, i, j, str, fl, bl);
    cout << endl;
    // print2d(dp);
    // generateString2(dp, i, j, str, "", "");      //backengineering
}

//https://practice.geeksforgeeks.org/problems/count-palindromic-subsequences/1
int countPalindromicSubsequence(int i, int j, string s, vector<vector<int>> &dp)
{
    if (i == j || i > j)
        return dp[i][j] = i > j ? 0 : 1;

    if (dp[i][j] != -1)
        return dp[i][j];

    int X = countPalindromicSubsequence(i + 1, j - 1, s, dp);
    int Y = countPalindromicSubsequence(i, j - 1, s, dp);
    int Z = countPalindromicSubsequence(i + 1, j, s, dp);

    return dp[i][j] = s[i] == s[j] ? (X + 1) + (Y + Z - X) : Y + Z - X;
}

//******initialise with 0
int countPalindromicSubsequenceDP(string s, vector<vector<int>> &dp)
{
    int n = s.length();
    for (int gap = 0; gap < n; gap++)
    {
        for (int i = 0, j = gap; j < n; j++, i++)
        {
            if (i == j || i > j)
            {
                dp[i][j] = i > j ? 0 : 1;
                continue;
            }

            int X = dp[i + 1][j - 1];
            int Y = dp[i][j - 1];
            int Z = dp[i + 1][j];

            dp[i][j] = s[i] == s[j] ? (X + 1) + (Y + Z - X) : Y + Z - X;
        }
    }
    return dp[0][s.length() - 1];
}

void countPalindromicSubsequenceProb()
{
    string str = "abcd";
    // string str = "abccbdaa";
    int n = str.length();
    // vector<vector<int>> dp(n, vector<int>(n, -1));
    vector<vector<int>> dp(n, vector<int>(n, 0));
    // cout << countPalindromicSubsequence(0, n - 1, str, dp) << endl;
    cout << countPalindromicSubsequenceDP(str, dp) << endl;
    print2d(dp);
}

// https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/dynamic-programming/cps-official/ojquestion, equal = y+z+1
public static int countPalindromicSubsequences(String s) {
    int n = s.length();
    int[][] dp = new int[n][n];
    for (int gap = 0; gap < n; gap++) {
        for (int i = 0, j = gap; j < n; i++, j++) {
            if (gap == 0)
                dp[i][j] = 1;
            else if (gap == 1) {
                dp[i][j] = s.charAt(i) == s.charAt(j) ? 3 : 2;
            } else {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i][j - 1] + dp[i + 1][j] + 1;
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];
                }
            }
        }
    }
    return dp[0][n - 1];
}

//30 November
//115
int distinctSubsequences(int n, int m, vector<vector<int>> &dp, string s, string t)
{
    if (m == 0 || n < m)
        return dp[n][m] = n < m ? 0 : 1;

    if (dp[n][m] != -1)
        return dp[n][m];

    int count = 0;
    if (s[n - 1] == t[m - 1])
    {
        count += distinctSubsequences(n - 1, m - 1, dp, s, t);
        count += distinctSubsequences(n - 1, m, dp, s, t);
    }
    else
        count += distinctSubsequences(n - 1, m, dp, s, t);
    return dp[n][m] = count;
}

int distinctSubsequencesDP(int N, int M, vector<vector<int>> &dp, string s, string t)
{
    for (int n = 0; n <= N; n++)
    {
        for (int m = 0; m <= M; m++)
        {
            if (m == 0 || n < m)
            {
                dp[n][m] = n < m ? 0 : 1;
                continue;
            }

            int count = 0;
            if (s[n - 1] == t[m - 1])
            {
                count += dp[n - 1][m - 1];
                count += dp[n - 1][m];
            }
            else
                count += dp[n - 1][m];
            dp[n][m] = count;
        }
    }
    return dp[N][M];
}

void DistinctSubsequencesProb()
{
    string s = "geksfogeks";
    string t = "gks";
    int n = s.length();
    int m = t.length();
    vector<vector<int>> dp(n + 1, vector<int>(m + 1, -1));
    // cout << distinctSubsequences(n, m, dp, s, t) << endl;
    cout << distinctSubsequencesDP(n, m, dp, s, t) << endl;
    print2d(dp);
}

//1143
int longestCommonSubsequence(int n, int m, string s1, string s2, vector<vector<int>> &dp)
{
    if (n == 0 || m == 0)
        return dp[n][m] = 0;

    if (dp[n][m] != -1)
        return dp[n][m];

    int length = 0;
    if (s1[n - 1] == s2[m - 1])
        length = longestCommonSubsequence(n - 1, m - 1, s1, s2, dp) + 1;
    else
        length = max(longestCommonSubsequence(n - 1, m, s1, s2, dp), longestCommonSubsequence(n, m - 1, s1, s2, dp));
    return dp[n][m] = length;
}

int longestCommonSubsequenceDP(int N, int M, string s1, string s2, vector<vector<int>> &dp)
{
    for (int n = 0; n <= N; n++)
    {
        for (int m = 0; m <= M; m++)
        {
            if (n == 0 || m == 0)
            {
                dp[n][m] = 0;
                continue;
            }

            int length = 0;
            if (s1[n - 1] == s2[m - 1])
                length = dp[n - 1][m - 1] + 1;
            else
                length = max(dp[n - 1][m], dp[n][m - 1]);
            dp[n][m] = length;
        }
    }
    return dp[N][M];
}

void longestCommonSubsequenceProb()
{
    string s1 = "abcde";
    string s2 = "ace";
    int n = s1.length(), m = s2.length();
    vector<vector<int>> dp(n + 1, vector<int>(m + 1, -1));
    // cout << longestCommonSubsequence(n, m, s1, s2, dp) << endl;
    cout << longestCommonSubsequenceDP(n, m, s1, s2, dp) << endl;
    print2d(dp);
}

//392-dp not reqd yc
int isSubsequence(string s, string t, vector<vector<int>> &dp, int n, int m)
{
    if (n == 0 || m == 0)
        return dp[n][m] = (m == 0 ? 1 : 0);

    if (dp[n][m] != -1)
        return dp[n][m];

    bool res = false;
    if (t[n - 1] == s[m - 1])
        res = res || (isSubsequence(s, t, dp, n - 1, m - 1) == 1);
    else
        res = res || (isSubsequence(s, t, dp, n - 1, m) == 1);

    return dp[n][m] = (res == true ? 1 : 0);
}

bool isSubsequence(string s, string t)
{
    int n = t.length();
    int m = s.length();

    vector<vector<int>> dp(n + 1, vector<int>(m+1, -1));
    return (isSubsequence(s, t, dp, n, m) == 1);
}

//1035-We write the integers of nums1 and nums2 (in the order they are given) on two separate horizontal lines.
int uncrossedLine(int n, int m, vector<int> &A, vector<int> &B, vector<vector<int>> &dp)
{
    if (n == 0 || m == 0)
        return dp[n][m] = 0;

    if (dp[n][m] != -1)
        return dp[n][m];

    int count = 0;
    if (A[n - 1] == B[m - 1])
        count = uncrossedLine(n - 1, m - 1, A, B, dp) + 1;
    else
        count = max(uncrossedLine(n - 1, m, A, B, dp), uncrossedLine(n, m - 1, A, B, dp));
    return dp[n][m] = count;
}

int uncrossedLineDP(int N, int M, vector<int> &A, vector<int> &B, vector<vector<int>> &dp)
{
    for (int n = 0; n <= N; n++)
    {
        for (int m = 0; m <= M; m++)
        {
            if (n == 0 || m == 0)
            {
                dp[n][m] = 0;
                continue;
            }

            int count = 0;
            if (A[n - 1] == B[m - 1])
                count = dp[n - 1][m - 1] + 1;
            else
                count = max(dp[n - 1][m], dp[n][m - 1]);
            dp[n][m] = count;
        }
    }
    return dp[N][M];
}

void uncrossedLinesProb()
{
    vector<int> A = {1, 4, 2};
    vector<int> B = {1, 2, 4};
    int n = A.size(), m = B.size();
    vector<vector<int>> dp(n + 1, vector<int>(m + 1, -1));
    // cout << uncrossedLine(n, m, A, B, dp) << endl;
    cout << uncrossedLineDP(n, m, A, B, dp) << endl;
}

//72
int minimumConversion(int n, int m, string s, string t, vector<vector<int>> &dp)
{
    if (n == 0 || m == 0)
        return dp[n][m] = n != 0 ? n : m;

    if (dp[n][m] != 0)
        return dp[n][m];

    if (s[n - 1] == t[m - 1])
        dp[n][m] = minimumConversion(n - 1, m - 1, s, t, dp);
    else
    {
        dp[n][m] = 1 + min(min(minimumConversion(n, m - 1, s, t, dp), minimumConversion(n - 1, m, s, t, dp)), minimumConversion(n - 1, m - 1, s, t, dp));
    }
    return dp[n][m];
}

//*******initialise dp with 0 in tabulation when we are using those index.
int minimumConversionDP(int N, int M, string s, string t, vector<vector<int>> &dp)
{
    for (int n = 0; n <= N; n++)
    {
        for (int m = 0; m <= M; m++)
        {
            if (n == 0 || m == 0)
            {
                dp[n][m] = n != 0 ? n : m;
                continue;
            }

            if (s[n - 1] == t[m - 1])
                dp[n][m] = dp[n - 1][m - 1];
            else
                dp[n][m] = 1 + min(min(dp[n][m - 1], dp[n - 1][m]), dp[n - 1][m - 1]);
        }
    }
    return dp[N][M];
}

void minimumConversionProb()
{
    string s = "intention";
    string t = "execution";
    int n = s.length(), m = t.length();
    vector<vector<int>> dp(n + 1, vector<int>(m + 1, 0));
    // cout << minimumConversion(n, m, s, t, dp) << endl;
    cout << minimumConversionDP(n, m, s, t, dp) << endl;
}

//44
int wildCardMatching(int n, int m, string s, string p, vector<vector<int>> &dp)
{
    if (n == 0 || m == 0)
    {
        if (n == 0 && m == 0)
            return dp[n][m] = 1;
        else if (m == 1 && p[m - 1] == '*')
            return dp[n][m] = 1;
        return dp[n][m] = 0;
    }
 
    if (dp[n][m] != -1)
        return dp[n][m];

    char ch1 = s[n - 1];
    char ch2 = p[m - 1];
    int val = -1;
    if (ch1 == ch2 || ch2 == '?')
        val = wildCardMatching(n - 1, m - 1, s, p, dp);
    else if(ch2 == '*'){
            val = wildCardMatching(n,m-1,s,p,dp)|| wildCardMatching(n-1,m,s,p,dp);
    }
    else
        val = 0; //na equal na question mark na star

    return dp[n][m] = val;
}

string compressString(string str)
{
    if (str.length() == 0)
        return ""; //for test case s="",p=""
    int n = str.length(), i = 1;
    string compressedString = "";
    compressedString += str[0];
    while (i < n)
    {
        while (i < n && str[i - 1] == '*' && str[i] == '*')i++;
        if (i < str.length()) //check else empty appended...size dkhoge *** tou 2 without this chk
            compressedString += str[i];
        i++;
    }
    return compressedString;
}

void wildCardMatchingProb()
{
    string s = "";
    string p = "***";
    p = compressString(p);
    int n = s.length(), m = p.length();
    cout << p << n << m << endl;
    vector<vector<int>> dp(n + 1, vector<int>(m + 1, -1)); //dp integer type because true and false both answer cant make dp of boolean
    // bool result = wildCardMatching(n, m, s, p, dp);
    // cout << wildCardMatching(n, m, s, p, dp);
}

int main()
{
    // countpalindromicsubstringsprob();
    // longestpalindromicsubstringProb();
    // longestpalindromeSUBSEQUENCEProb();
    // DistinctSubsequencesProb();
    // countPalindromicSubsequenceProb();
    // longestCommonSubsequenceProb();
    // uncrossedLinesProb();
    // minimumConversionProb();
    // wildCardMatchingProb();
    return 0;
}

// https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/dynamic-programming/longest-common-substring-official/ojquestion
public static int LongestCommonSubstring(String s1, String s2) {
    int[][] dp = new int[s1.length() + 1][s2.length() + 1];

    int max = 0;
    for (int i = 1; i <= s1.length(); i++) {
        for (int j = 1; j <= s2.length(); j++) {
            char c1 = s1.charAt(i - 1);
            char c2 = s2.charAt(j - 1);

            if (c1 == c2) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            } else
                dp[i][j] = 0;
                
            if (dp[i][j] > max)
                max = dp[i][j];
        }
    }
    return max;
}


// =========
//583
int minCostToMakeStringIdentical(string word1, string word2)
{
    int n = word1.length();
    int m = word2.length();

    vector<vector<int>> dp(n + 1, vector<int>(m + 1, -1));
    int lcsLength = longestCommonSubsequence(n, m, word1, word2, dp);

    return n - lcsLength + m - lcsLength;
}

//940
int mod=(int)1e9+7;
public int distinctSubseqII(String str) {
    int n=str.length();
    int []dp=new int[n+1];
    HashMap<Character,Integer>map=new HashMap<>();
    
    dp[0]=1;        //no character 1 subseqeunce
    for(int i=1;i<dp.length;i++)
    {
        dp[i]=(2*dp[i-1])%mod;
        char ch=str.charAt(i-1);
        
        if(map.containsKey(ch))
        dp[i]=(dp[i]%mod-dp[map.get(ch)-1]%mod + mod)%mod;
        
        map.put(ch,i);
    }
    return dp[n]%mod-1;//-1 for empty
}

public static boolean wordBreak(String sentence, HashSet<String> dictionary) {
    int n = sentence.length();
    int[] dp = new int[n];
    for (int i = 0; i < sentence.length(); i++) {
        for (int j = 0; j <= i; j++) {
            String str = sentence.substring(j, i + 1);
            if (dictionary.contains(str)) {
                if (j > 0)
                    dp[i] += dp[j - 1];
                else
                    dp[i] += 1;
            }  
        }
    }
    // for(int i=0;i<n;i++)
    // System.out.print(dp[i]+" ");
    return dp[n - 1] != 0 ? true : false;
} 

//730 leetcode do it by mod
public static int distinctPalindromicSubsequence(String s) {  
    
    int n=s.length();
    int [][]dp=new int[n][n];
    
    HashMap<Character,Integer>map=new HashMap<>();
    int []prev=new int[n];
    for(int i=0;i<n;i++)
    {
        char ch=s.charAt(i);
        if(map.containsKey(ch)==false)
            prev[i]=-1;
        else prev[i]=map.get(ch);
        
        map.put(ch,i);
    }
    
    map.clear();
    int []next=new int[n];
    for(int i=n-1;i>=0;i--)
    {
        char ch=s.charAt(i);
        if(map.containsKey(ch)==false)
            next[i]=n;
        else next[i]=map.get(ch);
        
        map.put(ch,i);
    }
    
    for(int gap=0;gap<n;gap++)
    {
        for(int i=0,j=gap;j<n;j++,i++)
        {
            if(gap==0)dp[i][j]=1;
            else if(gap==1)dp[i][j]=2;
            else{
                
                char ch1=s.charAt(i);
                char ch2=s.charAt(j);
                
                if(ch1!=ch2)
                    dp[i][j]=dp[i+1][j]+dp[i][j-1]-dp[i+1][j-1];
                else{
                    
                    int ne=next[i];
                    int pr=prev[j];
                    
                    if(ne==pr)
                        dp[i][j]=2*dp[i+1][j-1]+1;
                    else if(ne<pr)
                        dp[i][j]=2*dp[i+1][j-1]-dp[ne+1][pr-1];
                    else
                        dp[i][j]=2*dp[i+1][j-1]+2;
                }
            }
        }
    }
    return dp[0][n-1];
}

public static boolean regularExpression(String s, String p) {
    int n = p.length();
    int m = s.length();
    boolean[][] dp = new boolean[n + 1][m + 1];

    for (int i = 0; i <= n; i++) {
        for (int j = 0; j <= m; j++) {
            if (i == 0 && j == 0)
                dp[i][j] = true;
            else if (i == 0)
                dp[i][j] = false;
            else if (j == 0) {
                char ch = p.charAt(i - 1);
                if (ch == '*')
                    dp[i][j] = dp[i - 2][j];
                else
                    dp[i][j] = false;
            } else {
                char ch1 = p.charAt(i - 1);
                char ch2 = s.charAt(j - 1);

                if (ch1 == '*') {
                    dp[i][j] = dp[i - 2][j];

                    if (dp[i][j] == false) {
                        char ch3 = p.charAt(i - 2);
                        if (ch3 == ch2 || ch3 == '.')
                            dp[i][j] = dp[i][j - 1];
                    }
                } else if (ch1 == ch2 || ch1 == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else
                    dp[i][j] = false;
            }
        }
    }
    return dp[n][m];
}

//===========


/*
===========String
14.(1)palindromic substring-dp[s.length][s.length]
                        Gap strategy outer loop gap=0 to n 
                        2.inner loop i=0,j=gap;till j<n(i++,j++)
                        3.gap=0 true,gap=1 check if i & j character same(true) or not(false),else if(s[i]==s[j] && dp[i+1]==dp[j-1])?t:f

                        in Count-all process same if(dp[i][j]==t)count++

15.(1)longest palindromic substring-gap=0 dp[i][j]=1,
                                gap=1 if (s[i]==s[j]) dp[i][j]=2,
                                else if(s[i]==s[j] && inner length!=-1)dp[i][j]=inner length+2
                                update length,ei,si
                                r->substr

16.(4)longest palindromic subsequence-M1:1.bc:i==j r->1 i>j r->0
                                    2.s[i]==s[j] length=call(i+1,j-1)+2;
                                      else length=max(call(i+1,j),call(i,j-1))
                                    3.dp[i][j]=length

            M2-gap strategy
               1.make dp,for ans make sdp,in base case if i==j sdp[i][j]=s[i] and if equal sdp[i][j]=sdp[i]+sdp[i+1][j-1]+s[i]
               2.if(dp[i+1][j]>dp[i][j-1])sdp[i][j]=sdp[i+1][j] else..
               3.put length in dp[i][j]
               4.r->dp[0][s.length()-1]

            M3- 1.same base case add in whichever list if i==j add print and pop and return
                2.Pass filled dp....Take two ll-bl and fl,if char(i,j) equal add in bl(pushback) and add in fl(pushfront) call for(i+1,j-1),& popback frm both
                3.else whichever side length greater call for that length else call for both

            M4-Take string and do same like linkedlist(add at back,add at front) ...in base case 
            add wherver...no need to remove

17.(2)Count Palindromic Subsequence-bc:i>j r->0 || i==j r->1
                                2.call(i+1,j-1),call(i+1,j),call(i,j-1)
                                3.r->dp[i][j]=s[i],s[j] equal then y+z+1 else y+z-x  .........Gap strategy r->dp[0][s.length]

18*.(2)distinct subsequence-M1:Code in terms of length from back..1.dp[n+1][m+1](-1)
                            2.bc:m==0 r->1..if n<m r->0
                            3.if ch same(n-1,m-1) c+=two calls->call(n-1,m-1) & call(n-1,m)
                              else c+=call(n-1,m);
                            4.r->dp[n][m]=c
                M2-outer loop(n 0 to N) and inner(m 0 to M)
                    r->dp[N][M]

19.(2)UnCrossed Line/Longest common Subsequence-Code from back---M1:bc:n==0||m==0 r->0
                                2.if equal call(n-1,m-1)
                                  2.2 max(call(n-1,m),call(n,m-1))
                                3.dp[n][m]=length
                        M2-same as distinct subs
    
    Q.)if p is subseq of s-bc:if n==0||m==0 if m r->t else r->f
                            2.if ch equal res||call(n-1,m-1)
                            else res||call(n-1,m)
                            3.store dp[n][m]=1(t)/0(f)..default value -1 in dp

20*.(2)Minimum Conversion-1.n==0||m==0 whichever not 0 return that value
                    2.if char equal call(n-1,m-1)
                    else call=1+min(c(n-1,m)[delete],c(n,m-1)[insert],call(n-1,m-1))
                M2-same as above

21.(1)WildCard Matching-Code from back..1.Compressed pattern string-handle zero length,add first char,i=1
                            1.2 iterate i till < length
                                1.3 while(i<len & (prev & cur=*)i++
                                1.4 if(i<len)str+=p[i++]
                    2.dp(n+1,m+1,-1)...
                    3.bc:n==0||m==0 if both string len 0 r-->1
                        3.1 if m==1 && star r->1 else r->0
                    4.Take out char if(ch1==ch2||ch2=='?')call(n-1,m-1)
                        4.2 if ch2 * take bool variable and res=res|call(n-1,m)
                            res=res|call(n,m-1)..if res true then val=1
                    5.else val=0..r->dp[n][m]=val
*/