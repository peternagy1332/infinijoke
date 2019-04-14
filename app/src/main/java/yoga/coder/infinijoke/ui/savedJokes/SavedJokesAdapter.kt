package yoga.coder.infinijoke.ui.savedJokes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_joke.view.*
import yoga.coder.infinijoke.R
import yoga.coder.infinijoke.model.Joke
import yoga.coder.infinijoke.viewmodel.JokeViewModel

class SavedJokesAdapter(
    private val jokeViewModel: JokeViewModel,
    private val mValues: MutableList<Joke> = mutableListOf()
) : RecyclerView.Adapter<SavedJokesAdapter.ViewHolder>() {

    var list: RecyclerView? = null

    fun setValues(values: List<Joke>) {
        mValues.clear()
        mValues.addAll(values.asIterable())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_joke, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    fun removeValue(position: Int) {
        list?.post {
            mValues.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        with(holder) {
            mType.visibility = View.GONE
            mSetup.text = item.setup
            mPunchline.text = item.punchline
            mRating.rating = item.rating
            mRating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
                if (rating == item.rating || rating == 0.0f) {
                    jokeViewModel.delete(item)
                    val pos = mValues.indexOf(item)
                    removeValue(position)
                }
            }
        }
        with(holder.mView) {
            tag = item
        }
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mType: TextView = mView.type
        var mSetup: TextView = mView.setup
        var mPunchline: TextView = mView.punchline
        var mRating: RatingBar = mView.rating
    }
}