package ekzeget.ru.ekzeget.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ekzeget.ru.ekzeget.ui.fragment.TalksPoemTextFragment;

public class TalksPoemTextAdapter extends FragmentPagerAdapter {
    private String mBookName;
    private String mBookKey;
    private int mBookParts;
    private String mBookPoem;
    private String mBookChapter;

    public TalksPoemTextAdapter(FragmentManager fm, String bookKey, String bookChapter,
                                       int bookParts, String bookName, String bookPoem) {
        super(fm);

        mBookName = bookName;
        mBookKey = bookKey;
        mBookChapter = bookChapter;
        mBookParts = bookParts;
        mBookPoem = bookPoem;
    }

    @Override
    public Fragment getItem(int position) {
        return TalksPoemTextFragment.newInstance(mBookKey, mBookChapter,
                String.valueOf(position + 1), mBookPoem);
    }

    @Override
    public int getCount() {
        return mBookParts;
    }
}
