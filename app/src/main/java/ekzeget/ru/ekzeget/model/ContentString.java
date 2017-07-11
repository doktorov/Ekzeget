package ekzeget.ru.ekzeget.model;

public class ContentString {
    private int mStNo;
    private int mStart;
    private int mEnd;
    private String mText;

    public ContentString(int stNo, int start, int end, String text) {
        this.mStNo = stNo;
        this.mStart = start;
        this.mEnd = end;
        this.mText = text;
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

    public String getText() {
        return mText;
    }
}
