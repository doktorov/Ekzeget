package ekzeget.ru.ekzeget.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import ekzeget.ru.ekzeget.R;
import ekzeget.ru.ekzeget.mvp.presenters.SplashPresenter;
import ekzeget.ru.ekzeget.mvp.views.SplashView;

public class SplashActivity extends MvpAppCompatActivity implements SplashView {
    @InjectPresenter
    SplashPresenter mSplashPresenter;

    @BindView(R.id.progressBarView)
    View mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        mSplashPresenter.checkDataBaseBeforeStart();
    }

    @Override
    public void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void delayShowMain() {
        new Handler().postDelayed(this::showMain, 1000);
    }

    @Override
    public void showMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
