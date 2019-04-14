package yoga.coder.infinijoke.ui.addJoke


import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.textfield.TextInputEditText

import yoga.coder.infinijoke.R
import yoga.coder.infinijoke.model.Joke
import yoga.coder.infinijoke.viewmodel.JokeViewModel

class AddJokeFragment : DialogFragment() {

    companion object {
        val TAG = "add_joke_dialog"
        fun display(fragmentManager: FragmentManager): AddJokeFragment {
            val dialog = AddJokeFragment()
            dialog.show(fragmentManager, TAG)
            return dialog
        }
    }

    private lateinit var jokeViewModel: JokeViewModel
    private var toolbar: Toolbar? = null

    private var selectedType: String? = null
    private var typeView: AutoCompleteTextView? = null
    private var setup: TextInputEditText? = null
    private var punchline: TextInputEditText? = null
    private var rating: RatingBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
        jokeViewModel = ViewModelProviders.of(this).get(JokeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_add_joke, container, false)
        toolbar = view.findViewById(R.id.toolbar)
        typeView = view.findViewById(R.id.autocompleteView)
        setup = view.findViewById(R.id.setup_joke)
        punchline = view.findViewById(R.id.punchline)
        rating = view.findViewById(R.id.rating)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val types = resources.getStringArray(R.array.joke_types)
        val adapter = ArrayAdapter(context, R.layout.dropdown_item, types)
        typeView?.setAdapter(adapter)
        typeView?.setOnItemClickListener { parent, view, position, id ->
            selectedType = parent.getItemAtPosition(position).toString()
        }

        toolbar?.setNavigationOnClickListener { v -> dismiss() }
        toolbar?.setTitle("Add joke")
        toolbar?.setOnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.action_save -> {
                    val joke = Joke(
                        typeView?.text.toString(),
                        setup?.text.toString(),
                        punchline?.text.toString(),
                        rating?.rating ?: 3.0f
                    )
                    jokeViewModel.insert(joke)
                    Toast.makeText(context, "Joke saved.", Toast.LENGTH_LONG).show()
                    dismiss()
                    true
                }
                else -> {
                    true
                }
            }
        }
//        (activity as AppCompatActivity).setSupportActionBar(toolbar)
//        val actionBar =(activity as AppCompatActivity).supportActionBar
//        actionBar?.setDisplayShowHomeEnabled(true)
//        actionBar?.setDisplayHomeAsUpEnabled(true)
//        actionBar?.setDefaultDisplayHomeAsUpEnabled(true)
//        actionBar?.setHomeButtonEnabled(true)
//        actionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
//
//        toolbar?.inflateMenu(R.menu.add_joke_menu)
//        toolbar?.setNavigationOnClickListener { v ->
//            dismiss()
//        }
//        setHasOptionsMenu(true)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        return dialog
    }


}
