package ru.app_kotlin

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class SplashPresenter : MvpPresenter<SplashView>() {
    fun onShowDialogClick() {
        viewState.showDialog()
    }

    fun onHideDialog() {
        viewState.hideDialog()
    }
}