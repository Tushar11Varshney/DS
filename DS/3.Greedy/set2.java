// N meeting in 1 room
// disjoint interval
// Min shot to burst balloon
// Max train for which stoppage can be provided

//activity selection
// https://practice.geeksforgeeks.org/problems/n-meetings-in-one-room-1587115620/1#
public static int maxMeetings(int start[], int end[], int n) {
    List<int[]> li = new ArrayList<>();
    for (int i = 0; i < n; i++)
        li.add(new int[] { start[i], end[i] });

    Collections.sort(li, (a, b) -> {
        if (a[1] == b[1])
            return a[0] - b[0];
        else
            return a[1] - b[1];  //sorted on ep
    });

    int count = 1, i = 0, j = 1;
    while (j < li.size()) {
        if (li.get(i)[1] < li.get(j)[0]) { // non overlapping
            count++;
            i = j;
            j++;
        } else       // overlapping
            j++;
    }
    return count;
}

//https://www.interviewbit.com/old/problems/disjoint-intervals/
public int disjointInterval(int[][] A) {
    Arrays.sort(A,(a,b)->{
        if (a[1] == b[1])
        return a[0] - b[0];
    else
        return a[1] - b[1];  //sorted on ep
    });
    
    int count = 1, i = 0, j = 1;
    while (j < A.length) {
        if (A[i][1] < A[j][0]) { // non overlapping
            count++;
            i = j;
            j++;
        } else       // overlapping
            j++;
    }
    return count;
}

// 452 
public int findMinArrowShots(int[][] A) {
    Arrays.sort(A,(a,b)->{   
    // Arrays.sort(A,(int []a, int[]b)->{  in the first case, the types are explicitly declared by the programmer, while in the second case, the compiler infers the types.
    if (a[1] == b[1])
    return Integer.compare(a[0], b[0]);  //[[-2147483646,-2147483645],[2147483646,2147483647]] used integer.compare because of this
    else
    return Integer.compare(a[1], b[1]);  //sorted on ep
    });
    
    int count = 1, i = 0, j = 1;
    while (j < A.length) {
        if (A[i][1] < A[j][0]) { // non overlapping
            count++;
            i = j;
            j++;
        } else       // overlapping
            j++;
    }
    return count;
}

// https://www.geeksforgeeks.org/maximum-trains-stoppage-can-provided/
static int maximumTrainsStoppage(int[][] matrix, int platform, int length) {
    HashMap<Integer, ArrayList<int[]>> map = new HashMap<>();
    for (int i = 0; i < length; i++) {
        int ptnum = matrix[i][2];
        map.putIfAbsent(ptnum, new ArrayList<>());
        map.get(ptnum).add(new int[] { matrix[i][0], matrix[i][1] });
    }

    int stoppage = 0;
    for (int key : map.keySet()) {
        ArrayList<int[]> temp = map.get(key);
        Collections.sort(temp, (a, b) -> {
            return a[1] - b[1];
        });

        int j = 0, i = 1, stop = 1;
        while (i < temp.size()) {
            int endtime = temp.get(j)[1];
            int starttime = temp.get(i)[0];

            if (endtime <= starttime) {
                j=i;
                i++;
                stop++;
            } else{
                i++;
            }
        }
        stoppage += stop;
    }
    return stoppage;
}


