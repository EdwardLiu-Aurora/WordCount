package bean;

// 记录详细行数的类
public class LineCount {
    // 空行
    int blankLineCount = 0;
    // 代码行
    int codeLineCount = 0;
    // 注释行
    int commentLineCount = 0;

    public LineCount(int blankLineCount, int codeLineCount, int commentLineCount) {
        this.blankLineCount = blankLineCount;
        this.codeLineCount = codeLineCount;
        this.commentLineCount = commentLineCount;
    }

    public int getBlankLineCount() {
        return blankLineCount;
    }

    public void setBlankLineCount(int blankLineCount) {
        this.blankLineCount = blankLineCount;
    }

    public int getCodeLineCount() {
        return codeLineCount;
    }

    public void setCodeLineCount(int codeLineCount) {
        this.codeLineCount = codeLineCount;
    }

    public int getCommentLineCount() {
        return commentLineCount;
    }

    public void setCommentLineCount(int commentLineCount) {
        this.commentLineCount = commentLineCount;
    }
}
