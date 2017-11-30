package ekzeget.ru.ekzeget.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import ekzeget.ru.ekzeget.R;

public class ContentPoemTextPageFragment extends Fragment {
    public static final String BOOK_KEY = "book_key";
    public static final String BOOK_CHAPTER = "book_chapter";
    public static final String BOOK_ST_NO = "book_st_no";
    public static final String BOOK_POEM = "book_poem";

    private static String mBookKey;
    private static String mBookChapter;
    private static String mBookStNo;
    private static String mBookPoem;

    private TextView mContextText;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    public static ContentPoemTextPageFragment newInstance(String bookKey, String bookChapter,
                                                      String bookStNo, String bookPoem) {
        ContentPoemTextPageFragment f = new ContentPoemTextPageFragment();

        Bundle args = new Bundle();
        args.putString(BOOK_KEY, bookKey);
        args.putString(BOOK_CHAPTER, bookChapter);
        args.putString(BOOK_ST_NO, bookStNo);
        args.putString(BOOK_POEM, bookPoem);
        f.setArguments(args);

        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout ll = (LinearLayout) inflater.inflate(
                R.layout.fragment_content_poem_text_page, container, false);

        mBookKey = getArguments().getString(BOOK_KEY);
        mBookChapter = getArguments().getString(BOOK_CHAPTER);
        mBookStNo = getArguments().getString(BOOK_ST_NO);
        mBookPoem = getArguments().getString(BOOK_POEM);

        mContextText = ll.findViewById(R.id.context_text);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager(),
                mBookKey, mBookChapter, mBookPoem);

        mViewPager = ll.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(Integer.parseInt(mBookChapter) - 1);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //mToolbar.setTitle(String.format("%s %s", mBookChapterAuthor, String.valueOf(position + 1)));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        return ll;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Nullable
    private String getArgumentBookKey() {
        final Bundle bundle = getArguments();
        if (bundle == null) return null;

        return bundle.getString(BOOK_KEY);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private String mBookKey;
        private String mBookChapter;
        private String mBookPoem;

        private SectionsPagerAdapter(FragmentManager fm, String bookKey,
                                     String bookChapter, String bookPoem) {
            super(fm);

            mBookKey = bookKey;
            mBookChapter = bookChapter;
            mBookPoem = bookPoem;
        }

        @Override
        public Fragment getItem(int position) {
//            return ContentPoemTextFragment.newInstance(mBookKey, mBookName,
//                    String.valueOf(position + 1));

            return ContentPoemTextFragment.newInstance(mBookKey, mBookChapter, String.valueOf(position + 1), mBookPoem);
        }

        @Override
        public int getCount() {
            return 10;// mBookParts;
        }
    }
}
