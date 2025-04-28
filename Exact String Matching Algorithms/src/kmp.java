public class kmp extends StringMatcher {

    private String target;
    private String pattern;
    private int[] lps;
    private int comparisonCount;

    @Override
    public void setTarget(String t) {
        // case‑insensitive
        this.target = t.toLowerCase();
    }

    @Override
    public void setPattern(String p) {
        // case‑insensitive
        this.pattern = p.toLowerCase();

        // 1) build LPS array
        int M = pattern.length();
        lps = new int[M];
        lps[0] = 0;
        int len = 0;
        int i = 1;
        while (i < M) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }
// algo code credits: https://favtutor.com/blogs/kmp-algorithm + debugged by chatgpt

    @Override
    public int match() {
        int M = pattern.length();
        int N = target.length();
        comparisonCount = 0;

        int i = 0;  // index in target
        int j = 0;  // index in pattern

        // 2) scan through target
        while (i < N) {
            // count the single comparison between pattern[j] and target[i]
            comparisonCount++;
            if (pattern.charAt(j) == target.charAt(i)) {
                i++;
                j++;
                // full match?
                if (j == M) {
                    return i - j;
                }
            } else {
                // mismatch -> fall back in pattern or advance in text
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        // no match found
        return -1;
    }

    @Override
    public int getNumberOfComparisons() {
        return comparisonCount;
    }
}
