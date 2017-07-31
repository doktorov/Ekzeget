package ekzeget.ru.ekzeget.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ekzeget.ru.ekzeget.R;

public class GlavaTalkContextFragment extends Fragment {
    private static final String ST_NO = "st_no";
    private static final String ST_TEXT = "st_text";
    private static final String COMMENTS = "comments";

    public GlavaTalkContextFragment() {
    }

    public static GlavaTalkContextFragment newInstance(int sectionNumber,
                                                       String stText, String comments) {
        GlavaTalkContextFragment fragment = new GlavaTalkContextFragment();
        Bundle args = new Bundle();
        args.putInt(ST_NO, sectionNumber);
        args.putString(ST_TEXT, stText);
        args.putString(COMMENTS, comments);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_glava_talk_context, container, false);

        TextView textView = (TextView) rootView.findViewById(R.id.st_text);
        textView.setText(getArguments().getInt(ST_NO) + " " + getArguments().getString(ST_TEXT));

        TextView textView1 = (TextView) rootView.findViewById(R.id.comments);
        textView1.setText(getArguments().getString(COMMENTS));

        return rootView;
    }
}
