package ekzeget.ru.ekzeget.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import ekzeget.ru.ekzeget.R;
import ekzeget.ru.ekzeget.db.queries.BibleQueries;

public class BookTranslateGlavaActivity  extends AppCompatActivity {
    public static final String BOOK_KEY = "book_key";
    public static final String BOOK_FIELD = "book_field";

    private static String mBookKey;
    private static String mBookField;

    private Toolbar mToolbar;

    private TextView mInfo;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_translate_glava);

        mBookKey = getIntent().getStringExtra(BOOK_KEY);
        mBookField = getIntent().getStringExtra(BOOK_FIELD);

        //ButterKnife.bind(this);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mInfo = findViewById(R.id.info);

        loadData();
    }

    private void loadData() {
        List<String> list = BibleQueries.getTranslatesGlava(mBookKey, mBookField);

        StringBuilder tr = new StringBuilder();
        for (String glava :list) {
            tr.append(glava).append("\n");
        }

        mInfo.setText(tr.toString());
    }
}
