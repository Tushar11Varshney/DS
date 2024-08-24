import java.util.*;
public class recursion_basic {
    
    public static void printDecreasing(int n) {
        if (n == 0)return;
        System.out.println(n);
        printDecreasing(n - 1);
    }

    public static void printIncreasing(int n) {
        if (n == 0)return;
        printIncreasing(n - 1);
        System.out.println(n);
    }

    public static void pdi(int n) { // 321123
        if (n == 0)return;
        System.out.println(n);
        pdi(n - 1);
        System.out.println(n);
    }

    public static void decreaseEven_IncreaseOdd(int n) { // 10 8 6 4 2 1 3 5 7 9
        if (n == 0)return;

        if (n % 2 == 0)System.out.println(n);
        decreaseEven_IncreaseOdd(n - 1);
        if (n % 2 != 0)System.out.println(n);
    }

    public static int factorial(int n) {
        // return n==0?1:factorial(n-1)*n;
        if (n == 0)return 1;
        else return factorial(n - 1) * n;
    }

    public static int power(int x, int n) {
        return n == 0 ? 1 : power(x, n - 1) * x;
    }

    public static int powerLogarithmic(int x, int n) {
        if (n == 0)return 1;
        int smallAns = powerLogarithmic(x, n / 2);
        return n % 2 == 0 ? smallAns * smallAns : smallAns * smallAns * x;
    }

    public static void zigZag(int n) {
        if (n == 0)return;

        System.out.println(n);
        zigZag(n - 1);

        System.out.println(n);

        zigZag(n - 1);
        System.out.println(n);
    }

    public static int stackTrace(int n) {
        if (n == 0 || n == 1) {
            System.out.println("base:" + " " + n);
            return n;
        }
        int ans = 0;
        System.out.println("pre:" + " " + n);
        ans += stackTrace(n - 1);

        System.out.println("in:" + " " + n);

        ans += stackTrace(n - 2);
        System.out.println("post:" + " " + n);
        return ans + 3;
    }

    public static void displayArr(int[] arr, int idx) {
        // if(idx<0) return ;
        // displayArr(arr,idx-1);
        // System.out.println(arr[idx]);

        if (idx == arr.length)return;
        System.out.println(arr[idx]);
        displayArr(arr, idx + 1);
    }

    public static void displayArrReverse(int[] arr, int idx) {
        if (idx < 0)return;
        System.out.println(arr[idx]);
        displayArrReverse(arr, idx - 1);

        // if(idx==arr.length)return;
        // displayArrReverse(arr, idx+1);
        // System.out.println(arr[idx]);
    }

    public static int maxOfArray(int[] arr, int idx) {
        if (idx == arr.length)
            return -(int) 1e9;
        // int ans=maxOfArray(arr,idx+1);
        // ans=Math.max(ans,arr[idx]);

        // int ans=Math.max(maxOfArray(arr,idx+1),arr[idx]);

        // return ans;
        return Math.max(maxOfArray(arr, idx + 1), arr[idx]);
    }

    public static int minOfArray(int[] arr, int idx) {
        if (idx == arr.length)return (int) 1e9;
        return Math.min(minOfArray(arr, idx + 1), arr[idx]);
    }

    public static int firstIndex(int[] arr, int idx, int x) {
        if (idx == arr.length)return -1;
        else if (arr[idx] == x) 
            return idx;
        else return firstIndex(arr, idx + 1, x); // return likhna h is line mein bcz agr if else-if nhi chle tou fir fn puchega kya return krna h
    }

    public static int lastIndex2(int[] arr, int idx, int x) { // zyada badia
        if (idx < 0)return -1;
        else if (arr[idx] == x)return idx;
        else return lastIndex2(arr, idx - 1, x);
    }

    public static int lastIndex(int[] arr, int idx, int x) {
        if (idx == arr.length)
            return -1;
        int index = lastIndex(arr, idx + 1, x);
        if (index != -1)
            return index;

        return arr[idx] == x ? idx : -1;
    }

    public static int[] allIndices(int[] arr, int x, int idx, int count) {
        if (idx == arr.length)
            return new int[count];
        else if (arr[idx] == x)
            count++;
        int res[] = allIndices(arr, x, idx + 1, count);
        if (arr[idx] == x)
            res[count - 1] = idx;

        // for(int i=0;i<count;i++) //agar sirf array return krni hoti jisme x hai...but ye hr baar krega put while going back..isse badiya sirf return count.
        // res[i]=x;
        return res;
    }

    public static void toh(int n, int t1id, int t2id, int t3id) {
        if(n==0)
        return;
        
        toh(n-1,t1id,t3id,t2id);
        System.out.println(n+"["+t1id+" -> "+t2id+"]");
        toh(n-1,t3id,t2id,t1id);
    }
    
    public static void getStairPaths(int n,ArrayList<String>res,String psf){
        if(n==0)
            res.add(psf);
            
        if(n-1>=0)        
        getStairPaths(n-1,res,psf+"1");
        
        if(n-2>=0)        
        getStairPaths(n-2,res,psf+"2");
        
        if(n-3>=0)        
        getStairPaths(n-3,res,psf+"3");  
    }

    public static ArrayList<String> getStairPathsSir(int n) {
        if (n == 0) {
            ArrayList<String> base1 = new ArrayList<>();
            base1.add("");
            return base1;
        } else if (n < 0) {
            ArrayList<String> base2 = new ArrayList<>();
            return base2;
        }

        ArrayList<String> path1 = getStairPathsSir(n - 1);
        ArrayList<String> path2 = getStairPathsSir(n - 2);
        ArrayList<String> path3 = getStairPathsSir(n - 3);
        ArrayList<String> paths = new ArrayList<>();
        for (String str : path1) {
            paths.add("1" + str);
        }
        for (String str : path2) {
            paths.add("2" + str);
        }
        for (String str : path3) {
            paths.add("3" + str);
        }
        return paths;
    }

// public static int printTargetSumSubsets(int[] arr, int idx,int sos,String set, int tar) {
public static int printTargetSumSubsets(int[] arr, int idx, String set, int tar) {
    // if(tar==sos)
    if (tar == 0) {
        System.out.println(set + ".");
        return 1;
    }

    int count = 0;
    for (int i = idx; i < arr.length; i++) {
        // if(sos+arr[i]<=tar)
        if (tar - arr[i] >= 0) {
            // count+=printTargetSumSubsets(arr,i+1,sos+arr[i],set+arr[i]+", ",tar);
            count += printTargetSumSubsets(arr, i + 1, set + arr[i] + ", ", tar - arr[i]);
        }
    }
    return count;
}

public static void printTargetSumSubsets(int[] arr, int idx, String set, int sos, int tar) {
    if (idx == arr.length) {
        if (sos == tar) {
            System.out.println(set + ".");
        }
        return;
    }

    printTargetSumSubsets(arr, idx + 1, set + arr[idx] + ", ", sos + arr[idx], tar);
    printTargetSumSubsets(arr, idx + 1, set, sos, tar);
}

// 91
public static int printEncodings(String str, int idx, String ans) {
    if (idx == str.length()) {
        System.out.println(ans);
        return 1;
    }

    char ch = str.charAt(idx);
    if (ch == '0')
        return 0;
    int count = 0;
    count += printEncodings(str, idx + 1, ans + (char) (ch - '1' + 'a')); // -1 depend on mapping agar char mein nhi typecast krenge tou string mein number ki trh add hojaata jaayga(Eg='9'-'1'+'a'=105).aur jb do number waale char ko subtract krte hai tou hume integer differnce milta hai.

    if (idx < str.length() - 1) {
        int num = (str.charAt(idx) - '0') * 10 + (str.charAt(idx + 1) - '0');
        if (num < 27) {
            count += printEncodings(str, idx + 2, ans + (char) (num - 1 + 'a')); // -1 depend on mapping
        }
    }
    return count;
}


// leetcode 17
static String arr2[] = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
public static ArrayList<String> printKPC_(String str, int idx) {
    if (idx == str.length()) {
        ArrayList<String> base = new ArrayList<>();
        base.add("");
        return base;
    }

    int digit = str.charAt(idx) - '0';
    ArrayList<String> smallAns = printKPC_(str, idx + 1);
    ArrayList<String> ans = new ArrayList<>();

    String code = arr2[digit];
    for (int i = 0; i < code.length(); i++) {
        for (String str1 : smallAns)
            ans.add(code.charAt(i) + str1);
    }
    return ans;
}

public List<String> letterCombinations(String digits) {
    ArrayList<String> res = new ArrayList<>();
    if (digits.length() == 0)
        return res;
    return printKPC(digits, 0);
}

static String codes1[] = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz", "+-*", "/%^" };
public static int KpcExtension(String str, int idx, String ans) {
    if (str.length() == idx) {
        System.out.println(ans);
        return 1;
    }
    int num = str.charAt(idx) - '0';
    String code = codes1[num];
    int count = 0;
    for (int i = 0; i < code.length(); i++)
        count += KpcExtension(str, idx + 1, ans + code.charAt(i));
    if (idx < str.length() - 1) {
        num = (str.charAt(idx) - '0') * 10 + (str.charAt(idx + 1) - '0');
        if (num == 10 || num == 11) {
            code = codes1[num];
            for (int i = 0; i < code.length(); i++)
                count += KpcExtension(str, idx + 2, ans + code.charAt(i));
        }
    }
    return count;
}

// 1.Pehle hi check if ans possible or not
// 2.Depth wise travel krna to break loop,radius mein travel krna
// 3.visited mein blocker waali value dalna

public static int printMazePaths(int sr, int sc, int dr, int dc, String psf) {
    if (sr == dr && sc == dc) {
        System.out.println(psf);
        return 1;

        // res.add(psf); //pep par ArrayList<String> deni hai
        // return res;
    }

    int count = 0;
    if (sc + 1 <= dc)
        count += printMazePaths(sr, sc + 1, dr, dc, psf + "h"); // ek hi baat pehle "h"+psf .ans upar niche hojaynge but

    // if(sc+1<=dc && sr+1<=dr)count+=printMazePaths(sr+1,sc+1,dr,dc,psf+"d");

    if (sr + 1 <= dr)count += printMazePaths(sr + 1, sc, dr, dc, psf + "v");

    return count;
}

public static int printMazePath(int sr, int sc, int dr, int dc, String psf) {
    if (sr == dr && sc == dc) {
        System.out.println(psf);
        return 1;

        // res.add(psf); //pep par we have to give ArrayList<String>
    }

    int count = 0;
    for (int i = 1; i <= dc; i++) {
        if (sc + i <= dc)
            count += printMazePath(sr, sc + i, dr, dc, psf + "h" + i);
    }

    for (int i = 1; i <= dr; i++) {
        if (sr + i <= dr)
            count += printMazePath(sr + i, sc, dr, dc, psf + "v" + i);
    }

    for (int i = 1; i <= dr; i++) {
        if (sr + i <= dr && sc + i <= dc)
            count += printMazePath(sr + i, sc + i, dr, dc, psf + "d" + i);
    }

    return count;
}

static int dir[][] = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
static char direction[] = { 'r', 's', 'd', 'w', 'l', 'n', 'u', 'e' };
public static int printMazePath2(int sr, int sc, int dr, int dc, String psf, int visited[][]) {
    if (sr == dr && sc == dc) {
        System.out.println(psf);
        return 1;
    }

    int count = 0;
    visited[sr][sc] = 1; // agar yahan mark krenge iska mtlb ki humne jaakr maark kia
    for (int i = 0; i < dir.length; i++) {
        int row = sr + dir[i][0];
        int column = sc + dir[i][1];
        if (row >= 0 && column >= 0 && row <= dr && column <= dc && visited[row][column] == 0) {
            // visited[row][column] = 1; //agr yahan mark krenge iska mtlb jaane se phle mark kiya
            //aur iskelie neeche se 0,0 kelie pehle hi set
            count += printMazePath2(row, column, dr, dc, psf + direction[i], visited);
            // visited[row][column] = 0; //yhan pr krne se ye hota h ki vo baar baar ussi point
            // kelie true false true false krta rhta hai.so this will be costly operation.
        }
    }
    visited[sr][sc] = 0;
    return count;
}

public static int printMazePathmultipleJump(int sr, int sc, int dr, int dc, String psf, int visited[][]) {
    if (sr == dr && sc == dc) {
        System.out.println(psf);
        return 1;
    }

    int count = 0;
    visited[sr][sc] = 1;
    for (int i = 0; i < dir.length; i++) {
        for (int rad = 1; rad <= Math.max(dr, dc); rad++) {
            int row = sr + rad * dir[i][0];
            int column = sc + rad * dir[i][1];
            if (row >= 0 && column >= 0 && row <= dr && column <= dc) {
                if (visited[row][column] == 0)
                    count += printMazePathmultipleJump(row, column, dr, dc, psf + direction[i] + rad, visited);
            } else break;
        }
    }
    visited[sr][sc] = 0;
    return count;
}

//https://www.pepcoding.com/resources/online-java-foundation/recursion-backtracking/flood-fill-official/ojquestion (1 obstacle) 
static int dir2[][] = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
static char direction2[] = { 't', 'l', 'd', 'r' };

public static void floodfill(int sr, int sc, int dr, int dc, String psf, int visited[][]) {
    if (sr == dr && sc == dc) {
        System.out.println(psf);
    }

    visited[sr][sc] = 1;
    for (int i = 0; i < dir2.length; i++) {
        int row = sr + dir2[i][0];
        int column = sc + dir2[i][1];
        if (row >= 0 && column >= 0 && row <= dr && column <= dc && visited[row][column] != 1) {
            floodfill(row, column, dr, dc, psf + direction2[i], visited);
        }
    }
    visited[sr][sc] = 0;
}

//https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1#
static int dir1[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
static char direction1[] = { 'U', 'D', 'L', 'R' };

public static void printMazePathJump(int sr, int sc, int dr, int dc, String psf, int visited[][],
        ArrayList<String> arr, int m[][]) { // rat in a maze single jump
    if (sr == dr && sc == dc) {
        arr.add(psf);
    }

    visited[sr][sc] = 1;
    for (int i = 0; i < dir1.length; i++) {
        int row = sr + dir1[i][0];
        int column = sc + dir1[i][1];
        if (row >= 0 && column >= 0 && row <= dr && column <= dc && m[row][column] != 0 && visited[row][column] == 0) {
            printMazePathJump(row, column, dr, dc, psf + direction1[i], visited, arr, m);
        }
    }
    visited[sr][sc] = 0;
}

public static ArrayList<String> findPath(int[][] m, int n) {
    ArrayList<String> arr = new ArrayList<>();
    if (n == 0 || m[0][0] == 0 || m[n - 1][n - 1] == 0)
        return arr;
    int[][] visited = new int[n][n];
    printMazePathJump(0, 0, n - 1, n - 1, "", visited, arr, m);
    Collections.sort(arr); // given in qs return sorted list of path
    return arr;
}

public static void printMazePath_WithoutVisited(int sr, int sc, int dr, int dc, String psf, int visited[][], 
        ArrayList<String> arr) {
    if (sr == dr && sc == dc) {
        arr.add(psf);
    }

    visited[sr][sc] = 0; // mark
    for (int i = 0; i < dir1.length; i++) {
        int row = sr + dir1[i][0];
        int column = sc + dir1[i][1];
        if (row >= 0 && column >= 0 && row <= dr && column <= dc && visited[row][column] != 0) {
            printMazePath_WithoutVisited(row, column, dr, dc, psf + direction1[i], visited, arr);
        }
    }
    visited[sr][sc] = 1;
}

public static ArrayList<String> findPath2(int[][] m, int n) {
    ArrayList<String> arr = new ArrayList<>();
    if (m[0][0] == 0 || m[n - 1][n - 1] == 0)
        return arr;
    printMazePath_WithoutVisited(0, 0, n - 1, n - 1, "", m, arr);
    Collections.sort(arr); // here no need for visited array can use this array only for visited purpose.
    return arr;
}

// https://practice.geeksforgeeks.org/problems/rat-maze-with-multiple-jumps/0#:~:text=A%20Maze%20is%20given%20as,forward%20if%20possible%20or%20down
public static void display(int[][] m) {
    for (int i = 0; i < m.length; i++) {
        for (int j = 0; j < m[0].length; j++) {
            System.out.printf("%d ", m[i][j]);
        }
        System.out.println("");
    }
}

public static boolean ratMazeWithMultipleJump(int sr, int sc, int dr, int dc, int[][] dir, int[][] m,int[][] pathDirection) {
    if (sr == dr && sc == dc) {
        pathDirection[sr][sc] = 1; // iske answer mein humse bola h ki pahunchkr mark kro thats why need to do and humne isko wapas 0 bnane ki zrurat ni but aesi aadat bnalo ki agr koi cheez change kri hai then wapas usko waise hi krdo jaise pehle tha kaam hone ke baad
        display(pathDirection);
        pathDirection[sr][sc] = 0;
        return true;
    }

    boolean res = false;
    pathDirection[sr][sc] = 1;
    for (int jump = 1; jump <= m[sr][sc]; jump++) {  //first check in radius
        for (int d = 0; d < dir.length; d++) {
            int r = sr + jump * dir[d][0];
            int c = sc + jump * dir[d][1];

            if (r >= 0 && r <= dr && c >= 0 && c <= dc) {
                res = res || ratMazeWithMultipleJump(r, c, dr, dc, dir, m, pathDirection);
                // if(res==true)
                // return res;
            }
        }
    }
    pathDirection[sr][sc] = 0;
    return res;

    // Scanner sc=new Scanner(System.in);
    // int t=sc.nextInt();
    // int [][]dir={{0,1},{1,0}};
    // while(t -- > 0)
    // {
    // int n=sc.nextInt();
    // int m[][]=new int[n][n];
    // int [][]pathDirection=new int[n][n];
    // for(int i=0;i<n;i++)
    // {
        // for(int j=0;j<n;j++)
        // {
        // m[i][j]=sc.nextInt();
        // }
    // }

    // boolean res=false;
    // if(m[0][0]!=0)
    // res=ratMazeWithMultipleJump(0,0,n-1,n-1,dir,m,pathDirection);
    // if(res==false)System.out.println(-1);
    // }
}

static class Pair {
    int longestPathCount;
    String longestPath;

    Pair(int longestPathCount, String longestPath) {
        this.longestPathCount = longestPathCount;
        this.longestPath = longestPath;
    }
}

public static Pair longestPath(int sr, int sc, int dr, int dc, int[][] visited, int[][] dir, char[] dirs) {
    if (sr == dr && sc == dc) {
        return new Pair(0, "");
    }

    Pair myans = new Pair(-1, ""); // agar blocked waala question hua aur staring pt se saari dirn block h jahan se call lgskti hai tou agr myans meinagar count hum 0 krenge tou call tou kyunki koi lgi nhi h then hume jo ans milega wo hoga 0 jisse ye hoskta h ki hume jab answer mile ki 0 longest path jabki actual mein koi path tha hi ni blockage ki wjh se so return -1

    visited[sr][sc] = 1;
    for (int d = 0; d < dir.length; d++) {
        int r = sr + dir[d][0];
        int c = sc + dir[d][1];

        if (r >= 0 && r <= dr && c >= 0 && c <= dc && visited[r][c] == 0) {
            Pair recursiveAns = longestPath(r, c, dr, dc, visited, dir, dirs);
            //blockage ni h isliye need not apply recursiveAns.longestPathCount!=-1
            if (recursiveAns.longestPathCount + 1 > myans.longestPathCount) {
                myans.longestPathCount = recursiveAns.longestPathCount + 1;
                myans.longestPath = dirs[d] + recursiveAns.longestPath;
            }
        }
    }
    visited[sr][sc] = 0;

    return myans;
    // int [][] visited=new int[3][3];
    // Pair result=longestPath(0,0,2,2,visited,dir1,direction1);
    // System.out.println(result.longestPath);
}

//1291
public List<Integer> sequentialDigits(int low, int high) {
    List<Integer>ans=new ArrayList<>();
    
    for(int i=1;i<=8;i++)
        sequential_DigitHelper(ans,low,high,0,i);
    Collections.sort(ans);
    return ans;
}

public void sequential_DigitHelper(List<Integer>ans,int low,int high,int num,int dig)
{
    if(num>=low && num<=high)
        ans.add(num);
    
    if(num>high || dig==10)
        return;
    
    sequential_DigitHelper(ans,low,high,num*10+dig,dig+1);
}
