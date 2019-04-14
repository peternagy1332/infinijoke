package yoga.coder.infinijoke.ui.imFeelingLucky

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.card_joke.view.*
import yoga.coder.infinijoke.R
import yoga.coder.infinijoke.model.Joke
import java.util.*

class ImFeelingLuckyAdapter(
    private var mValues: MutableList<Joke> = mutableListOf()
): RecyclerView.Adapter<ImFeelingLuckyAdapter.ViewHolder>() {

    companion object {
        val PLACEHOLDER_W = 600
        val PLACEHOLDER_H = 800
        val PLACEHOLDER_PROVIDERS = listOf(
            "http://placekitten.com/$PLACEHOLDER_W/$PLACEHOLDER_H",
            "https://www.placecage.com/$PLACEHOLDER_W/$PLACEHOLDER_H",
            "https://www.placecage.com/g/$PLACEHOLDER_W/$PLACEHOLDER_H",
            "https://www.placecage.com/c/$PLACEHOLDER_W/$PLACEHOLDER_H",
            "https://www.placecage.com/gif/$PLACEHOLDER_W/$PLACEHOLDER_H",
            "https://stevensegallery.com/$PLACEHOLDER_W/$PLACEHOLDER_H",
            "https://stevensegallery.com/g/$PLACEHOLDER_W/$PLACEHOLDER_H"
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.card_im_feeling_lucky, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        with(holder) {
            mType.text = item.type
            mSetup.text = item.setup
            mPunchline.text = item.punchline
            mView.tag = item
            Glide.with(image)
                .load(PLACEHOLDER_PROVIDERS.random())
                .into(image)
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    fun addItem(item: Joke) {
        mValues.add(item)
        notifyItemInserted(mValues.size-1)
    }

    fun removeItem(position: Int) {
        mValues.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mType: TextView = mView.type
        var mSetup: TextView = mView.setup
        var mPunchline: TextView = mView.punchline
        var image: ImageView = mView.findViewById(R.id.item_image)
    }
}