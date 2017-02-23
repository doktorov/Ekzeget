package ekzeget.ru.ekzeget.util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import ekzeget.ru.ekzeget.App;
import ekzeget.ru.ekzeget.R;
import ekzeget.ru.ekzeget.db.table.BooksTable;
import ekzeget.ru.ekzeget.model.Book;
import ekzeget.ru.ekzeget.model.gson.GsonBooks;

import static ekzeget.ru.ekzeget.App.getAppResources;

public class BookUtil {
    public static List<Book> getBooks() {
        List<Book> books = new ArrayList<>();

        InputStream inputBooks = getAppResources().openRawResource(R.raw.books);
        String readBooks = FileUtils.readTextFile(inputBooks);
        Gson gson = new Gson();
        GsonBooks gsonBooks = gson.fromJson(readBooks, GsonBooks.class);

        Book bookTitle = new Book();
        bookTitle.short_name = "Кн.";
        bookTitle.name = "Книга";
        bookTitle.key = "null";
        books.add(bookTitle);
        for (GsonBooks.Book item : gsonBooks.nz) {
            Book book = new Book();
            book.short_name = item.short_name;
            book.name = item.name;
            book.key = item.key;
            book.parts = item.parts;
            books.add(book);
        }

        return books;
    }

    public static List<String> getFullNamesBooks(String book) {
        SQLiteDatabase mReadableDatabase = App.getReadableDatabase();

        List<String> books = new ArrayList<>();

        Cursor cursor = mReadableDatabase.query(
                BooksTable.TABLE_NAME,
                null,
                BooksTable.WHERE_ZAV,
                new String[]{ book },
                null,
                null,
                null);

        final int TITLE_INDEX = cursor.getColumnIndex(BooksTable.TITLE);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            books.add(cursor.getString(TITLE_INDEX));

            cursor.moveToNext();
        }

        cursor.close();

        return books;
    }
}
