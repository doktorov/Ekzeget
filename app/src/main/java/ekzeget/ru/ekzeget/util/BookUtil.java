package ekzeget.ru.ekzeget.util;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ekzeget.ru.ekzeget.App;
import ekzeget.ru.ekzeget.db.table.BooksTable;
import ekzeget.ru.ekzeget.model.Book;

public class BookUtil {
    public static List<Book> getBooks() {
        List<Book> books = new ArrayList<>();

        Book bookTitle = new Book();
        bookTitle.short_name = "Кн.";
        bookTitle.name = "Книга";
        bookTitle.key = "null";
        books.add(bookTitle);

        Cursor cursor = App.getReadableDatabase().query(
                BooksTable.TABLE_NAME,
                null,
                BooksTable.WHERE_ZAV,
                new String[]{ "nz" },
                null,
                null,
                null);

        final int MENU_INDEX = cursor.getColumnIndex(BooksTable.MENU);
        final int TITLE_INDEX = cursor.getColumnIndex(BooksTable.TITLE);
        final int KN_INDEX = cursor.getColumnIndex(BooksTable.KN);
        final int PARTS_INDEX = cursor.getColumnIndex(BooksTable.PARTS);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Book book = new Book();
            book.short_name = cursor.getString(MENU_INDEX);
            book.name = cursor.getString(TITLE_INDEX);
            book.key = cursor.getString(KN_INDEX);
            book.parts = cursor.getInt(PARTS_INDEX);
            books.add(book);

            cursor.moveToNext();
        }

        bookTitle = new Book();
        bookTitle.short_name = "";
        bookTitle.name = "";
        bookTitle.key = "null";
        books.add(bookTitle);

        cursor = App.getReadableDatabase().query(
                BooksTable.TABLE_NAME,
                null,
                BooksTable.WHERE_ZAV,
                new String[]{ "vz" },
                null,
                null,
                null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Book book = new Book();
            book.short_name = cursor.getString(MENU_INDEX);
            book.name = cursor.getString(TITLE_INDEX);
            book.key = cursor.getString(KN_INDEX);
            book.parts = cursor.getInt(PARTS_INDEX);
            books.add(book);

            cursor.moveToNext();
        }

        cursor.close();

        return books;
    }

    public static List<String> getFullNamesBooks(String book) {
        List<String> books = new ArrayList<>();

        Cursor cursor = App.getReadableDatabase().query(
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
