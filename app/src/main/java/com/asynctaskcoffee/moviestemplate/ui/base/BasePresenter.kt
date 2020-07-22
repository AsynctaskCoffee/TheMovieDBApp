package com.asynctaskcoffee.moviestemplate.ui.base

import androidx.lifecycle.LifecycleObserver
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BasePresenter<V : BaseContract.View> : BaseContract.Presenter<V>, LifecycleObserver {

    protected var disposables: CompositeDisposable? = CompositeDisposable()
    protected var view: WeakReference<V>? = null
    protected var hasSavedInstance = false

    override fun getView(): V? = view?.get()

    override fun attachView(view: V) {
        this.view = WeakReference(view)
    }

    override fun detachView() {
        view?.clear()
        view = null
    }

    override fun onPresenterCreated() {
        this.disposables = CompositeDisposable()
    }

    override fun onPresenterDestroyed() {
        disposables?.dispose()
        disposables = null
    }

    override fun onCreated() {

    }

    override fun onStarted() {

    }

    override fun onResumed() {

    }

    override fun onStopped() {

    }

    override fun onPaused() {

    }

    override fun onDestroyed() {

    }

    override fun onUserInteraction() {

    }

    protected fun resetPresenter() {
        onPresenterDestroyed()
        onPresenterCreated()
    }

    override fun onBackPressed(): Boolean = false

    override fun onSavedInstance(hasSavedInstance: Boolean) {
        this.hasSavedInstance = hasSavedInstance
    }
}