package com.asynctaskcoffee.moviestemplate.ui.components.showroom

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.asynctaskcoffee.moviestemplate.R
import com.asynctaskcoffee.moviestemplate.data.localmodels.AdditionalResultItem
import com.asynctaskcoffee.moviestemplate.data.localmodels.CommonResultItem
import com.asynctaskcoffee.moviestemplate.ui.base.BaseFragment
import com.asynctaskcoffee.moviestemplate.ui.components.movies.MoviesFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.fragment_showroom.*
import java.io.Serializable
import javax.inject.Inject

class ShowRoomFragment : BaseFragment<ShowRoomContract.View, ShowRoomContract.Presenter>(),
    ShowRoomContract.View {

    @Inject
    lateinit var showRoomPresenter: ShowRoomPresenter

    lateinit var commonResultItem: CommonResultItem

    companion object {
        fun newInstance(commonResultItem: CommonResultItem): Fragment {
            val fragment = ShowRoomFragment()
            val args = Bundle()
            args.putSerializable("CommonResultItem", commonResultItem as Serializable)
            fragment.arguments = args
            return fragment
        }
    }

    override fun initPresenter() = showRoomPresenter

    override fun injectDependencies() = getApplicationComponent().inject(this)

    override fun getLayoutResId(): Int = R.layout.fragment_showroom

    override fun initUI() {
        if (requireArguments().containsKey("CommonResultItem")) {
            commonResultItem = requireArguments().getParcelable("CommonResultItem")!!

            Glide.with(this).load("https://image.tmdb.org/t/p/w500" + commonResultItem.posterPath)
                .into(detailsImage)

            detailsTitle.text = commonResultItem.name
            detailsRating.text = commonResultItem.rating
            detailsDate.text = commonResultItem.dateString

            innerRating.text = commonResultItem.rating
            innerTitle.text = commonResultItem.name

            cardShowRoomBack.setOnClickListener {
                transitionStart()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onAdditionalInformationReady(additionalResultItem: AdditionalResultItem) {
        detailsInformation.text = additionalResultItem.information
        detailsDuration.text = additionalResultItem.duration + " min"
        detailsSubTitle.text = ""
        for (item in additionalResultItem.genres!!) {
            detailsSubTitle.append(item?.name + " ")
        }

        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + commonResultItem.backdropPath)
            .into(detailsCoverImage)

        animateInnerLayout()
    }


    override fun showProgressDialog() {
    }

    override fun hideProgressDialog() {
    }

    override fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    private fun lockViewPager() {
        if (parentFragment is MoviesFragment)
            (parentFragment as MoviesFragment).verticalViewPagerMovies.lock()
    }

    private fun unlockViewPager() {
        if (parentFragment is MoviesFragment)
            (parentFragment as MoviesFragment).verticalViewPagerMovies.unlock()
    }

    private fun transitionStart() {
        showRoomMotionLayout.setTransitionDuration(300)
        if (showRoomMotionLayout.currentState == R.id.startShowRoom) {
            lockViewPager()
            showRoomMotionLayout.setTransition(R.id.startShowRoom, R.id.midShowRoom)
            showRoomMotionLayout.transitionToEnd()
            Handler().postDelayed({
                showRoomMotionLayout.setTransition(R.id.midShowRoom, R.id.endShowRoom)
                showRoomMotionLayout.transitionToEnd()
                Handler().postDelayed({
                    showRoomPresenter.getMoviesDetails(commonResultItem.id.toString())
                }, 350)
            }, 350)
        }
    }

    private fun transitionReverseStart() {
        showRoomInnerMotionLayout.setTransition(
            R.layout.activity_details_demo_design_end,
            R.layout.activity_details_demo_design
        )
        showRoomInnerMotionLayout.transitionToEnd()
        showRoomMotionLayout.setTransitionDuration(300)
        Handler().postDelayed({ reverseParentMotion() }, 350)
    }

    private fun reverseParentMotion() {
        if (showRoomMotionLayout.currentState == R.id.endShowRoom) {
            showRoomMotionLayout.setTransition(R.id.endShowRoom, R.id.midShowRoom)
            showRoomMotionLayout.transitionToEnd()
            Handler().postDelayed({
                showRoomMotionLayout.setTransition(R.id.midShowRoom, R.id.startShowRoom)
                showRoomMotionLayout.transitionToEnd()
                unlockViewPager()
                Handler().postDelayed({
                    showRoomInnerMotionLayout.loadLayoutDescription(0)
                    showRoomInnerMotionLayout.setTransition(
                        R.layout.activity_details_demo_design,
                        R.layout.activity_details_demo_design
                    )
                }, 350)
            }, 350)
        }
    }

    private fun animateInnerLayout() {
        showRoomInnerMotionLayout.loadLayoutDescription(R.xml.activity_details_demo_design_scene)
        showRoomInnerMotionLayout.setTransition(
            R.layout.activity_details_demo_design,
            R.layout.activity_details_demo_design_end
        )
        showRoomInnerMotionLayout.transitionToEnd()
        Handler().postDelayed({
            showRoomInnerMotionLayout.setTransition(
                R.layout.activity_details_demo_design_end,
                R.layout.activity_details_demo_design_end
            )
        }, 600)
    }

    override fun onBackPressed(): Boolean {
        if (showRoomMotionLayout.currentState == R.id.endShowRoom)
            transitionReverseStart()
        else requireActivity().finish()
        return super.onBackPressed()
    }

}