package com.company.ds;

import java.util.Arrays;

public class ArrayDS {

    // given an arry, return array where each
    // index stores the product of all numbers
    // except itself
    public static int[] products(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];

        int prod = 1;
        for (int i = 0; i < n; i++) {
            result[i] = prod;
            prod *= arr[i];
        }

        prod = 1;
        for (int i = n - 1; i > -1; i--) {
            result[i] *= prod;
            prod *= arr[i];
        }

        return result;

    }

    public static int findFirstUnique(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean isRepeated = false;
            for (int j = 0; j < arr.length; j++) {
                if (i != j && arr[i] == arr[j]) {
                    isRepeated = true;
                }
            }
            if (!isRepeated) {
                return arr[i];
            }
        }
        return -1;

    }

    /**
     * Right rotate array by one index
     * ex: 1, 2, 3, 4, 5 -> 5, 1, 2, 3, 4
     * @param arr
     */
    public static void rotateArray(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
        }
    }

    /**
     * such that all the negative elements appear at the left and positive elements appear at the right.
     * ex. 5 -4 -2 -1 3 2 --> -4 -2 1 2 3
     * 0 is positive, order does not matter
     * @param arr
     */
    public static void reArrange(int[] arr) {
        int n = arr.length;
        int lastNegativeIdx = n -1;
        for (int i = 0; i < n; i++) {
            if (arr[i] >= 0) {
                while (lastNegativeIdx > i) {
                    if (arr[lastNegativeIdx] < 0) {
                        break;
                    }
                    lastNegativeIdx--;
                }
                if (lastNegativeIdx == i) {
                    break;
                }
                int temp = arr[lastNegativeIdx];
                arr[lastNegativeIdx] = arr[i];
                arr[i] = temp;
                lastNegativeIdx--;
            }
        }

    }

    public static int maxSumSubarray1(int[] arr) {
        int n = arr.length;
        int currMaxS = - Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        for (int i = 0; i < n; i++) {
            int s = 0;
            for (int j = i; j < n; j++) {
                s += arr[j];
                if (s > currMaxS) {
                    currMaxS = s;
                    start = i;
                    end = j;
                }
            }
        }
        int[] subArray = new int[end + 1 - start];
        for (int i = 0; i < subArray.length; i++) {
            subArray[i] = arr[start + i];
        }
        System.out.println("Subarray: " + Arrays.toString(subArray));
        return currMaxS;
    }

    public static int maxSumSubarray2(int[] arr) {
        int currentSum = arr[0];
        int maxSum = currentSum;
        for (int i = 1; i < arr.length; i++) {
            if (currentSum + arr[i] > arr[i]) {
                currentSum += arr[i];
            } else {
                currentSum = arr[i];
            }

            //currentSum = Math.max(currentSum + arr[i], arr[i]);

            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
            // maxSum = Math.max(currentSum, maxSum);
        }
        return maxSum;
    }



}
