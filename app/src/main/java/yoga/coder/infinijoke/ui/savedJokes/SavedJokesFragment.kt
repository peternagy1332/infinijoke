package yoga.coder.infinijoke.ui.savedJokes


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout

import yoga.coder.infinijoke.R
import yoga.coder.infinijoke.model.Joke
import yoga.coder.infinijoke.ui.extension.observeOnce
import yoga.coder.infinijoke.viewmodel.JokeViewModel

class SavedJokesFragment : Fragment(), SavedJokesScreen {

    private lateinit var jokeViewModel: JokeViewModel
    private var tabs: TabLayout? = null
    private val adapter by lazy { SavedJokesAdapter(jokeViewModel) }
    private val manager by lazy { LinearLayoutManager(context) }
    private var items: List<Joke> = listOf()
    private var selectedJokeType: String? = null
    private var list: RecyclerView? = null
    private var emptyView: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        jokeViewModel = ViewModelProviders.of(this).get(JokeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_saved_jokes, container, false)

        tabs = view.findViewById(R.id.tabs)
        list = view.findViewById<RecyclerView>(R.id.list)
        emptyView = view.findViewById<LinearLayout>(R.id.empty_view)

        adapter.list = list
        if (list is RecyclerView) {
            list?.layoutManager = manager
            list?.adapter = adapter
        }

        val owner = this
        tabs?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                selectedJokeType = tab.text.toString()
                resetList()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })

        jokeViewModel.savedJokes.observe(owner, Observer { jokes: List<Joke> ->
            items = jokes
            resetList()
        })

        jokeViewModel.jokeTypes.observe(this, Observer { t: List<String>? ->
            t?.let {
                if (t.size != tabs?.tabCount) {
                    tabs?.removeAllTabs()
                    t.forEach {
                        tabs?.addTab(tabs?.newTab()!!.setText(it))
                    }
                }
            }
        })

        return view
    }

    private fun resetList() {
        if (items.isEmpty()) {
            emptyView?.visibility = View.VISIBLE
            list?.visibility = View.GONE
            tabs?.visibility = View.GONE
        } else {
            tabs?.visibility = View.VISIBLE
            val jokesOfType = items.filter { it.type.equals(selectedJokeType) }
            if (jokesOfType.isEmpty()) {
                emptyView?.visibility = View.VISIBLE
                list?.visibility = View.GONE
            } else {
                emptyView?.visibility = View.GONE
                list?.visibility = View.VISIBLE
                adapter.setValues(jokesOfType)
            }
        }
    }

}
