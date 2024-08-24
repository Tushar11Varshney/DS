// Min platfrom reqd for all trains
// car pooling(2)

// o(n) space
// https://practice.geeksforgeeks.org/problems/minimum-platforms-1587115620/1
static int findPlatform(int arr[], int dep[], int n) { // treeset mein lower bhot saare chotton mein se dikhayga value sabse closest to element tou jab treeSet use kra tou zyada count aaya aana chahiye tha kam testcase pr
    List<int[]> li = new ArrayList<>();
    for (int i = 0; i < n; i++) {
        li.add(new int[] { arr[i], dep[i] });
    }

    Collections.sort(li, (a, b) -> {
        return a[0] - b[0];
    });

    int platforms = 1;
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    pq.add(li.get(0)[1]);
    for (int i = 1; i < n; i++) {
        Integer val = pq.peek();
        if (val < li.get(i)[0]) {
            pq.remove();
            pq.add(li.get(i)[1]);
        } else {
            platforms++;
            pq.add(li.get(i)[1]);
        }
    }
    return platforms;
}

// o(1)space
static int findPlatform(int arr[], int dep[], int n) { 
    Arrays.sort(arr);
    Arrays.sort(dep);

    int platforms = 1, i = 1, j = 0; // i moves always i is for next train and j is for train currently running
    while (i < n) {
        if (dep[j] < arr[i]) // same platform can not be used for both departure of a train and arrival of another train.
        {
            i++;
            j++;
        } else {
            platforms++;
            i++;
        }
    }
    return platforms;
}


// 1094
public boolean carPooling(int[][] trips, int capacity) {
        int n=trips.length;
        int [][]tripStart=new int[n][2];
        
        for(int i=0;i<n;i++)
        {
            tripStart[i][0]=trips[i][1];
            tripStart[i][1]=trips[i][0];
        }
        
        Arrays.sort(tripStart,(a,b)->{
            return a[0]-b[0];
        });
        
        int [][]tripEnd=new int[n][2];
        
        for(int i=0;i<n;i++)
        {
            tripEnd[i][0]=trips[i][2];
            tripEnd[i][1]=trips[i][0];
        }
        
        Arrays.sort(tripEnd,(a,b)->{
            return a[0]-b[0];
        });
        
        int i=0,j=1,currentSitting=tripStart[0][1];
        while(j<n)
        {
            if(currentSitting>capacity)return false;  //sabse phle hi check krlo
            while(i<j && tripEnd[i][0]<=tripStart[j][0])
            {
                currentSitting-=tripEnd[i][1];
                i++;
            }
            currentSitting+=tripStart[j][1];
            j++;
            
        }
        
        return currentSitting>capacity?false:true;
}

public boolean carPooling2(int[][] trips, int capacity) {
    int time[] = new int[1001];
    for (int[] t : trips) {
        time[t[1]] += t[0];
        time[t[2]] -= t[0];
    }

    for (int num : time) {
        capacity -= num;
        if (capacity < 0)
            return false;
    }
    return true;
}
