package ekzeget.ru.ekzeget.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import ekzeget.ru.ekzeget.R;
import ekzeget.ru.ekzeget.ui.fragment.ContextTextPagerFragment;

public class ContextTextActivity  extends AppCompatActivity {
    public static final String BOOK_NAME = "book_name";
    public static final String BOOK_KEY = "book_key";
    public static final String BOOK_PARTS = "book_parts";
    public static final String BOOK_CHAPTER = "book_chapter";
    public static final String BOOK_CHAPTER_AUTHOR = "book_chapter_author";

    private static String mBookName;
    private static String mBookKey;
    private static int mBookParts;
    private static String mBookChapter;
    private static String mBookChapterAuthor;

    private Toolbar mToolbar;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_context_text);

        mBookName = getIntent().getStringExtra(BOOK_NAME);
        mBookKey = getIntent().getStringExtra(BOOK_KEY);
        mBookParts = getIntent().getIntExtra(BOOK_PARTS, 0);
        mBookChapter = getIntent().getStringExtra(BOOK_CHAPTER);
        mBookChapterAuthor = getIntent().getStringExtra(BOOK_CHAPTER_AUTHOR);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToolbar.setTitle(String.format("%s %s", mBookChapterAuthor, mBookChapter));

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),
                mBookKey, mBookParts, mBookName, mBookChapter);

        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(Integer.parseInt(mBookChapter) - 1);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mToolbar.setTitle(String.format("%s %s", mBookChapterAuthor, String.valueOf(position + 1)));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_glava_talk_context, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                return true;
            case  android.R.id.home:
                finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private String mBookName;
        private String mBookKey;
        private int mBookParts;
        private String mBookChapter;

        private SectionsPagerAdapter(FragmentManager fm, String bookKey, int bookParts,
                                     String bookName, String bookChapter) {
            super(fm);

            mBookName = bookName;
            mBookKey = bookKey;
            mBookParts = bookParts;
            mBookChapter = bookChapter;
        }

        @Override
        public Fragment getItem(int position) {
           return ContextTextPagerFragment.newInstance(mBookKey, position + 1,
                   mBookName, String.valueOf(position + 1));
        }

        @Override
        public int getCount() {
            return mBookParts;
        }
    }
}
