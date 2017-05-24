package ekzeget.ru.ekzeget.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ekzeget.ru.ekzeget.R;
import ekzeget.ru.ekzeget.db.queries.BibleQueries;
import ekzeget.ru.ekzeget.model.Bible;

public class ContentTextFragment extends Fragment {
    public static final String BOOK_KEY = "book_key";
    public static final String BOOK_CHAPTER = "book_chapter";

    private static String mBookKey;
    private static String mBookChapter;

    //private Unbinder unbinder;

    //@BindView(R.id.context_text)
    TextView mContextText;

    public static ContentTextFragment newInstance(String bookKey, String bookChapter) {
        ContentTextFragment f = new ContentTextFragment();

        Bundle args = new Bundle();
        args.putString(BOOK_KEY, bookKey);
        args.putString(BOOK_CHAPTER, bookChapter);
        f.setArguments(args);

        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NestedScrollView rv = (NestedScrollView) inflater.inflate(
                R.layout.fragment_context_text, container, false);

        //unbinder = ButterKnife.bind(this, view);

        mBookKey = getArguments().getString(BOOK_KEY);
        mBookChapter = getArguments().getString(BOOK_CHAPTER);

        mContextText = (TextView) rv.findViewById(R.id.context_text);

        return rv;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //unbinder.unbind();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Bible> bibles = BibleQueries.getChapterContent(mBookKey + mBookChapter);

        StringBuilder stringBuilder = new StringBuilder();

        for (Bible bible : bibles) {
            stringBuilder.append(bible.st_no + " ");
            stringBuilder.append(bible.st_text + " ");
        }

        stringBuilder.append("\n\n");

        mContextText.setText(stringBuilder.toString());
    }
}
