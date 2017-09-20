package ekzeget.ru.ekzeget.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Map;

import ekzeget.ru.ekzeget.R;
import ekzeget.ru.ekzeget.db.queries.BibleQueries;
import ekzeget.ru.ekzeget.ui.activity.BookActivity;
import ekzeget.ru.ekzeget.ui.activity.BookContentActivity;
import ekzeget.ru.ekzeget.ui.activity.ContextTextActivity;

public class BookContentListFragment extends Fragment {
    private static String mBookKey;
    private static String mBookName;
    private static int mBookParts;

    public static BookContentListFragment newInstance(String bookKey, String bookName, int bookParts) {
        BookContentListFragment f = new BookContentListFragment();

        Bundle args = new Bundle();
        args.putString(BookActivity.BOOK_KEY, bookKey);
        args.putString(BookActivity.BOOK_NAME, bookName);
        args.putInt(BookActivity.BOOK_PARTS, bookParts);
        f.setArguments(args);

        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RecyclerView rv = (RecyclerView) inflater.inflate(
                R.layout.fragment_cheese_list, container, false);

        mBookKey = getArguments().getString(BookActivity.BOOK_KEY);
        mBookName = getArguments().getString(BookActivity.BOOK_NAME);
        mBookParts = getArguments().getInt(BookActivity.BOOK_PARTS);

        setupRecyclerView(rv);

        return rv;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new SimpleStringRecyclerViewAdapter(getActivity(),
                BibleQueries.getListContentsSorted(mBookKey, mBookParts), mBookName, mBookKey));
    }

    private static class SimpleStringRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();
        private int mBackground;
        private Map<Integer, Integer> mValues;
        private String mBookName;
        private String mBookKey;

        static class ViewHolder extends RecyclerView.ViewHolder {
            private String mBookName;
            private String mBookKey;
            private String mBookChapter;
            private String mBookChapterAuthor;

            private final View mView;
            private final TextView mTextView;

            private ViewHolder(View view) {
                super(view);
                mView = view;
                mTextView = view.findViewById(android.R.id.text1);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mTextView.getText();
            }
        }

        public Integer getValueAt(int position) {
            return mValues.get(position);
        }

        private SimpleStringRecyclerViewAdapter(Context context, Map<Integer, Integer> items,
                                                String bookName, String bookKey) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
            mValues = items;
            mBookName = bookName;
            mBookKey = bookKey;
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
            holder.mBookName = mBookName;
            holder.mBookChapter = String.valueOf(position + 1);
            holder.mBookKey = mBookKey;
            holder.mBookChapterAuthor = String.format("%s. Глава %s", mBookName, (position + 1));
            holder.mTextView.setText( String.format("%s. Глава %s (%s стихов)",
                    mBookName, (position + 1), mValues.get(position + 1)));

            holder.mView.setOnClickListener(v -> {
                Context context = v.getContext();
                //Intent intent = new Intent(context, BookContentActivity.class);
                Intent intent = new Intent(context, ContextTextActivity.class);
                intent.putExtra(ContextTextActivity.BOOK_NAME, holder.mBookName);
                intent.putExtra(ContextTextActivity.BOOK_KEY, holder.mBookKey);
                intent.putExtra(ContextTextActivity.BOOK_CHAPTER, holder.mBookChapter);
                intent.putExtra(ContextTextActivity.BOOK_CHAPTER_AUTHOR, holder.mBookChapterAuthor);
                context.startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }
    }
}
