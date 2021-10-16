package cs146F21.hatch.project2;

/**
 * Three different algorithms for computing largest contiguous subarray
 */
public class MaxSumArray {

    /**
     * Kadane's algorithm. O(n) runtime.
     * @param arr The array
     * @return The maximum sum, starting index, and ending index of subarray
     */
    public static MaxSumResult linear(int[] arr) {
        int maxSum = 0;
        int maxTemp = 0;
        int arrive = 0;
        int depart = -1;
        int tempArrive = 0;

        for (int i = 0; i < arr.length; i++) {
            maxTemp += arr[i];
            if (maxTemp < 0) {
                maxTemp = 0;
                arrive = i + 1;
            }

            if (maxTemp > maxSum) {
                maxSum = maxTemp;
                depart = i;
                tempArrive = arrive;
            }
        }
        arrive = tempArrive;

        if(maxSum <= 0) {
            return new MaxSumResult(0, 0, -1);
        }
        return new MaxSumResult(maxSum, arrive, depart);
    }

    /**
     * Divide and conquer algorithm. O(nlogn) runtime.
     * @param arr The array
     * @return The maximum sum, starting index, and ending index of subarray
     */
    public static MaxSumResult divideandconquer(int[] arr) {
        if(arr.length > 0) {
            return maximumSubArray(arr, 0, arr.length - 1);
        } else {
            return new MaxSumResult(0, 0, -1);
        }
    }

    private static MaxSumResult maximumSubArray(int[] arr, int low, int high) {
        if (low == high) {
            return new MaxSumResult(arr[low], low, high);
        } else {
            int mid = (low + high) / 2;

            MaxSumResult lowerSum = maximumSubArray(arr, low, mid);
            MaxSumResult higherSum = maximumSubArray(arr, mid + 1, high);
            MaxSumResult crossSum = crossSum(arr, low, mid, high);

            if (lowerSum.getSum() >= higherSum.getSum() & lowerSum.getSum() >= crossSum.getSum() && lowerSum.getSum() > 0) {
                return lowerSum;
            } else if (higherSum.getSum() >= lowerSum.getSum() & higherSum.getSum() >= crossSum.getSum() && higherSum.getSum() > 0) {
                return higherSum;
            } else if(crossSum.getSum() > 0){
                return crossSum;
            } else {
                return new MaxSumResult(0, 0, -1);
            }
        }
    }

    private static MaxSumResult crossSum(int[] arr, int low, int mid, int high) {
        int maxLeft = 0;
        int maxRight = 0;
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;

        int i = mid;
        for (; i >= low; i--) {
            sum += arr[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        i = mid + 1;
        for (; i <= high; i++) {
            sum += arr[i];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = i;
            }
        }
        return new MaxSumResult(leftSum + rightSum, maxLeft, maxRight);
    }

    /**
     * Brute force approach. O(n^2) runtime.
     * @param arr The array
     * @return The maximum sum, starting index, and ending index of subarray
     */
    public static MaxSumResult quadratic(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int low = -1;
        int high = -1;

        for (int i = 0; i < arr.length - 1; i++) {
            int thisSum = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                thisSum += arr[j];
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                    low = i;
                    high = j;
                }
            }
        }
        if(maxSum <= 0) {
            return new MaxSumResult(0, 0, -1);
        }
        return new MaxSumResult(maxSum, low, high);
    }
}
