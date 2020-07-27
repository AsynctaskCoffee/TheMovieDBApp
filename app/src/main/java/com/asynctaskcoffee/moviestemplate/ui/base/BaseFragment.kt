package com.asynctaskcoffee.moviestemplate.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import com.asynctaskcoffee.moviestemplate.MoviesApp

abstract class BaseFragment<V : BaseContract.View, P : BaseContract.Presenter<V>> : Fragment(),
    BaseContract.View {

    protected var presenter: P? = null
    private var hasSavedInstance: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hasSavedInstance = savedInstanceState != null
        injectDependencies()
        val viewModel: BaseViewModel<V, P> =
            ViewModelProvider(this).get(BaseViewModel<V, P>()::class.java)
        val isCreated = viewModel.presenter == null
        viewModel.presenter = initPresenter()
        presenter = viewModel.presenter
        presenter?.attachView(this as V)
        presenter?.onSavedInstance(hasSavedInstance)
        if (isCreated) presenter?.onPresenterCreated()
        presenter?.onCreated()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(getLayoutResId(), container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart() {
        super.onStart()
        presenter?.onStarted()
    }

    override fun onResume() {
        super.onResume()
        presenter?.onResumed()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter?.detachView()
    }

    override fun onPause() {
        super.onPause()
        presenter?.onPaused()
    }

    override fun onStop() {
        super.onStop()
        presenter?.onStopped()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroyed()
    }

    override fun handleLongPress(): Boolean? {
        return (view?.parent as ViewGroup).performLongClick()
    }

    fun getApplicationComponent() = (activity?.application as MoviesApp).applicationComponent

    protected abstract fun initPresenter(): P

    protected abstract fun injectDependencies()

    abstract fun getLayoutResId(): Int

    open fun onBackPressed(): Boolean = presenter?.onBackPressed() ?: false
}