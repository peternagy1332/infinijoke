package yoga.coder.infinijoke.ui.infiniteJokes

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import yoga.coder.infinijoke.R

import kotlinx.android.synthetic.main.fragment_infinitejokes.view.*
import yoga.coder.infinijoke.model.Joke
import yoga.coder.infinijoke.viewmodel.JokeViewModel
import java.util.*

class InfiniteJokesAdapter(
    private val jokeViewModel: JokeViewModel,
    private val mValues: MutableList<Joke> = mutableListOf()
) : androidx.recyclerview.widget.RecyclerView.Adapter<InfiniteJokesAdapter.ViewHolder>() {

    fun addValues(values: List<Joke>?) {
        mValues.addAll(values!!.asIterable())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_infinitejokes, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        with(holder) {
            mType.text = item.type
            mSetup.text = item.setup
            mPunchline.text = item.punchline
            mRating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
                item.rating = rating
                jokeViewModel.insert(item)
            }
        }
        with(holder.mView) {
            tag = item
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(mView) {
        var mType: TextView = mView.type
        var mSetup: TextView = mView.setup
        var mPunchline: TextView = mView.punchline
        var mRating: RatingBar = mView.rating
    }
}
