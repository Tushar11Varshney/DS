// Job Scheduling
// Fractional Knapsack
// Max stock if i stock on i days
// Max unit in truck
// Min boats to save every person
// Max min amt to spend to buy all candy
// assign mouse to hole and tell max time
// Min day need to buy food to survive
// Bag to token score


// https://practice.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1#
int[] JobScheduling(Job arr[], int n) { 
    int[] ans = new int[2];
    List<int[]> li = new ArrayList<>();
    for (int i = 0; i < arr.length; i++) {
        li.add(new int[] { arr[i].id, arr[i].deadline, arr[i].profit });
    }

    Collections.sort(li, (a, b) -> {
        return b[2] - a[2];
    });

    // boolean []time=new boolean[100];
    boolean[] time = new boolean[n];
    for (int i = 0; i < li.size(); i++) {
        int d = Math.min(n, li.get(i)[1]);
        while (d - 1 >= 0 && time[d - 1] != false)
            d--;
        if (d - 1 >= 0) {
            time[d - 1] = true;
            ans[1] += li.get(i)[2];
            ans[0]++;
        }
    }
    return ans;
}


double fractionalKnapsack(int W, Item arr[], int n) {
    List<double[]> li = new ArrayList<>();
    for (int i = 0; i < arr.length; i++) {
        double ratio = (arr[i].value * 1.0) / arr[i].weight;
        li.add(new double[] { ratio, arr[i].weight * 1.0 });
    }

    li.sort((a, b) -> Double.compare(a[0], b[0]));

    double profit = 0.0;
    int i = li.size() - 1;
    while (W > 0 && i >= 0) {
        if (W - li.get(i)[1] >= 0) {
            profit += li.get(i)[0] * li.get(i)[1];
            W -= li.get(i)[1];
        } else {
            profit += W * li.get(i)[0];
            W = 0;
        }
        i--;
    }
    return profit;
}

// https://www.codingninjas.com/codestudio/problems/buy-maximum-stocks-if-i-stocks-can-be-bought-on-i-th-day_1169470?leftPanelTab=0
public static int maxStock(int[] prices, int n, int amount) {
    List<int[]> li = new ArrayList<>();
    for (int i = 0; i < prices.length; i++)
        li.add(new int[] { prices[i], i + 1 });

    Collections.sort(li, (a, b) -> {
        return a[0] - b[0];
    });

    int totalStock = 0;
    for (int i = 0; i < n; i++) {
        int weCanBuy = amount / li.get(i)[0];
        if (weCanBuy > li.get(i)[1]) {
            amount -= li.get(i)[0] * li.get(i)[1];
            totalStock += li.get(i)[1];
        } else {
            totalStock += weCanBuy;
            break;
        }
    }
    return totalStock;
}

// 1710
public int maximumUnits(int[][] boxTypes, int truckSize) {

    Arrays.sort(boxTypes, (a, b) -> {
        return b[1] - a[1];
    });

    int unit = 0;
    for (int i = 0; i < boxTypes.length; i++) {
        if (truckSize >= boxTypes[i][0]) {
            unit += boxTypes[i][0] * boxTypes[i][1];
            truckSize -= boxTypes[i][0];
        } else {
            unit += truckSize * boxTypes[i][1];
            break;
        }
    }
    return unit;
}

// 881
public int numRescueBoats(int[] people, int limit) {
    Arrays.sort(people);
    int n = people.length, boats = 0, i = 0, j = n - 1;
    while (i <= j) {
        if (people[i] + people[j] <= limit) {
            boats++;
            i++;
            j--;
        } else {
            boats++;  //1 <= people[i] <= limit <= 3 * 10
            j--;
        }
    }
    return boats;
}

// https://practice.geeksforgeeks.org/problems/shop-in-candy-store1145/1
static ArrayList<Integer> candyStore(int candies[], int N, int K) {
    Arrays.sort(candies);
    int min = 0, max = 0, i = 0, j = N - 1;

    while (i <= j) {
        min += candies[i++];
        j -= K;
    }

    i = 0;
    j = N - 1;
    while (i <= j) {
        max += candies[j--];
        i += K;
    }

    ArrayList<Integer> li = new ArrayList<>();
    li.add(min);
    li.add(max);

    return li;
}

//https://practice.geeksforgeeks.org/problems/assign-mice-holes3053/1
static int assignMiceHoles(int N, int[] M, int[] H) {  //read explanation of qs
    Arrays.sort(M);
    Arrays.sort(H);

    int max = 0;
    for (int i = 0; i < N; i++) {
        max = Math.max(max, Math.abs(H[i] - M[i]));
    }

    return max;
}

// https://practice.geeksforgeeks.org/problems/check-if-it-is-possible-to-survive-on-island4922/1#
static int minimumDays(int S, int N, int M) {
    int sunday = S / 7;
    int daySheCanBuy = S - sunday;

    int totalReqmt = S * M;
    int reqddays = (int) Math.ceil(totalReqmt * 1.0 / N);

    if (reqddays <= daySheCanBuy)
        return reqddays;

    return -1;
}


// 948
public int bagOfTokensScore(int[] tokens, int P) {
    Arrays.sort(tokens);

    int score = 0, maxScore = 0;
    int i = 0, j = tokens.length - 1;
    while (i <= j) {
        if (P >= tokens[i]) {
            score++;
            P -= tokens[i++];
            maxScore = Math.max(maxScore, score);
        } else if (score > 0) {
            score--;
            P += tokens[j--];
        } else
            break; // cant do anything
    }
    return maxScore;
}

