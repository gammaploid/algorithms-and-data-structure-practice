public class StringMatcherBuiltin extends StringMatcher {

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

        return target.indexOf(pattern);
    }

    @Override
    public int getNumberOfComparisons() {
        return -1;
    }

}
