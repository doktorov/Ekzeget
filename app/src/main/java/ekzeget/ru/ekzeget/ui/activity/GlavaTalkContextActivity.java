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

import java.util.List;

import ekzeget.ru.ekzeget.R;
import ekzeget.ru.ekzeget.db.queries.BibleQueries;
import ekzeget.ru.ekzeget.db.queries.TalksQueries;
import ekzeget.ru.ekzeget.model.BibleModel;
import ekzeget.ru.ekzeget.model.Talks;
import ekzeget.ru.ekzeget.ui.fragment.GlavaTalkContextFragment;

public class GlavaTalkContextActivity extends AppCompatActivity {
    public static final String BOOK_KEY_CHAPTER = "book_key_chapter";
    public static final String CHAPTER_AUTHOR = "chapter_author";

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    private Toolbar mToolbar;

    private static String mBookKeyChapter;
    private static String mChapterAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glava_talk_context);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBookKeyChapter = getIntent().getStringExtra(BOOK_KEY_CHAPTER);
        mChapterAuthor = getIntent().getStringExtra(CHAPTER_AUTHOR);

        mToolbar.setTitle(mChapterAuthor);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),
                BibleQueries.getChapterContent(mBookKeyChapter),
                TalksQueries.getListTalksText(mBookKeyChapter, mChapterAuthor));

        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
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
        private List<BibleModel> mBibleModel;

        private SectionsPagerAdapter(FragmentManager fm, List<BibleModel> bibleModel, List<Talks> talks) {
            super(fm);

            mBibleModel = bibleModel;

            if (talks.size() != 0) {
                for (Talks talk : talks) {
                    for (BibleModel chp : bibleModel) {
                        if (talk.stNo == chp.stNo) {
                            chp.comments = talk.comments;
                            break;
                        }
                    }
                }
            }
        }

        @Override
        public Fragment getItem(int position) {
            return GlavaTalkContextFragment.newInstance(mBibleModel.get(position).stNo,
                    mBibleModel.get(position).stText, mBibleModel.get(position).comments);
        }

        @Override
        public int getCount() {
            return mBibleModel.size();
        }
    }
}
