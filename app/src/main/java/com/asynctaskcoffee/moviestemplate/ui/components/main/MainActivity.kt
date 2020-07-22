package com.asynctaskcoffee.moviestemplate.ui.components.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asynctaskcoffee.moviestemplate.R
import com.asynctaskcoffee.moviestemplate.ui.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity<MainContract.View, MainContract.Presenter>(), MainContract.View {

    @Inject
    lateinit var mainPresenter: MainPresenter

    override fun initPresenter() = mainPresenter

    override fun injectDependencies() = getApplicationComponent().inject(this)

    override fun getLayoutResId() = R.layout.activity_main

    override fun initUI() {

    }

    override fun initLastIndexes(lastTabIndex: Int, lastTabPageIndex: Int) {

    }
}