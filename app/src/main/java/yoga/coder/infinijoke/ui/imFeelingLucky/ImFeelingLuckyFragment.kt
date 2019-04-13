package yoga.coder.infinijoke.ui.imFeelingLucky


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import com.yuyakaido.android.cardstackview.*

import yoga.coder.infinijoke.R
import yoga.coder.infinijoke.injector
import yoga.coder.infinijoke.model.Joke
import yoga.coder.infinijoke.viewmodel.JokeViewModel
import javax.inject.Inject

class ImFeelingLuckyFragment : Fragment(), CardStackListener, ImFeelingLuckyScreen {
    companion object {
        val NUM_OF_CARDS = 3
    }

    @Inject
    lateinit var presenter: ImFeelingLuckyPresenter

    private lateinit var cardStackView: CardStackView
    private lateinit var jokeViewModel: JokeViewModel
    private val manager by lazy { CardStackLayoutManager(this.context, this) }
    private val adapter by lazy { ImFeelingLuckyAdapter() }
    private var firstDataArrived = false
    private var initialized = false
    private var lastDisappeared: Joke? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
        presenter.attachScreen(this)
    }

    override fun onDetach() {
        presenter.detachScreen()
        super.onDetach()
    }

    override fun onStart() {
        super.onStart()
        presenter.requestRandomJoke(NUM_OF_CARDS)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        jokeViewModel = ViewModelProviders.of(this).get(JokeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_im_feeling_lucky, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        cardStackView = view!!.findViewById(R.id.card_stack_view)
        initialize()
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {

    }

    override fun onCardSwiped(direction: Direction?) {
        presenter.requestRandomJoke()
        lastDisappeared?.let {
            when(direction){
                Direction.Right -> it.rating = 3.0f
                Direction.Top -> it.rating = 5.0f
                Direction.Left -> it.rating = 1.0f
                Direction.Bottom -> return
            }
            jokeViewModel.insert(it)
        }
    }

    override fun onCardCanceled() {

    }

    override fun onCardAppeared(view: View?, position: Int) {

    }

    override fun onCardRewound() {

    }

    override fun onCardDisappeared(view: View?, position: Int) {
        // adapter.removeItem(position)
        val joke: Joke? = view?.tag as Joke?
        lastDisappeared = joke
    }

    private fun initialize() {
        if(!firstDataArrived || initialized)
            return
        initialized = true

        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.FREEDOM)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        manager.setSwipeableMethod(SwipeableMethod.Manual)
        manager.setOverlayInterpolator(LinearInterpolator())
        cardStackView.layoutManager = manager
        cardStackView.adapter = adapter
        cardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }

    override fun addRandomJoke(joke: Joke) {
        adapter.addItem(joke)
        firstDataArrived = true
        initialize()
    }
}
