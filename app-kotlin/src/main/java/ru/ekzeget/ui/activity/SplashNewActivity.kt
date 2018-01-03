package ru.ekzeget.ui.activity

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.ekzeget.R
import ru.ekzeget.mvp.presenters.SplashNewPresenter
import ru.ekzeget.mvp.views.SplashView

class SplashNewActivity  : MvpAppCompatActivity(), SplashView {

    @InjectPresenter
    lateinit var splashPresenter: SplashNewPresenter

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

    override fun showMain() {
        var s = ""
    }
}