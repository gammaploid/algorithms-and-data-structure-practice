import java.util.Arrays;

public class bm extends StringMatcher {

    private String target;
    private String pattern;
    private int comparisonCount;
    private static final int NO_OF_CHARS = 256;

    @Override
    public void setTarget(String t) {
        // normalize to lowercase
        this.target = t.toLowerCase();
    }

    @Override
    public void setPattern(String p) {
        // normalize to lowercase
        this.pattern = p.toLowerCase();
    }


   // algo credit: https://notes.iamdev.in/simple-boyer-moore-algorithm-in-java-a-step-by-step-guide/ + chatgpt for debugging
    @Override
    public int match() {
        int n = target.length();
        int m = pattern.length();
        comparisonCount = 0;

        // build bad-char table
        int[] badChar = new int[NO_OF_CHARS];
        Arrays.fill(badChar, -1);
        for (int i = 0; i < m; i++) {
            badChar[(int)pattern.charAt(i)] = i;
        }

        // slide over text
        int s = 0;
        while (s <= n - m) {
            int j = m - 1;

            // compare right->left, count every test
            while (j >= 0) {
                comparisonCount++;
                if (pattern.charAt(j) == target.charAt(s + j)) {
                    j--;
                } else {
                    break;
                }
            }

            // full match?
            if (j < 0) {
                return s;
            }

            // bad-character shift
            int badIndex = (int)target.charAt(s + j);
            int shift = j - badChar[badIndex];
            s += Math.max(1, shift);
        }

        return -1;
    }

    @Override
    public int getNumberOfComparisons() {
        return comparisonCount;
    }
}
