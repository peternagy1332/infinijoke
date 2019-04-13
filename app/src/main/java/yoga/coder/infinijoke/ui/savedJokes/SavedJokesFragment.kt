package yoga.coder.infinijoke.ui.savedJokes


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import yoga.coder.infinijoke.R
import yoga.coder.infinijoke.model.Joke
import yoga.coder.infinijoke.ui.InfiniteScrollListener
import yoga.coder.infinijoke.viewmodel.JokeViewModel

class SavedJokesFragment : Fragment(), SavedJokesScreen {

    private lateinit var jokeViewModel: JokeViewModel
    private val adapter by lazy { SavedJokesAdapter(jokeViewModel) }
    private val manager by lazy { LinearLayoutManager(context) }

    override fun showJokes(jokes: List<Joke>?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        jokeViewModel = ViewModelProviders.of(this).get(JokeViewModel::class.java)
        jokeViewModel.allJokes.observe(this, Observer {  t: List<Joke>? ->
            t?.let {
                adapter.setValues(t)
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_infinitejokes_list, container, false)

        if (view is androidx.recyclerview.widget.RecyclerView) {
            view.layoutManager = manager
            view.adapter = adapter
            view.addOnScrollListener(
                InfiniteScrollListener(
                    { requestJokes() },
                    manager
                )
            )
        }
        return view
    }

    private fun requestJokes() {

    }

}
