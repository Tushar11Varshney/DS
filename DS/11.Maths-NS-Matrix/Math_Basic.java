import java.util.*;

public class Math_Basic { 

    int fib(int n)
    {
        if (n <= 1)
            return n;
        int a = 0, b = 1, c = 0;
        cout << a << endl;
        cout << b << endl;
        for (int i = 0; i < n - 2; i++) //= lgana h vaise but in pep portal w/o
        {
            c = a + b;
            a = b;
            b = c;
            cout << c << endl;
        }
        return c;
        // for (int i = 0; i < n; i++)     //a travel the whole fibonacci series in loop.
        //     cout<<a<<endl;
    }

    public static int countDigit(int n) {
        int count = 0;
        while (n > 0) {
            n = n / 10;
            count++;
        }
        return count;
    }

    public static int getDigitFrequency(int n, int d) {
        int count = 0;
        while (n > 0) {
            int rem = n % 10;
            n = n / 10;
            if (rem == d)
                count++;
        }
        return count;
    }

    public static void pythagoreanTriplet() {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int max = 0;
        if (a > b && a > c) max = a;
        else if (b > c) max = b;
        else max = c;

        if (max == b) {
            b = a;
            a = max;
        } else if (max == c) {
            c = a;
            a = max;
        }

        // boolean flag=((b*b + c*c) == a*a);print flag
        if ((b * b + c * c) == a * a) 
            System.out.println("true");
        else System.out.println("false");
    }
    
    public static void rotateNumber() {
        int n = 27356, numcopy = n;
        int k = 2, count = 0, sum = 0;
        count = countDigit(n);
        k = (k % count + count) % count; // ya k=k%count and if k<0 k=k+count;
        int rem = numcopy % ((int) Math.pow(10, k));
        int quo = numcopy / ((int) Math.pow(10, k));
        sum = rem * ((int) Math.pow(10, count - k)) + quo;
        System.out.println(sum);
    }

    public static void inverseNumber() {
        int num = 21453;
        int sum = 0, originalPosition = 1;
        while (num > 0) {
            int originalDigit = num % 10;
            sum += originalPosition * Math.pow(10, originalDigit - 1);
            num = num / 10;
            originalPosition++;
        }
        System.out.println(sum);
    }

    public static void digitofNumber() {
        Scanner sc = new Scanner(System.in);
        Integer n = sc.nextInt(); // ya int n
        // String str=Integer.toString(n);
        // for(int i=0;i<str.length();i++)
        // System.out.println(str.charAt(i));

        int count = countDigit(n), num = n;
        int divisor = (int) (Math.pow(10, count - 1));
        // can we do?int quotient=num/(Math.pow(10, count-1)); //cant do because num int
        // and divisor is double first convert divisor to int.

        while (divisor != 0) {         //cant do num!=0.eg:100
            System.out.println(num / divisor);
            num = num % divisor;
            divisor = divisor / 10;
        }

        while (count > 0) {
            int digit = num / (int) (Math.pow(10, count - 1));
            num = num % (int) (Math.pow(10, count - 1));
            count--;
            System.out.println(digit);
        }
    }

    vector<int> primesum(int n)
    {
        for (int i = 2; i < n; i++)
        {
            if (isPrime(i) && isPrime(n - i))
            {
                return {i, n - i};
            }
        }
        return {-1, -1};
    }

    public static void PrimeFactoriztion() {
        Scanner sc = new Scanner(System.in);
        int num1 = sc.nextInt();
        //dont start loop from 2 and do till num!=1 for 46 2 will divide then it will run till 23

        for (int div = 2; div * div <= num1; div++) {
            while (num1 % div == 0) {
                num1 = num1 / div;
                System.out.printf("%d ", div);
            }
        }
        if (num1 != 1)
            System.out.printf("%d ", num1);
    }
    
    int powerof2Integer(int A) //Q.if number can be expressed in A^P
    {
        if (A == 1)
            return 1;

        for (int i = 2; i * i <= A; i++)
        {
            int flag = 0, num = A; //har baar num=A;
            while (num != 1)
            {
                if (num % i != 0)
                {
                    flag = 1;
                    break;
                }
                num = num / i;
            }

            if (flag == 0)
                return 1;
        }

        return 0;
    }

    //Maths
    // https://practice.geeksforgeeks.org/problems/max-sum-in-sub-arrays0824/1# read the soln 
    // 2 ≤ N ≤ 10^5 1 ≤ A[i] ≤ 10^18
    long pairWithMaxSum(long long arr[], long long N)
    {
        long maximum=arr[0]+arr[1];
        for(long i=1;i<N-1;i++)
        {
            long j=i+1;
            long newSum=arr[i]+arr[j];
            maximum=max(maximum,newSum);
        }
        return maximum;
    }

    //==================================
    //https:practice.geeksforgeeks.org/problems/find-the-position-of-m-th-item1723/1#
    int findPosition(int tpeople, int candies, int sposn)
    {

        // int ans = (candies % tpeople + (sposn - 1)) % tpeople;  //my
        // if (ans == 0)
        //     ans = tpeople;
        // return ans;

        if (candies <= tpeople - (sposn - 1))
            return sposn - 1 + candies;

        candies -= tpeople - (sposn - 1);

        return candies % tpeople == 0 ? tpeople : candies % tpeople;
    }

    // 1201
    // 1 <= a * b * c <= 10^18
    public long gcd(long n1, long n2) {
        while (n1 != 0) {
            long r = n2 % n1;
            n2 = n1;
            n1 = r;
        }
        return n2;
    }

    public long lcm(long n1, long n2) {
        return (n1 * n2) / gcd(n1, n2);
    }

    public long count(int n, int n1, int n2, int n3) {
        return n / n1 + n / n2 + n / n3 - (n / lcm(n1, n2)) - (n / lcm(n2, n3)) - (n / lcm(n1, n3))
                + (n / lcm(lcm(n1, n2), n3));
    }

    //The key idea is that the range of searching is monotonic, i.e., If F(a) == true, then for every b > a, F(b) = true. So our goal is to find the leftmost point a that F(a) == true, which can be solved by binary search in order to find the leftmost point which satisfies the condition.
    public int nthUglyNumber(int n, int a, int b, int c) {
        //It is guaranteed that the result will be in range [1, 2 * 10^9]
        int low = 1, high = 2 * (int) 1e9, mid = 0, result = 0;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (count(mid, a, b, c) >= n) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }
    
