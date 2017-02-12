package ekzeget.ru.ekzeget;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import ekzeget.ru.ekzeget.util.FileUtils;

public class BibleActivity extends AppCompatActivity {

    GsonBooks gsonBooks = new GsonBooks();
    List<Book> books = new ArrayList<>();
    List<String> chapters = new ArrayList<>();

    List<GsonInterpreting> gsonInterpreting = new ArrayList<>();
    List<Interpreting> interpretings = new ArrayList<>();

    List<GsonChapter> gsonChapter = new ArrayList<>();

    FABRevealMenu fabMenu;

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
        setContentView(R.layout.activity_interpreting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        final Spinner sBooks = (Spinner) findViewById(R.id.books);
        final Spinner sChapters = (Spinner) findViewById(R.id.chapters);
        final Spinner sInterpreting = (Spinner) findViewById(R.id.interpreting);

        // Books
        InputStream inputBooks = getResources().openRawResource(R.raw.books);
        String readBooks = FileUtils.readTextFile(inputBooks);
        Gson gson = new Gson();
        gsonBooks = gson.fromJson(readBooks, GsonBooks.class);

        Book bookTitle = new Book();
        bookTitle.short_name = "Кн.";
        bookTitle.key = "null";
        books.add(bookTitle);
        for (GsonBooks.Book item : gsonBooks.nz) {
            Book book = new Book();
            book.short_name = item.short_name;
            book.key = item.key;
            book.parts = item.parts;
            books.add(book);
        }
        //

        // interpreting
        InputStream inputInterpreting = getResources().openRawResource(R.raw.interpreting);
        String readInterpreting = FileUtils.readTextFile(inputInterpreting);
        gson = new Gson();
        gsonInterpreting = gson.fromJson(readInterpreting,  new TypeToken<ArrayList<GsonInterpreting>>() {}.getType());

        for (GsonInterpreting item : gsonInterpreting) {
            Interpreting interpreting = new Interpreting();
            interpreting.name = item.t_name;
            interpretings.add(interpreting);
        }
        //

        // chapters
        InputStream inputChapter = getResources().openRawResource(R.raw.chapter);
        String readChapter = FileUtils.readTextFile(inputChapter);
        gson = new Gson();
        gsonChapter = gson.fromJson(readChapter,  new TypeToken<ArrayList<GsonChapter>>() {}.getType());
        //

        ArrayAdapter<Book> adapterBooks = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, books);
        sBooks.setAdapter(adapterBooks);
        sBooks.setSelection(0);
        sBooks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chapters = new ArrayList<>();
                chapters.add("Гл.");

                if (books.get(position).key != "null") {
                    chapters.add("О книге");
                    for (int i = 0; i < books.get(position).parts; i++) {
                        chapters.add(String.valueOf(i + 1));
                    }
                }

                ArrayAdapter<String> adapterChapters = new ArrayAdapter<>(BibleActivity.this, android.R.layout.simple_spinner_item, chapters);
                sChapters.setAdapter(adapterChapters);
                sChapters.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        ArrayAdapter<Interpreting> adapterInterpretings = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, interpretings);
        sInterpreting.setAdapter(adapterInterpretings);
        sInterpreting.setSelection(0);

        StringBuilder sb = new StringBuilder();
        for (GsonChapter item  :gsonChapter) {
            sb.append(item.st_no + " " + item.st_text + " ");
        }

        SpannableString ss = new SpannableString(sb.toString());
        TextView interpreting = (TextView) findViewById(R.id.interpreting_text);
        interpreting.setText(ss);
        interpreting.setMovementMethod(LinkMovementMethod.getInstance());
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
