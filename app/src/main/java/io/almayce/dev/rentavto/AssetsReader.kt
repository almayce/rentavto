package io.almayce.dev.rentavto

import android.content.Context
import android.graphics.drawable.Drawable
import android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable
import java.io.IOException


/**
 * Created by almayce on 31.08.17.
 */
object AssetsReader {
    var cars = ArrayList<Drawable>()

    fun readfromAssets(context: Context, key: String) {
        cars.clear()
        var counter = 1

        while (counter > 0) {
            try {
                val ims = context.getAssets().open("cars/$key$counter.png")
                val d = Drawable.createFromStream(ims, null)
                cars.add(d)
                counter++
            } catch (ex: IOException) {
                counter = -1
            }
        }
        println(cars.size)



    }
}