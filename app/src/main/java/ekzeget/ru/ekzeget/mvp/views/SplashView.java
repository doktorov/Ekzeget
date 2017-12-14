package ekzeget.ru.ekzeget.mvp.views;

import com.arellomobile.mvp.MvpView;

public interface SplashView extends MvpView {
    void showProgress();

    void hideProgress();

    void delayShowMain();

    void showMain();
}