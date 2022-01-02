public class Ex14 {

    // Q1
    public static int findMinDiff(int[] a, int x, int y) {
        int minDistance = a.length;
        int i = 0;
        int j = 1;
        boolean numsExist = false;

        while (j < a.length) { // we're changing indexes by a condition, so can't use for! גמישות - משתמשים ב-while
            if (i == j) { // if i got to j - not good. we want them to be different
                if (j == a.length - 1) // i=j=the end of the array, nothing to check anymore! bye
                    break;
                j++;
            }

            if (((a[i] == x && a[j] == y) || (a[i] == y && a[j] == x)) && a[i] != a[j]) {
                numsExist = true;
                int distance = j - i;
                minDistance = Math.min(minDistance, distance);
                i++;
            } else if (a[i] == x || a[i] == y)
                j++;

            else if (a[j] == x || a[j] == y)
                i++;
            else {// both a[i] and a[j] are not equal to x or y, so it's not interesting! let's skip them
                i++;
                j++;
            }
        }

        if (numsExist)
            return minDistance;
        return Integer.MAX_VALUE;
    }

    // time = O(2n) = O(n)
    // place = O(1)


    // Q2
    public static boolean search(int[][] mat, int num) {
        int i = 0;
        int j = 0;
        int size = mat.length;  // 4
        int q1Max; // max number in quarter 1
        int q2Max; // max number in quarter 2
        int q3Max; // max number in quarter 3
        int q4Max; // max number in quarter 4

        if (num < mat[0][0] || num > mat[size - 1][0])
            return false; // num value in smaller than min or larger than max value in array

        while (i < mat.length && j < mat[i].length) { // as long as we are in array range

            if (mat[i][j] == num)
                return true; // found the number. bye

            if (size == 1) { // we made it to the final square.
                return mat[i][j] == num;
            }

            // max number in each quarter
            q1Max = mat[i + (size / 2) - 1][j];
            q2Max = mat[i + (size / 2) - 1][j + size / 2];
            q3Max = mat[i + size - 1][j + size / 2];
            q4Max = mat[i + size - 1][j];

            if (q1Max == num || q2Max == num || q3Max == num || q4Max == num)
                return true; // one of our Max's is equals to num

            if (size == 2) {  // square is 2x2 , if the above conditions is false - return false. num is not in square
                return false;
            }

            if (num < q1Max) {        // move indexes to q1. Search only there
                // do nothing! indexes stay the same
            } else if (num < q2Max) { // move indexes to q2. Search only there
                // i stay the same
                j = j + size / 2;
            } else if (num < q3Max) { // move indexes to q3. Search only there
                i = i + size / 2;
                j = j + size / 2;
            } else if (num < q4Max) { // move indexes to q4. Search only there
                i = i + size / 2;
                // j stay the same
            }

            size = size / 2; // quarter size
        }
        return false; // needed?
    }


// time = O(log n)
// place = O(1)


    // Q3
    public static boolean equalSplit(int[] arr) {
        if (arr.length % 2 != 0) // array length is not zugi. bye
            return false;

        int groupMembers = arr.length / 2; // each group number of members

        int groupSum = sumArray(arr, 0) / 2; // each group sum

        return equalSplit(arr, 0, groupMembers, groupSum); // need to find groupMembers which together are groupSum
    }

    private static boolean equalSplit(int[] arr, int index, int num, int sum) {
        if (index == arr.length)
            return false;

        if (sum < 0 || num < 0)
            return false;

        if (sum == 0 && num == 0)
            return true;

        return equalSplit(arr, index + 1, num, sum) || equalSplit(arr, index + 1, num - 1, sum - arr[index]);
    }


    private static int sumArray(int[] a, int i) {
        if (i == a.length)
            return 0;
        return a[i] + sumArray(a, i + 1);
    }
}

