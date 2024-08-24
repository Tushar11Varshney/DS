import java.util.*;

public class strings {   

//1844
string replaceDigits(string s)
{
    for (int i = 1; i < s.length(); i += 2)
    {
        s[i]=(s[i-1]+(s[i]-'0'));  //java mein char mein typecast
    }
    return s;
}

// 1880
public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
    int numFirst = 0;
    // String numFirst="";
    for (int i = 0; i < firstWord.length(); i++) {
        // numFirst+=firstWord.charAt(i)-'a';
        numFirst = numFirst * 10 + (firstWord.charAt(i) - 'a');
    }

    int numSecond = 0;
    for (int i = 0; i < secondWord.length(); i++) 
        numSecond = numSecond * 10 + (secondWord.charAt(i) - 'a');

    int numTarget = 0;
    for (int i = 0; i < targetWord.length(); i++)
        numTarget = numTarget * 10 + (targetWord.charAt(i) - 'a');

    // System.out.println(numFirst+" "+numSecond+" "+numTarget); 
    // if(Integer.parseInt(numFirst)+Integer.parseInt(numSecond)==Integer.parseInt(numTarget))
    if (numFirst + numSecond == numTarget)
        return true;
    return false;
}

// 412
public List<String> fizzBuzz(int n) {
    List<String> ans = new ArrayList<>();  // return a string array answer (1-indexed)
    for (int i = 0; i < n; i++) {
        if ((i + 1) % 3 == 0 && (i + 1) % 5 == 0)
            ans.add("FizzBuzz");
        else if ((i + 1) % 3 == 0)
            ans.add("Fizz");
        else if ((i + 1) % 5 == 0)
            ans.add("Buzz");
        else
            ans.add((i + 1) + "");
    }
    return ans;
}

// string vs stringbuilder(immutable/slow -- mutable/fast)..just like arrayList
public static void StringBuilderVSString() {
    // String str1="abcd tushar huly";
    // String arr[]=str1.split(" ");
    // for(int i=0;i<arr.length;i++) //arr ek normal array h string ki so length not length()
    // System.out.println(arr[i]);

    // StringBuilder sb=new StringBuilder("hello");
    // System.out.println(sb.append(" append"));
    // System.out.println(sb.insert(2," insert")); //2 index par
    // sb.setCharAt(0, '9');
    // System.out.println(sb);  //hello
    // System.out.println(sb.deleteCharAt(4)); //4th index waala


    // String s="";
    // StringBuilder sb=new StringBuilder("");
    // long start=System.currentTimeMillis();
    // for(int i=0;i<1000000;i++)
    // // s+=i;
    // sb.append(i);
    // long end=System.currentTimeMillis();
    // long duration=end-start;
    // System.out.println(duration);
}

public static void allsubstring() {
    String str = "abcd";
    for (int i = 0; i < str.length(); i++)
        for (int j = i + 1; j <= str.length(); j++)
            System.out.println(str.substring(i, j));  
}

public static boolean isPalindromic(String str) {
    int i = 0, j = str.length() - 1;
    while (i < j) {
        char ch1 = str.charAt(i);
        char ch2 = str.charAt(j);
        if (ch1 == ch2) // ye tou character hain inme equals vgra ni hota check directly
        {
            i++;
            j--;
        } else return false;
    }
    return true;
}

public static void palindromicSubstring(String str) {
    for (int i = 0; i <str.length(); i++) {
        for (int j = i + 1; j <= str.length(); j++) {
            if (isPalindromic(str.substring(i, j))) { 
                System.out.println(str.substring(i, j));
            }
        }
    }
}

public static String toggleCase(String str) {
    // String str1 = "";
    // for (int i = 0; i < str.length(); i++) {
        // char ch = str.charAt(i);
        // if (ch >= 65 && ch <= 90)
        // ch += 32;
        // else if (ch >= 97 && ch <= 122)
        // ch -= 32;
    
        // str1 += ch;
    // }
    // return str1;

    StringBuilder sb = new StringBuilder(str);
    for (int i = 0; i < sb.length(); i++) {
        char ch = sb.charAt(i);
        if (ch >= 'A' && ch <= 'Z') {
            char lc = (char) (ch + 'a' - 'A');
            sb.setCharAt(i, lc);
        } else if (ch >= 'a' && ch <= 'z') {
            char uc = (char) (ch + 'A' - 'a');
            sb.setCharAt(i, uc);
        }
    }
    return sb.toString();
}



// ib
public boolean isVowel(char ch) {
    if (ch == 'A' || ch == 'a' || ch == 'E' || ch == 'e' || ch == 'I' || ch == 'i' || ch == 'O' || ch == 'o'
            || ch == 'U' || ch == 'u')return true;
    return false;
}

public int amazingSubstring(String A) { // substring which start with vowel
    int n = A.length();
    int ans = 0;
    int mod = 10003;
    for (int i = 0; i < n; i++) {
        if (isVowel(A.charAt(i))) {
            ans+=(n-i)%mod;  //just like eg:mod=10 then ans=5+6..then at return time it makes 1
            //(a+b)%mod=(a%mod+b%mod)%mod;
        }
    }

    return ans%mod;
}

public static String stringwithASCIIDiffernce(String str) {
    StringBuilder sb=new StringBuilder();
    for(int i=0;i<str.length()-1;i++)
    {
        int differnce=str.charAt(i+1)-str.charAt(i);
        // sb.append(str.charAt(i)+Integer.toString(differnce));
        sb.append(str.charAt(i));  
        sb.append(differnce);
        //cant do sb.append(str.charAt(i)+differnce) output galat "abc"->9899c
    }
    sb.append(str.charAt(str.length()-1));
    return sb.toString();  //null allowed 
}

// 13
public int romanToInt(String s) {
    int number = 0;
    for (int i = 0; i < s.length(); i++) {
        char ch = s.charAt(i);
        if (ch == 'I')
            number += 1;
        else if (ch == 'V') {
            number += 5;
            if (i - 1 >= 0 && s.charAt(i - 1) == 'I')
                number -= 2;
        } else if (ch == 'X') {
            number += 10;
            if (i - 1 >= 0 && s.charAt(i - 1) == 'I')
                number -= 2;
        } else if (ch == 'L') {
            number += 50;
            if (i - 1 >= 0 && s.charAt(i - 1) == 'X')
                number -= 20;
        } else if (ch == 'C') {
            number += 100;
            if (i - 1 >= 0 && s.charAt(i - 1) == 'X')
                number -= 20;
        } else if (ch == 'D') {
            number += 500;
            if (i - 1 >= 0 && s.charAt(i - 1) == 'C')
                number -= 200;
        } else if (ch == 'M') {
            number += 1000;
            if (i - 1 >= 0 && s.charAt(i - 1) == 'C')
                number -= 200;
        }
    }

    return number;
}

// 13
public int myValue(char ch) {
    if (ch == 'I') 
        return 1;
    else if (ch == 'V')
        return 5;
    else if (ch == 'X')
        return 10;
    else if (ch == 'L')
        return 50;
    else if (ch == 'C')
        return 100;
    else if (ch == 'D')
        return 500;
    else if (ch == 'M')
        return 1000;

    return 0;
}

public int romanToInt2(String s) {
    int number = 0;
    for (int i = 0; i < s.length(); i++) {
        if (i + 1 < s.length() && myValue(s.charAt(i)) < myValue(s.charAt(i + 1))) {
            number += myValue(s.charAt(i + 1)) - myValue(s.charAt(i));
            i = i + 1;
        } else  number += myValue(s.charAt(i));
    }
    return number;
}

//165
int compareVersion(string version1, string version2)
{
    int i = 0, j = 0;
    int v1Length = version1.length(), v2Length = version2.length();

    while (i < v1Length || j < v2Length)
    {
        int temp1 = 0;
        int temp2 = 0;

        while (i < v1Length && version1[i] != '.')
            temp1 = temp1 * 10 + (version1[i++] - '0');

        while (j < v2Length && version2[j] != '.')
            temp2 = temp2 * 10 + (version2[j++] - '0');

        if (temp1 > temp2)
            return 1;
        else if (temp1 < temp2)
            return -1;
        else
        {
            i++;
            j++;
        }
    }

    return 0;
}

// 1881
public String maxValue(String n, int x) {
    if (n == null || n.length() == 0)
        return x + "";
    char ch = n.charAt(0);
    if (ch != '-') {
        for (int i = 0; i < n.length(); i++) {
            if (x > (n.charAt(i) - '0'))
                return n.substring(0, i) + x + n.substring(i);
        }
        return n + x;
    } else {
        for (int i = 1; i < n.length(); i++) {
            if (x < (n.charAt(i) - '0'))
                return n.substring(0, i) + x + n.substring(i);
        }
        return n + x;
    }
}



public static int factorial(int n) {
    if (n <= 1)
        return 1;
    else
        return n * factorial(n - 1);
}

public static void permutation(String str) {
    int n = str.length();
    int totalPermutation = factorial(n);
    for (int i = 0; i < totalPermutation; i++) {
        StringBuilder originalString = new StringBuilder(str);
        StringBuilder sb = new StringBuilder("");
        int m = n, temp = i;
        while (m > 0) {
            int quo = temp / m;
            int index = temp % m;
            sb.append(originalString.charAt(index));
            originalString.deleteCharAt(index);
            m--;
            temp = quo;
        }
        System.out.println(sb);
    }
}

public static String compression1(String str) {
    int n = str.length();
    String str1 = "";
    for (int i = 0; i < n; i++) {
        int j = i + 1;
        while (j < n && str.charAt(i) == str.charAt(j))j++;
        if (j - i > 1) {
            str1 += str.charAt(i);
            str1 += j - i;  //in java chlega
        } else str1 += str.charAt(i);
        i = j - 1;
    }
    return str1;
}

public static String compression2(String str) {
    int n = str.length();
    String str1 = "";
    for (int i = 0; i < n; i++) {
        int j = i + 1;
        while (j < n && str.charAt(i) == str.charAt(j))
            j++;
        str1 += str.charAt(i);

        i = j - 1;
    }
    return str1;
}


//925
bool isLongPressedName(string name, string typed) {
    int i=0,j=0;
    while(i<name.size())
    {
        char ch=name[i];
        int count_i=0;
        while(i<name.size() && name[i]==ch)
        {
            count_i++;
            i++;
        }
        
        int count_j=0;
        while(j<typed.size() && typed[j]==ch){
            count_j++;
            j++;
        }
        if(count_j<count_i)return false;
    }
    return j==typed.size()?true:false;  //"alex" "aaleexa"
}

// 38
public String countAndSay(int n) {
    if (n == 1)
        return "1";

    StringBuilder sb = new StringBuilder("1");
    for (int i = 2; i <= n; i++) {
        int si = 0, ei = 0;
        StringBuilder ans = new StringBuilder();
        while (ei < sb.length()) {
            char lc = sb.charAt(si);
            while (ei < sb.length() && sb.charAt(ei) == lc)ei++;
            ans.append(Integer.toString(ei - si) + lc); //ya alg alg line mein simple add 
            si = ei;
        }

        sb = ans;
    }
    return sb.toString();
}


// 1876-good string if no repeated character
int countGoodSubstrings(string s)
{
    vector<int> arr(26);

    int si = 0, ei = 0, count = 0, repeat=0;
    while (ei < s.length())
    {
        int ch = s[ei] - 'a';
        if (arr[s[ei++] - 'a']++ == 1)
        repeat++;
        while(repeat>0)
        {
            if(arr[s[si++] - 'a']-- == 2)
            repeat--;
        }

        if (ei - si == 3)
        {
            arr[s[si++] - 'a']--;
            count++;
        }
    }
    return count;
}

int countGoodSubstrings(string s)
{
    if (s.length() < 3)
        return 0;
    int total = 0;
    for (int i = 0; i < s.length() - 2; i++)
    {
        if (s[i] != s[i + 1] && s[i + 1] != s[i + 2] && s[i] != s[i + 2])
            total++;
    }

    return total;
}


//1869
bool checkZeroOnes(string s)
{
    int si = 0, ei = 0;
    int zeroMl = 0, oneMl = 0;
    while (ei < s.length())
    {
        while (ei < s.length() && s[ei] == '1')
            ei++;
        oneMl = max(oneMl, ei - si);

        si = ei;
        while (ei < s.length() && s[ei] == '0')
            ei++;
        zeroMl = max(zeroMl, ei - si);
        si = ei;
    }

    return oneMl > zeroMl;
}

// 58
public int lengthOfLastWord(String s) {
    int sz = s.length();

    int lastSpace = -1;
    for (int i = 0; i < sz; i++) {
        if (s.charAt(i) == ' ')
            lastSpace = i;
    }

    int length = 0;
    for (int i = lastSpace + 1; i < sz; i++)
        length++;

    if (length == 0) {
        for (int i = lastSpace - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ')
                continue;
            else {
                while (i >= 0 && s.charAt(i--) != ' ')
                    length++;
                break;
            }
        }
    }
    return length;
}

public int lengthOfLastWord_(String s) {
    int tail = s.length() - 1;
    int len = 0;
    while (tail >= 0 && s.charAt(tail) == ' ')
        tail--;
    while (tail >= 0 && s.charAt(tail) != ' ') {
        len++;
        tail--;
    }

    return len;
}

// 65
public boolean ValidNumber(String s) {
    boolean pointSeen = false;
    boolean numSeen = false;
    boolean eSeen = false;

    for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            numSeen = true;
        } else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            if (i != 0 && s.charAt(i - 1) != 'e')
                return false;
        } else if (s.charAt(i) == 'e' || s.charAt(i) == 'E') {
            if (eSeen || !numSeen)
                return false;
            eSeen = true;
            numSeen = false;
        } else if (s.charAt(i) == '.') {
            if (pointSeen || eSeen)
                return false;
            pointSeen = true;
        } else return false; // for any other char other than above
    }
    return numSeen;
}

 // 468
public String validIPAddress(String IP) {
    // "7334:"" ye 7334 and empty me toot ta h -]\1 se
    String[] arrOfStr1 = IP.split("\\.", -1);
    String[] arrOfStr2 = IP.split("\\:", -1);  //-1 se pattern applied as many time as psble,0 se trailing empty string discarded

    if (arrOfStr1.length == 4) { 
        for (int i = 0; i < arrOfStr1.length; i++) {
            String str = arrOfStr1[i];
            int n = arrOfStr1[i].length();  //if n=1 then '0' is allowed
            if (n == 1 && (!(str.charAt(0) >= '0' && str.charAt(0) <= '9'))) return "Neither";
            else if (n >= 2 && n <= 3) {
                if (str.charAt(0) == '0')return "Neither";
                int num = str.charAt(0) - '0';
                for (int j = 1; j < n; j++) {
                    if (!(str.charAt(j) >= '0' && str.charAt(j) <= '9'))
                        return "Neither";
                    num = num * 10 + (str.charAt(j) - '0');
                }
                if (num >= 256)return "Neither";
            } else if (n > 3 || n == 0)return "Neither"; // 1.1.1.
        }
        return "IPv4";
    } 
    else if (arrOfStr2.length == 8) {
        for (int i = 0; i < arrOfStr2.length; i++) {
            String s = arrOfStr2[i];
            int n = arrOfStr2[i].length();
            if (n >= 1 && n <= 4) {
                for (int j = 0; j < n; j++) {
                    if (!((s.charAt(j) >= '0' && s.charAt(j) <= '9') || (s.charAt(j) >= 'a' && s.charAt(j) <= 'f')
                            || (s.charAt(j) >= 'A' && s.charAt(j) <= 'F')))
                        return "Neither";
                }
            } else if (n > 4 || n == 0)return "Neither";
        }
        return "IPv6";
    } else return "Neither";
}


//8
int myAtoi(string s)
{
    long number = 0;
    int size = s.length(), sign = 0, i=0;
    while(i < s.length())
    {
        if (s[i] == ' ')  //ignore leading whitespace
        {
            i++;
            continue;
        }
        else if (s[i] == '-' || s[i] == '+')
        {
            if (sign != 0)
                break; //+-12 answer 0
            sign = s[i] == '-' ? -1 : 1;
            if (i + 1 < s.length() && !(s[i + 1] >= '0' && s[i + 1] <= '9'))
                break; //"  +  413" ans->0
            i++;
        }
        else if(s[i]>='0' && s[i]<='9')
        {
            while (i < s.length() && (s[i] >= '0' && s[i] <= '9'))
            {
                number = number * 10 + (s[i] - '0');
                if ((sign == -1 && number < INT_MIN) || (number > INT_MAX))
                {
                    if (sign == -1)
                        number = INT_MIN;
                    else
                        number = INT_MAX;
                    break;
                }
                i++;
            }
            break;
        }
        else break;
    }
    if (sign == 0)
        sign = 1; //42
    return number * sign;
}

//1312
int minInsertions(string s)
{
    int n = s.length();
    vector<vector<int>> dp(n, vector<int>(n, 0)); //initialse with -1 chalta hai recursion mein

    for (int gap = 0; gap < n; gap++)
    {
        for (int i = 0, j = gap; j < n; i++, j++)
        {
            if(i==j)
            {
                dp[i][j]=0;
                continue;
            }

            if (s[i] == s[j])
                dp[i][j] = dp[i + 1][j - 1];
            else
                dp[i][j] = min(dp[i][j - 1], dp[i + 1][j]) + 1;
        }
    }

    return dp[0][n - 1];
}

//======================

int minimumAppendForPalindrome(string A)
{
    // 1 <= string len <= 10^5 so memory limit exceed agar dp bnayi then check kra function se ki palindrome hai ya ni aaage ki string(in both case iterarive/recursive mle)

    //if without dp we check for each character that palindromic/not then worst case n^2
    string rev = A;
    reverse(rev.begin(), rev.end());
    return A.length() - lps(rev + "$" + A);  //agr pata lgjaaye piche se string kitni palindromic hai tou length-lps[length()-1] is answer;
}

// 28
int strStr(string str, string find) {
    if(find.length()==0)return 0;
    vector<int>flps(find.length());
    
    int i=0,j=0;
    lps(find,flps);
    while(i<str.length())
    {
        if(str[i]==find[j])
        {
            i++;
            j++;
        }
        else if(j==find.length())break; //c++ aage wale address krleta hai even if dont belong
        else {
            if(j>0)
                j=flps[j-1];
            else
                i++;
        }
    }
    return j==find.length()?i-j:-1;
}

//686
public int repeatedStringMatch(String a, String b) {
    StringBuilder res=new StringBuilder(a);
    int nt=(int)Math.ceil(b.length()/a.length())+1;
    int count=1;
    while(nt-->0)
    {
        if(res.indexOf(b)!=-1)
            return count;
        res.append(a);
        count++;
    }
    if(res.indexOf(b)!=-1)
            return count;
    return -1;
}

//459
vector<int>lps(string s)
{
    vector<int>ans(s.length());
    int len=0,i=1;
    while(i<s.length())
    {
        if(s[i]==s[len])
        {
            len++;
            ans[i]=len;
            i++;
        }
        else{
            if(len>0)len=ans[len-1];
            else{
                ans[i]=0;
                i++;
            }
        }
    }
    return ans;
}

bool repeatedSubstringPattern(string s) {
    vector<int>res=lps(s);
    
    // for(int i=0;i<res.size()/2;i++)
    // {
    //     int j=i,jump=i+1,count=1;
    //     while(j<res.size())
    //     {
    //         if(j+jump<s.length() && res[j+jump]==count*jump)
    //         {
    //             j=j+jump;
    //             count++;
    //         }
    //         else break;
    //     }
    //     if(j==res.size()-1)return true;
    // }
    // return false;
    
    int n=s.length();
    int patlen=n-res[n-1];
    if(res[n-1]>0 && n%patlen==0)return true;
    return false;
}

//1332
int removePalindromeSub(string s) {
    int j=s.length()-1,i=0;
    
    while(i<j)
    {
        if(s[i]==s[j])
        {
            i++;j--;
        }
        else return 2;
    }
    return 1;
}


//1268
public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        
    int n=products.length;
    List<List<String>> result = new ArrayList<>();
    
    Arrays.sort(products);
    int l=0,r=n-1;
    for(int i=0;i<searchWord.length();i++)
    {
        List<String>small_ans=new ArrayList<>();
        char ch=searchWord.charAt(i);
        
        //Condition to skip
        while(l<=r && (products[l].length()<=i || products[l].charAt(i)!=ch))
            l++;
        while(l<=r && (products[r].length()<=i || products[r].charAt(i)!=ch))
            r--;
        
        int total=r-l+1;
        for(int j=0;j<Math.min(3,total);j++)
            small_ans.add(products[l+j]);
        
        result.add(small_ans);
    }
    
    return result;
}

{"upload_url":"https://github-production-user-asset-6210df.s3.amazonaws.com",
"header":{},
"asset":{
    "id":244842724,
    "name":"WhatsApp.Image.2022-11-15.at.21.08.08.jpeg","size":276633,
    "content_type":"image/jpeg",
    "href":"https://github.com/Tushar11Varshney/My-Diary/assets/65900578/40f34883-9973-440d-963a-b6dd72c28fe2",
    "original_name":"WhatsApp Image 2022-11-15 at 21.08.08.jpeg"
},
"form":{
    "key":"65900578/244842724-40f34883-9973-440d-963a-b6dd72c28fe2.jpeg",
    "acl":"public-read",
    "policy":"eyJleHBpcmF0aW9uIjoiMjAyMy0wNi0xMFQwMzoxNToyN1oiLCJjb25kaXRpb25zIjpbeyJidWNrZXQiOiJnaXRodWItcHJvZHVjdGlvbi11c2VyLWFzc2V0LTYyMTBkZiJ9LHsia2V5IjoiNjU5MDA1NzgvMjQ0ODQyNzI0LTQwZjM0ODgzLTk5NzMtNDQwZC05NjNhLWI2ZGQ3MmMyOGZlMi5qcGVnIn0seyJhY2wiOiJwdWJsaWMtcmVhZCJ9LFsiY29udGVudC1sZW5ndGgtcmFuZ2UiLDI3NjYzMywyNzY2MzNdLHsieC1hbXotY3JlZGVudGlhbCI6IkFLSUFJV05KWUFYNENTVkVINTNBLzIwMjMwNjEwL3VzLWVhc3QtMS9zMy9hd3M0X3JlcXVlc3QifSx7IngtYW16LWFsZ29yaXRobSI6IkFXUzQtSE1BQy1TSEEyNTYifSx7IngtYW16LWRhdGUiOiIyMDIzMDYxMFQwMDAwMDBaIn0seyJDb250ZW50LVR5cGUiOiJpbWFnZS9qcGVnIn0seyJDYWNoZS1Db250cm9sIjoibWF4LWFnZT0yNTkyMDAwIn0seyJ4LWFtei1tZXRhLVN1cnJvZ2F0ZS1Db250cm9sIjoibWF4LWFnZT0zMTU1NzYwMCJ9XX0=",
    "X-Amz-Algorithm":"AWS4-HMAC-SHA256",
    "X-Amz-Credential":"AKIAIWNJYAX4CSVEH53A/20230610/us-east-1/s3/aws4_request",
    "X-Amz-Date":"20230610T000000Z",
    "X-Amz-Signature":"cb8562d7ada3f004a3609044e5de934fbd52901cfadc1e8447ce2efb8f56d060","Content-Type":"image/jpeg",
    "Cache-Control":"max-age=2592000",
    "x-amz-meta-Surrogate-Control":"max-age=31557600"
},
"same_origin":false,
"asset_upload_url":"/upload/assets/244842724","upload_authenticity_token":"Z05Z4HzeknoO3knhz2U3m9qKr3Q9QbmXgVHanOdBBn8DWAlFQOYx4p_Vxt6MOrwA7aUq8VHfjVcE9aQ6l1OKcQ","asset_upload_authenticity_token":"VJXANkKnZ63cMo7sWZPTGOixSMcqbaagcWXqcOWLJIWpbZF5EpFO1kmID7rICkP7LgOl8pVeidk-u2DIup_T3w"
}