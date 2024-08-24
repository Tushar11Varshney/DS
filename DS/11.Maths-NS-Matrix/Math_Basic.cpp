#include <iostream>
#include <cmath>
#include <unordered_map>
#include <vector>
#include <string.h>
#include <algorithm>
using namespace std;

void gradingSystem(int m)
{
    // if(m>90)
    // cout<<"excellent"<<endl;
    // else if(m>80 && m<=90)
    // cout<<"good"<<endl;
    // else if(m>70 && m<=80)
    // cout<<"fair"<<endl;
    // else if(m>60 && m<=70)
    // cout<<"meets expectations"<<endl;
    // else
    // cout<<"below par";

    int i = 10;
    // if(i++ == i) //pehle i ki value use hogi mtlb 10 then increment mtlb 11 hui
    // then compare 10==11
    // cout<<to_string(i) + " is good"<<endl;
    // else
    // cout<<to_string(i) + " is bad"<<endl; //11 is bad

    // int j = 20;
    // if(++j == j) //pehle j ki value increment mtlb 21 fir compare to 21 mtlb true
    // cout<<to_string(j) + " is good"<<endl; //21 is good
    // else
    // cout<<to_string(j) + " is bad"<<endl;
}

int isRectangle(int A, int B, int C, int D)
{

    // if(A==B && B==C && C==D && D==A)  //for square
    // return 1; not reqd
    if ((A == B && C == D) || (A == C && B == D) || (A == D && C == B))
        return 1;

    return 0;
}

// https://practice.geeksforgeeks.org/problems/lcm-and-gcd4516/1
vector<long long> lcmAndGcd(long long num1, long long num2)
{
    long long i = 2, res = 1, num1Copy = num1, num2Copy = num2;
    //LCM
    // while (num1 != 1 || num2 != 1)
    // {
    //     if (num1 % i == 0 || num2 % i == 0)
    //     {
    //         if (num1 % i == 0)   //% and / ka khyal rkha kro
    //             num1 = num1 / i;
    //         if (num2 % i == 0)
    //             num2 = num2 / i;
    //         res = res * i;
    //     }
    //     else
    //         i++;
    // }

    //HCF
    // while (num1Copy != num2Copy)
    // {
    //     if (num1Copy > num2Copy)
    //         num1Copy = num1Copy - num2Copy;
    //     else
    //         num2Copy = num2Copy - num1Copy;
    // }
    // vector<long long>result(2,-1); //abe dynamic hoti h bhai cant do this then pushback

    long long rem = 0;
    long long onum1 = num1, onum2 = num2;
    while (num1 % num2 != 0) //num2!=0 pr num1 answer
    {
        rem = num1 % num2;
        num1 = num2;
        num2 = rem;
    }
    long long lcm = (onum1 * onum2) / num2;
    return {lcm,num2};
}

int totalMovesForBishop(int A, int B) //The bishop chess piece moves in any direction diagonally
{
    A--;
    B--;
    int i = A, j = B, moves = 0;
    while (i - 1 >= 0 && j - 1 >= 0)
    {
        moves++;
        i--;
        j--;
    }

    i = A;
    j = B;
    while (i - 1 >= 0 && j + 1 <= 7)
    {
        moves++;
        i--;
        j++;
    }

    i = A;
    j = B;
    while (i + 1 <= 7 && j + 1 <= 7)
    {
        moves++;
        i++;
        j++;
    }

    i = A;
    j = B;
    while (i + 1 <= 7 && j - 1 >= 0)
    {
        moves++;
        i++;
        j--;
    }

    return moves;

    // return min(A - 1, B - 1) +  min(A - 1, 8 - B) + min(8 - A, B - 1) + min(8 - A, 8 - B) 
}



//231
bool isPowerOfTwo(int n)
{
    if (n <= 0)
        return false;
    n = (n & (n - 1));
    return n == 0;
}

bool isPowerOfTwo(int n) {
    if(n<=0)return false;
    while(n!=1)
    {
        if(n%2!=0)
            return false;
        n=n/2;
    }
    return true;
}

public boolean isPowerOfThree(int n) {
    int maxValue=(int)(Math.pow(3,(int)(Math.log(Integer.MAX_VALUE)/Math.log(3))));
    return (n>0 && maxValue%n==0); 
}

public boolean isPowerOfThree2(int n) {
    if(n==0)return false;
    StringBuilder sb=new StringBuilder();
    while(n!=0)
    {
        int rem=n%3;
        sb.append(rem);
        n/=3;
    }
    
    System.out.println(sb.toString());  //19684 1000000001 cant do by checking only last char
    for(int i=0;i<sb.length()-1;i++)
    {
        if(sb.charAt(i)!='0')return false;
    }
    if(sb.charAt(sb.length()-1)=='1')return true;
    return false;
}

//gcd using euclid
//https://www.interviewbit.com/problems/largest-coprime-divisor/
int gcd(int a, int b)
{
    return b == 0 ? a : gcd(b, a % b);
}

int LargestCoPrimeDivisor(int A, int B)  // a number is coprime if both number have common divisor only 1.
{ //ib-->we will remove the common factors of x and y from x by finding the greatest common divisor (gcd) of x and y and dividing x with that gcd.
    while (gcd(A, B) != 1)
    {
        A = A / gcd(A, B);
    }

    return A;
}

// 263
public boolean isUgly(int n) {
    while (n != 1) {
        int oldNum = n;
        if (n % 2 == 0)
            n = n / 2;
        if (n % 3 == 0)
            n = n / 3;
        if (n % 5 == 0)
            n = n / 5;
        if (oldNum == n)
            return false;
    }
    return true;
}

// 264
public int nthUglyNumber(int n) {
    int[] dp = new int[n];
    dp[0] = 1;

    int p2 = 0, p3 = 0, p5 = 0;
    for (int i = 1; i < n; i++) {
        int p2Val = dp[p2] * 2;
        int p3Val = dp[p3] * 3;
        int p5Val = dp[p5] * 5;

        int minVal = Math.min(p2Val, Math.min(p3Val, p5Val));
        if (minVal == p2Val)  //if lgana pdega ek value ke pointer do baar bad skte hain
            p2++;
        if (minVal == p3Val)
            p3++;
        if (minVal == p5Val)
            p5++;

        dp[i] = minVal;
    }

    return dp[n - 1];
}

int nthUglyNumber_M2(int n) {
    set<long>s,possibleSet;
    possibleSet.insert(1);
    while(s.size()!=n)
    {
        long val=*possibleSet.begin();
        s.insert(val);
        possibleSet.erase(val);
        possibleSet.insert(2*val);
        possibleSet.insert(3*val);
        possibleSet.insert(5*val);
    }
    return *s.rbegin();
}

// 313
public int nthSuperUglyNumber(int n, int[] primes) {

    int m = primes.length;
    int[] dp = new int[n];
    int[] pointers = new int[m];
    dp[0] = 1;

    for (int i = 1; i < n; i++) {
        dp[i] = Integer.MAX_VALUE;
        for (int j = 0; j < m; j++)
            dp[i] = Math.min(dp[i], primes[j] * dp[pointers[j]]);
        for (int j = 0; j < m; j++) {
            if (dp[i] == primes[j] * dp[pointers[j]])
                pointers[j]++;
        }
    }
    return dp[n - 1];
}

//172
int trailingZeroes(int n)
{
    int p = 5, count = 0;
    while (n / p > 0)
    {
        count += n / p;
        p = p * 5;
    }
    return count;
}
