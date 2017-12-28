package ru.ekzeget

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.arellomobile.mvp.presenter.ProvidePresenterTag

class SplashActivity  : MvpAppCompatActivity(), SplashView {
    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var splashPresenter: SplashPresenter

    @ProvidePresenterTag(presenterClass = SplashPresenter::class, type = PresenterType.GLOBAL)
    fun provideDialogPresenterTag(): String = "Splash"

    @ProvidePresenter(type = PresenterType.GLOBAL)
    fun provideDialogPresenter() = SplashPresenter()

    private var mProgress: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mProgress = findViewById(R.id.progressBarView)

        splashPresenter.checkDataBaseBeforeStart()
    }

    override fun showProgress() {
        mProgress?.visibility= View.VISIBLE
    }

    override fun hideProgress() {
        mProgress?.visibility= View.GONE
    }

    override fun delayShowMain() {
        var s = ""
        //Handler().postDelayed({ this.showMain() }, 1000)
    }

    override fun showMain() {
        var s = ""
        //val intent = Intent(this, MainActivity::class.java)
        //startActivity(intent)
        //finish()
    }
}