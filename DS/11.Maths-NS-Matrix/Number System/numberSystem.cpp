#include <iostream>
#include <cmath>
#include <vector>
#include <string.h>
#include <algorithm>
using namespace std;

string addBinary(string a, string b)
{ //67  //1 <= a.length, b.length <= 10^4 string size very big so number bnake normal addition ni chlega with base 2 
    string res = "";
    int carry = 0, i = a.length() - 1, j = b.length() - 1;
    while (i >= 0 || j >= 0 || carry > 0)
    {
        int sum = carry;
        sum += (i >= 0 ? (a[i--] - '0') : 0);
        sum += (j >= 0 ? (b[j--] - '0') : 0);
        res = res + ((char)(sum % 2 + '0'));
        carry = sum / 2;
    }

    reverse(res.begin(), res.end());
    return res;
}

//43
string addTwoNum(string num1, string num2)
{
    int i = num1.length() - 1, j = num2.length() - 1;
    int carry = 0;
    string ans = "";
    while (i >= 0 || j >= 0 || carry > 0)
    {
        int sum = carry;
        sum += (i >= 0 ? (num1[i--] - '0') : 0);
        sum += (j >= 0 ? (num2[j--] - '0') : 0);
        int digit = sum % 10;
        ans = ans + ((char)(digit + '0'));
        carry = sum / 10;
    }

    reverse(ans.begin(), ans.end());
    return ans;
}

string multiplyByoneNumber(string num1, char ch)
{
    int carry = 0, i = num1.length() - 1;
    string ans = "";
    while (i >= 0 || carry > 0)
    {
        int digit = (i >= 0 ? num1[i--] - '0' : 0);
        int sum=(digit * (ch - '0') + carry);
        int rem = sum % 10;
        ans = ans + ((char)(rem + '0'));
        carry = sum / 10;
    }

    reverse(ans.begin(), ans.end());
    return ans;
}

string multiply(string num1, string num2)
{
    int j = num2.length() - 1;
    int p = 0;
    string ans = "";
    while (j >= 0)
    {
        string sum = multiplyByoneNumber(num1, num2[j--]);
        int zero = p;
        while (zero-- > 0)
            sum = sum + '0';
        p++;
        ans = addTwoNum(ans, sum);
    }

    return ans[0] == '0' ? "0" : ans;  // "9133" * "0"
}

string multiply(string num1, string num2)
{
    vector<int> product(num1.length() + num2.length());
    for (int i = num1.length() - 1; i >= 0; i--)
    {
        for (int j = num2.length() - 1; j >= 0; j--)
        {
            int d1 = num1[i] - '0';
            int d2 = num2[j] - '0';
            product[i + j + 1] += d1 * d2;
        }
    }

    int carry = 0;
    for (int i = product.size() - 1; i >= 0; i--)
    {
        int digit = (carry + product[i]) % 10;
        carry = (carry + product[i]) / 10;
        product[i] = digit;
    }

    string ans = "";
    int i = 0;
    while (i < product.size() && product[i] == 0)
        i++;

    while (i < product.size())
        ans = ans + ((char)(product[i++] + '0'));
    return ans == "" ? "0" : ans;
}

public String nextSmallestPalindrome(String A) {
        int len = A.length();
        String center = "";
        if (len % 2 != 0)
            center = A.charAt(len / 2) + "";

        StringBuilder nsp = new StringBuilder(); // next smallest palindrome
        StringBuilder ri = new StringBuilder(); // right
        for (int i = 0; i < len / 2; i++)
            ri.insert(0, A.charAt(i));

        nsp.append(A.substring(0, len / 2) + center + ri.toString());

        int i = 0;
        long nspNum = 0, Anum = 0;

        while (i < len) {     //1st case
            nspNum = nspNum * 10 + (nsp.charAt(i) - '0');
            Anum = Anum * 10 + (A.charAt(i) - '0');

            if (nspNum > Anum)
                return nsp.toString();
            else if (nspNum < Anum)
                break;
            i++;
        }

        StringBuilder sp = new StringBuilder();
        if (!center.equals("")) { // odd waale
            if (center.charAt(0) < '9') {     
                long num = Long.parseLong(nsp.substring(0, len / 2 + 1)) + 1;
                sp.append(num + nsp.substring(len / 2 + 1));
                return sp.toString();
            } else {
                // yahan pr agr 999 ya 9999 ko theek krenge tou 999 tou hojayga but 9999 nhi hoga
                if (len == 1 && A.charAt(0) == '9')
                    return "11";
                center = "0";
            }
        }

        // all 9
        String left = A.substring(0, len / 2);  //3rd
        StringBuilder number = new StringBuilder();
        int nt = len / 2;
        while (nt-- > 0)
            number.append("9");

        if (left.equals(number.toString())) {
            sp.append(1);
            nt = len - 1;
            while (nt-- > 0)
                sp.append(0);
            sp.append(1);
            return sp.toString();
        }

        else {   //4th
            int lpt = len / 2 - 1;
            while (nsp.charAt(lpt) == '9') {
                nsp.replace(lpt, lpt + 1, "0");
                lpt--;
            }

            nsp.replace(lpt, lpt + 1, // left pointer
                    Integer.toString(nsp.charAt(lpt) - '0' + 1));
            sp.append(nsp.substring(0, len / 2));
            StringBuilder rev = new StringBuilder(sp);
            rev = rev.reverse();

            sp.append(center + rev.toString());

            return sp.toString();
        }
}

int NumberOfLengthN_ValueLessThanK (vector<int> &A, int length, int number)
{
        if (A.size() == 0)
            return 0;
        int numLen = countDigit(number), ans = 0;
        if (numLen < length)return ans;
        else if (numLen > length)
        {
            if (A[0] == 0)
                ans = (A.size() - 1) * pow(A.size(), length - 1);
            else
                ans = pow(A.size(), length);

            if (length == 1 && A[0] == 0)
                ans++; //numLen=2 length=1 number=20
            return ans;
        }
        else
        {
            if (length == 1) //bina iske bhi length 1 waale smbhal jaaynge but [0] 1 3 kelie fir answer 0 aayga hona chhaiye 1
            {
                for (int i = 0; i < A.size(); i++)
                {
                    if (A[i] < number)
                        ans++;
                }
            }
            else
            {
                vector<int> temp(length);
                int num = number;
                for (int i = length - 1; i >= 0; i--)
                {
                    int rem = num % 10;
                    temp[i] = rem;
                    num /= 10;
                }

                int count=0,flag=0,j=1;
                for(int i=0;i<A.size();i++)
                {
                    if(A[i]==0)continue;
                    else if(A[i]==temp[0])flag=1;
                    else if(A[i]>temp[0])break;
                    count++;
                }

                ans = count * (pow(A.size(), length - 1));
                //remove extras

                while (flag == 1 && j < length)
                {
                    count = 0;flag = 0;
                    for (int i = 0; i < A.size(); i++)
                    {
                        if (A[i] > temp[j])
                        {
                            count++;
                        }
                        if (A[i] == temp[j])
                            flag = 1;
                    }

                    ans -= (count) * (pow(A.size(), length - j - 1));
                    j++;
                }

                if (flag == 1 && j == length)
                    ans--;
            }
        }    
    return ans;
}

int fact(int f)
{
    if (f == 0)
        return 1;

    return f * fact(f - 1) % 1000003;
}

int sortedPerm_WithRank(string A)
{
    int n = A.length(), mod = 1000003, size = 'z' - 'A' + 1;
    vector<int> charArr(size);

    for (int i = 0; i < n; i++)
        charArr[A[i] - 65]++;

    long ans = 0;
    for (int i = 0; i < n; i++)
    {
        int smallerAlphabet = 0;
        char ch = A[i];
        for (int j = 0; j < size; j++)
        {
            if (j == (ch - 65))
                break; //a ki ascii value 32 se compare break;

            if (charArr[j] == 1)
                smallerAlphabet++;
        }

        if (smallerAlphabet > 0)
            ans = ans + (smallerAlphabet * fact(n - (i + 1))) % mod;
        charArr[ch - 65] = 0;
    }

    return (ans + 1) % mod;
}

int pwr_exp(long a, int exp)
{
    int ans = 1;
    while (exp)
    {
        if (exp & 1)
            ans = (ans * a) % 1000003;
        a = (a * a) % 1000003;
        exp >>= 1;
    }
    return ans;
}

int sortedPerm_RankwithRepeat(string A)
{
    int n = A.length();

    unordered_map<char, int> map;
    for (int i = 0; i < n; i++)
        map[A[i]]++;

    long ans = 0;
    int mod = 1000003;
    for (int i = 0; i < n; i++)
    {
        vector<char> smallerAlphabet;
        unordered_map<char, int>::iterator itr;
        for (itr = map.begin(); itr != map.end(); itr++)
        {
            if (itr->first < A[i])
                smallerAlphabet.push_back(itr->first);
        }

        for (int j = 0; j < smallerAlphabet.size(); j++)
        {
            long divMul = 1L; //Multiply all divisor
            for (itr = map.begin(); itr != map.end(); itr++)
            {
                if (map[itr->first] > 1)
                {
                    if (itr->first == smallerAlphabet[j])
                        divMul = (divMul * fact(map[itr->first] - 1)) % mod;
                    else
                        divMul = (divMul * fact(map[itr->first])) % mod;
                }
            }
            //(1/A) % MOD = A ^ (MOD - 2) % MOD        //divisor inverse
            long divInv = pwr_exp(divMul, mod - 2) % mod;
            ans = (ans + (fact(n - 1 - i) * divInv) % mod);
        }
        map[A[i]]--;
        if (map[A[i]] == 0)
            map.erase(A[i]);
    }

    return (ans + 1) % mod;
}

int givenNum = 0;
class fibonacciFinder {
    long[][][] exponents; // tle ya mle for 10^6
    int mod = 0;

    fibonacciFinder() {
        exponents = new long[givenNum][2][2];
        mod = (int) 1e9 + 7;
        exponents[0] = new long[][] { { 1, 1 }, { 1, 0 } }; // 1 kelie yahan array out of bound dega but ib pr test case 1 ni hai
        for (int i = 1; i < exponents.length; i++) // precalculation of square
        {
            exponents[i] = square(exponents[i - 1]);
        }
    }

    private long[][] square(long[][] m) {
        return multiply(m, m);
    }

    private long[][] multiply(long[][] m1, long[][] m2) {
        long[][] ans = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    long pro=(m1[i][k]*m2[k][j])%mod;
                    ans[i][j]=(ans[i][j]+pro)%mod; 
                }
            }
        }
        return ans;
    }

        public long[][] binaryExponentiation(int n) {
        long[][] result = new long[][] { { 1, 0 }, { 0, 1 } };
        for (int i = 31; i >= 0; i--) {
            if ((n & (1 << i)) != 0)
                result = multiply(result, exponents[i]);
        }
        return result;
    }

    public int fib(int n) {
        if (n<=1)
            return n; 
        else {
            long[][] m = binaryExponentiation(n - 1);
            return (int) (m[0][0]+ m[0][1]) % mod;
        }
    }
}

public int nthFibonaci(int A) {
    //1 <= A <= 10^9.
    givenNum = A - 1; 
    fibonacciFinder ff = new fibonacciFinder();  //14 kelie answer 377 hai program ke hisaab se 14 kelie 610 so a-1
    return ff.fib(givenNum);
}

int mod = (int) 1e9 + 7;
private long[][] multiply(long[][] m1, long[][] m2) {
    long[][] ans = new long[2][2];
    for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 2; k++) {
                long pro=(m1[i][k]*m2[k][j])%mod;
                ans[i][j]=(ans[i][j]+pro)%mod; 
            }
        }
    }

    return ans;
}

private long[][] pow(long[][]a, int p){
    if(p<=1) 
        return a;
    long[][] r=pow(a, p/2);
    long[][] x=multiply(r,r);
    if(p%2==0)
        return x;
    return multiply(x,a);
}

public int nthFibonaci_(int a) {
        if (a == 1)
            return 1;
        long[][] arr = { { 1, 1 }, { 1, 0 } };
        arr=pow(arr, a-2); //a-1(kyunki first term 1 rkhi hai aur power kelie dusra -1)
        return (int) (arr[0][0]+ arr[0][1]) % mod;
}