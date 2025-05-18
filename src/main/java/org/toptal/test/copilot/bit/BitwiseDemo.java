package org.toptal.test.copilot.bit;

public class BitwiseDemo {
    public static boolean isEven(int num) {
        return (num & 1) == 0; // Returns true if last bit is 0 (even number)
    }

    public static boolean isOdd(int num) {
        return (num & 1) == 1; // Returns true if last bit is 1 (odd number)
    }
    public static void swap(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("Temporarily swapped: a=" + a + ", b=" + b);
    }
    public static int findUnique(int[] arr) {
        int unique = 0;
        for (int num : arr) {
            unique ^= num;
        }
        return unique;
    }
    public static int findUniqueBitMask(int[] arr) {
        int[] bitCounts = new int[32]; // Tracking bit frequency across numbers

        // Count bit occurrences across all numbers
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                if ((num & (1 << i)) != 0) { // Check if bit at position i is set
                    bitCounts[i]++;
                }
            }
        }

        int unique = 0;
        for (int i = 0; i < 32; i++) {
            if (bitCounts[i] % 3 != 0) { // If bits appear NON-multiples of 3, they belong to the unique number
                unique |= (1 << i);
            }
        }

        return unique;
    }
    public static int multiplyByPowerOfTwo(int x, int power) {
        return x << power;  // Multiplies x by 2^power
    }
    public static int divideByPowerOfTwo(int x, int power) {
        return x >> power;  // Divides x by 2^power
    }
    public static int fastExponentiation(int x, int n) {
        int result = 1;

        while (n > 0) {
            if ((n & 1) == 1) { // If lowest bit is 1, multiply result by x
                result *= x;
            }
            x *= x;  // Square x for the next bit
            n >>= 1; // Shift n right (divide by 2)
        }

        return result;
    }public static int modularExponentiation(int base, int exp, int mod) {
        int result = 1;
        base %= mod; // Ensure base is within modulo range

        while (exp > 0) {
            if ((exp & 1) == 1) { // If lowest bit is 1, multiply result by base
                result = (result * base) % mod;
                System.out.println("Intermediate result: " + result);
            }
            base = (base * base) % mod; // Square base under modulo
            exp >>= 1; // Bitwise right shift (dividing exp by 2)
        }

        return result;
    }

    public static void main(String[] args) {
        int a = 6;  // Binary: 0110
        int b = 3;  // Binary: 0011

        System.out.println("AND (6 & 3): " + (a & b));   // 0010 → 2
        System.out.println("OR (6 | 3): " + (a | b));    // 0111 → 7
        System.out.println("XOR (6 ^ 3): " + (a ^ b));   // 0101 → 5
        System.out.println("NOT (~6): " + (~a));        // Inverts bits (-7 in signed integer)

        System.out.println("Left Shift (6 << 1): " + (a << 1));   // 1100 → 12
        System.out.println("Right Shift (6 >> 1): " + (a >> 1));  // 0011 → 3

        System.out.println("Returns true if last bit is 1 (odd number) (6 & 1): " + isOdd(a));
        System.out.println("Returns true if last bit is 0 (odd number) (3 & 1): " + isEven(b));
        swap(a, b);
        System.out.println("a=" + a + ", b=" + b);
        System.out.println();

        int unique = findUnique(new int[]{2, 2, 3, 4, 4, 5, 5, 5, 5});
        System.out.println("Unique: " + unique);

        int uniqueBitMask = findUniqueBitMask(new int[]{2, 2, 3, 4, 4, 5, 5, 5, 5, 7, 8});
        System.out.println("Unique Bit Mask: " + uniqueBitMask);
        System.out.println();

        System.out.println("6 × 2 = " + multiplyByPowerOfTwo(6, 1));  // 12
        System.out.println("3 × 8 = " + multiplyByPowerOfTwo(3, 3));  // 24

        System.out.println("6 : 2 = " + divideByPowerOfTwo(6, 1));  // 3
        System.out.println("100 : 4 = " + divideByPowerOfTwo(100, 2));  // 25

        System.out.println("3^16 = " + fastExponentiation(3, 16));  // 43,046,721
        System.out.println("2^10 % 7 = " + modularExponentiation(2, 10, 7)); // 4
        System.out.println();

    }
}
