package ekzeget.ru.ekzeget.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import ekzeget.ru.ekzeget.ui.activity.BookActivity;

public class GlavaTalkFragment extends Fragment {
    public static GlavaTalkFragment newInstance(String bookKey) {
        GlavaTalkFragment f = new GlavaTalkFragment();

        Bundle args = new Bundle();
        args.putString(BookActivity.BOOK_KEY, bookKey);
        f.setArguments(args);

        return f;

        //select * from talks where kn='mf1' and st_no=1
    }

}
