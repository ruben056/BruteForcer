package be.rd.solution;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruben on 6/11/14.
 */
public class BruteForcer {

    interface IHash {
        long hash(String s);
    }


    public static void main(String[] args) {

        BruteForcer bf = new BruteForcer(new IHash() {
            @Override
            public long hash(String s) {
                long h = 7;
                String letters = "acdegilmnoprstuw";
                for (int i = 0; i < s.length(); i++) {
                    char curChar = s.charAt(i);
                    h = (h * 37 + letters.indexOf(curChar));

                }
                return h;
            }
        });

        bf.setPossibleChars(new char[]{'a', 'c', 'd', 'e', 'g', 'i', 'l', 'm', 'n', 'o', 'p', 'r', 's', 't', 'u', 'w'});

        bf.setResult(680131659347L);// --> sample : leepadg
        bf.setLength(7);
        bf.start();

        bf.setResult(910897038977002L); // --> solution: asparagus, or as we Flemish call it the white gold :)
        bf.setLength(9);
        bf.start();
    }




    private IHash hashAlgorithm;
    private long result;
    private char[] possibleChars;
    private int length;
    private List<String> solutions;

    public BruteForcer(IHash hashAlgorithm) {
        this.hashAlgorithm = hashAlgorithm;
    }

    public void setResult(long result) {
        this.result = result;
    }

    public void setPossibleChars(char[] possibleChars) {
        this.possibleChars = possibleChars;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<String> getSolutions() {
        return solutions;
    }

    public void start()
    {
        solutions = new ArrayList<String>();
        generateCombinations(new char[length], 0);
    }

    private void generateCombinations(char[] chars, int idx) {
        if (idx == chars.length) {
            String s = new String(chars);
            if (result == hashAlgorithm.hash(s)) {
                System.out.println("solution :" + s);
                solutions.add(s);// don't quit here, can have multiple solutions
            }
            return;
        }

        for (int i = 0; i < possibleChars.length; i++) {
            chars[idx] = possibleChars[i];
            generateCombinations(chars, idx + 1);
        }
    }
}
