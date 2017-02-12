package ekzeget.ru.ekzeget;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import ekzeget.ru.ekzeget.fabrevealmenu.enums.Direction;
import ekzeget.ru.ekzeget.fabrevealmenu.listeners.OnFABMenuSelectedListener;
import ekzeget.ru.ekzeget.fabrevealmenu.model.FABMenuItem;
import ekzeget.ru.ekzeget.fabrevealmenu.view.FABRevealMenu;

public class BibleFragment extends BaseFragment implements OnFABMenuSelectedListener {

    private ArrayList<FABMenuItem> items;
    private String[] mDirectionStrings = {"Direction - LEFT", "Direction - UP"};
    private Direction currentDirection = Direction.LEFT;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bible, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initItems(false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        final FABRevealMenu fabMenu = (FABRevealMenu) view.findViewById(R.id.fabMenu);

        try {
            if (fab != null && fabMenu != null) {
                setFabMenu(fabMenu);
                fabMenu.bindAncherView(fab);
                fabMenu.setMenuItems(items);
                fabMenu.setOnFABMenuSelectedListener(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        CheckBox cbTitle = (CheckBox) view.findViewById(R.id.chTitle);
        cbTitle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (fabMenu != null) {
                    fabMenu.setTitleVisible(b);
                }
            }
        });
        CheckBox cbShowOverlay = (CheckBox) view.findViewById(R.id.chOverlay);
        cbShowOverlay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (fabMenu != null) {
                    fabMenu.setShowOverlay(b);
                }
            }
        });
        CheckBox cbDouble = (CheckBox) view.findViewById(R.id.chDouble);
        cbDouble.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (fabMenu != null) {
                    initItems(b);
                    fabMenu.setMenuItems(items);
                }
            }
        });

        Spinner spDirections = (Spinner) view.findViewById(R.id.spDirection);
        spDirections.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, mDirectionStrings));
        spDirections.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (fabMenu != null) {
                    if (position == 0 && currentDirection != Direction.LEFT) {
                        currentDirection = Direction.LEFT;
                        fabMenu.setMenuDirection(Direction.LEFT);
                    } else if (position == 1 && currentDirection != Direction.UP) {
                        currentDirection = Direction.UP;
                        fabMenu.setMenuDirection(Direction.UP);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                if (fabMenu != null) {
                    fabMenu.setMenuDirection(Direction.LEFT);
                }
            }
        });

        assert fabMenu != null;
        fabMenu.setOverlayBackground(R.color.colorAccent);
        fabMenu.setMenuBackground(R.color.colorWhite);
    }

    @Override
    public void onMenuItemSelected(View view) {
        int position = (int) view.getTag();
        if (position >= 0 && items != null && items.size() > position) {
            Toast.makeText(getActivity(), items.get(position).getTitle() + "Clicked", Toast.LENGTH_SHORT).show();
        }
    }

    private void initItems(boolean toShowDoubleItems) {
        items = new ArrayList<>();
        for (int i = 0; i < (toShowDoubleItems ? 10 : 5); i++) {
            items.add(new FABMenuItem("Item " + i, BitmapFactory.decodeResource(getResources(), R.drawable.ic_done)));
        }
    }
}

/*public class BibleFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bible, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Menu 1");
    }
}*/
