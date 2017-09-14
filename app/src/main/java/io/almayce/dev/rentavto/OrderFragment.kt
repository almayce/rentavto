package io.almayce.dev.rentavto

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.almayce.dev.rentavto.databinding.FragmentOrderBinding


/**
 * Created by almayce on 21.08.17.
 */
class OrderFragment : Fragment() {

    private lateinit var binding: FragmentOrderBinding
    private lateinit var preferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater?, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentOrderBinding>(
                inflater, R.layout.fragment_order, container, false)
        binding.btOrder.setOnClickListener({ send() })
        binding.tp.setIs24HourView(true)
        return binding.getRoot()
    }

    private fun send() {
        preferences = context.getSharedPreferences("history", Context.MODE_PRIVATE)
        val to = "info@rentavto.ru"

        var line1: String? = "${binding.et1.text?.toString()}"
        var line2: String? = "${binding.et2.text?.toString()}"
        var line3: String? = "${binding.et3.text?.toString()}"
        var line4: String? = "${binding.et4.text?.toString()}"
        var line5: String? = "${binding.et5.text?.toString()}"
        var car = activity.intent.getStringExtra("car")

        var text = "Имя: $line1\n" +
                "Телефон: $line2\n" +
                "Автомобиль: $car\n" +
                "Дата: ${binding.dp.dayOfMonth}/${binding.dp.month}/${binding.dp.year}\n" +
                "Время: ${binding.tp.currentHour}ч. ${binding.tp.currentMinute}м.\n" +
                "Адрес подачи: $line3\n" +
                "Описание маршрута: $line4\n" +
                "Дополнительные требования: $line5"


        val email = Intent(Intent.ACTION_SEND)
        email.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
        email.putExtra(Intent.EXTRA_SUBJECT, "Заказ")
        email.putExtra(Intent.EXTRA_TEXT, text)

        //для того чтобы запросить email клиент устанавливаем тип
        email.type = "message/rfc822"

        val ed = preferences.edit()
        var savedHistory = preferences.getString("history", "")
        if (savedHistory == "")
            ed.putString("history", text)
        else ed.putString("history", "$text split $savedHistory ")
        ed.apply()

        startActivity(Intent.createChooser(email, "Отправить заказ на $to с помошью:"))
    }
}