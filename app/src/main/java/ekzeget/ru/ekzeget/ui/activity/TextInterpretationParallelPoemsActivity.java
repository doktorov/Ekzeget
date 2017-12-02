package ekzeget.ru.ekzeget.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import ekzeget.ru.ekzeget.R;
import ekzeget.ru.ekzeget.ui.adapter.ContentPoemTextPagerAdapter;
import ekzeget.ru.ekzeget.ui.fragment.ContentPoemTextFragment;
import ekzeget.ru.ekzeget.ui.fragment.ContentPoemTextPageFragment;
import ekzeget.ru.ekzeget.ui.fragment.ContextTextPagerFragment;
import ekzeget.ru.ekzeget.ui.fragment.ParallelsListPoemFragment;
import ekzeget.ru.ekzeget.ui.fragment.TalksPoemTextFragment;

public class TextInterpretationParallelPoemsActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static final String BOOK_NAME = "book_name";
    public static final String BOOK_KEY = "book_key";
    public static final String BOOK_CHAPTER = "book_chapter";
    public static final String BOOK_ST_NO = "book_st_no";
    public static final String BOOK_POEM = "book_poem";
    public static final String BOOK_CHAPTER_SIZE = "book_chapter_size";

    private static String mBookName;
    private static String mBookKey;
    private static String mBookChapter;
    private static String mBookStNo;
    private static String mBookPoem;
    private static int mBookChapterSize;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private ContentPoemTextPagerAdapter mContentPoemTextPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_interpretation_parallel_poems);

        mBookName = getIntent().getStringExtra(BOOK_NAME);
        mBookKey = getIntent().getStringExtra(BOOK_KEY);
        mBookChapter = getIntent().getStringExtra(BOOK_CHAPTER);
        mBookStNo = getIntent().getStringExtra(BOOK_ST_NO);
        mBookPoem = getIntent().getStringExtra(BOOK_POEM);
        mBookChapterSize = getIntent().getIntExtra(BOOK_CHAPTER_SIZE, 0);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        mViewPager = findViewById(R.id.view_pager_container);

        mContentPoemTextPagerAdapter = new ContentPoemTextPagerAdapter(getSupportFragmentManager(),
                mBookKey, mBookChapter, mBookChapterSize, mBookName, mBookPoem);
        mViewPager.setAdapter(mContentPoemTextPagerAdapter);
        mViewPager.setCurrentItem(Integer.parseInt(mBookStNo) - 1);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mToolbar.setTitle(String.format("%s. Глава %s, стр %s", mBookName, mBookChapter, String.valueOf(position + 1)));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        updateToolbarText(String.format("%s. Глава %s, стр %s", mBookName, mBookChapter, mBookStNo));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                //selectedFragment = ContentPoemTextFragment.newInstance(mBookKey, mBookChapter, mBookStNo, mBookPoem);
                selectedFragment = ContentPoemTextPageFragment.newInstance(mBookKey, mBookChapter, mBookStNo, mBookPoem);
                break;
            case R.id.navigation_dashboard:
                selectedFragment = TalksPoemTextFragment.newInstance(mBookKey, mBookChapter, mBookStNo, mBookPoem);
                break;
            case R.id.navigation_notifications:
                selectedFragment = ParallelsListPoemFragment.newInstance(mBookKey, mBookChapter, mBookStNo);
                break;
        }

        updateToolbarText(item.getTitle());

        return true;
    }

    private void updateToolbarText(CharSequence text) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(text);
        }
    }
}
