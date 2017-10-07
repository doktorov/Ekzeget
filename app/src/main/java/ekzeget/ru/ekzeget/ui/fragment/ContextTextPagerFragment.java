package ekzeget.ru.ekzeget.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ekzeget.ru.ekzeget.R;
import ekzeget.ru.ekzeget.db.queries.BibleQueries;
import ekzeget.ru.ekzeget.model.Bible;

public class ContextTextPagerFragment extends Fragment {
    public static final String BOOK_KEY = "book_key";
    public static final String BOOK_PART = "book_part";

    public static ContextTextPagerFragment newInstance(String bookKey, int part) {
        ContextTextPagerFragment fragment = new ContextTextPagerFragment();
        Bundle args = new Bundle();
        args.putString(BOOK_KEY, bookKey);
        args.putInt(BOOK_PART, part);
        fragment.setArguments(args);
        return fragment;
    }

    public String getBookKey() {
        return getArguments().getString(BOOK_KEY);
    }

    public int getBookPart() {
        return getArguments().getInt(BOOK_PART);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_glava_talk_context, container, false);

        TextView textView = rootView.findViewById(R.id.st_text);

        List<Bible> bibles = BibleQueries.getChapterContent(getBookKey() + getBookPart());

        textView.setText(String.valueOf(getBookPart()) + bibles.get(0).getStText());

        return rootView;
    }
}
