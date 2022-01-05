/**
 * Harel Erlich
 * 15/12/2021
 */
public class equalSplitTesterHarel extends Ex14 {
    public static void main(String[] args) {
        System.out.println("**equalSplit test**\n");
        int[] array1 = new int[]{-3, 5, 12, 14, -9, 13};
        System.out.println("correct:\ntrue");
        System.out.println("Your answer:");
        System.out.println(equalSplit(array1) + "\n");

        int[] array2 = new int[]{1, 2, 3, 5, 8, -1, 2, 0};
        System.out.println("correct:\ntrue");
        System.out.println("Your answer:");
        System.out.println(equalSplit(array2) + "\n");

        int[] array3 = new int[]{-5, 7, 9, 5, 8, 4, -12, 0, -7, -7};
        System.out.println("correct:\ntrue");
        System.out.println("Your answer:");
        System.out.println(equalSplit(array3) + "\n");


        int[] array4 = new int[]{1, 2, 3, 3, 2, 1};
        System.out.println("correct:\ntrue");
        System.out.println("Your answer:");
        System.out.println(equalSplit(array4) + "\n");


        int[] array5 = new int[]{-2, 3, 4, 5, 9, 8};
        System.out.println("correct:\nfalse");
        System.out.println("Your answer:");
        System.out.println(equalSplit(array5) + "\n");


        int[] array6 = new int[]{-5, 7, 9, 5, 8};
        System.out.println("correct:\nfalse");
        System.out.println("Your answer:");
        System.out.println(equalSplit(array6) + "\n");
    }
}

