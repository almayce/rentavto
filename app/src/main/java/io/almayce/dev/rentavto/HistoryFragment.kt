package io.almayce.dev.rentavto

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.almayce.dev.intenta.adapter.CustomAdapter
import io.almayce.dev.rentavto.databinding.FragmentHistoryBinding

/**
 * Created by almayce on 21.08.17.
 */
class HistoryFragment : Fragment(), CustomAdapter.ItemClickListener {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var preferences: SharedPreferences
    private lateinit var adapter: CustomAdapter

    override fun onCreateView(inflater: LayoutInflater?, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentHistoryBinding>(
                inflater, R.layout.fragment_history, container, false)
        preferences = context.getSharedPreferences("history", Context.MODE_PRIVATE)

        if (preferences.getString("history", "") == "")
            adapter = CustomAdapter(context, arrayOf("Нет истории"))
        else adapter = CustomAdapter(context, preferences.getString("history", "").split("split").toTypedArray())

        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(context)
        adapter.setClickListener(this)

        return binding.getRoot()
    }

    override fun onItemClick(view: View, position: Int) {
        send(view.contentDescription.toString())
    }

    private fun send(text: String) {
        val to = "info@rentavto.ru"

        val email = Intent(Intent.ACTION_SEND)
        email.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
        email.putExtra(Intent.EXTRA_SUBJECT, "Заказ")
        email.putExtra(Intent.EXTRA_TEXT, text)

        //для того чтобы запросить email клиент устанавливаем тип
        email.type = "message/rfc822"

        startActivity(Intent.createChooser(email, "Отправить заказ на $to с помошью:"))
    }
}