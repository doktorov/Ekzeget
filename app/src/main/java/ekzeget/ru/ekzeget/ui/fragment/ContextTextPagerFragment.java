package ekzeget.ru.ekzeget.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ekzeget.ru.ekzeget.R;

public class ContextTextPagerFragment extends Fragment {
    private static final String ST_TEXT = "st_text";

    public static ContextTextPagerFragment newInstance(String stText) {
        ContextTextPagerFragment fragment = new ContextTextPagerFragment();
        Bundle args = new Bundle();
        args.putString(ST_TEXT, stText);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_glava_talk_context, container, false);

        TextView textView = rootView.findViewById(R.id.st_text);
        textView.setText(getArguments().getString(ST_TEXT));

        return rootView;
    }
}
