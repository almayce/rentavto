package io.almayce.dev.rentavto

import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import com.arellomobile.mvp.MvpAppCompatActivity
import io.almayce.dev.icologs.view.adapter.SectionsPagerAdapter
import io.almayce.dev.rentavto.databinding.ActivitySelectionBinding
import io.almayce.dev.rentavto.databinding.DialogInfoBinding
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by almayce on 31.08.17.
 */
class SelectionActivity : MvpAppCompatActivity(), ViewPager.OnPageChangeListener {

    private lateinit var bn: ActivitySelectionBinding
    private lateinit var pagerAdapter: SectionsPagerAdapter
    private lateinit var array: Array<String>
    private lateinit var array_: Array<String>
    private var info = StringBuilder()
    private lateinit var dsp: Disposable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bn = DataBindingUtil.setContentView(this, R.layout.activity_selection)

        val key = intent.getStringExtra("key")

        when {
            key == "a" -> {
                array = resources.getStringArray(R.array.a)
                array_ = resources.getStringArray(R.array.a_)
            }
            key == "b" -> {
                array = resources.getStringArray(R.array.b)
                array_ = resources.getStringArray(R.array.b_)
            }
            key == "c" -> {
                array = resources.getStringArray(R.array.c)
                array_ = resources.getStringArray(R.array.c_)
            }
            key == "d" -> {
                array = resources.getStringArray(R.array.d)
                array_ = resources.getStringArray(R.array.d_)
            }
            key == "e" -> {
                array = resources.getStringArray(R.array.e)
                array_ = resources.getStringArray(R.array.e_)
            }
            key == "f" -> {
                array = resources.getStringArray(R.array.f)
                array_ = resources.getStringArray(R.array.f_)
            }
        }

        pagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        bn.container.adapter = pagerAdapter

        info.setLength(0)
        info.append(array_[0])
        supportActionBar!!.title = array[0]
        bn.container.addOnPageChangeListener(this)
        bn.btOrder.setOnClickListener({
            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra("car", supportActionBar!!.title)
            startActivity(intent)
        })

        bn.container.setCurrentItem(array.size-1, true)
        launch(UI) {
            delay(500L)
            bn.container.setCurrentItem(0, true)
        }
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        supportActionBar!!.title = array[position]
        info.setLength(0)
        info.append(array_[position])
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_info, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.action_info) {
            infoDialog(info.toString())
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun infoDialog(text: String) {
        var dbn = DataBindingUtil
                .inflate<DialogInfoBinding>(LayoutInflater.from(this), R.layout.dialog_info, null, false)
        val builder = AlertDialog.Builder(this)
        dbn.tvInfo.text = text

        builder.setView(dbn.getRoot())

        builder.setCancelable(true)
        var dialogAdd = builder.create()
        dialogAdd.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialogAdd.show()
        dialogAdd.getWindow()!!.setBackgroundDrawableResource(android.R.color.transparent)
    }

}