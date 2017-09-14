package io.almayce.dev.rentavto

import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import io.almayce.dev.rentavto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_order -> {
                supportActionBar!!.title = "Форма заказа"
                supportFragmentManager.beginTransaction().replace(R.id.content, OrderFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_history -> {
                supportActionBar!!.title = "История"
                supportFragmentManager.beginTransaction().replace(R.id.content, HistoryFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_info -> {
                supportActionBar!!.title = "О нас"
                supportFragmentManager.beginTransaction().replace(R.id.content, InfoFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        supportActionBar!!.title = "Форма заказа"
        supportFragmentManager.beginTransaction().replace(R.id.content, OrderFragment()).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_dial, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.action_call) {
            val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "+74954113494"))
            ContextCompat.startActivity(this, callIntent, null)
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

}
