/**
 * Maman 14 - Class Ex14
 *
 * @author Noa Amir
 * @version 2022a
 */

public class Ex14 {
    /**
     * Gets one dimension integers array, and 2 integers - different from one another.
     * Returns the minimal distance between the two integers cells in the array.
     * Time complexity: O(2n) = O(n)
     * Place complexity: O(1)
     *
     * @param a - one dimension integers array
     * @param x - integer - different from y
     * @param y - integer - different from x
     *
     * @return  - if both x and y are in array, return minimal distance between the two integers cells. else - return Integer.MAX_VALUE
     */
    public static int findMinDiff(int[] a, int x, int y) {
        int minDistance = a.length - 1;
        int i = 0;
        int j = 1;
        boolean numsExist = false;

        while (j < a.length) {
            if (i == j) {              // i index should "chase" j index, but never be equal. Looking for a distance between 2 different cells.
                if (j == a.length - 1) // i=j=a.length-1, both indexes are on the last cell. Check finished
                    break;
                j++;
            }

            if (((a[i] == x && a[j] == y) || (a[i] == y && a[j] == x)) && a[i] != a[j]) { // i and j cells are ok. one equals to x, and other equals to y
                numsExist = true;                               // both x,y exists in array
                int distance = j - i;                           // find distance between 2 cells
                minDistance = Math.min(minDistance, distance);  // save minimal distance
                i++;                                            // move i index forward
            }
            else if (a[i] == x || a[i] == y) // only i cell is ok. move j cell forward
                j++;
            else if (a[j] == x || a[j] == y) // only j cell is ok. move i cell forward
                i++;
            else {                           // both i and j cells are not equal to x or y. skip them. move both forward
                i++;
                j++;
            }
        }

        if (numsExist)            // if both x and y are in array, return their minimal cells distance
            return minDistance;
        return Integer.MAX_VALUE; // if not - return the maximal integer
    }


    /**
     * Gets two dimensions integers array which is sorted circular, and integer - num.
     * Returns true if num value exists in array, false if not.
     * Time complexity: O(logn)
     * Place complexity: O(1)
     *
     * @param mat - two dimensions integers array which is sorted circular
     * @param num - integer
     *
     * @return true if num value exists in mat, false if not
     */
    public static boolean search(int[][] mat, int num) {
        int i = 0;
        int j = 0;
        int size = mat.length;
        int q1Max; // max number in quarter 1
        int q2Max; // max number in quarter 2
        int q3Max; // max number in quarter 3
        int q4Max; // max number in quarter 4

        if (num < mat[0][0] || num > mat[size - 1][0])
            return false;  // num value is smaller than min value in array or larger than max value in array

        while (i < mat.length && j < mat[i].length) {

            if (mat[i][j] == num)
                return true; // found num

            if (size == 1) {             // the final square - 1x1
                return mat[i][j] == num; // if square value is num - return true. else return false
            }

            // get max value of each quarter
            q1Max = mat[i + (size / 2) - 1][j];
            q2Max = mat[i + (size / 2) - 1][j + size / 2];
            q3Max = mat[i + size - 1][j + size / 2];
            q4Max = mat[i + size - 1][j];

            if (q1Max == num || q2Max == num || q3Max == num || q4Max == num)
                return true;  // one of the qMax is equals to num. found num

            if (size == 2) {
                return false; // if square is 2x2 and the above conditions is false - num is not in square
            }

            // move indexes to the relevant quarter by num value in comparison to each quarter max
            if (num < q1Max) {
                // do nothing. indexes stay the same
            } else if (num < q2Max) {
                // i stay the same
                j = j + size / 2;
            } else if (num < q3Max) {
                i = i + size / 2;
                j = j + size / 2;
            } else if (num < q4Max) {
                i = i + size / 2;
                // j stay the same
            }

            size = size / 2; // quarter size. next loop will look only inside specific quarter
        }
        return false; // num was not found in array
    }


    /**
     * Gets one dimension integers array.
     * Returns true if the array can be split into two different groups as explained:
     * (1) Each group should contain the same number of members,
     * (2) Both groups sum should be the same.
     * If not possible - return false
     *
     * @param arr - two dimensions integers array which is sorted circular
     *
     * @return true if the split is possible by the rules, false if not
     */
    public static boolean equalSplit(int[] arr) {
        final int SPLIT_TO = 2;

        if (arr.length % SPLIT_TO != 0)
            return false;       // array length is not splitting to SPLIT_TO. Can't have SPLIT_TO groups with the same number of members

        int arrSum = sumArray(arr, 0);
        if (arrSum % SPLIT_TO != 0)
            return false;       // array sum is not splitting to SPLIT_TO. Can't have SPLIT_TO groups with the same sum for each

        int groupMembers = arr.length / SPLIT_TO; // each group number of members

        int groupSum = arrSum / SPLIT_TO;         // each group sum

        return equalSplit(arr, 0, groupMembers, groupSum); // check if array has groupMembers members with groupSum summary
    }


    // check if array has num integers with sum summary
    // arr - integers array
    // index - index inside arr
    // num - number of integers in the array to calculate their sum.
    // sum - goal summary of num integers
    // if method finds num integers with sum summary - return true. otherwise, return false
    private static boolean equalSplit(int[] arr, int index, int num, int sum) {
        if (index == arr.length)
            return false;  // got to the end of the array

        if (num < 0)
            return false;  // crossed the desired number of integers. too many

        if (sum == 0 && num == 0)
            return true;   // reached the desired number of integers, with the desired summary

        // check all combinations of num integers and their summary in the array.
        // for each integer - can be calculated in the combination - or not.
        // return true if it finds num integers with sum summary
        return equalSplit(arr, index + 1, num, sum) || equalSplit(arr, index + 1, num - 1, sum - arr[index]);
    }

    // return array sum
    // a - array to summarize
    // i - index to start summarize from
    private static int sumArray(int[] a, int i) {
        if (i == a.length)
            return 0;
        return a[i] + sumArray(a, i + 1);
    }


    /**
     * Gets an integer
     * Returns true if integer is a special number, false if not
     * Special number is TODO: add short definition of special number (positive prime number?)
     *
     * @param n - integer
     *
     * @return true if n is a special number, false if not
     */
    public static boolean isSpecial(int n) {

        return isSpecial(n, 2);
    }

    // currPlace - refers to the current index of n, in an imaginary array of positive integers (>0)
    // for example:
    // n=7: 1,2,3,4,5,6,7..., currPlace is 7
    // n=13: 1,3,7,9,13..., currPlace is 5
    // deletePlace - refers to the indexes in an imaginary array of positive integers (>0) to "delete"
    // for example:
    // deletePlace=2, delete all indexes which are divided into 2 with no
    // input:  1,2,3,4,5,6,7...
    // output: 1,3,5,7...
    // this method will remove all deletePlace multiples indexes, and if currPlace is not removed - it is a special number
    private static boolean isSpecial(int currPlace, int deletePlace) {
        if (currPlace % deletePlace == 0 || currPlace <= 0)
            return false; // currPlace is divided by deletePlace, so it will be removed or currPlace is negative - must be positive by definition

        if (currPlace < deletePlace)
            return true;  // currPlace is smaller than deletePlace - which means it will never be removed. currPlace value is a special number


        // each round:
        // remove all deletePlace indexes up until currPlace - and send new currPlace after removal
        // increase deletePlace by 1
        // if currPlace will never be deleted - it is a special number - return true. otherwise, return false
        return isSpecial(currPlace - currPlace / deletePlace, deletePlace + 1);
    }
}

