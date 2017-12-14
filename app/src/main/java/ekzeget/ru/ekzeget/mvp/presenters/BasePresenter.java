package ekzeget.ru.ekzeget.mvp.presenters;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BasePresenter<View extends MvpView> extends MvpPresenter<View> {
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    protected void unSubscribeOnDestroy(@NonNull Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override public void onDestroy() {
        super.onDestroy();
        compositeSubscription.clear();
    }
}