package io.almayce.dev.icologs.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.Nullable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import io.almayce.dev.rentavto.AssetsReader
import io.almayce.dev.rentavto.R
import io.almayce.dev.rentavto.databinding.FragmentSelectionBinding

/**
 * Created by almayce on 28.08.17.
 */


class SelectionFragment : MvpAppCompatFragment() {

    private lateinit var bn: FragmentSelectionBinding
    override fun onCreateView(inflater: LayoutInflater?, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        bn = DataBindingUtil.inflate<FragmentSelectionBinding>(
                inflater, R.layout.fragment_selection, container, false)

        var index = arguments.getInt("num")
        bn.ivContent.setImageDrawable(AssetsReader.cars.get(index))
        return bn.getRoot()
    }

    companion object {
        fun newInstance(sectionNumber: Int): SelectionFragment {
            val fragment = SelectionFragment()
            val args = Bundle()
            args.putInt("num", sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }
}