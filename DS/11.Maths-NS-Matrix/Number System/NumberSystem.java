public class NumberSystem {
    
    // 504
    public String convertToBase7(int num) {
        int remainder = 0, convertedNumber = 0, i = 0;
        int sign = num < 0 ? -1 : 1;
        int n = Math.abs(num);
        while (n > 0) {
            remainder = n % 7;
            convertedNumber += remainder * ((int) Math.pow(10, i));
            i++;
            n = n / 7;
        }
        convertedNumber *= sign;
        return String.valueOf(convertedNumber);
    }

    public static int decimalToAnyBase(int n, int b) {
        // int quotient=0;
        int remainder = 0, convertedNumber = 0, i = 0;
        while (n > 0) {
            // quotient=n/b;
            remainder = n % b;
            convertedNumber += remainder * ((int) Math.pow(10, i));
            i++;
            n = n / b; // ya n=quotient; ye statement remainder niklane ke baad
        }
        return convertedNumber;
    }

    public static int getValueIndecimal(int n, int b) {
        int remainder = 0, convertedNumber = 0, i = 0;
        while (n > 0) {
            remainder = n % 10;
            convertedNumber += remainder * ((int) Math.pow(b, i));
            i++;
            n = n / 10;
        }
        return convertedNumber;
    }

    public static int anyBaseToanyBase(int n, int b1, int b2) {
        int decimalNumber = getValueIndecimal(n, b1);
        return decimalToAnyBase(decimalNumber, b2);
    }

    //add binary string-cpp

    public static int getSum(int b, int n1, int n2) {
        int carry = 0, sum = 0, p = 1;
        while (n1 > 0 || n2 > 0 || carry > 0) {
            int d1 = n1 % 10;
            int d2 = n2 % 10; 

            int num=d1 + d2 + carry;
            sum += ( num % b) * p;
            p = p * 10;
            carry=num/b;

            n1 = n1 / 10;
            n2 = n2 / 10;
        }

        return sum;
    }

    //multiply strings-cpp

    public static int getProduct(int b, int n1, int n2) {
        int p = 1, sum = 0;
        while (n2 > 0) {
            int digit = n2 % 10;
            int product = getProductfor1Number(b, digit, n1) * p;
            sum = getSum(b, sum, product);
            p = p * 10;
            n2 = n2 / 10;
        }
        return sum;
    }

    public static int getProductfor1Number(int b, int digit, int n1) {
        int sum = 0, carry = 0, p = 1;
        while (n1 > 0 || carry > 0) {
            int rem = n1 % 10;
            n1 = n1 / 10;
            int num = ((rem * digit) + carry);
            sum += (num % b) * p;
            carry = num / b;
            p = p * 10;
        }
        return sum;
    }

    public static int getDifference(int b, int n1, int n2) {
        int difference = 0, carry = 0, p = 1;
        while (n2 > 0) {
            int d1 = n1 % 10;
            int d2 = n2 % 10;
            d2 = d2 + carry;
            if (d2 < d1) {
                d2 = d2 + b;
                carry = -1;
            } else
                carry = 0;
            difference += (d2 - d1) * p;
            p = p * 10;
            n1 = n1 / 10;
            n2 = n2 / 10;
        }
        return difference;
    }

}
