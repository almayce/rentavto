package io.almayce.dev.rentavto

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.almayce.dev.rentavto.databinding.FragmentInfoBinding

/**
 * Created by almayce on 21.08.17.
 */
class InfoFragment : Fragment() {
    private lateinit var binding: FragmentInfoBinding
    override fun onCreateView(inflater: LayoutInflater?, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentInfoBinding>(
                inflater, R.layout.fragment_info, container, false)
        return binding.getRoot()
    }
}