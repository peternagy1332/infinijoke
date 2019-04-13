package yoga.coder.infinijoke.ui.infiniteJokes

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import yoga.coder.infinijoke.R
import yoga.coder.infinijoke.injector
import yoga.coder.infinijoke.model.Joke
import yoga.coder.infinijoke.ui.InfiniteScrollListener
import yoga.coder.infinijoke.ui.imFeelingLucky.ImFeelingLuckyAdapter
import yoga.coder.infinijoke.viewmodel.JokeViewModel

import javax.inject.Inject

class InfiniteJokesFragment : Fragment(), InfiniteJokesScreen {

    @Inject
    lateinit var presenter: InfiniteJokesPresenter

    private val adapter by lazy { InfiniteJokesAdapter(jokeViewModel) }
    private lateinit var jokeViewModel: JokeViewModel
    private val manager by lazy { LinearLayoutManager(context) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
        presenter.attachScreen(this)
    }

    override fun onDetach() {
        presenter.detachScreen()
        super.onDetach()
    }


    override fun showJokes(jokes: MutableList<Joke>?) {
        adapter.addValues(jokes)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        jokeViewModel = ViewModelProviders.of(this).get(JokeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_infinitejokes_list, container, false)

        if (view is androidx.recyclerview.widget.RecyclerView) {
            view.layoutManager = manager
            view.adapter = adapter
            view.addOnScrollListener(InfiniteScrollListener(
                { presenter.requestTenRandomJokes() },
                manager
            ))
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.requestTenRandomJokes()
    }
}
