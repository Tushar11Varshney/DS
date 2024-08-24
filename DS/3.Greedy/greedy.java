import java.util.*;

public class greedy {

    // 14
    // https://practice.geeksforgeeks.org/problems/choose-and-swap5932/1
    string chooseandswap(string a){    //at most once
        set<char>s;
        for(int i=0;i<a.length();i++)
        s.insert(a[i]);
        
        for(int i=0;i<a.length();i++)
        {
            char ch1=a[i];
            s.erase(a[i]); //remove so that we get smallest 
            if(s.empty())break;
            char ch2=*s.begin();
            if(ch2<ch1)        //string "abac"
            {
                for(int i=0;i<a.length();i++)
                {
                    if(a[i]==ch1)
                    a[i]=ch2;
                    else if(a[i]==ch2)
                    a[i]=ch1;
                }
                break;
            }            
        }
        return a;
    }

    // https://www.spoj.com/submit/CHOCOLA/id=28248929
    public static int mincost(Integer[] hor, Integer[] ver) {
        int minCost = 0;
        Arrays.sort(hor, (a, b) -> {
            return b - a;
        });
        Arrays.sort(ver, (a, b) -> {
            return b - a;
        });

        int vpiece = 1, hpiece = 1, i = 0, j = 0;
        while (i < hor.length || j < ver.length) {
            int v1 = i < hor.length ? hor[i] : -(int) 1e9;
            int v2 = j < ver.length ? ver[j] : -(int) 1e9;

            if (v2 > v1) {
                minCost += v2 * vpiece;
                hpiece++;
                j++;
            } else {
                minCost += v1 * hpiece;
                vpiece++;
                i++;
            }
        }
        return minCost;
    }

    // https://practice.geeksforgeeks.org/problems/maximize-sum-after-k-negations1149/1#
    public static long maximizeSum(long arr[], int n, int k) {
        Arrays.sort(arr);

        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0 && k > 0) {
                arr[i] = -arr[i];
                k--;
            }
            sum += arr[i];
        }

        if (k > 0) {
            Arrays.sort(arr);
            k = k % 2;
            if (k == 1)
                sum -= 2 * arr[0];
        }
        return sum;
    }

    // https://www.geeksforgeeks.org/smallest-subset-sum-greater-elements/
    public int smallest_SubsetWithSumGreater_ThanAllOtherElement(int[] nums) {
        Arrays.sort(nums);
        int sum = 0, n = nums.length;
        for (int ele : nums)
            sum += ele;

        int currentSum = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            currentSum += nums[i];
            sum -= nums[i];
            if (currentSum > sum)
                return n - i;
        }

        return 0;
    }

    // https://www.spoj.com/problems/DEFKIN/
    public static int maxArea(int[] xcoordinates, int[] ycoordinates, int houses, int width, int height) {
        xcoordinates[houses] = 0;
        xcoordinates[houses + 1] = width + 1;
        ycoordinates[houses] = 0;
        ycoordinates[houses + 1] = height + 1;
        Arrays.sort(xcoordinates);
        Arrays.sort(ycoordinates);

        int w = -(int) 1e9;
        for (int i = 1; i < xcoordinates.length; i++) {
            w = Math.max(w, xcoordinates[i] - xcoordinates[i - 1] - 1);
        }

        int h = -(int) 1e9;
        for (int i = 1; i < ycoordinates.length; i++) {
            h = Math.max(h, ycoordinates[i] - ycoordinates[i - 1] - 1);
        }

        return w * h;
    }

    // https://practice.geeksforgeeks.org/problems/smallest-number5829/1#
    static String smallestNumber(int S, int D) {
        if ((9 * D) < S)
            return "-1";
        char[] arr = new char[D];
        while (S > 9) {
            arr[D - 1] = '9';
            D--;
            S -= 9;
        }

        if (D > 1) {
            arr[0] = '1';
            S -= 1;
            for (int i = 1; i < D - 1; i++)
                arr[i] = '0';
            arr[D - 1] = (char) (S + '0');
        } else
            arr[0] = (char) (S + '0');

        return new String(arr);
    }

    // https://www.spoj.com/problems/DIEHARD/
    public static int maximumTimeSurvival(int health, int armor, int[][] dp, int state) {
        // dp.resize(health+4);
        // for (int i = 0; i < health+4; ++i)
        // dp[i].resize(armor+6);
        // dp.resize(health+4, vector<int>(armor+6));
        if (health <= 0 || armor <= 0)
            return 0;

        if (dp[health][armor] != 0)
            return dp[health][armor];

        if (state == 1) {
            dp[health][armor] = Math.max(maximumTimeSurvival(health - 5, armor - 10, dp, 2),
                    maximumTimeSurvival(health - 20, armor + 5, dp, 3)) + 1;
        } else if (state == 2) {
            dp[health][armor] = Math.max(maximumTimeSurvival(health + 3, armor + 2, dp, 1),
                    maximumTimeSurvival(health - 20, armor + 5, dp, 3)) + 1;
        } else {
            dp[health][armor] = Math.max(maximumTimeSurvival(health + 3, armor + 2, dp, 1),
                    maximumTimeSurvival(health - 5, armor - 10, dp, 2)) + 1;
        }

        return dp[health][armor];

        // while(t-->0)
        // {
        // int health=sc.nextInt();
        // int armor=sc.nextInt();
        // int dp[][]=new int[1500][1500];
        // // System.out.println(dp.length+" "+dp[0].length);
        // int ma=maximumTimeSurvival(health+3,armor+2,dp,1);
        // int mw=maximumTimeSurvival(health-5,armor-10,dp,2);
        // int mf=maximumTimeSurvival(health-20,armor+5,dp,3);
        // System.out.println(Math.max(Math.max(ma,mw),mf));
        // }
    }

    // my soln was ek list mein sp of x and number of x daale pair classbnakr.
    // fir hume chhaiye pq jo min bnde nikaalegi max distance pr aur bnde brabar 
    // then zyada dist pr pq mein daale sp,difference of distance,number of people and last waale kelie usse pichle waale ka diff liya fir pq se nikaala jab tak size 1 and then ek naya group banaya ..pr fir pq se jiske saath mila hai vo nikalemge kaise?? 5.calculate jump
    public int seats(String A) {
        int i = 0, j = A.length() - 1, mod = 10000003, l = 0, r = 0, ans = 0;
        while (i <= j) {
            while (i <= j && A.charAt(i) == 'x') {
                i++;
                l++; // l:no of people on left
            }

            while (i <= j && A.charAt(j) == 'x') {
                j--;
                r++; // r:no of people on right
            }
            if (i <= j) {
                if (l <= r) {
                    ans = (ans + l) % mod;
                    i++;
                } else {
                    ans = (ans + r) % mod;
                    j--;
                }
            }
        }
        return ans;
    }

//=======

//767
public String reorganizeString(String s) {
    HashMap<Character, Integer> count = new HashMap<>();
    for (int i = 0; i < s.length(); i++)
        count.put(s.charAt(i), count.getOrDefault(s.charAt(i), 0) + 1);

    PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> {
        return count.get(b) - count.get(a);
    });   //instead of using pair

    pq.addAll(count.keySet());
    StringBuilder sb = new StringBuilder();
    while (pq.size() > 1) {
        char ch1 = pq.remove();
        char ch2 = pq.remove();

        sb.append(ch1);
        sb.append(ch2);

        count.put(ch1, count.get(ch1) - 1);
        count.put(ch2, count.get(ch2) - 1);

        if (count.get(ch1) == 0)
            count.remove(ch1);
        else
            pq.add(ch1);

        if (count.get(ch2) == 0)
            count.remove(ch2);
        else
            pq.add(ch2);    
    }

    if (pq.size() == 1) {
        char ch = pq.remove();
        if (count.get(ch) > 1)
            return "";
        sb.append(ch);
    }

    return sb.toString();
}

// 135
public int candy(int[] rating) {
    int n = rating.length;
    int[] candyArr = new int[n];

    candyArr[0] = 1;
    for (int i = 1; i < n; i++) {
        if (rating[i] > rating[i - 1])
            candyArr[i] = candyArr[i - 1] + 1;
        else
            candyArr[i] = 1;
    }

    for (int i = n - 2; i >= 0; i--) {
        if (rating[i] > rating[i + 1])
            if (candyArr[i] <= candyArr[i + 1])
                candyArr[i] = candyArr[i + 1] + 1;
    }

    int reqd = 0;
    for (int i = 0; i < n; i++)
        reqd += candyArr[i];

    return reqd;
}

// 1383
public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
    int[][] engi = new int[n][2];
    for (int i = 0; i < n; i++) {
        engi[i][0] = speed[i];
        engi[i][1] = efficiency[i];
    }

    Arrays.sort(engi, (a, b) -> {
        return b[1] - a[1];  //efficiency
    });

    long ans = 0, s = 0, mod = (int) 1e9 + 7;
    PriorityQueue<Integer> pq = new PriorityQueue<>();  //speed dalenge
    for (int i = 0; i < n; i++) {
        s += engi[i][0];
        pq.add(engi[i][0]);
        if (pq.size() > k)
            s -= pq.remove();
        ans = Math.max(ans, (s * engi[i][1]));
    }
    return (int) (ans % mod);
}  

// 502
public int findMaximizedCapital(int k, int ic, int[] profits, int[] capital) {
    PriorityQueue<Integer> pq1 = new PriorityQueue<>((a, b) -> {
        return profits[b] - profits[a];  //no need to makr array for profit & capital
    });

    PriorityQueue<Integer> pq2 = new PriorityQueue<>((a, b) -> {
        return capital[a] - capital[b];
    });

    int n = profits.length;
    for (int i = 0; i < n; i++)
        pq2.add(i);

    while (k-- > 0) {
        while (pq2.size() != 0 && capital[pq2.peek()] <= ic) {
            pq1.add(pq2.remove());
        }

        if (pq1.size() != 0)
            ic += profits[pq1.remove()];
    }

    return ic;
}


// 857
static class pair {
    double ratio;
    int quality;

    pair(double ratio, int quality) {
        this.ratio = ratio;
        this.quality = quality;
    }
}

public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
    int n=quality.length;
    double ans=Double.MAX_VALUE;
    
    for(int i=0;i<n;i++)
    {
        double ratio=(wage[i]*1.0)/quality[i];
        double leastMoney=0;
        PriorityQueue<Double>pq = new PriorityQueue<>((a, b) -> {
            if(a>b)return -1;
            else if(b>a)return 1;
            return 0;
        });
        for(int j=0;j<n;j++)
        {
            double salary=ratio*quality[j];
            if(salary>=wage[j])
              pq.add(salary);
            if(pq.size()>k)
                pq.remove();
        }
        
        if(pq.size()==k)
        {
            while(pq.size()!=0)
            leastMoney+=pq.remove();

            ans=Math.min(ans,leastMoney);
        }
    }
    
    return ans;
}

//2nd approach put all ratio in array sort the array and check for captain from k-1
public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
    int n=quality.length;
    List<pair>li=new ArrayList<>();
    for(int i=0;i<n;i++)
    {
        double ratio=(wage[i]*1.0)/quality[i];
        li.add(new pair(ratio,quality[i]));    
    }
    
    Collections.sort(li,(a,b)->{
        if(a.ratio>b.ratio)return 1;
        else if(b.ratio>a.ratio)return -1;
        return 0;
    });
    
    PriorityQueue<Integer>pq = new PriorityQueue<>((a, b) -> {
            return b-a;
    });
    
    int sum=0;
    for(int i=0;i<k;i++)
    {
        pq.add(li.get(i).quality);
        sum+=li.get(i).quality;
    }
    
    double ratio=li.get(k-1).ratio;
    double minCost=ratio*sum,ans=minCost;
    for(int i=k;i<n;i++)
    {        
        if(li.get(i).quality<pq.peek())
        {
            int q=pq.remove();
            sum-=q;
            pq.add(li.get(i).quality);
            sum+=li.get(i).quality;
        }
        
        ratio=li.get(i).ratio;
        minCost=ratio*sum;
        ans=Math.min(ans,minCost);
    }
    
    return ans;
}

//1465
int maxArea(int h, int w, vector<int>& horizontalCuts, vector<int>& verticalCuts) {             
    horizontalCuts.insert(horizontalCuts.begin(),0);
    horizontalCuts.push_back(h);
    verticalCuts.insert(verticalCuts.begin(),0);
    verticalCuts.push_back(w);
    
    sort(horizontalCuts.begin(),horizontalCuts.end());
    sort(verticalCuts.begin(),verticalCuts.end());
    
    int maxH=-1e9;
    int maxV=-1e9;
    
    for(int i=0;i<horizontalCuts.size()-1;i++)
        maxH=max(maxH,horizontalCuts[i+1]-horizontalCuts[i]);
    
    for(int i=0;i<verticalCuts.size()-1;i++)
        maxV=max(maxV,verticalCuts[i+1]-verticalCuts[i]);
    
    int mod=1e9+7;
    // cout<<maxH<<" "<<maxV;
    long max1=maxH,max2=maxV;
    return (max1*max2)%mod;;
}
