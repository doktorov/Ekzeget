package ekzeget.ru.ekzeget.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import ekzeget.ru.ekzeget.App;
import ekzeget.ru.ekzeget.R;
import ekzeget.ru.ekzeget.ui.fragment.BookInfoListFragment;
import ekzeget.ru.ekzeget.ui.fragment.ContentTextFragment;
import ekzeget.ru.ekzeget.ui.fragment.GlavaTalkFragment;

public class BookContentActivity extends AppCompatActivity {
    public static final String BOOK_NAME = "book_name";
    public static final String BOOK_KEY = "book_key";
    public static final String BOOK_CHAPTER = "book_chapter";

    private static String mBookName;
    private static String mBookKey;
    private static String mBookChapter;

    private DrawerLayout mDrawerLayout;

    private Toolbar mToolbar;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_content);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBookName = getIntent().getStringExtra(BOOK_NAME);
        mBookKey = getIntent().getStringExtra(BOOK_KEY);
        mBookChapter = getIntent().getStringExtra(BOOK_CHAPTER);

        ViewPager viewPager = findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        mDrawerLayout = findViewById(R.id.drawer_layout);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(App.getAppResources().getString(R.string.text));
        //adapter.addFragment(App.getAppResources().getString(R.string.interpretations));//Close
        adapter.addFragment(App.getAppResources().getString(R.string.parallel_poems));
        viewPager.setAdapter(adapter);
    }

    private static class Adapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 2;

        private final List<String> mFragmentTitles = new ArrayList<>();

        Adapter(FragmentManager fm) {
            super(fm);
        }

        void addFragment(String title) {
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return ContentTextFragment.newInstance(mBookName, mBookKey, mBookChapter);
                //case 1:
                //    return GlavaTalkFragment.newInstance(mBookKey, mBookChapter);//Close
                case 1:
                    return BookInfoListFragment.newInstance(mBookKey);
            }

            return null;
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    public void setActionBarTitle(String title){
        mToolbar.setTitle(title);
    }
}
