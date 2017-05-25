package ekzeget.ru.ekzeget.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ekzeget.ru.ekzeget.R;
import ekzeget.ru.ekzeget.db.queries.BibleQueries;
import ekzeget.ru.ekzeget.model.Bible;
import ekzeget.ru.ekzeget.model.ContentString;

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

        List<ContentString> contentStrings = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (Bible bible : bibles) {
            String res = String.format("%s %s ", bible.st_no, bible.st_text);

            contentStrings.add(new ContentString(
                    bible.st_no,
                    stringBuilder.toString().length(),
                    stringBuilder.toString().length() + res.length() - 1));

            stringBuilder.append(res);
        }

        stringBuilder.append("\n\n");

        SpannableString ss = new SpannableString(stringBuilder.toString());
        for (final ContentString contentString : contentStrings) {
            ss.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View textView) {
                    String s = String.valueOf(contentString.getStNo());
                    s = "";
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(false);
                }
            }, contentString.getStart(), contentString.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        mContextText.setText(ss);
        mContextText.setMovementMethod(LinkMovementMethod.getInstance());
        mContextText.setHighlightColor(Color.TRANSPARENT);
    }
}
