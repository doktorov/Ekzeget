package ekzeget.ru.ekzeget.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
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
import ekzeget.ru.ekzeget.ui.activity.BookContentPoemActivity;

public class ContextTextPagerFragment extends Fragment {
    public static final String BOOK_NAME = "book_name";
    public static final String BOOK_KEY = "book_key";
    public static final String BOOK_PART = "book_part";
    public static final String BOOK_CHAPTER = "book_chapter";

    private TextView mContextText;
    private View mProgressView;

    public static ContextTextPagerFragment newInstance(String bookKey, int part,
                                                       String bookName, String bookChapter) {
        ContextTextPagerFragment fragment = new ContextTextPagerFragment();

        Bundle args = new Bundle();
        args.putString(BOOK_NAME, bookName);
        args.putString(BOOK_KEY, bookKey);
        args.putInt(BOOK_PART, part);
        args.putString(BOOK_CHAPTER, bookChapter);
        fragment.setArguments(args);

        return fragment;
    }

    public String getBookName() {
        return getArguments().getString(BOOK_NAME);
    }

    public String getBookKey() {
        return getArguments().getString(BOOK_KEY);
    }

    public int getBookPart() {
        return getArguments().getInt(BOOK_PART);
    }

    public String getBookChapter() {
        return getArguments().getString(BOOK_CHAPTER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View rootView = inflater.inflate(R.layout.fragment_glava_talk_context, container, false);

//        TextView textView = rootView.findViewById(R.id.st_text);
//
//        List<Bible> bibles = BibleQueries.getChapterContent(getBookKey() + getBookPart());
//
//        StringBuilder stringBuilder = new StringBuilder();
//        for (Bible bible : bibles) {
//            String res = String.format("%s %s \n", bible.stNo, bible.stText);
//
//            stringBuilder.append(res);
//        }
//
//        textView.setText(stringBuilder.toString());

        CoordinatorLayout rv = (CoordinatorLayout) inflater.inflate(
                R.layout.fragment_context_text_pager, container, false);

        mContextText = rv.findViewById(R.id.context_text);
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

        List<Bible> bibles = BibleQueries.getChapterContent(getBookKey() + String.valueOf(getBookPart()));

        List<ContentString> contentStrings = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (Bible bible : bibles) {
            String res = String.format("%s %s \n", bible.stNo, bible.stText);

            contentStrings.add(new ContentString(
                    bible.stNo,
                    stringBuilder.toString().length(),
                    stringBuilder.toString().length() + res.length() - 1,
                    res));

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
                    intent.putExtra(BookContentPoemActivity.BOOK_NAME, getBookName());
                    intent.putExtra(BookContentPoemActivity.BOOK_KEY, getBookKey());
                    intent.putExtra(BookContentPoemActivity.BOOK_CHAPTER, getBookChapter());
                    intent.putExtra(BookContentPoemActivity.BOOK_ST_NO, String.valueOf(contentString.getStNo()));
                    intent.putExtra(BookContentPoemActivity.BOOK_POEM, contentString.getText());
                    startActivityForResult(intent, 1111);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(false);
                }
            }, contentString.getStart(), contentString.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            ss.setSpan(new AbsoluteSizeSpan(14, true),
                    contentString.getStart(),
                    contentString.getStart() + String.valueOf(contentString.getStNo()).length() + 1,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            ss.setSpan(new ForegroundColorSpan(Color.GRAY), contentString.getStart(),
                    contentString.getStart() + String.valueOf(contentString.getStNo()).length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


            ss.setSpan(new AbsoluteSizeSpan(16, true),
                    contentString.getStart() + String.valueOf(contentString.getStNo()).length() + 1,
                    contentString.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        mContextText.setText(ss);
        mContextText.setMovementMethod(LinkMovementMethod.getInstance());
        mContextText.setHighlightColor(Color.TRANSPARENT);
    }

}
