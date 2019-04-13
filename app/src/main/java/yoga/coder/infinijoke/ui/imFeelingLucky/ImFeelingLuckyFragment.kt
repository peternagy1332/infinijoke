package yoga.coder.infinijoke.ui.imFeelingLucky


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import yoga.coder.infinijoke.R

class ImFeelingLuckyFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_im_feeling_lucky, container, false)
    }
}
