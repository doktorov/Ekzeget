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
import ekzeget.ru.ekzeget.db.queries.TalksQueries;
import ekzeget.ru.ekzeget.model.Talks;
import ekzeget.ru.ekzeget.ui.activity.GlavaTalkContextActivity;

public class GlavaTalkFragment extends Fragment {
    public static final String BOOK_KEY = "book_key";
    public static final String BOOK_CHAPTER = "book_chapter";

    private static String mBookKey;
    private static String mBookChapter;

    public static GlavaTalkFragment newInstance(String bookKey, String bookChapter) {
        GlavaTalkFragment f = new GlavaTalkFragment();

        Bundle args = new Bundle();
        args.putString(BOOK_KEY, bookKey);
        args.putString(BOOK_CHAPTER, bookChapter);
        f.setArguments(args);

        return f;

        //select * from talks where kn='mf1' and st_no=1

//        select st_no, t_name from talks where kn='mf1'
//        group by t_name
//        order by t_name

        //select * from talks where kn='mf1'
        //and t_name = 'Иоанн Златоуст свт.'
        //order by CAST(st_no as int)
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) inflater.inflate(
                R.layout.fragment_cheese_list, container, false);

        mBookKey = getArguments().getString(BOOK_KEY);
        mBookChapter = getArguments().getString(BOOK_CHAPTER);

        setupRecyclerView(rv);

        return rv;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new SimpleStringRecyclerViewAdapter(getActivity(),
                TalksQueries.getListTalksGroupByName(mBookKey + mBookChapter)));
    }

    private static class SimpleStringRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();
        private int mBackground;
        private List<Talks> mValues;

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

        public Talks getValueAt(int position) {
            return mValues.get(position);
        }

        private SimpleStringRecyclerViewAdapter(Context context, List<Talks> items) {
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
            holder.mBoundString = mValues.get(position).tName;
            holder.mTextView.setText(mValues.get(position).tName);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, GlavaTalkContextActivity.class);
                    intent.putExtra(GlavaTalkContextActivity.BOOK_KEY_CHAPTER, mBookKey + mBookChapter);
                    intent.putExtra(GlavaTalkContextActivity.CHAPTER_AUTHOR, mValues.get(position).tName);
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }
    }

}
