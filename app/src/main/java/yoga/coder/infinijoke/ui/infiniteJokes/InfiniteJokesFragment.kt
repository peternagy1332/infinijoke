package yoga.coder.infinijoke.ui.infiniteJokes

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import yoga.coder.infinijoke.R
import yoga.coder.infinijoke.injector
import yoga.coder.infinijoke.model.Joke
import yoga.coder.infinijoke.ui.InfiniteScrollListener
import yoga.coder.infinijoke.viewmodel.JokeViewModel

import javax.inject.Inject

class InfiniteJokesFragment : Fragment(), InfiniteJokesScreen {

    @Inject
    lateinit var presenter: InfiniteJokesPresenter

    private val adapter by lazy { InfiniteJokesAdapter(context, jokeViewModel) }
    private lateinit var jokeViewModel: JokeViewModel
    private val manager by lazy { LinearLayoutManager(context) }
    private var emptyView: LinearLayout? = null
    private var list: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var items: List<Joke> = listOf()

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
        progressBar?.visibility = View.GONE

        val newJokes = jokes?.filter { !items.contains(it) && !adapter.contains(it) }
        if (newJokes!!.isEmpty()) {
            Toast.makeText(context, "Out of jokes! :( ", Toast.LENGTH_LONG).show()
        } else {
            newJokes.forEach {
                it.rating = 0.0f
            }
            adapter.addValues(newJokes)

            if (adapter.itemCount == 0) {
                emptyView?.visibility = View.VISIBLE
                list?.visibility = View.GONE
            } else {
                emptyView?.visibility = View.GONE
                list?.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        jokeViewModel = ViewModelProviders.of(this).get(JokeViewModel::class.java)
        jokeViewModel.savedJokes.observe(this, Observer { jokes: List<Joke> ->
            items = jokes
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_infinitejokes_list, container, false)

        list = view.findViewById(R.id.list)
        emptyView = view.findViewById(R.id.empty_view)
        progressBar = view.findViewById(R.id.progress_bar)

        if (list is RecyclerView) {
            list?.layoutManager = manager
            list?.adapter = adapter
            list?.addOnScrollListener(
                InfiniteScrollListener(
                    {
                        progressBar?.visibility = View.VISIBLE
                        presenter.requestTenRandomJokes()
                    },
                    manager
                )
            )
        }

        if (adapter.itemCount == 0) {
            emptyView?.visibility = View.VISIBLE
            list?.visibility = View.GONE
            progressBar?.visibility = View.GONE
        } else {
            emptyView?.visibility = View.GONE
            list?.visibility = View.VISIBLE
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.requestTenRandomJokes()
    }
}
