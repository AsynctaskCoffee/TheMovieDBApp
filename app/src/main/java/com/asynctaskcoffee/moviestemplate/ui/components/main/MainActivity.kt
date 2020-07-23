package com.asynctaskcoffee.moviestemplate.ui.components.main

import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.asynctaskcoffee.moviestemplate.R
import com.asynctaskcoffee.moviestemplate.ui.base.BaseActivity
import com.asynctaskcoffee.moviestemplate.ui.components.movies.MoviesFragment
import com.asynctaskcoffee.moviestemplate.ui.components.series.SeriesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainContract.View, MainContract.Presenter>(), MainContract.View,
    BottomNavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var mainPresenter: MainPresenter

    override fun initPresenter() = mainPresenter

    override fun injectDependencies() = getApplicationComponent().inject(this)

    override fun getLayoutResId() = R.layout.activity_main

    override fun initUI() {
        getMainNavigationView().setOnNavigationItemSelectedListener(this)
    }

    override fun initLastIndexes(lastTabIndex: Int, lastTabPageIndex: Int) {
        if (lastTabIndex == 0) pushMoviesFragment()
        if (lastTabIndex == 1) pushSeriesFragment()
    }

    override fun getMainToolbarTitleTextView(): TextView = titleText

    override fun getMainFragmentManager(): FragmentManager = supportFragmentManager

    override fun getMainFragmentHolder(): Int = R.id.fragmentHolderView

    override fun getMainNavigationView(): BottomNavigationView = bottomNavigationView

    override fun pushMoviesFragment() {
        getMainFragmentManager().beginTransaction()
            .replace(getMainFragmentHolder(), newFragmentInstance<MoviesFragment>()).commit()
        getMainToolbarTitleTextView().text = getString(R.string.title_movies)
    }

    override fun pushSeriesFragment() {
        getMainFragmentManager().beginTransaction()
            .replace(getMainFragmentHolder(), newFragmentInstance<SeriesFragment>()).commit()
        getMainToolbarTitleTextView().text = getString(R.string.title_series)
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private inline fun <reified T : Fragment> newFragmentInstance() = T::class.java.newInstance()

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_movies -> pushMoviesFragment()
            R.id.navigation_series -> pushSeriesFragment()
        }
        return true
    }
}