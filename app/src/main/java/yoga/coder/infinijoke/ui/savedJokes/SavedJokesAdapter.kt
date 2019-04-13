package yoga.coder.infinijoke.ui.savedJokes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_infinitejokes.view.*
import yoga.coder.infinijoke.R
import yoga.coder.infinijoke.model.Joke
import yoga.coder.infinijoke.viewmodel.JokeViewModel
import java.util.*

class SavedJokesAdapter(
    private val jokeViewModel: JokeViewModel,
    private val mValues: MutableList<Joke> = mutableListOf()
): androidx.recyclerview.widget.RecyclerView.Adapter<SavedJokesAdapter.ViewHolder>() {

    fun setValues(values: List<Joke>) {
        mValues.clear()
        mValues.addAll(values.asIterable())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedJokesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_infinitejokes, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    override fun onBindViewHolder(holder: SavedJokesAdapter.ViewHolder, position: Int) {
        val item = mValues[position]
        with(holder) {
            mType.text = item.type
            mSetup.text = item.setup
            mPunchline.text = item.punchline
            mRating.rating = item.rating
            mRating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
                if(rating == item.rating || rating == 0.0f){
                    jokeViewModel.delete(item)
                }else {
                    item.rating = rating
                    jokeViewModel.insert(item)
                }
            }
        }
        with(holder.mView) {
            tag = item
        }
    }

    inner class ViewHolder(val mView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(mView) {
        var mType: TextView = mView.type
        var mSetup: TextView = mView.setup
        var mPunchline: TextView = mView.punchline
        var mRating: RatingBar = mView.rating
    }
}