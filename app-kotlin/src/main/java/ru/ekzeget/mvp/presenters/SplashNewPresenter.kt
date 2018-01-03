package ru.ekzeget.mvp.presenters

import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.ekzeget.App
import ru.ekzeget.db.DbHelper
import ru.ekzeget.mvp.views.SplashView
import ru.ekzeget.utils.Zip

@InjectViewState
class SplashNewPresenter : MvpPresenter<SplashView>() {
    init {
        App.appComponent.inject(this)
    }

    private val observable: Observable<String>
        get() = Observable.create { subscriber ->
            subscriber.onNext(onUnzip())
            subscriber.onComplete()
        }

    fun checkDataBaseBeforeStart() {
        viewState.showProgress()

        if (!checkDataBase()) {
            observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(unzipObserver)
        } else {
            viewState.hideProgress()

            Handler().postDelayed({ viewState.showMain() }, 1000)
        }
    }

    private fun checkDataBase(): Boolean {
        val dbPath = App.context?.getDatabasePath(DbHelper.DATABASE_NAME)
        if (dbPath != null) {
            return dbPath.exists()
        }

        return false
    }

    private val unzipObserver: Observer<String>
        get() = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(value: String) {

            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {
                viewState.hideProgress()

                viewState.showMain()
            }
        }

    private fun onUnzip(): String {
        val zip = Zip()
        return zip.onUnzipZip()
    }
}