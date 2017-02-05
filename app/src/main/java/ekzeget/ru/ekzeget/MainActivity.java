package ekzeget.ru.ekzeget;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import ekzeget.ru.ekzeget.model.Book;
import ekzeget.ru.ekzeget.model.Interpreting;
import ekzeget.ru.ekzeget.model.gson.GsonBooks;
import ekzeget.ru.ekzeget.model.gson.GsonChapter;
import ekzeget.ru.ekzeget.model.gson.GsonInterpreting;
import ekzeget.ru.ekzeget.util.FileUtils;

public class MainActivity extends AppCompatActivity {
    GsonBooks gsonBooks = new GsonBooks();
    List<Book> books = new ArrayList<>();
    List<String> chapters = new ArrayList<>();

    List<GsonInterpreting> gsonInterpreting = new ArrayList<>();
    List<Interpreting> interpretings = new ArrayList<>();

    List<GsonChapter> gsonChapter = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

                ArrayAdapter<String> adapterChapters = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, chapters);
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



        /*TextView TV = (TextView)findViewById(R.id.textData);
        Spannable wordtoSpan = new SpannableString("I know just how to whisper, And I know just how to cry,I know just where to find the answers");

        wordtoSpan.setSpan(new ForegroundColorSpan(Color.BLUE), 15, 30, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        TV.setText(wordtoSpan);*/

        StringBuilder sb = new StringBuilder();
        for (GsonChapter item  :gsonChapter) {
            sb.append(item.st_no + " " + item.st_text + " ");

            ClickableSpan span = new ClickableSpan() {
                @Override
                public void onClick(View textView) {
                    String s = "";
                }
            };
        }

        /*ClickableSpan span1 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                String s = "";
            }
        };
        ClickableSpan span2 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                String s = "";
            }
        };*/

        SpannableString ss = new SpannableString(sb.toString());
        //ss.setSpan(span1, 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //ss.setSpan(span2, 6, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView textView = (TextView)findViewById(R.id.textData);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}



