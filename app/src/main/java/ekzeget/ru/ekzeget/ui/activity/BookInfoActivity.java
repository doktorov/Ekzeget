package ekzeget.ru.ekzeget.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import ekzeget.ru.ekzeget.R;

public class BookInfoActivity extends AppCompatActivity {
    public static final String INFO_NAME = "info_name";
    public static final String INFO_TEXT = "info_text";

    private String mInfoName;
    private String mInfoText;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mInfoName = getIntent().getStringExtra(INFO_NAME);
        mInfoText = getIntent().getStringExtra(INFO_TEXT);

        TextView txtAuthor = (TextView) findViewById(R.id.author);

        txtAuthor.setText(mInfoName);

        TextView txtInfo = (TextView) findViewById(R.id.info);

        txtInfo.setText(mInfoText);
    }
}
