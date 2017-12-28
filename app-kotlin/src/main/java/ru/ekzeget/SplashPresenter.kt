package ru.ekzeget

import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.AsyncSubject
import ru.ekzeget.db.DbHelper
import ru.ekzeget.utils.Zip

@InjectViewState
class SplashPresenter : MvpPresenter<SplashView>() {
    private val observable: Observable<String>
        get() = Observable.just("Cricket", "Football")

    fun checkDataBaseBeforeStart() {
        viewState.showProgress()

        if (!checkDataBase()) {
//            val source = AsyncSubject.create<String>()
//            source.subscribe(unzipObserver)
//            source.onComplete()

            observable
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
                val zip = Zip()
                zip.onUnzipZip()
            }

            override fun onNext(t: String) {
               val s = ""
            }

            override fun onError(e: Throwable) {}

            override fun onComplete() {
                viewState.hideProgress()

                Handler().postDelayed({ viewState.showMain() }, 1000)
            }
        }
}