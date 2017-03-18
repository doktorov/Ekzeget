package ekzeget.ru.ekzeget.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import ekzeget.ru.ekzeget.ui.fragment.BibleFragment;
import ekzeget.ru.ekzeget.R;
import ekzeget.ru.ekzeget.db.queries.BibleQueries;
import ekzeget.ru.ekzeget.fabrevealmenu.view.FABRevealMenu;
import ekzeget.ru.ekzeget.model.Book;
import ekzeget.ru.ekzeget.model.gson.GsonChapter;
import ekzeget.ru.ekzeget.util.ChapterUtils;
import ekzeget.ru.ekzeget.util.FileUtils;

public class BibleActivity extends AppCompatActivity {
    private List<Book> mBooks;
    private List<String> mChapters;
    private List<GsonChapter> mGsonChapter;
    private ArrayAdapter<Book> mAdapterBooks;

    FABRevealMenu fabMenu;

    Spinner mBooksSpinner;
    Spinner mChaptersSpinner;
    Spinner mInterpretingSpinner;
    TextView mInterpretingText;

    Spinner mBooksMenu;
    Spinner mChaptersMenu;
    Spinner mInterpretingMenu;
    Button mApplyMenu;

    int book_id;
    int chapter_id;

    @Override
    public void onBackPressed() {
        if (fabMenu.isShowing())
            fabMenu.closeMenu();
        else
            super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bible);

        book_id = getIntent().getIntExtra(BibleFragment.BIBLE_BOOK_ID, 0);
        chapter_id = getIntent().getIntExtra(BibleFragment.BIBLE_CHAPTER_ID, 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBooksSpinner = (Spinner) findViewById(R.id.content_books);
        mChaptersSpinner = (Spinner) findViewById(R.id.content_chapters);
        mInterpretingText = (TextView) findViewById(R.id.content_interpreting);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fabMenu = (FABRevealMenu) findViewById(R.id.fabMenu);

        try {
            if (fab != null && fabMenu != null) {
                View customView = View.inflate(this, R.layout.layout_custom_menu, null);
                fabMenu.setCustomView(customView);
                fabMenu.bindAncherView(fab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        mBooksMenu = (Spinner) findViewById(R.id.menu_books);
        mChaptersMenu = (Spinner) findViewById(R.id.menu_chapters);
        mInterpretingMenu = (Spinner) findViewById(R.id.menu_interpreting);
        mApplyMenu = (Button) findViewById(R.id.menu_apply);

        mBooks = BibleQueries.getBooks();

        mAdapterBooks = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mBooks);
        mBooksSpinner.setAdapter(mAdapterBooks);
        mBooksSpinner.setSelection(book_id);
        mBooksSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    book_id = position;

                    mChapters = ChapterUtils.getChapters(mBooks.get(position).key, mBooks.get(position).parts, true);
                    ArrayAdapter<String> adapterChapters = new ArrayAdapter<>(BibleActivity.this, android.R.layout.simple_spinner_item, mChapters);
                    mChaptersSpinner.setAdapter(adapterChapters);
                    mChaptersSpinner.setSelection(chapter_id);

                    setupCustomFilterView();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mChaptersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (position != 0) {
                    selectItem(book_id, position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        setupCustomFilterView();
    }

    private void selectItem(int bookId, int chapterId) {
        mBooksSpinner.setVisibility(View.GONE);
        mChaptersSpinner.setVisibility(View.GONE);

        // chapters
        InputStream inputChapter = getResources().openRawResource(R.raw.chapter);
        String readChapter = FileUtils.readTextFile(inputChapter);
        Gson gson = new Gson();
        mGsonChapter = gson.fromJson(readChapter,  new TypeToken<ArrayList<GsonChapter>>() {}.getType());
        //

        StringBuilder sb = new StringBuilder();
        for (GsonChapter item  : mGsonChapter) {
            sb.append(item.st_no + " " + item.st_text + " ");
        }

        SpannableString ss = new SpannableString(sb.toString());

        mInterpretingText.setText(ss);

        book_id = bookId;
        chapter_id = chapterId;

        setupCustomFilterView();
    }

    private void setupCustomFilterView() {
        if (mBooks != null) {
            mBooksMenu.setAdapter(mAdapterBooks);
            mBooksMenu.setSelection(book_id);
            mBooksMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position != 0) {
                        mChapters = ChapterUtils.getChapters(mBooks.get(position).key, mBooks.get(position).parts, true);
                        ArrayAdapter<String> adapterChapters = new ArrayAdapter<>(BibleActivity.this, android.R.layout.simple_spinner_item, mChapters);
                        mChaptersMenu.setAdapter(adapterChapters);
                        mChaptersMenu.setSelection(chapter_id);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
