package FoundationModule1;

import java.util.*;

public class pattern {
    // pattern1-abstraction ka concept(pehle socho 2 loop lgake hr jgah star lgane
    // ki andar waala loop baad mein socho..trust kro ki wo chl jaayga...pehle
    // bahaar wala socho then andar waala...)i>=j ya andar waala looputne star jitna
    // row number condn j<=i..pattern-diagonal ka left prtion
    public static void pattern1() {
        int n = 5;
        for (int i = 0; i < n; i++) // pattern1
        {
            for (int j = 0; j < n; j++) // important concept-abstraction(hiding the internal
            // detail) hume input(andar waala loop jitna row number utne star print
            // krega)
            // dekr output pr vishwas rkhna h aur jaane ki koshish nhi krni ki andar kya
            // chlrha hai.tou aese question mein upar loop pr socho aur niche pr vishwas
            // rkho .help in clear thinking.
            {
                if (i >= j) //// j<=i in loop cond
                    System.out.print("*\t");
            }
            System.out.println("");
        }
    }

    // pattern2-i+j<=n-1 antidiagonal ka left portion ..ya loop mein andar wala loop
    // utne star print krega jitna row number so here loop start kro outer i=n se..
    public static void pattern2() {
        int n = 5;
        for (int i = n - 1; i >= 0; i--) // pattern2 //i=n;i>=1
        {
            for (int j = i; j >= 0; j--) // j=1;j<=i(just to make it like upar wala)
            {
                System.out.print("*\t");
            }
            System.out.println("");
        }
    }

    // antidiagonal ka right wala i+j>=n-1 else mein space..ya space star se sochlo
    // usme pattern bnakr..outer loop row ka inner check kro kya reqmnt hai..
    public static void pattern3() {
        int n = 5;
        for (int i = 0; i < n; i++) // pattern3
        {
            for (int j = 0; j < n; j++) {
                if (i + j >= n - 1)
                    System.out.print("*\t");
                else
                    System.out.print("\t");
            }
            System.out.println("");
        }

        // int sp=n-1,st=1; //pattern3
        // for(int i=0;i<n;i++)
        // {
        // for(int j=0;j<sp;j++)
        // System.out.print("\t");

        // for(int j=0;j<st;j++)
        // System.out.print("*\t");
        // System.out.print("\n");

        // sp--;st++;
        // }
    }

    // diagonal ka right waala i<=j else mein space...ya space star se sochlo usme
    // pattern bnakr..outer loop row ka inner check kro kya reqmnt hai..
    public static void pattern4() {
        int n = 5;
        for (int i = 0; i < n; i++) // pattern4
        {
            for (int j = 0; j < n; j++) {
                if (i <= j)
                    System.out.print("*\t");
                else
                    System.out.print("\t");
            }
            System.out.println("");
        }

        // int sp=0,st=n; //pattern4
        // for(int i=0;i<n;i++)
        // {
        // for(int j=0;j<sp;j++)
        // System.out.print("\t");

        // for(int j=0;j<st;j++)
        // System.out.print("*\t");
        // System.out.print("\n");

        // sp++;st--;
        // }
    }

    // outer loop row space star ka pattern bnakr socho andar waala
    public static void pattern5() {
        int n = 5;
        int sp = n / 2, st = 1; // pattern5(number always odd) //n-3 vo sirf 5 kelie
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < sp; j++)
                System.out.print("\t");

            for (int j = 0; j < st; j++)
                System.out.print("*\t");
            System.out.print("\n");
            if (i < n / 2) {
                sp = sp - 1;
                st = st + 2;
            } else {
                sp = sp + 1;
                st = st - 2;
            }
        }
    }

    // outer loop row space star ka pattern bnakr socho andar waala
    public static void pattern6() {
        int n = 5;
        int st = n / 2 + 1, sp = 1; // pattern 6 //n-2 for n=5 only
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < st; j++)
                System.out.print("*\t"); // "* " dont give like it pattern khrab(bcz space
            // galat bat ti hain 2nd row mein 3 deni thi milgyi 4) ya tou ye do "*\t"(pr
            // isse bhi accha pattern ni bnta)
            for (int j = 0; j < sp; j++)
                System.out.print("\t");
            for (int j = 0; j < st; j++)
                System.out.print("*\t");
            System.out.println("");
            if (i < n / 2) {
                sp = sp + 2;
                st = st - 1;
            } else {
                sp = sp - 2;
                st = st + 1;
            }
        }
    }

    // diagonal i==j else space
    public static void pattern7() {
        int n = 5;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    System.out.print("*\t");
                else
                    System.out.print("\t");
            }
            System.out.println("");
        }
    }

    // anti-diagonal i+j==n-1 else space
    public static void pattern8() {
        int n = 5;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i + j == n - 1)
                    System.out.print("*\t");
                else
                    System.out.print("\t");
            }
            System.out.println("");
        }
    }

    // both d and antidiagonal else space
    public static void pattern9() {
        int n = 5;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i + j == n - 1 || i == j)
                    System.out.print("*\t");
                else
                    System.out.print("\t");
            }
            System.out.println("");
        }
    }

    // outer loop row space star ka pattern bnakr socho andar waala...space
    // printstar innerspace and star in >0 && <n-1
    public static void pattern10() {
        int n = 5;
        int os = n / 2, is = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < os; j++)
                System.out.print("\t");
            System.out.print("*\t");
            for (int j = 0; j < is; j++)
                System.out.print("\t");
            if (i > 0 && i < n - 1)
                System.out.print("*\t");
            System.out.println("");
            if (i < n / 2) {
                os--;
                is = is + 2;
            } else {
                os++;
                is = is - 2;
            }
        }
    }

    // i>=j waala hi but counting print krni h
    public static void pattern11() {
        int n = 5;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                count++;
                System.out.print(count + "\t");
            }
            System.out.println("");
        }
    }

    // i>=j waala hi but fibonacci print krni h..so ya tou row 0 ,1 print kralo and then row 2 se loop ya..hume pta hai a puri series mein travel krta h usko print krdo.
    public static void pattern12() {
        int n = 5;
        // int a = 0, b = 1, c = 0; // my soln
        // System.out.println(a);
        // System.out.println(b + "\t" + b);

        // a = b;
        // for (int i = 2; i < n; i++) {
        // for (int j = 0; j <= i; j++) {
        // c = a + b;
        // System.out.print(c + "\t");
        // a = b;
        // b = c;
        // }
        // System.out.println("");
        // }

        int a = 0, b = 1, c = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(a + "\t"); // a sb pr travel krta jaarha h
                c = a + b;
                a = b;
                b = c;
            }
            System.out.println("");
        }
    }

    public static int fact(int n) {
        int fact = 1;
        if (n <= 1)
            return fact;
        else {
            for (int i = 2; i <= n; i++)
                fact = fact * i;
        }
        return fact;
    }

    //pascal triangle...jab col=0 ya col==i ke brabar then 1 else calculate icj
    //2nd approach 1c(j+1)=icj(i-j)/(j+1);..initialse icj=1 in each row.
    public static void pattern13() {
        // for (int i = 0; i < n; i++) // pattern 13
        // {
        // for (int j = 0; j <= i; j++) {
        // if (j == 0 && j == i)
        // System.out.print("1\t");
        // else {
        // int val = fact(i) / (fact(j) * fact(i - j));
        // System.out.print(val + "\t");
        // }
        // }
        // System.out.println("");
        // }

        int n = 5;
        int icj = 1;
        for (int i = 0; i < n; i++) {
            icj = 1;
            for (int j = 0; j <= i; j++) {
                System.out.print(icj + "\t");
                icj = (icj * (i - j)) / (j + 1);
            }
            System.out.println("");
        }

    }

    //print table 1 loop lgega
    public static void pattern14() {
        int n = 5;
        for (int i = 1; i <= 10; i++) // pattern 14
        {
            System.out.println(n + " * " + i + " = " + n * i);
        }
    }

    // pattern 5,then convert star to 1,then convert to row number+1,then while changing in space and star maintain count and print count in star loop,then take value of count in variable val and change count when st<star/2;
    public static void pattern15() {
        int n = 5;
        int sp = n / 2, st = 1, count = 1, k = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < sp; j++) {
                System.out.print("\t");
            }
            int val = count;
            for (int j = 0; j < st; j++) {
                // System.out.print(i+1+"\t"); //3rd stage
                System.out.print(val + "\t"); // 4th stage
                // val++; //5th stage
                if (j < st / 2) // 6th stage
                    val++;
                else
                    val--;
            }

            if (i < n / 2) {
                sp--;
                st = st + 2;
                count++;
            } else {
                sp++;
                st = st - 2;
                count--;
            }
            System.out.println("");
        }
    }

    //st sp wala hi concept ...pehle val lelo usse pehle star ke loop mein increment dusre star ke loop mein decrement..last row alg se handle.
    public static void pattern16() {
        int n = 7;
        int val, st = 2, sp = 2 * n - 1 - 2;
        for (int i = 1; i <= n; i++) {
            val = 1;
            for (int j = 0; j < st / 2; j++) // meine yahan condn mein i rakha tha
            {
                System.out.print(val + "\t");
                val++;
            }
            for (int j = 0; j < sp; j++) // meine yahan condn mein 2*n-1-2*i rakha tha
                System.out.print("\t");
            if (i == n) { // yahan pr ek stop variable bnaya tha
                val--;
                st--;
            }
            for (int j = 0; j < st / 2; j++) // meine yahan condn mein i rakha tha pr fir isme alg se printing krni pdegi
            {
                val -= 1;
                System.out.print(val + "\t"); // meine print ke badd val-- kiya tha tou phle bhi
                // val-1 krke likhna pdrha tha
            }
            st += 2;
            sp -= 2;
            System.out.println("");
        }
    }

    //arrow print krna hai...sp st walaa hi concept when i!=n/2 && i==n/2 pr loop se star
    public static void pattern17() {
        int n = 5;
        int sp = n / 2, st = 1;
        for (int i = 0; i < n; i++) {
            if (i != n / 2) {
                for (int j = 0; j < sp; j++)
                    System.out.print("\t");
                for (int j = 0; j < st; j++)
                    System.out.print("*\t");
                if (i < n / 2)
                    st++;
                else
                    st--;
            } else {
                for (int j = 0; j < n; j++)
                    System.out.print("*\t");
                st--;
            }
            System.out.println("");
        }
    }

    // sp st wala hi concept pr (i > 0 && i < n / 2 && j > 0 && j < st - 1)
    public static void pattern18() {
        int n = 7;
        int sp = 0, st = n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < sp; j++)
                System.out.print(" ");

            for (int j = 0; j < st; j++) {
                if (i > 0 && i < n / 2 && j > 0 && j < st - 1)
                    System.out.print(" ");
                else
                    System.out.print("*");
            }

            if (i < n / 2) {
                sp++;
                st -= 2;
            } else {
                sp--;
                st += 2;
            }
            System.out.println("");
        }
    }

    // break the problem i==0 pr j=n-1 || j<=n/2,i<n/2 pr j==n/2 aur j==n-1,i==n/2 pr hr col mein,(i > n / 2 && (j == n / 2 || j == 0)),(i == n - 1 && (j == 0 || j >= n / 2))
    public static void pattern19() {
        int n = 7;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    if (j == n - 1 || j <= n / 2)
                        System.out.print("*\t");
                    else
                        System.out.print("\t");
                } else if (i < n / 2) {
                    if (j == n / 2 || j == n - 1)
                        System.out.print("*\t");
                    else
                        System.out.print("\t");
                } else if (i == n / 2)
                    System.out.print("*\t");
                else if (i > n / 2 && (j == n / 2 || j == 0))
                    System.out.print("*\t");
                else if (i == n - 1 && (j == 0 || j >= n / 2))
                    System.out.print("*\t");
                else
                    System.out.print("\t");
            }
            System.out.println("");
        }
    }

    // w bnana hai..so jab col==0 ya col==n-1 tou star and (i>=n/2 && d | ad) 
    public static void pattern20() {
        int n = 5;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0 || j == n - 1 || (i >= n / 2 && (i == j || i + j == n - 1)))
                    System.out.print("*\t");
                else
                    System.out.print("\t");
            }
            System.out.println("");
        }
    }

    //printz(direct print ya 2 loop ka logic ki 1 row aur last row mein n star aur beech ki row mein n-1-i pr star else space)
    void printZ() //soln in video was using exact print statement as pattern.
    {
        int n = 5;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (i == 0 || i == n - 1)
                    cout << "*";
                else if (i > 0 && i < n - 1 && j == n - i - 1)
                    cout << "*";
                else
                    cout << " ";
            }
            cout << endl;
        }
    }
}

//164
int maximumGap(vector<int> &nums)
{ //[2,99999999] memory limit exceeded
    //   10 ki power 7 tak chlrha hai

    if (nums.size() < 2)
        return 0;

    int minimum = 1e9;
    int maximum = -1e9;

    for (int ele : nums)
    {
        minimum = min(ele, minimum);
        maximum = max(ele, maximum);
    }

    int range = maximum - minimum + 1;

    vector<int> bucketArray(range);
    fill(bucketArray.begin(), bucketArray.end(), -1);

    for (int i = 0; i < nums.size(); i++)
    {
        int idx = nums[i] - minimum;
        bucketArray[idx] = nums[i];
    }

    int idx = 0;
    for (int i = 0; i < range; i++)
    {
        if (bucketArray[i] != -1)
            nums[idx++] = bucketArray[i];
    }

    int maxGap = 0;
    for (int i = 0; i < nums.size() - 1; i++)
        maxGap = max(maxGap, nums[i + 1] - nums[i]);

    return maxGap;
}

//explaination of
// the min value is min and the max value is max. Then the maximum gap will be no smaller than ceiling[(max - min ) / (N - 1)].
// Suppose we have N elements from min to max, since the gap between each elements are different, there must be an average of gap in array, assume it is x.

// For example [1,2,4,5] we have gap array [1,2,1], the average gap x = (1+2+1)/3.
// now the average gap is x, and we have n-1 gaps for a n-length array, which makes total gap be (n-1) * x, we also know that total gap = max - min
// So we know that x*(n-1) = max - min => x = (max-min)/(n-1).
// now since the average gap is x, we know there must be gap smaller than it and larger than it.
// If every gap is smaller or equal to Floor of (max-min)/(n-1), then average number won't be (max-min)/(n-1), so there must be a gap larger than Ceil of (max-min)/(n-1)
// We do not need to consider the numbers in the same bucket, because the distance in the same bucket will not be larger than gap.
// Then previous bucket's maximum should be continuous with current bucket's minimum if they are in the sorted array.
// That's why the algorithm works.

int maximumGap(vector<int> &nums)
{
    int n = nums.size();
    if (n < 2)
        return 0;

    int minimum = 1e9;
    int maximum = -1e9;

    for (int ele : nums)
    {
        minimum = min(ele, minimum);
        maximum = max(ele, maximum);
    }

    if (maximum == minimum)
        return 0; //[1,1,1]
    int bucketSize = (int)ceil((double)(maximum - minimum) / (n - 1));
    vector<int> minArray(n, INT_MAX);
    vector<int> maxArray(n, INT_MIN);

    for (int i = 0; i < n; i++)
    {
        int idx = (nums[i] - minimum) / bucketSize;
        minArray[idx] = min(minArray[idx], nums[i]);
        maxArray[idx] = max(maxArray[idx], nums[i]);
    }

    int prev = maxArray[0], maxGap = bucketSize;
    for (int i = 1; i < n; i++)
    {
        if (minArray[i] != INT_MAX)
        {
            maxGap = max(maxGap, minArray[i] - prev);
            prev = maxArray[i];
        }
    }
    return maxGap;
}

// find max then ya tou loop max se lekr 1 tak ya 0 se lekr max tak...agr max->1
// then inner loop jo arr ki length ka hoga then usme condn socho.
public static void barChart() {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt(), max = 0;
    int arr[] = new int[n];
    for (int i = 0; i < n; i++)
        arr[i] = sc.nextInt();
    for (int i = 0; i < n; i++) {
        if (arr[i] > max)
            max = arr[i];
    }

    // for (int i = max; i >= 1; i--) {
    // for (int j = 0; j < arr.length; j++) {
    // if (i<=arr[j])
    // System.out.print("*\t");
    // else
    // System.out.print("\t");
    // }
    // System.out.println("");
    // }

    for (int i = 0; i < max; i++) {
        for (int j = 0; j < arr.length; j++) {
            if (i >= max - arr[j])
                System.out.print("*\t");
            else
                System.out.print("\t");
        }
        System.out.println("");
    }
}
