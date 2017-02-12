package ekzeget.ru.ekzeget;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
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

import ekzeget.ru.ekzeget.fabrevealmenu.view.FABRevealMenu;
import ekzeget.ru.ekzeget.model.Book;
import ekzeget.ru.ekzeget.model.Interpreting;
import ekzeget.ru.ekzeget.model.gson.GsonBooks;
import ekzeget.ru.ekzeget.model.gson.GsonChapter;
import ekzeget.ru.ekzeget.model.gson.GsonInterpreting;
import ekzeget.ru.ekzeget.util.BookUtil;
import ekzeget.ru.ekzeget.util.ChapterUtils;
import ekzeget.ru.ekzeget.util.FileUtils;

public class BibleActivity extends AppCompatActivity {

    GsonBooks gsonBooks = new GsonBooks();
    List<Book> books = new ArrayList<>();
    List<String> chapters = new ArrayList<>();

    List<GsonInterpreting> gsonInterpreting = new ArrayList<>();
    List<Interpreting> interpretings = new ArrayList<>();

    List<GsonChapter> gsonChapter = new ArrayList<>();

    FABRevealMenu fabMenu;

    Spinner mBooks;
    Spinner mChapters;
    Spinner mInterpreting;
    TextView mInterpretingText;

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

        mInterpreting = (Spinner) findViewById(R.id.interpreting);

        mBooks = (Spinner) findViewById(R.id.books);
        mChapters = (Spinner) findViewById(R.id.chapters);

        mInterpretingText = (TextView) findViewById(R.id.interpreting_text);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fabMenu = (FABRevealMenu) findViewById(R.id.fabMenu);

        try {
            if (fab != null && fabMenu != null) {

                View customView = View.inflate(this, R.layout.layout_custom_menu, null);
                setupCustomFilterView(customView);
                fabMenu.setCustomView(customView);
                fabMenu.bindAncherView(fab);
//                setupCustomFilterView(fabMenu.getCustomView());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



        final List<Book> books = BookUtil.getBooks();
        ArrayAdapter<Book> adapterBooks = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, books);
        mBooks.setAdapter(adapterBooks);
        mBooks.setSelection(book_id);
        mBooks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<String> chapters = ChapterUtils.getChapters(books.get(position).key, books.get(position).parts, true);
                ArrayAdapter<String> adapterChapters = new ArrayAdapter<>(BibleActivity.this, android.R.layout.simple_spinner_item, chapters);
                mChapters.setAdapter(adapterChapters);
                mChapters.setSelection(chapter_id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mChapters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (position != 0) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if (book_id > 0) {
            mBooks.setVisibility(View.GONE);
            mChapters.setVisibility(View.GONE);
        }

        mInterpretingText.setText("123");
    }

    private void setupCustomFilterView(View customView) {
        if (customView != null) {
            Button btnApply = (Button) customView.findViewById(R.id.btnApply);
//            CheckBox cb1 = (CheckBox) customView.findViewById(R.id.cb1);
//            CheckBox cb2 = (CheckBox) customView.findViewById(R.id.cb2);
//            CheckBox cb3 = (CheckBox) customView.findViewById(R.id.cb3);
//            CheckBox cb4 = (CheckBox) customView.findViewById(R.id.cb4);
//
//            final CheckBox[] filters = new CheckBox[]{cb1, cb2, cb3, cb4};
//
//            btnApply.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    fabMenu.closeMenu();
//                    StringBuilder builder = new StringBuilder("Selected:");
//                    for (CheckBox filter : filters) {
//                        if (filter.isChecked()) {
//                            builder.append("\n").append(filter.getText().toString());
//                        }
//                    }
//                    Toast.makeText(InterpretingActivity.this, builder.toString(), Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
