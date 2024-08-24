// Least time to complete task
// Single threaded cpu


// 621
public int leastInterval(char[] tasks, int coolTime) {
    PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
        return b - a;
    });

    int n = tasks.length;
    int[] charArr = new int[26];
    for (int i = 0; i < n; i++)
        charArr[tasks[i] - 'A']++;

    for (int i = 0; i < 26; i++)
        if (charArr[i] != 0)
            pq.add(charArr[i]);

    int result = 0;
    while (pq.size() != 0) {
        List<Integer> li = new ArrayList<>();
        int time = 0;
        for (int i = 0; i < coolTime + 1; i++) {
            if (pq.size() != 0) {
                int val = pq.remove();
                if (val - 1 != 0)
                    li.add(val - 1);
                time++;
            }
        }

        while (li.size() != 0)
            pq.add(li.remove(li.size() - 1));

        result += pq.size() != 0 ? coolTime + 1 : time;
    }
    return result;
}


// 1834
class pair {
    int at;
    int pt;
    int id;

    pair(int at, int pt, int id) {
        this.at = at;
        this.pt = pt;
        this.id = id;
    }
}

public int[] getOrder(int[][] tasks) {
    int n = tasks.length;
    int[][] Tasks = new int[n][3];
    for (int i = 0; i < n; i++) {
        Tasks[i][0] = tasks[i][0];
        Tasks[i][1] = tasks[i][1];
        Tasks[i][2] = i;
    }

    Arrays.sort(Tasks, (a, b) -> {
        if (a[0] != b[0])  
            return a[0] - b[0];
        return a[1] - b[1];  //cpu will choose shortest tym wala
    });

    PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> {
        if (a.pt != b.pt)
            return a.pt - b.pt;
        return a.id - b.id;
    });

    int processTime = 0, i = 0, k = 0;
    int[] ans = new int[n];
    while (i < n || pq.size() != 0) {
        if (pq.size() == 0) {
            pq.add(new pair(Tasks[i][0], Tasks[i][1], Tasks[i][2]));
            processTime =  Math.max(processTime, Tasks[i][0]);
            i++;
        }
        pair p = pq.remove();
        processTime += p.pt;
        ans[k++] = p.id;

        while (i < n) {
            if (Tasks[i][0] <= processTime) {
                pq.add(new pair(Tasks[i][0], Tasks[i][1], Tasks[i][2]));
                i++;
            } else
                break;
        }
    }

    return ans;
}


