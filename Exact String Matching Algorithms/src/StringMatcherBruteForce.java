public class StringMatcherBruteForce extends StringMatcher {
String target;
String pattern;
int comparisonCount = 0;

    @Override
    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }


    @Override
    public int match() {
        comparisonCount = 0;
        int m = pattern.length();
        int n = target.length();
        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            while (j < m) {
                comparisonCount++;
                if (Character.toLowerCase(target.charAt(i + j)) != Character.toLowerCase(pattern.charAt(j))) {
                    break;
                }
                j++;
            }
            if (j == m) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getNumberOfComparisons() {
            return comparisonCount;
        }
    }
