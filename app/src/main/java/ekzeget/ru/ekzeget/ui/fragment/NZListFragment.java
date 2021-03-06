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

import java.util.List;

import ekzeget.ru.ekzeget.R;
import ekzeget.ru.ekzeget.db.queries.BooksQueries;
import ekzeget.ru.ekzeget.model.Book;
import ekzeget.ru.ekzeget.ui.activity.BookActivity;

public class NZListFragment extends Fragment {

    private static RecyclerView mRecyclerView;
    private static View mProgressView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_content_list, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerview);
        mProgressView = view.findViewById(R.id.progress);

        setupRecyclerView(mRecyclerView);

        return view;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new SimpleStringRecyclerViewAdapter(getActivity(),
                BooksQueries.getFullNamesBooks("nz")));
    }

    private static class SimpleStringRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();
        private int mBackground;
        private List<Book> mValues;

        static class ViewHolder extends RecyclerView.ViewHolder {
            private String mBoundString;

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

        public Book getValueAt(int position) {
            return mValues.get(position);
        }

        private SimpleStringRecyclerViewAdapter(Context context, List<Book> items) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            view.setBackgroundResource(mBackground);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mBoundString = mValues.get(position).name;
            holder.mTextView.setText(mValues.get(position).name);

            holder.mView.setOnClickListener(v -> {
                mRecyclerView.setVisibility(View.GONE);
                mProgressView.setVisibility(View.VISIBLE);

                Context context = v.getContext();
                Intent intent = new Intent(context, BookActivity.class);
                intent.putExtra(BookActivity.BOOK_KEY, mValues.get(position).key);
                intent.putExtra(BookActivity.BOOK_NAME, mValues.get(position).menu);
                intent.putExtra(BookActivity.BOOK_PARTS, mValues.get(position).parts);
                context.startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }
    }
}
