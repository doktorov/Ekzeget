package ru.app_kotlin

import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.arellomobile.mvp.presenter.ProvidePresenterTag

class SplashActivity  : MvpAppCompatActivity(), SplashView {

    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var splashPresenter: SplashPresenter

    var alertDialog: AlertDialog? = null

    @ProvidePresenterTag(presenterClass = SplashPresenter::class, type = PresenterType.GLOBAL)
    fun provideDialogPresenterTag(): String = "Hello"

    @ProvidePresenter(type = PresenterType.GLOBAL)
    fun provideDialogPresenter() = SplashPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //findViewById(R.id.activity_main).setOnClickListener { splashPresenter.onShowDialogClick() }

        splashPresenter.onShowDialogClick()
    }

    override fun showDialog() {
        alertDialog = AlertDialog.Builder(this)
                .setTitle("Title")
                .setMessage("Message")
                .setOnDismissListener { splashPresenter.onHideDialog() }
                .show()
    }

    override fun hideDialog() {
        alertDialog?.setOnDismissListener {  }
        alertDialog?.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()

        hideDialog()
    }
}