import java.util.*;

/*


Check div by 3

Power of 2
Sum of bit difference among all pair
Count set bit in first n natural #
Swap all odd and even
Flip bits a to b
Abbreviation using bits
N Bit grey code
copy set bit in range
Print binary and reverse bits

Solve 7n/8
trailing zero
Josephus Problem
Minimum software developer reqd
Power of 4
Min xor pair
Xor oprn in array
Count # of set bits(Hamming wt-3 )
subsetXORSum
*/

public class bitmanipulation {

    // 137
    public int singleNumber(int[] arr) {
        // Because in integer.max_value , the 32th bit is 0, as it is a positive number
        // , create a bit mask with all 32 bits on. The number representing all 32 bits
        // on is -1.
        int tn = -1, tnp1 = 0, tnp2 = 0;

        for (int i = 0; i < arr.length; i++) {
            int ctn = arr[i] & tn;
            int ctnp1 = arr[i] & tnp1;
            int ctnp2 = arr[i] & tnp2;

            tn = tn & (~ctn);
            tnp1 = tnp1 | ctn;

            tnp1 = tnp1 & (~ctnp1);
            tnp2 = tnp2 | ctnp1;

            tnp2 = tnp2 & (~ctnp2);
            tn = tn | ctnp2;
        }
        return tnp1;
    }

    public static int reverse(int n) {
        int rev = 0;
        while (n != 0) {
            int lb = (n & 1); // lowest bit
            rev |= lb;

            n >>= 1;
            rev <<= 1;
        }

        rev >>= 1;
        return rev;
    }

    public static int NthPalindromicBinary(int n) {
        int count = 1;
        int len = 1;

        while (count < n) {
            len++;
            int elementForthisLen = (1 << (len - 1) / 2);
            count += elementForthisLen;
        }

        count -= (1 << (len - 1) / 2);
        int offset = n - count - 1;

        int ans = (1 << (len - 1));
        ans |= (offset << (len / 2));

        int valFR = (ans >> (len / 2)); // val for reverse
        int rev = reverse(valFR);

        ans |= rev;

        return ans;
    }

    // 393
    public boolean validUtf8(int[] arr) {
        int rbytes = 0;
        for (int val : arr) {
            if (rbytes == 0) {
                if ((val >> 7) == 0b0) // 1 byte of 1 length char
                    rbytes = 0;
                else if ((val >> 5) == 0b110) // 1 byte of 2 length char
                    rbytes = 1;
                else if ((val >> 4) == 0b1110) // 1 byte of 3 length char
                    rbytes = 2;
                else if ((val >> 3) == 0b11110) // 1 byte of 4 length char
                    rbytes = 3;
                else if ((val >> 7) != 0b0) // [255]
                    return false;
            } else {
                if ((val >> 6) == 0b10)
                    rbytes--;
                else
                    return false;
            }
        }

        if (rbytes == 0)
            return true;
        else
            return false;
    }

    public static int reduceNto1(long n) {
        int res = 0;
        while (n != 1) {
            if (n % 2 == 0)
                n = n / 2;
            else if (n == 3) {
                res+ = 2;
                break;
            } else if ((n & 3) == 1)
                n = n - 1;
            else if ((n & 3) == 3)
                n = n + 1;

            res++;
        }

        return res;
    }

    public static long ncr(long n, long r) {
        if (n < r)
            return 0L;

        long res = 1L;

        for (long i = 0L; i < r; i++) {
            res = res * (n - i);
            res = res / (i + 1);
        }

        return res;
    }

    // System.out.println(sameNumberofBitsAsOfN(n, k, 63));
    public static long sameNumberofBitsAsOfN(long n, long k, int i) {
        if (i == 0)
            return 0L;

        long mask = (1L << i); // 1 <= N <= 10^12

        long res = 0L;
        if ((n & mask) == 0)
            res = sameNumberofBitsAsOfN(n, k, i - 1);
        else {
            long res0 = ncr(i, k);
            long res1 = sameNumberofBitsAsOfN(n, k - 1, i - 1);
            res = res0 + res1;
        }

        return res;
    }

    public static void triplet(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int val = arr[i];
            for (int k = i + 1; k < arr.length; k++) {
                val ^= arr[k];
                if (val == 0)
                    count += k - i;
            }
        }

        System.out.println(count);
    }
