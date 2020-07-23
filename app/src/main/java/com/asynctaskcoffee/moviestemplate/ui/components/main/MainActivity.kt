package com.asynctaskcoffee.moviestemplate.ui.components.main

import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.asynctaskcoffee.moviestemplate.R
import com.asynctaskcoffee.moviestemplate.ui.base.BaseActivity
import com.asynctaskcoffee.moviestemplate.ui.components.movies.MoviesFragment
import com.asynctaskcoffee.moviestemplate.ui.components.series.SeriesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainContract.View, MainContract.Presenter>(), MainContract.View,
    BottomNavigationView.OnNavigationItemSelectedListener,ViewPager.OnPageChangeListener {

    @Inject
    lateinit var mainPresenter: MainPresenter

    override fun initPresenter() = mainPresenter

    override fun injectDependencies() = getApplicationComponent().inject(this)

    override fun getLayoutResId() = R.layout.activity_main

    override fun initUI() {
        fragmentViewPager.adapter = MainViewPagerAdapter(supportFragmentManager)
        fragmentViewPager.addOnPageChangeListener(this)
        getMainNavigationView().setOnNavigationItemSelectedListener(this)
    }

    override fun initLastIndexes(lastTabIndex: Int, lastTabPageIndex: Int) {
        if (lastTabIndex == 0) pushMoviesFragment()
        else if (lastTabIndex == 1) pushSeriesFragment()
    }

    override fun getMainToolbarTitleTextView(): TextView = titleText

    override fun getMainNavigationView(): BottomNavigationView = bottomNavigationView

    override fun pushMoviesFragment() {
        getMainToolbarTitleTextView().text = getString(R.string.title_movies)
        fragmentViewPager.currentItem = 0
        bottomNavigationView.menu.getItem(1).isChecked = false
        bottomNavigationView.menu.getItem(0).isChecked = true
    }

    override fun pushSeriesFragment() {
        getMainToolbarTitleTextView().text = getString(R.string.title_series)
        fragmentViewPager.currentItem = 1
        bottomNavigationView.menu.getItem(0).isChecked = false
        bottomNavigationView.menu.getItem(1).isChecked = true
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_movies -> pushMoviesFragment()
            R.id.navigation_series -> pushSeriesFragment()
        }
        return true
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        bottomNavigationView.menu.getItem(position).isChecked = true
    }
}