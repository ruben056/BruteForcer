package be.rd.messy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruben on 6/11/14.
 */
public class FogTest {

    public static void main(String[] args) {
        /*System.out.println(hash("leepadg"));
        */
        /*System.out.println(hash("abc")); // --> 354535
        long result =  910897038977002L; // challenge
        long result1 = 354535; // test (abc)
        bruteForce(result1, 3, getAllSmallCaseLetters());
        long result2 = hash("rd");
        bruteForce(result2, 2, getAllSmallCaseLetters());
        bruteForce(910897038977002L, 9, getAllSmallCaseLetters());*/

        bruteForce(680131659347L, 7, new char[]{'a', 'c', 'd', 'e', 'g', 'i', 'l', 'm', 'n', 'o', 'p', 'r', 's', 't', 'u', 'w'}); // --> this was example solution :leepadg

        bruteForce(910897038977002L, 9, new char[]{'a', 'c', 'd', 'e', 'g', 'i', 'l', 'm', 'n', 'o', 'p', 'r', 's', 't', 'u', 'w'}); // --> this was the question solution :asparagus


    }

    private static void printChars() {
        char[] c = new char[9];
        for (int i = 0; i < 127; i++) {
            System.out.println(i + " : " + (char) i);
        }
    }

    /**
     * prints all possible results (can be multiple: see hash code collision)
     * (possibilities are Math.pow(possibleChars.length, wordLength);)
     * <p/>
     * Issue with memore ...
     * Size of wordlist: approx 9 * 2 * 68719476736 is ... a lot ... cannot even write it to disk...
     * Need another approach ...
     *
     * @param result
     */
    private static void bruteForce(long result, int length, char[] possibleChars) {
        char[] wordChars = new char[length];
        bruteForce(possibleChars, wordChars, 0, result);
    }

    private static List<String> generateWords(char[] charRow, int len) {
        List<String> results = new ArrayList<String>();
        if (len == 0) {
            results.add("");
            return results;
        }

        List<String> intermediateResults = generateWords(charRow, len - 1);
        for (String partialString : intermediateResults) {
            for (char curChar : charRow) {
                results.add(curChar + partialString);
            }
        }

        return results;
    }

    private static char[] getAllSmallCaseLetters() {
        char[] letters = new char[26];
        int offset = 97;
        for (int idx = 0; idx < 26; idx++) {
            letters[idx] = (char) (idx + offset);
        }
        return letters;
    }

    public static long hash(String s) {
        long[] hArr = new long[s.length()];
        long[] indexOfArr = new long[s.length()];
        long h = 7;
        String letters = "acdegilmnoprstuw";
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            h = (h * 37 + letters.indexOf(curChar));

        }
        return h;
    }


    private static void bruteForce(char[] possibleChars, char[] wordChars, int idx, long result) {
        if (idx == wordChars.length) {
            String s = new String(wordChars);
            if (result == hash(s)) {
                System.out.println("solution :" + s); // don't quit here, can have multiple solutions
            }
            return;
        }

        for (int i = 0; i < possibleChars.length; i++) {
            wordChars[idx] = possibleChars[i];
            bruteForce(possibleChars, wordChars, idx + 1, result);
        }
    }

}
