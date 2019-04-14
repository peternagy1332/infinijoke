package yoga.coder.infinijoke.ui.infiniteJokes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import yoga.coder.infinijoke.R

import kotlinx.android.synthetic.main.card_joke.view.*
import yoga.coder.infinijoke.model.Joke
import yoga.coder.infinijoke.viewmodel.JokeViewModel
import kotlin.concurrent.thread

class InfiniteJokesAdapter(
    private val context: Context?,
    private val jokeViewModel: JokeViewModel,
    private val mValues: MutableList<Joke> = mutableListOf()
) : RecyclerView.Adapter<InfiniteJokesAdapter.ViewHolder>() {

    fun addValues(values: List<Joke>?) {
        mValues.addAll(values!!.asIterable())
        notifyDataSetChanged()
    }

    fun removeValue(position: Int) {
        mValues.removeAt(position)
        notifyItemRemoved(position)
    }

    fun contains(joke: Joke): Boolean {
        return mValues.contains(joke)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_joke, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        with(holder) {
            mType.text = item.type
            mSetup.text = item.setup
            mPunchline.text = item.punchline
            mRating.rating = item.rating
            mRating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
                if (rating > 0.0f) {
                    item.rating = rating
                    jokeViewModel.insert(item)
                    val pos = mValues.indexOf(item)
                    removeValue(pos)
                    Toast.makeText(context, "Joke saved.", Toast.LENGTH_LONG).show()
                }
            }
        }
        with(holder.mView) {
            tag = item
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mType: TextView = mView.type
        var mSetup: TextView = mView.setup
        var mPunchline: TextView = mView.punchline
        var mRating: RatingBar = mView.rating
    }
}
