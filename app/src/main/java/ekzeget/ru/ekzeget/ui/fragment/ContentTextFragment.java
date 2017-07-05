package ekzeget.ru.ekzeget.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
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
import ekzeget.ru.ekzeget.ui.activity.BookContentActivity;
import ekzeget.ru.ekzeget.ui.activity.BookContentPoemActivity;

public class ContentTextFragment extends Fragment {
    public static final String BOOK_NAME = "book_name";
    public static final String BOOK_KEY = "book_key";
    public static final String BOOK_CHAPTER = "book_chapter";

    private static String mBookName;
    private static String mBookKey;
    private static String mBookChapter;

    private TextView mContextText;
    private View mProgressView;

    public static ContentTextFragment newInstance(String bookName, String bookKey, String bookChapter) {
        ContentTextFragment f = new ContentTextFragment();

        Bundle args = new Bundle();
        args.putString(BOOK_NAME, bookName);
        args.putString(BOOK_KEY, bookKey);
        args.putString(BOOK_CHAPTER, bookChapter);
        f.setArguments(args);

        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CoordinatorLayout rv = (CoordinatorLayout) inflater.inflate(
                R.layout.fragment_context_text, container, false);

        mBookName = getArguments().getString(BOOK_NAME);
        mBookKey = getArguments().getString(BOOK_KEY);
        mBookChapter = getArguments().getString(BOOK_CHAPTER);

        ((BookContentActivity) getActivity()).setActionBarTitle(String.format("%s, Глава %s", mBookName, mBookChapter));

        mContextText = (TextView) rv.findViewById(R.id.context_text);
        mProgressView = rv.findViewById(R.id.progress);

        return rv;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
                    mContextText.setVisibility(View.GONE);
                    mProgressView.setVisibility(View.VISIBLE);

                    Context context = getActivity();
                    Intent intent = new Intent(context, BookContentPoemActivity.class);
                    intent.putExtra(BookContentPoemActivity.BOOK_NAME, mBookName);
                    intent.putExtra(BookContentPoemActivity.BOOK_KEY, mBookKey);
                    intent.putExtra(BookContentPoemActivity.BOOK_CHAPTER, mBookChapter);
                    intent.putExtra(BookContentPoemActivity.BOOK_ST_NO, String.valueOf(contentString.getStNo()));
                    context.startActivity(intent);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(false);
                }
            }, contentString.getStart(), contentString.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            ss.setSpan(new UnderlineSpan(), contentString.getStart(), contentString.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        mContextText.setText(ss);
        mContextText.setMovementMethod(LinkMovementMethod.getInstance());
        mContextText.setHighlightColor(Color.TRANSPARENT);
    }
}
