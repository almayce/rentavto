package io.almayce.dev.icologs.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import io.almayce.dev.icologs.view.SelectionFragment
import io.almayce.dev.rentavto.AssetsReader


/**
 * Created by almayce on 28.08.17.
 */
class SectionsPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a SelectionFragment (defined as a static inner class below).
        return SelectionFragment.newInstance(position)
    }

    override fun getCount(): Int {
        return AssetsReader.cars.size
    }

//    override fun getPageTitle(position: Int): CharSequence? {
//        return titles.get(position)
//    }
}