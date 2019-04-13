package yoga.coder.infinijoke.ui.main

import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.fragment.app.Fragment
import androidx.core.view.GravityCompat
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*
import yoga.coder.infinijoke.R
import yoga.coder.infinijoke.injector
import yoga.coder.infinijoke.ui.infiniteJokes.InfiniteJokesFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainScreen, NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injector.inject(this)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onStart() {
        super.onStart()
        mainPresenter.attachScreen(this)
        mainPresenter.presentInfiniteJokes()
    }

    override fun onStop() {
        super.onStop()
        mainPresenter.detachScreen()
    }

    override fun showFragment(fragment: Fragment) {
        nav_view.menu.close()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_infinite_jokes -> {
                mainPresenter.presentInfiniteJokes()
            }
            R.id.nav_saved_jokes -> {
                mainPresenter.presentSavedJokes()
            }
            R.id.nav_lucky -> {
                mainPresenter.presentImFeelingLucky()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
