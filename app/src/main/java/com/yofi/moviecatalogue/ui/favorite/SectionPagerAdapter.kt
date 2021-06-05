package com.yofi.moviecatalogue.ui.favorite

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.yofi.moviecatalogue.R
import com.yofi.moviecatalogue.ui.favorite.movie.FavMoviesFragment
import com.yofi.moviecatalogue.ui.favorite.tvshow.FavTvShowFragment
import com.yofi.moviecatalogue.ui.main.movie.MoviesFragment
import com.yofi.moviecatalogue.ui.main.tvshow.TvShowFragment

class SectionsPagerAdapter(private val mContext: Context, fragment: FragmentManager, bundle: Bundle) : FragmentPagerAdapter(fragment, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val TAB_TITLES = intArrayOf(
        R.string.tab1,
        R.string.tab2
    )

    private var fragmentBundle: Bundle

    init {
        fragmentBundle = bundle
    }

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        var fragment : Fragment? = null
        when(position){
            0 -> fragment = FavMoviesFragment()
            1 -> fragment = FavTvShowFragment()
        }
        fragment?.arguments = this.fragmentBundle
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }

}