package au.com.tangke.tram.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import au.com.tangke.tram.R
import au.com.tangke.tram.ui.mvp.TramListFragment
import au.com.tangke.tram.ui.mvvm.TramFragment

class TramActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tram_activity)

        //mvvm
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, TramFragment.newInstance())
                    .commitNow()
        }

        //mvp
        /*if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, TramListFragment.newInstance())
                    .commitNow()
        }*/


    }

}
