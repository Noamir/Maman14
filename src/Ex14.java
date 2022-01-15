/**
 * Maman 14 - Class Ex14
 *
 * @author Noa Amir
 * @version 2022a
 */

public class Ex14 {
    /**
     * Find the minimal cells distance between two given integers in a given array
     * Time complexity: O(2n) = O(n) - go over <a> array with 2 indexes. Each index "worst case" is n
     * Space complexity: O(1) - constant number of variables allocation. Refer it as 1
     *
     * @param a - integers array
     * @param x - integer - different from y
     * @param y - integer - different from x
     * @return - If both x and y are in array, return the minimal cells distance between them. Else - return Integer.MAX_VALUE
     */
    public static int findMinDiff(int[] a, int x, int y) {
        int minDistance = Integer.MAX_VALUE;
        int i = 0;
        int j = 1;

        while (j < a.length) {
            if (i == j) {
                if (j == a.length - 1)
                    break;              // i=j=a.length-1, both indexes are on the last cell. Check finished
                j++;                    // i index should "chase" j index, but never be equal. Looking for a distance between 2 different cells.
            }
            if (((a[i] == x && a[j] == y) || (a[i] == y && a[j] == x))) {   // i and j cells are ok. one equals to x, other equals to y
                minDistance = Math.min(minDistance, j - i);                 // save minimal distance
                i++;                                                        // move i index forward
            } else if (a[i] == x || a[i] == y)                              // only i cell is ok. move j cell forward
                j++;
            else if (a[j] == x || a[j] == y)                                // only j cell is ok. move i cell forward
                i++;
            else {                                                          // both i and j cells are not equal to x or y. move both forward
                i++;
                j++;
            }
        }
        return minDistance;
    }


    /**
     * Search number in a sorted circular two dimensions integers array
     * Time complexity: O(log n) - each iteration divides <mat> size in RATIO
     * Space complexity: O(1) - constant number of variables allocation. Refer it as 1
     *
     * @param mat - two dimensions integers array which is sorted circular
     * @param num - integer to search in mat
     * @return true if num value exists in mat, false if not
     */
    public static boolean search(int[][] mat, int num) {
        final int RATIO = 2;            // ratio of quarter side size to matrix side size

        int i = 0, j = 0;               // row and col, respectively
        int size = mat.length;          // define the limits of the matrix in a given moment
        int q1Max, q2Max, q3Max, q4Max; // max number in each quarter

        // num value is smaller than min value in array or larger than max value in array
        // optimization for worst case scenario - num equals to mat[0][0]
        if (num <= mat[0][0] || num > mat[size - 1][0])
            return num == mat[0][0];

        while (true) {
            if (mat[i][j] == num)
                return true;                // found num

            if (size == 1)                  // the final square - 1x1
                return mat[i][j] == num;    // if square value is num - return true. else return false

            // get max value of each quarter
            q1Max = mat[i + (size / RATIO) - 1][j];
            q2Max = mat[i + (size / RATIO) - 1][j + size / RATIO];
            q3Max = mat[i + size - 1][j + size / RATIO];
            q4Max = mat[i + size - 1][j];

            if (q1Max == num || q2Max == num || q3Max == num || q4Max == num)
                return true;            // one of the qMax is equals to num. found num

            if (num > q3Max) {          // move indexes to quarter 4
                i = i + size / RATIO;
            } else if (num > q2Max) {   // move indexes to quarter 3
                i = i + size / RATIO;
                j = j + size / RATIO;
            } else if (num > q1Max) {   // move indexes to quarter 2
                j = j + size / RATIO;
            }

            size = size / RATIO;     // change the limits of the matrix to a specific quarter
        }
    }


    /**
     * Check if a given array can be split into equal groups as explained:
     * (1) Both group should be in the same size
     * (2) Both groups should have the same sum
     *
     * @param arr - integers array
     * @return true if the split as explained is possible, false if not
     */
    public static boolean equalSplit(int[] arr) {
        final int SPLIT_TO = 2;     // number of groups to split to

        if (arr.length % SPLIT_TO != 0)
            return false;           // array length is not splitting to SPLIT_TO. Can't have SPLIT_TO groups with the same size

        int arrSum = sumArray(arr, 0);
        if (arrSum % SPLIT_TO != 0)
            return false;           // array sum is not splitting to SPLIT_TO. Can't have SPLIT_TO groups with the same sum

        int groupSize = arr.length / SPLIT_TO;    // each group size

        int groupSum = arrSum / SPLIT_TO;         // each group sum

        return equalSplit(arr, 0, groupSize, groupSum); // check if array has groupSize members with groupSum summary
    }

    // returns if there is <num> number of cells, in <arr> array, starting from <index> index, with <sum> summary
    private static boolean equalSplit(int[] arr, int index, int num, int sum) {
        if (index == arr.length)
            return false;       // got to the end of the array

        if (num == 0)
            return sum == 0;    // reached the desired number of integers, return if the summary is ok

        // chek all combinations: cell may be present or not in a group
        return equalSplit(arr, index + 1, num, sum) || equalSplit(arr, index + 1, num - 1, sum - arr[index]);
    }

    // returns <a> array sum from <i> index
    private static int sumArray(int[] a, int i) {
        if (i == a.length)
            return 0;
        return a[i] + sumArray(a, i + 1);
    }


    /**
     * Check if a given number is special
     *
     * @param n - integer to check if special
     * @return true if n is a special number, false if not
     */
    public static boolean isSpecial(int n) {
        if (n <= 0)
            return false;   // special number must be positive

        return isSpecial(n, 2);
    }

    // this method ignores positive numbers in all deletePlace multiples positions, and change other numbers positions accordingly
    private static boolean isSpecial(int currPlace, int deletePlace) {
        if (currPlace % deletePlace == 0)
            return false; // currPlace is a multiple of deletePlace, so it will be ignored. Not a special number

        if (currPlace < deletePlace)
            return true;  // currPlace is smaller than deletePlace - it will never be ignored. currPlace is a special number

        return isSpecial(currPlace - currPlace / deletePlace, deletePlace + 1);
    }
}

