// Bubble
// Selection
// Insertion
// Merge sort
// Quick sort
// Count sort
// Count sort Radix
// Maximum Gap

public static void bubbleSort(int[] arr) {
    // 8 7 6
    // ans-> 7 8 6 => 7 6 8
    // 6 7 8
    for (int i = 0; i < arr.length-1; i++) {   //loop for no of iteration
        int flag=0;
        for (int j = 1; j <= arr.length - i -1; j++) {   //us iteration mein kitne parts
            if (arr[j]<arr[j-1]) {                  
                swap(arr, j, j - 1);
                flag=1;
            }
        }
        if(flag==0){
            System.out.println("break in btw");
            break;
        }
    }
}

public static void selectionSort(int[] arr) {   
    for (int i = 0; i < arr.length - 1; i++) {       //loop for no of iteration
        int min = i;
        for (int j = i + 1; j < arr.length; j++) {       //loop min dhundne kelie us posn pr jo aana chahiye
            if (arr[j]<arr[min])
                min = j;
        }
        swap(arr, i, min); 
    }
}

public static void insertionSort(int[] arr) {
    // 9 8 7 6
    // 8 9 7 6
    // 8 7 9 6 => 7 8 9 6
    // 7 8 6 9 => 7 6 8 9 => 6 7 8 9 
    for (int i = 1; i < arr.length; i++) {        //loop for no of iteration
        for (int j = i; j >0; j--) {
            if (arr[j]<arr[j-1])
                swap(arr, j, j - 1);
            else
                break; // kyunki hum apne element sorted order mein maintain krke chlrhe the..tou agr koi element apne piche waale ko kisi tym pr beat nhi krpaaya tou usse piche waale kyunki usse chhote hain tou unko bhi kbhi beat nhi krpaayaga.
        }
    }
}

public static void countSortRadix(int[] arr, int exp) {
    int psum[] = new int[10];
    int sortedArray[] = new int[arr.length];
    for (int ele : arr) {
        psum[(ele / exp) % 10]++;
    }
    psum[0]--;
    for (int i = 1; i < 10; i++) {
        psum[i] = psum[i] + psum[i - 1];
    }
    
    for (int i = arr.length - 1; i >= 0; i--) {
        int val = arr[i];
        int index = psum[(val / exp) % 10];
        sortedArray[index] = val;
        psum[(val / exp) % 10]--;
    }
    for (int i = 0; i < arr.length; i++) {
        arr[i] = sortedArray[i];
    }
}

public static void radixSort(int[] arr) {
    int max = -(int) 1e9;
    for (int ele : arr) {
        max = Math.max(max, ele);
    }
    int exp = 1;  //exponent
    while (exp <= max) {
        countSortRadix(arr, exp);
        exp = exp * 10;
    }
}

public static int[] mergeTwoSortedArrays(int[] a, int[] b) {

    int res[] = new int[a.length + b.length];
    int k = 0, i = 0, j = 0;
    while (i < a.length && j < b.length) {
        if (a[i] < b[j])
            res[k++] = a[i++];
        // else if(b[j]<a[i]) //jab element dono array mein same ho tou dono array se copy krna h
        else
            res[k++] = b[j++];
        // else{
        // res[k++]=a[i++]; 
        // j++; //ye ni chlega bcz j incremented also

        // res[k++]=a[i++];
        // res[k++]=b[j++]; //ye chlega
        // }
    }
    while (i < a.length)
        res[k++] = a[i++];
    while (j < b.length)
        res[k++] = b[j++];

    return res;
}

public static int[] mergeSort(int[] arr, int lo, int hi) {
    if (lo == hi) {
        int bcarray[] = new int[1];
        bcarray[0] = arr[lo];
        return bcarray;
    }
    // else if(lo<hi){
    int mid = (lo + hi) / 2;
    int fp[] = mergeSort(arr, lo, mid);
    int sp[] = mergeSort(arr, mid + 1, hi);
    int res[] = mergeTwoSortedArrays(fp, sp);
    return res;
    // }
    // return new int[0]; //dmsn zruri   //cant do {} ya {0}
    // return null;
}

public static int partition(int[] arr, int val, int lo, int hi) {
    int pivot=lo-1, i=lo;
    while (i <= hi) {
        if (arr[i] <= val) {
            ++pivot;
            int temp=arr[pivot];
            arr[pivot]=arr[i];
            arr[i]=temp;
            i++;
        } else i++;
    }
    return pivot;
}

public static void quickSort(int[] arr, int lo, int hi) {
    if (lo >= hi)
    return;
    
    int val = arr[hi];
    int pivotIndex = partition(arr, val, lo, hi);
    
    quickSort(arr, lo, pivotIndex - 1);
    quickSort(arr, pivotIndex + 1, hi);
}


public static void countSort(int[] arr, int min, int max) {

    int size = max - min + 1;
    int psum[] = new int[size];
    int sortedArray[] = new int[arr.length];
    for (int ele : arr) {
        psum[ele - min]++; // create frequency array first then psum array
    }
    psum[0]--;
    for (int i = 1; i < size; i++) {
        psum[i] = psum[i] + psum[i - 1];
    }
    for (int i = arr.length - 1; i >= 0; i--) {  //frequency ke hisaab se krenge tou relative order is not maintained
        int val = arr[i];
        int index = psum[val - min];
        sortedArray[index] = val;
        psum[val - min]--;
    }
    
    for (int i = 0; i < arr.length; i++) // copy to arr
        arr[i] = sortedArray[i];
}

public int maximumGap(int[] arr) {
    if(arr.length<=1) return 0;
    int max = -(int) 1e9;
    for (int ele : arr) {
        max = Math.max(max, ele);
    }
    int exp = 1;  //exponent
    while (exp <= max) {
        countSortRadix(arr, exp);
        exp = exp * 10;
    }

    int maxDistance=-(int)1e9;
    for(int i=0;i<arr.length-1;i++)
    {
        maxDistance=Math.max(maxDistance, arr[i+1]-arr[i]);
    }

    return maxDistance;
}
