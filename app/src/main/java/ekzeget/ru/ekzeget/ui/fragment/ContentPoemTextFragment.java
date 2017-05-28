package ekzeget.ru.ekzeget.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ekzeget.ru.ekzeget.R;
import ekzeget.ru.ekzeget.db.queries.CommonQueries;
import ekzeget.ru.ekzeget.db.queries.TalksQueries;
import ekzeget.ru.ekzeget.model.Common;
import ekzeget.ru.ekzeget.model.Talks;

public class ContentPoemTextFragment extends Fragment {
    public static final String BOOK_KEY = "book_key";
    public static final String BOOK_CHAPTER = "book_chapter";
    public static final String BOOK_ST_NO = "book_st_no";

    private static String mBookKey;
    private static String mBookChapter;
    private static String mBookStNo;

    private TextView mContextText;

    public static ContentPoemTextFragment newInstance(String bookKey, String bookChapter, String bookStNo) {
        ContentPoemTextFragment f = new ContentPoemTextFragment();

        Bundle args = new Bundle();
        args.putString(BOOK_KEY, bookKey);
        args.putString(BOOK_CHAPTER, bookChapter);
        args.putString(BOOK_ST_NO, bookStNo);
        f.setArguments(args);

        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RecyclerView rv = (RecyclerView) inflater.inflate(
                R.layout.fragment_content_poem_text_list, container, false);

        mBookKey = getArguments().getString(BOOK_KEY);
        mBookChapter = getArguments().getString(BOOK_CHAPTER);
        mBookStNo = getArguments().getString(BOOK_ST_NO);

        mContextText = (TextView) rv.findViewById(R.id.context_text);

        setupRecyclerView(rv);

        return rv;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        List<Talks> talksList = TalksQueries.getListTalksPoem(mBookKey + mBookChapter, mBookStNo);

        recyclerView.setAdapter(new SimpleStringRecyclerViewAdapter(getActivity(),
                CommonQueries.getListContents(mBookKey)));
    }

    private static class SimpleStringRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();
        private int mBackground;
        private List<Common> mValues;

        static class ViewHolder extends RecyclerView.ViewHolder {
            private String mBoundString;

            private final View mView;
            private final TextView mTextView;

            private ViewHolder(View view) {
                super(view);
                mView = view;
                mTextView = (TextView) view.findViewById(android.R.id.text1);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mTextView.getText();
            }
        }

        public Common getValueAt(int position) {
            return mValues.get(position);
        }

        private SimpleStringRecyclerViewAdapter(Context context, List<Common> items) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
            mValues = items;
        }

        @Override
        public SimpleStringRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            view.setBackgroundResource(mBackground);
            return new SimpleStringRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final SimpleStringRecyclerViewAdapter.ViewHolder holder, final int position) {
            holder.mBoundString = mValues.get(position).name;
            holder.mTextView.setText(mValues.get(position).name);

//            holder.mView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Context context = v.getContext();
//                    Intent intent = new Intent(context, BookInfoActivity.class);
//                    intent.putExtra(BookInfoActivity.INFO_NAME, mValues.get(position).name);
//                    intent.putExtra(BookInfoActivity.INFO_TEXT, mValues.get(position).text);
//                    context.startActivity(intent);
//                }
//            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }
    }
}
