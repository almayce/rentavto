package io.almayce.dev.rentavto

import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import io.almayce.dev.rentavto.databinding.ActivityLauncherBinding

/**
 * Created by almayce on 31.08.17.
 */
class LauncherActivity : MvpAppCompatActivity() {

    private lateinit var bn : ActivityLauncherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bn = DataBindingUtil.setContentView(this, R.layout.activity_launcher)
    }

    fun onItemClick(v: View) {
        AssetsReader.readfromAssets(this, v.contentDescription.toString())
        var intent = Intent(this, SelectionActivity::class.java)
        intent.putExtra("key", v.contentDescription)
        startActivity(intent)
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
}