package ekzeget.ru.ekzeget.model;

public class ContentString {
    private int mStNo;
    private int mStart;
    private int mEnd;

    public ContentString(int stNo, int start, int end) {
        this.mStNo = stNo;
        this.mStart = start;
        this.mEnd = end;
    }

    public int getStNo() {
        return mStNo;
    }

    public int getStart() {
        return mStart;
    }

    public int getEnd() {
        return mEnd;
    }
}
