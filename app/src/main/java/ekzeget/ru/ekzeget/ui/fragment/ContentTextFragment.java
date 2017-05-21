package ekzeget.ru.ekzeget.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import ekzeget.ru.ekzeget.ui.activity.BookActivity;

public class ContentTextFragment extends Fragment {
    public static ContentTextFragment newInstance(String bookKey, String bookChapter) {
        ContentTextFragment f = new ContentTextFragment();

        Bundle args = new Bundle();
        args.putString(BookActivity.BOOK_KEY, bookKey);
        f.setArguments(args);

        return f;
    }
}
