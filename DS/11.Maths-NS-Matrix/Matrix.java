import java.util.*;

public class Matrix {

    public static void display(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void display1(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 2d demo-input & display,
    public static void Demo2d() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] matrix = new int[n][m];
        int val, i = 0, j;
        while (i < n) // can use both for and while loop if value was asked to fill like 11 12.if we start iteration from 1 to <=n and use i in matrix then it will show exception when i=n.
        {
            j = 0;
            while (j < m) {
                val = sc.nextInt();
                // if (val == ((i + 1) * 10 + (j + 1))) { //no need qs mein sirf input lena h
                matrix[i][j] = val;
                j++;
                // }
            }
            i++;
        }
        display(matrix);
    }

    public static void matrixMultiplication() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] matrix1 = new int[n][m];
        //input array matrix1 using for loop

        int p = sc.nextInt();
        int q = sc.nextInt();
        if (m != p) {
            System.out.println("Invalid input");
            return;
        }

        int[][] matrix2 = new int[p][q];
        //input array matrix2 using for loop

        int[][] matrix3 = new int[n][q];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < q; j++) {
                matrix3[i][j] = 0;
                for (int k = 0; k < m; k++) {
                    matrix3[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        display(matrix3);
    }

    public static void waveTraversal() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] A = new int[n][m];
        //input array using for loop

        for (int col = 0; col < A[0].length; col++) {
            if (col % 2 == 0) {
                for (int row = 0; row < A.length; row++) {
                    System.out.println(A[row][col]);
                }
            } else {
                for (int row = A.length - 1; row >= 0; row--) {
                    System.out.println(A[row][col]);
                }
            }
        }
    }

    public static void spiralTraversal() {  
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] A = new int[n][m];

        int totalElement = n * m;
        int count = 0;
        int minrow = 0, mincolumn = 0, maxrow = A.length - 1, maxcolumn = A[0].length - 1;
        while (count < totalElement) {
            for (int i = minrow, j = mincolumn; i <= maxrow && count < totalElement; i++) // left column
            {
                System.out.println(A[i][j]);
                count++;
            }
            mincolumn++;

            for (int i = maxrow, j = mincolumn; j <= maxcolumn && count < totalElement; j++) // bottom row
            {
                System.out.println(A[i][j]);
                count++;
            }
            maxrow--;

            for (int i = maxrow, j = maxcolumn; i >= minrow && count < totalElement; i--) // right column
            {
                System.out.println(A[i][j]);
                count++;
            }
            maxcolumn--;

            for (int i = minrow, j = maxcolumn; j >= mincolumn && count < totalElement; j--) // top row
            {
                System.out.println(A[i][j]);
                count++;
            }
            minrow++;
        }
    }

    // 54
    public List<Integer> spiralOrder(int[][] A) {

        int n = A.length;
        int m = A[0].length;

        List<Integer> result = new ArrayList<>();

        int totalElement = n * m;
        int count = 0;
        int minrow = 0, mincolumn = 0, maxrow = A.length - 1, maxcolumn = A[0].length - 1;
        while (count < totalElement) {
            for (int i = minrow, j = mincolumn; j <= maxcolumn && count < totalElement; j++) // top row
            {
                result.add(A[i][j]);
                count++;
            }
            minrow++;

            for (int i = minrow, j = maxcolumn; i <= maxrow && count < totalElement; i++) // right column
            {
                result.add(A[i][j]);
                count++;
            }
            maxcolumn--;

            for (int i = maxrow, j = maxcolumn; j >= mincolumn && count < totalElement; j--) // bottom row
            {
                result.add(A[i][j]);
                count++;
            }
            maxrow--;

            for (int i = maxrow, j = mincolumn; i >= minrow && count < totalElement; i--) // left column
            {
                result.add(A[i][j]);
                count++;
            }
            mincolumn++;
        }

        return result;
    }

    // 59
    public int[][] generateMatrix(int n) {

        int[][] A = new int[n][n];

        int totalElement = n * n;
        int count = 1;
        int minrow = 0, mincolumn = 0, maxrow = A.length - 1, maxcolumn = A[0].length - 1;
        while (count <= totalElement) {
            for (int i = minrow, j = mincolumn; j <= maxcolumn && count <= totalElement; j++)
                A[i][j] = count++;
            minrow++;

            for (int i = minrow, j = maxcolumn; i <= maxrow && count <= totalElement; i++)
                A[i][j] = count++;
            maxcolumn--;

            for (int i = maxrow, j = maxcolumn; j >= mincolumn && count <= totalElement; j--)
                A[i][j] = count++;
            maxrow--;

            for (int i = maxrow, j = mincolumn; i >= minrow && count <= totalElement; i--)
                A[i][j] = count++;
            mincolumn++;
        }
        return A;
    }

    // 885
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {

        int totalElement = rows * cols;
        List<int[]> result = new ArrayList<>();

        int count = 1;
        int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        int steps = 1, d = 0;
        result.add(new int[] { rStart, cStart });
        while (count < totalElement) {
            for (int i = 0; i < steps; i++) {
                rStart = rStart + dirs[d][0];
                cStart = cStart + dirs[d][1];

                if (rStart >= 0 && rStart < rows && cStart >= 0 && cStart < cols) {
                    result.add(new int[] { rStart, cStart });
                    count++;
                }
            }
            d = (d + 1) % 4;
            if (d == 0 || d == 2)
                steps += 1;
        }

        return result.toArray(new int[rows * cols][2]);
    }

    public static void exitPointofMatrix() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] A = new int[n][m];
        int val;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                val = sc.nextInt();
                A[i][j] = val;
            }
        }

        int dir = 0, i = 0, j = 0;
        while (true) {
            dir = (dir + A[i][j]) % 4;
            if (dir == 0) {
                j++;
            } else if (dir == 1) {
                i++;
            } else if (dir == 2) {
                j--;
            } else if (dir == 3) {
                i--;
            }

            if (i < 0) {
                i++;
                break;
            } else if (i == A.length) {
                i--;
                break;
            } else if (j < 0) {
                j++;
                break;
            } else if (j == A[0].length) {
                j--;
                break;
            }
        }
        System.out.println(i);
        System.out.println(j);
    }

    public static void rotateby90() // 48
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] A = new int[n][n]; // transpose can be done of both square and non square
        int val;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                val = sc.nextInt();
                A[i][j] = val;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = A[i][j];
                A[i][j] = A[j][i];
                A[j][i] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) // ya while li<ri li=0 and ri=arr.length-1 then li++ ri--;
            {
                int temp = A[i][j];
                A[i][j] = A[i][n - j - 1];
                A[i][n - j - 1] = temp;
            }
        }
    }

    public static void saddlePoint() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] A = new int[n][n];
        int val;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                val = sc.nextInt();
                A[i][j] = val;
            }
        }

        int flag = 0;
        for (int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE,column = 0,max = 0;
            for (int j = 0; j < n; j++) {
                if (A[i][j] < min) {
                    min = A[i][j];
                    column = j;
                }
            }
            max = min;
            for (int k=0; k < n; k++) {
                if (A[k][column] > max) {
                    max = A[k][column];
                    break;
                }
            }
            if (max == min) {
                System.out.println(max);
                flag = 1;
                break;
            }
        }
        if (flag == 0)
            System.out.println("Invalid input");
    }

    public static void searchMatrix() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] A = new int[n][n];
        int val;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                val = sc.nextInt();
                A[i][j] = val;
            }
        }

        int target = sc.nextInt();
        int si = 0, ei = n * n - 1, flag = 0;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            int row = mid / n;
            int col = mid % n;
            if (A[row][col] == target) {
                System.out.println(row);
                System.out.println(col);
                flag = 1;
                break;
            } else if (A[row][col] < target) {
                si = mid + 1;
            } else
                ei = mid - 1;
        }
        if (flag == 0)
            System.out.println("Not Found");
    }

    public static void searchMatrix1() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] A = new int[n][n];
        int val;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                val = sc.nextInt();
                A[i][j] = val;
            }
        }
        int target = sc.nextInt();
        int i = 0, j = n - 1, flag = 0;
        while (i < n && j >= 0) {
            if (A[i][j] == target) {
                System.out.println(i);
                System.out.println(j);
                flag=1;
                return;
            } else if (A[i][j] < target) {
                i++;
            } else {
                j--;
            }
        }
        if(flag==0)
        System.out.println("Not Found");
    }

    public static void diagonalTraversal(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] A = new int[n][n];
        int val;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                val = sc.nextInt();
                A[i][j] = val;
            }
        }

        for (int gap = 0; gap < n; gap++) // abe i<=j tou row wise value print krayga hume diagonal wise krani hai
        {
            for (int i = 0, j = gap; j < n; j++, i++) {
                System.out.println(A[i][j]);
            }
        }

    }

    public static void reverse(int arr[], int i, int j) {
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;

            i++;
            j--;
        }
    }

    public static void rotate(int arr[], int rotate) {
        reverse(arr, 0, arr.length - 1);
        reverse(arr, 0, rotate - 1);
        reverse(arr, rotate, arr.length - 1);
    }

    public static int[] fillOnedfromShell(int A[][], int shell, int rotate) {
        int srow = shell - 1;
        int scolumn = shell - 1;
        int erow = A.length - shell;
        int ecolumn = A[0].length - shell;
        // int size=lw+bw+rw+tw;
        // int size=(erow-srow+1)*2+(ecolumn-scolumn+1)*2 -4;
        int size = 2 * ((erow - srow) + (ecolumn - scolumn));
        int arr[] = new int[size];
        int k = 0;
        for (int i = srow; i <= erow; i++) {
            arr[k++] = A[i][scolumn];
        }
        scolumn++;

        for (int i = scolumn; i <= ecolumn; i++) {
            arr[k++] = A[erow][i];
        }
        erow--;

        for (int i = erow; i >= srow; i--) {
            arr[k++] = A[i][ecolumn];
        }
        ecolumn--;

        for (int i = ecolumn; i >= scolumn; i--) {
            arr[k++] = A[srow][i];
        }
        return arr;
    }

    public static void fillShellfromOned(int arr[], int A[][], int shell) {
        int srow = shell - 1; // 1
        int scolumn = shell - 1; // 1
        int erow = A.length - shell; // 4
        int ecolumn = A[0].length - shell; // 6

        int k = 0;
        for (int i = srow; i <= erow; i++) {
            A[i][scolumn] = arr[k++];
        }
        scolumn++;

        for (int i = scolumn; i <= ecolumn; i++) {
            A[erow][i] = arr[k++];
        }
        erow--;

        for (int i = erow; i >= srow; i--) {
            A[i][ecolumn] = arr[k++];
        }
        ecolumn--;

        for (int i = ecolumn; i >= scolumn; i--) {
            A[srow][i] = arr[k++];
        }
    }

    public static void shellRotateques() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] A = new int[n][m];
        // int[][] A={{11,12,13,14,15,16,17},
        // {21,22,23,24,25,26,27},
        // {31,32,33,34,35,36,37},
        // {41,42,43,44,45,46,47},
        // {51,52,53,54,55,56,57}};
        int val;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                val = sc.nextInt();
                A[i][j] = val;
            }
        }
        int s = sc.nextInt();
        int rotate = sc.nextInt();
        // int s=2,r=3;
        
        int arr[] = fillOnedfromShell(A, s, rotate);

        rotate = rotate % (arr.length);
        if (rotate < 0)
            rotate += arr.length;
        rotate(arr, rotate);
        
        fillShellfromOned(arr, A, s);
        display(A);
    }

}
