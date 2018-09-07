package bean;

/*
记录总字符数和中文字符数的类
 */
public class CharCount {
    // 全体字符数目
    long allCharCount = 0;
    // 中文字符数目
    long chnCharCount = 0;

    public CharCount(long allCharCount, long chnCharCount) {
        this.allCharCount = allCharCount;
        this.chnCharCount = chnCharCount;
    }

    public long getAllCharCount() {
        return allCharCount;
    }

    public void setAllCharCount(long allCharCount) {
        this.allCharCount = allCharCount;
    }

    public long getChnCharCount() {
        return chnCharCount;
    }

    public void setChnCharCount(long chnCharCount) {
        this.chnCharCount = chnCharCount;
    }
}