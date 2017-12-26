package ru.ekzeget

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.ekzeget.db.DbHelper
import java.util.zip.ZipFile
import java.util.zip.ZipInputStream
import android.content.res.AssetManager
import ru.ekzeget.utils.Zip
import java.io.*
import java.util.zip.ZipOutputStream


@InjectViewState
class SplashPresenter : MvpPresenter<SplashView>() {
    fun checkDataBaseBeforeStart() {
        viewState.showProgress()

        if (!checkDataBase()) {
//            val operationObservable = Observable.create({ subscriber ->
//                subscriber.onNext(onUnzipZip())
//                subscriber.onCompleted()
//            } as Observable.OnSubscribe<String>).subscribeOn(Schedulers.io())
//
//            operationObservable.subscribe(object : Subscriber<String>() {
//                override fun onCompleted() {
//
//                }
//
//                override fun onError(e: Throwable) {}
//
//                override fun onNext(value: String) {
//                    viewState.showMain()
//                }
//            })
            val zip: Zip = Zip()
            val onUnzipZip = zip.onUnzipZip()

            val s1 = ""

        } else {
            viewState.hideProgress()

            viewState.delayShowMain()
        }
    }

    private fun checkDataBase(): Boolean {
        val dbPath = App.context?.getDatabasePath(DbHelper.DATABASE_NAME)
        if (dbPath != null) {
            return dbPath.exists()
        }

        return false
    }
}