package yoga.coder.infinijoke.ui.infiniteJokes

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import yoga.coder.infinijoke.R
import yoga.coder.infinijoke.injector
import yoga.coder.infinijoke.model.Joke

import javax.inject.Inject

class InfiniteJokesFragment : Fragment(), InfiniteJokesScreen {
    @Inject
    lateinit var infiniteJokesPresenter: InfiniteJokesPresenter


    private var infiniteJokesAdapter: InfiniteJokesRecyclerViewAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
        infiniteJokesPresenter.attachScreen(this)
    }

    override fun onDetach() {
        infiniteJokesPresenter.detachScreen()
        super.onDetach()
    }


    override fun showJokes(jokes: MutableList<Joke>?) {
        infiniteJokesAdapter!!.addValues(jokes)
    }

    private fun requestJokes() {
        infiniteJokesPresenter.requestTenRandomJokes()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_infinitejokes_list, container, false)

        val linearLayoutManager = LinearLayoutManager(context)
        infiniteJokesAdapter = InfiniteJokesRecyclerViewAdapter()

        if (view is RecyclerView) {
            with(view) {
                layoutManager = linearLayoutManager
                adapter  = infiniteJokesAdapter
                addOnScrollListener(InfiniteScrollListener({ requestJokes() }, linearLayoutManager))
            }
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requestJokes()
    }
}
