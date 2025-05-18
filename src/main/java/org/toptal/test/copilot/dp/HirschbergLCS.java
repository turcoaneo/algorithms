package org.toptal.test.copilot.dp;

public class HirschbergLCS {

    // Compute LCS Length with space-efficient DP
    private static int[] lcsLength(String first, String second) {
        int m = first.length(), n = second.length();
        int[] prev = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            int[] curr = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                if (first.charAt(i - 1) == second.charAt(j - 1)) {
                    curr[j] = prev[j - 1] + 1;
                } else {
                    curr[j] = Math.max(prev[j], curr[j - 1]);
                }
            }
            prev = curr;
        }
        return prev;
    }

    // Recursive Hirschberg's Algorithm
    private static String hirschbergRec(String first, String second) {
        if (first.isEmpty() || second.isEmpty()) return "";
        if (first.length() == 1 || second.length() == 1) {
            return second.chars().mapToObj(c -> String.valueOf((char) c))
                    .filter(first::contains)
                    .reduce("", String::concat);
        }

        int m = first.length() / 2;
        String leftFirst = first.substring(0, m);
        int[] l1 = lcsLength(leftFirst, second);
        String rightFirst = first.substring(m);
        int[] l2 = lcsLength(new StringBuilder(rightFirst).reverse().toString(),
                new StringBuilder(second).reverse().toString());

        int split = 0, maxSum = 0;
        for (int j = 0; j <= second.length(); j++) {
            int sum = l1[j] + l2[second.length() - j];
            if (sum > maxSum) {
                maxSum = sum;
                split = j;
            }
        }

        String leftSecond = second.substring(0, split);
        String rightSecond = second.substring(split);
        return hirschbergRec(leftFirst, leftSecond) + hirschbergRec(rightFirst, rightSecond);
    }

    public static String hirschbergLCS(String first, String second) {
        return hirschbergRec(first, second);
    }

    public static void main(String[] args) {
        String first = "GXTXAYB";
        String second = "AGGTAB";
        System.out.println("LCS: " + hirschbergLCS(first, second));


        String X = "AGGTAB";
        String Y = "GXTXAYB";
        System.out.println("LCS: " + hirschbergLCS(X, Y));


        String U = "GENERAATION";
        String V = "RECREEATION";
        System.out.println("LCS: " + hirschbergLCS(U, V));
    }
}