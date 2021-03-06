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
import ekzeget.ru.ekzeget.ui.fragment.BookContentListFragment;
import ekzeget.ru.ekzeget.ui.fragment.BookInfoListFragment;

public class BookActivity extends AppCompatActivity {
    public static final String BOOK_KEY = "book_key";
    public static final String BOOK_NAME = "book_name";
    public static final String BOOK_PARTS = "book_parts";

    private static String mBookKey;
    private static String mBookName;
    private static int mBookParts;

    private DrawerLayout mDrawerLayout;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBookKey = getIntent().getStringExtra(BOOK_KEY);
        mBookName = getIntent().getStringExtra(BOOK_NAME);
        mBookParts = getIntent().getIntExtra(BOOK_PARTS, 0);

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
        adapter.addFragment(App.getAppResources().getString(R.string.content));
        adapter.addFragment(App.getAppResources().getString(R.string.book_info));
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
                    return BookContentListFragment.newInstance(mBookKey, mBookName, mBookParts);
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
}
