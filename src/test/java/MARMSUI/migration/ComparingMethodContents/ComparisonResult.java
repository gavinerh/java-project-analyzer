package MARMSUI.migration.ComparingMethodContents;

public class ComparisonResult {
    private String content;
    private int lastIndex;

    public ComparisonResult(String content, int lastIndex) {
        this.content = content;
        this.lastIndex = lastIndex;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }
}
