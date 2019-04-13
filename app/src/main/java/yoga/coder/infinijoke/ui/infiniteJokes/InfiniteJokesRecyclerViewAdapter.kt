package yoga.coder.infinijoke.ui.infiniteJokes

import android.media.Rating
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import yoga.coder.infinijoke.R

import kotlinx.android.synthetic.main.fragment_infinitejokes.view.*
import yoga.coder.infinijoke.model.Joke
import java.util.*

class InfiniteJokesRecyclerViewAdapter(
    private val mValues: MutableList<Joke> = mutableListOf()
) : RecyclerView.Adapter<InfiniteJokesRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private val mOnRatingBarChangeListener: RatingBar.OnRatingBarChangeListener;

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Joke
            Log.d("asdasd", item.punchline)
        }

        mOnRatingBarChangeListener = RatingBar.OnRatingBarChangeListener {
            ratingBar, rating, fromUser ->

        }
    }

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
            mType.text = mValues[position].type
            mSetup.text = mValues[position].setup
            mPunchline.text = mValues[position].punchline
            mRating.rating = Random().nextInt(5).toFloat()
            mRating.setOnRatingBarChangeListener(mOnRatingBarChangeListener)
        }
        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mType: TextView = mView.type
        var mSetup: TextView = mView.setup
        var mPunchline: TextView = mView.punchline
        var mRating: RatingBar = mView.rating

        override fun toString(): String {
            return super.toString() + " '" + mType.text + "'"
        }
    }
}
