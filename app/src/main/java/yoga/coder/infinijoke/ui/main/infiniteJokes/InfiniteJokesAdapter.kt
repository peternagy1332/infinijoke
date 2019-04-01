package yoga.coder.infinijoke.ui.main.infiniteJokes

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import yoga.coder.infinijoke.R
import yoga.coder.infinijoke.model.Joke

class InfiniteJokesAdapter constructor(
    private val context: Context,
    private var jokes: List<Joke>) : RecyclerView.Adapter<InfiniteJokesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.card_artist, viewGroup, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val joke = jokes[position]


        holder.tvName.text = joke.name
        holder.tvPopularity.text = joke.popularity!!.toString()
    }

    override fun getItemCount() = jokes.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvName: TextView = view.tvName
        var tvPopularity: TextView = view.tvPopularity
    }
}