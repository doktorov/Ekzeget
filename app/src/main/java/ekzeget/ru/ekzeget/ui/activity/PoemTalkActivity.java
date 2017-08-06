package ekzeget.ru.ekzeget.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import ekzeget.ru.ekzeget.R;

public class PoemTalkActivity extends AppCompatActivity {
    public static final String BOOK_KEY_CHAPTER = "book_key_chapter";
    public static final String CHAPTER_AUTHOR = "chapter_author";
    public static final String COMMENT = "text";

    private Toolbar mToolbar;

    private static String mBookKeyChapter;
    private static String mChapterAuthor;
    private static String mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poem_talk_activity);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBookKeyChapter = getIntent().getStringExtra(BOOK_KEY_CHAPTER);
        mChapterAuthor = getIntent().getStringExtra(CHAPTER_AUTHOR);
        mText = getIntent().getStringExtra(COMMENT);

        mToolbar.setTitle(mChapterAuthor);

        TextView textView = (TextView) findViewById(R.id.context_text);
        textView.setText(mText);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case  android.R.id.home:
                finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
