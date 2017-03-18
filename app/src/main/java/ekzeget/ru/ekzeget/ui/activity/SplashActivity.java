package ekzeget.ru.ekzeget.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import ekzeget.ru.ekzeget.R;
import ekzeget.ru.ekzeget.ui.activity.MainCheesesActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showMain();
            }
        }, 5 * 1000);
    }

    private void showMain() {
        Intent intent = new Intent(this, MainCheesesActivity.class);
        startActivity(intent);
        finish();
    }
}
