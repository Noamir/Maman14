
/**
 *  Harel Erlich
 *  15/12/2021
 */
public class isSpecialTesterHarel extends Ex14
{
    public static void main (String [] args) {
        System.out.println ("**isSpecial test**");
        System.out.println ("check all speciel numbers between 1-1010\n");
        String str1 = "";
        for (int i = 1; i < 1010; i++) {
            if (isSpecial(i) == true) {
                str1 +=  (i + "\n");
            }
        }
        String str2 = "1\n3\n7\n13\n19\n27\n39\n49\n63\n79\n91\n109\n133\n147\n181\n207\n223\n253\n289\n307\n349\n387\n399\n459\n481\n529\n" +
                "567\n613\n649\n709\n763\n807\n843\n927\n949\n1009\n";

        if (str1.equals(str2)) {
            System.out.println ("OK\n");
        }
        else {
            System.out.println ("ERROR\n");
        }
        System.out.println ("**check negative numbers**\n");
        System.out.println("number: -39");
        System.out.println("correct:\nfalse");
        System.out.println ("Your answer:");
        System.out.println(isSpecial(-39) + "\n");


    }
}

